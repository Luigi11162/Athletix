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

import Model.bean.CategoriaBean;

public class CategoriaDAO implements Model<CategoriaBean>{

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
	
	private static final String TABLE_NAME = "categoria";
	public CategoriaDAO() {
		
		super();
	}
	
	@Override
	public synchronized void doSave(CategoriaBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + CategoriaDAO.TABLE_NAME
				+ "(Nome) VALUES (?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
	
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
	public synchronized int doUpdate(CategoriaBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+CategoriaDAO.TABLE_NAME+" SET Nome=? WHERE  id" + CategoriaDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
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

		String deleteSQL = "DELETE FROM " + CategoriaDAO.TABLE_NAME + " WHERE id" + CategoriaDAO.TABLE_NAME + " = ?";

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
	public synchronized CategoriaBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaBean bean = new CategoriaBean();
		String selectSQL = "SELECT * FROM " + CategoriaDAO.TABLE_NAME + " WHERE id" + CategoriaDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idcategoria"));
				bean.setNome(rs.getString("Nome"));
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
	public synchronized Collection<CategoriaBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT * FROM " + CategoriaDAO.TABLE_NAME;
		Collection<CategoriaBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();
				bean.setId(rs.getInt("idcategoria"));
				bean.setNome(rs.getString("Nome"));
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

	public synchronized Collection<CategoriaBean> doRetrieveByArticolo(int articolo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT categoria.idcategoria, nome FROM " + CategoriaDAO.TABLE_NAME+" NATURAL JOIN appartenenza WHERE idarticolo=? ";
		Collection<CategoriaBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, articolo);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CategoriaBean bean = new CategoriaBean();
				bean.setId(rs.getInt("idcategoria"));
				bean.setNome(rs.getString("Nome"));
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

	public synchronized CategoriaBean doRetrieveByNome(String nome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaBean bean = new CategoriaBean();
		String selectSQL = "SELECT * FROM " + CategoriaDAO.TABLE_NAME + " WHERE Nome=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nome);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idcategoria"));
				bean.setNome(rs.getString("Nome"));
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
