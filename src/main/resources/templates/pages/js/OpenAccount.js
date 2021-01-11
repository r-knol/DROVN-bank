

function random() {
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


const requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: urlencoded,
    redirect: 'follow'
};

function checkUniqueIban() {
    let element = document.querySelector("#ibanGenerator");
    element.classList.add("show");

    fetch("http://localhost:8080/openaccount/check_iban")
        .then(function(response) {
            return response.json()
        }).then(function (data){
            console.log(data)
    })


    let popupWindow = null;
    function centeredPopup(url, winName, w, h, scroll) {
        LeftPosition = (screen.width) ? (screen.width - w) / 2 : 0;
        TopPosition = (screen.height) ? (screen.height - h) / 2 : 0;
        settings =
            'height=' + h + ',width=' + w + ',top=' + TopPosition + ',left=' + LeftPosition + ',scrollbars=' + scroll + ',resizable'
        popupWindow = window.open(url, winName, settings)
    }

    function ibanButton() {
        document.addEventListener("click", random());
        disableButton();
    }

    function disableButton() {
        document.getElementById("ibanButtonClicked").disabled = true;
    }

    document.getElementById('ibanGenerator').addEventListener('focusout', checkUniqueIban);
}