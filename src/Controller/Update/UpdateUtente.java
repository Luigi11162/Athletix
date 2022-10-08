package Controller.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.UtenteDAO;
import Model.bean.CarrelloBean;
import Model.bean.UtenteBean;
import Risorse.Risorse;

@WebServlet("/UpdateUtente")
public class UpdateUtente  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static UtenteDAO model=new UtenteDAO();
	
	public UpdateUtente() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UtenteBean user=new UtenteBean();
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		if(request.getSession().getAttribute("user")==null) {
			
			int id=obj.getInt("id");
			String objString=obj.getString("json");
			obj=new JSONObject(objString);
			
			if(request.getSession().getAttribute("operator")!=null) {
				try {
					user=model.doRetrieveByKey(id);		
					String stato_account=obj.getString("status");
					user.setStato_account(stato_account);
					model.doUpdate(user);
				} catch (SQLException e) {
					response.addHeader("res","403");
					e.printStackTrace();
				}
			}else{
				String email=obj.getString("username");
				String password=obj.getString("password");	
				String nome =obj.getString("name");
				String cognome=obj.getString("surname");
				String genere=obj.getString("gender");
				
				try {
					password=Risorse.encrypt(password);
				} catch (NoSuchAlgorithmException e1) {
					response.addHeader("res","403");
					e1.printStackTrace();		
				}
					
				try {
					user.setNome(nome);
					user.setCognome(cognome);
					user.setGenere(genere);
					user.setEmail(email);
					user.setPassword(password);
					user.setStato_account("Attivo");
					
					if(id==-1) {
						if(!model.doRetrieveByEmail(email).getEmail().equals(email))
						{
							if(request.getSession().getAttribute("cart")!=null)
							{
								ArrayList<CarrelloBean> carrello=(ArrayList<CarrelloBean>)request.getSession().getAttribute("cart");
								user.setCarrello(carrello);
							}
							model.doSave(user);
							request.getSession().setAttribute("user", user);
						}
						else {
							response.addHeader("res","400");
							return;
						}
					}else{
						user.setId(id);
						model.doUpdate(user);
						request.getSession().setAttribute("user", user);
					}
						
				} catch (SQLException e) {
					System.out.println("Errore nell'update utente "+e.getMessage());
					response.addHeader("res","403");
					e.printStackTrace();
				}
		
			}
		} else {
			UtenteBean bean=(UtenteBean) request.getSession().getAttribute("user");
			String objString=obj.getString("json_user_reserved_area");
			obj=new JSONObject(objString);
			
			bean.setEmail(obj.getString("email"));	
			bean.setNome(obj.getString("name"));
			bean.setCognome(obj.getString("surname"));
			String password=obj.getString("password");
			if(password.length()==0) 
			{
				try {
					model.doUpdateWithoutPassword(bean);
				} catch (SQLException e) {
					response.addHeader("res","403");
					e.printStackTrace();
				}
			}else {
				try {
					password=Risorse.encrypt(password);
					bean.setPassword(password);
					model.doUpdate(bean);
				} catch (NoSuchAlgorithmException | SQLException e1) {
					response.addHeader("res","403");
					e1.printStackTrace();		
				}
			}
			
			request.getSession().setAttribute("user", bean);
		}
		response.addHeader("res","200");
	}
	
}
