function setTrial(obj) {
    var cls = $(obj).attr("class");
    var clsArr = cls.split(" ");
    var type = clsArr[clsArr.length -1];
    console.log(type);

    $("[name=type]").val(type);

    $.ajax({
        url: "/trial/login",
        type: "POST",
        data: $("[name=trialfrm]").serialize(),
        success: function (data) {
            var json = $.parseJSON(data);

            if (json.httpCode == 200) {
                location.replace(json.rtnUrl);
            } else {
                console.error("Initial Server Error 500")
            }
        },
        error: function (e) {
            console.error("Connection Error " + e.status);
        }
    })
}