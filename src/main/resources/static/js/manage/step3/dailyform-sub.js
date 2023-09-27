const canvas = document.getElementById("canvas");
var openable1 = false;
var openable2 = false;
var openable3 = false;
var openable4 = true; //기본적으로 오늘 날자를 세팅해 놓으므로 true로 둠


/* function : 결재버튼을 통해서만 체크박스를 체크하거나 해제할 수 있다.  */
/* function: 오늘 날자로 인풋 자동 채우기 */
const dateInput = document.getElementById('date');
const todayDate = new Date();
dateInput.value = todayDate.toISOString().slice(0, 10);


/* function : onfocus시 자동으로 010을 채워준다*/
var phoneNumberPattern = /^010[0-9]{8}$/;
function fill010() {
    const telInput = document.getElementById('carSubmitTel');
    if (phoneNumberPattern.test(telInput.value)) {
        openable3 = true;
    } if(telInput.value.length <=3) {
        telInput.value = "010";
    }
}

function validateInput1(input) {
    if(!input.value) {
        openable1 = false;
    } else {
        openable1 = true;
        listData()
    }
}

function validateInput2(input) {
    if(!input.value) {
        openable2 = false;
    } else {
        openable2 = true;
        listData()
    }
}

/* function : oninput 인풋이 바르지 않으면 보더컬러를 red로 바꿈 */
function validateInput3(input) {
    if (phoneNumberPattern.test(input.value)) {
        input.style.borderColor = '';
        openable3 = true;
        listData()
    } else {
        input.style.borderColor = 'red';
        openable3 = false;
    }
}




/* function : open/close popup */
const popup = document.getElementById('popup');
function openPop() {
    if(dateInput === '') { // 데이트 기록이 없으면
        openable4 = false;
    } else {
        openable4 = true;
        listData()
    }
    if(openable1 & openable2& openable3 & openable4 === true) {
        popup.style.display = 'flex';
        saved.forEach(function(elem){
            elem.classList.add('hidden');
        })
        unsaved.forEach(function(elem) {
            initialize(elem)
        });
    } else {
        alert("입력된 정보를 다시 확인해주세요");
    }
}

const saved = popup.querySelectorAll('.saved')
const unsaved = popup.querySelectorAll('.unsaved') //신규저장

function initialize(element) {
    if (element.classList.contains('hidden')) {
        element.classList.remove('hidden');
    }
}

function closePop() {
    $.emptyRow();
    popup.style.display = 'none';
    saved.forEach(function(elem) {
        initialize(elem)
    });
    unsaved.forEach(function(elem) {
        initialize(elem)
    });
}




/* 비고 사이즈 자동변경 */
const freetext = document.getElementById('Rem');
freetext.oninput = event => {
    const inputValue = event.target;
    freetext.style.height = 0
    freetext.style.height = 22 + inputValue.scrollHeight + 'px';
};


/* 대수, 운반단가로 합계보여주기 */
    //<!-- "대수"와 "운반 단가" 입력 요소 가져오기 -->
const QtyInput = document.getElementById('Qty');
const QtyupInput = document.getElementById('Qtyup');
const totalAmountInput = document.getElementById('totalAmount');

    //<!-- 입력 값이 변경될 때마다 합계 업데이트 함수 호출 -->
QtyInput.addEventListener('input', updateTotalAmount);
QtyupInput.addEventListener('input', updateTotalAmount);

    //<!-- 합계 업데이트 함수 -->
function updateTotalAmount() {
    <!-- "대수"와 "운반 단가" 값을 가져옴 -->
    const Qty = parseFloat(QtyInput.value);
    const unitPrice = parseFloat(QtyupInput.value);

    //<!--값이 유효한 경우에만 합계 계산 및 표시-->
    if (!isNaN(Qty) && !isNaN(unitPrice)) {
        <!-- "대수"와 "운반 단가"를 곱하여 소수점 이하 2자리까지 표시 -->
        totalAmountInput.value = Qty * unitPrice;
    } else {
        //<!-- 값이 유효하지 않은 경우 합계 입력 상자를 비움 -->
        totalAmountInput.value = '';
    }
}

/* 운송 신규등록 금액부분 보여주거나 숨기기 */
const popCheckbox = document.getElementById("showHideCheckbox");
const hiddenPart = document.getElementById("hiddenPart");
const checkboxLabel = document.getElementById("checkboxLabel");

popCheckbox.addEventListener("change", function() {
    if (popCheckbox.checked) {
        hiddenPart.style.display = "block";
        checkboxLabel.style.color = "#333";
    } else {
        hiddenPart.style.display = "none";
        checkboxLabel.style.color = "#aaa";
    }
});

/* 전체 삭제 버튼 누르면 인풋 비우기 */
function clearInputs() {
    const inputs = canvas.getElementsByTagName("input");
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].value = ""; // Set the value of each input field to an empty string
    }
}


/* 결재 체크박스 체크되면 밸류 바꾸기 */
/* 결재 체크박스 체크되면 인풋 수정 불가 */

function approved() {
    const chk = document.getElementById('checkbox');
    const mtable = document.getElementById('main-table');
    const inputElements = mtable.querySelectorAll('.input');

    if (chk.checked) {
        chk.value = '1';
        console.log(chk.value);

        // 거래처정보 인풋 비활성화
        inputElements.forEach(function(input) {
            input.disabled = true;
            input.style.backgroundColor = "#F2F2F2";
            input.style.color = "333"
        });
    } else {
        chk.value = '0';
        console.log(chk.value);

        // 결재 취소하면 다시 활성화
        inputElements.forEach(function(input) {
            input.disabled = false;
            input.style.backgroundColor = "#fff";
        });
    }
}




/* 제출하기 버튼을 클릭하면 결재 체크되고 제출체크가 체크하면되 결재도 체크됨*/
function submitCheck() {
    const dropdown = document.getElementById('dropdown')
    const chk1 = document.getElementById('checkbox');
    //chk1.disabled = true;
    chk1.checked = true;
    dropdown.textContent = "제출";
    dropdown.style.color = "#0068b7";
    dropdown.style.border = "1px solid #0068b778";
    approved();
}


/* fillPop으로 인풋팝업이 뜰때는 버튼이 바뀌어야 한다. */
const filledInput = document.querySelectorAll('.filledInput');
const newInput = document.querySelectorAll('.emptyInput');
function fillPop(event) {
    var clicked = event.currentTarget.id;
    const clickedRow = document.getElementById(clicked);
    var td1 = clickedRow.querySelector('td:nth-child(1)').textContent;
    var td2 = clickedRow.querySelector('td:nth-child(2)').textContent;
    var td3 = clickedRow.querySelector('td:nth-child(3)').textContent;
    var td4 = clickedRow.querySelector('td:nth-child(4)').textContent;
    var td5 = clickedRow.querySelector('td:nth-child(5)').textContent;
    var td6 = clickedRow.querySelector('td:nth-child(6)').textContent;
    var td7 = clickedRow.querySelector('td:nth-child(7)').textContent;

    // Fill the input fields in the popup with these values
    document.getElementById('fromsite').value = td1;
    document.getElementById('tosite').value = td2;
    document.getElementById('item').value = td3;
    document.getElementById('Qty').value = td4;
    document.getElementById('Rem').value = td5;
    document.getElementById('sheetsubID').value = td6;
    document.getElementById('Qtyup').value = td7;

    // Open the popup
    openPop();
    unsaved.forEach(function(elem){
        elem.classList.add('hidden');
    })
    saved.forEach(function(elem) {
        initialize(elem)
    });
}


function listData() {
    if(openable1 & openable2& openable3 & openable4 === true) {
        $.list();
    }
}


