function login() {
    var username = document.getElementById("loginUser").value;
    var password = document.getElementById("loginPassword").value;

    if (username == "" || password == "")
    {
        notification("notification_danger","Si prega di inserire le credenziali");
    }   
    else {
        fetch('/Athletix/LoginOperatore', {
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
                        location.href = '../dashboard/dashboard.jsp';
                    } else if (res == "403") {
                        notification("notification_danger","Credenziali errate");
                    }
                } else {
                    
                }
            })
        .catch((error) => {
            console.error('Error:', error);
        });
    }
}

/*function myFunction(event) {
    var x = event.code;
    console.log(x);
    if(x == "Enter") {
        login();
    }    
}*/
document.addEventListener("keypress", function(event) {
    var x = event.code;
    if(x == "Enter") {
        login();
    } 
});