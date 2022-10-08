package Controller.Update;

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

@WebServlet("/UpdateOperatore")
public class UpdateOperatore  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OperatoreDAO model=new OperatoreDAO();
	
	public UpdateOperatore() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		OperatoreBean operator=new OperatoreBean();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		//modificare i nomi json
		int id=obj.getInt("id");
		String email=obj.getString("email");
		String password=obj.getString("password");
		String nome=obj.getString("nome");
		String cognome=obj.getString("cognome");
		
		try {
			password=Risorse.encrypt(password);
		} catch (NoSuchAlgorithmException e1) {
			response.addHeader("res","403");
			e1.printStackTrace();		
		}
		
		try {
			operator.setNome(nome);
			operator.setCognome(cognome);
			operator.setEmail(email);
			operator.setPassword(password);
			
			if(id==-1) {		
				if(!model.doRetrieveByEmail(email).getEmail().equals(email))
				{	
					model.doSave(operator);
					request.getSession().setAttribute("operator", operator);
					response.addHeader("res","200");
				}
				else
					response.addHeader("res","403");
			}else {
				operator.setId(id);
				model.doUpdate(operator);
				request.getSession().setAttribute("operator", operator);
				response.addHeader("res","200");
			}
		} catch (SQLException e) {
			System.out.println("Errore nell'update operatore "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
	}
	
}