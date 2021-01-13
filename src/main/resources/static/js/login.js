// Select the forms that need validation
const form = document.querySelector('.needs-validation');

// Select the fields that need additional validation of input data
const userNameField = document.querySelector('[name=userName]');
const passwordField = document.querySelector('[name=password]');
updateStyle('display: none!important;');

userNameField.addEventListener('submit', function() {
    console.log(userNameField.value);
});

form.addEventListener('submit', function(event){
    let userName = userNameField.value;
    doFetch(userName);
    if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
    }
})

function doFetch(userName) {
    fetch('/api/customer/find/username/' + userName, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
        .then(response => {
            if (response.ok) {
                userNameField.setCustomValidity('');
                return response.json();
            } else {
                userNameField.setCustomValidity('Ongeldige invoer');
                console.log(userNameField.validity);
                showError();
            }
        })
        .then(json => {
            const passwordInput = passwordField.value;
            const password = json.password;
            if (passwordInput !== password) {
                passwordField.setCustomValidity('Ongeldige invoer');
                showError();
            } else {
                passwordField.setCustomValidity('');
            }
        })
}

function showError() {
    updateStyle('display:block!important;')
}

function updateStyle(style) {
    const error = document.querySelector('#error');
    error.setAttribute('style', style);
}
