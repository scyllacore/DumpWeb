class PopUpHandler {

    objHandler = new ObjectHandler();

    constructor() {
        this.setAnimation('menu-wrap');
    }

    openPopUp(page) {
        this.objHandler.selectElementByClass(page).style.display = 'block';
    }

    closePopUp(page) {
        this.objHandler.selectElementByClass(page).style.display = 'none';
    }

    handleDetailSearchPopUp(page, btn) {
        const element = this.objHandler.selectElementByClass(page)

        if (element.style.display === 'none') {
            btn.innerHTML = '상세 검색 닫기';
            element.style.display = 'block'
        } else {
            btn.innerHTML = '상세 검색 열기';
            element.style.display = 'none';
        }
    }

    setAnimation(className) {
        const element = this.objHandler.selectElementByClass(className)
        element.classList.add("animation-slideInFromRight");
    }
}


