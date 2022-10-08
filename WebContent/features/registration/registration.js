function controlloRegistrazione() {

	if (controlloEmail() && controlloPassword()) {
		registrazione();
	}
}


function controlloEmail() {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.querySelector(".form__container-email-input").value)) {
		return (true)
	}		 
	else {
		notification("notification_danger","Hai inserito una email non valida!");
		return (false)
		}
	
}
	
function controlloPassword() { 
	var passw= /^[A-Za-z]\w{7,14}$/;
	
	if(document.querySelector(".form__container-password-input").value.match(passw)) { 
		if(document.querySelector(".form__container-password-input").value==document.querySelector(".form__container-confirm-password-input").value)
			return (true);
		else
			notification("notification_danger","Password non uguale a Conferma Password!");
	}
	
	else { 
		notification("notification_danger","Password non valida!");
	return false;
	}	
} 

function json_registrazione(){
    let gender = document.getElementsByName("gender").forEach(radio =>{
        if(radio.checked)
            return radio.value;
    })
	 return ` {
	    "name" : "${document.querySelector(".form__container-name-input").value}",
	    "surname" : "${document.querySelector(".form__container-surname-input").value}",
	    "gender" : "${gender}",
	    "username" : "${document.querySelector(".form__container-email-input").value}",
	    "password" : "${document.querySelector(".form__container-password-input").value}",
	  }`;
}

function registrazione() {
	var json=json_registrazione();
	var id=-1;
    var username = document.querySelector(".form__container-email-input").value;
    var password = document.querySelector(".form__container-password-input").value;
    var name = document.querySelector(".form__container-name-input").value;
    var surname = document.querySelector(".form__container-surname-input").value;
    
    if (name == "" || surname == "")
    {
        notification("notification_danger","Si prega di inserire i dati dell'account");
    }   
    else {
        if (username == "" || password == "")
        {
            notification("notification_danger","Si prega di inserire le credenziali");
        }   
        else {
            fetch('/Athletix/UpdateUtente', {  //da mettere la servlet che si occupa dell registrazione del cliente
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    id: id,
                    json: json
                }),
            })
            .then(res => {
                res = res.headers.get('res');
                console.log(res)
                    if (res != "500" && res != "0" && res != 'session_expired') {
                        if (res == "200") {
                            location.href = '../../index.jsp';
                        } else if(res=="400"){
                        	notification("notification_danger","Email già registrata");	
                        } else if (res == "403") {
                            notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                        }
                    } else {
                        notification("notification_danger","Errore interno, si prega di riprovare");
                    }
                })
            .catch((error) => {
                console.error('Error:', error);
            });
        }
    }
}