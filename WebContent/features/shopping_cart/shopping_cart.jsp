<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if(op!=null){
		response.sendRedirect(request.getContextPath()+"/reserved_area/dashboard/dashboard.jsp");
		return;
	}
	UtenteBean utente=(UtenteBean) session.getAttribute("user");
%>

<div class="container-shopping-cart">
    <h1 class="container-shopping-cart__name-section">Carrello</h1>

    <div class="container-shopping-cart__container-section">
        <%
        ArticoloDAO articoloDao=new ArticoloDAO();
        List<CarrelloBean> carrello=null;
        double totale=0;
        double tasse=0;
        if(utente!=null)
            carrello=(List<CarrelloBean>)utente.getCarrello();
        else 
            carrello=(List<CarrelloBean>) session.getAttribute("cart"); 
        if(carrello!=null)
        {
            Iterator<CarrelloBean> itCar=carrello.iterator();
            while(itCar.hasNext())
            {
                CarrelloBean carBean=itCar.next();
                ArticoloBean articolo = articoloDao.doRetrieveByKey(carBean.getIdArticolo());
                Integer quantita = carBean.getQuantita();
                int taglia;
                if(carBean.getTaglia()==null)
                 	taglia =-1;
                else
                	taglia=carBean.getTaglia().getId();
                totale+=articolo.getPrezzo()*carBean.getQuantita();
                tasse+=(articolo.getPrezzo()/100*articolo.getIva())*carBean.getQuantita();
        %>
        <div class="container-shopping-cart__product">
            <div class="container-shopping-cart__product-image">
                <img class="container-shopping-cart__product-image-img" src="./img/<%=articolo.getId()%>.jpg">
            </div>
            <div class="container-shopping-cart__product-details">
                <div class="container-shopping-cart__product-title"><%=articolo.getNome()%></div>
                <!--<p class="container-shopping-cart__product-description"><%=articolo.getDescrizione()%></p>-->
            </div>
            <div class="container-shopping-cart__product-pieces">
                <%=carBean.getQuantita()%> <!--da caricare in modo dinamico-->
            </div>
            <div class="container-shopping-cart__product-price"><%=String.format("%.02f", articolo.getPrezzo())%></div>
            <!--<div class="container-shopping-cart__product-quantity">
                <input type="number" value="2" min="1">
            </div>-->
            <div class="container-shopping-cart__product-removal">
                <button class="container-shopping-cart__product-removal-button" onclick="delete_article_from_cart(<%=articolo.getId()%>,<%=taglia%>)">
                    Remove
                </button>
            </div>
            <!--<div class="product-line-price">25.98</div>-->
        </div>
        <%
                }
            }
        %>
    </div>

    <hr class="container-shopping-cart__hr">

    <div class="container-shopping-cart__total">
        <div class="container-shopping-cart__total-item container-shopping-cart__total-item-subtotal-div">
            <label class="container-shopping-cart__total-item-label">Subtotale</label>
            <div class="container-shopping-cart__total-item-subtotal"><%=String.format("%.02f", totale-tasse)%></div>
        </div>
        <div class="container-shopping-cart__total-item container-shopping-cart__total-item-tax-div">
            <label class="container-shopping-cart__total-item-label">Tasse</label>
            <div class="container-shopping-cart__total-item-tax"><%=String.format("%.02f", tasse)%></div>
        </div>
        <div class="container-shopping-cart__total-item container-shopping-cart__total-item-total-div">
            <label class="container-shopping-cart__total-item-total-label">Totale</label>
            <div class="container-shopping-cart__total-item-total"><%=String.format("%.02f", totale)%></div>
        </div>

        <div class="container-shopping-cart__total-button">
            <button class="container-shopping-cart__total-button-button" onclick="open_modal_buy_shopping_cart()">Acquista</button>
        </div>
        
    </div>
</div>
   