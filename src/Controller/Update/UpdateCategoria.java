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

import Model.DAO.CategoriaDAO;
import Model.bean.CategoriaBean;

@WebServlet("/UpdateCategoria")
public class UpdateCategoria  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static CategoriaDAO model=new CategoriaDAO();
	
	public UpdateCategoria() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		CategoriaBean categoria=new CategoriaBean();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
		String nome=obj.getString("category");
		try {
			categoria.setNome(nome);
			
			if(id==-1) {		
				if(model.doRetrieveByNome(nome).getNome()==null)
				{	
					model.doSave(categoria);
				}
				else	
				{
					response.addHeader("res","403");
					return;
				}
			}else {
				categoria.setId(id);
				model.doUpdate(categoria);
			}
		} catch (SQLException e) {
			System.out.println("Errore nell'update categoria "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
		response.addHeader("res","200");
	}
	
}