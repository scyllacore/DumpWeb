class HtmlModifier {

    objHandler = new ObjectHandler();

    moveColumnToTheFront(movingTh) {
        const ChildTh = this.objHandler.selectElementByClass(movingTh);
        const parentTh = ChildTh.parentNode;
        parentTh.insertBefore(ChildTh, parentTh.firstChild);
    }

    printList(tHeadClass, tBodyClass, listData) {
        const tBodyEle = this.objHandler.selectElementByClass(tBodyClass);
        const tHeadEle = this.objHandler.selectElementByClass(tHeadClass);

        tBodyEle.innerHTML = "";
        listData.forEach((data, no) => {
            const row = document.createElement("tr");
            const childTd = tHeadEle.children;

            row.innerHTML += `<td>` + (no + 1) + `</td>`

            for (let i = 1; i < childTd.length; i++) {
                row.innerHTML += `<td>` + data[childTd[i].className] + `</td>`
            }

            tBodyEle.appendChild(row);
        });
    }

    addRedLineToTableByDifferentValue(tBodyClass) {
        const tBodyEle = this.objHandler.selectElementByClass(tBodyClass);

        const childTrs = tBodyEle.children;
        let start = childTrs[0].children[0];

        for (let i = 1; i < childTrs.length; i++) {
            let end = childTrs[i].children[0];

            if (start.innerHTML !== end.innerHTML) {
                end.classList.add("red-line-divider");
                start = end;
            }
        }
    }

    addDataPropertyToTag(ele, name, val) {
        ele.setAttribute('data-' + name, val);
    }

}
