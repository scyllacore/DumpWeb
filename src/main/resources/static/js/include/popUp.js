//함수 호출
setAnimation('menu-wrap');

//함수 정의
function openPopUp(page) {
    document.querySelector('.' + page).style.display = 'block';
}

function closePopUp(page) {
    document.querySelector('.' + page).style.display = 'none';
}

function setAnimation(elementClassName) {
    const element = document.querySelector('.' + elementClassName); // 요소를 선택
    element.classList.add("animation-slideInFromRight"); // 애니메이션 클래스 추가
}