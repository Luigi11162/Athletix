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

import Model.DAO.RecapitoDAO;
import Model.bean.RecapitoBean;
import Model.bean.UtenteBean;

@WebServlet("/UpdateRecapito")
public class UpdateRecapito  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static RecapitoDAO model=new RecapitoDAO();
	
	public UpdateRecapito() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RecapitoBean recapito=new RecapitoBean();
		UtenteBean utente=(UtenteBean) request.getSession().getAttribute("user");
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		//modificare i nomi json
		int id=obj.getInt("id");
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
		String indirizzo=obj.getString("address");
		String citta=obj.getString("city");
		int cap=obj.getInt("cap");
		
		try {
			recapito.setIndirizzo(indirizzo);
			recapito.setCitta(citta);
			recapito.setCap(cap);
			recapito.setIdUtente(utente.getId());
			
			if(id==-1) {			
					model.doSave(recapito);
					
			}else {
				recapito.setId(id);
				model.doUpdate(recapito);
			}
			response.addHeader("res","200");
			
		} catch (SQLException e) {
			System.out.println("Errore nell'update recapito "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
	}
	
}