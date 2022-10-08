package Controller.Richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.JSONObject;

import Model.DAO.RecensioneDAO;
import Model.DAO.UtenteDAO;
import Model.bean.ArticoloBean;
import Model.bean.RecensioneBean;
import Model.bean.UtenteBean;

@WebServlet("/RichiestaRecensione")
public class RichiestaRecensione extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public RichiestaRecensione() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RecensioneBean> list=new ArrayList<>();
		RecensioneDAO dao=new RecensioneDAO();
		UtenteDAO userDAO=new UtenteDAO();
		
		ServletContext cxt=getServletContext();
		ArticoloBean article =(ArticoloBean) request.getSession().getAttribute("article");
		try {
			list=(List<RecensioneBean>) dao.doRetrieveByArticolo(article.getId());
		} catch (SQLException e) {
			response.addHeader("res","403");
			e.printStackTrace();
		}
		JSONObject table=new JSONObject();
		if(list.size()==0)
			table.put("old_comment", "");
		else
			for(RecensioneBean bean: list) {
				JSONObject jso=new JSONObject();
				UtenteBean user;
				try {
					user = userDAO.doRetrieveByKey(bean.getIdUtente());
					jso.put("name", user.getNome()+ " "+ user.getCognome());
					jso.put("date", bean.getData());
					jso.put("review", bean.getDescrizione());
					table.append("old_comment", jso);
				} catch (SQLException e) {
					response.addHeader("res","403");
					e.printStackTrace();
				}
			}
		
		response.addHeader("json_review_article", table.toString());	
	}
}