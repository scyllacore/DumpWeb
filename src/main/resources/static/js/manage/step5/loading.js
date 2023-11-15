let step5Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step5Handler = new Step5Handler();
})

const func = {
    async save() {
        await step5Handler.save();
    },
    async remove() {
        await step5Handler.remove();
    },
    async mileageRetrieval() {
        await step5Handler.mileageRetrieval();
    }
}