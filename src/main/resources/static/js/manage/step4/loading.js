let manageHandler;
document.addEventListener("DOMContentLoaded", () => {
    manageHandler = new ManageHandler(paramContainer);
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
    },
    async test() {
        const responseData = await new RequestHandler()
            .get(defaultUrl + '/fetch/' + 'recommendKeyword');

        console.log(responseData);
    }
}