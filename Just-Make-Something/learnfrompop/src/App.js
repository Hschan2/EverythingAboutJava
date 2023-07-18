import './App.css';
import Top from './components/top';
import { kor, lyrics } from './constants/pop'

function App() {
  const getLyrics = lyrics.split('\n').map(v => v.trim()).filter(v => v.length > 0);
  const getKor = kor.split('\n').map(v => v.trim()).filter(v => v.length > 0);

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
      <Top />
      {getLyrics?.map((lyrics, index) => (
        <div key={index} className='container'>
          <p onClick={speakText} className="speak">{lyrics}</p>
          <p>{getKor[index]}</p>
        </div>
      ))}
    </div>
  );
}

export default App;
