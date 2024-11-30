package example.jdbc.dao;

import java.util.Collection;

public interface DaoInterface<T, K> {
// a method to retrieve all records
	Collection<T> retrieveAll();
// a method to retrieve single record based upon its identity
	 T retrieveOne(K key);
// a method to insert a new record
	 void create (T t);
// a method to update an existing record
	 void update(T t);
// a method to delete an existing record
	 void delete(K key);
	
}
