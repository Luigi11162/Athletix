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

import Model.bean.RecapitoBean;

public class RecapitoDAO implements Model<RecapitoBean>{

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

	private static final String TABLE_NAME = "recapito";
	
	public RecapitoDAO() {
		super();
	}

	@Override
	public synchronized void doSave(RecapitoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecapitoDAO.TABLE_NAME
				+ "(Indirizzo,Citta,CAP,Utente) VALUES (?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getIndirizzo());
			preparedStatement.setString(2, bean.getCitta());
			preparedStatement.setInt(3, bean.getCap());
			preparedStatement.setInt(4, bean.getIdUtente());
			
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
	public synchronized int doUpdate(RecapitoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+RecapitoDAO.TABLE_NAME+" SET Indirizzo=?, Citta=?, CAP=?, Utente=? WHERE  id" + RecapitoDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getIndirizzo());
			preparedStatement.setString(2, bean.getCitta());
			preparedStatement.setInt(3, bean.getCap());
			preparedStatement.setInt(4, bean.getIdUtente());
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

		String deleteSQL = "DELETE FROM " + RecapitoDAO.TABLE_NAME + " WHERE id" + RecapitoDAO.TABLE_NAME + " = ?";

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
	public synchronized RecapitoBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		RecapitoBean bean = new RecapitoBean();
		String selectSQL = "SELECT * FROM " + RecapitoDAO.TABLE_NAME + " WHERE id" + RecapitoDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idrecapito"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCap(rs.getInt("CAP"));
				bean.setIdUtente(rs.getInt("Utente"));
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
	public synchronized Collection<RecapitoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<RecapitoBean> list=new ArrayList<>();
		String selectSQL = "SELECT * FROM " + RecapitoDAO.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				RecapitoBean bean = new RecapitoBean();
				
				bean.setId(rs.getInt("idrecapito"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCap(rs.getInt("CAP"));
				bean.setIdUtente(rs.getInt("Utente"));
				
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
	

	public synchronized Collection<RecapitoBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + RecapitoDAO.TABLE_NAME+" WHERE Utente=?";
		Collection<RecapitoBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecapitoBean bean = new RecapitoBean();
				
				bean.setId(rs.getInt("idrecapito"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCap(rs.getInt("CAP"));
				
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
}