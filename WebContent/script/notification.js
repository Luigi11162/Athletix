function notification(value, text){
    document.querySelector(".notification__text").innerHTML = text;
    if(value == "notification_success")
        document.querySelector(".notification__text").style.background = "#49d249";
    else if(value == "notification_danger")
        document.querySelector(".notification__text").style.background = "#F81D1D";

    document.querySelector(".notification").style.display = "flex";
    document.querySelector(".notification").classList.add("cascade-animation-top-index");
    setTimeout(function (){
        document.querySelector(".notification").classList.remove("cascade-animation-top-index");
        document.querySelector(".notification").classList.add("cascade-animation-top-reverse-index");
    }, 2500);
    setTimeout(function (){
        document.querySelector(".notification").classList.remove("cascade-animation-top-reverse-index");
        document.querySelector(".notification").style.display = "none";
    }, 4000);
}