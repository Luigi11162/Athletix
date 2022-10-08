package Controller.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.DAO.ArticoloDAO;
import Model.DAO.CategoriaDAO;
import Model.DAO.TagliaDAO;
import Model.DAO.UnitaDAO;
import Model.bean.ArticoloBean;
import Model.bean.CategoriaBean;
import Model.bean.TagliaBean;
import Risorse.Risorse;

@WebServlet("/UpdateArticolo")
public class UpdateArticolo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public UpdateArticolo() {
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
		int id=obj.getInt("id");
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
	
		bean.setNome(obj.getString("name"));
		bean.setPrezzo(Float.valueOf(obj.getString("price")));
		bean.setDescrizione(obj.getString("description"));
		bean.setMarca(obj.getString("brand"));
		bean.setMateriale(obj.getString("material"));
		bean.setQuantita(Integer.valueOf(obj.getString("quantity")));
		bean.setIva(Integer.valueOf(obj.getString("iva")));
		bean.setImmagine(obj.getString("img"));		
		
		try {
			UnitaDAO unita=new UnitaDAO();
			bean.setUnita(unita.doRetrieveByKey(Integer.valueOf(obj.getString("unity"))));
			
			CategoriaDAO categoria=new CategoriaDAO();
			List<CategoriaBean> listCat=new ArrayList<>();
			String[] listCatArr=obj.get("category").toString().replaceAll("\\[", "").replaceAll("]", "").split(",");
                    
			for(int i=0;i<listCatArr.length;i++) 
				listCat.add(categoria.doRetrieveByKey(Integer.valueOf(listCatArr[i])));
			
			bean.setCategorie(listCat);
			
			TagliaDAO taglia=new TagliaDAO();
			List<TagliaBean> listTag=new ArrayList<>();
			JSONArray tagArr=obj.getJSONArray("size");
			for(int i=0;i<tagArr.length();i++)
			{
				TagliaBean tag=taglia.doRetrieveByKey(Integer.valueOf(tagArr.getJSONObject(i).getString("id")));
				tag.setQuantita(Integer.valueOf(tagArr.getJSONObject(i).getString("quantity")));
				listTag.add(tag);
			}
			
			bean.setTaglie(listTag);

			if(id == -1)				
				dao.doSave(bean);
			else
				bean.setId(id);
				dao.doUpdate(bean);

			if(!bean.getImmagine().contains(".jpg") && !bean.getImmagine().equals(""))
			{		
				Risorse.saveAndEncrypt(bean.getImmagine(), getServletContext().getRealPath("/")+"img\\"+bean.getId()+".jpg");
			}
		} catch(SQLException e)	
		{
			response.addHeader("res","403");
			e.printStackTrace();
			return;
		}
		
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/Catalogo");
		dispatcher.forward(request, response);
	}

}
