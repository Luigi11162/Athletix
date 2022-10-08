<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if(op!=null){
		response.sendRedirect(request.getContextPath()+"/reserved_area/dashboard/dashboard.jsp");
		return;
	}
%>
<div class="container-catalog">
    <div class="container-catalog__filter">
        <div class="container-catalog__filter-text">
            <!--<label class="container-catalog__filter-text-label">Filtri</label>-->
            <div class="container-catalog__filter-text-input">
                <input  class="container-catalog__filter-text-input-input" type="text" placeholder="">
                <button class="container-catalog__filter-text-input-button" type="button" onclick="onload_card_on_page(-1)"><img class="container-catalog__filter-text-input-button-img" src="./img/index_img/search.png"></button> <!--../../img/index_img/search.png-->
            </div>
        </div>
        <div class="container-catalog__filter-category">
            <label class="container-catalog__filter-category-label">Filtri</label>
            <button class="container-catalog__filter-category-button" onclick="open_modal_category_catalog()">Seleziona categorie</button>
        </div>
    </div>
    <div class="container-catalog__catalog">
            
    </div>
</div>

<div class="modal-view-category-catalog">
    <div class="modal-view-category-catalog__container">
        <div class="modal-view-category-catalog__header">
            <div class="modal-view-category-catalog__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_modal_category_catalog()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-view-category-catalog__content">

            <!--<div class="modal-view-category-catalog__filter">
                <label class="modal-view-category-catalog__filter-label">Filtri</label>
                <input type="search" class="light-table-filter-line modal-view-category-catalog__filter-input" data-table="order-table-line" placeholder="Cerca...">
            </div>-->
            <div class="modal-view-category-catalog__checkbox-div">
                
            </div>
            

            <div class="modal-view-category-catalog__save-button">
                <button class="modal-view-category-catalog__save-button-button" onclick="onload_card_on_page(-1)"> Salva</button>
            </div>
        </div>
    </div>
</div>