/* function :   open/close search popup */
function openPop() {
    document.getElementById('popup').style.display = 'flex';
}
function closePop() {
    document.getElementById('popup').style.display = 'none';
}
function openPopSearch() {
    document.getElementById('popSearch').style.display = 'flex';
}
function closePopSearch() {
    document.getElementById('popSearch').style.display = 'none';
}


/* function : 결재버튼을 통해서만 체크박스를 체크하거나 해제할 수 있다.  */
const dateDisplay = document.getElementById('dateDisplay');
const currentDate = document.getElementById('currentDate');
currentDate.textContent = '연도- 월- 일';

function checkBox() {
     const checkbox = document.getElementById('checkbox');
     checkbox.checked = !checkbox.checked;

     if (checkbox.checked) {
         const today = new Date();
         const year = today.getFullYear();
         const month = String(today.getMonth() + 1).padStart(2, '0');
         const day = String(today.getDate()).padStart(2, '0');
         currentDate.textContent = year+'-'+month+'-'+day;
         checkbox.disabled = true;
     } else {
         currentDate.textContent = '연도- 월- 일';
     }
}

/* function : onfocus시 자동으로 010을 채워준다*/
function fill010() {
    const telInput = document.getElementById('carSubmitTel');
    telInput.value = "010";
}


/* function: -,+로 날자 조정 */
const dateInput = document.getElementById('date');

function prevday(){
    const currentDate = new Date(dateInput.value);
    currentDate.setDate(currentDate.getDate() - 1);
    const formattedDate = currentDate.toISOString().split('T')[0];
    dateInput.value = formattedDate;
}

function nextday(){
    const currentDate = new Date(dateInput.value);
    currentDate.setDate(currentDate.getDate() + 1);
    const formattedDate = currentDate.toISOString().split('T')[0];
    dateInput.value = formattedDate;
}