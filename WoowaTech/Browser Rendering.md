# Browser Rendering (브라우저 렌더링)

## Web Browser
코드를 가지고 웹 페이지를 그려주는 역할을 한다. 하나의 웹 페이지를 보여주기 위해서는 많은 단계를 거친다.   

* User Interface
    * UI Backend
    * Browser Engine
        * Data Persistance
        * Rendering Engine
            * Networking
            * Javascript Interpreter
            * UI Backend

* User Interface: 주소 표시줄, 이전/다음/새로고침 버튼 등 웹 페이지를 제외하고 사용자와 상호작용하는 사용자 인터페이스
* Browser Engine: 유저 인터페이스와 렌더링 엔진을 연결하는 브라우저 엔진
* Rendering Engine: HTML과 CSS를 파싱하여 요청한 웹 페이지를 표시하는 렌더링 엔진
* Networking: 각종 네트워크 요청을 수행하는 네트워킹 파트
* Javascript Interpreter: 자바스크립트 코드를 실행하는 인터프리터
* UI Backend: 체크박스나 버튼과 같은 기본적인 위젯을 그려주는 UI 백엔드 파트
* Data Persistance: LocalStorage나 Cookie와 같이 보조 기억장치에 데이터를 저장하는 파트   

## Rendering Engine
렌더링의 목표는 두 가지를 가지고 있다.   
* HTML, CSS, JS, 이미지 등 웹 페이지에 포함된 모든 요소들을 화면에 표시
* 업데이트가 필요할 때, 효율적으로 렌더링을 할 수 있도록 자료 구조를 생성   

### Rendering Engine의 동작 과정
<b>Critical Rendering Path</b>   

* DOM Tree 생성, CSSOM Tree 생성
    * Render Tree 생성
        * Render Tree 배치 (Layout (Reflow))
            * Render Tree 그리기 (Paint (Repaint))   

* DOM Tree 생성, CSSOM Tree 생성: ```<html>...</html>```과 같이 StartTag와 EndTag로 태그를 생성하고 종료한다. 그리고 브라우저에 렉싱 과정을 통해 토큰이 해당 속성과 규칙을 정의하는 노드 객체로 변환한다. 그리고 각 노드가 서로 연관성을 가질 수 있도록 트리를 생성하는데 이를 DOM Tree라고 한다. 이는 HTML의 모든 것을 구성한다. CSSOM은 DOM과 비슷하게 CSS를 구성하는 것을 말한다.   

* Render Tree: 화면에 표시되어야 할 모든 노드의 컨텐츠, 스타일 정보를 포함하는 트리이다. (DOM Tree + CSSOM Tree) Document 객체부터 각 노드를 순회하면서 각각 맞는 CSSOM을 찾아서 규칙을 적용한다. (meta, display: none;과 같은 것은 포함 X)   

* Render Tree 배치 (Layout (Reflow)): 뷰포트내에서 요소들에 정확한 위치와 크기를 계산하는 과정이다. 박스에 따라 텍스트나 요소의 박스가 화면에 차지하는 영역이나 여백, 스타일 속성이 계산된다.

* Render Tree 그리기 (Paint (Repaint)): 레이아웃 과정에서 렌더링 엔진이 각 요소들이 어떻게 생겼고 어떻게 보여주는지 알게 되면 화면에 실제 픽셀로 그려지도록 변환하는 과정. Render Tree에 포함된 요소들이나 텍스트, 이미지들이 실제 픽셀로 그려진다.   

Critical Rendering Path의 소요 시간을 줄이면 웹 페이지를 보여주는데 걸리는 시간 또한 줄어든다. 그러나 사용자의 동작으로 자바스크립트가 실행되어 CSS가 변경되거나 애니메이션이 재생될 때는 다르다.

### UI가 업데이트되는 상황 3가지
* 다시 Layout이 발생하는 경우: 주로 요소의 크기나 위치가 바뀔 때, 혹은 브라우저 창의 크기가 바뀌었을 때 다시 실행한다.   
```Javascript -> Style -> Layout -> Paint -> Composite```

* Paint부터 다시 발생되는 경우: 주로 배경 이미지나 텍스트 색상, 그림자 등 레이아웃의 수치를 변화시키지 않는 스타일의 변경이 일어났을 때 발생한다. (레이아웃의 수치를 변화시키지 않음)   
```Javascript -> Style -> Paint -> Composite```

* 레이어의 합성만 다시 발생하는 경우: Layout과 Paint을 수행하지 않고 레이어의 합성만 발생하기 때문에 성능상으로 가장 큰 이점을 가진다. (페인팅할 영역을 나누어 놓는 것을 의미, 레이어들은 트리로 구성)
```Javascript -> Style -> Composite```

[위의 상황들을 볼 수 있는 사이트](https://csstriggers.com)   

구글 크롬에서 개발자 도구를 이용해서 웹 페이지가 그려지는 가정을 한 눈에 확인할 수 있다. (개발자 도구 - Performance) 초록색으로 보여지는 것이 다시 그려지는 것이다.   

애니메이션을 주기 위해 Left 속성을 사용하면 그림을 다시 그리는 과정을 계속 하게되어 효율적이지 않을 수 있다. 여기서 Transform을 사용하면 다시 그리는 과정. 즉, 페인트 과정 없이 레이어의 합성으로만 이루어져 더욱 빠르고 효율적인 애니메이션을 만들 수 있다. (성능 최적화)

[출처](https://www.youtube.com/watch?v=sJ14cWjrNis)