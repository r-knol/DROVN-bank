(function () {

    const $buttonPrivate = $("#private");
    const $buttonLegal = $("#legal");

    $buttonLegal.on('click', function (event) {
        event.preventDefault();
        $(this).toggleClass('active');
        $buttonPrivate.toggleClass('active');
    });

    $buttonPrivate.on('click', function (event) {
        event.preventDefault();
        $(this).toggleClass('active');
        $buttonLegal.toggleClass('active');
    });

    // Hide 0 in input field
    document.querySelector('#kvkNumber').value = '';

    // Select the forms that need validation
    const forms = document.querySelectorAll('.needs-validation');

    // Select the fields that need additional validation of input data
    let dataVerificationFields = [];

    const usernameFields = document.querySelectorAll('[name=userName]');
    const socialSecurityNumberFields = document.querySelectorAll('[name=socialSecurityNumber]');
    const kvkNumberFields = document.querySelectorAll('[name=kvkNumber]');

    // Base url for fetch of data from db
    const customerAPI = "/api/customer/find/"; // CustomerController

    setData(usernameFields, 'Deze gebruikersnaam is al in gebruik.', customerAPI);
    setData(socialSecurityNumberFields, 'Dit BSN is al geregistreerd.', customerAPI);
    setData(kvkNumberFields, 'Dit KvK-nummer is al geregistreerd.', customerAPI);
    dataVerificationFields.push(usernameFields);
    dataVerificationFields.push(socialSecurityNumberFields);
    dataVerificationFields.push(kvkNumberFields);

    // Now add validation to the forms
    validateForms(forms, dataVerificationFields);

    function setData(fields, message, fetchBaseUrl) {
        fields.forEach(field => {
            field.invalidMessage = message;
            field.fetchBaseUrl = fetchBaseUrl + field.name.toLowerCase() + '/';
        });
    }

})();
