package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Model.bean.OrdineRigoBean;
import Model.bean.OrdineTestataBean;
import Risorse.Risorse;

public class OrdineTestataDAO implements Model<OrdineTestataBean> {

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
	
	private static final String TABLE_NAME = "ordine_testata";
	public OrdineTestataDAO() {	
		super();
	}
	
	@Override
	public synchronized void doSave(OrdineTestataBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineTestataDAO.TABLE_NAME
				+ "(Data,Indirizzo_fatturazione,Ora,Pagamento,Indirizzo_spedizione,Utente) VALUES (?, ?, ?, ?, ?, ?)";

		String idSQL= "SELECT LAST_INSERT_ID() AS id";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, bean.getData());
			preparedStatement.setString(2, bean.getIndirizzo_Fatturazione());
			preparedStatement.setTime(3, bean.getOra());
			preparedStatement.setString(4, bean.getPagamento());
			preparedStatement.setString(5, bean.getIndirizzo_Spedizione());
			preparedStatement.setInt(6, bean.getIdUtente());
			preparedStatement.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.commit();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(idSQL);
			ResultSet rs=preparedStatement.executeQuery();
			
			if(!rs.next())
				throw new SQLException();
			bean.setId(rs.getInt("id"));
			preparedStatement.close();
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
	public synchronized int doUpdate(OrdineTestataBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+OrdineTestataDAO.TABLE_NAME+" SET Data=?, Indirizzo_fatturazione=?, Ora=?, Pagamento=?, Indirizzo_spedizione=?,  Utente=? WHERE  id" + OrdineTestataDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setDate(1, bean.getData());
			preparedStatement.setString(2, bean.getIndirizzo_Fatturazione());
			preparedStatement.setTime(3, bean.getOra());
			preparedStatement.setString(4, bean.getPagamento());
			preparedStatement.setString(5, bean.getIndirizzo_Spedizione());
			preparedStatement.setInt(6, bean.getIdUtente());
			preparedStatement.setInt(7, bean.getId());
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

		String deleteSQL = "DELETE FROM " + OrdineTestataDAO.TABLE_NAME + " WHERE id" + OrdineTestataDAO.TABLE_NAME + " = ?";

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
	public synchronized OrdineTestataBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineTestataBean bean = new OrdineTestataBean();
		String selectSQL = "SELECT * FROM " + OrdineTestataDAO.TABLE_NAME + " WHERE id" + OrdineTestataDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idordine_testata"));
				bean.setData(rs.getDate("Data"));
				bean.setIndirizzo_Fatturazione(rs.getString("Indirizzo_fatturazione"));
				bean.setOra(rs.getTime("Ora"));
				bean.setPagamento(rs.getString("Pagamento"));
				bean.setIndirizzo_Spedizione(rs.getString("Indirizzo_spedizione"));
				OrdineRigoDAO ord=new OrdineRigoDAO();
				bean.setOrdineRigo((List<OrdineRigoBean>)ord.doRetrieveByTestata(bean.getId()));
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
	public synchronized Collection<OrdineTestataBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<OrdineTestataBean> list=new ArrayList<>();
		String selectSQL = "SELECT * FROM " + OrdineTestataDAO.TABLE_NAME;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				OrdineTestataBean bean = new OrdineTestataBean();
				bean.setId(rs.getInt("idordine_testata"));
				bean.setData(rs.getDate("Data"));
				bean.setIndirizzo_Fatturazione(rs.getString("Indirizzo_fatturazione"));
				bean.setOra(rs.getTime("Ora"));
				bean.setPagamento(rs.getString("Pagamento"));
				bean.setIndirizzo_Spedizione(rs.getString("Indirizzo_spedizione"));
				OrdineRigoDAO ord=new OrdineRigoDAO();
				bean.setOrdineRigo((List<OrdineRigoBean>)ord.doRetrieveByTestata(bean.getId()));
				bean.setIdUtente(rs.getInt("Utente"));
				list.add(bean);
			}
			Risorse.sortOrdiniTestata((ArrayList<OrdineTestataBean>)list);

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

	public synchronized Collection<OrdineTestataBean> doRetrieveByUtente(int utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<OrdineTestataBean> list=new ArrayList<>();
		String selectSQL = "SELECT * FROM " + OrdineTestataDAO.TABLE_NAME + " WHERE Utente=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, utente);
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				OrdineTestataBean bean = new OrdineTestataBean();
				bean.setId(rs.getInt("idordine_testata"));
				bean.setData(rs.getDate("Data"));
				bean.setIndirizzo_Fatturazione(rs.getString("Indirizzo_fatturazione"));
				bean.setOra(rs.getTime("Ora"));
				bean.setPagamento(rs.getString("Pagamento"));
				bean.setIndirizzo_Spedizione(rs.getString("Indirizzo_spedizione"));
				OrdineRigoDAO ord=new OrdineRigoDAO();
				bean.setOrdineRigo((List<OrdineRigoBean>)ord.doRetrieveByTestata(bean.getId()));
				bean.setIdUtente(rs.getInt("Utente"));
				list.add(bean);
			}
			Risorse.sortOrdiniTestata((ArrayList<OrdineTestataBean>)list);

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
