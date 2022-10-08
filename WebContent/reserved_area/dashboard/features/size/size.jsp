<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if (op==null)
	{	
    	response.sendRedirect(request.getContextPath()+"/reserved_area/authentication/login-form.jsp");
    	return;
	}
%>
<div class="container-size">

    <div class="container-size__filter">
        <label class="container-size__filter-label">Filtri</label>
        <input type="search" class="light-table-filter container-size__filter-input" data-table="order-table" placeholder="Cerca...">
    </div>
    

    <button class="container-size__add-button" onclick="open_update_modal_size(-1)">AGGIUNGI</button>

    <table class="container-size__table order-table table">
        <thead>
            <tr>
                <th class="container-size__table-header-td container-size__name-th">Taglia</th>
                <th></th>
            </tr>
        </thead>
        <tbody class="table_body">
       	<%
	        TagliaDAO dao=new TagliaDAO();
	    	ArrayList<TagliaBean> taglie=(ArrayList<TagliaBean>)dao.doRetrieveAll();
	    	for(TagliaBean taglia: taglie){ 
        %>
            <tr>
                <td class="container-size__table-body-td container-size__table-size-td"><%=taglia.getTaglia()%></td>
                
                <td class="container-size__table-body-td container-size__table-button-td">
                    <a onclick="open_update_modal_size(<%=taglia.getId()%>)"><img class="container-size__table-button" src="./assets/img/general_icon/edit.png"></a>
                    <a onclick="open_delete_modal_size(<%=taglia.getId()%>)"><img class="container-size__table-button" src="./assets/img/general_icon/delete.png"></a>
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

<div class="modal-update-size">
    <div class="modal-update-size__container">
        <div class="modal-update-size__header">
            <div class="modal-update-size__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_update_modal_size()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-update-size__content">

            <div class="modal-update-size__size">
                <label class="modal-update-size__size-label">Taglia</label>
                <input type="text" name="nome" class="modal-update-size__size-field">
            </div>

            <button class="modal-update-size__save-button" >SALVA</button>
        </div>
    </div>
</div>

<div class="modal-delete-size">
    <div class="modal-delete-size__container">
        <div class="modal-delete-size__header modal-delete-size__header-delete">
            <div class="modal-delete-size__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_delete_modal_size()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-delete-size__content">
            <p class="modal-delete-size__text">Sei sicuro di voler cancellare l'elemento?</p>
            <div class="modal-delete-size__button-div">
                <a class="modal-delete-size__button modal-delete-size__button-no" onclick="close_delete_modal_size()">NO</a>
                <a class="modal-delete-size__button modal-delete-size__button-yes">SI</a>
            </div>
        </div>
    </div>
</div>