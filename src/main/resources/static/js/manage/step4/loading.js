let manageHandler;
document.addEventListener("DOMContentLoaded", () => {
    manageHandler = new ManageHandler(paramsContainer);
})

const func = {
    async listRetrieval() {
        await manageHandler.listRetrieval('step4');
    },
    async approveInBulk() {
        await manageHandler.modifyPaymentChkInBulk('step4', 'approve');
    },
    async cancelInBulk() {
        await manageHandler.modifyPaymentChkInBulk('step4', 'cancel');
    }
}