assignUserTypeText();

function assignUserTypeText() {
    const queryString = window.location.search;
    const params = new URLSearchParams(queryString);
    const paramUserType = params.get("type");

    const TypeTextTag = document.querySelector(".user-type-text");

    const userType = {
        driver: "기사",
        manager: "담당자"
    }

    TypeTextTag.innerHTML = userType[paramUserType];
    document.querySelector('input[name = "userType"]').value = paramUserType;
}

function openPersonalInfoNoticePopUp(page) {
    document.querySelector('.' + page).style.display = 'block';
}

function closePersonalInfoNoticePopUp(page) {
    document.querySelector('.' + page).style.display = 'none';
}

function join() {

    const joinForm = document.querySelector('form[name = "joinForm"]');
    const inputData = joinForm.querySelectorAll('input');

    for (let i = 1; i < inputData.length; i++) {

        if (i === 3) {
            continue;
        }

        const name = inputData[i].name;
        const value = inputData[i].value;
        const label = inputData[i].parentNode.children[0].innerText;

        if (value === '') {
            alert(label + "를 다시 확인해주세요.");
            document.querySelector(`input[name = "${name}"]`).focus();
            return;
        }
    }

    if (inputData[2].value !== inputData[3].value) {
        alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        document.querySelector('input[name = "userPwd"]').focus();
        return;
    }

    if(inputData[1].value.length < 4){
        alert("비밀번호는 4글자 이상 입력해주세요.");
        document.querySelector('input[name = "userPwd"]').focus();
    }

    if(document.querySelector('input[name = "privacyChk"]').checked === false){
        alert("개인 정보 수집 및 이용에 동의해주세요.");
        return;
    }


    $.ajax({
        url: "/join/ajax/join",
        type: "POST",
        data: $("form[name=joinForm]").serialize(),
        async: false,
        success: function (response) {
            if (response.status !== 200) {
                alert(response.data);
                return false;
            }

            alert("정상적으로 회원가입이 완료 되었습니다.");
            location.href = '/login';
        },
        error: function (e) {
            if (e.status == 408) {
                alert("Join keep timed out. \nPlease again.");
                location.href = "/";
            }
        }
    });

}
