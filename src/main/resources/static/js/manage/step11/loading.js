let step11BaseHandler;
let step11GroupHandler;

const popUpHandler = new PopUpHandler();

document.addEventListener("DOMContentLoaded", () => {
    step11BaseHandler = new Step11BaseHandler();
    step11GroupHandler = new Step11GroupHandler();
})

const objHandler = new ObjectHandler();

const func = {
    async save() {
        await step11GroupHandler.save();
    },
    async submit() {
        objHandler.selectElementByName('groupSubmitChk').value = true;
        await step11GroupHandler.save();
    },
    async remove() {
        await step11GroupHandler.remove();
    },
    async groupDriveReportsRetrieval() {
        await step11GroupHandler.groupReportsRetrieval();
    },
    async driverListRetrievalForGroup() {
        await step11GroupHandler.receiverListRetrieval();
    },
    addGroupReport() {
        const res = step11BaseHandler.addDriveReport(step11GroupHandler.groupList);
        if (res) {
            return;
        }
        popUpHandler.closePopUp('drive-report');
        step11GroupHandler.htmlModifier
            .printList('group-table-key', 'group-table-tuple', step11GroupHandler.groupList, 'driveReportId');

        const tBodyEleChild = step11GroupHandler.objHandler.selectElementByClass('group-table-tuple').children;

        step11GroupHandler.groupList.forEach((data, idx) => {
            step11GroupHandler.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'idx', idx);
        })

        },
    removeGroupReport() {
        step11BaseHandler.removeDriveReport(step11GroupHandler.groupList);
        popUpHandler.closePopUp('drive-report');
        step11GroupHandler.htmlModifier
            .printList('group-table-key', 'group-table-tuple', step11GroupHandler.groupList, 'driveReportId');

        const tBodyEleChild = step11GroupHandler.objHandler.selectElementByClass('group-table-tuple').children;

        step11GroupHandler.groupList.forEach((data, idx) => {
            step11GroupHandler.htmlModifier.addDataPropertyToTag(tBodyEleChild[idx], 'idx', idx);
        })

    },
    openPopUpAndInit() {
        objHandler.selectElementByName('driveReportIdx').value = '-1';
        objHandler.selectElementByName('driveReportId').value = '0';
        popUpHandler.openPopUp('order-report');
    },
    async driverListRetrieval() {
        await step11BaseHandler.receiverListRetrieval();
    },
    async driveReportsRetrieval() {
        await step11BaseHandler.driveReportsRetrieval(step11GroupHandler.groupList);
    },

    uploadImageFile() {
        objHandler.selectElementByName('imageFile').click();
    }
}