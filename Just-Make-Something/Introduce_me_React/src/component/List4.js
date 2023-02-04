import React from "react";
import './Listvar.css';
import Button from '@material-ui/core/Button';

function List4() {
  return (
    <>
      <h1>Todo List with React, JavaScript</h1>
      <p>React(Hook)을 이용하여 Todo List를 따라 제작하였습니다.
      JavaScript(VanillaJS)를 이용하여 Todo List를 따라 제작하였습니다.
      CRUD를 배우기 가장 간단하고 좋은 방법이 Todo List라고 생각하여 진행하였습니다.</p>
      <div className="SourceLink" style={{marginTop:-100}}>
        <a href="https://hschan2.github.io/React_todo/" target="_blank"><Button>PAGE LINK(REACT)</Button></a>
        <a href="https://github.com/Hschan2/React_todo" target="_blank"><Button>GITHUB LINK(REACT)</Button></a>
        <a href="https://hschan2.github.io/React_ToDo_with_Hook/" target="_blank"><Button>PAGE LINK(HOOK)</Button></a>
        <a href="https://github.com/Hschan2/React_ToDo_with_Hook" target="_blank"><Button>GITHUB LINK(HOOK)</Button></a>
        <a href="https://hschan2.github.io/nomadcoder-vanillaJS-completion/vanillaJS/index.html" target="_blank"><Button>PAGE LINK(JAVASCRIPT)</Button></a>
        <a href="https://github.com/Hschan2/nomadcoder-vanillaJS-completion" target="_blank"><Button>GITHUB LINK(JAVASCRIPT)</Button></a>
      </div>
    </>
  );
}

export default List4;
