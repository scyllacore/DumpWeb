//함수 호출
setAnimation('menu-wrap');

//함수 정의
function setAnimation(elementClassName){
    const element = document.querySelector('.'+elementClassName); // 요소를 선택
    element.classList.add("animation-slideInFromRight"); // 애니메이션 클래스 추가
}