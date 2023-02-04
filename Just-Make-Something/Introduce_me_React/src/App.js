import React, {Component} from 'react';
import './App.css';
import profile from './image/profile2.jpg';
import github from './image/GitHub-Mark-120px-plus.png';
import insta from './image/glyph-logo_May2016.png';
import List from './component/List';
import {Helmet} from 'react-helmet';
import Mailto from 'react-protected-mailto';

function App() {
  const githuburl = "https://github.com/Hschan2";
  const instaurl = "https://www.instagram.com/hseongchan/";
  //const button = "<Button variant="contained" style={{backgroundColor:"#FF5675", color:"#ffffff"}}>버튼</Button>"
  return (
    <div className="App">
      <Helmet>
      <title>It's Me</title>
      </Helmet>
      <div className="Itsme">
        <img src={profile} className="Profile"/>
        <ul className="List">
          <li><h4>홍성찬</h4></li>
          <li className="mail">
          <Mailto
            className="mailto"
            email='seongchan_@naver.com'
            headers={
              {subject:'안녕하세요.'},
              {cc:'seongchan_@naver.com'}
          }/>
          </li>
          <li>
            <a href={githuburl} target="_blank"><button className="GithubBtn"><img src={github}/></button></a>
            <a href={instaurl} target="_blank"><button className="InstagramBtn"><img src={insta}/></button></a>
          </li>
        </ul>
      </div>
      <div className="MyProject"><List /></div>
    </div>
  );
}

export default App;
