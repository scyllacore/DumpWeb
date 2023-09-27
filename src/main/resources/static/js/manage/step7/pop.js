var clBtn = null;
var opBtn = null;

$(document).ready(function(){
    var agent = navigator.userAgent.toLowerCase(); // 인터넷 익스플로러 체크 후 예외 처리
    if ((navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1) || (agent.indexOf("edge") != -1)) {
        $(".input-group-clear").attr("class", "dis-n");

        $('.input-group > .input-group-clear').each(function(i, e){
            $(this).hide();
        });
    }else{
        $('.input-group > .input-group-clear').on("click", function(){
            var clear_id = $(this).attr("id").split("_clear");

            $("#" + clear_id[0]).val('').focus();
            $(this).hide();
        })

        $('.input-group > input').on("propertychange change keyup paste", function(){
            $('#' + $(this).attr("id") + "_clear").toggle(Boolean($(this).val()))
        })

        $('.input-group > .input-group-clear').each(function(i, e){
            $(this).toggle(Boolean($(this).closest("div").find("input").val()))
        });
    }

    init();
    popJS(opBtn,clBtn);

    // /*
    // 2023-09-18 김준형
    // 선택값 자동완성 추가 (추후 스크립트 파일 이동)
    // */
    // $("#carNoFullBox").select2();
});

function init(){
    clBtn = $(".clBtn");
    opBtn = $(".opBtn");
}

function popJS(opBtn,clBtn){
    var opTarget;
    $(opBtn).click(function(){
        opTarget = "#"+ $(this).attr("data-popName");
        $(opTarget).addClass('active');
    });
    $(clBtn).click(function(){
       $(opTarget).removeClass("active")
    });
}


/*
    2023-09-18 김준형
    일괄배차 팝업 open, close 함수 추가
 */
function openPopupTest(pop_name) {
    $("#pop-" + pop_name).removeClass("dis-n");
}

function closePopUpTest(e) {
    $(e).closest(".layerMask").addClass("dis-n");
}

function saveCarData() {
    // alert("개발 중 입니다.");
    $.ajax({
        url: "/dailyReport/ajax/saveCarData",
        type: "POST",
        data: $("[name=cardatafrm]").serialize(),
        success: function (data) {
            var json = $.parseJSON(data);
            if (json.httpCode == 200) {
                alert("차량등록이 완료되었습니다.");
                location.reload();
            } else {
                alert("요청을 처리하는 도중 오류가 발생하였습니다. 관리자에게 문의부탁드립니다.");
                return false;
            }

        },
        error: function () {
            alert("서버와의 통신에 실패하였습니다.");
        }
    })
}

/*
    2023-09-18 김준형
    선택값 자동입력함수 생성 (추후 스크립트 파일 이동)
 */
$.selectBoxChange = function (data, moveid) {
    $('input[name=' + moveid + ']').val(data);
    $('#' + moveid + '_clear').show();
}