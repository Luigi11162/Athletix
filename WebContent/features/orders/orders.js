function onclick_orders_header(element){
    let parent = element.parentNode;
    let order_line = parent.childNodes[3];
    if(order_line.style.display == "flex"){
        order_line.style.display = "none";
        
    }
    else{
        order_line.style.display = "flex";
        
    }
        
}

function print_order(element){
    let parent = element.parentNode.parentNode;
    onclick_orders_header(parent);
    let number_order = parent.childNodes[1].childNodes[3].innerHTML;

    fetch('/Athletix/Fattura', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
	        number_order_header : number_order,
	    }),
    })
    .then(res => {
            res = res.headers.get('res');
            if (res != "500" && res != "0" && res != 'session_expired') {
                if (res == "200") {
                    window.open("./pdf/"+number_order+".pdf");
                }else 
                if (res == "403") {
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