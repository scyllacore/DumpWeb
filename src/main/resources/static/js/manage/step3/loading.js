let manageHandler;

document.addEventListener("DOMContentLoaded", () => {
    manageHandler = new ManageHandler(paramsContainer);
})

const func = {
    async save() {
        await manageHandler.save('step3');
    },
    async remove() {
        await manageHandler.remove('step3')
    },
    async listRetrieval() {
        await manageHandler.listRetrieval('step3')
    },
    async submitterListRetrieval() {
        await manageHandler.submitterListRetrieval('step3Submitter')
    }
}