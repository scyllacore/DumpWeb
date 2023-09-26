$(document).ready(function () {
        // AOP 대신 임시로 설정
        if($('[name=uuserID]').val() == null || $.trim($('[name=uuserID]').val()) == "") {
            location.replace("/");
        }
});

$.pwChange = function(){

    console.log(/(\d)\1\1\1/.test("1111"));

    const repeatNumber = /(\d)\1\1\1/;
    const Continuously = /(0123)|(2345)|(3456)|(4567)|(5678)|(6789)|(7890)/;

    // LINE :: 비밀번호 확인
    if ($("[name=new_userPw]").val() == 0) {
        alert("비밀번호를 입력해주세요.");
        $("[name=new_userPw]").focus();
        return false;
    }

    // LINE :: 비밀번호 확인
    if ($("[name=re_userPw]").val() == 0) {

        alert("비밀번호확인을 입력해주세요.");
        $("[name=re_userPw]").focus();
        return false;
    }

    // LINE :: 비밀번호 유효성 검사
    if($("[name=new_userPw]").val().length < 4){
        alert("비밀번호는 4자리 이상이어야 합니다.");
        return false;
    }

    // LINE :: 비밀번호가 다를 경우
    if($("[name=new_userPw]").val() != $("[name=re_userPw]").val()){
        alert("비밀번호가 서로 다릅니다.\n확인 후 다시 입력해주세요.");
        $("[name=re_userPw]").focus();
        return false;
    }

    //
    if(repeatNumber.test($("[name=new_userPw]").val())) {
        alert("비밀번호에 같은 숫자가 4자 이상 반복될 수 없습니다.");
        $("[name=new_userPw]").focus();
        return false;
    }

    if (Continuously.test($("[name=new_userPw]").val())) {
        alert("비밀번호에 4회 이상 연속된 숫자를 입력할 수 없습니다.");
        $("[name=new_userPw]").focus();
        return false;
    }
    
    if (!Number($("[name=new_userPw]").val())) {
        alert("비밀번호는 숫자로만 설정할 수 있습니다.");
        $("[name=new_userPw]").focus();
        return false;
    }

    if ($("#privacyChk").is(":checked") == false) {
        alert("개인정보 수집 및 이용에 동의해야합니다.");
        return false;
    }

    if(confirm("설정하신 비밀번호로 변경하시겠습니까?")) {
        $.ajax( {
            url:"/ajax/pwChange",
            type:"POST",
            data: $('[name=pwfrm]').serialize(),
            success: function (data) {
                // console.log(data);
                var json = $.parseJSON(data);

                if(json.httpCode == 200) {
                    // console.log(json.smsSample);
                    location.href = "/";
                } else {
                    modal({
                        title: '알림메세지',
                        type: 'alert',
                        text: json.message
                    })
                }
            }
        })
    }

}

$.openLayerPrivaryPopUp = function(target, _this){
    //레이어팝업 닫기시 다시 탭 포커스 이동해시켜주기 위함
    _focus = $(_this);

    $("#"+target).removeClass("dis-n");
    //포커스를 팝업 내로 옮겨줌
    $("#"+target).attr("tabindex","0").focus();
    /*return $(this);*/
}

$.closeLayerPrivaryPopUp = function(obj) {
    if (obj) {
        $(obj).parents(".layerMask").addClass("dis-n");
    } else {
        $(".layerMask").addClass("dis-n");
    }
    if (_focus.length > 0) {
        _focus.focus();
    }
}

$.prevPage = function (grade) {
    if(grade == '1') {
        location.replace("/slipStatus/list");
    }
    else if(grade == '2') {
        location.replace("/dispatchMng/list");

    }
    else if(grade == '3') {
        location.replace("/unspecifiedDispatch/list");

    }
    else if(grade == '4') {
        location.replace("/carManage/list");

    }
    else if (grade == '5') {
        location.replace("/unmanned/loader/list");

    }
}