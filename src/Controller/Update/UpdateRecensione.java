package Controller.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.RecensioneDAO;
import Model.bean.ArticoloBean;
import Model.bean.RecensioneBean;
import Model.bean.UtenteBean;

@WebServlet("/UpdateRecensione")
public class UpdateRecensione  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static RecensioneDAO model=new RecensioneDAO();
	
	public UpdateRecensione() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RecensioneBean recensione=new RecensioneBean();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		ArticoloBean article =(ArticoloBean) request.getSession().getAttribute("article");
		UtenteBean	utente=(UtenteBean) request.getSession().getAttribute("user");
		int idUtente=utente.getId();
		int idArticolo=article.getId();
		String descrizione=obj.getString("text");
		LocalDate current_date = LocalDate.now();
		Date data=Date.valueOf(current_date);
		
		recensione.setIdUtente(idUtente);
		recensione.setIdArticolo(idArticolo);
		recensione.setDescrizione(descrizione);
		recensione.setData(data);
		
		try {		
			model.doSave(recensione);
			response.addHeader("res","200");
		} catch (SQLException e) {
			System.out.println("Errore nell'update recensione "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
	}
	
}