import React from "react";
import './Listvar.css';
import Button from '@material-ui/core/Button';

function List3() {
  return (
    <>
      <h1>졸업작품</h1>
      <p>대학교 졸업작품으로 데이터 베이스를 이용하여 서로 조건이 비슷한 
        아르바이트생과 기업을 연결하는 프로젝트를 제작하였습니다.</p>
      <div className="SourceLink">
        <a href="http://ci2016pocit.dongyangmirae.kr/index.jsp" target="_blank"><Button>PAGE LINK</Button></a>
        <a href="https://github.com/Hschan2/Project_For_Graduate" target="_blank"><Button>GITHUB LINK</Button></a>
      </div>
    </>
  );
}

export default List3;
