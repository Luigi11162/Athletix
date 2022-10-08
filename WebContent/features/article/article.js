let global_size;
let maxQuantity;

function cancel_textarea_article_content(){
    document.querySelector(".container-article-comment-container__actually-comment-textarea").value = "";
}

function insert_review_article(){
    let text_review = document.querySelector(".container-article-comment-container__actually-comment-textarea").value;

    if(text_review == "")
        notification("notification_danger","Inserire la recensione");
    else{
        fetch('/Athletix/UpdateRecensione', { //inserire la servlet che si occupa di registrare la recensione e che deve restituire un codice
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                //bisognerebbe aggiungere l'id della persona preso dalla sessione e in più da backend prendere la data del giorno in cui viene mandata la recensione
                text: text_review
            }),
        })
        .then(res => {
                res = res.headers.get('res');
                if (res != "500" && res != "0" && res != 'session_expired') {
                    if (res == "200") {
                        cancel_textarea_article_content();
                        notification("notification_success","Recensione registrata con successo");
                        download_review_article();
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

function download_review_article(){
	
    fetch('/Athletix/RichiestaRecensione', {
    	 method: 'POST',
         headers: {
             'Content-Type': 'application/json',
         },
         /*body: JSON.stringify({
            id_article: value;
         }),*/
     })
    .then(res => {
            let json = JSON.parse(res.headers.get('json_review_article'));
            fill_review_article(json.old_comment);
        })
    .catch((error) => {
        console.error('Error:', error);
    });

    /*
        //PER TESTARE
    let json = JSON.parse(`{
                    "old_comment" : [
                                        {
                                            "name" : "Abdul",
                                            "date" : "11/03/2021",
                                            "review" : "Io consigliare te prodotto, in mio paese fare tutti ciccio panza su questo, piacere piacere assai. Piacere sopprrattttuto fare foki foki. Is very good, we love you and your big ass. MLMLMLMLMLMLMLML XDXDXDXDXDDXDXD"
                                        },
                                        {
                                            "name" : "Emanuele",
                                            "date" : "01/02/1990",
                                            "review" : "uffa"
                                        },
                                        {
                                            "name" : "Marisa",
                                            "date" : "01/02/0000",
                                            "review" : "GERARDO NON DORMIRE"
                                        }
                                    ]
                }`);
    
    fill_review_article(json.old_comment);*/
}

function fill_review_article(json){
    let container_content = '';//document.querySelector(".container-article-comment-container__actually-comment");
    for(let i=0; i < json.length; i++)
    {
        container_content += `<div class="container-article-comment-container__old-comment">
                                <div class="container-article-comment-container__old-comment-image"><img class="container-article-comment-container__old-comment-image-img" src="./img/index_img/user_registered.png"></div>

                                <div class="container-article-comment-container__old-comment-name-time">
                                    <div class="container-article-comment-container__old-comment-name">${json[i].name}</div>
                                    <div class="container-article-comment-container__old-comment-time">${json[i].date}</div>
                                </div>
                                <textarea class="container-article-comment-container__old-comment-textarea" disabled> ${json[i].review}</textarea>
                                
                            </div>`;
    }
    
    document.querySelector(".container-article-comment-container__old-comment-container").innerHTML = container_content;
}

function add_article_to_cart(){   
	quantity=document.querySelector(".container-article-container__text-container-quantity-input").value;

    if (quantity>maxQuantity)
    	quantity=maxQuantity;
    else if(quantity<0)
    		quantity=0;
    if (global_size=='')
    	global_size=-1

    fetch('/Athletix/Carrello', {                   //inserire la servlet che si occupa di aggiungere al carrello il corrente articolo
    method: 'POST',                                 //NOTA BENE!! io non ti devo passare niente dell'articolo se non la taglia scelta dal cliente
        headers: {                                  //la devi prendere dall'attributo della sessione da cui io prendo l'articolo per caricalo in questa pagina
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
        	id:-1,
            quantity: quantity,
        	size: global_size
        }),
    })
    .then(res => {
        res = res.headers.get('res');
        if (res != "500" && res != "0" && res != 'session_expired') {
          if (res == "200") {
            notification("notification_success","Articolo aggiunto al carrello");
            shopping_cart_index();
            query_popup_cart();
            
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

function selected_size_button(element,size){
	global_size=size;
    let size_button = document.querySelectorAll(".container-article-container__text-container-size-button")

    for(let i=0; i< size_button.length; i++)
        if(size_button[i].classList.contains("active")){
            size_button[i].classList.remove('active');
        }

        element.classList.add('active'); 
}

function pop_article_quantity(){
    let value = parseInt(document.querySelector(".container-article-container__text-container-quantity-input").value);
    if(value-1 < 1)
        document.querySelector(".container-article-container__text-container-quantity-input").value=1;
    else
        document.querySelector(".container-article-container__text-container-quantity-input").value=value - 1;
}

function push_article_quantity(Avalue){
    let value = parseInt(document.querySelector(".container-article-container__text-container-quantity-input").value);
    if(value-1 < 0)
        document.querySelector(".container-article-container__text-container-quantity-input").value=0;
    else
        document.querySelector(".container-article-container__text-container-quantity-input").value=value + 1;

    fetch('/Athletix/RichiestaPezziMax', { //mandare il numero massimo di pezzi disponibili per quell'articolo
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            //bisognerebbe aggiungere l'id della persona preso dalla sessione e in più da backend prendere la data del giorno in cui viene mandata la recensione
            id: Avalue
        }),
    })
    .then(res => {
            value_article = res.headers.get('value_article');
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    if(value_article < (value + 1)){
                        document.querySelector(".container-article-container__text-container-quantity-input").value=value_article;
                        notification("notification_danger","Hai raggiunto la disponibilità massima presente nel magazzino");
                    }
                    else
                        document.querySelector(".container-article-container__text-container-quantity-input").value=value + 1;
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

function zoom(e){
    var zoomer = e.currentTarget;
    e.offsetX ? offsetX = e.offsetX : offsetX = e.touches[0].pageX
    e.offsetY ? offsetY = e.offsetY : offsetX = e.touches[0].pageX
    x = offsetX/zoomer.offsetWidth*100
    y = offsetY/zoomer.offsetHeight*100
    zoomer.style.backgroundPosition = x + '% ' + y + '%';
}
