if('SpeechRecongition' in window || 'webkitSpeechRecognition' in window){
      const SpeechRecongition = window.SpeechRecongition || window.webkitSpeechRecognition;

      const Images = document.querySelectorAll('.icon_mic');
      const textInput = document.querySelectorAll('.voice');

      Images.forEach((images,idx) => {
            images.addEventListener('click', () => {
                  const recognition = new SpeechRecongition();

                  recognition.onstart = () => {
                    textInput[idx].placeholder = '음성 입력 중...';
                  };

                  recognition.onresult = (event) => {
                    const transcript = event.results[0][0].transcript;
                    textInput[idx].value = transcript;
                  };

                  recognition.onspeechend = () => {
                      recognition.stop();
                    };

            // Set a timeout for 5 seconds
            setTimeout(() => {
                  recognition.stop();
                  textInput[idx].placeholder = '입력된 음성이 없습니다';
            }, 5000);

              recognition.start();
            });
      })
}else{
  alert('이 브라우저는 음성인식을 지원하지 않습니다.')
}