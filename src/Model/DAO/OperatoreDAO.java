package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Model.bean.OperatoreBean;

public class OperatoreDAO implements Model<OperatoreBean> {

	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/sito");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private static final String TABLE_NAME = "operatore";
	public OperatoreDAO() {
		
		super();
	}
	
	@Override
	public synchronized void doSave(OperatoreBean bean) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OperatoreDAO.TABLE_NAME
				+ "(Nome,Cognome,Email,Password) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getCognome());
			preparedStatement.setString(3, bean.getEmail());
			preparedStatement.setString(4, bean.getPassword());
			
			preparedStatement.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	@Override
	public synchronized int doUpdate(OperatoreBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+OperatoreDAO.TABLE_NAME+" SET Nome=?, Cognome=?, Email=?,Password=?  WHERE  id" + OperatoreDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getCognome());
			preparedStatement.setString(3, bean.getEmail());
			preparedStatement.setString(4, bean.getPassword());
			preparedStatement.setInt(5, bean.getId());
			result=preparedStatement.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.commit();
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		return result;
	}

	@Override
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OperatoreDAO.TABLE_NAME + " WHERE id" + OperatoreDAO.TABLE_NAME + " = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized OperatoreBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OperatoreBean bean = new OperatoreBean();
		String selectSQL = "SELECT * FROM " + OperatoreDAO.TABLE_NAME + " WHERE id" + OperatoreDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idoperatore"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	@Override
	public synchronized Collection<OperatoreBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + OperatoreDAO.TABLE_NAME;
		Collection<OperatoreBean> list=new ArrayList<>();
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OperatoreBean bean = new OperatoreBean();
				bean.setId(rs.getInt("idoperatore"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				list.add(bean);
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return list;
	}

	public synchronized OperatoreBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OperatoreBean bean = new OperatoreBean();
		String selectSQL = "SELECT * FROM " + OperatoreDAO.TABLE_NAME + " WHERE Email=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idoperatore"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	
}
