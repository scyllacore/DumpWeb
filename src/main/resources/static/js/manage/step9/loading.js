let step9BaseHandler;
let step9GroupHandler;

const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step9BaseHandler = new Step9BaseHandler();
    step9GroupHandler = new Step9GroupHandler();
})

const objHandler = new ObjectHandler();

const func = {
    async save() {
        await step9GroupHandler.save();
    },
    async submit() {
        objHandler.selectElementByName('groupSubmitChk').value = true;
        await step9GroupHandler.save();
    },
    async remove() {
        await step9GroupHandler.remove();
    },
    async groupDriveReportsRetrieval() {
        await step9GroupHandler.groupReportsRetrieval();
    },
    async submitterListRetrievalForGroup() {
        await step9GroupHandler.receiverListRetrieval();
    },
    addGroupReport() {
        const res = step9BaseHandler.addDriveReport(step9GroupHandler.groupList);
        if (res) {
            return;
        }
        popUpHandler.closePopUp('drive-report');
        step9GroupHandler.htmlModifier
            .printList('group-table-key', 'group-table-tuple', step9GroupHandler.groupList);
    },
    removeGroupReport() {
        step9BaseHandler.removeDriveReport(step9GroupHandler.groupList);
        popUpHandler.closePopUp('drive-report');
        step9GroupHandler.htmlModifier
            .printList('group-table-key', 'group-table-tuple', step9GroupHandler.groupList);
    },
    openPopUpAndInit() {
        objHandler.selectElementByName('driveReportIdx').value = '-1';
        objHandler.selectElementByName('driveReportId').value = '0';
        popUpHandler.openPopUp('drive-report');
    },
    async submitterListRetrieval() {
        await step9BaseHandler.receiverListRetrieval();
    },
    async driveReportsRetrieval() {
        await step9BaseHandler.driveReportsRetrieval(step9GroupHandler.groupList);
    }
}