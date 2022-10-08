<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if(op!=null){
		response.sendRedirect(request.getContextPath()+"/reserved_area/dashboard/dashboard.jsp");
		return;
	}
	UtenteBean utente=(UtenteBean) session.getAttribute("user");
	if(utente==null)
	{
		response.sendRedirect(request.getContextPath());
		return;
	}
%>
<div class="container-orders">

    <!--<div class="container-orders__filter">

    </div>-->
	<%
		ArrayList<OrdineTestataBean> listOrders=(ArrayList<OrdineTestataBean>) utente.getOrdini();
		Iterator<OrdineTestataBean> itOrder=listOrders.iterator();
		while(itOrder.hasNext())
		{
			OrdineTestataBean ordineBean=itOrder.next();
	%>
    <div class="container-orders__content">
        <div class="container-orders__content-element">
            <div class="container-orders__content-element-header" onclick="onclick_orders_header(this)">
                <div class="container-orders__content-element-header-order">
                    <label class="container-orders__content-element-header-order-label">Numero Ordine</label>
                    <label class="container-orders__content-element-header-order-label-write"><%=ordineBean.getId()%></label>
                </div>
                <div class="container-orders__content-element-header-date">
                    <label class="container-orders__content-element-header-date-label">Data di emissione</label>
                    <label class="container-orders__content-element-header-date-label-write"><%=ordineBean.getData().getDay()+"/"+ordineBean.getData().getMonth()+"/"+ordineBean.getData().getYear()%></label>
                </div>
                <div class="container-orders__content-element-header-address">
                    <label class="container-orders__content-element-header-address-label">Indirizzo di Spedizione</label>
                    <label class="container-orders__content-element-header-address-label-write"><%=ordineBean.getIndirizzo_Spedizione()%></label>
                </div>
                <div class="container-orders__content-element-header-payment">
                    <label class="container-orders__content-element-header-payment-label">Metodo di Pagamento</label>
                    <label class="container-orders__content-element-header-payment-label-write"><%=ordineBean.getPagamento().substring(0,4)+" **** **** "+ordineBean.getPagamento().substring(12,16)%></label>
                </div>
                <div class="container-orders__content-element-header-bill">
                    <!--<label class="container-orders__content-element-header-bill-label">Fattura</label>-->
                    <button type="button" class="container-orders__content-element-header-bill-button" onclick="print_order(this)">
                        <img src="./img/index_img/printer.png" class="container-orders__content-element-header-bill-button-image">
                    </button>
                </div>
            </div>
        <%
		ArrayList<OrdineRigoBean> listOrdersRow=(ArrayList<OrdineRigoBean>) ordineBean.getOrdineRigo();
		Iterator<OrdineRigoBean> itOrderRow=listOrdersRow.iterator();
		while(itOrderRow.hasNext())
		{
			OrdineRigoBean ordineRowBean=itOrderRow.next();
	%>
            <div class="container-orders__content-element-line">
                <div class="container-orders__content-element-line-element">
                    <div class="container-orders__content-element-line-element-image">
                        <img src="./img/<%=ordineRowBean.getArticolo().getId()%>.jpg" class="container-orders__content-element-line-element-image-img">
                    </div>
                    <div class="container-orders__content-element-line-element-title">     
                        <%=ordineRowBean.getArticolo().getNome()%>
                    </div>
                    <div class="container-orders__content-element-line-element-price">
                        <%=ordineRowBean.getPrezzo()%>
                    </div>
                    <div class="container-orders__content-element-line-element-quantity">
                         <%=ordineRowBean.getQuantita()%>
                    </div>
                    <div class="container-orders__content-element-line-element-iva">
                        <%=ordineRowBean.getIva()%>
                    </div>
                </div>
              
            </div>
            <%
           	 } 
            %>
        </div>
    </div>
    <%
		}
    %>

</div>