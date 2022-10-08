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

import Model.bean.RecensioneBean;

public class RecensioneDAO implements Model<RecensioneBean>{

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
	
	private static final String TABLE_NAME = "recensione";
	
	public RecensioneDAO() {
		super();
	}	

	@Override
	public synchronized void doSave(RecensioneBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + RecensioneDAO.TABLE_NAME
				+ "(idutente,idarticolo,Descrizione,Data) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bean.getIdUtente());
			preparedStatement.setInt(2, bean.getIdArticolo());
			preparedStatement.setString(3, bean.getDescrizione());
			preparedStatement.setDate(4, bean.getData());
			
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
	public synchronized int doUpdate(RecensioneBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+RecensioneDAO.TABLE_NAME+" SET Descrizione=?, SET Data=? WHERE  id" + RecensioneDAO.TABLE_NAME + " =? AND idutente=? AND idarticolo=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getDescrizione());
			preparedStatement.setDate(2, bean.getData());
			preparedStatement.setInt(3, bean.getId());
			preparedStatement.setInt(4, bean.getIdUtente());
			preparedStatement.setInt(5, bean.getIdArticolo());
			
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
		return false;
	}

	public synchronized boolean doDelete(int code, int utente, int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + RecensioneDAO.TABLE_NAME + " WHERE id" + RecensioneDAO.TABLE_NAME + " = ? AND idutente=? AND idarticolo=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setInt(2, utente);
			preparedStatement.setInt(3, articolo);

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
	public synchronized RecensioneBean doRetrieveByKey(int code) throws SQLException {
		return null;
	}
	
	public synchronized RecensioneBean doRetrieveByKey(int code, int utente, int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		RecensioneBean bean = new RecensioneBean();
		String selectSQL = "SELECT * FROM " + RecensioneDAO.TABLE_NAME + " WHERE id" + RecensioneDAO.TABLE_NAME + " =? AND idutente=? AND idarticolo=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setInt(2, utente);
			preparedStatement.setInt(3, articolo);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idrecensione"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setData(rs.getDate("Data"));
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
	public synchronized Collection<RecensioneBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM " + RecensioneDAO.TABLE_NAME;
		Collection<RecensioneBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setId(rs.getInt("idrecensione"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setData(rs.getDate("Data"));
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

	public synchronized Collection<RecensioneBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<RecensioneBean> list=new ArrayList<>();
		
		String selectSQL = "SELECT * FROM " + RecensioneDAO.TABLE_NAME + " WHERE idutente=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setId(rs.getInt("idrecensione"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setData(rs.getDate("Data"));
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
	
	public synchronized Collection<RecensioneBean> doRetrieveByArticolo(int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<RecensioneBean> list=new ArrayList<>();
		
		String selectSQL = "SELECT * FROM " + RecensioneDAO.TABLE_NAME + " WHERE idarticolo=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, articolo);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				bean.setId(rs.getInt("idrecensione"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setData(rs.getDate("Data"));
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
