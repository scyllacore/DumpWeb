var fcmToken = "";

$(document).ready(function() {
    var select_title = $(".b-selectCustomWrap > a");
    $('<div class="select_icon"></div>').insertAfter(select_title);

    select_title.click(function () {
        $(".b-selectCustom").toggleClass("active");
        $(".select_icon").toggleClass("active");
    });

    $(".b-selectCustom > li").on('click', function () {
        var _li_value = $(this).html();
        select_title.html(_li_value);
        $(".b-selectCustom").removeClass("active");
        $(".select_icon").toggleClass("active");

        $("[name=userSS]").val($(this).attr("select-data"));
    });
    $("body").click(function (e) {
        if($(".b-selectCustom").hasClass("active")){
            if(!$(".b-selectCustomWrap").has(e.target).length){
                $(".b-selectCustom").removeClass("active");
                $(".select_icon").removeClass("active");
            };
        }
    })

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(pos) {
            latitude = pos.coords.latitude;
            longitude = pos.coords.longitude;

            var geocoder = new kakao.maps.services.Geocoder();
            geocoder.coord2Address(longitude, latitude, function (result, status) {
                adrress = result[0].address.address_name;
                $("[name=address]").val(adrress)
            });
        });
    }


    var save = getCookie("saveDailyReportAccount");

    console.log(save.length);
    console.log(save);
    if (save.length > 0) {
        if (save == 'true') {
            console.log(1);
                var id = getCookie("dailyReportId");
                var pw = localStorage.getItem("dailyReportPw");

                $("[name=userId]").val(id);
                $("[name=userPw]").val(pw);
                $("[name=id_save]").attr("checked", true);

        } else {
            console.log(0);
            $("[name=id_save]").attr("checked", false);

        }
    } else {
        $("[name=id_save]").attr("checked", true);
    }




    // LINE :: FCM Token 설정
    // $.fcmTokenSetting()
});

// FUNCTION :: 알림 허용 확인
// $.fcmTokenSetting = function(){
//     var rtnResult = false;
//     var isNotificationSupported = 'Notification' in window;
//     if (isNotificationSupported) {
//         Notification.requestPermission().then(function (result) {
//             if (result === 'granted') {
//                 // console.log('[Notification] 허용: ', result);
//
//                 var isServiceWorkerSupported = 'serviceWorker' in navigator;
//                 if (isServiceWorkerSupported) {
//                     //브라우저에 Service Worker를 등록
//                     navigator.serviceWorker.register('/js/service-worker/service-worker.js', { scopre :'/'})
//                         .then(function(registration) {
//                             // console.log('[ServiceWorker] 등록 성공: ', registration.scope);
//
//                             // LINE :: Firebase 초기화 및 토큰 설정
//                             initFirebase(registration);
//                         })
//                         .catch(function(err) {
//                              console.log('[ServiceWorker] 등록 실패: ', err);
//                         });
//                 }
//             }
//             else {
//                 // console.log('[Notification] 차단: ', result);
//             }
//         });
//     }
// }
//
// // FUNCTION :: Firebase 초기화 및 토큰 설정
// function initFirebase(serviceWorkRegistration) {
//     // Your web app's Firebase configuration
//     // For Firebase JS SDK v7.20.0 and later, measurementId is optional
//     var firebaseConfig = {
//         apiKey: "AIzaSyAmioCvA8P-ir4OP2ToZcWupwNONWP7ya4",
//         authDomain: "testmyproject-463e9.firebaseapp.com",
//         projectId: "testmyproject-463e9",
//         storageBucket: "testmyproject-463e9.appspot.com",
//         messagingSenderId: "240000012907",
//         appId: "1:240000012907:web:ffd6595ee61a7a65b9972e",
//         measurementId: "G-RWSEJVDT12"
//     };
//     // Initialize Firebase
//     firebase.initializeApp(firebaseConfig);
//     firebase.analytics();
//
//     //Messaging 서비스 활성화
//     var messaging = firebase.messaging();
//     messaging.useServiceWorker(serviceWorkRegistration);
//     messaging.usePublicVapidKey("BACVvsMBCPhumn6HvzTUssA-E2dizA9w3HsNvo1l6uPSyo9KE4uMOp3CEgVHYSk2cG48NuUrqDvyioMaI_kfuu4");
//
//     //Instance ID Token 발급 요청
//     messaging.getToken()
//         .then((currentToken) => {
//             if (currentToken) {
//                 // console.log('[InstanceID Token] 발행완료: ', currentToken);
//                 fcmToken = currentToken;
//             } else {
//                 // console.log('[InstanceID Token] 발행실패');
//             }
//         })
//         .catch((err) => {
//             // console.log('[InstanceID Token] 발행오류: ', err);
//         });
//
//     //Instance ID Token 변경 시 호출되는 이벤트
//     messaging.onTokenRefresh(() => {
//         messaging.getToken()
//             .then((refreshedToken) => {
//                 console.log('[InstanceID Token] 갱신완료', refreshedToken);
//             })
//             .catch((err) => {
//                 console.log('[InstanceID Token] 갱신실패', err);
//             });
//     });
//
//     messaging.onMessage((payload) => {
//         //Push Message 수신 시 호출되는 이벤트
//        //  console.log('[PushMessage] 수신: ', payload);
//     });
// }

$.loginCheck = function() {
    if (document.loginfrm.userId.value.length == 0) {
        modal({
            title: '알림메세지',
            type: 'alert',
            text: '아이디를 입력하세요',
            callback : function(){
                document.loginfrm.userId.focus();
            }
        });
        return false;
    } else if (document.loginfrm.userPw.value.length == 0) {
        modal({
            title: '알림메세지',
            type: 'alert',
            text: '비밀번호를 입력하세요',
            callback : function(){
                document.loginfrm.userPw.focus();
            }
        });
        return false;
    }

    $("input[name=regid]").val(fcmToken);

    $.ajax({
        url : "/ajax/login",
        type : "POST",
        data : $("form[name=loginfrm]").serialize(),
        async : false,
        beforeSend : function(xmlHttpRequest){
            xmlHttpRequest.setRequestHeader("AJAX", "Use");
        },
        success : function(data) {
            var json = $.parseJSON(data);

            if(json.httpCode == 200){
                if($("[name=id_save]:checked").val() != undefined){
                    setCookie("dailyReportId",  $("[name=userId]").val(), 365);
                    setCookie("saveDailyReportAccount", true, 365);
                    localStorage.setItem("dailyReportPw",  $("[name=userPw]").val(), 365);
                    // setCookie("dispatchManageSS",  $("[name=userSS]").val(), 365);
                }else{
                    setCookie("saveDailyReportAccount", false, 365);
                    deleteCookie("dispatchManageId");
                    localStorage.removeItem("dispatchManagePw");
                    // deleteCookie("dispatchManageSS");
                }

                var isNotificationSupported = 'Notification' in window;
                if (isNotificationSupported) {
                    $.ajax({
                        url: "/ajax/saveToken",
                        type: "POST",
                        data: $("form[name=loginfrm]").serialize(),
                        async: false,
                        beforeSend: function (xmlHttpRequest) {
                            xmlHttpRequest.setRequestHeader("AJAX", "Use");
                        },
                        success: function (data) {},
                        error: function (e) {
                            if (e.status == 408) {
                                alert("Login keep timed out. \nPlease log in again.");
                                location.href = "/";
                            }
                        }
                    });
                }

                if ($('[name=userPw]').val() == "123") {
                    modal({
                        title: '알림메세지',
                        type: 'alert',
                        text: '비밀번호를 변경해야합니다.',
                        callback : function(){
                            location.href = "/pwChange";
                        }
                    });
                } else {
                    location.replace(json.rtnUrl);
                }

            }else{
                modal({
                    title: '알림메세지',
                    type: 'alert',
                    text: json.message,
                });
            }
        },
        error : function(e) {
            if(e.status == 408){
                alert("Login keep timed out. \nPlease log in again.");
                location.href = "/";
            }
        }
    });
}

// 쿠키 저장
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString() + "; path=/login");
    document.cookie = cookieName + "=" + cookieValue;
}

// 쿠키 삭제
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString() + "; path=/login";
}

// 쿠키 가져오기
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

function moveJoin() {
    location.href="/join";
}