let manageHandler;
document.addEventListener("DOMContentLoaded", () => {
    manageHandler = new ManageHandler(paramsContainer);
})

const func = {
    async listRetrieval() {
        await manageHandler.listRetrieval('step8');
    },
    async approveInBulk() {
        await manageHandler.modifyPaymentChkInBulk('step8', 'approve');
    },
    async cancelInBulk() {
        await manageHandler.modifyPaymentChkInBulk('step8', 'cancel');
    }
}