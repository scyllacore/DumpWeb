//Push Message 수신 이벤트
self.addEventListener('push', function (event) {
	// console.log('[ServiceWorker] 푸시알림 수신: ', event);

	var bodyAry = event.data.json().notification.body.split("{!%@}");

	//Push 정보 조회
	var title = event.data.json().notification.title || '배차 발행';
	var body = bodyAry[0];
	var icon = event.data.icon || '/Images/icon.png'; //512x512
	var badge = event.data.badge || '/Images/badge.png'; //128x128
	var options = {
		body: body,
		icon: icon,
		badge: badge,
		data : {
			token : bodyAry[1],
			carNo : bodyAry[2]
		},
	};

	//Notification 출력
	event.waitUntil(self.registration.showNotification(title, options));
});

//사용자가 Notification을 클릭했을 때
self.addEventListener('notificationclick', function (event) {
	// console.log('[ServiceWorker] 푸시알림 클릭: ', event);

	event.notification.close();

	var token = event.notification.data.token;
	var carNo = event.notification.data.carNo;

	event.waitUntil(
		clients.matchAll({ includeUncontrolled : true, type: "window" })
			.then(function (clientList) {
				/*//실행된 브라우저가 있으면 Focus
				for (var i = 0; i < clientList.length; i++) {

					var client = clientList[i];
					client.url = "/autoLogin";
					return client.focus();
					/!*if (client.url.indexOf('/') > -1) {
						return client.focus();
					}*!/
				}
				//실행된 브라우저가 없으면 Open
				if (clients.openWindow) {
					return clients.openWindow('/autoLogin?token=' + token)
				}*/

				return clients.openWindow('/login/ajax/autoLogin?token=' + token + "&carNo=" + carNo)
			})
	);
});

// console.log('[ServiceWorker] 시작');
