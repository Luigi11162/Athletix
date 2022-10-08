/*function onload_elements(){
  //per limitare le parole scritte nel div della descrizione e del nome che sono assai
  myDiv.text(myDiv.text().substring(0,300))
}*/

//modal
function open_update_modal_unity(value){
  if(value == -1)
    default_value_update_modal_unity();
  else
    modal_update_unity_request(value);

  if(document.querySelector("#page_container").classList.contains("active"))
    document.querySelector(".modal-update-unity").style.marginLeft = "-280px";
  else
    document.querySelector(".modal-update-unity").style.marginLeft = "0px";
  document.querySelector(".modal-update-unity").style.display = "flex";
}

function default_value_update_modal_unity(){
    document.querySelector(".modal-update-unity__name-field").value = "";
    document.querySelector(".modal-update-unity__quantity-field").value = 0;
    
    document.querySelector(".modal-update-unity__save-button").onclick = () =>{ update_unity(-1); }
}

function modal_update_unity_request(value){
	  fetch('/Athletix/RichiestaUnita', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json',
	    },
	    body: JSON.stringify({
	        api: 'get_date_unity_element',
	        id: value
	    }),
	  })
	  //.then(response => response.text())
	  .then(res => {
		  default_value_update_modal_unity();
		  json_value_update_modal_unity(res.headers.get('json_unity'),value);
	      })
	  .catch((error) => {
	      console.error('Error:', error);
	  });
}
  
function json_value_update_modal_unity(json,value){
  json = JSON.parse(json);
  console.log(json)
	  
  document.querySelector(".modal-update-unity__name-field").value = json.name;
  document.querySelector(".modal-update-unity__quantity-field").value = json.quantity;

  document.querySelector(".modal-update-unity__save-button").onclick = () =>{ update_unity(value); }
}

function close_update_modal_unity(){
    document.querySelector(".modal-update-unity").style.display = "none";
}

function change_status(checkbox){
  if(checkbox.checked){
    checkbox.parentElement.lastElementChild.disabled = false;
    checkbox.parentElement.lastElementChild.value = 1;
  }
  else
  {
    checkbox.parentElement.lastElementChild.disabled = true;
    checkbox.parentElement.lastElementChild.value = 0;
  }
}

function json_send_update_modal_unity(){
  return ` {
    "name" : "${document.querySelector(".modal-update-unity__name-field").value}",
    "quantity" : "${document.querySelector(".modal-update-unity__quantity-field").value}"
  }`;
}

function update_unity(value){
  let json = json_send_update_modal_unity();
  fetch('/Athletix/UpdateUnita', {
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
          close_update_modal_unity();
          notification("notification_success","Operazione eseguita con successo");
          redirect("unity",true);
          
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
function open_delete_modal_unity(value){
    if(document.querySelector("#page_container").classList.contains("active"))
      document.querySelector(".modal-delete-unity").style.marginLeft = "-280px";
    else
      document.querySelector(".modal-delete-unity").style.marginLeft = "0px";
    document.querySelector(".modal-delete-unity").style.display = "flex";

    document.querySelector(".modal-delete-unity__button-yes").addEventListener('click', function(){
      delete_unity(value);
    });
}

function delete_unity(value){
  close_delete_modal_unity();

  fetch('/Athletix/RemoveUnita', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        api: 'delete_unity_element',
        id: value
    }),
  })
  //.then(response => response.text())
  .then(res => {
      res = res.headers.get('res');
      if (res != "500" && res != "0" && res != 'session_expired') {
        if (res == "200") {
          notification("notification_success","Cancellazione eseguita con successo");
          redirect("unity",true);
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

function close_delete_modal_unity(){
    document.querySelector(".modal-delete-unity").style.display = "none";
}

function initImageUpload(box) {
    let uploadField = box.querySelector('.image-upload');
  
    uploadField.addEventListener('change', getFile);
  
    function getFile(e){
      let file = e.currentTarget.files[0];
      checkType(file);
    }
    
    function previewImage(file){
    
      let thumb = box.querySelector('.js--image-preview'),
          reader = new FileReader();
  
      reader.onload = function() {
        thumb.style.backgroundImage = 'url(' + reader.result + ')';
      }
      reader.readAsDataURL(file);
      thumb.className += ' js--no-default';
    }
  
    function checkType(file){
      let imageType = /image.*/;
      if (!file.type.match(imageType)) {
        throw 'Datei ist kein Bild';
      } else if (!file){
        throw 'Kein Bild gewählt';
      } else {
        previewImage(file);
      }
    }
}

function initDropEffect(box){
    let area, drop, areaWidth, areaHeight, maxDistance, dropWidth, dropHeight, x, y;
    
    // get clickable area for drop effect
    area = box.querySelector('.js--image-preview');
    area.addEventListener('click', fireRipple);
    
    function fireRipple(e){
      area = e.currentTarget
      // create drop
      if(!drop){
        drop = document.createElement('span');
        drop.className = 'drop';
        this.appendChild(drop);
      }
      // reset animate class
      drop.className = 'drop';
      
      // calculate dimensions of area (longest side)
      areaWidth = getComputedStyle(this, null).getPropertyValue("width");
      areaHeight = getComputedStyle(this, null).getPropertyValue("height");
      maxDistance = Math.max(parseInt(areaWidth, 10), parseInt(areaHeight, 10));
  
      // set drop dimensions to fill area
      drop.style.width = maxDistance + 'px';
      drop.style.height = maxDistance + 'px';
      
      // calculate dimensions of drop
      dropWidth = getComputedStyle(this, null).getPropertyValue("width");
      dropHeight = getComputedStyle(this, null).getPropertyValue("height");
      
      // calculate relative coordinates of click
      // logic: click coordinates relative to page - parent's position relative to page - half of self height/width to make it controllable from the center
      x = e.pageX - this.offsetLeft - (parseInt(dropWidth, 10)/2);
      y = e.pageY - this.offsetTop - (parseInt(dropHeight, 10)/2) - 30;
      
      // position drop and animate
      drop.style.top = y + 'px';
      drop.style.left = x + 'px';
      drop.className += ' animate';
      e.stopPropagation();
      
    }
}


  