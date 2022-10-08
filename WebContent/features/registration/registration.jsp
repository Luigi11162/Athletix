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

    <link rel="icon" type="image/x-icon" href="../../img/index_img/favicon.png">
    <link rel="stylesheet" href="../../style/common.css">
    <link href="./style/registration.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="../../script/notification.js"></script>
    <script type="text/javascript" src="./registration.js"></script>
    <title> Athletix | Registrazione </title>
</head>
<body>

    <div class="notification">
        <div class="notification__text"></div>
   </div>

    <div class="circle"></div>
    <form action="" class="form" id="form">
        <h3 class="form__h3"><b>Registrazione</b></h3>
        <p class="form__paragraph">Riempi questo form per iscriverti al nostro sito! </p>

        <div class="form__container">

            <div class="form__container-name">
                <label class="form__container-name-label">Nome</label>
                <input type="text" class="form__container-name-input" placeholder="" name="name" id="name" required>
            </div>
            
            <div class="form__container-surname">
                <label class="form__container-surname-label">Cognome</label>
                <input type="text" class="form__container-surname-input" placeholder="" required>
            </div>
          
            <div class="form__container-email">
                <label class="form__container-email-label">Email</label>
                <input type="text" class="form__container-email-input" placeholder="" required>
            </div>
            
            <div class="form__container-password">
                <label class="form__container-password-label">Password</label>
                <input type="password" class="form__container-password-input" placeholder="" required>
            </div>

            <div class="form__container-confirm-password">
                <label class="form__container-confirm-password-label">Conferma Password</label>
                <input type="password" class="form__container-confirm-password-input" placeholder="" required>
                
            </div>

            <div class="form__container-gender">
                <label class="form__container-gender-label">Genere</label>

                <div class="form__container-gender-container">
                    <div class="form__container-gender-male">
                        <input type="radio" class="form__container-gender-male-radio" name="gender" value="Maschio">
                        <label for="male" class="form__container-gender-male-label">Maschio</label>
                    </div>
                    
                    <div class="form__container-gender-female">
                        <input type="radio" class="form__container-gender-female-radio" name="gender" value="Femmina">
                        <label for="female" class="form__container-gender-female-label">Femmina</label>
                    </div>
                    
                    <div class="form__container-gender-notbinary">
                        <input type="radio" class="form__container-gender-notbinary-radio" name="gender" value="Nessuno" checked required >
                        <label for="notbinary" class="form__container-gender-notbinary-label">Preferisco non dirlo</label>
                    </div>
                </div>
            </div>

            <div class="form__container-terms">
                <p>Creando un account accetti i nostri termini e condizioni.</p>
            </div>

            <div class="form__container-button">
                <input type="button" class="form__container-button-button" onclick="controlloRegistrazione()" value="Registrati">
            </div>
        </div>

        <div class="form__container-signin">
            <p class="form__paragraph">Hai già un account? <a href="../login/login.jsp">Vai al login!</a></p>
        </div>
        

    </form>

     <script src="./registration.js"></script>
</body>
</html>