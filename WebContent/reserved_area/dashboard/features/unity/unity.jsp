<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if (op==null)
	{	
    	response.sendRedirect(request.getContextPath()+"/reserved_area/authentication/login-form.jsp");
    	return;
	}
%>
<div class="container-unity"">

    <div class="container-unity__filter">
        <label class="container-unity__filter-label">Filtri</label>
        <input type="search" class="light-table-filter container-unity__filter-input" data-table="order-table" placeholder="Cerca...">
    </div>
    

    <button class="container-unity__add-button" onclick="open_update_modal_unity(-1)">AGGIUNGI</button>

    <table class="container-unity__table order-table table">
        <thead>
            <tr>
                <th class="container-unity__table-header-td container-unity__name-th">Nome</th>
                <th class="container-unity__table-header-td container-unity__quantity-th">Quantita' </th>
                <th></th>
            </tr>
        </thead>
        <tbody class="table_body">
       <%
	        UnitaDAO dao=new UnitaDAO();
	    	ArrayList<UnitaBean> unita=(ArrayList<UnitaBean>)dao.doRetrieveAll();
	    	for(UnitaBean uni: unita){ 
        %>
            <tr>
                <td class="container-unity__table-body-td container-unity__table-name-td"><%=uni.getNome()%></td>
                <td class="container-unity__table-body-td container-unity__table-quantity-td"><%=uni.getQuantita()%></td>
                
                <td class="container-unity__table-body-td container-unity__table-button-td">
                    <a onclick="open_update_modal_unity(<%=uni.getId()%>)"><img class="container-unity__table-button" src="./assets/img/general_icon/edit.png"></a>
                    <a onclick="open_delete_modal_unity(<%=uni.getId()%>)"><img class="container-unity__table-button" src="./assets/img/general_icon/delete.png"></a>
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

<div class="modal-update-unity">
    <div class="modal-update-unity__container">
        <div class="modal-update-unity__header">
            <div class="modal-update-unity__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_update_modal_unity()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-update-unity__content">

            <div class="modal-update-unity__quantity">
                <label class="modal-update-unity__quantity-label">Quantita' </label>
                <input type="number" min="0" name="quantita" class="modal-update-unity__quantity-field">
            </div>

            <div class="modal-update-unity__name">
                <label class="modal-update-unity__name-label">Nome</label>
                <input type="text" name="nome" class="modal-update-unity__name-field">
            </div>

            <button class="modal-update-unity__save-button" >SALVA</button>
        </div>
    </div>
</div>

<div class="modal-delete-unity">
    <div class="modal-delete-unity__container">
        <div class="modal-delete-unity__header modal-delete-unity__header-delete">
            <div class="modal-delete-unity__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_delete_modal_unity()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-delete-unity__content">
            <p class="modal-delete-unity__text">Sei sicuro di voler cancellare l'elemento?</p>
            <div class="modal-delete-unity__button-div">
                <a class="modal-delete-unity__button modal-delete-unity__button-no" onclick="close_delete_modal_unity()">NO</a>
                <a class="modal-delete-unity__button modal-delete-unity__button-yes">SI</a>
            </div>
        </div>
    </div>
</div>