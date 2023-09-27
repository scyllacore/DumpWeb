var rdOli = null;
var rdDEF = null;
var rdEngOil = null;
var rdFix = null;
var rdNote = null;

$(document).ready(function(){
    init();
    popupJS(rdOli,rdDEF,rdEngOil,rdFix,rdNote);
});

function init(){
    rdOli = $('#rdOli');
    rdDEF = $('#rdDEF');
    rdEngOil = $('#rdEngOil');
    rdFix = $('#rdFix');
    rdNote = $('#rdNote');
}

function clickJS(rdOli,rdDEF,rdEngOil,rdFix,rdNote){
    var clickTarget;
    $(rdOli,rdDEF,rdEngOil,rdFix,rdNote).click(function(){
        clickTarget = "#"+ $(this).attr("data-popName");
        $(clickTarget).addClass('active');
    });

}
