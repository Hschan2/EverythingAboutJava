import React from "react";
import './Listvar.css';
import Button from '@material-ui/core/Button';

function List2() {
  return (
    <>
      <h1>One Image Diary</h1>
      <p>PHP, CSS, JAVASCRIPT, MYSQL의 기술을 이용하여
      한 장의 이미지를 이용한 다이어리 페이지를 제작하였습니다.</p>
      <div className="SourceLink">
        <a href="http://hschan2.dothome.co.kr" target="_blank"><Button>PAGE LINK</Button></a>
        <a href="https://github.com/Hschan2/simple_diary" target="_blank"><Button>GITHUB LINK</Button></a>
      </div>
    </>
  );
}

export default List2;
