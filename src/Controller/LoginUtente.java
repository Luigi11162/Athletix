package Controller;

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

@WebServlet("/LoginUtente")
public class LoginUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtenteBean user=null;	
	private UtenteDAO model=new UtenteDAO();
	
	public LoginUtente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		String email=obj.getString("loginUser");
		String password=obj.getString("loginPassword");
		
		if(checkLogin(email, password))
		{
			if(request.getSession().getAttribute("cart")!=null)
			{
				ArrayList<CarrelloBean> carrello=(ArrayList<CarrelloBean>)request.getSession().getAttribute("cart");
				user.getCarrello().addAll(carrello);
			}
			request.getSession().setAttribute("user", user);
			response.addHeader("res","200");
		}
		else
		{
			response.addHeader("res","403");
		}
	}

	private boolean checkLogin(String email, String password){

		try {
			user=model.doRetrieveByEmail(email);
			try {
				password=Risorse.encrypt(password);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();		
			}
			
			if(user.getPassword().equals(password) && user.getStato_account().equals("Attivo"))
			{
				return true;
			}
		
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		return false;
		
	}
}
