getUserIdIDX();
function getUserIdIDX(){
    document.querySelector('input[name="userIdIDX"]').value = userIdIDX;
}

function changePassword(){

    const passwordChangeForm = document.querySelector('form[name = "passwordChangeForm"]');
    const inputData = passwordChangeForm.querySelectorAll('input');

    if (inputData[1].value !== inputData[2].value) {
        alert("비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        document.querySelector('input[name = "userPwd"]').focus();
        return;
    }

    if(inputData[1].value.length < 4){
        alert("비밀번호는 4글자 이상 입력해주세요.");
        document.querySelector('input[name = "userPwd"]').focus();
    }

    $.ajax({
        url: "/passwordChange/ajax/changePassword",
        type: "POST",
        data: $("form[name=passwordChangeForm]").serialize(),
        async: false,
        success: function (response) {
            if (response.status !== 200) {
                alert(response.data);
                return false;
            }

            alert("정상적으로 비밀번호가 변경 되었습니다.");
            location.href = '/login';
        },
        error: function (e) {
            if (e.status == 408) {
                alert("change keep timed out. \nPlease again.");
                location.href = "/";
            }
        }
    });
}