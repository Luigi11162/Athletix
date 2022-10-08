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
<div class="container-reserved-area">
    <div class="container-reserved-area__profile">
        <div class="container-reserved-area__profile-titol">
            Profilo
        </div>
        <div class="container-reserved-area__profile-image">
            <img src="./img/index_img/user_.png" class="container-reserved-area__profile-image-img">
        </div>
        <div class="container-reserved-area__profile-text">
            <div class="container-reserved-area__profile-text-surname">
                <label class="container-reserved-area__profile-text-surname-label">Cognome</label>
                <input type="text" class="container-reserved-area__profile-text-surname-input" disabled>
            </div>

            <div class="container-reserved-area__profile-text-name">
                <label class="container-reserved-area__profile-text-name-label">Nome</label>
                <input type="text" class="container-reserved-area__profile-text-name-input" disabled>
            </div>

            <div class="container-reserved-area__profile-text-email">
                <label class="container-reserved-area__profile-text-email-label">Email</label>
                <input type="text" class="container-reserved-area__profile-text-email-input" disabled>
            </div>

            <div class="container-reserved-area__profile-text-password">
                <label class="container-reserved-area__profile-text-password-label">Password</label>
                <input type="password" class="container-reserved-area__profile-text-password-input" disabled>
            </div>

            <div class="container-reserved-area__profile-text-button">
                <div class="container-reserved-area__profile-text-button-change" onclick="change_data_user_reserved_area()">
                    <img src="./reserved_area/dashboard/assets/img/general_icon/edit.png" class="container-reserved-area__profile-text-button-change-img">
                </div>

                <div class="container-reserved-area__profile-text-button-delete" onclick="delete_change_data_user_reserved_area()">
                    <img src="./img/index_img/delete_change.png" class="container-reserved-area__profile-text-button-delete-img">
                </div>

                <div class="container-reserved-area__profile-text-button-save" onclick="update_change_data_user_reserved_area()">
                    <img src="./img/index_img/save.png" class="container-reserved-area__profile-text-button-save-img">
                </div>
            </div>
        </div>
    </div>

    <div class="container-reserved-area__shipping-address">

        <div class="container-reserved-area__shipping-address-titol">
            Indirizzi di Spedizione
        </div>
        
        <div class="container-reserved-area__shipping-address-element">
			
        </div>
    </div>

    <div class="container-reserved-area__payment-methods">

        <div class="container-reserved-area__payment-methods-titol">
            Metodi di Pagamento
        </div>

        <div class="container-reserved-area__payment-methods-element">
			<!--  <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal__payment_method_user_reserved_area(1)">
                <h3 class="container-reserved-area__payment-methods-element-main-title">
                    <!--Financial | <span>Elite</span>
                    <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                </h3>
                <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                <div class="container-reserved-area__payment-methods-element-chip"></div>
                <!--<div class="container-reserved-area__payment-methods-element-card-info">
                    <p class="container-reserved-area__payment-methods-element-no">5423  4426  6230  0041</p>
                    <p class="container-reserved-area__payment-methods-element-name">Luigi Bacco</p>
                    <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: 07/22</p>
                <!--</div>
                <div class="container-reserved-area__payment-methods-element-mastercard"></div>
            </div>
            <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal__payment_method_user_reserved_area(1)">
                <h3 class="container-reserved-area__payment-methods-element-main-title">
                    <!--Financial | <span>Elite</span>
                    <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                </h3>
                <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                <div class="container-reserved-area__payment-methods-element-chip"></div>
                <!--<div class="container-reserved-area__payment-methods-element-card-info">
                    <p class="container-reserved-area__payment-methods-element-no">5423  4426  6230  0041</p>
                    <p class="container-reserved-area__payment-methods-element-name">Luigi Bacco</p>
                    <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: 07/22</p>
                <!--</div>
                <div class="container-reserved-area__payment-methods-element-mastercard"></div>
            </div>
            <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal__payment_method_user_reserved_area(1)">
                <h3 class="container-reserved-area__payment-methods-element-main-title">
                    <!--Financial | <span>Elite</span>
                    <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                </h3>
                <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                <div class="container-reserved-area__payment-methods-element-chip"></div>
                <!--<div class="container-reserved-area__payment-methods-element-card-info">
                    <p class="container-reserved-area__payment-methods-element-no">5423  4426  6230  0041</p>
                    <p class="container-reserved-area__payment-methods-element-name">Luigi Bacco</p>
                    <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: 07/22</p>
                <!--</div>
                <div class="container-reserved-area__payment-methods-element-mastercard"></div>
            </div>
            <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal__payment_method_user_reserved_area(1)">
                <h3 class="container-reserved-area__payment-methods-element-main-title">
                    <!--Financial | <span>Elite</span>
                    <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                </h3>
                <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                <div class="container-reserved-area__payment-methods-element-chip"></div>
                <!--<div class="container-reserved-area__payment-methods-element-card-info">
                    <p class="container-reserved-area__payment-methods-element-no">5423  4426  6230  0041</p>
                    <p class="container-reserved-area__payment-methods-element-name">Luigi Bacco</p>
                    <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: 07/22</p>
                <!--</div>
                <div class="container-reserved-area__payment-methods-element-mastercard"></div>
            </div>
            <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal__payment_method_user_reserved_area(1)">
                <h3 class="container-reserved-area__payment-methods-element-main-title">
                    <!--Financial | <span>Elite</span>
                    <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                </h3>
                <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                <div class="container-reserved-area__payment-methods-element-chip"></div>
                <!--<div class="container-reserved-area__payment-methods-element-card-info">
                    <p class="container-reserved-area__payment-methods-element-no">5423  4426  6230  0041</p>
                    <p class="container-reserved-area__payment-methods-element-name">Luigi Bacco</p>
                    <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: 07/22</p>
                <!--</div>
                <div class="container-reserved-area__payment-methods-element-mastercard"></div>
            </div>
            <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal__payment_method_user_reserved_area(1)">
                <h3 class="container-reserved-area__payment-methods-element-main-title">
                    
                    <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                </h3>
                <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                <div class="container-reserved-area__payment-methods-element-chip"></div>
                
                <p class="container-reserved-area__payment-methods-element-no">5423  4426  6230  0041</p>
                <p class="container-reserved-area__payment-methods-element-name">Luigi Bacco</p>
                <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: 07/22</p>
                
                <div class="container-reserved-area__payment-methods-element-mastercard"></div>
            </div>-->
        </div>
    </div>
</div>

<div class="modal-update-delete-shipping-address">
    <div class="modal-update-delete-shipping-address__container">
        <div class="modal-update-delete-shipping-address__header">
            <div class="modal-update-delete-shipping-address__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_modal_shipping_address_user_reserved_area()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-update-delete-shipping-address__content">

            <!--<div class="modal-update-delete-shipping-address__filter">
                <label class="modal-update-delete-shipping-address__filter-label">Filtri</label>
                <input type="search" class="light-table-filter-line modal-update-delete-shipping-address__filter-input" data-table="order-table-line" placeholder="Cerca...">
            </div>-->
            <div class="modal-update-delete-shipping-address__address">
                <label class="modal-update-delete-shipping-address__address-label">Indirizzo</label>
                <input class="modal-update-delete-shipping-address__address-input" required>
            </div>

            <div class="modal-update-delete-shipping-address__city">
                <label class="modal-update-delete-shipping-address__city-label">Citta'</label>
                <input class="modal-update-delete-shipping-address__city-input" required>
            </div>

            <div class="modal-update-delete-shipping-address__cap">
                <label class="modal-update-delete-shipping-address__cap-label">CAP</label>
                <input class="modal-update-delete-shipping-address__cap-input" required>
            </div>
            
            <button class="modal-update-delete-shipping-address__delete-button" > <img src="./img/index_img/delete.png" class="modal-update-delete-shipping-address__delete-button-img"></button>

            <div class="modal-update-delete-shipping-address__save-button">
                
                <button class="modal-update-delete-shipping-address__save-button-button" > Salva</button> <!--onclick="onload_card_on_page(-1)"-->
            </div>
        </div>
    </div>
</div>

<div class="modal-delete-shipping-address">
    <div class="modal-delete-shipping-address__container">
        <div class="modal-delete-shipping-address__header modal-delete-shipping-address__header-delete">
            <div class="modal-delete-shipping-address__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_modal_delete_shipping_address()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-delete-shipping-address__content">
            <p class="modal-delete-shipping-address__text">Sei sicuro di voler cancellare l'elemento?</p>
            <div class="modal-delete-shipping-address__button-div">
                <a class="modal-delete-shipping-address__button modal-delete-shipping-address__button-no" onclick="close_modal_delete_shipping_address()">NO</a>
                <a class="modal-delete-shipping-address__button modal-delete-shipping-address__button-yes">SI</a>
            </div>
        </div>
    </div>
</div>

<div class="modal-update-delete-payment-methods">
    <div class="modal-update-delete-payment-methods__container">
        <div class="modal-update-delete-payment-methods__header">
            <div class="modal-update-delete-payment-methods__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_modal_payment_methods_user_reserved_area()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-update-delete-payment-methods__content">

            <!--<div class="modal-update-delete-payment-methods__filter">
                <label class="modal-update-delete-payment-methods__filter-label">Filtri</label>
                <input type="search" class="light-table-filter-line modal-update-delete-payment-methods__filter-input" data-table="order-table-line" placeholder="Cerca...">
            </div>-->
            <div class="modal-update-delete-payment-methods__no">
                <label class="modal-update-delete-payment-methods__no-label">Numero carta</label>
                <input class="modal-update-delete-payment-methods__no-input" required>
            </div>

            <div class="modal-update-delete-payment-methods__name">
                <label class="modal-update-delete-payment-methods__name-label">Nome intestatario carta</label>
                <input class="modal-update-delete-payment-methods__name-input" required>
            </div>

            <div class="modal-update-delete-payment-methods__exp-date">
                <label class="modal-update-delete-payment-methods__exp-date-label">Data di Scadenza</label>
                <div class="modal-update-delete-payment-methods__exp-date-input">
                    <select class="modal-update-delete-payment-methods__exp-date-input-month">
                        <option value="1">01</option>
                        <option value="2">02</option>
                        <option value="3">03</option>
                        <option value="4">04</option>
                        <option value="5">05</option>
                        <option value="6">06</option>
                        <option value="7">07</option>
                        <option value="8">08</option>
                        <option value="9">09</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                    </select>

                    <select class="modal-update-delete-payment-methods__exp-date-input-year">
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                    </select>
                </div>
            </div>

            <div class="modal-update-delete-payment-methods__cvv">
                <label class="modal-update-delete-payment-methods__cvv-label">CVV</label>
                <input class="modal-update-delete-payment-methods__cvv-input" required>
            </div>
            
            <button class="modal-update-delete-payment-methods__delete-button" > <img src="./img/index_img/delete.png" class="modal-update-delete-payment-methods__delete-button-img"></button>

            <div class="modal-update-delete-payment-methods__save-button">
                
                <button class="modal-update-delete-payment-methods__save-button-button" > Salva</button> <!--onclick="onload_card_on_page(-1)"-->
            </div>
        </div>
    </div>
</div>

<div class="modal-delete-payment-methods">
    <div class="modal-delete-payment-methods__container">
        <div class="modal-delete-payment-methods__header modal-delete-payment-methods__header-delete">
            <div class="modal-delete-payment-methods__x">
                <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                class="close bi bi-x float-right" onclick="close_modal_delete_payment_methods()" aria-label="close" viewBox="0 0 16 16">
                <path
                    d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                </svg>
            </div>
        </div>
        <div class="modal-delete-payment-methods__content">
            <p class="modal-delete-payment-methods__text">Sei sicuro di voler cancellare l'elemento?</p>
            <div class="modal-delete-payment-methods__button-div">
                <a class="modal-delete-payment-methods__button modal-delete-payment-methods__button-no" onclick="close_modal_delete_payment_methods()">NO</a>
                <a class="modal-delete-payment-methods__button modal-delete-payment-methods__button-yes">SI</a>
            </div>
        </div>
    </div>
</div>