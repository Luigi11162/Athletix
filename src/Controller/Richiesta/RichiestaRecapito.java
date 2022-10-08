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

import Model.DAO.RecapitoDAO;
import Model.bean.RecapitoBean;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaRecapito")
public class RichiestaRecapito extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaRecapito() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecapitoDAO dao=new RecapitoDAO();
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		int id=obj.getInt("id");
		JSONObject table=new JSONObject();
		if(id==-1) {
			List<RecapitoBean> list=new ArrayList<>();
			UtenteBean utenteBean=(UtenteBean) request.getSession().getAttribute("user");
			try {
				list=(List<RecapitoBean>) dao.doRetrieveByUtente(utenteBean.getId());
			} catch (SQLException e) {
				response.addHeader("res","403");
				e.printStackTrace();
			}
			
			
			if(list.size()==0)
				table.put("json", "");
			else
				for(RecapitoBean bean: list) {
					JSONObject jso=new JSONObject();
					jso.put("id", bean.getId());
					jso.put("address", bean.getIndirizzo());
					jso.put("city", bean.getCitta());
					jso.put("cap", bean.getCap());
					table.append("json", jso);
				}
		}else
		{
			try {
				RecapitoBean bean=dao.doRetrieveByKey(id);
				table.put("address", bean.getIndirizzo());
				table.put("city", bean.getCitta());
				table.put("cap", bean.getCap());
			} catch (SQLException e) {
				response.addHeader("res","403");
				e.printStackTrace();
			}
			
		}
		response.addHeader("res","200");
		response.addHeader("json_shipping_address_user_reserved_area", table.toString());	
	}
}