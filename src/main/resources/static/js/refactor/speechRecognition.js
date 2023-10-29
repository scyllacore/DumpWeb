class SpeechRecognition {

    objHandler = new ObjectHandler();

    constructor() {
        this.start();
    }

    start() {
        const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;

        if (SpeechRecognition === null) {
            alert('이 브라우저는 음성인식을 지원하지 않습니다.');
            return;
        }

        const micImages = this.objHandler.selectAllElementsByClass('mic-img');
        const micTexts = this.objHandler.selectAllElementsByClass('mic-text');

        const length = (micImages.length + micTexts.length) / 2;

        for (let i = 0; i < length; i++) {
            this.recognizeSpeech(micImages[i], micTexts[i]);
        }
    }

    recognizeSpeech(image, micText) {
        image.addEventListener('click', () => {
            const recognition = new SpeechRecognition();

            recognition.onstart = () => {
                micText.value = '음성 입력 중...';
            };

            recognition.onresult = (event) => {
                micText.value = event.results[0][0].transcript;
            };
            recognition.start();
        });
    }

}
