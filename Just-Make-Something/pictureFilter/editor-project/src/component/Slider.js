import React from 'react'
import '../App.css';

function Slider({min, max, value, handleChange}) {
    const sliderValue = Number(((value - min) * 100) / (max - min));
    const valueStyle = {
        left: `calc(${sliderValue}% + (${8 - sliderValue * 1.145}px))`
    };

  return (
      <div className='slider-container'>
          <input
              type='range'
              className='slider'
              min={min}
              max={max}
              value={value}
              onChange={handleChange}
          />
          <output className='value' style={valueStyle}>{value}</output>
      </div>
  )
}

export default Slider