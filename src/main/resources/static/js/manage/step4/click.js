$(".adsearch_btn").on("click", function (){
    if ($(this).text() == '상세검색 닫기 ▼'){
        $(this).text('상세검색 열기 ▼');
    } else {
        $(this).text('상세검색 닫기 ▼');
    }
    $(".search_ul").toggle();
})