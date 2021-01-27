/*
* Custom form validation with use of Constraint Validation API.
*
*
* The 'forms' parameter takes an array with all the forms of a page that need validation.
*
* The 'dataVerificationFields parameter takes an array, containing all fields that need verification of input data. All
* fields in the 'dataVerificationFields array need a 'fetchBaseUrl' attribute, containing url of a REST-API endpoint.
*
*/
function validateForms(forms, dataVerificationFields) {
    'use strict';

    Array.prototype.slice.call(forms).forEach(function (form) {

        // Select all inputboxes for validation
        const formInputs = form.querySelectorAll('input.form-control');

        // Set a property of the form to contain the fields for AJAX-update
        form.dataFields = dataVerificationFields;

        // Set a property to check if all validations are successful
        form.valid = false;

        // Select all selectboxes for validation
        const formSelects = form.querySelectorAll('select');

        // Add EventListener to all input fields to validate field constraints on input
        formInputs.forEach(field => {
            field.addEventListener('input', function () {
                validateField(field);
            });
            // Fields that need to match need a HTML-attribute 'matchField' containing the name of the field to match
            if (field.hasAttribute('matchField')) {
                const fieldToMatch = form.querySelector('[name=' + field.getAttribute('matchField') + ']');
                // Set the default message to be displayed when fields don't match
                field.invalidMessage = 'Velden komen niet overeen';
                // Set property to contain the field to match
                field.matchTo = fieldToMatch;
                // Set the 'invalidMessage' to a custom value if HTML-attribute 'noMatchMessage' is present.
                if (field.hasAttribute('noMatchMessage')) {
                    field.invalidMessage = field.getAttribute('noMatchMessage');
                }
            }
        });

        // Add EventListener to all fields that need extra verification by an AJAX-call
        form.dataFields.forEach(element => {
            element.forEach(field => {
                field.addEventListener('focusout', function (event) {
                    verifyData(this);
                }, false);
            });
        });

        // Set EventListener to selectboxes to validate on change
        formSelects.forEach(select => {
            select.addEventListener('change', function () {
                validateSelect(select);
            });
        });

        // Prevent form submission if the form has fields with invalid input
        form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                // Re-validate all fields to set the correct messages
                formInputs.forEach(field => {
                    validateField(field);
                });
                form.dataFields.forEach(element => {
                    element.forEach(field => {
                        verifyData(field);
                    });
                });
                formSelects.forEach(select => {
                    validateSelect(select);
                });
            }
            form.classList.add('was-validated');
        }, false);

    });

    // Validate an input field, using the JavaScript Constraint Validation API
    function validateField(field) {

        // Reset field validity
        field.setCustomValidity(''); // customError = false
        const errorDisplayElement = field.nextElementSibling;
        errorDisplayElement.innerHTML = ''; // clear previous messages, if any

        // Create an object that will contain custom vaLidation messages
        // Additional types that could be used are: badInput, rangeOverflow, rangeUnderflow, stepMismatch, typeMismatch
        let validationMessages = {
            patternMismatch: '', tooLong: '', tooShort: '', valueMissing: ''
        }

        // Set all general validation messages. These will be overridden if specific HTML-attributes are present
        setGeneralValidationMessages();

        // Set additional custom validation messages, obtained from HTML-attributes
        Object.keys(validationMessages).forEach(function (key) {
            if (validationMessages[key] === '') {
                validationMessages[key] = setValidationMessagesFromHTMLAttributes(key);
            }
        });

        // The 'matchField' HTML-attribute indicates the field value needs to match another field's value
        if (field.hasAttribute('matchField')) {
            verifyMatchField(field, field.matchTo); // Check if the fields match
        }

        // Check if the standard field constraints are fulfilled
        const isValid = field.checkValidity();

        // If the field is invalid, apply the correct CSS-class & set the appropriate error-message.
        if (!isValid) {
            // form.valid = false;
            if (field.validity.tooShort) {
                field.setCustomValidity(validationMessages.tooShort);
            }
            if (field.validity.patternMismatch) {
                field.setCustomValidity(validationMessages.patternMismatch);
            }
            if (field.validity.tooLong) {
                field.setCustomValidity(validationMessages.tooLong);
            }
            errorDisplayElement.innerHTML = field.validationMessage; // Display the error message
        }

        // Set general messages that will be overridden if HTML-message attributes are present
        function setGeneralValidationMessages() {
            if (field.hasAttribute('minlength')) {
                validationMessages.tooShort = `Minimaal ${field.getAttribute('minlength')} karakters.`;
            }
            if (field.hasAttribute('maxlength')) {
                validationMessages.tooLong = `Maximaal ${field.getAttribute('maxlength')} karakters.`
            }
            if (field.hasAttribute('pattern')) {
                validationMessages.patternMismatch = field.getAttribute('patternMismatch');
            }
        }

        // Currently supports HTML-attributes: patternMismatch, tooLong, tooShort, valueMissing
        function setValidationMessagesFromHTMLAttributes(key) {
            if (field.hasAttribute(key)) {
                return field.getAttribute(key);
            }
            return '';
        }

        updateCss(field);

    }

    // Check if the input of two fields match & apply appropriate CSS
    function verifyMatchField(field, matchField) {
        if (field.value !== matchField.value && matchField.value !== '') {
            field.setCustomValidity(field.invalidMessage)
            matchField.setCustomValidity(matchField.invalidMessage);
        } else {
            field.setCustomValidity('');
            matchField.setCustomValidity('')
        }
        updateCss(field);
        updateCss(matchField);
    }

    // Verify the input data by a call to an api endpoint.
    function verifyData(field) {

        // Check if the input meets standard constraints
        const isValidInput = field.checkValidity();

        // Avoid unnecessary calls by exiting early when standard constraints aren't fulfilled
        if (!isValidInput) {
            // form.valid = false;
            // console.log(form)
            return;
        }

        fetch(field.fetchBaseUrl + field.value, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
            .then(response => {
                if (response.ok) { // Data exists, user input is invalid
                    // form.valid = false;
                    field.setCustomValidity(field.invalidMessage); // Invalidate the field
                    field.nextElementSibling.innerHTML = field.invalidMessage; // Display error message
                    updateCss(field);
                } else {
                    // form.valid = true;
                }
            })
            .catch(error => {
                console.log(error);
            });

    }

    function validateSelect(select) {
        select.nextElementSibling.innerHTML = ""; // Reset message

        console.log(select.selectedIndex);
        console.log(select.options[select.selectedIndex].value);
        console.log(select.validity);

        const isValid = select.checkValidity();

        if (!isValid) {
            select.nextElementSibling.innerHTML = select.validationMessage;
        }
        updateCss(select);
        // if (select.validity.valueMissing) { // Required, but no value selected.
        //     select.setCustomValidity(select.validationMessage);
        //     updateCss(select);
        //     select.nextElementSibling.innerHTML = select.validationMessage;
        // }
    }

    // Set the appropriate CSS-classes, depending on the ValidityState
    function updateCss(field) {

        const isValid = field.validity.valid;

        if (isValid) {
            field.classList.add('is-valid');
            field.classList.remove('is-invalid');
        } else {
            field.classList.remove('is-valid');
            field.classList.add('is-invalid');
        }

    }
}