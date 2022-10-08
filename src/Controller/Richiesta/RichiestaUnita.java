package Controller.Richiesta;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import Model.DAO.UnitaDAO;
import Model.bean.UnitaBean;

@WebServlet("/RichiestaUnita")
public class RichiestaUnita extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaUnita() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UnitaBean bean=new UnitaBean();
		UnitaDAO dao=new UnitaDAO();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		try {
			bean=dao.doRetrieveByKey(obj.getInt("id"));
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}
		
		JSONObject jso=new JSONObject();
		jso.put("name", bean.getNome());
		jso.put("quantity", bean.getQuantita());
		
		response.addHeader("json_unity", jso.toString());
	}
	
}