package example.jdbc;

import java.sql.Date;
import java.util.Collection;
import java.util.Scanner;

import example.jdbc.dao.DaoInterface;
import example.jdbc.bean.*;
import example.jdbc.dao.*;

public class Assignment1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		DaoInterface< Article, Integer> daoRef =new ArticleDao();

		int ch,id1;
		String namestr,categoryStr  ,dateStr,creatorName ;
		 
		do {
			System.out.println("Article menu");
			System.out.println("==================");
			System.out.println("1.create Article");
			System.out.println("2.get Article By Id ");
			System.out.println("3.get all Article");
			System.out.println("4.updateArticle");
			System.out.println("5.delete Article");
			System.out.println("0.Exit");
			System.out.println("===================");
			System.out.println("choice");
			ch = in.nextInt();

			switch (ch) {
			case 1:
				System.out.println("enter value of id,name,category,dateCreated ,creatorName ");
				id1=in.nextInt();
				namestr = in.next();
				categoryStr=in.next();
				dateStr=in.next();
				creatorName=in.next();
				Category category = Category.valueOf(categoryStr.toUpperCase());
				Date dateCreated = java.sql.Date.valueOf(dateStr);
				
			    Article a= new Article(id1, namestr, category, dateCreated, creatorName);
			    daoRef.create(a);

		      break;

			case 2:
				System.out.println("Enter id");
				int idno;
				idno=in.nextInt();
				Article articleObj=daoRef.retrieveOne(idno);
				if(articleObj !=null)
					System.out.println(articleObj);
				else
					System.out.println("restaurant with given id does not exit");

				
				break;
			case 3:
				Collection<Article>allAvailableArticles= daoRef.retrieveAll();
				for(Article currentArticle:allAvailableArticles)
					System.out.println(currentArticle);
				System.out.println("========================");
				//allAvailableArticles.stream().
				//forEach(rst->System.out.println(rst));
                break;
			case 4:
				System.out.println("Enter id that you want to update");
				int idno1;
				idno1=in.nextInt();
				Article rst=daoRef.retrieveOne(idno1);
				rst.setName("mahakal");
				System.out.println("record updated");
				
				//Reflecting this to DB
				
				daoRef.update(rst);
           break;
				
            case 5:
            	System.out.println("Enter id that you want to delete");
				int idno2;
				idno2=in.nextInt();
            	daoRef.delete(idno2);

				
				break;
			case 0:
				System.out.println("Thanks for using code , coded by sagar rupnar");
				break;

				
			default:
				System.out.println("Wrong option selected:");
				break;

			}
		} while (ch != 0);


	}

}
