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

import Model.bean.CarrelloBean;
import Model.bean.OrdineTestataBean;
import Model.bean.PagamentoBean;
import Model.bean.RecapitoBean;
import Model.bean.RecensioneBean;
import Model.bean.UtenteBean;

public class UtenteDAO implements Model<UtenteBean>{

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
	
	private static final String TABLE_NAME = "utente";
	public UtenteDAO() {
		
		super();
	}
	
	@Override
	public synchronized void doSave(UtenteBean bean) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UtenteDAO.TABLE_NAME
				+ "(Nome,Cognome,Genere,Email,Password,Stato_Account) VALUES (?, ?, ?, ?, ?, ?)";

		String idSQL= "SELECT LAST_INSERT_ID() AS id";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getCognome());
			preparedStatement.setString(3,bean.getGenere());
			preparedStatement.setString(4, bean.getEmail());
			preparedStatement.setString(5, bean.getPassword());
			preparedStatement.setString(6, bean.getStato_account());
			
			preparedStatement.executeUpdate();
			
			connection.setAutoCommit(false);
			connection.commit();
			
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(idSQL);
			ResultSet rs=preparedStatement.executeQuery();
			
			if(!rs.next())
				throw new SQLException();
			bean.setId(rs.getInt("id"));
			
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
	public synchronized int doUpdate(UtenteBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+UtenteDAO.TABLE_NAME+" SET Nome=?, Cognome=?, Genere=?, Email=?,Password=?,Stato_Account=?  WHERE  id" + UtenteDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getCognome());
			preparedStatement.setString(3, bean.getGenere());
			preparedStatement.setString(4, bean.getEmail());
			preparedStatement.setString(5, bean.getPassword());
			preparedStatement.setString(6, bean.getStato_account());
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

		String deleteSQL = "DELETE FROM " + UtenteDAO.TABLE_NAME + " WHERE id" + UtenteDAO.TABLE_NAME + " = ?";

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
	public synchronized UtenteBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		String selectSQL = "SELECT * FROM " + UtenteDAO.TABLE_NAME + " WHERE id" + UtenteDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idutente"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setGenere(rs.getString("Genere"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setStato_account(rs.getString("Stato_Account"));
				CarrelloDAO carrello=new CarrelloDAO();
				bean.setCarrello((List<CarrelloBean>)carrello.doRetrieveByUtente(bean.getId()));
				OrdineTestataDAO ord= new OrdineTestataDAO();
				bean.setOrdini((List<OrdineTestataBean>) ord.doRetrieveByUtente(bean.getId()));
				RecensioneDAO recensione=new RecensioneDAO();
				bean.setRecensioni((List<RecensioneBean>) recensione.doRetrieveByUtente(bean.getId()));
				PagamentoDAO pagamento=new PagamentoDAO();
				bean.setPagamenti((List<PagamentoBean>)pagamento.doRetrieveByUtente(bean.getId()));
				RecapitoDAO recapito=new RecapitoDAO();
				bean.setRecapiti((List<RecapitoBean>)recapito.doRetrieveByUtente(bean.getId()));
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
	public synchronized Collection<UtenteBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT * FROM " + UtenteDAO.TABLE_NAME;
		Collection<UtenteBean> list=new ArrayList<>();
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();
				bean.setId(rs.getInt("idutente"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setGenere(rs.getString("Genere"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setStato_account(rs.getString("Stato_Account"));
				CarrelloDAO carrello=new CarrelloDAO();
				bean.setCarrello((List<CarrelloBean>)carrello.doRetrieveByUtente(bean.getId()));
				OrdineTestataDAO ord= new OrdineTestataDAO();
				bean.setOrdini((List<OrdineTestataBean>) ord.doRetrieveByUtente(bean.getId()));
				RecensioneDAO recensione=new RecensioneDAO();
				bean.setRecensioni((List<RecensioneBean>) recensione.doRetrieveByUtente(bean.getId()));
				PagamentoDAO pagamento=new PagamentoDAO();
				bean.setPagamenti((List<PagamentoBean>)pagamento.doRetrieveByUtente(bean.getId()));
				RecapitoDAO recapito=new RecapitoDAO();
				bean.setRecapiti((List<RecapitoBean>)recapito.doRetrieveByUtente(bean.getId()));
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
	
	public synchronized UtenteBean doRetrieveByEmail(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		String selectSQL = "SELECT * FROM " + UtenteDAO.TABLE_NAME + " WHERE Email=?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				bean.setId(rs.getInt("idutente"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setGenere(rs.getString("Genere"));
				bean.setEmail(rs.getString("Email"));
				bean.setPassword(rs.getString("Password"));
				bean.setStato_account(rs.getString("Stato_Account"));
				CarrelloDAO carrello=new CarrelloDAO();
				bean.setCarrello((List<CarrelloBean>)carrello.doRetrieveByUtente(bean.getId()));
				OrdineTestataDAO ord= new OrdineTestataDAO();
				bean.setOrdini((List<OrdineTestataBean>) ord.doRetrieveByUtente(bean.getId()));
				RecensioneDAO recensione=new RecensioneDAO();
				bean.setRecensioni((List<RecensioneBean>) recensione.doRetrieveByUtente(bean.getId()));
				PagamentoDAO pagamento=new PagamentoDAO();
				bean.setPagamenti((List<PagamentoBean>)pagamento.doRetrieveByUtente(bean.getId()));
				RecapitoDAO recapito=new RecapitoDAO();
				bean.setRecapiti((List<RecapitoBean>)recapito.doRetrieveByUtente(bean.getId()));
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

	public synchronized int doUpdateWithoutPassword(UtenteBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		
		String insertSQL = "UPDATE "+UtenteDAO.TABLE_NAME+" SET Nome=?, Cognome=?, Genere=?, Email=?,Stato_Account=?  WHERE  id" + UtenteDAO.TABLE_NAME + " =?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, bean.getNome());
			preparedStatement.setString(2, bean.getCognome());
			preparedStatement.setString(3, bean.getGenere());
			preparedStatement.setString(4, bean.getEmail());
			preparedStatement.setString(5, bean.getStato_account());
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
	
}
