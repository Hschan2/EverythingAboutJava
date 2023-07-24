import React from 'react'
import { kor, lyrics } from '../constants/pop';
import { speakText } from '../constants/SpeakVoice';

const GetLyrics = () => {
    const getLyrics = lyrics.split('\n').map(v => v.trim()).filter(v => v.length > 0);

    return (
        <div className='lyrics'>
            {getLyrics?.map((lyrics, index) => (
                <div key={index} className='container'>
                    <p onClick={speakText} className="speak">{lyrics}</p>
                </div>
            ))}
        </div>
    )
}

export default GetLyrics