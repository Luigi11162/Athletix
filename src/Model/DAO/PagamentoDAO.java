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

import Model.bean.PagamentoBean;

public class PagamentoDAO implements Model<PagamentoBean>{

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

	private static final String TABLE_NAME = "pagamento";
	
	public PagamentoDAO() {
		super();
	}

	@Override
	public synchronized void doSave(PagamentoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PagamentoDAO.TABLE_NAME
				+ "(Numero,Nome,Scadenza,Codice,Utente) VALUES (?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNumero());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getScadenza());
			preparedStatement.setInt(4, bean.getCodice());
			preparedStatement.setInt(5, bean.getIdUtente());
			
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
	public synchronized int doUpdate(PagamentoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+PagamentoDAO.TABLE_NAME+" SET Numero=?, Nome=?, Scadenza=?, Codice=?, Utente=? WHERE  id" + PagamentoDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNumero());
			preparedStatement.setString(2, bean.getNome());
			preparedStatement.setString(3, bean.getScadenza());
			preparedStatement.setInt(4, bean.getCodice());
			preparedStatement.setInt(5, bean.getIdUtente());
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

		String deleteSQL = "DELETE FROM " + PagamentoDAO.TABLE_NAME + " WHERE id" + PagamentoDAO.TABLE_NAME + " = ?";

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
	public synchronized PagamentoBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PagamentoBean bean = new PagamentoBean();
		String selectSQL = "SELECT * FROM " + PagamentoDAO.TABLE_NAME + " WHERE id" + PagamentoDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idpagamento"));
				bean.setNome(rs.getString("Nome"));
				bean.setNumero(rs.getString("Numero"));
				bean.setScadenza(rs.getString("Scadenza"));
				bean.setCodice(rs.getInt("Codice"));
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
	public synchronized Collection<PagamentoBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<PagamentoBean> list=new ArrayList<>();
		String selectSQL = "SELECT * FROM " + PagamentoDAO.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				PagamentoBean bean = new PagamentoBean();
				
				bean.setId(rs.getInt("idpagamento"));
				bean.setNome(rs.getString("Nome"));
				bean.setNumero(rs.getString("Numero"));
				bean.setScadenza(rs.getString("Scadenza"));
				bean.setCodice(rs.getInt("Codice"));
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
	

	public synchronized Collection<PagamentoBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + PagamentoDAO.TABLE_NAME+" WHERE Utente=?";
		Collection<PagamentoBean> list=new ArrayList<>();
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PagamentoBean bean = new PagamentoBean();
				
				bean.setId(rs.getInt("idpagamento"));
				bean.setNome(rs.getString("Nome"));
				bean.setNumero(rs.getString("Numero"));
				bean.setScadenza(rs.getString("Scadenza"));
				bean.setCodice(rs.getInt("Codice"));
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
	
	public synchronized PagamentoBean doRetrieveByNumero(String numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PagamentoBean bean = new PagamentoBean();
		String selectSQL = "SELECT * FROM " + PagamentoDAO.TABLE_NAME + " WHERE Numero=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, numero);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idpagamento"));
				bean.setNome(rs.getString("Nome"));
				bean.setNumero(rs.getString("Numero"));
				bean.setScadenza(rs.getString("Scadenza"));
				bean.setCodice(rs.getInt("Codice"));
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
}
