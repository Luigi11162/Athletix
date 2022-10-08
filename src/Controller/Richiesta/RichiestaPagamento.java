package Controller.Richiesta;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import Model.DAO.PagamentoDAO;
import Model.bean.PagamentoBean;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaPagamento")
public class RichiestaPagamento extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaPagamento() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PagamentoDAO dao=new PagamentoDAO();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		JSONObject table=new JSONObject();
		if(id==-1) {
			List<PagamentoBean> list=new ArrayList<>();
			UtenteBean utenteBean=(UtenteBean) request.getSession().getAttribute("user");
			try {
				list=(List<PagamentoBean>) dao.doRetrieveByUtente(utenteBean.getId());
			} catch (SQLException e) {
				response.addHeader("res","403");
				e.printStackTrace();
			}
			
			
			if(list.size()==0)
				table.put("json", "");
			else
				for(PagamentoBean bean: list) {
					JSONObject jso=new JSONObject();
					jso.put("id", bean.getId());
					jso.put("no", bean.getNumero());
					jso.put("name", bean.getNome());
					jso.put("expdate", bean.getScadenza());
					table.append("json", jso);
				}
		}else
		{
			try {
				PagamentoBean bean=dao.doRetrieveByKey(id);
				table.put("no", bean.getNumero());
				table.put("name", bean.getNome());
				table.put("expdate_month", Integer.parseInt(bean.getScadenza().substring(0,2)));
				table.put("expdate_year", Integer.parseInt(bean.getScadenza().substring(3,5)));
				table.put("cvv", bean.getCodice());
			} catch (SQLException e) {
				response.addHeader("res","403");
				e.printStackTrace();
			}
			
		}
		response.addHeader("res","200");
		response.addHeader("json_payment_methods_user_reserved_area", table.toString());	
	}
}