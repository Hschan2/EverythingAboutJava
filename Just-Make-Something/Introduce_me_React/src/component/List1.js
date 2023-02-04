import React from "react";
import './Listvar.css';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Button from '@material-ui/core/Button';


function List1() {
  return (
    <>
      <h1>Introduce Page</h1>
      <p>HTML, PHP, CSS, JAVASCRIPT, MYSQL의 기술을 이용하여
         저의 프로필을 소개하는 홈페이지를 제작하였습니다.</p>
      <div className="SourceLink">
        <a href="http://hseongchan2.dothome.co.kr" target="_blank"><Button>PAGE LINK</Button></a>
        <a href="https://github.com/Hschan2/INTRODUCE_PAGE_WITH_PHP" target="_blank"><Button>GITHUB LINK</Button></a>
      </div>
    </>
  );
}

export default List1;
