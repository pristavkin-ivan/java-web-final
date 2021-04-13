const FORM_DIV =".form-div";
const REGEX = /[^A-Za-zа-яА-ЯЁё\-\s]/;
const INCORRECT_SYMBOLS_MESSAGE = "Please, use only: A-Z, a-z, а-я, А-Я, Ё, ё, ' ', '-'\n";
const SHORT_PASSWORD_MESSAGE = "Password is too short. Please, use more than 8 symbols\n";

function clearErrors() {
    let errors = document.querySelectorAll('.error');

    for (let i = 0; i < errors.length; i++) {
        errors[i].remove();
    }
}

function validate() {
    let valid = true;

    if (!validateName()) {
        valid = false;
    }

    if (!validatePassword()) {
        valid = false;
    }

    return valid;
}

function validateName() {
    let name = document.querySelector('#name');
    let valid = true;

    clearErrors();

    if (REGEX.test(name.value)) {
        setNameErrorLabel(INCORRECT_SYMBOLS_MESSAGE, name, FORM_DIV);
        valid = false;
    }

    return valid;
}

function validatePassword() {
    let password = document.querySelector('#password');
    let valid = true;

    if (password.value.length < 8) {
        setNameErrorLabel(SHORT_PASSWORD_MESSAGE, password, FORM_DIV);
        valid = false;
    }
    return valid;
}

function setNameErrorLabel(message, field, parent) {
    let error = document.createElement('span');
    error.className = 'error';
    error.style.color = 'red';
    error.innerHTML = message + "<br>";
    document.querySelector(parent).insertBefore(error, field);
}

