package example.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import example.jdbc.bean.Article;
import example.jdbc.bean.Category;
import example.jdbc.utils.jdbcUtils;

public class ArticleDao implements DaoInterface<Article, Integer> {

    @Override
    public Collection<Article> retrieveAll() {
        // Creating empty ArrayList to hold Article objects
        Collection<Article> allArticles = new ArrayList<>();
        String sqlQuery = "SELECT * FROM article_master";

        try (Connection conn = jdbcUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlQuery)) {

            while (rs.next()) {
                int Id = rs.getInt(1);
                String name = rs.getString(2);

                // Correcting the way Category enum is retrieved
                Category category = Category.valueOf(rs.getString(3).toUpperCase());

                Date dateCreated = rs.getDate(4);
                String creatorName = rs.getString(5);

                // Building Article object with the retrieved data
                Article article = new Article(Id, name, category, dateCreated, creatorName);

                // Adding the Article object to the Collection
                allArticles.add(article);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Consider using proper logging in production code
        }
        return allArticles;
    }

    public Article retrieveOne(Integer id) {
        String query = "SELECT * FROM article_master WHERE id = ?";
        Article article = null;

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                article = new Article(
                        rs.getInt("id"),
                        rs.getString("name"),
                        Category.valueOf(rs.getString("category").toUpperCase()),  // Handling category enum correctly
                        rs.getDate("dateCreated"),
                        rs.getString("creatorName")
                );
            }

        } catch (Exception e) {
            e.printStackTrace(); // Consider using proper logging in production code
        }

        return article;
    }

    @Override
    public void create(Article newArticle) {
        String query = "INSERT INTO article_master (id,name, category, dateCreated, creatorName) VALUES (?,?, ?, ?, ?)";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newArticle.getId());
            stmt.setString(2, newArticle.getName());
            stmt.setString(3, newArticle.getCategory().name()); // Correctly storing the enum value as a string
            stmt.setDate(4, new java.sql.Date(newArticle.getDateCreated().getTime())); // Converting Date to java.sql.Date
            stmt.setString(5, newArticle.getCreatorName());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(); // Consider using proper logging in production code
        }
    }

    @Override
    public void update(Article modifyArticle) {
        String sqlQuery = "UPDATE article_master SET name=?, dateCreated=?, creatorName=? WHERE id=?";

        try (Connection conn = jdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            int Id = modifyArticle.getId();
            String name = modifyArticle.getName();
            Date dateCreated = modifyArticle.getDateCreated();
            String creatorName = modifyArticle.getCreatorName();

            pstmt.setString(1, name);
            pstmt.setDate(2, new java.sql.Date(dateCreated.getTime())); // Correctly converting to java.sql.Date
            pstmt.setString(3, creatorName);
            pstmt.setInt(4, Id);

            int updateCount = pstmt.executeUpdate();
            System.out.println(updateCount + " record updated");

        } catch (Exception e) {
            e.printStackTrace(); // Consider using proper logging in production code
        }
    }

    @Override
    public void delete(Integer id) {
        // Corrected table name in SQL query
        String sqlQuery = "DELETE FROM article_master WHERE id=?";

        try (Connection conn = jdbcUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            int updateCount = pstmt.executeUpdate();
            System.out.println(updateCount + " record deleted");

        } catch (Exception e) {
            e.printStackTrace(); // Consider using proper logging in production code
        }
    }
}
