let step8Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step8Handler = new Step8Handler();
})

const func = {
    async driveReportsRetrieval() {
        await step8Handler.driveReportsRetrieval();
    },
    async approveInBulk() {
        await step8Handler.modifyPaymentChkInBulk(true);
    },
    async cancelInBulk() {
        await step8Handler.modifyPaymentChkInBulk(false);
    },
    openAndCloseDetailSearch(page, btn) {
        const element = document.querySelector('.' + page);

        if (element.style.display === 'none' || element.style.display === '') { // 처음에 왜 값 조회 못하는지 알아낼 것.
            btn.innerHTML = '상세 검색 닫기';
            element.style.display = 'block'
        } else {
            btn.innerHTML = '상세 검색 열기';
            element.style.display = 'none';
        }
    }
}