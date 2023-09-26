var closeBtn = null;
var openBtn = null;

$(document).ready(function(){
    init();
    popupJS(openBtn,closeBtn);
});

function init(){
    closeBtn = $(".closeBtn");
    openBtn = $(".openBtn");
}

function popupJS(openBtn,closeBtn){
    var openTarget;
    $(openBtn).click(function(){
        openTarget = "#"+ $(this).attr("data-popName");
        $(openTarget).addClass('active');
    });
    $(closeBtn).click(function(){
       $(openTarget).removeClass("active") 
    });
}
