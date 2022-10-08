package Controller.Richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.ArticoloDAO;
import Model.DAO.TagliaDAO;
import Model.bean.ArticoloBean;
import Model.bean.CarrelloBean;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaCarrello")
public class RichiestaCarrello extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaCarrello() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CarrelloBean> list=new ArrayList<>();
		ArticoloDAO articoloDao=new ArticoloDAO();
		TagliaDAO tagliaDao=new TagliaDAO();
		float totale=0;
		float tasse=0;
		JSONObject table=new JSONObject();
			
		if(request.getSession().getAttribute("user")==null)
			list=(ArrayList<CarrelloBean>) request.getSession().getAttribute("cart");
		else {
			UtenteBean utente=(UtenteBean) request.getSession().getAttribute("user");
			list=(ArrayList<CarrelloBean>) utente.getCarrello();
		}
		

		if(list!=null) {
			if(list.size()!=0) {
				Iterator<CarrelloBean> itCar=list.iterator();
				while(itCar.hasNext()) {
					CarrelloBean bean=itCar.next();
					ArticoloBean articoloBean;
					try {
						articoloBean = articoloDao.doRetrieveByKey(bean.getIdArticolo());
						JSONObject jso=new JSONObject();
						jso.put("id", articoloBean.getId());
						jso.put("quantity", bean.getQuantita());
						if(bean.getTaglia()!=null)
						{		
							if(bean.getTaglia().getId()>=0) {
								jso.put("name", articoloBean.getNome()+" Taglia: "+tagliaDao.doRetrieveByKey(bean.getTaglia().getId()).getTaglia());
								jso.put("size", bean.getTaglia().getId());
							}
							else {
								jso.put("size", -1);
								jso.put("name", articoloBean.getNome());
							}
						}else {
							jso.put("size", -1);
							jso.put("name", articoloBean.getNome());
						}
						jso.put("price", String.format("%.02f", articoloBean.getPrezzo()));
						totale+=articoloBean.getPrezzo()*bean.getQuantita();
						tasse+=articoloBean.getPrezzo()/100*articoloBean.getIva()*bean.getQuantita();
						table.append("json", jso);	
					} catch (SQLException e) {
						response.addHeader("res","403");
						e.printStackTrace();
					}
				}
			}
			else
				table.put("json", "");	
		}
		else
			table.put("json", "");
		
		table.put("tax",String.format("%.02f", tasse));
		table.put("subtotal",String.format("%.02f", totale-tasse));
		table.put("total",String.format("%.02f", totale));
		response.addHeader("json_cart", table.toString());
				
		response.addHeader("res","200");
	}		
}