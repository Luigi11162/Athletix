function redirect_to_article(Aid){

    fetch('/Athletix/Article', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id: Aid
        }),
    })
    .then(res => {
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    redirect("article",true);
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

function onload_card_on_page(value){

    let json_ = json_category_catalog(value); 
	let res;
    fetch('/Athletix/CatalogoCategorie', {     //servlet che manda gli articoli per il caricamento della pagina
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            json: json_
        }),
    })
    .then(response=>{
        res=response.headers.get('res');
    	return response.json();
    })
    .then(response => {	
    		let json_catalog=response.json_catalog;
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    if(document.querySelector(".modal-view-category-catalog").style.display == "flex")
                        document.querySelector(".modal-view-category-catalog").style.display = "none";
                    create_element_on_page(json_catalog);
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

function json_category_catalog(value){

    let checkbox = document.querySelectorAll(".modal-view-category-catalog__checkbox-div-element-checkbox");
    let label = document.querySelectorAll(".modal-view-category-catalog__checkbox-div-element-label");
   
    let json = "{";

    if(value == 1){
        let label_track = label;
        for(let j = 0; j<label_track.length; j++){
            if(label_track[j].innerHTML == "Pista")
                checkbox[j].checked = true;
        }
    }

    if(value == 2){
        let label_track = label;
        for(let j = 0; j<label_track.length; j++){
            if(label_track[j].innerHTML == "Attrezzatura")
                checkbox[j].checked = true;
        }
    }

    if(value == 3){
        let label_track = label;
        for(let j = 0; j<label_track.length; j++){
            if(label_track[j].innerHTML == "Abbigliamento")
                checkbox[j].checked = true;
        }
    }
    
    for(let i = 0; i<checkbox.length; i++){
        if(checkbox[i].checked){
            json += '"'+label[i].innerHTML+'": "si",';
        }
        else
            json += '"'+label[i].innerHTML+'": "no",';
    }

    json += '"field_text":"'+document.querySelector(".container-catalog__filter-text-input-input").value +'"}';

    //json = json.substring(0,json.length-1) + "}";
    return json;
}

function create_element_on_page(json_article){
    document.querySelector(".container-catalog__catalog").innerHTML="";
    let content = "";
    let category;
    let description;
    for(let i=0;i<json_article.length; i++){
        category = "";
        for(let j=0;j<json_article[i].category.length;j++){
            if (j==0)
                category += json_article[i].category[j].nome;
            else
                category += ", "+ json_article[i].category[j].nome;

        }

        if(json_article[i].description.length >= 70 )
            description = json_article[i].description.substring(0,70) + " ...";
        else
            description = json_article[i].description;
        content += `<article class="container-catalog__catalog-article" onclick="javascript:redirect_to_article(${json_article[i].id})">
                        <div class="container-catalog__catalog-article-image">
                            <img class="container-catalog__catalog-article-image-img" src="./img/${json_article[i].id}.jpg">
                        </div>
                        <div class="container-catalog__catalog-article-description">
                            <div class="container-catalog__catalog-article-description-text">
                                ${description}
                            </div>
                            <div class="container-catalog__catalog-article-description-price">
                                ${json_article[i].price}
                            </div>
        					<div class="container-catalog__catalog-article-description-category">
                                ${category}
                            </div>
                        </div>
                    </article>`;
    }

    document.querySelector(".container-catalog__catalog").innerHTML = content;
}

//modal
function onload_modal_category_catalog(){
    fetch('/Athletix/RichiestaCategoria', {     //servlet che genera le categorie nella modale
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    /*.then(response=>{
    	 res=response.headers.get('res');
    	return response.json();
    })*/
    .then(response => {		
    		let json_category_catalog = JSON.parse(response.headers.get('json_category_catalog'));//response.json_category_catalog;
            let res = response.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    create_modal_category_catalog(json_category_catalog);
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

function create_modal_category_catalog(json){
    json = json.json_category_catalog;
    document.querySelector(".modal-view-category-catalog__checkbox-div").innerHTML = "";
    let content = "";

    for(let i=0; i<json.length; i++){
        content += `<div class="modal-view-category-catalog__checkbox-div-element">
                        <input type="checkbox" id="checkbox_category_${json[i].id}" value="${json[i].id}" class="modal-view-category-catalog__checkbox-div-element-checkbox">
                        <label class="modal-view-category-catalog__checkbox-div-element-label">${json[i].name}</label>
                    </div>`;
    }

    document.querySelector(".modal-view-category-catalog__checkbox-div").innerHTML = content;
}

function open_modal_category_catalog(){
    document.querySelector(".modal-view-category-catalog").style.display = "flex";
}

function close_modal_category_catalog(){
    document.querySelector(".modal-view-category-catalog").style.display = "none";
}