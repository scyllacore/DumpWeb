let step6Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step6Handler = new Step6Handler();
})

const func = {
    async mileageRetrieval(pageNum) {
        await step6Handler.mileageRetrieval(pageNum);
    },
    async approveInBulk() {
        await step6Handler.modifyPaymentChkInBulk(true);
    },
    async cancelInBulk() {
        await step6Handler.modifyPaymentChkInBulk(false);
    },
    async movePageSet(flag){
        step6Handler.movePageSet(flag);
    },
    async moveBothEndsPageSet(flag){
        step6Handler.moveBothEndsPageSet(flag);
    }
}