package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import Model.DAO.*;
import Model.bean.ArticoloBean;
import Model.bean.CarrelloBean;
import Model.bean.UtenteBean;

@WebServlet("/Carrello")
public class Carrello extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public Carrello() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader=request.getReader();
		JSONObject obj = new JSONObject(reader.readLine());
		
		ArticoloBean article =(ArticoloBean) request.getSession().getAttribute("article");
		int idArticolo=obj.getInt("id");
		if(idArticolo==-1)
			 idArticolo=article.getId();
		
		int quantita=obj.getInt("quantity");
		int taglia=obj.getInt("size");
		
		CarrelloDAO dao=new CarrelloDAO();
		TagliaDAO tagliaDao=new TagliaDAO();
		UtenteDAO utenteDao=new UtenteDAO();
		CarrelloBean carrello=new CarrelloBean();
		
		carrello.setIdArticolo(idArticolo);
		carrello.setQuantita(quantita);
		try {
				if(taglia==-1)
					carrello.setTaglia(null);
				else
					carrello.setTaglia(tagliaDao.doRetrieveByKey(taglia));
		} catch (SQLException e1) {
			response.addHeader("res", "403");
			e1.printStackTrace();
		}

		UtenteBean	utente=(UtenteBean) request.getSession().getAttribute("user");
		if(utente==null)
		{	
			
			ArrayList<CarrelloBean> list=new ArrayList<>();
			if(request.getSession().getAttribute("cart")!=null) 
				list.addAll((ArrayList<CarrelloBean>) request.getSession().getAttribute("cart"));
			list.remove(carrello);

			if(quantita!=0)
				list.add(carrello);
			request.getSession().setAttribute("cart",list);
		}
		else 
		{
			int idUtente=utente.getId();
			carrello.setIdUtente(idUtente);
			try {
				CarrelloBean car=new CarrelloBean();
				if(taglia!=-1) 
					car=dao.doRetrieveByKeyAndSize(idUtente, idArticolo, taglia);
				else
					car=dao.doRetrieveByKey(idUtente, idArticolo);
				if(car!=null)	
					dao.doDelete(car.getId(),idUtente,idArticolo);
				if(quantita!=0)
				{
					dao.doSave(carrello);
				}
			
				utente=utenteDao.doRetrieveByKey(utente.getId());
				request.getSession().setAttribute("user",utente);
				
			} catch (SQLException e) { 
				response.addHeader("res", "403");
				e.printStackTrace();
			}			
		}
		
		response.addHeader("res", "200");
	}
}
