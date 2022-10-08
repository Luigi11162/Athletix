<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if (op==null)
	{	
    	response.sendRedirect(request.getContextPath()+"/reserved_area/authentication/login-form.jsp");
    	return;
	}
%>
<div class="container-customers">

    <div class="container-customers__filter">
        <label class="container-customers__filter-label">Filtri</label>
        <input type="search" class="light-table-filter container-customers__filter-input" data-table="order-table" placeholder="Cerca...">
    </div>

    <table class="container-customers__table order-table table">
        <thead>
            <tr>
                <th class="container-customers__table-header-td container-customers__surname-th">Cognome</th>
                <th class="container-customers__table-header-td container-customers__name-th">Nome</th>
                <th class="container-customers__table-header-td container-customers__gender-th">Genere</th>
                <th class="container-customers__table-header-td container-customers__status-th">Stato Account</th>
                <th></th>
            </tr>
        </thead>
        <tbody class="table_body">
        <%
	        UtenteDAO dao=new UtenteDAO();
	    	ArrayList<UtenteBean> utenti=(ArrayList<UtenteBean>)dao.doRetrieveAll();
	    	for(UtenteBean user: utenti){ 
        %>
            <tr>
                <td class="container-customers__table-body-td container-customers__table-surname-td"><%=user.getCognome()%></td>
                <td class="container-customers__table-body-td container-customers__table-name-td"><%=user.getNome()%></td>
                <td class="container-customers__table-body-td container-customers__table-gender-td"><%=user.getGenere()%></td>
                <td class="container-customers__table-body-td container-customers__table-status-td"><%=user.getStato_account()%></td>
                <td class="container-customers__table-body-td container-customers__table-button-td">
                    <a onclick="open_update_modal_customers(<%=user.getId()%>)"><img class="container-customers__table-button" src="./assets/img/general_icon/edit.png"></a>
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

<div class="modal-update-customers">
    <div class="modal-update-customers__container">
        <div class="modal-update-customers__header">
            <div class="modal-update-customers__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_update_modal_customers()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-update-customers__content">

            <div class="modal-update-customers__surname">
                <label class="modal-update-customers__surname-label">Cognome</label>
                <input type="text" name="surname" class="modal-update-customers__surname-field" disabled>
            </div>
            
            <div class="modal-update-customers__name">
                <label class="modal-update-customers__name-label">Nome</label>
                <input type="text" name="name" class="modal-update-customers__name-field" disabled>
            </div>

            <div class="modal-update-customers__status">
                <label class="modal-update-customers__status-label">Stato Account</label>
                <input type="text" name="status" class="modal-update-customers__status-field">
            </div>

			 <div class="modal-update-customers__gender">
                <label class="modal-update-customers__gender-label">Genere</label>
                <input type="text" name="gender" class="modal-update-customers__gender-field" disabled>
            </div>

            <button class="modal-update-customers__save-button" >SALVA</button>
        </div>
    </div>
</div>