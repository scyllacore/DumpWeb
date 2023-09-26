
if('SpeechRecongition' in window || 'webkitSpeechRecognition' in window){
  const SpeechRecongition = window.SpeechRecongition || window.webkitSpeechRecognition;

  const Images = document.querySelectorAll('.drvVoiceImg');
  const textInput = document.querySelectorAll('.voice-notification');

  Images.forEach((images,idx) => {
    images.addEventListener('click', () => {
      const recognition = new SpeechRecongition();

      recognition.onstart = () => {
        textInput[idx].value = '음성 입력 중...';
      };

      recognition.onresult = (event) => {
        const transcript = event.results[0][0].transcript;
        textInput[idx].value = transcript;
      };

      recognition.start();
    });
  })
}else{
  alert('이 브라우저는 음성인식을 지원하지 않습니다.')
}