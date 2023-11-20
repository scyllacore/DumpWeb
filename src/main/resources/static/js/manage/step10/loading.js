let step10Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step10Handler = new Step10Handler();
})

const func = {
    async driveReportsRetrieval() {
        await step10Handler.groupDriveReportsRetrieval();
    },
    async approveInBulk() {
        await step10Handler.modifyPaymentChkInBulk(true);
    },
    async cancelInBulk() {
        await step10Handler.modifyPaymentChkInBulk(false);
    }
}