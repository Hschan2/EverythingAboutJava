# Virtual DOM

## 브라우저 렌더링 과정
1. DOM tree 생성 - 렌더 엔진이 HTML을 파싱하여 DOM 노드로 이루어진 트리 생성   
2. Render tree 생성 - CSS 파일과 Inline 스타일을 파싱, DOM + CSSOM = 렌더 트리플 생성 (문서에 시각적인 구조를 나타냄)   
3. Layour (Reflow) - 각 노드들의 스크린에서의 좌표에 따라 위치 결정 (Position, Size 등 계산)   
4. Paint (Repaint) - 실제 화면에 그리기   

만약, DOM에 문제가 생긴다면 Render tree가 재생성이 된다.   

## DOM 조작의 비효율성
만약 사용자가 장바구니에 상품을 등록하거나 삭제할 때, 전체 노드들이 다시 그려지는 비효율성의 문제가 발생한다. 그러나 최근 Virtual DOM이 등장하여, SPA를 많이 사용하게 되면서 DOM tree를 즉각적으로 많이 변경할 일이 생겼다.   

전체 페이지를 서버에서 매번 다시 보내주는 것이 아닌 브라우저에서의 자바스크립트가 관리하기 때문에 DOM조작을 효율적으로 하기 위한 최적화가 필요했다.   

## Virtual DOM 동작 원리
real DOM과 같은 속성들 (class 등)을 실질적으로 사용한다. 즉, Virtual DOM에 변경 사항이 반영이 되면 원본 DOM에 필요한 변화만 반영되어서 전체 real DOM을 바꾸지 않고도 필요한 UI의 업데이트를 적용할 수 있다.   

Virtual DOM은 HTML 객체에 기반한 자바스크립트 객체로 표현할 수 있다.
```
<ul id="item">
    <li>Item 1</li>
    <li>Item 2</li>
</ul>
```
위의 HTML 코드를 Virtual DOM은 자바스크립트 객체를 아래처럼 활용한다.
```
let domNode = {
    tagName: "ul",
    attributes: {id: "items"},
    children: [
        {
            tagName: "li",
            textContent: "Item 1",
        },
        {
            tagName: "li",
            textContent: "Item 2",
        },
    ],
};
```
이는, 자바스크립트 객체로 표현하며 멤모리 상에서 동작한다. 그러므로 더욱 빠르게 동작한다. 그리고 실제 렌더링이 되는 것이 아니기 때문에 연산 비용이 적다. 왜냐하면 모든 변화를 하나로 묶어서 딱 한 번만 실행시키기 때문이다.   

DOM fragment의 변화를 묶어서 적용한 다음 기존 DOM에 던져주는 과정은 Virtual DOM에서 자동화, 추상화해놓은 것에 불과하다.   

React는 Virtual DOM을 이용하는 대표적인 자바스크립트 라이브러리이다. React에서 Virtual DOM을 ```UI의 가상적인 표현을 메모리에 저장하고 React DOM과 같은 라이브러리에 의해 실제 DOM과 동기화```하는 것이라고 표현한다. 이를 재조정이라고 부른다.   

실제로 React에서는 JSX를 사용한다. 이는 HTML 객체처럼 보이지만 실제로 자바스크립트를 확장한 것이다. React elements는 DOM 요소의 가상 버전으로 가볍고, 상태를 가지 않으며, 불변성을 유지한다. (Light & Immutable, Stateless) 불변성 때문에 비교하고 업데이트하는 것이 쉬워지는 것이다. 그리고 React는 ReactDOM을 사용하여 실제 DOM에 반영한다.   

## JSX
JSX에서 React Element는 변경이 불가하기 때문에 만약 변경하기 위해서는 새로운 요소를 만들어서 ReactDom.render()로 전송해야 한다. 일부 컴포넌트를 바꾸고 싶지만 새로운 Element를 render해야 하는 것처럼 보이는 것이다. 그러나 React는 이럴 때 Virtual DOM을 사용한다. 모든 React DOM Object는 그에 대응하는 Virtual DOM Object가 있다. Virtual DOM Object는 DOM Object를 대신한다. 

## Diffing
데이터가 업데이트되면 바뀐 데이터를 바탕으로 React.createElement()를 통해 JSX Element를 렌더링한다. 이 때, 모든 각각의 Virtual DOM Object를 업데이트한다. Virtual DOM가 업데이트 되면 React는 Virtual DOM을 업데이트 전에 Virtual DOM 스냅샷과 비교하여 어떤 Virtual DOM이 바뀌었는지 검사한다.   

즉, Diffing은 Virtual DOM이 업데이트되면, React는 Virtual DOM을 업데이트 이전의 Virtual DOM 스냅샷과 비교하여 정확히 어떤 Virtual DOM이 바뀌었는지 검사하는 것을 말한다. 이는 재조정 과정에 속한다.   

Element의 속성 값만 변한 경우에는 속성 값만 업데이트한다. 그리고 Element의 태그 또는 컴포넌트가 변경된 경우에는 해당 노드를 포함한 하위 모든 노드를 Unmount한 후 새로운 Virtual DOM으로 대체한다.   

모든 업데이트가 마무리가 된 후에 딱 한 번 실제 DOM에 결과를 업데이트 한다.   

### React의 Virtual DOM 과정
1. 전체 Virtual DOM이 업데이트   
2. Virtual DOM을 업데이트 이전의 시점과 비교   
3. 실제로 바뀐 부분만 real DOM에서 변경 (새로운 태그만 real DOM tree에 렌더링)   
4. ream DOM에서의 변화가 스크린에 출력   

## 무조건 Virtual DOM?
아니다. 정보 제공만 하는 웹페이지라면 인터랙션이 발생하지 않기 때문에 일반 DOM의 성능이 더 좋을 수도 있다. SPA로 제작된 큰 규모의 웹 페이지에서는 Virtual DOM을 이용해서 브라우저의 연산 양을 줄여서 성능을 개선할 수 있다.   

[출처](https://www.youtube.com/watch?v=PN_WmsgbQCo)