package Controller.Richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import Model.DAO.PagamentoDAO;
import Model.DAO.RecapitoDAO;
import Model.bean.PagamentoBean;
import Model.bean.RecapitoBean;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaRecapitoPagamento")
public class RichiestaRecapitoPagamento extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaRecapitoPagamento() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecapitoDAO dao=new RecapitoDAO();

		JSONObject table=new JSONObject();
		ArrayList<RecapitoBean> list=new ArrayList<>();
		UtenteBean utenteBean=(UtenteBean) request.getSession().getAttribute("user");
		try {
			list=(ArrayList<RecapitoBean>) dao.doRetrieveByUtente(utenteBean.getId());
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}

		if(list.size()==0)
			table.put("shipping_address", "");
		else
			for(RecapitoBean bean: list) {
				JSONObject jso=new JSONObject();
				jso.put("id", bean.getId());
				jso.put("address", bean.getIndirizzo()+", "+bean.getCitta()+", "+bean.getCap());
				table.append("shipping_address", jso);
			}
		
		PagamentoDAO pagDao=new PagamentoDAO();
		ArrayList<PagamentoBean> listPag=new ArrayList<>();
		try {
			listPag=(ArrayList<PagamentoBean>) pagDao.doRetrieveByUtente(utenteBean.getId());
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}
		
		
		if(listPag.size()==0)
			table.put("payment_methods", "");
		else
			for(PagamentoBean bean: listPag) {
				JSONObject jso=new JSONObject();
				jso.put("id", bean.getId());
				jso.put("payment", bean.getNumero());
				table.append("payment_methods", jso);
			}
		
		response.addHeader("res","200");
		response.addHeader("json_shopping_cart", table.toString());	
	}
}