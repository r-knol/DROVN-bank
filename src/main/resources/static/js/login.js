// Select the forms that need validation
const form = document.querySelector('.needs-validation');

// Select the fields that need additional validation of input data
const userNameField = document.querySelector('[name=userName]');
const passwordField = document.querySelector('[name=password]');

userNameField.addEventListener('input', function () {
    console.log(userNameField.value);
});

document.getElementById("loginvalid").addEventListener("click", function () {
    doFetch(userNameField.value);
});

function doFetch(userName) {
    fetch('/api/customer/find/username/' + userName, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                showError();
                console.log("Username not found");
            }
        })
        .then(res => {
            const passwordInput = passwordField.value;
            const password = res.password;
            if (passwordInput !== password) {
                console.log("Password not correct");
                showError();
            } else {
                showSuccess();
                console.log("Password correct");
            }
        })
}

function showError() {
    updateStyleError('display:block!important;');
}

function showSuccess() {
    updateStyleSuccess('display:block!important;');
}

function updateStyleError(style) {
    const error = document.querySelector('#error');
    error.setAttribute('style', style);
    window.setTimeout(function () {
        $('.alert-danger').alert('close');
    }, 2000);
}

function updateStyleSuccess(style) {
    const success = document.querySelector('#success');
    success.setAttribute('style', style);
    window.setTimeout(function () {
        $('.alert-success').alert('close');
    }, 2000);
}