// eerste script
document.querySelector('#iban').addEventListener('focusout', checkForIbanPart);
const selectElement = document.querySelector('#selecteerRekening');
const ibanError = document.querySelector('#iban_error');

function checkForIbanPart() {
    let regex = new RegExp(/^[A-Za-z]{2}[0-9]{2}[A-Za-z0-9]{1,30}$/i)
    let iban = document.querySelector('#iban').value
    let bodyContent = `iban=${iban}`

    console.log('Iban is valide: ' + regex.test(iban))

    if (regex.test(iban)) {
        fetch('http://localhost:8080/iban', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: bodyContent
        })
            .then(response => {
                if (response.status === 200) {
                    showValidIban()
                    console.log('De iban is gevonden in de database.')
                } else {
                    console.log('De iban is helaas NIET gevonden in de database.')
                    showError()
                }
            })
            .catch(error => {
                console.log('Foutje ergens! ' + error)
            });
    } else {
        showError();
    }
}

function showValidIban() {
    ibanError.innerHTML = '<i class="fa fa-smile-o icon"></i> Dit is een geldige IBAN. ';
    ibanError.style.color = "green";
}

function showError() {
    ibanError.innerHTML = '<i class="fa fa-frown-o icon"></i> Ongeldige IBAN, probeer het opnieuw.';
    ibanError.style.color = "red";
}

// tweede script
selectElement.addEventListener('change', (event) => {
    const iban = event.target.value;
    loadCustomerNames(iban);
});

function loadCustomerNames(iban) {
    let bodyContent = `iban=${iban}`
    const element = document.querySelector('#rekeningHouders');
    let result = '';

    fetch('http://localhost:8080/getnames', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: bodyContent
    })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                console.log('De rekeninghoudernamen zijn helaas NIET gevonden in de database.')
            }
        })
        .then(json => {
            json.forEach(object => {
                result += object + '<br>';
                console.log(object);
                element.innerHTML = result;
            })
        })
}

