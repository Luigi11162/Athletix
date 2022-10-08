function login() {
    var username = document.getElementById("email").value;
    var password = document.getElementById("password").value;

    if (username == "" || password == "")
    {
        notification("notification_danger","Si prega di inserire le credenziali");
    }   
    else {
        fetch('/Athletix/LoginUtente', {  //da mettere la servlet che si occupa del login del cliente
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                loginUser: username, 
                loginPassword: password
            }),
        })
        .then(res => {
            res = res.headers.get('res');
                if (res != "500" && res != "0" && res != 'session_expired') {
                    if (res == "200") {
                        location.href = '../../index.jsp';
                    } else if (res == "403") {
                        notification("notification_danger","Credenziali Errate!");
                    }
                } else {
                    notification("notification_danger","Richiesta non andata a buon fine, riprovare");
                }
            })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
}

document.addEventListener("keypress", function(event) {
    var x = event.code;
    if(x == "Enter") {
        login();
    } 
});