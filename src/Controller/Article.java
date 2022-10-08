package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.*;
import Model.bean.*;

@WebServlet("/Article")
public class Article extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Article() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		ArticoloDAO dao=new ArticoloDAO();
		try {
			BufferedReader reader=request.getReader();
			JSONObject obj = new JSONObject(reader.readLine());
			
			ArticoloBean articolo = dao.doRetrieveByKey(obj.getInt("id"));
			request.getSession().setAttribute("article", articolo);
			response.addHeader("res", "200");
		} catch (SQLException e) {
			e.printStackTrace();
			response.addHeader("res", "403");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}
