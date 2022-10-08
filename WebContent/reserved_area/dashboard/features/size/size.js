/*function onload_elements(){
  //per limitare le parole scritte nel div della descrizione e del nome che sono assai
  myDiv.text(myDiv.text().substring(0,300))
}*/

//modal
function open_update_modal_size(value){
  if(value == -1)
    default_value_update_modal_size();
  else
    modal_update_size_request(value);

  if(document.querySelector("#page_container").classList.contains("active"))
    document.querySelector(".modal-update-size").style.marginLeft = "-280px";
  else
    document.querySelector(".modal-update-size").style.marginLeft = "0px";
  document.querySelector(".modal-update-size").style.display = "flex";
}

function default_value_update_modal_size(){
    document.querySelector(".modal-update-size__size-field").value = "";
    
    document.querySelector(".modal-update-size__save-button").onclick = () =>{ update_size(-1); }
}

function modal_update_size_request(value){
	  fetch('/Athletix/RichiestaTaglia', {   //richiesta articolo per la modale
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json',
	    },
	    body: JSON.stringify({
	        api: 'get_date_size_element',
	        id: value
	    }),
	  })
	  //.then(response => response.text())
	  .then(res => {
		  default_value_update_modal_size();
		  json_value_update_modal_size(res.headers.get('json_size'),value);
	      })
	  .catch((error) => {
	      console.error('Error:', error);
	  });
}
  
function json_value_update_modal_size(json,value){
  json = JSON.parse(json);
	  
  document.querySelector(".modal-update-size__size-field").value = json.size;

  document.querySelector(".modal-update-size__save-button").onclick = () =>{ update_size(value); }
}

function close_update_modal_size(){
    document.querySelector(".modal-update-size").style.display = "none";
}

function json_send_update_modal_size(){
  return ` {
    "size" : "${document.querySelector(".modal-update-size__size-field").value}"
  }`;
}

function update_size(value){
  let json = json_send_update_modal_size();
  fetch('/Athletix/UpdateTaglia', {  //modifica taglia
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
          close_update_modal_size();
          notification("notification_success","Operazione eseguita con successo");
          redirect("size",true);
          
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



//delete_modal
function open_delete_modal_size(value){
    if(document.querySelector("#page_container").classList.contains("active"))
      document.querySelector(".modal-delete-size").style.marginLeft = "-280px";
    else
      document.querySelector(".modal-delete-size").style.marginLeft = "0px";
    document.querySelector(".modal-delete-size").style.display = "flex";

    document.querySelector(".modal-delete-size__button-yes").addEventListener('click', function(){
      delete_size(value);
    });
}

function delete_size(value){
  close_delete_modal_size();

  fetch('/Athletix/RemoveTaglia', { //cancellazione taglia
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        api: 'delete_size_element',
        id: value
    }),
  })
  //.then(response => response.text())
  .then(res => {
      res = res.headers.get('res');
      if (res != "500" && res != "0" && res != 'session_expired') {
        if (res == "200") {
          notification("notification_success","Cancellazione eseguita con successo");
          redirect("size",true);
        } else if (res == "403") {
            notification("notification_danger","Cancellazione non avvenuta, riprovare");
        }
    } else {
        notification("notification_danger","Si è verificato un problema interno, riprova più tardi");
    }
      })
  .catch((error) => {
      console.error('Error:', error);
  });
}

function close_delete_modal_size(){
    document.querySelector(".modal-delete-size").style.display = "none";
}  