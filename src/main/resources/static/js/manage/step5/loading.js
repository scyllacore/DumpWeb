let manageHandler;
document.addEventListener("DOMContentLoaded", () => {
    manageHandler = new ManageHandler(paramsContainer);
})

const func = {
    async save() {
        await manageHandler.save('step5');
    },
    async remove() {
        await manageHandler.remove('step5')
    },
    async listRetrieval() {
        await manageHandler.listRetrieval('step5')
    }
}