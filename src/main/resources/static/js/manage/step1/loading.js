let step1Handler;
const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step1Handler = new Step1Handler();
})

const func = {
    async summaryRetrieval() {
        await step1Handler.summaryRetrieval();
    },
    async postingRetrieval(){
        await step1Handler.postingRetrieval();
    }
}