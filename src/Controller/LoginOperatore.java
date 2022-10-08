package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.OperatoreDAO;
import Model.bean.OperatoreBean;
import Risorse.Risorse;

@WebServlet("/LoginOperatore")
public class LoginOperatore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OperatoreBean operator=null;	
	private OperatoreDAO model=new OperatoreDAO();
	
	public LoginOperatore() {
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
				request.getSession().setAttribute("operator", operator);
				response.addHeader("res","200");
			}
			else
			{
				response.addHeader("res","403");
			}
	}

	private boolean checkLogin(String email, String password){

		try {
			 operator=model.doRetrieveByEmail(email);
			try {
				password=Risorse.encrypt(password);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();		
			}
			
			if(operator.getPassword().equals(password))
			{
				return true;
			}
		
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());	
		}
		return false;
	}

}