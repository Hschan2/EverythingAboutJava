export const speakText = (e) => {
    e.preventDefault();
    const text = e.target.textContent;
    const voices = window.speechSynthesis.getVoices();
    const utterance = new SpeechSynthesisUtterance(text);
    utterance.voice = voices[127];
    speechSynthesis.speak(utterance);
}