package Controller.Richiesta;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import Model.DAO.ArticoloDAO;
import Model.bean.ArticoloBean;
import Model.bean.CategoriaBean;

@WebServlet("/RichiestaArticolo")
public class RichiestaArticolo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaArticolo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticoloBean bean=new ArticoloBean();
		ArticoloDAO dao=new ArticoloDAO();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		
		try {
			bean=dao.doRetrieveByKey(obj.getInt("id"));
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}
		
		JSONObject jso=new JSONObject();
		jso.put("img", bean.getImmagine());	
		jso.put("name", bean.getNome());	
		jso.put("description", bean.getDescrizione());	
		jso.put("price", String.valueOf(bean.getPrezzo()));	
		jso.put("brand", bean.getMarca());	
		jso.put("quantity", String.valueOf(bean.getQuantita()));	
		jso.put("material", bean.getMateriale());	
		jso.put("iva", String.valueOf(bean.getIva()));
		jso.put("unity", bean.getUnita().getId());

		List<Integer> categorieId=new ArrayList<>();
		Iterator<CategoriaBean> itCat=bean.getCategorie().iterator();
		while(itCat.hasNext())
		{
			categorieId.add(itCat.next().getId());
		}
		jso.put("category", categorieId);
		
		jso.put("size", bean.getTaglie());
		
		response.addHeader("json_article", jso.toString());
	}
	
}
