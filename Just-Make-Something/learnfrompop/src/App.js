import './App.css';
import { lyrics } from './constants/pop'

function App() {
  const getLyrics = lyrics.split('\n').map(v => v.trim()).filter(v => v.length > 0);

  const speakText = (e) => {
    e.preventDefault();
    const text = e.target.textContent;
    const voices = window.speechSynthesis.getVoices();
    const utterance = new SpeechSynthesisUtterance(text);
    utterance.voice = voices[127];
    speechSynthesis.speak(utterance);
  }

  return (
    <div className="App">
      {getLyrics?.map((lyrics, index) => (
        <p key={index} onClick={speakText}>{lyrics}</p>
      ))}
    </div>
  );
}

export default App;
