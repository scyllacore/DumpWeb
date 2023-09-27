// 저장 버튼 클릭 이벤트 처리
//document.querySelector(".opBtn[data-popName='golPop3']").addEventListener("click", () => {
//  // 팝업 창을 띄우는 코드
//  const golPop3 = document.querySelector("#golPop3");
//  golPop3.style.display = "block";
//});

function clickSaveBtn(){
  const golPop = document.querySelector("#golPop3");
  golPop.style.display = "flex";
}

/* js에서 confirmBtn(확인)이 눌릴 수 있도록 처리 */
function clickConfirmButton(){
  // 팝업 창을 숨기는 코드
  const golPop3 = document.querySelector("#golPop3");
  golPop3.style.display = "none";
  bindList();
}

/* 선택한 옵션을 통해 데이터를 받아올 수 있도록 ajax POST 처리. */
function bindList() {
  $.ajax({
      url: "/dailyReport/ajax/orderform",
      type: "POST",
      data: $("[name=golForm]").serialize(),
      success: function (data) {
//          addTableRow(data);
          alert("저장이 완료되었습니다.");
      }
  })
}

function insertFirstHeader(arr,idx) {
  //삭제 -> 삽입하여 header td를 제일 앞으로 옮김.
  const firstHeader = arr.splice(idx,1)[0];
  arr.splice(0,0,firstHeader);
}

// 저장 버튼 클릭 이벤트를 처리합니다.
function addTableRow(tableBody, data, isUndefined, rowNumber) {
  const newRow = document.createElement("tr");

  if (isUndefined) {
    newRow.innerHTML = `
          <td>${rowNumberForUndefined}</td>
          <td>${data.loadPoint}</td>
          <td>${data.unloadPoint}</td>
          <td>${data.golItems}</td>
          <td>${data.golCount}</td>
          <td><button class="undefined-button">미지정</button></td>
      `;
  } else {
      newRow.innerHTML = `
          <td>${rowNumberForCarNumb}</td>
          <td>${data.loadPoint}</td>
          <td>${data.unloadPoint}</td>
          <td>${data.golItems}</td>
          <td>${data.golCount}</td>
          <td>${data.carNumb}</td>
      `;
  }

  let end = order[0];

  if (start !== end) {
      start = end;
      row.classList.add("red-line-divider")
  }

  tableBody.appendChild(row);
}

// 데이터를 가져와서 테이블에 표시
function fetchAndDisplayData() {
  let rowNumberForUndefined = 1; // 미지정인 경우의 rowNumber 초기화
  let rowNumberForCarNumb = 1; // 차량번호인 경우의 rowNumber 초기화
  datalist.forEach((data) => {
      if (!data.carNumb || data.carNumb === "미지정") {
          addTableRow(tableBody, data, true, rowNumberForUndefined);
          rowNumberForUndefined++; // 미지정인 경우의 rowNumber 증가
      } else {
          addTableRow(tableBody, data, false, rowNumberForCarNumb);
          rowNumberForCarNumb++; // 차량번호인 경우의 rowNumber 증가
      }
  });
}
//
//const checkbox = document.getElementById("chk1");
//
//// 체크박스 클릭 이벤트 리스너 추가
//checkbox.addEventListener("change", function () {
//    var resultElement = document.getElementById("result");
//    if (checkbox.checked) {
//        resultElement.textContent = "1";
//        console.log('1'); // 체크됐을 때 1 출력
//    } else {
//        resultElement.textContent = "0";
//        console.log('0'); // 체크 해제됐을 때 0 출력
//    }
//});