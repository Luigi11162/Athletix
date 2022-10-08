/*function onload_elements(){
  //per limitare le parole scritte nel div della descrizione e del nome che sono assai
  myDiv.text(myDiv.text().substring(0,300))
}*/

//modal
function open_update_modal_category(value){
  if(value == -1)
    default_value_update_modal_category();
  else
    modal_update_category_request(value);

  if(document.querySelector("#page_container").classList.contains("active"))
    document.querySelector(".modal-update-category").style.marginLeft = "-280px";
  else
    document.querySelector(".modal-update-category").style.marginLeft = "0px";
  document.querySelector(".modal-update-category").style.display = "flex";
}

function default_value_update_modal_category(){
    document.querySelector(".modal-update-category__category-field").value = "";
    
    document.querySelector(".modal-update-category__save-button").onclick = () =>{ update_category(-1); }
}

function modal_update_category_request(value){
	  fetch('/Athletix/RichiestaCategoria', {   //richiesta articolo per la modale
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json',
	    },
	    body: JSON.stringify({
	        api: 'get_date_category_element',
	        id: value
	    }),
	  })
	  //.then(response => response.text())
	  .then(res => {
		  default_value_update_modal_category();
		  json_value_update_modal_category(res.headers.get('json_category'),value);
	      })
	  .catch((error) => {
	      console.error('Error:', error);
	  });
}
  
function json_value_update_modal_category(json,value){
  json = JSON.parse(json);
	  
  document.querySelector(".modal-update-category__category-field").value = json.category;

  document.querySelector(".modal-update-category__save-button").onclick = () =>{ update_category(value); }
}

function close_update_modal_category(){
    document.querySelector(".modal-update-category").style.display = "none";
}

function json_send_update_modal_category(){
  return ` {
    "category" : "${document.querySelector(".modal-update-category__category-field").value}"
  }`;
}

function update_category(value){
  let json = json_send_update_modal_category();
  fetch('/Athletix/UpdateCategoria', {  //modifica categoria
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
          close_update_modal_category();
          notification("notification_success","Operazione eseguita con successo");
          redirect("category",true);
         
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
function open_delete_modal_category(value){
    if(document.querySelector("#page_container").classList.contains("active"))
      document.querySelector(".modal-delete-category").style.marginLeft = "-280px";
    else
      document.querySelector(".modal-delete-category").style.marginLeft = "0px";
    document.querySelector(".modal-delete-category").style.display = "flex";

    document.querySelector(".modal-delete-category__button-yes").addEventListener('click', function(){
      delete_category(value);
    });
}

function delete_category(value){
  close_delete_modal_category();

  fetch('/Athletix/RemoveCategoria', { //cancellazione categoria
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        api: 'delete_category_element',
        id: value
    }),
  })
  //.then(response => response.text())
  .then(res => {
      res = res.headers.get('res');
      if (res != "500" && res != "0" && res != 'session_expired') {
        if (res == "200") {
          notification("notification_success","Cancellazione eseguita con successo");
          redirect("category",true);
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

function close_delete_modal_category(){
    document.querySelector(".modal-delete-category").style.display = "none";
}  