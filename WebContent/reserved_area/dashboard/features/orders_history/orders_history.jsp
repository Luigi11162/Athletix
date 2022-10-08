<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if (op==null)
	{	
    	response.sendRedirect(request.getContextPath()+"/reserved_area/authentication/login-form.jsp");
    	return;
	}
%>
<div class="container-order-history">

    <div class="container-order-history__filter">
        <label class="container-order-history__filter-label">Filtri</label>
        <input type="search" class="light-table-filter container-order-history__filter-input" data-table="order-table" placeholder="Cerca...">
    </div>

    <div class="container-order-history__filter-date-start">
        <label class="container-order-history__filter-date-start-label">Data inizio</label>
        <input type="date" class="container-order-history__filter-date-start-input">
    </div>

    <div class="container-order-history__filter-date-finish">
        <label class="container-order-history__filter-date-finish-label">Data fine</label>
        <input type="date" class=" container-order-history__filter-date-finish-input">
    </div>
    

    <!--<button class="container-order-history__add-button" onclick="open_view_modal_order_history(-1)">AGGIUNGI</button>-->

    <table class="container-order-history__table order-table table">
        <thead>
            <tr>
                <th class="container-order-history__table-header-td container-order-history__date-th">Data</th>
                <th class="container-order-history__table-header-td container-order-history__hour-th">Ora</th>
                <th class="container-order-history__table-header-td container-order-history__customer-th">Cliente</th>
                <th class="container-order-history__table-header-td container-order-history__address-th">Indirizzo Spedizione</th>
                <th></th>
            </tr>
        </thead>
        <tbody class="table_body">
        <%
	        OrdineTestataDAO dao=new OrdineTestataDAO();
        	UtenteDAO utenteDAO=new UtenteDAO();
	    	ArrayList<OrdineTestataBean> ordini=(ArrayList<OrdineTestataBean>)dao.doRetrieveAll();
            String day;
            String month;
            String year;
	    	for(OrdineTestataBean ordine: ordini){ 
                UtenteBean user=utenteDAO.doRetrieveByKey(ordine.getIdUtente());
                year = ordine.getData().toString().substring(0, 4);
                month = ordine.getData().toString().substring(5, 7);
                day = ordine.getData().toString().substring(8, 10);
        %>
            <tr>
                <td class="container-order-history__table-body-td container-order-history__table-date-td"><%=day + "-" + month + "-" + year%></td>
                <td class="container-order-history__table-body-td container-order-history__table-hour-td"><%=ordine.getOra().toString().substring(0, 5)%></td>
                <td class="container-order-history__table-body-td container-order-history__table-customer-td"><%=user.getNome()+" "+user.getCognome()%></td>
                <td class="container-order-history__table-body-td container-order-history__table-address-td"><%=ordine.getIndirizzo_Spedizione()%></td>
                <td class="container-order-history__table-body-td container-order-history__table-button-td">
                    <a onclick="open_view_modal_order_history(<%=ordine.getId()%>)"><img class="container-order-history__table-button" src="./assets/img/general_icon/view.png"></a>
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

<div class="modal-view-order-history">
    <div class="modal-view-order-history__container">
        <div class="modal-view-order-history__header">
            <div class="modal-view-order-history__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_view_modal_order_history()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-view-order-history__content">

            <div class="modal-view-order-history__filter">
                <label class="modal-view-order-history__filter-label">Filtri</label>
                <input type="search" class="light-table-filter-line modal-view-order-history__filter-input" data-table="order-table-line" placeholder="Cerca...">
            </div>

            <div class="modal-view-order-history__table-order-line">
                <table class="modal-view-order-history__table order-table-line table">
                    <thead>
                        <tr>
                            <th class="modal-view-order-history__table-header-td modal-view-order-history__article-th">Articolo</th>
                            <th class="modal-view-order-history__table-header-td modal-view-order-history__price-th">Prezzo</th>
                            <th class="modal-view-order-history__table-header-td modal-view-order-history__quantity-th">Quantita'</th>
                            <th class="modal-view-order-history__table-header-td modal-view-order-history__iva-th">Iva</th>
                        </tr>
                    </thead>
                    <tbody class="table_body_line">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>