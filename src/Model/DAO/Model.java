package Model.DAO;

import java.sql.SQLException;
import java.util.Collection;

public interface Model<T>{
	
	void doSave(T bean) throws SQLException;
	int doUpdate(T bean) throws SQLException;
	boolean doDelete(int code) throws SQLException;
	T doRetrieveByKey(int code) throws SQLException;
	Collection<T> doRetrieveAll()throws SQLException;
	
}
