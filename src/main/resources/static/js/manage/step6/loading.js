let manageHandler;
document.addEventListener("DOMContentLoaded", () =>{
    manageHandler = new ManageHandler(paramContainer);
})

const func = {
    async listRetrieval(){
       await manageHandler.listRetrieval('step6');
    },
    async approveInBulk(){
        await manageHandler.modifyPaymentChkInBulk('step6','approve');
    },
    async cancelInBulk(){
        await manageHandler.modifyPaymentChkInBulk('step6','cancel');
    }
}