
function set_cookie(a_cookie_name, a_cookie_value, a_days_duration) {
    var current_date = new Date();
    current_date.setTime(current_date.getTime() + (a_days_duration * 24 * 60 * 60 * 1000));
    var expire_date = "expires=" + current_date.toUTCString();

    document.cookie = a_cookie_name + "=" + a_cookie_value + ";" + expire_date + ";path=/";
}

function get_cookie_by_name(a_cookie_name) {
    var cookie_name = a_cookie_name + "=";
    var cookie_items_array = document.cookie.split(';');

    for (var i = 0; i < cookie_items_array.length; i++) {
        var cookie_item = cookie_items_array[i];

        while (cookie_item.charAt(0) == ' ') {
            cookie_item = cookie_item.substring(1);
        }

        if (cookie_item.indexOf(cookie_name) == 0) {
            return cookie_item.substring(cookie_name.length, cookie_item.length);
        }
    }

    return "";
}