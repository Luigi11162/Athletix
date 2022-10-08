package Controller.Richiesta;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.DAO.UtenteDAO;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaUtente")
public class RichiestaUtente extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaUtente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteDAO dao=new UtenteDAO();
		JSONObject jso=new JSONObject();

		if(request.getSession().getAttribute("user")!=null) {
			UtenteBean bean=(UtenteBean) request.getSession().getAttribute("user");
			jso.put("name", bean.getNome());
			jso.put("surname", bean.getCognome());
			jso.put("email", bean.getEmail());
			jso.put("status", bean.getStato_account());
			jso.put("gender", bean.getGenere());
			response.addHeader("json_user_reserved_area", jso.toString());
		}else{
			BufferedReader reader=request.getReader();
			JSONObject obj = new JSONObject(reader.readLine());
			UtenteBean bean;
			try {
				bean = dao.doRetrieveByKey(obj.getInt("id"));
				jso.put("name", bean.getNome());
				jso.put("surname", bean.getCognome());
				jso.put("gender", bean.getGenere());
				jso.put("status", bean.getStato_account());
			} catch (JSONException | SQLException e) {
				response.addHeader("res", "403");
				e.printStackTrace();
			}
		}
		response.addHeader("json_customers", jso.toString());
		response.addHeader("res", "200");
		
	}
	
}