<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if(op!=null){
		response.sendRedirect(request.getContextPath()+"/reserved_area/dashboard/dashboard.jsp");
		return;
	}
	UtenteBean user=(UtenteBean) session.getAttribute("user");
	ArticoloDAO articoloDao=new ArticoloDAO();
	List<CarrelloBean> carrello=new ArrayList<>();
	double totale=0;
%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        
        <title>Athletix</title>
        <link rel="icon" type="image/x-icon" href="./img/index_img/favicon.png">

        <!--css-->
        <link rel="stylesheet" href="./style/common.css">
        <link rel="stylesheet" href="./style/index.css">
        <link rel="stylesheet" href="./features/catalog/style/catalog.css">
        <link rel="stylesheet" href="./features/article/style/article.css">
        <link rel="stylesheet" href="./features/user_reserved_area/style/user_reserved_area.css">
        <link rel="stylesheet" href="./features/shopping_cart/style/shopping_cart.css">
        <link rel="stylesheet" href="./features/orders/style/orders.css">

        <!--js-->
        <script type="text/javascript" src="./script/libs/hammer.js"></script>
        <script type="text/javascript" src="./script/libs/mobile_detector.js"></script>
        <script type="text/javascript" src="./script/libs/cookie_management.js"></script>
        <script type="text/javascript" src="./script/libs/question_modal.js"></script>
        <script type="text/javascript" src="./script/notification.js"></script>

        <script type="text/javascript" src="./features/catalog/catalog.js"></script>
        <script type="text/javascript" src="./features/article/article.js"></script>
        <script type="text/javascript" src="./features/user_reserved_area/user_reserved_area.js"></script>
        <script type="text/javascript" src="./features/shopping_cart/shopping_cart.js"></script>
        <script type="text/javascript" src="./features/orders/orders.js"></script>
        <script type="text/javascript" src="./script/index.js"></script>
        
    </head>
    <body onload="caricamento()">
        <div class="wrapper" id='wrapper'>

            <div class="notification">
                <div class="notification__text"></div>
            </div>

            <div id="header" class="header">

                <div class="header_content">
                    <div class="hamburger_menu_div" id="sidebarCollapse" onclick="open_sidenav()">
                        <img class="hamburger_menu" src="./img/index_img/hamburger.png" alt="">
                    </div>

                    <div class="header__category">
                        <a href="javascript:redirect_to_catalog_with_fields(1)" class="header__category-name">Pista</a>
                        <a href="javascript:redirect_to_catalog_with_fields(2)" class="header__category-name">Attrezzatura</a>
                        <a href="javascript:redirect_to_catalog_with_fields(3)" class="header__category-name">Abbigliamento</a>
                    </div>

                    <div class="header__logo" onclick="go_to_home()">
                        <img class="header__logo-img" src="./img/index_img/logo.png">
                    </div>
                    
                    <div class="header__selection">
                        <!-- The form -->
                        <!--<form class="header__selection-form" action="action_page.php">
                            <input  class="header__selection-form-text" type="text" placeholder="">
                            <button class="header__selection-form-button" type="submit"><img class="header__selection-form-button-img" src="./img/index_img/search.png"></button>
                        </form>
                        <button type="button" class="header__selection-button"><img class="header__selection-button-star" src="./img/index_img/star.png"></button>-->

                        <button type="button" class="header__selection-button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false" onclick="show_dropdown_menu()">
                            <img class="header__selection-button-user" src="./img/index_img/user.png">
                        </button>

                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                        <% 
								if((user!=null))
								{
									carrello=(ArrayList<CarrelloBean>)user.getCarrello();
							%>
                            <div class="dropdown-menu__with-login">
                                <div class="dropdown-menu__with-login-image">
                                    <img class="dropdown-menu__with-login-image-img" src="./img/index_img/user_registered.png">
                                </div>
                                <div class="dropdown-menu__with-login-name">
                                    <%=user.getNome()+" "+user.getCognome()%>
                                </div>
                                <hr class="dropdown-menu__with-login-hr">
                                <div class="dropdown-menu__with-login-reserved-area" onclick="redirect('user_reserved_area',true)">
                                    Area Riservata
                                </div>
                                <div class="dropdown-menu__with-login-orders" onclick="redirect('orders',true)">
                                    I miei ordini
                                </div>
                                <div class="dropdown-menu__with-login-button">
                                    <button class="dropdown-menu__with-login-button-button" onclick="logout()">Disconnetti</button>
                                </div>
                            </div>
    
                            
                            <!--<div class='name_operator'>
                                <span class="profile_name"></span>
                                <span class="group_name"></span>
                            </div>
                            <hr class="divider">
                            <div id="fullscreen_button" class="dropdown_item" onclick="set_fullscreen()">
                                <img src="./assets/img/dashboard_icons/fullscreen_icon.svg" alt="">
                                <span>Attiva schermo intero</span>
                            </div>
                            <div id="logout_button" class="dropdown_item"
                                onclick="create_question_modal('logout_modal', 'danger_header', 'Effettuare il logout?', 'logout()', '')">
                                <img src="./assets/img/dashboard_icons/logout_icon.svg" alt="">
                                <span>Logout</span>
                            </div>
                           	<a class="dropdown-item" id='logout' onclick='logout()'>LOGOUT</a> -->
                           	
                        <%
                            }
                            else
                            {
                                session.getAttribute("cart");
                                if(session.getAttribute("cart")!=null)
                                    carrello.addAll((ArrayList<CarrelloBean>)session.getAttribute("cart"));
                                
                        %>
	    					<div class="dropdown-menu__without-login">
                                <div class="dropdown-menu__without-login-name">
                                    Non hai effettuato il login
                                </div>
                                <div class="dropdown-menu__without-login-button">
                                    <button class="dropdown-menu__without-login-button-access" onclick="go_to_login_from_index()">Accedi</button>
                                    <button class="dropdown-menu__without-login-button-registration" onclick="go_to_registration_from_index()">Registrati</button>
                                </div>
                            </div>
                             <%
								}
	                        %>
                        </div>
					
                        
                        <button type="button" class="header__selection-button" onclick="open_cart()">
                            <!--<span class='header__selection-button-cart-span'> 5 </span>-->
                            <img class="header__selection-button-cart" src="./img/index_img/shopping-cart.png"> 
                            <span class="header__selection-button-cart-number">
                                <%=carrello.size()%>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <div id="sidebar" > <!--class='shadow menu'-->
    
                <div class='header_menu'>
    
                    <div id="dismiss" onclick="close_menu()">
                        <img id='arrow_left' src='./img/index_img/arrow_left.png'>
                    </div>
    
                    <!--<div class="logo_div"><img class="logo" src="./assets/img/dashboard_icons/menu/logo.png" alt=""></div>-->
    
                    <!--<div class="pin_div" onclick='toggle_sidenav_pin()'>
                        <img id='img_pin' src='./assets/img/dashboard_icons/menu/pin.png'>
                    </div>-->
                </div>
    
                <div id="menu_container" class="menu_container">
                    <!--<div onclick="redirect('home', '')"
                        class="navigation_button "
                        style="margin-left:0px">
    
                        <div class="navigation_button_icon ">
                            <img src="./assets/img/dashboard_icons/menu/home_icon.png" alt="" class="menu_icon">
                        </div>
    
                        <span class="navigation_button_text">Home</span>
                    </div>-->
                    <div class="menu_container__profile" onclick="redirect('user_reserved_area',true)">
                        <img src="./img/index_img/user.png" class="menu_container__profile-image">
                        <div class="menu_container__profile-text"> Area riservata</div>
                    </div>

                    <div class="menu_container__cart" onclick="redirect('shopping_cart',true)">
                        <img src="./img/index_img/shopping-cart.png" class="menu_container__cart-image">
                        <div class="menu_container__cart-text"> Carrello</div>
                    </div>

                    <div class="menu_container__order" onclick="redirect('orders',true)">
                        <img src="./img/index_img/order.png" class="menu_container__order-image">
                        <div class="menu_container__order-text"> I miei ordini</div>
                    </div>

                </div>
    
            </div>

            <div class="cart-div" > <!--class='shadow menu'-->
    
                <div class='cart-div__arrow' onclick="close_cart()">
                    <img class="cart-div__arrow-img" src='./img/index_img/arrow_left.png'>
                </div>

                <!--<div class="cart-div__text">
                    CARRELLO
                </div>-->
    
                <div class="cart-div__content">
                   <!-- <div class="cart-div__content-element">
                        <div class="cart-div__content-element-image"><img class="cart-div__content-element-image-img" src="./img/.jpg"></div>
                        <p class="cart-div__content-element-titol"></p>
                        <p class="cart-div__content-element-price"> €</p>
                        <p class="cart-div__content-element-quantity"></p>
                        <div class="cart-div__content-element-cancel"><img class="cart-div__content-element-cancel-img" src="./img/index_img/delete.png" onclick="delete_article_from_cart(<)"></div>
                    </div>-->
                </div>

                <div class="cart-div__total">
                    <div class="cart-div__total-price"></div>
                    <div class="cart-div__total-button">
                        <a class="cart-div__total-button-cart" href="javascript: redirect('shopping_cart',true)">Vai al carrello</a>
                        <button class="cart-div__total-button-button" onclick="open_modal_buy_shopping_cart()">Acquista</button>
                    </div>
                     
                </div>
    
            </div>

            <div id="page_container" class="overflow-hidden" ></div>
            
        </div>

        

        <div class="modal-buy-shopping-cart">
            <div class="modal-buy-shopping-cart__container">
                <div class="modal-buy-shopping-cart__header">
                    <div class="modal-buy-shopping-cart__x">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                        class="close bi bi-x float-right" onclick="close_modal_buy_shopping_cart()" aria-label="close" viewBox="0 0 16 16">
                        <path
                            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                        </svg>
                    </div>
                </div>
                <div class="modal-buy-shopping-cart__content">
                    <div class="modal-buy-shopping-cart__shipping-address">
                        <label class="modal-buy-shopping-cart__shipping-address-label">Indirizzo</label>
                        <select class="modal-buy-shopping-cart__shipping-address-select">
                            
                        </select>
                    </div>
        
                    <div class="modal-buy-shopping-cart__payment-methods">
                        <label class="modal-buy-shopping-cart__payment-methods-label">Metodo di pagamento</label>
                        <select class="modal-buy-shopping-cart__payment-methods-select">
                            
                        </select>
                    </div>
        
                    <div class="modal-buy-shopping-cart__save-button">
                        
                        <button class="modal-buy-shopping-cart__save-button-button" onclick="buy_cart_by_shopping_cart()"> Compra</button> <!--onclick="onload_card_on_page(-1)"-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>