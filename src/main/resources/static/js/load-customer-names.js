function loadCustomerNames(iban) {
    let bodyContent = `iban=${iban}`
    const element = document.querySelector('#rekeningHouders');
    let result = '';

    fetch('/getnames', {
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