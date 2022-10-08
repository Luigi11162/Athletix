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

import Model.DAO.OrdineRigoDAO;
import Model.bean.OrdineRigoBean;

@WebServlet("/RichiestaOrdineRigo")
public class RichiestaOrdineRigo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaOrdineRigo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineRigoDAO dao=new OrdineRigoDAO();
		List<OrdineRigoBean> list=new ArrayList<>();
		
		
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		try {
			list=(List<OrdineRigoBean>) dao.doRetrieveByTestata(obj.getInt("id"));
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}
		
		JSONObject table=new JSONObject();
		
		for(OrdineRigoBean bean: list) {
			JSONObject jso=new JSONObject();
			jso.put("article",bean.getArticolo().getNome());
			jso.put("iva",bean.getIva());
			jso.put("quantity",bean.getQuantita());
			jso.put("price",bean.getPrezzo());
			table.append("table", jso);
		}	

		response.addHeader("json_order_history_line", table.toString());
	}
	
}