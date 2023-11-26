let step10Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step10Handler = new Step10Handler();
})

const func = {
    async groupDriveReportsRetrieval() {
        await step10Handler.groupReportsRetrieval();
    },
    async approveInBulk() {
        await step10Handler.modifyPaymentChkInBulk(true);
    },
    async cancelInBulk() {
        await step10Handler.modifyPaymentChkInBulk(false);
    }
}