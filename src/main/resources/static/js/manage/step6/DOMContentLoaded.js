/* DOMContentLoaded */
document.addEventListener("DOMContentLoaded", function () {
    // 페이지 로드 시 초기 테이블 열 순서 설정
    changeTableColumnOrder();

    // 페이지 로드 후 검색 버튼이 자동으로 눌리도록 처리.
    clickSearchButton();

    //리스트를 통한 페이지 이동 처리.
    clickListThAndRedirect();
});
