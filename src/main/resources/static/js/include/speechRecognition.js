//함수 호출.
changeSpeechToText();

//함수 선언.
function changeSpeechToText(){

  const SpeechRecongition = window.SpeechRecongition || window.webkitSpeechRecognition;

  if(SpeechRecongition === null)
  {
    alert('이 브라우저는 음성인식을 지원하지 않습니다.');
    return;
  }

  const micImages = document.querySelectorAll('.mic-img');
  const micText = document.querySelectorAll('.mic-text');

  micImages.forEach((image,idx) => {
    image.addEventListener('click', () => {
      const recognition = new SpeechRecongition();

      recognition.onstart = () => {
        micText[idx].value = '음성 입력 중...';
      };

      recognition.onresult = (event) => {
        const transcript = event.results[0][0].transcript;
        micText[idx].value = transcript;
      };
      recognition.start();
    });
  })

}