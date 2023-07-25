export const speakText = (e) => {
    e.preventDefault();
    const text = e.target.textContent;
    const voices = window.speechSynthesis.getVoices();
    let voice = null;

    for (let i = 0; i < voices.length; i++) {
        if (voices[i].name === 'Microsoft Zira Desktop - English (United States)' || voices[i].lang === 'en-US') {
            voice = voices[i];
            break;
        }
    }

    if (!voice) {
        console.error('Voice not found for speech synthesis.');
        return;
    }

    const utterance = new SpeechSynthesisUtterance(text);
    utterance.voice = voice;
    speechSynthesis.speak(utterance);
}