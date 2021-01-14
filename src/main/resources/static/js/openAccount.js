
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

    window.addEventListener("DOMContentLoaded", function(e) {

    const myForm = document.querySelector("#ibanForm");
    const checkForm = function(e) {
        if(!this.terms.checked) {
            alert("Om uw nieuwe rekening te bevestigen dient u akkoord te gaan met de algemene voorwaarden");
            this.terms.focus();
            e.preventDefault(); // equivalent to return false
        }
    };

    // attach the form submit handler
    myForm.addEventListener("submit", checkForm, false);

    var myCheckbox = document.querySelector("#terms");
    var myCheckboxMsg = "Graag de algemene voorwaarden accepteren";

    // set the starting error message
    myCheckbox.setCustomValidity(myCheckboxMsg);

    // attach checkbox handler to toggle error message
    myCheckbox.addEventListener("change", function(e) {
        this.setCustomValidity(this.validity.valueMissing ? myCheckboxMsg : "");
    }, false);

}, false);




/*
const form = document.querySelector('.needs-validation');
console.log(form);

const ibanField = document.querySelector('[name=ibanGenerator]');
console.log(ibanField.value);
checkUniqueIban(ibanGenerator);

form.addEventListener('submit', function(event){
    checkUniqueIban(ibanGenerator);
})

function checkUniqueIban(ibanGenerator) {
    let bodyContent = `iban=${ibanGenerator}`;
    fetch("http://localhost:8080/check_iban/", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'},
        body: bodyContent
    })
        .then(response => {

            if(response.ok){
                console.log('De iban is niet gevonden in de DB')
                processIban();
            } else {
                console.log("iban wel gevonden gevonden")
                showError();
            }
        })
    ;
}

function processIban(iban){
    document.getElementById('error').style.display = 'none';
    document.getElementById('iban').classList.remove('error');
}

function showError(){
    document.getElementById('error').style.display = 'block';
    document.getElementById('iban').classList.add('error');
}
*/
