package Controller.Richiesta;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.bean.CarrelloBean;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaQuantitaCarrello")
public class RichiestaQuantitaCarrello extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaQuantitaCarrello() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CarrelloBean> list=new ArrayList<>();
			
		if(request.getSession().getAttribute("user")==null)
			list=(ArrayList<CarrelloBean>) request.getSession().getAttribute("cart");
		else {
			UtenteBean utente=(UtenteBean) request.getSession().getAttribute("user");
			list=(ArrayList<CarrelloBean>) utente.getCarrello();
		}
		
			response.addHeader("value_popup", String.valueOf(list.size()));			
			response.addHeader("res","200");
		}
		
	
}