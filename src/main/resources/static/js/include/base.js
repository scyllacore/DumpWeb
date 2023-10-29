function checkUndefined(val) {
    if (typeof val === 'undefined') {
        return true;
    }
    return false;
}

console.log( document.querySelector('[name="' + 'paymentChk' + '"]').type);