import React from "react";
import { Link, Route, Switch, BrowserRouter as Router } from "react-router-dom";
import List1 from "./List1";
import List2 from "./List2";
import List3 from "./List3";
import List4 from "./List4";
import './List.css';

function List() {
  return (
    <Router>
      <header className="header">
        <Link to="/">
          <button className="Lbutton">Project 1</button>
        </Link>
        <Link to="/about">
          <button className="Lbutton">Project 2</button>
        </Link>
        <Link to="/now">
          <button className="Lbutton">Project 3</button>
        </Link>
        <Link to="/users">
          <button className="Lbutton">Project 4</button>
        </Link>
      </header>
      <main>
        <Switch>
          <Route exact path="/" component={List1} />
          <Route path="/about" component={List2} />
          <Route path="/now" component={List3} />
          <Route component={List4} />
        </Switch>
      </main>
    </Router>
  );
}

export default List;