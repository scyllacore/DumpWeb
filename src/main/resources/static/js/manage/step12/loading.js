let step12Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step12Handler = new Step12GroupHandler();
})

const func = {
    async groupOrderReportsRetrieval() {
        await step12Handler.groupReportsRetrieval();
    },
    async approveInBulk() {
        await step12Handler.modifyPaymentChkInBulk(true);
    },
    async cancelInBulk() {
        await step12Handler.modifyPaymentChkInBulk(false);
    }
}