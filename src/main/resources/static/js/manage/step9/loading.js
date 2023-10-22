let manageHandler;

document.addEventListener("DOMContentLoaded", () => {
    manageHandler = new ManageHandler(paramsContainer);
})

const func = {
    async save() {
        await manageHandler.save('step9', false, {driveReports: manageHandler.groupList});
    },
    async submit() {
        await manageHandler.save('step9', true, {driveReports: manageHandler.groupList});
    },
    async remove() {
        await manageHandler.remove('step9')
    },
    async listRetrieval() {
        await manageHandler.listRetrieval('step9')
    },
    async submitterListRetrieval() {
        await manageHandler.receiverListRetrieval('step9Submitter')
    },
    addGroupReport() {
        manageHandler.addGroupReport();
    },
    removeGroupReport() {
        manageHandler.removeGroupReport();
    },
    openPopUpAndInit() {
        document.querySelector('[name="groupReportIdx"]').value = '-1';
        openPopUp('drive-report');
    }
}