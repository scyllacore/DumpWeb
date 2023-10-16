//함수 호출
setAnimation('menu-wrap');

//함수 정의
function openPopUp(page) {
    document.querySelector('.' + page).style.display = 'block';
}

function closePopUp(page) {
    document.querySelector('.' + page).style.display = 'none';
}

function openAndCloseDetailSearch(page,btn) {
    const element = document.querySelector('.' + page);

    if(element.style.display === 'none' || element.style.display === ''){ // 처음에 왜 값 조회 못하는지 알아낼 것.
        btn.innerHTML = '상세 검색 닫기';
        element.style.display = 'block'
    }else{
        btn.innerHTML = '상세 검색 열기';
        element.style.display = 'none';
    }
}

function setAnimation(elementClassName) {
    const element = document.querySelector('.' + elementClassName); // 요소를 선택
    element.classList.add("animation-slideInFromRight"); // 애니메이션 클래스 추가
}


