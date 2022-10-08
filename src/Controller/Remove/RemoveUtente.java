package Controller.Remove;

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

@WebServlet("/RemoveUtente")
public class RemoveUtente extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RemoveUtente() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteDAO dao=new UtenteDAO();
		UtenteBean bean=new UtenteBean();
		
		BufferedReader reader=request.getReader();
		
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		
		try {
			if(request.getSession().getAttribute("user")!=null)
			{
				bean=dao.doRetrieveByKey(id);
				bean.setStato_account("Sospeso");
				dao.doUpdate(bean);
				response.addHeader("res", "200");
			}else if(request.getSession().getAttribute("operator")!=null)
			{
				dao.doDelete(id);
				response.addHeader("res", "200");
			}else
				response.addHeader("res", "403");
		} catch (JSONException | SQLException e) {
			response.addHeader("res", "403");
			e.printStackTrace();
		}
	}
	
}