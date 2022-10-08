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

import Model.DAO.TagliaDAO;
import Model.bean.TagliaBean;

@WebServlet("/RichiestaTaglia")
public class RichiestaTaglia extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaTaglia() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TagliaBean bean=new TagliaBean();
		TagliaDAO dao=new TagliaDAO();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		try {
			bean=dao.doRetrieveByKey(obj.getInt("id"));
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}
		
		JSONObject jso=new JSONObject();
		jso.put("size", bean.getTaglia());
		
		response.addHeader("json_size", jso.toString());
	}
	
}