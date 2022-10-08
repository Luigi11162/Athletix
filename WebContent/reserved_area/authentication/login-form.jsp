<%@ page language="java" import="java.util.*" import="Model.bean.*" import="Model.DAO.*" import="java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	OperatoreBean op=(OperatoreBean) session.getAttribute("operator");
	UtenteBean us=(UtenteBean) session.getAttribute("user");
	if (us!=null)
	{	
    	response.sendRedirect(request.getContextPath());
    	return;
	}
	if(op!=null)
	{
		response.sendRedirect(request.getContextPath()+"/reserved_area/dashboard/dashboard.jsp");
    	return;
	}
%>
<!DOCTYPE html>
<html>
     <head>
          <link rel="stylesheet" href="../../style/common.css">
          <link rel="stylesheet" href="./style_login/login.css">

			<link rel="icon" type="image/x-icon" href="../../img/index_img/favicon.png">
          <script type="text/javascript" src="../../script/notification.js"></script>
          <script src="./login.js"></script>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>Login</title>
     </head>
     <body>

          <div class="notification">
               <div class="notification__text"></div>
          </div>
          

          <div class="login-wrapper">
               
               <form class='form' id="login_container" action="Login" method="post">
                    <!-- <img onclick="window.location.href='../../index.html'" src="../../img/logo_black.png" alt=""> -->
                    <!--<h2>Login</h2>-->
                    <div class="input-group">
                         <input autocomplete="" type="text" name="Email" id="loginUser" required>
                         <label for="loginUser">Username</label>
                    </div>
                    <div class="input-group">
                         <input autocomplete="" type="password" name="Password" id="loginPassword" required>
                         <label for="loginPassword">Password</label>
                    </div>
               
                    <input type="button" onclick="login()" value="Login" class="submit-btn">
                    <input type="reset" value="reset" class="btn-indietro">
               </form>
          
          </div>

     </body>
</html>
