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

import Model.bean.OrdineRigoBean;

public class OrdineRigoDAO implements Model<OrdineRigoBean> {

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
	
	private static final String TABLE_NAME = "ordine_rigo";
	public OrdineRigoDAO() {	
		super();
	}
	
	@Override
	public synchronized void doSave(OrdineRigoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineRigoDAO.TABLE_NAME
				+ "(Iva,Quantita,Prezzo,Articolo,Testata) VALUES (?, ?, ?, ?, ?)";

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bean.getIva());
			preparedStatement.setInt(2, bean.getQuantita());
			preparedStatement.setFloat(3, bean.getPrezzo());
			preparedStatement.setInt(4, bean.getArticolo().getId());
			preparedStatement.setInt(5, bean.getIdTestata());
		
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
	public synchronized int doUpdate(OrdineRigoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+OrdineRigoDAO.TABLE_NAME+" SET Iva=?, Quantita=?, Prezzo=?, Articolo=?, Testata=? WHERE  id" + OrdineRigoDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, bean.getIva());
			preparedStatement.setInt(2, bean.getQuantita());
			preparedStatement.setFloat(3, bean.getPrezzo());
			preparedStatement.setInt(4, bean.getArticolo().getId());
			preparedStatement.setInt(5, bean.getIdTestata());
			preparedStatement.setInt(6, bean.getId());
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

		String deleteSQL = "DELETE FROM " + OrdineRigoDAO.TABLE_NAME + " WHERE id" + OrdineRigoDAO.TABLE_NAME + " = ?";

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
	public synchronized OrdineRigoBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineRigoBean bean = new OrdineRigoBean();
		String selectSQL = "SELECT * FROM " + OrdineRigoDAO.TABLE_NAME + " WHERE id" + OrdineRigoDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idordine_rigo"));
				bean.setIva(rs.getInt("Iva"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				ArticoloDAO articolo=new ArticoloDAO();
				bean.setArticolo(articolo.doRetrieveByKey(rs.getInt("Articolo")));
				bean.setIdTestata(rs.getInt("Testata"));
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
	public synchronized Collection<OrdineRigoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<OrdineRigoBean> list=new ArrayList<>();
		String selectSQL = "SELECT * FROM " + OrdineRigoDAO.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				OrdineRigoBean bean = new OrdineRigoBean();
				bean.setId(rs.getInt("idordine_rigo"));
				bean.setIva(rs.getInt("Iva"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				ArticoloDAO articolo=new ArticoloDAO();
				bean.setArticolo(articolo.doRetrieveByKey(rs.getInt("Articolo")));
				bean.setIdTestata(rs.getInt("Testata"));
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
	

	public synchronized Collection<OrdineRigoBean> doRetrieveByTestata(int testata) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + OrdineRigoDAO.TABLE_NAME+" WHERE Testata=?";
		Collection<OrdineRigoBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, testata);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineRigoBean bean = new OrdineRigoBean();
				bean.setId(rs.getInt("idordine_rigo"));
				bean.setIva(rs.getInt("Iva"));
				bean.setQuantita(rs.getInt("Quantita"));
				bean.setPrezzo(rs.getFloat("Prezzo"));
				ArticoloDAO articolo=new ArticoloDAO();
				bean.setArticolo(articolo.doRetrieveByKey(rs.getInt("Articolo")));
				bean.setIdTestata(rs.getInt("Testata"));
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
