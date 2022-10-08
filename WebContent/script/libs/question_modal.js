function create_question_modal(a_modal_id, a_modal_header_type, a_question_text, a_yes_function, a_prevent_key_close) {
    var modal_container = document.createElement('div');
    modal_container.setAttribute("class", `modal question_modal fade text-white`);
    modal_container.setAttribute("id", `${a_modal_id}`);
    modal_container.setAttribute("tabindex", `-1`);
    modal_container.setAttribute("role", `dialog`);
    modal_container.setAttribute("aria-labelledby", `${a_modal_id}`);
    modal_container.setAttribute("aria-hidden", `true`);

    //<div class="modal fade text-white" id="${a_modal_id}" tabindex="-1" role="dialog" aria-labelledby="${a_modal_id}" aria-hidden="true">
    let tmp_item = `
    
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="py-0 modal-header ${a_modal_header_type}">
                        <h5 class="modal-title d-none">Modal title</h5>
                        <button type="button" class="close close_modal" onclick="$('#${a_modal_id}').modal('hide')">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="question_container text-dark text-center"> 
                            ${a_question_text}
                        </div>
                       
                    </div>
                    <div class="modal-footer justify-content-around">
                        <button type="button" class="btn btn-outline-secondary question_modal_button close_modal" onclick="$('#${a_modal_id}').modal('hide')">No</button> 
                        <button onclick="${a_yes_function}" type="button" class="btn question_modal_button confirm_button text-white`;

    if (a_modal_header_type == "danger_header")
        tmp_item += ` danger_confirm_button`;
    tmp_item += `
                        ">Sì</button>
                    </div>
                </div>
            </div>
        
    `;
    //</div>

    modal_container.innerHTML = tmp_item;
    document.getElementById("page_container").getElementsByClassName("page")[0].appendChild(modal_container);

    if (a_question_text.split(',')[0] == 'orders') {
        switch (a_question_text.split(',')[1]) {
            case 'ND': {
                $('.question_container').text('Alcuni prodotti nel carrello non sono disponibili, procedendo, i prodotti non disponibili non saranno ordinati. Procedere con il check-out?');
                break;
            }
            case 'PO': {
                $('.question_container').text('Alcuni prodotti nel carrello sono preordinabili, procedendo, l\'ordine sarà processato quando tutti i prodotti saranno disponibili . Procedere con il check-out?');
                break;
            }
            case 'NP': {
                $('.question_container').text('Nel carrello sono presenti uno o più prodotti preordinabili e non disponibili, procedendo, l\'ordine sarà processato quando tutti i prodotti saranno disponibili e quelli non disponibili non verranno ordinati. Procedere con il check-out?');
                break;
            }
        }
    }



    if (a_prevent_key_close === true)
        $(`#${a_modal_id}`).modal({ backdrop: 'static', keyboard: false })
    else
        $(`#${a_modal_id}`).modal();

    $(`#${a_modal_id}`).on('hide.bs.modal', function () {
        $(this).remove()
    });

    //document.getElementById("page_container").getElementsByClassName("page")[0].innerHTML += tmp_item;

    /*$(".close_modal").on('click', function () {
        $(".modal").remove();
    });*/
}

function set_question() {
    //$('.question_container').text(a_question);
}