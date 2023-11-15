let step7Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step7Handler = new Step7Handler();
})

const func = {
    async save() {
        await step7Handler.save();
    },
    async submit() {
        step7Handler.objHandler.selectElementByName('submitChk').value = true;
        await step7Handler.save();
    },
    async remove() {
        await step7Handler.remove();
    },
    async driveReportsRetrieval() {
        await step7Handler.driveReportsRetrieval();
    },
    async driverListRetrieval() {
        await step7Handler.receiverListRetrieval();
    }
}