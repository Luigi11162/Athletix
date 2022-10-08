var mobile_detector;
let url;

function caricamento(){
    mobile_detector = new MobileDetect(window.navigator.userAgent);
    
    update_clock();
    //setup_dashboard();
    
    setup_sidenav();

    url = window.location.href;
    if(url.includes("#"))
        redirect(url.substring(url.indexOf('#') + 1), false);
    else
    	redirect('article', '');
    
    document.querySelector(".dropdown-menu").style.display = "none";

    var json_obj = JSON.parse(`{
                                "groupName":"Administrastor",
                                "username":"ADMIN",
                                "menu":[
                                        {"name":"Gestione Articoli","redirectPage":"article"},
                                        {"name":"Gestione Categorie","redirectPage":"category"},
                                        {"name":"Gestione Unità","redirectPage":"unity"},
                                        {"name":"Gestione Taglie","redirectPage":"size"},
                                        {"name":"Archivi","redirectPage":"",
                                            "children":[
                                                {"name":"Clienti","redirectPage":"customers"},
                                                {"name":"Storico ordini","redirectPage":"orders_history"}
                                            ]
                                        }
                                    ]
                                }`);

    /* {"name":"Gestione ordini","redirectPage":"",
            "children":[
                    {"name":"Ordini","redirectPage":"new_orders"},
                    {"name":"Storico ordini","redirectPage":"orders_history"}
                ]
            },
        {"name":"Gestione articoli","redirectPage":"",
            "children":[
                    {"name":"Categorie","redirectPage":"categories"},
                    {"name":"Anagrafica Articoli","redirectPage":"articles"}
                ]
            },
        {"name":"Gestione Documenti","redirectPage":"",
            "children":[
                    {"name":"Documenti Vendita","redirectPage":"sales_documents"},
                    {"name":"Documenti Acquisto","redirectPage":"purchase_documents"}
                ]
            },
        {"name":"Gestione Pagamenti","redirectPage":"",
            "children":[
                    {"name":"Tipi Pagamento","redirectPage":"payments_types"},
                    {"name":"Credenziali Stripe","redirectPage":"stripe_credentials"}
                ]
            },
        {"name":"Statistiche","redirectPage":"statistics"},
        {"name":"Archivi","redirectPage":"",
            "children":[
                {"name":"Operatori","redirectPage":"operators"},
                {"name":"Gruppi Operatori","redirectPage":"operators_groups"},
                {"name":"Clienti","redirectPage":"customers"},
                {"name":"Fornitori","redirectPage":"providers"}
            ]
        },
        {"name":"Tabelle","redirectPage":"",
            "children":[
                {"name":"Tabelle Iva","redirectPage":"",
                    "children":[
                        {"name":"Iva","redirectPage":"vat"},
                        {"name":"Esenzioni iva","redirectPage":"vat_nature"}
                    ]
                },
                {"name":"Tabelle Doc","redirectPage":"",
                    "children":[
                        {"name":"Gruppi Documenti","redirectPage":"documents_groups"},
                        {"name":"Tipi Documento","redirectPage":"document_types"},
                        {"name":"Causali Documento","redirectPage":"documents_causal"}
                    ]
                },
                {"name":"Spedizioni","redirectPage":"shipment_types"},
                {"name":"Parametri","redirectPage":"parameters"}
            ]
        }*/

    document.querySelector(".profile_name").innerHTML = json_obj.username;
    document.querySelector(".group_name").innerHTML = json_obj.groupName;

    var json_menu = json_obj.menu;

    var div = document.getElementsByClassName("menu_container")[0];
    var new_margin = -20;

    create_sidenav_item(json_menu, div, new_margin);
}

var LightTableFilter = (function(Arr) {

    var _input;
    let j;

    function _onInputEvent(e) {
        _input = e.target;
        var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
        j = 0;
        
        Arr.forEach.call(tables, function(table) {
            Arr.forEach.call(table.tBodies, function(tbody) {
                Arr.forEach.call(tbody.rows, _filter)
            });
        });
        
        if(j==0)
            document.querySelector(".container__table-body-tr-no-element").style.display = "table-row";
        else
            document.querySelector(".container__table-body-tr-no-element").style.display = "none";
    }

    function _filter(row) {
        var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
        if(text.indexOf(val) === -1){
            row.style.display = 'none';
        }   
        else
        {
            row.style.display = 'table-row';
            j = 1;
        }  
    }

    return {
        init: function() {
            var inputs = document.getElementsByClassName('light-table-filter');
            Arr.forEach.call(inputs, function(input) {
                input.oninput = _onInputEvent;
            });
        }
    };
})(Array.prototype);

var TableFilterOrdersLine = (function(Arr) {

    var _input;
    let j;

    function _onInputEvent(e) {
        _input = e.target;
        var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
        j = 0;

        
        
        Arr.forEach.call(tables, function(table) {
            Arr.forEach.call(table.tBodies, function(tbody) {
                Arr.forEach.call(tbody.rows, _filter)
            });
        });
        
        
        if(j==0)
            document.querySelector(".modal-view-order-history__table-body-tr-no-element").style.display = "table-row";
        else
            document.querySelector(".modal-view-order-history__table-body-tr-no-element").style.display = "none";
    }

    function _filter(row) {
        var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
        if(text.indexOf(val) === -1){
            row.style.display = 'none';
        }   
        else
        {
            row.style.display = 'table-row';
            j = 1;
        }  
    }

    return {
        init: function() {
            var inputs = document.getElementsByClassName('light-table-filter-line');
            Arr.forEach.call(inputs, function(input) {
                input.oninput = _onInputEvent;
            });
        }
    };
})(Array.prototype);

function update_clock() {
    var date = new Date();
    var h = date.getHours(); // 0 - 23
    var m = date.getMinutes(); // 0 - 59   

    h = (h < 10) ? "0" + h : h;
    m = (m < 10) ? "0" + m : m;

    var time = h + ":" + m;

    document.getElementById("clock").innerHTML = time;

    setTimeout(update_clock, 1000);
}

function show_dropdown_menu(){
    if(document.querySelector(".dropdown-menu").style.display == "none")
        document.querySelector(".dropdown-menu").style.display = "block";
    else
        document.querySelector(".dropdown-menu").style.display = "none";
}

function open_sidenav() {
    // open sidebar
    document.querySelector("#sidebar").classList.add('active');
    // fade in the overlay
    /*document.querySelector(".overlay").classList.add('active');
    document.querySelector(".collapse.in").classList.remove('in');
    document.querySelector('a[aria-expanded=true]').classList.setAttribute('aria-expanded', 'false');*/
}

function create_sidenav_item(json, div, new_margin) {
    var sub_div;
    var sub_div_child;
    new_margin += 20;
    var icon_path = './assets/img/dashboard_icons/menu/';

    if (json != null) {
        for (var i = 0; i < json.length; i++) {
            var icon_name = json[i].name.toLowerCase();
            //icon_name = icon_name.replaceAll(' ', "_");
            icon_name = icon_name.replace(/ /g, "_");

            if (json[i].redirectPage == "") {
                sub_div = document.createElement('div');
                sub_div_child = document.createElement('div');
                div.appendChild(sub_div);

                sub_div.innerHTML += `<div class="navigation_button submenu_button" style="margin-left:` + new_margin + `px" onclick="submenu_button_click(this)">
                
                        <div class="navigation_button_icon ">
                            <img src="`+ icon_path + icon_name + `.png" alt="" class='menu_icon'>
                        </div>

                        <span class="navigation_button_text">`+ json[i].name + `</span>
                    
                        <svg class="navigation_button_menu" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right-fill" viewBox="0 0 16 16">  
                            <path d="M12.14 8.753l-5.482 4.796c-.646.566-1.658.106-1.658-.753V3.204a1 1 0 0 1 1.659-.753l5.48 4.796a1 1 0 0 1 0 1.506z" />
                        </svg>
                    </div>`;

                //sub_div_child.classList.add(`pl-2`);
                sub_div_child.classList.add("submenu");
                sub_div.appendChild(sub_div_child);

                create_sidenav_item(json[i].children, sub_div_child, new_margin);
            }
            else {
                div.innerHTML += `<div id="${json[i].redirectPage}_button" onclick="redirect('` + json[i].redirectPage + `',true)" class="navigation_button" style="margin-left:` + new_margin + `px">
                
                                    <div class="navigation_button_icon">
                                        <img src="`+ icon_path + icon_name + `.png" alt="" class='menu_icon'>
                                    </div>

                                    <span class="navigation_button_text">`+ json[i].name + `</span>
                            </div>`;
            }
        }
    }
}
    
function submenu_button_click(element){

    let submenu = element.parentNode.getElementsByClassName("submenu")[0];
    close_all_submenus(submenu);

    let submenu_collapse_icon = element.parentNode.getElementsByClassName("navigation_button")[0].getElementsByTagName("svg")[0];

    if (submenu.style.display == "block") {
        submenu_collapse_icon.style.transform = 'rotate(0deg)';
        submenu.style.display = "none";
    } else {
        submenu_collapse_icon.style.transform = 'rotate(90deg)';
        submenu.style.display = "block";
    }
}

async function setup_dashboard(){

    fetch('', {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            api: 'get_session'
        }),
    })
    .then(response => response.text())
    .then(res => {
        let json_obj = JSON.parse(res);

        console.log(json_obj)

        document.querySelector(".profile_name").innerHTML = json_obj.username;
        document.querySelector(".group_name").innerHTML = json_obj.groupName;

        var json_menu = json_obj.menu;

        var div = document.getElementsByClassName("menu_container")[0];
        var new_margin = -20;

        create_sidenav_item(json_menu, div, new_margin);
        })
    .catch((error) => {
        console.error('Error:', error);
    });

}

function redirect(a_page, a_has_to_pushstate) {
    const page_container = document.querySelector("#page_container");

    fetch(`./features/${a_page}/${a_page}.jsp`)
    .then(response => response.text())
    .then(res => {
        if(a_has_to_pushstate)
        	history.pushState({pagina : a_page}, `${a_page}` , `./dashboard.jsp#${a_page}`);
        	
            
		
        page_container.innerHTML = res;
        window[a_page]();
        })
    .catch((error) => {
        console.error('Error:', error);
    });
}

function article(){
    LightTableFilter.init();

    var boxes = document.querySelectorAll('.box');
    
    for (let i = 0; i < boxes.length; i++) {
        let box = boxes[i];
        initDropEffect(box);
        initImageUpload(box);
    }
}

function unity(){
    LightTableFilter.init();
}

function size(){
    LightTableFilter.init();
}

function category(){
    LightTableFilter.init();
}

function customers(){
    LightTableFilter.init();
}

function orders_history(){
    TableFilterOrdersLine.init();
    LightTableFilter.init();

    //filter date start
    document.querySelector(".container-order-history__filter-date-start-input").addEventListener("change",() => event_date_click());
    
    //filter date finish
    document.querySelector(".container-order-history__filter-date-finish-input").addEventListener("change",() => event_date_click());
}

window.addEventListener('popstate', e => {
    if(e.state == null)
        redirect('home',false);
    else
        redirect(e.state.pagina, false);
});

//history.replaceState({pagina: null}, 'home', './');

function setup_sidenav() {

    if (get_cookie_by_name('menu_blocked') == 'True') {
        document.querySelector("#sidebar").classList.add('active');
    }

    hammertime = new Hammer(document.getElementById('sidebar'));
    hammertime.get('swipe').set({ direction: Hammer.DIRECTION_HORIZONTAL });

    if (get_cookie_by_name('menu_blocked') == '') {
        if (mobile_detector.mobile() == null) {
            pin_sidenav();
            document.getElementById("sidebar").className += 'active';
        } else {
            unpin_sidenav();
        }
    } else {
        if (get_cookie_by_name('menu_blocked') == 'True') {
            pin_sidenav();
        }
        else {
            unpin_sidenav();
        }
    }
}

function pin_sidenav() {
    document.getElementById("img_pin").style.transform = "rotate(0deg)";

    document.getElementById("dismiss").style.display = "none";
    document.querySelector("#page_container").classList.add('active');
    document.getElementById('sidebarCollapse').style.display = "none";
    document.querySelector(".header_content").classList.add('active');
    hammertime.off('swipe');
    document.querySelector('#clock').setAttribute('margin', '0 auto');
    //document.querySelector('#clock').style.css('margin', '0 auto');
}

function unpin_sidenav() {
    document.getElementById("img_pin").style.transform = "rotate(45deg)";

    document.getElementById("dismiss").style.display = "block";
    document.querySelector("#page_container").classList.remove('active');
    document.getElementById('sidebarCollapse').style.display = "block";
    document.querySelector(".header_content").classList.remove('active');
    document.querySelector('#clock').setAttribute('margin', '0');
    //document.querySelector('#clock').style.css('margin', '0');

    hammertime.get('swipe').set({ direction: Hammer.DIRECTION_HORIZONTAL });
    hammertime.on('swipe', function (ev) {
        if (document.getElementById('sidebar').classList.contains('active')) {
            close_menu();
        }
    });
}

function toggle_sidenav_pin() {
    if (get_cookie_by_name('menu_blocked') == 'undefined') {
        hammertime.on('swipe', function (ev) {

            if (document.getElementById('sidebar').classList.contains('active')) {
                close_menu();
            }
        });
    }

    if (get_cookie_by_name('menu_blocked') == 'False') {
        set_cookie('menu_blocked', 'True', "999999");
        pin_sidenav();
    }
    else {
        set_cookie('menu_blocked', 'False', "999999");
        unpin_sidenav();
    }
}

function close_menu() {
    // hide sidebar
    document.querySelector("#sidebar").classList.remove('active');
    
    // hide overlay
    //document.querySelector(".overlay").remove('active');
}

function close_all_submenus(element) {
    const submenu_items_collection = document.getElementsByClassName('submenu_button');
    let submenu;
    let submenu_collapse_icon;
    var father = element;

    while(father.parentNode.parentNode.matches(".submenu"))
    {
        father = father.parentNode.parentNode;
    }

    for (let i = 0; i < submenu_items_collection.length; i++) {
        submenu = submenu_items_collection[i].parentNode.getElementsByClassName("submenu")[0];
        submenu_collapse_icon = submenu_items_collection[i].parentNode.getElementsByClassName("navigation_button")[0].getElementsByTagName("svg")[0];

        
        if(submenu != father && submenu != element)
        {
            submenu.style.display = "none";
            submenu_collapse_icon.style.transform = 'rotate(0deg)';
        }
    }
}

function set_fullscreen() {
    elem = document.documentElement;
    let fullscreen_button_image = fullscreen_button.getElementsByTagName('img')[0];
    let fullscreen_button_label = fullscreen_button.getElementsByTagName('span')[0];


    if (elem.requestFullscreen) {
        elem.requestFullscreen();
    } else if (elem.webkitRequestFullscreen) { /* Safari */
        elem.webkitRequestFullscreen();
    } else if (elem.msRequestFullscreen) { /* IE11 */
        elem.msRequestFullscreen();
    }

    fullscreen_button_image.src = './assets/img/dashboard_icons/fullscreen_icon_active.svg';
    fullscreen_button.onclick = unset_fullscreen;
    fullscreen_button_label.innerText = 'Disattiva schermo intero';

    set_cookie('fullscreen', 'true', "999999");
}

function unset_fullscreen() {
    let fullscreen_button_image = fullscreen_button.getElementsByTagName('img')[0];
    let fullscreen_button_label = fullscreen_button.getElementsByTagName('span')[0];

    if (document.exitFullscreen) {
        document.exitFullscreen();
    } else if (document.webkitExitFullscreen) { /* Safari */
        document.webkitExitFullscreen();
    } else if (document.msExitFullscreen) { /* IE11 */
        document.msExitFullscreen();
    }

    fullscreen_button_image.src = './assets/img/dashboard_icons/fullscreen_icon.svg';
    fullscreen_button_label.innerText = 'Attiva schermo intero';
    fullscreen_button.onclick = set_fullscreen;

    set_cookie('fullscreen', 'false', "999999");
}

function open_logout_modal(){
    /*if(document.querySelector("#page_container").classList.contains("active"))
      document.querySelector(".modal-delete-unity").style.marginLeft = "-280px";
    else
      document.querySelector(".modal-delete-unity").style.marginLeft = "0px";*/
    document.querySelector(".modal-logout").style.display = "flex";

    /*document.querySelector(".modal-delete-unity__button-yes").addEventListener('click', function(){
      delete_article(value);
    });*/
}

function close_logout_modal(){
    document.querySelector(".modal-logout").style.display = "none";
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
            window.location = "../authentication/login-form.jsp";
        }
    }).catch(error => console.log("Si è verificato un errore!"));

    
}