package Controller.Update;

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

@WebServlet("/UpdateTaglia")
public class UpdateTaglia  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static TagliaDAO model=new TagliaDAO();
	
	public UpdateTaglia() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		TagliaBean taglia=new TagliaBean();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
		String nome=obj.getString("size");
		
		try {
			taglia.setTaglia(nome);
			
			if(id==-1) {		
				if(!model.doRetrieveByTaglia(nome).getTaglia().equals(nome))
				{	
					model.doSave(taglia);
					response.addHeader("res","200");
				}
				else
					response.addHeader("res","403");
			}else {
				taglia.setId(id);
				model.doUpdate(taglia);
				response.addHeader("res","200");
			}
		} catch (SQLException e) {
			System.out.println("Errore nell'update taglia "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
	}
	
}