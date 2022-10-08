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

import Model.DAO.UnitaDAO;
import Model.bean.UnitaBean;

@WebServlet("/UpdateUnita")
public class UpdateUnita  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static UnitaDAO model=new UnitaDAO();
	
	public UpdateUnita() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		UnitaBean unita=new UnitaBean();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
		String nome=obj.getString("name");
		int quantita=obj.getInt("quantity");
		
		try {
			unita.setNome(nome);
			unita.setQuantita(quantita);
			
			if(id==-1) {		
				System.out.println(model.doRetrieveByNome(nome,quantita));
				if(!model.doRetrieveByNome(nome,quantita).getNome().equals(nome) && model.doRetrieveByNome(nome,quantita).getQuantita()!=quantita)
				{	
					model.doSave(unita);
					response.addHeader("res","200");
				}
				else
					response.addHeader("res","403");
			}else {
				unita.setId(id);
				model.doUpdate(unita);
				response.addHeader("res","200");
			}
		} catch (SQLException e) {
			System.out.println("Errore nell'update unita "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
	}
	
}