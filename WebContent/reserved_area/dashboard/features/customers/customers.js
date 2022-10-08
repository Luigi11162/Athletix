//modal
function open_update_modal_customers(value){
  modal_update_customers_request(value);

  if(document.querySelector("#page_container").classList.contains("active"))
    document.querySelector(".modal-update-customers").style.marginLeft = "-280px";
  else
    document.querySelector(".modal-update-customers").style.marginLeft = "0px";
  document.querySelector(".modal-update-customers").style.display = "flex";
}

function default_value_update_modal_customers(){
	document.querySelector(".modal-update-customers__surname-field").value = "";
	  document.querySelector(".modal-update-customers__name-field").value = "";
	  document.querySelector(".modal-update-customers__status-field").value = "";
	  document.querySelector(".modal-update-customers__gender-field").value = "";
	    
	  document.querySelector(".modal-update-customers__save-button").onclick = () =>{ update_customers(-1); } 
	}

function modal_update_customers_request(value){
	  fetch('/Athletix/RichiestaUtente', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json',
	    },
	    body: JSON.stringify({
	        api: 'get_date_customers_element',
	        id: value
	    }),
	  })
	  //.then(response => response.text())
	  .then(res => {
		  default_value_update_modal_customers();
		  json_value_update_modal_customers(res.headers.get('json_customers'),value);
	      })
	  .catch((error) => {
	      console.error('Error:', error);
	  });
}
  
function json_value_update_modal_customers(json,value){
  json = JSON.parse(json);
	  
  document.querySelector(".modal-update-customers__surname-field").value = json.surname;
  document.querySelector(".modal-update-customers__name-field").value = json.name;
  document.querySelector(".modal-update-customers__status-field").value = json.status;
  document.querySelector(".modal-update-customers__gender-field").value = json.gender;
  
  document.querySelector(".modal-update-customers__save-button").onclick = () =>{ update_customers(value); }

}

function close_update_modal_customers(){
    document.querySelector(".modal-update-customers").style.display = "none";
}

function json_send_update_modal_customers(){

  return ` {
   "surname" : "${document.querySelector(".modal-update-customers__surname-field").value}",
    "name" : "${document.querySelector(".modal-update-customers__name-field").value}",
    "gender" : "${document.querySelector(".modal-update-customers__gender-field").value}",
    "status" : "${document.querySelector(".modal-update-customers__status-field").value}"
  }`;
}

function update_customers(value){
  let json = json_send_update_modal_customers();
  fetch('/Athletix/UpdateUtente', {  //modifica cliente
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        id: value,
        json: json
    }),
  })
  //.then(response => response.text())
  .then(res => {
      res = res.headers.get('res');
      if (res != "500" && res != "0" && res != 'session_expired') {
        if (res == "200") {
          close_update_modal_customers();
          notification("notification_success","Operazione eseguita con successo");
          redirect("customers",true);
          
        } else if (res == "403") {
            notification("notification_danger","Richiesta non andata a buon fine, riprovare");
        }
    } else {
        notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
    }
      })
  .catch((error) => {
	  notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
  });
}