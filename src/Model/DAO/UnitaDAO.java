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

import Model.bean.UnitaBean;

public class UnitaDAO implements Model<UnitaBean> {

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
	
	private static final String TABLE_NAME = "unita";
	public UnitaDAO() {
		
		super();
	}
	
	
	@Override
	public synchronized void doSave(UnitaBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UnitaDAO.TABLE_NAME
				+ "(Nome,Quantita) VALUES (?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setInt(2, bean.getQuantita());
			
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
	public synchronized int doUpdate(UnitaBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+UnitaDAO.TABLE_NAME+" SET Nome=?, Quantita=? WHERE  id" + UnitaDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setInt(2, bean.getQuantita());
			preparedStatement.setInt(3, bean.getId());
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

		String deleteSQL = "DELETE FROM " + UnitaDAO.TABLE_NAME + " WHERE id" + UnitaDAO.TABLE_NAME + " = ?";

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
	public synchronized UnitaBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UnitaBean bean = new UnitaBean();
		String selectSQL = "SELECT * FROM " + UnitaDAO.TABLE_NAME + " WHERE id" + UnitaDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idunita"));
				bean.setNome(rs.getString("Nome"));
				bean.setQuantita(rs.getInt("Quantita"));
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
	public synchronized Collection<UnitaBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + UnitaDAO.TABLE_NAME;
		Collection<UnitaBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UnitaBean bean = new UnitaBean();
				bean.setId(rs.getInt("idunita"));
				bean.setNome(rs.getString("Nome"));
				bean.setQuantita(rs.getInt("Quantita"));
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

	public synchronized UnitaBean doRetrieveByNome(String nome, int quantita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UnitaBean bean = new UnitaBean();
		String selectSQL = "SELECT * FROM " + UnitaDAO.TABLE_NAME + " WHERE Nome=? && Quantita=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			preparedStatement.setInt(2, quantita);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idunita"));
				bean.setNome(rs.getString("Nome"));
				bean.setQuantita(rs.getInt("Quantita"));
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
