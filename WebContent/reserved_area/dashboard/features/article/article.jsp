<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if (op==null)
	{	
    	response.sendRedirect(request.getContextPath()+"/reserved_area/authentication/login-form.jsp");
    	return;
	}
%>
<div class="container-article">

    <div class="container-article__filter">
        <label class="container-article__filter-label">Filtri</label>
        <input type="search" class="light-table-filter container-article__filter-input" data-table="order-table" placeholder="Cerca...">
    </div>
    

    <button class="container-article__add-button" onclick="open_update_modal_article(-1)">AGGIUNGI</button>

    <table class="container-article__table order-table table">
        <thead>
            <tr>
                <th class="container-article__table-header-td container-article__image-th">Immagine</th>
                <th class="container-article__table-header-td container-article__name-th">Nome</th>
                <th class="container-article__table-header-td container-article__price-th">Prezzo</th>
                <th class="container-article__table-header-td container-article__description-th">Descrizione</th>
                <th class="container-article__table-header-td container-article__brand-th">Marca</th>
                <th class="container-article__table-header-td container-article__quantity-th">Quantità</th>
                <th class="container-article__table-header-td container-article__material-th">Materiale</th>
                <th></th>
            </tr>
        </thead>
        <tbody class="table_body">
        <%
        	ArticoloBean articolo;
       	 	ServletContext cxt= getServletContext();
        	ArrayList<ArticoloBean> articoli=(ArrayList<ArticoloBean>)cxt.getAttribute("articles");
        	if(articoli==null){
        		response.sendRedirect("/Athletix/Catalogo");
        		return;
        	}
			Iterator<ArticoloBean> it=articoli.iterator();
			String descrizione;
        	while(it.hasNext()){
        		articolo=it.next();
        		if(articolo.getDescrizione().length()>=170)
        			 descrizione=articolo.getDescrizione().substring(0,170)+"...";
        		else
					descrizione=articolo.getDescrizione();
        %>
            <tr>
                <td class="container-article__table-body-td"><img class="container-article__table-img-img" src="../../img/<%=articolo.getId()%>.jpg" ></td>
                <td class="container-article__table-body-td container-article__table-name-td"><%=articolo.getNome()%></td>
                <td class="container-article__table-body-td container-article__table-price-td">€<%=String.format("%.02f", articolo.getPrezzo())%></td>
                <td class="container-article__table-body-td container-article__table-description-td"><%=descrizione%></td>
                <td class="container-article__table-body-td container-article__table-brand-td"><%=articolo.getMarca()%></td>
                <td class="container-article__table-body-td container-article__table-quantity-td"><%=articolo.getQuantita()%></td>
                <td class="container-article__table-body-td container-article__table-material-td"><%=articolo.getMateriale()%></td>
                <td class="container-article__table-body-td container-article__table-button-td">
                    <a onclick="open_update_modal_article(<%=articolo.getId()%>)"><img class="container-article__table-button" src="./assets/img/general_icon/edit.png"></a>
                    <a onclick="open_delete_modal_article(<%=articolo.getId()%>)"><img class="container-article__table-button" src="./assets/img/general_icon/delete.png"></a>
                </td>
            </tr>
        <%
   			}
        %>
            <tr class="container__table-body-tr-no-element">
                <td class="container__table-body-td " colspan=8>
                    <div class="container__table-body-td-no-element">
                        <p>NON CI SONO ELEMENTI DA MOSTRARE NELLA TABELLA</p> 
                        <img class="container__table-body-td-icon-img" src="./assets/img/general_icon/sad_emoji.png" >
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
</div>

<div class="modal-update-article">
    <div class="modal-update-article__container">
        <div class="modal-update-article__header">
            <div class="modal-update-article__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_update_modal_article()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-update-article__content">

            <div class="modal-update-article__image">
                <label class="modal-update-article__image-label">Immagine</label>
                <div class="box">
                    <div class="js--image-preview"></div>
                    <div class="upload-options">
                        <label >
                        <a class="box__update"><img class="container-article__table-button" src="./assets/img/general_icon/edit.png"></a>
                        <input type="file" class="image-upload" accept="image/*" />
                        </label>
                    </div>
                    </div>
            </div>
            
            <div class="modal-update-article__name">
                <label class="modal-update-article__name-label">Nome</label>
                <input type="text" name="nome" class="modal-update-article__name-field">
            </div>

            <div class="modal-update-article__description">
                <label class="modal-update-article__description-label">Descrizione</label>
                <textarea type="text" name="descrizione" class="modal-update-article__description-textarea"></textarea>
            </div>

            <div class="modal-update-article__price">
                <label class="modal-update-article__price-label">Prezzo</label>
                <input type="number" min="0" name="prezzo" class="modal-update-article__price-field">
            </div>

            <div class="modal-update-article__brand">
                <label class="modal-update-article__brand-label">Marca</label>
                <input type="text" name="marca" class="modal-update-article__brand-field">
            </div>

            <div class="modal-update-article__quantity">
                <label class="modal-update-article__quantity-label">Quantità</label>
                <input type="number" min="0" name="quantita" class="modal-update-article__quantity-field">
            </div>

            <div class="modal-update-article__material">
                <label class="modal-update-article__material-label">Materiale</label>
                <input type="text" name="materiale" class="modal-update-article__material-field">
            </div>

            <div class="modal-update-article__iva">
                <label class="modal-update-article__iva-label">Iva</label>
                <input type="number" name="iva" class="modal-update-article__iva-field">
            </div>

            <div class="modal-update-article__category">
                <label class="modal-update-article__category-label">Categorie</label>
                <div class="modal-update-article__category-checkbox-div">
                   	<%
	                	CategoriaDAO category_dao=new CategoriaDAO();
                   		CategoriaBean category_bean;
	                	ArrayList<CategoriaBean> category =(ArrayList<CategoriaBean>)category_dao.doRetrieveAll();
	                	Iterator<CategoriaBean> itCategory= category.iterator();
	                	while(itCategory.hasNext()){
	               			category_bean = itCategory.next();
	                %>
                    	<div class="modal-update-article__category-checkbox-div-element">
                    		<input type="checkbox" id="checkbox_category_<%=category_bean.getId()%>"  value="<%=category_bean.getId()%>" class="modal-update-article__category-checkbox-div-element-checkbox">
                    		<p><%=category_bean.getNome()%></p>
                    	</div>
                    <%
	                	}	
	                %>
                </div>
            </div>
            
            <div class="modal-update-article__size">
                <label class="modal-update-article__size-label">Taglie</label>
                <div class="modal-update-article__size-checkbox-div">
                
                	<%
	                	TagliaDAO size_dao=new TagliaDAO();
	                	TagliaBean size_bean;
	                	ArrayList<TagliaBean> size =(ArrayList<TagliaBean>)size_dao.doRetrieveAll();
	                	Iterator<TagliaBean> itSize = size.iterator();
	                	while(itSize.hasNext()){
	               			size_bean = itSize.next();
	                %>
                    	<div class="modal-update-article__size-checkbox-div-element">
                    		<input type="checkbox" id="checkbox_size_<%=size_bean.getId()%>" value="<%=size_bean.getId()%>" class="modal-update-article__size-checkbox-div-element-checkbox" onclick="change_status(this)">
                    		<p><%=size_bean.getTaglia() %></p>
                    		<input type="number" id="input_size_<%=size_bean.getId()%>" min="1" class="modal-update-article__size-checkbox-div-element-field">
                    	</div>
                    <%
	                	}	
	                %>
                </div>
            </div>

            <div class="modal-update-article__unity">
                <label class="modal-update-article__unity-label">Unità</label>
                <select name="unita" class="modal-update-article__unity-select">
                <%
                	UnitaDAO unity_dao=new UnitaDAO();
                	UnitaBean unity_bean;
                	ArrayList<UnitaBean> unity=(ArrayList<UnitaBean>)unity_dao.doRetrieveAll();
                	Iterator<UnitaBean> itUnity = unity.iterator();
                	while(itUnity.hasNext()){
                		unity_bean = itUnity.next();
                %>
                    <option id="unity_option_<%=unity_bean.getId()%>" class="modal-update-article__unity-option" value="<%=unity_bean.getId()%>"><%=unity_bean.getQuantita()+" "+unity_bean.getNome()%></option>
                <%
                	}	
                %>
                </select>
            </div>

            <button class="modal-update-article__save-button" >SALVA</button>
        </div>
    </div>
</div>

<div class="modal-delete-article">
    <div class="modal-delete-article__container">
        <div class="modal-delete-article__header modal-delete-article__header-delete">
            <div class="modal-delete-article__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_delete_modal_article()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-delete-article__content">
            <p class="modal-delete-article__text">Sei sicuro di voler cancellare l'elemento?</p>
            <div class="modal-delete-article__button-div">
                <a class="modal-delete-article__button modal-delete-article__button-no" onclick="close_delete_modal_article()">NO</a>
                <a class="modal-delete-article__button modal-delete-article__button-yes">SI</a>
            </div>
        </div>
    </div>
</div>