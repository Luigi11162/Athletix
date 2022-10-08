package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.*;
import Model.bean.*;

@WebServlet("/EffettuaOrdine")
public class EffettuaOrdine extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public EffettuaOrdine() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrdineTestataDAO testataDao=new OrdineTestataDAO();
		OrdineRigoDAO rigoDao=new OrdineRigoDAO();
		ArticoloDAO articoloDao=new ArticoloDAO();
		TagliaDAO tagliaDao=new TagliaDAO();
		RecapitoDAO recapitoDao=new RecapitoDAO();
		CarrelloDAO carrelloDao=new CarrelloDAO();
		PagamentoDAO pagamentoDao=new PagamentoDAO();
		UtenteDAO utenteDao=new UtenteDAO();
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		int idSpedizione=obj.getInt("shipping_address");
		int idPagamento=obj.getInt("payment_methods");
		
		UtenteBean user=(UtenteBean) request.getSession().getAttribute("user");
		if(user!=null)
		{
			if(user.getCarrello().size()!=0) {
				ArrayList<CarrelloBean> ordine=(ArrayList<CarrelloBean>) user.getCarrello();
				OrdineTestataBean ordineTest=new OrdineTestataBean();
				LocalDate current_date = LocalDate.now();
				ordineTest.setData(Date.valueOf(current_date));
				LocalTime current_time= LocalTime.now();
				ordineTest.setOra(Time.valueOf(current_time));
				ordineTest.setIdUtente(user.getId());
				RecapitoBean recapitoBean;
				try {
					recapitoBean=recapitoDao.doRetrieveByKey(idSpedizione);
					ordineTest.setIndirizzo_Fatturazione(recapitoBean.getCitta()+" "+recapitoBean.getIndirizzo()+ " "+recapitoBean.getCap());
					ordineTest.setIndirizzo_Spedizione(recapitoBean.getCitta()+" "+recapitoBean.getIndirizzo()+ " "+recapitoBean.getCap());
					ordineTest.setPagamento(pagamentoDao.doRetrieveByKey(idPagamento).getNumero());
					testataDao.doSave(ordineTest);
					
					for(CarrelloBean prodotto: ordine)
					{
						OrdineRigoBean rigoBean=new OrdineRigoBean();
						ArticoloBean art=articoloDao.doRetrieveByKey(prodotto.getIdArticolo());
						TagliaBean tag=new TagliaBean();
						if(art.getTaglie().size()!=0)
							tag=art.getTaglie().get(art.getTaglie().indexOf(prodotto.getTaglia()));
						else
							tag.setId(-1);
						rigoBean.setArticolo(art);
						rigoBean.setIva(art.getIva());
						rigoBean.setPrezzo(art.getPrezzo());
						rigoBean.setQuantita(prodotto.getQuantita());
						rigoBean.setIdTestata(ordineTest.getId());
						
						if(art.getQuantita()-prodotto.getQuantita()<0)
						{
							testataDao.doDelete(ordineTest.getId());
							response.addHeader("res", "400");
							return;
						}
						if(tag!=null) {
							if(tag.getId()>0)
							{
								if(tag.getQuantita()-prodotto.getQuantita()<0)
								{
									testataDao.doDelete(ordineTest.getId());
									response.addHeader("res", "400");
									return;
								}
								tag.setQuantita(tag.getQuantita()-prodotto.getQuantita());
								tagliaDao.doUpdate(tag);
							}
						}
						art.setQuantita(art.getQuantita()-prodotto.getQuantita());	
						articoloDao.doUpdate(art);
						rigoDao.doSave(rigoBean);
					}
				} catch (SQLException e) {
					response.addHeader("res", "403");	
					e.printStackTrace();
				}
				Iterator<CarrelloBean> itCar=user.getCarrello().iterator();
				while(itCar.hasNext()) {
					CarrelloBean carrelloBean=itCar.next();
					try {
						carrelloDao.doDelete(carrelloBean.getId(), user.getId(), carrelloBean.getIdArticolo());
					} catch (SQLException e) {
						response.addHeader("res", "403");	
						e.printStackTrace();
					}
				}
				user.getCarrello().clear();	
			}else
				response.addHeader("res", "403");	
		}
		else
		{
			response.addHeader("res","403");
		}
		
		try {
			request.getSession().setAttribute("user", utenteDao.doRetrieveByKey(user.getId()));
		} catch (SQLException e) {
			response.addHeader("res", "403");
			e.printStackTrace();
		}
		response.addHeader("res", "200");	
	}
}
