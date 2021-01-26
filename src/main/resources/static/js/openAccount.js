
window.onload = function random() {
    let country_code = "NL"
    let bank_code = "DROVN";
    let IBAN = "";
    let string_length = 11;
    let characters = "0123456789";
    for (let i = 0; i < string_length; i++) {
        IBAN += characters.charAt(Math.floor(Math.random() * characters.length));
    }
    let first_part = IBAN.substring(0, 2)
    let final_IBAN = IBAN.substring(0, 9);

    return document.ibanForm.ibanGenerator.value = country_code + first_part + bank_code + final_IBAN;
}

$(document).ready(function(){
    $('.openTerms').on('click',function(){
        var dataURL = $(this).attr('data-href');
        $('.modal-body').load(dataURL,function(){
            $('#termsConditions').modal({show:true});
        });
    });
});

let acc = document.getElementsByClassName("accordion");
let i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        let panel = this.nextElementSibling;
        if (panel.style.maxHeight) {
            panel.style.maxHeight = null;
        } else {
            panel.style.maxHeight = panel.scrollHeight + "px";
        }
    });
}


