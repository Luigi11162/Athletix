package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DAO.*;
import Model.bean.*;

@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Catalogo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		ArticoloDAO dao=new ArticoloDAO();
		try{
			ServletContext cxt= getServletContext();
			ArrayList<ArticoloBean> articoli=(ArrayList<ArticoloBean>) dao.doRetrieveAll();
			
			articoli=(ArrayList<ArticoloBean>) articoli.stream().filter(articolo->articolo.getQuantita()!=-1).collect(Collectors.toList());
			cxt.setAttribute("articles", articoli);
			response.addHeader("res", "200");
			
		} catch (SQLException e) {
			response.addHeader("res", "403");
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}
