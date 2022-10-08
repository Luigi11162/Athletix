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

import Model.bean.TagliaBean;
import Risorse.Risorse;

public class TagliaDAO implements Model<TagliaBean> {

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
	
	private static final String TABLE_NAME = "taglia";
	public TagliaDAO() {
		
		super();
	}
	
	@Override
	public synchronized void doSave(TagliaBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TagliaDAO.TABLE_NAME
				+ "(Taglia) VALUES (?)";

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getTaglia());
		
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
	public synchronized int doUpdate(TagliaBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+TagliaDAO.TABLE_NAME+" SET Taglia=? WHERE  id" + TagliaDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getTaglia());
			preparedStatement.setInt(2, bean.getId());
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

		String deleteSQL = "DELETE FROM " + TagliaDAO.TABLE_NAME + " WHERE id" + TagliaDAO.TABLE_NAME + " = ?";

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
	public synchronized TagliaBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TagliaBean bean = new TagliaBean();
		String selectSQL = "SELECT * FROM " + TagliaDAO.TABLE_NAME + " WHERE id" + TagliaDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idtaglia"));
				bean.setTaglia(rs.getString("Taglia"));
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
	public synchronized Collection<TagliaBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + TagliaDAO.TABLE_NAME;
		Collection<TagliaBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				TagliaBean bean = new TagliaBean();
				bean.setId(rs.getInt("idtaglia"));
				bean.setTaglia(rs.getString("Taglia"));
				list.add(bean);
			}
			Risorse.sortTaglie((ArrayList<TagliaBean>)list);
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

	public synchronized TagliaBean doRetrieveByTaglia(String taglia) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		TagliaBean bean = new TagliaBean();
		String selectSQL = "SELECT * FROM " + TagliaDAO.TABLE_NAME + " WHERE Taglia=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, taglia);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idtaglia"));
				bean.setTaglia(rs.getString("Taglia"));
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
	
	public synchronized Collection<TagliaBean> doRetrieveByArticolo(int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + TagliaDAO.TABLE_NAME+" NATURAL JOIN taglia_articolo WHERE idarticolo=?";
		Collection<TagliaBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, articolo);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
					TagliaBean bean = new TagliaBean();
					bean.setId(rs.getInt("idtaglia"));
					bean.setTaglia(rs.getString("Taglia"));
					bean.setQuantita(rs.getInt("Quantita"));
					list.add(bean);
			}
			Risorse.sortTaglie((ArrayList<TagliaBean>)list);
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
