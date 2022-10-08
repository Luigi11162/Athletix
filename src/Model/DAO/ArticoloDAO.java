package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Model.bean.ArticoloBean;
import Model.bean.CategoriaBean;
import Model.bean.RecensioneBean;
import Model.bean.TagliaBean;

public class ArticoloDAO implements Model<ArticoloBean>{

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
		
		private static final String TABLE_NAME = "articolo";
		public ArticoloDAO() {
			
			super();
		}
		
		@Override
		public synchronized void doSave(ArticoloBean bean) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String insertSQL = "INSERT INTO " + ArticoloDAO.TABLE_NAME
					+ "(Nome,Prezzo,Descrizione,Marca,Quantita,Materiale,Iva,Unita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					
			String idSQL= "SELECT LAST_INSERT_ID() AS id";
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, bean.getNome());
				preparedStatement.setFloat(2, bean.getPrezzo());
				preparedStatement.setString(3, bean.getDescrizione());
				preparedStatement.setString(4, bean.getMarca());
				preparedStatement.setInt(5, bean.getQuantita());
				preparedStatement.setString(6, bean.getMateriale());
				preparedStatement.setInt(7, bean.getIva());
				preparedStatement.setInt(8, bean.getUnita().getId());
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
				
				Iterator<CategoriaBean> itCat=bean.getCategorie().iterator();
				while(itCat.hasNext()) {
					addCategoria(bean.getId(),itCat.next());
				}
				
				Iterator<TagliaBean> itTag=bean.getTaglie().iterator();
				while(itTag.hasNext()) {	
					addTaglia(bean.getId(),itTag.next());
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
		}

		@Override
		public synchronized int doUpdate(ArticoloBean bean) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			int result = 0;
			
			String insertSQL = "UPDATE "+ArticoloDAO.TABLE_NAME+" SET Nome= ?, Prezzo=?, Descrizione=?, Marca=?, Quantita=?, Materiale=?, Iva=?, Unita=?  WHERE  id" + ArticoloDAO.TABLE_NAME + " =?";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, bean.getNome());
				preparedStatement.setFloat(2, bean.getPrezzo());
				preparedStatement.setString(3, bean.getDescrizione());
				preparedStatement.setString(4, bean.getMarca());
				preparedStatement.setInt(5, bean.getQuantita());
				preparedStatement.setString(6, bean.getMateriale());
				preparedStatement.setInt(7, bean.getIva());
				preparedStatement.setInt(8, bean.getUnita().getId());
				preparedStatement.setInt(9, bean.getId());
				result=preparedStatement.executeUpdate();
				
				preparedStatement.close();
				
				deleteCategoria(bean.getId());
				deleteTaglia(bean.getId());
				
				Iterator<CategoriaBean> itCat=bean.getCategorie().iterator();
				while(itCat.hasNext()) {
					addCategoria(bean.getId(),itCat.next());
				}
				
				Iterator<TagliaBean> itTag=bean.getTaglie().iterator();
				while(itTag.hasNext()) {	
					addTaglia(bean.getId(),itTag.next());
				}		
				
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

			String deleteSQL = "DELETE FROM " + ArticoloDAO.TABLE_NAME + " WHERE id" + ArticoloDAO.TABLE_NAME + " = ?";

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
		public synchronized ArticoloBean doRetrieveByKey(int code) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ArticoloBean bean = new ArticoloBean();
			String selectSQL = "SELECT * FROM " + ArticoloDAO.TABLE_NAME + " WHERE id" + ArticoloDAO.TABLE_NAME + " =?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, code);
				ResultSet rs = preparedStatement.executeQuery();

				if(rs.next()) {
					bean.setId(rs.getInt("idarticolo"));
					bean.setNome(rs.getString("Nome"));
					bean.setPrezzo(rs.getFloat("Prezzo"));
					bean.setDescrizione(rs.getString("Descrizione"));
					bean.setMarca(rs.getString("Marca"));
					bean.setMateriale(rs.getString("Materiale"));
					bean.setQuantita(rs.getInt("Quantita"));
					bean.setIva(rs.getInt("Iva"));
					UnitaDAO unita=new UnitaDAO();
					bean.setUnita(unita.doRetrieveByKey(rs.getInt("Unita")));
					CategoriaDAO categoria=new CategoriaDAO();
					bean.setCategorie((List<CategoriaBean>)categoria.doRetrieveByArticolo(bean.getId()));
					TagliaDAO taglia=new TagliaDAO();
					bean.setTaglie((List<TagliaBean>) taglia.doRetrieveByArticolo(bean.getId()));
					RecensioneDAO recensione=new RecensioneDAO();
					bean.setRecensioni((List<RecensioneBean>) recensione.doRetrieveByArticolo(bean.getId()));
					bean.setImmagine(String.valueOf(bean.getId()));					
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
		public synchronized Collection<ArticoloBean> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			String selectSQL = "SELECT * FROM " + ArticoloDAO.TABLE_NAME;
			Collection<ArticoloBean> list=new ArrayList<>();
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					ArticoloBean bean = new ArticoloBean();
					bean.setId(rs.getInt("idarticolo"));
					bean.setNome(rs.getString("Nome"));
					bean.setPrezzo(rs.getFloat("Prezzo"));
					bean.setDescrizione(rs.getString("Descrizione"));
					bean.setMarca(rs.getString("Marca"));
					bean.setMateriale(rs.getString("Materiale"));
					bean.setQuantita(rs.getInt("Quantita"));
					bean.setIva(rs.getInt("Iva"));
					UnitaDAO unita=new UnitaDAO();
					bean.setUnita(unita.doRetrieveByKey(rs.getInt("Unita")));
					CategoriaDAO categoria=new CategoriaDAO();
					bean.setCategorie((List<CategoriaBean>)categoria.doRetrieveByArticolo(bean.getId()));
					TagliaDAO taglia=new TagliaDAO();
					bean.setTaglie((List<TagliaBean>) taglia.doRetrieveByArticolo(bean.getId()));
					RecensioneDAO recensione=new RecensioneDAO();
					bean.setRecensioni((List<RecensioneBean>) recensione.doRetrieveByArticolo(bean.getId()));
					bean.setImmagine("/img/"+bean.getId()+".jpg");	
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
		
		public synchronized int addCategoria(int articolo, CategoriaBean categoria) throws SQLException{
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			int result = 0;
			
			String selectSQL = "INSERT INTO appartenenza VALUES (?,?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, articolo);
				preparedStatement.setInt(2, categoria.getId());
				result=preparedStatement.executeUpdate();
				
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
		
		public synchronized int deleteCategoria(int articolo) throws SQLException{
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			int result = 0;
			
			String selectSQL = "DELETE FROM appartenenza WHERE idarticolo=?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, articolo);
				result=preparedStatement.executeUpdate();
				
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
		
		public synchronized int addTaglia(int articolo, TagliaBean taglia) throws SQLException{
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			int result = 0;
			
			String selectSQL = "INSERT INTO taglia_articolo VALUES (?,?,?)";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, articolo);
				preparedStatement.setInt(2, taglia.getId());
				preparedStatement.setInt(3, taglia.getQuantita());
				result=preparedStatement.executeUpdate();
				
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
		
		public synchronized int deleteTaglia(int articolo) throws SQLException{
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			int result = 0;
			
			String selectSQL = "DELETE FROM taglia_articolo WHERE idarticolo=?";

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, articolo);
				result=preparedStatement.executeUpdate();
				
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
