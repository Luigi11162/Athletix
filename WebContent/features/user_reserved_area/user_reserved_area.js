
//onload
function onload_user_reserved_area(){
	delete_change_data_user_reserved_area();
    onload_shipping_address_user_reserved_area();
    onload_payment_methods_user_reserved_area();
}

function onload_shipping_address_user_reserved_area(){
    fetch('/Athletix/RichiestaRecapito', { //servlet per caricare tutti gli indirizzi di spedizione dell'utente
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: -1
        }),
    })
    .then(res => {
    		json = JSON.parse(res.headers.get('json_shipping_address_user_reserved_area'));
    		json=json.json;
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    onload_shipping_address_user_reserved_area_html(json);
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function onload_shipping_address_user_reserved_area_html(json){
    document.querySelector(".container-reserved-area__shipping-address-element").innerHTML = "";
    let content ="";
    for(let i=0; i< json.length; i++)
        content += `
                <div class="container-reserved-area__shipping-address-element-div" onclick="open_modal_shipping_address_user_reserved_area(${json[i].id})">
                    <div class="container-reserved-area__shipping-address-element-address">
                        <label class="container-reserved-area__shipping-address-element-address-label">Indirizzo: </label>
                        <label class="container-reserved-area__shipping-address-element-address-label-text">${json[i].address}</label>
                    </div>

                    <div class="container-reserved-area__shipping-address-element-city">
                        <label class="container-reserved-area__shipping-address-element-city-label">Città: </label>
                        <label class="container-reserved-area__shipping-address-element-city-label-text">${json[i].city}, ${json[i].cap}</label>
                    </div>
                </div>
                 `;
    content +=`
                <div class="container-reserved-area__shipping-address-element-div-add" onclick="open_modal_shipping_address_user_reserved_area(-1)">
                    +
                </div>
                `;

    document.querySelector(".container-reserved-area__shipping-address-element").innerHTML = content;
}

function onload_payment_methods_user_reserved_area(){
    fetch('/Athletix/RichiestaPagamento', { //servlet per caricare tutti gli indirizzi di spedizione dell'utente
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: -1
        }),
    })
    .then(res => {
    		json = JSON.parse(res.headers.get('json_payment_methods_user_reserved_area'));
    		json=json.json;
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    onload_payment_methods_user_reserved_area_html(json);
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function onload_payment_methods_user_reserved_area_html(json){
    document.querySelector(".container-reserved-area__payment-methods-element").innerHTML = "";
    let content ="";
    for(let i=0; i< json.length; i++)
        content += `
                    <div class="container-reserved-area__payment-methods-element-div" onclick="open_modal_payment_methods_user_reserved_area(${json[i].id})">
                        <h3 class="container-reserved-area__payment-methods-element-main-title">
                            
                            <img src="./img/index_img/logo.png" class="container-reserved-area__payment-methods-element-main-title-img">
                        </h3>
                        <i class="container-reserved-area__payment-methods-element-globe" class="fa fa-globe"></i>
                        <!--<div class="container-reserved-area__payment-methods-element-chip"></div>-->
                        
                        <p class="container-reserved-area__payment-methods-element-no">${json[i].no.substring(0,4) + " **** **** "+ json[i].no.substring(12,16)}</p>
                        <p class="container-reserved-area__payment-methods-element-name">${json[i].name}</p>
                        <p class="container-reserved-area__payment-methods-element-exp-date"><span>Data di scadenza</span>: ${json[i].expdate}</p>
                        
                        <div class="container-reserved-area__payment-methods-element-mastercard"></div>
                    </div>
                 `;
    content +=`
                <div class="container-reserved-area__payment-methods-element-div-add" onclick="open_modal_payment_methods_user_reserved_area(-1)">
                    +
                </div>
                `;

    document.querySelector(".container-reserved-area__payment-methods-element").innerHTML = content;
}

//operation
function change_data_user_reserved_area(){
    document.querySelector(".container-reserved-area__profile-text-surname-input").disabled = false;
    document.querySelector(".container-reserved-area__profile-text-name-input").disabled = false;
    document.querySelector(".container-reserved-area__profile-text-email-input").disabled = false;
    document.querySelector(".container-reserved-area__profile-text-password-input").disabled = false;

    document.querySelector(".container-reserved-area__profile-text-button-change").style.display = "none";
    document.querySelector(".container-reserved-area__profile-text-button-delete").style.display = "block";
    document.querySelector(".container-reserved-area__profile-text-button-save").style.display = "block";
}

function delete_change_data_user_reserved_area(){

    fetch('/Athletix/RichiestaUtente', { //servlet per ridarmi i dati dell'utente
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(res => {
    		json = JSON.parse(res.headers.get('json_user_reserved_area'));
            res = res.headers.get('res');        
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    document.querySelector(".container-reserved-area__profile-text-surname-input").value = json.surname;
                    document.querySelector(".container-reserved-area__profile-text-name-input").value = json.name;
                    document.querySelector(".container-reserved-area__profile-text-email-input").value = json.email;

                    document.querySelector(".container-reserved-area__profile-text-surname-input").disabled = true;
                    document.querySelector(".container-reserved-area__profile-text-name-input").disabled = true;
                    document.querySelector(".container-reserved-area__profile-text-email-input").disabled = true;
                    document.querySelector(".container-reserved-area__profile-text-password-input").disabled = true;

                    document.querySelector(".container-reserved-area__profile-text-button-change").style.display = "block";
                    document.querySelector(".container-reserved-area__profile-text-button-delete").style.display = "none";
                    document.querySelector(".container-reserved-area__profile-text-button-save").style.display = "none";
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function update_change_data_user_reserved_area(){
	if(controlloDati()){
	    let json = `{
	                    "surname":"${document.querySelector(".container-reserved-area__profile-text-surname-input").value}",
	                    "name":"${document.querySelector(".container-reserved-area__profile-text-name-input").value}",
	                    "email":"${document.querySelector(".container-reserved-area__profile-text-email-input").value}",
	                    "password":"${document.querySelector(".container-reserved-area__profile-text-password-input").value}"
	                }`
	    fetch('/Athletix/UpdateUtente', { //servlet per salvare i dati dell'utente
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify({
	            json_user_reserved_area: json
	        }),
	    })
	    .then(res => {
	    		json = JSON.parse(res.headers.get('json_user_reserved_area'));
	            res = res.headers.get('res');          
	            if (res != "500" && res != "0" && res != 'session_expired') {
	                if (res == "200") {
	
	                    notification("notification_success","Dati cambiati correttamente");
	                    
	                    document.querySelector(".container-reserved-area__profile-text-surname-input").disabled = true;
	                    document.querySelector(".container-reserved-area__profile-text-name-input").disabled = true;
	                    document.querySelector(".container-reserved-area__profile-text-email-input").disabled = true;
	                    document.querySelector(".container-reserved-area__profile-text-password-input").disabled = true;
	
	                    document.querySelector(".container-reserved-area__profile-text-button-change").style.display = "block";
	                    document.querySelector(".container-reserved-area__profile-text-button-delete").style.display = "none";
	                    document.querySelector(".container-reserved-area__profile-text-button-save").style.display = "none";
	                } else if (res == "403") {
	                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
	                }
	            } else {
	                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
	            }
	        
	        })
	    .catch((error) => {
	        console.error('Error:', error);
	    });
	}
}


//indirizzi di spedizione
function open_modal_shipping_address_user_reserved_area(value){
    default_value_modal_shipping_address_user_reserved_area();
    document.querySelector(".modal-update-delete-shipping-address__delete-button").style.display = "none";
    if(value != -1){
        document.querySelector(".modal-update-delete-shipping-address__delete-button").style.display = "block";
        download_value_modal_shipping_address_user_reserved_area(value);
    }

    document.querySelector(".modal-update-delete-shipping-address__delete-button").onclick = () =>{ open_delete_modal_shipping_address_user_reserved_area(value); }
    document.querySelector(".modal-update-delete-shipping-address__save-button").onclick = () =>{ upload_value_modal_shipping_address_user_reserved_area(value); }
    
    document.querySelector(".modal-update-delete-shipping-address").style.display = "flex";

}

function close_modal_shipping_address_user_reserved_area(){
    document.querySelector(".modal-update-delete-shipping-address").style.display = "none";
}

function default_value_modal_shipping_address_user_reserved_area(){
    document.querySelector(".modal-update-delete-shipping-address__address-input").value = "";
    document.querySelector(".modal-update-delete-shipping-address__city-input").value = "";
    document.querySelector(".modal-update-delete-shipping-address__cap-input").value = "";
}

function download_value_modal_shipping_address_user_reserved_area(value){
    fetch('/Athletix/RichiestaRecapito', { //servlet per ridarmi i dati dell'utente
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: value
        }),
    })
    .then(res => {
    		json = JSON.parse(res.headers.get('json_shipping_address_user_reserved_area'));
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    document.querySelector(".modal-update-delete-shipping-address__address-input").value = json.address;
                    document.querySelector(".modal-update-delete-shipping-address__city-input").value = json.city;
                    document.querySelector(".modal-update-delete-shipping-address__cap-input").value = json.cap;
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function upload_value_modal_shipping_address_user_reserved_area(value){
	
	let json = `{
         "address":"${document.querySelector(".modal-update-delete-shipping-address__address-input").value}",
         "city":"${document.querySelector(".modal-update-delete-shipping-address__city-input").value}",
         "cap":"${document.querySelector(".modal-update-delete-shipping-address__cap-input").value}",
     }`
	
    fetch('/Athletix/UpdateRecapito', { //servlet per salvare i dati dell'indirizzo di spedizione
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
	        id: value,
	        json: json
	    }),
    })
    .then(res => {
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    notification("notification_success","Indirizzo salvato correttamente");
                    close_modal_shipping_address_user_reserved_area();
                    onload_shipping_address_user_reserved_area();
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function open_delete_modal_shipping_address_user_reserved_area(value){
    document.querySelector(".modal-delete-shipping-address").style.display = "flex";
    document.querySelector(".modal-delete-shipping-address__button-yes").addEventListener('click', function(){
        delete_shipping_address(value);
      });
}

function close_modal_delete_shipping_address(){
    document.querySelector(".modal-delete-shipping-address").style.display = "none";
}

function delete_shipping_address(value){
    fetch('/Athletix/RemoveRecapito', { //servlet per cancellare un indirizzo di spedizione
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
	        id: value
	    }),
    })
    .then(res => {
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    notification("notification_success","Indirizzo cancellato correttamente");
                    close_modal_shipping_address_user_reserved_area();
                    close_modal_delete_shipping_address()
                    onload_shipping_address_user_reserved_area();
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}


//metodi di pagamento
function open_modal_payment_methods_user_reserved_area(value){
    default_value_modal_payment_methods_user_reserved_area();
    document.querySelector(".modal-update-delete-payment-methods__delete-button").style.display = "none";
    if(value != -1){
        document.querySelector(".modal-update-delete-payment-methods__delete-button").style.display = "block";
        download_value_modal_payment_methods_user_reserved_area(value);
    }

    document.querySelector(".modal-update-delete-payment-methods__delete-button").onclick = () =>{ open_delete_modal_payment_methods_user_reserved_area(value); }
    document.querySelector(".modal-update-delete-payment-methods__save-button").onclick = () =>{ upload_value_modal_payment_methods_user_reserved_area(value); }
    
    document.querySelector(".modal-update-delete-payment-methods").style.display = "flex";

}

function close_modal_payment_methods_user_reserved_area(){
    document.querySelector(".modal-update-delete-payment-methods").style.display = "none";
}

function default_value_modal_payment_methods_user_reserved_area(){
    document.querySelector(".modal-update-delete-payment-methods__no-input").value = "";
    document.querySelector(".modal-update-delete-payment-methods__name-input").value = "";
    document.querySelector(".modal-update-delete-payment-methods__exp-date-input-month").getElementsByTagName('option')[0].selected='selected';
    document.querySelector(".modal-update-delete-payment-methods__exp-date-input-year").getElementsByTagName('option')[0].selected='selected';
    document.querySelector(".modal-update-delete-payment-methods__cvv-input").value = "";
}

function download_value_modal_payment_methods_user_reserved_area(value){
    fetch('/Athletix/RichiestaPagamento', { //servlet per ridarmi i dati dell'utente
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: value
        }),
    })
    .then(res => {
    		json = JSON.parse(res.headers.get('json_payment_methods_user_reserved_area'));
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    document.querySelector(".modal-update-delete-payment-methods__no-input").value = json.no.substring(0,4)+ " " +json.no.substring(4,8)+" "+json.no.substring(8,12)+ " " +json.no.substring(12,16);
                    document.querySelector(".modal-update-delete-payment-methods__name-input").value = json.name;
                    for(let i=0;i<12;i++){
                        if(json.expdate_month == document.querySelector(".modal-update-delete-payment-methods__exp-date-input-month").getElementsByTagName('option')[i].value)
                            document.querySelector(".modal-update-delete-payment-methods__exp-date-input-month").getElementsByTagName('option')[i].selected = "selected";
                    }

                    for(let i=0;i<6;i++){
                        if(json.expdate_year == document.querySelector(".modal-update-delete-payment-methods__exp-date-input-year").getElementsByTagName('option')[i].value)
                            document.querySelector(".modal-update-delete-payment-methods__exp-date-input-year").getElementsByTagName('option')[i].selected = "selected";
                    }
                    //document.querySelector(".modal-update-delete-payment-methods__exp-date-input").value = json.expdate;
                    document.querySelector(".modal-update-delete-payment-methods__cvv-input").value = json.cvv;
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function upload_value_modal_payment_methods_user_reserved_area(value){
	let select_month = document.querySelector(".modal-update-delete-payment-methods__exp-date-input-month");
    let select_year = document.querySelector(".modal-update-delete-payment-methods__exp-date-input-year");
	let json = `{
         "no":"${document.querySelector(".modal-update-delete-payment-methods__no-input").value.replace(/\s+/g, '')}",
         "name":"${document.querySelector(".modal-update-delete-payment-methods__name-input").value}",
         "expdate_month":"${select_month.options[select_month.selectedIndex].value}",
         "expdate_year":"${select_year.options[select_year.selectedIndex].value}",
         "cvv":"${document.querySelector(".modal-update-delete-payment-methods__cvv-input").value}"
     }`
	if(controllaCarta())
	{
	    fetch('/Athletix/UpdatePagamento', { //servlet per salvare i dati dell'indirizzo di spedizione
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json',
	        },
	        body: JSON.stringify({
		        id: value,
		        json: json
		    }),
	    })
	    .then(res => {
	            res = res.headers.get('res');
	            if (res != "500" && res != "0" && res != 'session_expired') {
	                if (res == "200") {
	                    notification("notification_success","Metodo di pagamento salvato correttamente");
	                    close_modal_payment_methods_user_reserved_area();
	                    onload_payment_methods_user_reserved_area();
	                } else if (res == "403") {
	                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
	                }
	            } else {
	                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
	            }
	        
	        })
	    .catch((error) => {
	        console.error('Error:', error);
	    });
	}
}

function open_delete_modal_payment_methods_user_reserved_area(value){
    document.querySelector(".modal-delete-payment-methods").style.display = "flex";
    document.querySelector(".modal-delete-payment-methods__button-yes").addEventListener('click', function(){
        delete_payment_methods(value);
      });
}

function close_modal_delete_payment_methods(){
    document.querySelector(".modal-delete-payment-methods").style.display = "none";
}

function delete_payment_methods(value){
    fetch('/Athletix/RemovePagamento', { //servlet per cancellare un indirizzo di spedizione
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
	        id: value
	    }),
    })
    .then(res => {
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    notification("notification_success","Metodo di pagamento cancellato correttamente");
                    close_modal_payment_methods_user_reserved_area();
                    close_modal_delete_payment_methods()
                    onload_payment_methods_user_reserved_area();
                } else if (res == "403") {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            } else {
                notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
            }
        
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}


//controllo dati 

function controlloDati() {

	if (controlloEmail() && controlloPassword()) {
		return (true)
	}
}

function controlloEmail() {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.querySelector(".container-reserved-area__profile-text-email-input").value)) {
		return (true)
	}		 
	else {
		notification("notification_danger","Hai inserito una email non valida!");
		return (false)
		}
	
}
	
function controlloPassword() { 
	var passw= /^[A-Za-z]\w{7,14}$/;
	
	if(document.querySelector(".container-reserved-area__profile-text-password-input").value.match(passw) || document.querySelector(".container-reserved-area__profile-text-password-input").value=="") { 
	return (true);
	}
	
	else { 
		notification("notification_danger","Password non valida!");
	return false;
	}	
} 

function controllaCarta() {
	let number=document.querySelector(".modal-update-delete-payment-methods__no-input").value.replace(/\s+/g, '');
	let cvv=document.querySelector(".modal-update-delete-payment-methods__cvv-input").value;
	let nome=document.querySelector(".modal-update-delete-payment-methods__name-input").value;

	if(number=="" || number.length!=16){
		notification("notification_danger","Numero carta non valida!");
		return (false);
	}
	if(cvv=="" || cvv.length!=3)
	{
		notification("notification_danger","CVV non valido!");
		return (false);
	}
	if(nome==""){
			notification("notification_danger","Nome non valido!");
			return(false);
	}
	return (true);
}