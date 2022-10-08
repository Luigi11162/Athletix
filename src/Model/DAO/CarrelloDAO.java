package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Model.bean.CarrelloBean;

public class CarrelloDAO implements Model<CarrelloBean>{

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
	
	private static final String TABLE_NAME = "carrello";
	
	public CarrelloDAO() {
		super();
	}

	@Override
	public void doSave(CarrelloBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + CarrelloDAO.TABLE_NAME
				+ "(idutente,idarticolo,Quantita_carrello,Taglia) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bean.getIdUtente());
			preparedStatement.setInt(2, bean.getIdArticolo());
			preparedStatement.setInt(3, bean.getQuantita());
			if(bean.getTaglia()!=null)
				preparedStatement.setInt(4, bean.getTaglia().getId());
			else
				preparedStatement.setNull(4, Types.INTEGER);
			
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
	public int doUpdate(CarrelloBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+CarrelloDAO.TABLE_NAME+" SET Quantita_carrello=?, SET Taglia=? WHERE  id" + CarrelloDAO.TABLE_NAME + " =? AND idutente=? AND idarticolo=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bean.getQuantita());
			if(bean.getTaglia()!=null)
				preparedStatement.setInt(2, bean.getTaglia().getId());
			else
				preparedStatement.setNull(2, Types.INTEGER);
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
	
	public boolean doDelete(int code,int utente, int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + CarrelloDAO.TABLE_NAME + " WHERE id" + CarrelloDAO.TABLE_NAME + " = ? AND idutente=? AND idarticolo=?";

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
	public CarrelloBean doRetrieveByKey(int code) throws SQLException {
		return null;
	}
	
	public synchronized CarrelloBean doRetrieveByKey(int utente, int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CarrelloBean bean = new CarrelloBean();
		TagliaDAO tagliaDao=new TagliaDAO();
		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME + " WHERE idutente=? AND idarticolo=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			preparedStatement.setInt(2, articolo);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idcarrello"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setQuantita(rs.getInt("Quantita_carrello"));
				bean.setTaglia(tagliaDao.doRetrieveByKey(rs.getInt("Taglia")));
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
	public Collection<CarrelloBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TagliaDAO tagliaDao=new TagliaDAO();
		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME;
		Collection<CarrelloBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CarrelloBean bean = new CarrelloBean();
				bean.setId(rs.getInt("idcarrello"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setQuantita(rs.getInt("Quantita_carrello"));
				bean.setTaglia(tagliaDao.doRetrieveByKey(rs.getInt("Taglia")));
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

	public synchronized Collection<CarrelloBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TagliaDAO tagliaDao=new TagliaDAO();
		Collection<CarrelloBean> list=new ArrayList<>();
		
		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME + " WHERE idutente=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CarrelloBean bean = new CarrelloBean();
				bean.setId(rs.getInt("idcarrello"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setQuantita(rs.getInt("Quantita_carrello"));
				bean.setTaglia(tagliaDao.doRetrieveByKey(rs.getInt("Taglia")));
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
	
	public synchronized CarrelloBean doRetrieveByKeyAndSize(int utente, int articolo, int taglia) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TagliaDAO tagliaDao=new TagliaDAO();
		CarrelloBean bean = new CarrelloBean();
		
		String selectSQL = "SELECT * FROM " + CarrelloDAO.TABLE_NAME + " WHERE idutente=? AND idarticolo=? AND Taglia=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			preparedStatement.setInt(2, articolo);
			preparedStatement.setInt(3, taglia);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {		
				bean.setId(rs.getInt("idcarrello"));
				bean.setIdUtente(rs.getInt("idutente"));
				bean.setIdArticolo(rs.getInt("idarticolo"));
				bean.setQuantita(rs.getInt("Quantita_carrello"));
				bean.setTaglia(tagliaDao.doRetrieveByKey(rs.getInt("Taglia")));
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