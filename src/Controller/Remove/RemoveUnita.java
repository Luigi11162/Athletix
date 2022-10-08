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

import Model.DAO.UnitaDAO;

@WebServlet("/RemoveUnita")
public class RemoveUnita extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RemoveUnita() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UnitaDAO dao=new UnitaDAO();
		
		BufferedReader reader=request.getReader();
		
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		try {
			dao.doDelete(id);
			response.addHeader("res", "200");
		} catch (JSONException | SQLException e) {
			response.addHeader("res", "403");
			e.printStackTrace();
		}
	}
	
}