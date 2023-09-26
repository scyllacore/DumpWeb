flatpickr("#startDate", {
  enableTime: false,
  dateFormat: "Y-m-d",
  monthSelectorType: "static",
  altInput: true,
  altFormat: "시작 날짜를 선택하세요",
  locale: {
    months: {
      shorthand: [
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
      ],
      longhand: [
        "1월",
        "2월",
        "3월",
        "4월",
        "5월",
        "6월",
        "7월",
        "8월",
        "9월",
        "10월",
        "11월",
        "12월",
      ],
    },
  },
  onChange: function (selectedDates, dateStr, instance) {
    // 날짜가 선택될 때 실행되는 함수
    if (dateStr) {
      instance.altInput.value = dateStr; // 선택된 날짜를 altInput에 표시
    }
  },
});

flatpickr("#endDate", {
  enableTime: false,
  dateFormat: "Y-m-d",
  monthSelectorType: "static",
  altInput: true,
  altFormat: "종료 날짜를 선택하세요",
  locale: {
    months: {
      shorthand: [
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
      ],
      longhand: [
        "1월",
        "2월",
        "3월",
        "4월",
        "5월",
        "6월",
        "7월",
        "8월",
        "9월",
        "10월",
        "11월",
        "12월",
      ],
    },
  },
  onChange: function (selectedDates, dateStr, instance) {
    // 날짜가 선택될 때 실행되는 함수
    if (dateStr) {
      instance.altInput.value = dateStr; // 선택된 날짜를 altInput에 표시
    }
  },
});

// 검색 버튼 요소를 가져옵니다.
const searchButton = document.querySelector(".search_btn");

// 검색 버튼 클릭 이벤트를 처리합니다.
searchButton.addEventListener("click", () => {
  // 검색 결과 데이터 (가상 데이터로 가정)
  const searchResultData = [
    {
      차량번호: "ABC 123",
      품목: "박스",
      상차지: "출발지 A",
      하차지: "도착지 B",
      시간: "2023-08-30 10:00",
      대수: 2,
      진행: "진행 중",
    },
    {
      차량번호: "XYZ 789",
      품목: "가전제품",
      상차지: "출발지 X",
      하차지: "도착지 Y",
      시간: "2023-08-30 13:30",
      대수: 5,
      진행: "완료",
    },
    {
      차량번호: "XYZ 789",
      품목: "가전제품",
      상차지: "출발지 X",
      하차지: "도착지 Y",
      시간: "2023-08-30 13:30",
      대수: 1,
      진행: "완료",
    },
  ];

  // 테이블 본문 요소
  const tableBody = document.querySelector("table tbody");

  // 테이블 본문 내용 초기화
  tableBody.innerHTML = "";

  // 검색 결과 데이터를 테이블 본문에 추가합니다.
  searchResultData.forEach((data, index) => {
    const row = document.createElement("tr");
    row.innerHTML = `
    <td>${index + 1}</td>
    <td>${data.차량번호}</td>
    <td>${data.품목}</td>
    <td>${data.시간}</td>
    <td>${data.진행}</td>
  `;
    tableBody.appendChild(row);
  });

  // 검색 결과 텍스트 생성
  const dataCount = searchResultData.length;
  const totalCount = searchResultData.reduce(
    (total, data) => total + data.대수,
    0
  );
  const resultText = `데이터 <span class="blue">${dataCount}</span>건 (총대수: <span class="blue">${totalCount}</span>대)가 검색되었습니다.`;

  // 결과를 result_search 요소에 출력
  const resultSearch = document.querySelector(".result_search");
  resultSearch.innerHTML = `<h1>${resultText}</h1>`;
});

// 일괄취소 버튼 요소를 가져옵니다.
const cancelButton = document.querySelector(".common_btn:nth-of-type(2)");

// 일괄취소 버튼 클릭 이벤트를 처리합니다.
cancelButton.addEventListener("click", () => {
  // 테이블 본문 요소를 찾습니다.
  const tableBody = document.querySelector("table tbody");

  // 테이블 본문 내용을 초기화합니다. (데이터 삭제)
  tableBody.innerHTML = "";

  // 검색 결과 텍스트 초기화
  const resultSearch = document.querySelector(".result_search");
  resultSearch.innerHTML = "";
});
