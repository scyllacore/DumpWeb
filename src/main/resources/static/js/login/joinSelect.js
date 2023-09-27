function setType(obj) {
    var cls = $(obj).attr("class");
    var clsArr = cls.split(" ");
    var type = clsArr[clsArr.length - 1];
    console.log(type);

    var frm = document.joinfrm;

    $("[name=type]").val(type);
    frm.action = "/join/step2";
    frm.submit();
}