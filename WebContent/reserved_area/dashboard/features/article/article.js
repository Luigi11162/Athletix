/*function onload_elements(){
  //per limitare le parole scritte nel div della descrizione e del nome che sono assai
  myDiv.text(myDiv.text().substring(0,300))
}*/

//modal
function open_update_modal_article(value){
  if(value == -1)
    default_value_update_modal_article();
  else
  modal_update_article_request(value);

  if(document.querySelector("#page_container").classList.contains("active"))
    document.querySelector(".modal-update-article").style.marginLeft = "-280px";
  else
    document.querySelector(".modal-update-article").style.marginLeft = "0px";
  document.querySelector(".modal-update-article").style.display = "flex";
}

function default_value_update_modal_article(){
    document.querySelector(".js--image-preview").style.backgroundImage = "";
    document.querySelector(".modal-update-article__name-field").value = "";
    document.querySelector(".modal-update-article__description-textarea").value = "";
    document.querySelector(".modal-update-article__price-field").value = 0;
    document.querySelector(".modal-update-article__brand-field").value = "";
    document.querySelector(".modal-update-article__quantity-field").value = 0;
    document.querySelector(".modal-update-article__material-field").value = "";
    document.querySelector(".modal-update-article__iva-field").value = 22;
    
    document.querySelector(".modal-update-article__save-button").onclick = () =>{ update_article(-1); }

    let checkbox_category = document.querySelectorAll(".modal-update-article__category-checkbox-div-element-checkbox");
    for(let i=0; i < checkbox_category.length; i++)
    {
      checkbox_category[i].checked = false;
    }

    let checkbox_size = document.querySelectorAll(".modal-update-article__size-checkbox-div-element-checkbox");
    let input_size = document.querySelectorAll(".modal-update-article__size-checkbox-div-element-field");
    for(let i=0; i < checkbox_size.length; i++)
    {
      checkbox_size[i].checked = false;
      input_size[i].value = "0";
      input_size[i].disabled = true;
    }
}

function modal_update_article_request(value){
	  fetch('/Athletix/RichiestaArticolo', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json',
	    },
	    body: JSON.stringify({
	        api: 'get_date_article_element',
	        id: value
	    }),
	  })
	  //.then(response => response.text())
	  .then(res => {
		  default_value_update_modal_article();
		  json_value_update_modal_article(res.headers.get('json_article'),value);
	      })
	  .catch((error) => {
	      console.error('Error:', error);
	  });
}
  
function json_value_update_modal_article(json,value){
  
  /*let json = JSON.parse(` {
                            "img" : "url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAIAAADTED8xAAADMElEQVR4nOzVwQnAIBQFQYXff81RUkQCOyDj1YOPnbXWPmeTRef+/3O/OyBjzh3CD95BfqICMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMK0CMO0TAAD//2Anhf4QtqobAAAAAElFTkSuQmCC')",
                            "name" : "Area di caduta Salto con l'Asta completa di materassina anti chiodi",
                            "description" : "Area di caduta salto in con l'asta, composta da materassi di ammortizzazione in schiuma poliuretanica espansa(PU), con densitÃƒÂ  21kg/mÃ‚Â³ dello spessore di 70 cm (+10 cm di materassina anti-chiodi), e rivestiti in PVC antistrappo, con peso 650gr/mÃ‚Â² ",
                            "price" : "8900.00",
                            "brand" : "Athletix",
                            "quantity" : "50",
                            "material" : "PVC 650gr/mÃ‚Â² Ignifugo Classe 2",
                            "iva" : "22",
                            "unity" : "2",
                            "category" : [1,2,3],
                            "size" : [{
                                        "id" : "1",
                                        "quantity" : "34"
                                      },
                                      {
                                        "id" : "4",
                                        "quantity" : "59"
                                      }
                                    ]
                          }`);*/
  json = JSON.parse(json);

  if(json.img != undefined && json.img != "")
  {
	  document.querySelector(".js--image-preview").style.backgroundImage = "url('../../img/"+ json.img +".jpg')";
  }
	  
  document.querySelector(".modal-update-article__name-field").value = json.name;
  document.querySelector(".modal-update-article__description-textarea").value = json.description;
  document.querySelector(".modal-update-article__price-field").value = json.price;
  document.querySelector(".modal-update-article__brand-field").value = json.brand;
  document.querySelector(".modal-update-article__quantity-field").value = json.quantity;
  document.querySelector(".modal-update-article__material-field").value = json.material;
  document.querySelector(".modal-update-article__iva-field").value = json.iva;

  document.querySelector(".modal-update-article__save-button").onclick = () =>{ update_article(value); }

  document.querySelector("#unity_option_"+ json.unity).selected = 'selected';
  let checkbox_category = json.category;
  for(let i=0; i < checkbox_category.length; i++)
  {
    document.getElementById("checkbox_category_"+ checkbox_category[i]).checked = true;
  }

  let size_element = json.size;
  for(let i=0; i < size_element.length; i++)
  {
    document.getElementById("checkbox_size_"+ size_element[i].id).checked = true;
    document.getElementById("input_size_"+ size_element[i].id).value = size_element[i].quantita;
    document.getElementById("input_size_"+ size_element[i].id).disabled = false;
  }
}

function close_update_modal_article(){
    document.querySelector(".modal-update-article").style.display = "none";
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

function json_send_update_modal_article(){
  let select = document.querySelector(".modal-update-article__unity-select");
  
  let checkbox_category = document.querySelectorAll(".modal-update-article__category-checkbox-div-element-checkbox");
  let category="";
  for(let i=0; i < checkbox_category.length; i++)
  {
    if(checkbox_category[i].checked)
      category += checkbox_category[i].value + ",";
  }

  category = category.slice(0, category.length - 1);

  let checkbox_size = document.querySelectorAll(".modal-update-article__size-checkbox-div-element-checkbox");
  let input_size = document.querySelectorAll(".modal-update-article__size-checkbox-div-element-field");
  let size ="";
  for(let i=0; i < checkbox_size.length; i++)
  {
    
    if(checkbox_size[i].checked)
      size += `{
                "id" : "${checkbox_size[i].value}",
                "quantity" : "${input_size[i].value}"
              },`
  }
  if(size != "{}")
    size = size.slice(0, size.length - 1);

  return ` {
	"img" : '${document.querySelector(".js--image-preview").style.backgroundImage}',
    "name" : "${document.querySelector(".modal-update-article__name-field").value}",
    "description" : "${document.querySelector(".modal-update-article__description-textarea").value}",
    "price" : "${document.querySelector(".modal-update-article__price-field").value}",
    "brand" : "${document.querySelector(".modal-update-article__brand-field").value}",
    "quantity" : "${document.querySelector(".modal-update-article__quantity-field").value}",
    "material" : "${document.querySelector(".modal-update-article__material-field").value}",
    "iva" : "${document.querySelector(".modal-update-article__iva-field").value}",
    "unity" : "${select.options[select.selectedIndex].value}",
    "category" : [${category}],
    "size" : [${size}]
  }`;
}

function update_article(value){
  let json = json_send_update_modal_article();
  fetch('/Athletix/UpdateArticolo', {
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
          close_update_modal_article();
          notification("notification_success","Operazione eseguita con successo");
          redirect("article",true);
          
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
function open_delete_modal_article(value){
    if(document.querySelector("#page_container").classList.contains("active"))
      document.querySelector(".modal-delete-article").style.marginLeft = "-280px";
    else
      document.querySelector(".modal-delete-article").style.marginLeft = "0px";
    document.querySelector(".modal-delete-article").style.display = "flex";

    document.querySelector(".modal-delete-article__button-yes").addEventListener('click', function(){
      delete_article(value);
    });
}

function delete_article(value){
  close_delete_modal_article();

  fetch('/Athletix/CancellaArticolo', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify({
        api: 'delete_article_element',
        id: value
    }),
  })
  //.then(response => response.text())
  .then(res => {
      res = res.headers.get('res');
      if (res != "500" && res != "0" && res != 'session_expired') {
        if (res == "200") {
          notification("notification_success","Cancellazione eseguita con successo");
          redirect("article",true);
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

function close_delete_modal_article(){
    document.querySelector(".modal-delete-article").style.display = "none";
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


  