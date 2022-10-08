package Controller.Richiesta;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import Model.DAO.CategoriaDAO;
import Model.bean.CategoriaBean;

@WebServlet("/RichiestaCategoria")
public class RichiestaCategoria extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaCategoria() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CategoriaBean> list=new ArrayList<>();
		CategoriaDAO dao=new CategoriaDAO();
		
		JSONObject jsoCat=new JSONObject();
		
		if(request.getSession().getAttribute("operator")!=null)
		{
			BufferedReader reader=request.getReader();
			JSONObject obj = new JSONObject(reader.readLine());
			
			CategoriaBean bean;
			try {
				bean = dao.doRetrieveByKey(obj.getInt("id"));
				jsoCat.put("category", bean.getNome());
				response.addHeader("json_category", jsoCat.toString());
			} catch (JSONException | SQLException e) {
				response.addHeader("res","403");
				e.printStackTrace();
			}
		}else {
			try {
				list=(ArrayList<CategoriaBean>) dao.doRetrieveAll();
			} catch (SQLException e) {
				response.addHeader("res","403");
				e.printStackTrace();
			}
			
			Iterator<CategoriaBean> itCat=list.iterator();
			while(itCat.hasNext()) {
				CategoriaBean bean=itCat.next();
				JSONObject jso=new JSONObject();
				jso.put("name", bean.getNome());
				jso.put("id", bean.getId());
				jsoCat.append("json_category_catalog", jso);
			}	
			response.addHeader("json_category_catalog", jsoCat.toString());
			
		}
		response.addHeader("res","200");
	}
	
	
}
