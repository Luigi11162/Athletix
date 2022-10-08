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

import Model.DAO.PagamentoDAO;
import Model.bean.PagamentoBean;
import Model.bean.UtenteBean;

@WebServlet("/UpdatePagamento")
public class UpdatePagamento  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static PagamentoDAO model=new PagamentoDAO();
	
	public UpdatePagamento() {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PagamentoBean pagamento=new PagamentoBean();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		//modificare i nomi json
		int id=obj.getInt("id");
		String objString=obj.getString("json");
		obj=new JSONObject(objString);
		String numero=obj.getString("no");
		String nome=obj.getString("name");
		int scadenza_mese=obj.getInt("expdate_month");
		int scadenza_anno=obj.getInt("expdate_year");
		String scadenza;
		if(scadenza_mese<=9)
			 scadenza="0"+scadenza_mese+"/"+scadenza_anno;
		else
			 scadenza=+scadenza_mese+"/"+scadenza_anno;
		int codice=obj.getInt("cvv");
		UtenteBean utenteBean=(UtenteBean) request.getSession().getAttribute("user");
		int utente=utenteBean.getId();
		
		try {
			pagamento.setNome(nome);
			pagamento.setNumero(numero);
			pagamento.setScadenza(scadenza);
			pagamento.setCodice(codice);
			pagamento.setIdUtente(utente);
			
			if(id==-1) {		
				if(!model.doRetrieveByNumero(numero).getNumero().equals(pagamento))
				{	
					model.doSave(pagamento);
					response.addHeader("res","200");
				}
				else
					response.addHeader("res","403");
			}else {
				pagamento.setId(id);
				model.doUpdate(pagamento);
				response.addHeader("res","200");
			}
		} catch (SQLException e) {
			System.out.println("Errore nell'update pagamento "+e.getMessage());
			response.addHeader("res","403");
			e.printStackTrace();
		}
	}
	
}