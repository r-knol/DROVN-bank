
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

let popupWindow = null;
function centeredPopup(url, winName, w, h, scroll) {
    LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
    TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
    settings =
        'height=' + h + ',width=' + w + ',top=' + TopPosition + ',left=' + LeftPosition + ',scrollbars=' + scroll + ',resizable'
    popupWindow = window.open(url, winName, settings);
}

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
