const rekeningHouders = document.querySelector('#rekeningHouders');
const iban = document.querySelector('#ibanDebitAccount').value;
rekeningHouders.innerHTML = loadCustomerNames(iban);