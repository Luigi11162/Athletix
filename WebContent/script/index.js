let mobile_detector;
let size_screen;
let size_screen_history;

function caricamento(){
    document.querySelector(".dropdown-menu").style.display = "none";
    mobile_detector = new MobileDetect(window.navigator.userAgent);

    hammertime = new Hammer(document.getElementById('sidebar'));
    hammertime.get('swipe').set({ direction: Hammer.DIRECTION_HORIZONTAL });
    hammertime.on('swipe', function (ev) {
        if (document.getElementById('sidebar').classList.contains('active')) {
            close_menu();
        }
    });

    if(document.body.offsetWidth >= 770)
        size_screen_history = 1;
    else
        size_screen_history = 2;

    popup_cart();
    shopping_cart_index();
    url = window.location.href;
    if(url.includes("#"))/*{
        console.log(url.substring(url.indexOf('#') + 1, url.indexOf('#') + 8))
        if(url.substring(url.indexOf('#') + 1, url.indexOf('#') + 8) == "article"){
            let url_article = url.substring(url.indexOf('#') + 1)
        }
        else
            redirect(url.substring(url.indexOf('#') + 1), false);
    } */
        redirect(url.substring(url.indexOf('#') + 1), false);
    else
        redirect('catalog', true);
}

function redirect(a_page, a_has_to_pushstate) {
    const page_container = document.querySelector("#page_container");

    fetch(`./features/${a_page}/${a_page}.jsp`)
    .then(response => response.text())
    .then(res => {
        if(a_has_to_pushstate){
            history.pushState({pagina : a_page}, `${a_page}` , `./index.jsp#${a_page}`);
        }
        
        if(document.querySelector("#sidebar").classList.contains("active"))
            close_menu();
        
        if(document.querySelector(".cart-div").classList.contains("active"))
            close_cart();

        if(document.querySelector(".dropdown-menu").style.display == "block")
            document.querySelector(".dropdown-menu").style.display = "none";
        
        page_container.innerHTML = res + footer();
        window[a_page]();
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function footer(){
    return `
                <div class="footer">
                    <div class="footer__section">
                        <div class="footer__service">
                            <h3 class="footer__title">Servizio offerto da</h3>
                            <img src="./img/index_img/logo.png" class="footer__image-service">
                        </div>

                        <div class="footer__invert-div">
                            <div class="footer__contact">
                                <h3 class="footer__title">Contatti</h3>
                                
                                <div class="footer__number-contact">
                                    <div class="footer__titol-contact">
                                        <!--<img class="footer__titol-contact-img" src="./assets/img/phone_footer.png">-->
                                        <p>Telefono</p>
                                    </div>
                                    <div class="footer__text-contact">
                                        <p class="footer__text-contact-p">Francesco 3333333333</p>
                                        <p class="footer__text-contact-p">Luigi 3333333333</p>
                                    </div>
                                </div>

                                <div class="footer__mail-contact">
                                    <div class="footer__titol-contact">
                                        <!--<img class="footer__titol-contact-img" src="./assets/img/mail_footer.png">-->
                                        <p>E-mail</p>
                                    </div>
                                    <div class="footer__text-contact">
                                        <p class="footer__text-contact-p">info@atletix.it</p>
                                    </div>
                                </div>
                                
                            </div>

                            <div class="footer__social">
                                <h3 class="footer__title">Social</h3>
                                <div class="footer__link-container">

                                    <div class="footer__name">Athletix</div>

                                    <div class="footer__link">
                                        <a href=""><img src="./img/index_img/instagram.png" class="footer__image-link"></a>
                                        <a href=""><img src="./img/index_img/facebook.png" class="footer__image-link"></a>
                                    </div>
                                </div>

                                <!--<div class="footer__link-container">

                                    <div class="footer__name">DARP Studio</div>

                                    <div class="footer__link">
                                        <a><img src="./assets/img/instagram.png" class="footer__image-link"></a>
                                        <a><img src="./assets/img/facebook.png" class="footer__image-link"></a>
                                        <a class="footer__image-link-web-a"><img src="./assets/img/website.png" class="footer__image-link-web"></a>
                                    </div>
                                </div>-->

                            </div>    
                        </div>
                        
                    </div>
                    
                    <hr>

                    <div class="footer__text-div">
                        <div class="footer__text"> Athletix</div>
                        <a class="footer__text-a" href="./cookie/cookie.html" target="_blank"> Privacy Policy </a>
                        <a class="footer__text-a" href="./reserved_area/authentication/login-form.jsp" target="_blank"> Area Riservata </a>
                    </div>
                </div>
            `;
}

function catalog(){
    onload_modal_category_catalog();
    onload_card_on_page(-1);
}

function article(){
    download_review_article();
    global_size = "";
}

function user_reserved_area(){
    onload_user_reserved_area();
}

function shopping_cart(){
    
}

function orders(){
    
}

function redirect_to_catalog_with_fields(value){
    redirect('catalog', true);
    setTimeout(function (){
        onload_card_on_page(value);
    }, 100);
}

function popup_cart(){
    if(parseInt(document.querySelector(".header__selection-button-cart-number").innerHTML) == 0)
        document.querySelector(".header__selection-button-cart-number").style.display = "none";
    else
        document.querySelector(".header__selection-button-cart-number").style.display = "block";
}

function query_popup_cart(){
    fetch("/Athletix/RichiestaQuantitaCarrello", {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(res => {
        value=res.headers.get("value_popup");
    	res=res.headers.get("res");
        if (res=="200") { 
            document.querySelector(".header__selection-button-cart-number").innerHTML = value;
            popup_cart();
        }
    }).catch(error => console.log("Si è verificato un errore!"));
}

window.addEventListener('popstate', e => {
    if(e.state != null)
        redirect(e.state.pagina, false);
});

function open_sidenav() {
    document.querySelector("#sidebar").classList.add('active');
}

function close_menu() {
    document.querySelector("#sidebar").classList.remove('active');
}

//cart
function open_cart() {
    document.querySelector(".cart-div").classList.add('active');
}

function close_cart() {
    document.querySelector(".cart-div").classList.remove('active');
}

window.addEventListener('resize', function(event){
    
    if(document.body.offsetWidth >= 770)
        size_screen = 1;
    else
        size_screen = 2;

    if(size_screen != size_screen_history)
        if(size_screen == 1){
            if(this.document.querySelector("#sidebar").classList.contains("active"))
                close_menu();
            size_screen_history = 1;
        }
        else{
            if(this.document.querySelector(".cart-div").classList.contains("active"))
                close_cart();
            size_screen_history = 2;
        }
    else
        if(size_screen == 1)
            size_screen_history = 1;
        else
            size_screen_history = 2;
});

function show_dropdown_menu(){
    if(document.querySelector(".dropdown-menu").style.display == "none")
        document.querySelector(".dropdown-menu").style.display = "block";
    else
        document.querySelector(".dropdown-menu").style.display = "none";
}

function go_to_login_from_index(){
    window.location.href = "./features/login/login.jsp";
}

function go_to_registration_from_index(){
    window.location.href = "./features/registration/registration.jsp";
}

function logout() {

    fetch("/Athletix/Logout", {
        method: 'POST', 
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            api: 'logout'
        }),
    })
    .then(res => {
    	res=res.headers.get("res");
        if (res=="200") { 
            window.location = "./index.jsp";
        }
    }).catch(error => console.log("Si è verificato un errore!"));
}

function shopping_cart_index(){
    fetch('/Athletix/RichiestaCarrello', {                   //inserire la servlet che si occupa di aggiungere al carrello il corrente articolo
        method: 'POST',                                 //NOTA BENE!! io non ti devo passare niente dell'articolo se non la taglia scelta dal cliente
            headers: {                                  //la devi prendere dall'attributo della sessione da cui io prendo l'articolo per caricalo in questa pagina
                'Content-Type': 'application/json',
            }
        })
        .then(res => {
        	json=JSON.parse(res.headers.get('json_cart'));

            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
              if (res == "200") {
                document.querySelector(".cart-div__content").innerHTML = "";
                let content = "";
                
                for(let i = 0; i< json.json.length; i++){

                    if(i!=0)
                        content += `<hr class="cart-div__content-hr">`;

                    content += `
                                <div class="cart-div__content-element">
                                    <div class="cart-div__content-element-image"><img class="cart-div__content-element-image-img" src="./img/${json.json[i].id}.jpg"></div>
                                    <p class="cart-div__content-element-titol">${json.json[i].name}</p>
                                    <p class="cart-div__content-element-price"> € ${json.json[i].price}</p>
                                    <p class="cart-div__content-element-quantity">${json.json[i].quantity}</p>
                                    <div class="cart-div__content-element-cancel"><img class="cart-div__content-element-cancel-img" src="./img/index_img/delete.png" onclick="delete_article_from_cart(${json.json[i].id},${json.json[i].size})"></div>
                                </div>
                                `;
                }

                document.querySelector(".cart-div__content").innerHTML = content;
                document.querySelector(".cart-div__total-price").innerHTML= json.total;
                
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

function delete_article_from_cart(article,global_size){
	
    fetch('/Athletix/Carrello', {                   //inserire la servlet che si occupa di aggiungere al carrello il corrente articolo
    method: 'POST',                                 //NOTA BENE!! io non ti devo passare niente dell'articolo se non la taglia scelta dal cliente
        headers: {                                  //la devi prendere dall'attributo della sessione da cui io prendo l'articolo per caricalo in questa pagina
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
        	id:article,
            quantity: 0,
        	size: global_size
        }),
    })
    .then(res => {
        res = res.headers.get('res');
        console.log(res);
        if (res != "500" && res != "0" && res != 'session_expired') {
          if (res == "200") {
            notification("notification_success","Articolo rimosso dal carrello");
            if(window.location.href.includes("#"))
                if(window.location.href.substring(url.indexOf('#') + 1) == "shopping_cart")
                    redirect("shopping_cart",false);
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

function open_modal_buy_shopping_cart(){
    if(document.querySelector(".cart-div").classList.contains("active"))
            close_cart();
    if(document.querySelector(".dropdown-menu__with-login") != null){
        document.querySelector(".modal-buy-shopping-cart").style.display = "flex";
    	onload_payment_method_and_shipping_address();
    }
    else
        notification("notification_danger","Devi effettuare il login prima di acquistare");
}

function buy_cart_by_shopping_cart(){
	let shippingAddress = document.querySelector(".modal-buy-shopping-cart__shipping-address-select");
    let paymentMethod = document.querySelector(".modal-buy-shopping-cart__payment-methods-select");
    
    fetch('/Athletix/EffettuaOrdine', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
	        shipping_address: shippingAddress.options[shippingAddress.selectedIndex].value,
	        payment_methods: paymentMethod.options[paymentMethod.selectedIndex].value,
	    }),
    })
    .then(res => {
            json = JSON.parse(res.headers.get('json_shopping_cart'));
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    notification("notification_success","Ordine avvenuto con successo");
                    close_modal_buy_shopping_cart();
                    shopping_cart_index();
                    query_popup_cart();
                    redirect("catalog",true)
                }else if(res=="400"){
                	notification("notification_danger","Mancata quantità prodotto");
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

function close_modal_buy_shopping_cart(){
    document.querySelector(".modal-buy-shopping-cart").style.display = "none";
}   

function onload_payment_method_and_shipping_address(){
    fetch('/Athletix/RichiestaRecapitoPagamento', { //servlet che restituisce due json all'interno di un json, uno per gli indirizzi di spedizione, uno per i metodi di pagamento
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(res => {
            json = JSON.parse(res.headers.get('json_shopping_cart'));
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    fill_modal_shipping_address_shopping_cart(json.shipping_address);
                    fill_modal_payment_methods_shopping_cart(json.payment_methods);
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

function fill_modal_shipping_address_shopping_cart(json){
    document.querySelector(".modal-buy-shopping-cart__shipping-address-select").innerHTML = "";
    let select = "";

    for(let i=0;i<json.length; i++){
        select += `<option value="${json[i].id}">${json[i].address}</option>`;
    }

    document.querySelector(".modal-buy-shopping-cart__shipping-address-select").innerHTML = select;
}

function fill_modal_payment_methods_shopping_cart(json){
    document.querySelector(".modal-buy-shopping-cart__payment-methods-select").innerHTML = "";
    let select = "";

    for(let i=0;i<json.length; i++){
        select += `<option value="${json[i].id}">${json[i].payment.substring(0,4) +" "+ json[i].payment.substring(4,8) +" "+ json[i].payment.substring(8,12) +" "+ json[i].payment.substring(12,16)}</option>`;
    }

    document.querySelector(".modal-buy-shopping-cart__payment-methods-select").innerHTML = select;
}

function go_to_home(){
    window.location.href = "./index.jsp";
}