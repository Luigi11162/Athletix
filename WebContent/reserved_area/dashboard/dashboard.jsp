<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	if (op==null)
	{	
    	response.sendRedirect(request.getContextPath()+"/reserved_area/authentication/login-form.jsp");
    	return;
	}
%>
<html>
    <head>
        <!--<meta property="og:site_name" content="Comix21">
        <meta property="og:title" content="Athletix" />
        <meta property="og:image" itemprop="image" content="https://Athletix.it/img/logo.png">
        <meta property="og:type" content="website" />
        <meta property="og:updated_time" content="1440432930" />
        <meta property="og:url" content="https://Athletix.it">-->

		<link rel="icon" type="image/x-icon" href="../../img/index_img/favicon.png">
        <meta charset="UTF-8">

        <!--css-->
        <link rel="stylesheet" href="../../style/common.css">
        <link rel="stylesheet" href="./style/dashboard_style.css">
        <link rel="stylesheet" href="./features/article/style/article.css">
        <link rel="stylesheet" href="./features/category/style/category.css">
        <link rel="stylesheet" href="./features/unity/style/unity.css">
        <link rel="stylesheet" href="./features/size/style/size.css">
        <link rel="stylesheet" href="./features/customers/style/customers.css">
        <link rel="stylesheet" href="./features/orders_history/style/orders_history.css">

        <!--js-->
        <script type="text/javascript" src="./script/libs/hammer.js"></script>
        <script type="text/javascript" src="./script/libs/mobile_detector.js"></script>
        <script type="text/javascript" src="./script/libs/cookie_management.js"></script>
        <script type="text/javascript" src="./script/libs/question_modal.js"></script>
        <script type="text/javascript" src="../../script/notification.js"></script>
        
        
        <script type="text/javascript" src="./features/article/article.js"></script>
        <script type="text/javascript" src="./features/category/category.js"></script>
        <script type="text/javascript" src="./features/unity/unity.js"></script>
        <script type="text/javascript" src="./features/size/size.js"></script>
        <script type="text/javascript" src="./features/customers/customers.js"></script>
        <script type="text/javascript" src="./features/orders_history/orders_history.js"></script>
        <script type="text/javascript" src="./script/dashboard_script.js"></script>


        <title>Dashboard</title>
    </head>
    <body onload="caricamento()">
        <div class="wrapper" id='wrapper'>

            <div class="notification">
                <div class="notification__text"></div>
            </div>

            <div id="header" class="header">

                <div class="header_content">
                    <div class="hamburger_menu_div" id="sidebarCollapse" onclick="open_sidenav()">
                        <img class="hamburger_menu" src="./assets/img/dashboard_icons/menu/hamburger_menu_3.png" alt="">
                    </div>

                    <div class="clock" id="clock"></div>

                    <div id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false" onclick="show_dropdown_menu()">
                        <img src="./assets/img/dashboard_icons/profile_icon.svg" alt="">
                    </div>

                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <div class='name_operator'>
                            <span class="profile_name"></span>
                            <span class="group_name"></span>
                        </div>
                        <hr class="divider">
                        <div id="fullscreen_button" class="dropdown_item" onclick="set_fullscreen()">
                            <img src="./assets/img/dashboard_icons/fullscreen_icon.svg" alt="">
                            <span>Attiva schermo intero</span>
                        </div>
                        <div id="logout_button" class="dropdown_item"
                            onclick="open_logout_modal()">
                            <img src="./assets/img/dashboard_icons/logout_icon.svg" alt="">
                            <span>Logout</span>
                        </div>
                        <!-- <a class="dropdown-item" id='logout' onclick='logout()'>LOGOUT</a> -->
                    </div>
                </div>
            </div>
            <div id="sidebar" > <!--class='shadow menu'-->
    
                <div class='header_menu'>
    
                    <div id="dismiss" onclick="close_menu()">
                        <img id='arrow_left' src='./assets/img/dashboard_icons/menu/arrow_left.png'>
                    </div>
    
                    <div class="logo_div"><img class="logo" src="./assets/img/dashboard_icons/menu/logo.png" alt=""></div>
    
                    <div class="pin_div" onclick='toggle_sidenav_pin()'>
                        <img id='img_pin' src='./assets/img/dashboard_icons/menu/pin.png'>
                    </div>
                </div>
    
               <div id="menu_container" class="menu_container">
                    <div onclick="redirect('article', '')"
                        class="navigation_button "
                        style="margin-left:0px">
    
                        <div class="navigation_button_icon ">
                            <img src="./assets/img/dashboard_icons/menu/home_icon.png" alt="" class="menu_icon">
                        </div>
    
                        <span class="navigation_button_text">Home</span>
                    </div>
                </div>
                
    
            </div>

            <div id="page_container" class="overflow-hidden"></div>
        </div>

        <div class="modal-logout">
            <div class="modal-logout__container">
                <div class="modal-logout__header modal-logout__header-delete">
                    <div class="modal-logout__x">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="currentColor" width="2em" height="2em"
                        class="close bi bi-x float-right" onclick="close_logout_modal()" aria-label="close" viewBox="0 0 16 16">
                        <path
                            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                        </svg>
                    </div>
                </div>
                <div class="modal-logout__content">
                    <p class="modal-logout__text">Sei sicuro di voler effettuare il logout?</p>
                    <div class="modal-logout__button-div">
                        <a class="modal-logout__button modal-logout__button-no" onclick="close_logout_modal()">NO</a>
                        <a class="modal-logout__button modal-logout__button-yes" onclick="logout()">SI</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>