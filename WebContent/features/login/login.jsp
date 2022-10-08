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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Athletix | Login</title>

        <link rel="icon" type="image/x-icon" href="../../img/index_img/favicon.png">
        <link rel="stylesheet" href="../../style/common.css">
        <link rel="stylesheet" href="./style/login.css">

        <script type="text/javascript" src="../../script/notification.js"></script>
        <script type="text/javascript" src="./login.js"></script>
    </head>
    <body>

        <div class="notification">
            <div class="notification__text"></div>
       </div>
        
        <div class="circle"></div>
        <div class="form">
            <h2 class="form__title">Effettua il login</h2>
            <p class="form__paragraph">Torna all' <a href="../../index.jsp" class="form__link">homepage</a></p>
            
                <div class="form__container">

                    <div class="form__container-group">
                        <label for="email" class="form__container-label" name="loginUser">Email</label>
                        <input type="email" id="email" class="form__container-input" placeholder="">
                    </div>

                    <div class="form__container-group">
                        <label for="password" class="form__container-label" name="loginPassword">Password</label>
                        <input type="password" id="password" class="form__container-input" placeholder="">
                    </div>
                    
                    <input type="button" class="form__container-submit" value="Invia" onclick="login()">

                </div>
                
                <div class="form__container-signup">
            		<p class="form__paragraph">Non hai un account? <a href="../registration/registration.jsp">Registrati!</a></p>
        		</div>
            </div>

    </body>
</html>