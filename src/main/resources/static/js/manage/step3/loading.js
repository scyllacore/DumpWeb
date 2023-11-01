let step3Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step3Handler = new Step3Handler();
})

const func = {
    async save() {
        await step3Handler.save();
    },
    async submit() {
        step3Handler.objHandler.selectElementByName('submitChk').value = true;
        await step3Handler.save();
    },
    async remove() {
        await step3Handler.remove();
    },
    async driveReportsRetrieval() {
        await step3Handler.driveReportsRetrieval();
    },
    async submitterListRetrieval() {
        await step3Handler.receiverListRetrieval();
    }
}