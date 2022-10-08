package Controller.Remove;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.DAO.ArticoloDAO;
import Model.bean.ArticoloBean;

@WebServlet("/CancellaArticolo")
public class CancellaArticolo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public CancellaArticolo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloDAO dao=new ArticoloDAO();
		
		BufferedReader reader=request.getReader();
		
		JSONObject obj = new JSONObject(reader.readLine());
		try {
			ArticoloBean articolo=dao.doRetrieveByKey(obj.getInt("id"));
			articolo.setQuantita(-1);
			dao.doUpdate(articolo);
		} catch (JSONException | SQLException e) {
			response.addHeader("res", "403");
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Catalogo");
		dispatcher.forward(request, response);
	}
	
}
