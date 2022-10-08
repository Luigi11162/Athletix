package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.*;
import Model.bean.*;

@WebServlet("/CatalogoCategorie")
public class CatalogoCategorie extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public CatalogoCategorie() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArticoloDAO dao=new ArticoloDAO();
		ServletContext cxt= getServletContext();
		ArrayList<ArticoloBean> articoli=new ArrayList<>();
		try {
			articoli =(ArrayList<ArticoloBean>) dao.doRetrieveAll();
			articoli=(ArrayList<ArticoloBean>) articoli.stream().filter(articolo->articolo.getQuantita()!=-1).collect(Collectors.toList());
			cxt.setAttribute("articles", articoli);
		} catch (SQLException e1) {
			response.addHeader("res", "403");
			e1.printStackTrace();
		}
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
		
		String[] categorie=JSONObject.getNames(obj);
		
		int i=0;
		for(String categoria:categorie) {
			if(obj.getString(categoria).equals("no"))
				i++;
		}

		
		
		if(i!=categorie.length-1) {
			ArrayList<ArticoloBean> newArticoli=new ArrayList<>();
			ArrayList<String> listCat=new ArrayList<>();
			for(String categoria:categorie) {
				if(obj.getString(categoria).equals("si")) 
					listCat.add(categoria);
			}
			Iterator<ArticoloBean> itArt=articoli.iterator();
			while(itArt.hasNext())
			{
				ArticoloBean article=itArt.next();
				for(String categoria: listCat)
				{
					Iterator<CategoriaBean> itCat=article.getCategorie().iterator();
					while(itCat.hasNext()) {
						CategoriaBean cat=itCat.next();
						if(cat.getNome().equals(categoria)) {
							newArticoli.add(article);
							break;
						}
					}
				}
			}
			articoli=newArticoli;
		}
		
		Iterator<ArticoloBean> itArt=articoli.iterator();
		JSONObject json_article=new JSONObject();
		while(itArt.hasNext())
		{
			ArticoloBean article=itArt.next();
			if(article.getNome().toLowerCase().contains(obj.getString("field_text").toLowerCase())) {
				JSONObject json_single_article=new JSONObject();
				json_single_article.put("id", article.getId());
				json_single_article.put("description", article.getDescrizione());
				json_single_article.put("price", article.getPrezzo());
				json_single_article.put("category", article.getCategorie());
				json_article.append("json_catalog", json_single_article);
			}
		}
		
		response.addHeader("res", "200");
		response.getWriter().println(json_article.toString());
	}
	
}
