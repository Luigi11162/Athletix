<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if(op!=null){
		response.sendRedirect(request.getContextPath()+"/reserved_area/dashboard/dashboard.jsp");
		return;
	}
	ArticoloBean article=(ArticoloBean) session.getAttribute("article");
	UtenteBean utente=(UtenteBean) session.getAttribute("user");
	UtenteDAO utenteDAO=new UtenteDAO();
	if(article==null)
	{
		response.sendRedirect("/Athletix/Catalogo");
		return;
	}
%>
<div class="container-article">

    <div class="container-article-container">
        <div class="container-article-container__image" onmousemove='zoom(event)' style="background: url(./img/<%=article.getId()%>.jpg);">
            <div class="container-article-container__image-div">
                <img class="container-article-container__image-img"src="./img/<%=article.getId()%>.jpg" >
            </div>
        </div>

        <h1 class="container-article-container__text-container-titol"><%=article.getNome()%></h1>

        <div class="container-article-container__text-container">
            
            <% 	
                    Iterator<CategoriaBean> categorie = article.getCategorie().iterator();
                    int i = 0;
                    String categorie_stringa ="";
                    while(categorie.hasNext())
                    {
                        if(i==0)
                            i++;
                        else
                            categorie_stringa += ", ";
                        
                        CategoriaBean single_category = categorie.next();
                        categorie_stringa += single_category.getNome();
                    }
            %>
            <h3 class="container-article-container__text-container-category">
                <%= categorie_stringa %>
            </h3>

            <p class="container-article-container__text-container-description"><%=article.getDescrizione()%></p>

            <%
            Iterator<TagliaBean> taglie = article.getTaglie().iterator();
            if(taglie.hasNext())
            {
            %>
            <div class="container-article-container__text-container-size">
        
                <span>Taglie</span>
    
                <div >
                <% 
                while(taglie.hasNext())
                {
                    TagliaBean taglia=taglie.next();
                    if(taglia.getQuantita()>0){
                %>
                    <button class="container-article-container__text-container-size-button" onclick="selected_size_button(this,<%=taglia.getId()%>)"><%=taglia.getTaglia()%></button>
                <%
                    }                
                }
                %>
                </div>
    
                <!-- <a class="container__text-container-size-a" href="#">How to configurate your headphones</a>-->
            </div>
            <%} %>
            
            <div class="container-article-container__text-container-quantity">
                <button type="button" class="container-article-container__text-container-quantity-button" onclick="pop_article_quantity()">-</button>
                <input type="number" class="container-article-container__text-container-quantity-input" min="1" max="<%=article.getQuantita()%>" value="1">
                <button type="button" class="container-article-container__text-container-quantity-button" onclick="push_article_quantity(<%=article.getId()%>)">+</button>
            </div> 
            
            <div class="container-article-container__text-container-price">
                <span class="container-article-container__text-container-price-number"> € <%=String.format("%.02f", article.getPrezzo())%></span>
                <button class="container-article-container__text-container-price-button" onclick="add_article_to_cart()">Aggiungi al carrello</button>
            </div>

        </div>
    </div>

    <div class="container-article-comment-container">
    <%
        if(utente!=null)
        {
    %>
        <div class="container-article-comment-container__actually-comment">
            <div class="container-article-comment-container__actually-comment-image"><img class="container-article-comment-container__actually-comment-image-img" src="./img/index_img/user_registered.png"></div>

            <div class="container-article-comment-container__actually-comment-name-time">
                <div class="container-article-comment-container__actually-comment-name"><%=utente.getNome()+" "+utente.getCognome()%></div> <!--devi mettere il nome del tizio in sessione-->
                <div class="container-article-comment-container__actually-comment-time"><%=new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime())%></div> <!--devi mettere la data odierna-->
            </div>
            <textarea class="container-article-comment-container__actually-comment-textarea"></textarea>
            
            <div class="container-article-comment-container__actually-comment-button">
                <button type="button" class="container-article-comment-container__actually-comment-button-cancell" onclick="cancel_textarea_article_content()">Cancella</button>
                <button type="button" class="container-article-comment-container__actually-comment-button-enter" onclick="insert_review_article()">Invia</button>
            </div>
        </div>
    <%
        }
    %>
        <div class="container-article-comment-container__old-comment-container">
        <!--  
            <div class="container-article-comment-container__old-comment">
                <div class="container-article-comment-container__old-comment-image"><img class="container-article-comment-container__old-comment-image-img" src="./img/index_img/user_registered.png"></div>

                <div class="container-article-comment-container__old-comment-name-time">
                    <div class="container-article-comment-container__old-comment-name"></div>
                    <div class="container-article-comment-container__old-comment-time"></div>
                </div>
                <textarea class="container-article-comment-container__old-comment-textarea" disabled></textarea>
                
            </div>
            -->
        </div>
    </div>
</div>