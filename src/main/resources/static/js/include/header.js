$(document).ready(function () {
    var nowPath = $(location).attr('pathname');
    var nows = nowPath.split("/");

    $(".left_menu").each(function (index, el) {
        var aPath = $(this).attr("href");
        var tempAs = aPath.split("/");

        var nowTemp = nows[1];

        if(tempAs[1] == 'dispatchSetting' || tempAs[1] == 'unmanned' || tempAs[1] == 'dailyReport'){
            if(nowTemp = 'unmanned' && nows[2].indexOf("step") != -1){
                nowTemp = "select";
                tempAs[1] = tempAs[2];
            }else {
                nowTemp = nows[2];
                tempAs[1] = tempAs[2];
            }
        }

        if (nowTemp == tempAs[1]) {
            $(this).addClass("active");
        }
    });

    if ($("#searchDetailChk").val() == 0) {
        $("#advsearch").html("상세검색 열기 ▼");
    } else {
        $("#advsearch").html("상세검색 닫기 ▲");
        $(".searchArea").toggleClass("dis-n");
        $("#dateArea").addClass("open");
    }

    $("#advsearch").click(function() {
        if($(".searchArea").hasClass("dis-n")){
            $("#advsearch").html("상세검색 닫기 ▲");
            $("#dateArea").addClass("open");
            $("#searchDetailChk").val("1");
        } else {
            $("#advsearch").html("상세검색 열기 ▼");
            $("#dateArea").removeClass("open");
            $("#searchDetailChk").val("0");
        }
        $(".searchArea").toggleClass('dis-n');
    });

    $(".screen_top").click(function () {
        $("html, body").animate({
            scrollTop: 0
        }, 400);
    })
})


$.moveChangeForm = function () {
    modal({
        title: '알림메시지',
        type: 'confirm',
        text: '암호를 변경 하시겠습니까?',
        callback : function(result){
            if (result == true) {
                location.href="/pwChange";
            }
        }
    });
}


$.logout = function() {
    modal({
        title: '알림메시지',
        type: 'confirm',
        text: '로그아웃 하시겠습니까?',
        callback : function(result){
            if (result == true) {
                location.href="/logout";
            }
        }
    });
}


$.toggleLeftMenu = function() {
    //마스크 활성화
    $(".layer-mask").toggleClass('show');

    var leftMenu = $(".mobile-navi-wrap");

    //레프트메뉴 오픈, 닫기
    if($(".layer-mask").hasClass("show")){
        $(leftMenu).animate({right: '0'}, 500);
        $(leftMenu).addClass("scroll");
        $("html, body").addClass("overflow-h");
        $(".mobile-navi-wrap").css("display","block");
        $(".layer-mask").css("display","block")
        //$("html, body").css({overflow:'hidden'});
    }else{
        $(leftMenu).animate({right: '-1200px'}, 500);
        $(leftMenu).removeClass("scroll");
        $("html, body").removeClass("overflow-h");
        $(".mobile-navi-wrap").css("display","none")
        $(".layer-mask").css("display","none")
        //$("html, body").css({overflow:'auto'});
    }
};


//레이어팝업 오픈
$.openLayerHelpMsgPopUp = function( _this, code){
    //레이어팝업 닫기시 다시 탭 포커스 이동해시켜주기 위함
    _focus = $(_this);

    $("#pop-question").removeClass("dis-n");
    //포커스를 팝업 내로 옮겨줌
    $("#pop-question").attr("tabindex","0").focus();
    /*return $(this);*/

    $.ajax({
        type: 'post',
        url: "/common/helpMsg/" + code,
        async : false,
        beforeSend : function(xmlHttpRequest){
            xmlHttpRequest.setRequestHeader("AJAX", "Use");
        },
        success: function(data) {
            var json = $.parseJSON(data);

            if (json.httpCode == 200) {
                $("#pop-question").find("#helpMsgTitle").html("도움말 (" + code + ")");
                $("#pop-question").find("#helpMsgBody").html(json.helpMsg);
            }
        },
        error : function(e) {
            if(e.status == 408){
                alert("Login keep timed out. \nPlease log in again.");
                location.href = "/";
            }
        }
    });
}

$.closeLayerHelpMsgPopUp = function(obj){
    if(obj){
        $(obj).parents(".layerMask").addClass("dis-n");
    }else{
        $(".layerMask").addClass("dis-n");
    }
    if(_focus.length > 0){
        _focus.focus();
    }
}
