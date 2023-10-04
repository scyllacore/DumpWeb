function accessInDriveMode(_this) {

    document.querySelector('input[name = "userType"]').value = _this.getAttribute("name");

    $.ajax({
        url: "/trial/ajax/trialLogin",
        type: "POST",
        data: $('form[name="trialInfo"]').serialize(),
        async: false,
        success: function (response) {
            location.href = response.data;
        },
        error: function (e) {
            if (e.status == 408) {
                alert("Login keep timed out. \nPlease log in again.");
                location.href = "/";
            }
        }

    });
}