function event_date_click(){
  let j=0;
  
   j = event_date(j);
   
  if(j==0)
      document.querySelector(".container__table-body-tr-no-element").style.display = "table-row";
  else
      document.querySelector(".container__table-body-tr-no-element").style.display = "none";
  
}

function event_date(j){
  let date_start = document.querySelector(".container-order-history__filter-date-start-input").value;
  let date_finish = document.querySelector(".container-order-history__filter-date-finish-input").value;
  let date_elements = document.querySelectorAll(".container-order-history__table-date-td");
  
  //variable of date finish
  let year_finish = date_finish.substring(0,4);
  let month_finish = date_finish.substring(5,7);
  let day_finish = date_finish.substring(8,10);

  //variable of date start
  let year_start = date_start.substring(0,4);
  let month_start = date_start.substring(5,7);
  let day_start = date_start.substring(8,10);

  //variable of single row
  let day;
  let month;
  let year;
  
  if(date_start.length==0 || date_finish.length==0){
	  if(date_start.length!=0){
		  for(let i = 0; i < date_elements.length; i++){
			  year = date_elements[i].innerHTML.substring(6, 10);
			  month = date_elements[i].innerHTML.substring(3, 5);
			  day = date_elements[i].innerHTML.substring(0, 2);
			  
			  if(parseInt(year) > parseInt(year_start)){
			      date_elements[i].parentNode.style.display = "table-row";
			      j += 1;
			  }else{
				  if(parseInt(year) == parseInt(year_start)){
			          if(parseInt(month) >= parseInt(month_start)){
			            if(parseInt(month) == parseInt(month_start)){
			              if(parseInt(day) >= parseInt(day_start)){
			                date_elements[i].parentNode.style.display = "table-row";
			                j += 1;
			              }
			              else
			            	  date_elements[i].parentNode.style.display = "none";
			            }
			            else
			            {
			              date_elements[i].parentNode.style.display = "table-row";
			              j += 1;
			            }
			          }
			          else
			            date_elements[i].parentNode.style.display = "none";
				  	}else
				  		date_elements[i].parentNode.style.display = "none";
			      }
		  }
	  }else if(date_finish.length!=0){
		  for(let i = 0; i < date_elements.length; i++){
			  year = date_elements[i].innerHTML.substring(6, 10);
			  month = date_elements[i].innerHTML.substring(3, 5);
			  day = date_elements[i].innerHTML.substring(0, 2);
			  
			  if(parseInt(year) < parseInt(year_finish)){
			      date_elements[i].parentNode.style.display = "table-row";
			      j += 1;
			  }
			  else{
				  if(parseInt(year) == parseInt(year_finish)){
			          if(parseInt(month) <= parseInt(month_finish)){
			            if(parseInt(month) == parseInt(month_finish)){
			              if(parseInt(day) <= parseInt(day_finish)){
			                date_elements[i].parentNode.style.display = "table-row";
			                j += 1;
			              }
			              else
			            	  date_elements[i].parentNode.style.display = "none";
			            }
			            else
			            {
			              date_elements[i].parentNode.style.display = "table-row";
			              j += 1;
			            }
			          }
			          else
			            date_elements[i].parentNode.style.display = "none";
			        }else
				  		date_elements[i].parentNode.style.display = "none";
			      }
		  }
	  }else{
		  for(let i = 0; i < date_elements.length; i++){
			  date_elements[i].parentNode.style.display = "table-row";
              j += 1;
		  }
	  }
  }else{
  
	  for(let i = 0; i < date_elements.length; i++){
	
	    year = date_elements[i].innerHTML.substring(6, 10);
	    month = date_elements[i].innerHTML.substring(3, 5);
	    day = date_elements[i].innerHTML.substring(0, 2);
	
	    if(parseInt(year) > parseInt(year_start) && parseInt(year) < parseInt(year_finish)){
	      date_elements[i].parentNode.style.display = "table-row";
	      j += 1;
	    }
	    else{
	      if(parseInt(year) == parseInt(year_start) || parseInt(year) == parseInt(year_finish)){
	        if(parseInt(year) == parseInt(year_start)){
	          if(parseInt(month) >= parseInt(month_start)){
	            if(parseInt(month) == parseInt(month_start)){
	              if(parseInt(day) >= parseInt(day_start)){
	                date_elements[i].parentNode.style.display = "table-row";
	                j += 1;
	              }
	              else
	            	  date_elements[i].parentNode.style.display = "none";
	            }
	            else
	            {
	              date_elements[i].parentNode.style.display = "table-row";
	              j += 1;
	            }
	          }
	          else
	            date_elements[i].parentNode.style.display = "none";
	        }
	        else
	        if(parseInt(year) == parseInt(year_finish)){
	          if(parseInt(month) <= parseInt(month_finish)){
	            if(parseInt(month) == parseInt(month_finish)){
	              if(parseInt(day) <= parseInt(day_finish)){
	                date_elements[i].parentNode.style.display = "table-row";
	                j += 1;
	              }
	              else
	            	  date_elements[i].parentNode.style.display = "none";
	            }
	            else
	            {
	              date_elements[i].parentNode.style.display = "table-row";
	              j += 1;
	            }
	          }
	          else
	            date_elements[i].parentNode.style.display = "none";
	        }
	      }
	      else
	      {
	        date_elements[i].parentNode.style.display = "none";
	      }
	    }
	  }
  }
  return j;
}

//modal view
function open_view_modal_order_history(value){
  modal_view_order_history_request(value);

  if(document.querySelector("#page_container").classList.contains("active"))
    document.querySelector(".modal-view-order-history").style.marginLeft = "-280px";
  else
    document.querySelector(".modal-view-order-history").style.marginLeft = "0px";
  document.querySelector(".modal-view-order-history").style.display = "flex";
}

function default_value_view_modal_order_history(){
  document.querySelector(".table_body_line").innerHTML = "";   
}

function modal_view_order_history_request(value){
	  fetch('/Athletix/RichiestaOrdineRigo', {   //devi mettere la servlet che richiama gli ordini righi di quell'ordine testata
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/json',
	    },
	    body: JSON.stringify({
	        api: 'get_date_order_history_element',
	        id: value
	    }),
	  })
	  //.then(response => response.text())
	  .then(res => {
		  default_value_view_modal_order_history();
		  json_value_view_modal_order_history(res.headers.get('json_order_history_line'),value);
	      })
	  .catch((error) => {
	      console.error('Error:', error);
	  });
}
  
function json_value_view_modal_order_history(json,value){
  json = JSON.parse(json);
  let table = json.table;
  let table_component = "";

  for(let i=0; i< table.length; i++){
    table_component += `
                        <tr>
                            <td class="modal-view-order-history__table-body-td modal-view-order-history__table-article-td">${table[i].article}</td>
                            <td class="modal-view-order-history__table-body-td modal-view-order-history__table-price-td">${table[i].price}</td>
                            <td class="modal-view-order-history__table-body-td modal-view-order-history__table-quantity-td">${table[i].quantity}</td>
                            <td class="modal-view-order-history__table-body-td modal-view-order-history__table-iva-td">${table[i].iva}</td>
                        </tr>
                        `;
  }
  
  table_component += `
                      <tr class="modal-view-order-history__table-body-tr-no-element">
                        <td class="modal-view-order-history__table-body-td " colspan=8>
                            <div class="modal-view-order-history__table-body-td-no-element">
                                <p>NON CI SONO ELEMENTI DA MOSTRARE NELLA TABELLA</p> 
                                <img class="modal-view-order-history__table-body-td-icon-img" src="./assets/img/general_icon/sad_emoji.png" >
                            </div>
                        </td>
                      </tr>
                      `;

  document.querySelector(".table_body_line").innerHTML = table_component;

}

function close_view_modal_order_history(){
    document.querySelector(".modal-view-order-history").style.display = "none";
}