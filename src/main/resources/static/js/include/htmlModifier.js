class HtmlModifier {

    objHandler = new ObjectHandler();

    moveColumnToTheFront(movingTh) {
        const ChildTh = this.objHandler.selectElementByClass(movingTh);
        const parentTh = ChildTh.parentNode;
        parentTh.insertBefore(ChildTh, parentTh.firstChild);
    }

    printList(tBodyClass, listData) {
        const tBodyEle = this.objHandler.selectElementByClass(tBodyClass);
        tBodyEle.innerHTML = "";

        listData.forEach((data, no) => {
            const row = document.createElement("tr");
            const childTd = tBodyEle.children;

            row.innerHTML += `<td>` + no + `</td>`

            for (let i = 0; i < childTd.length; i++) {
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

    searchList(listData, className) {
        const searchEle = this.objHandler.selectElementByClass(className);
        const inputEle = searchEle.querySelector('input');

        inputEle.addEventListener('click', (event) => {
            this.printSearchListToDiv(listData, inputEle);
        });

        inputEle.addEventListener('keyup', (event) => {
            const matchDataList = listData.filter((data) => data.includes(inputEle.value));
            this.printSearchListToDiv(matchDataList, inputEle);
        });
    }

    printSearchListToDiv(listData, inputEle) {
        const ele = inputEle.parentElement.querySelector('div');
        ele.innerHTML = ``;
        ele.style.display = 'block';

        const key = ele.getAttribute('name');
        for (const val of listData[key]) {
            ele.innerHTML += `<option>` + val + `</option>`
        }
    }

}
