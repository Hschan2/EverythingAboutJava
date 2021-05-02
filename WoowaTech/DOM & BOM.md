# DOM & BOM

## DOM (Document Object Model)
```문서에 대한 모든 내용을 담고 있는 객체```이다. 텍스트 파일로 만들어진 문서를 브라우저가 이해할 수 있는 구조로 구성한 것이며, HTML 요소 간의 부자 관계를 반영하여 모든 노드들을 <B>트리 구조</B>로 구성한 것이다. HTML, XML 문서의 프로그래밍, <b>interface</b>가 존재하며, 문서의 구조화된 표현, Structured Representation을 제공하며 프로그래밍 언어가 DOM 구조에 접근할 수 있는 방법을 제공하여 <b>문서 구조, 스타일, 내용</b>등을 변경할 수 있게 돕는다.   

요소는 HTML 문서를 구성하는 개별적인 요소를 의미한다. HTML 요소는 렌더링 엔진에 의해 파싱되어 DOM을 구성하는 유선 노드 객체로 변환된다.

```<div class="woowaTech">Frontend</div>```

* div = 시작 태그
* class = 어트리뷰트 이름
* "woowaTech" = 어트리뷰트 값
* Frontend = 콘텐츠
* /div = 종료 태그   

* 요소 노드 = div
* 어트리뷰트 노드 = class="woowaTech"
* 텍스트 노드 = Frontend   

### 문서 노드 (Document Node)
DOM 트리의 <b>최상위에 존재하는 루트 노드</b>로서 Document 객체를 가리킨다. HTML 문서당 Document 객체는 유일하다. DOM 트리의 루트 노드이므로 DOM 트리의 노드들에 접ㄱ븐하기 위한 진입점의 역할을 한다. 즉, 요소, 어트리뷰트, 텍스트 노드에 접근하려면 문서 노드를 통해야 한다.   

### 요소 노드 (Element Node)
<b>HTML 요소를 가리키는 객체</b>다. HTML 요소 간의 중첩에 의해 부자 관계를 가지며, 이 부자 관계를 통해 정보를 구조화한다. 즉, 요소 노드는 문서의 구조를 표현한다.   

### 어트리뷰트 노드 (Attribute Node)
HTML 요소의 어트리뷰트를 가리키는 객체다. 어트리뷰트 노드는 지정된 HTML 요소의 요소 노드와 형제 관계를 갖는다. (단, 부모 노드와는 연결하지 않는다.) 어트리뷰트 노드에 접근하여 어트리뷰트를 참조하거나 변경하려면 먼저 형제 노드인 <b>요소 노드</b>에 접근해야 한다.

### 텍스트 노드 (Text Node)
HTML 요소의 텍스트를 가리키는 객체다. 문서의 정보를 표현하며, 텍스트 노드는 요소 노드의 자식 노드이며, 자식 노드를 가질 수 없는 리프 노드이다. 텍스트 노드에 접근하려면 먼저 부모 노드인 <b>요소 노드</b>에 접근해야 한다.   

### 노드 객체의 상속 구조
노드 객체의 상속 구조를 이해하기 위해 Input 요소를 예로 확인하는 방법으로 아래의 표를 확인하는 것이 좋다.   

|Input 요소 노드 객체의 특성|프로토타입을 제공하는 객체|
|-----|---|
|객체|Object|
|이벤트를 발생시키는 객체|Event Target|
|트리 자료구조의 노드 객체|Node|
|브라우저가 렌더링할 수 있는 웹 문서의 요소 (HTML, XML, SVG)를 표현하는 객체|Element|
|웹 문서의 요소 중에서 HTML 요소를 표현하는 객체|HTMLElement|
|HTML 요소 중에서 Input 요소를 표현하는 객체|HTMLInputElement|

### DOM API
DOM은 브라우저에서 제공하는 API 중 하나이다. DOM API를 이용해 자바스크립트로 웹 페이지를 <b>동적</b>으로 포작할 수 있다. 대표적인 DOM API로 ```Document.querySelector()```, ```Node.appendChild()```, ```Node.removeChild()```가 있다.

### DOM API - 노드 취득

|HTMLCollection|NodeList|
|-----|---|
|DOM API가 여러 개의 결과값을 반환하기 위한 DOM 컬렉션 객체|
|유사 배열 객체이면서 이터러블|
|배열로 변환 후 사용이 권장|
|getElementByTagName, getElementByClassName|querySelectorAll|
|Live 객체|대부분 Non-live 객체|
|forEach 사용 불가|forEach 사용 가능|   

getElement로 시작하는 것은 HTML 컬렉션이며, querySelectorAll은 노드 리스트를 반환한다. 유사 배열은 Length를 가지고 있어 <b>인덱싱</b>을 할 수 있고 <b>For문</b>을 돌 수 있다. 그리고 이터러블은 <b>For of 반복문</b>을 사용할 수 있고 <b>스프레드 연산자</b>를 사용할 수 있으며 배열 디스트럭처링을 할 수 있을 때, ```이터러블```하다고 한다. 그러나 배열이 아닌 유사 배열이기 때문에 배열에서 제공하는 <b>Map</b>과 <b>Filter</b>, <b>Reduce</b>를 사용할 수 없다.   

Live 객체는 노드 객체 상태를 <b>실시간</b>으로 반영한다. 반대로 Non-live 객체는 반대로 <b>정적</b>인 상태를 유지한다.   

여기서 중요한 것은, 둘 다 For문을 사용해서 반복문을 수행할 수 있지만 노드 객체의 상태 변경과 상관없이 안전하게 DOM Collection을 사용하려면 둘 다 객체를 <b>스프레드 연산자</b>를 통해서 배열로 변환하여 사용하는 것이 좋다.   

### DOM API - 노드 추가
1. InnerHTML   
* 장점
    * 쉽고 간단하게 새로운 요소를 추가할 수 있다.
* 단점
    * 기존 요소 노드의 모든 자식 노드를 제거하고 할당한 HTML 마크업 문자열을 파싱하여 DOM을 변경한다.
    * 요소와 요소 사이에 새로운 요소를 삽입하기 어렵다.
    * 크로스 사이트 스크립팅 공격에 취약하다. (인자로 innerHTML이 DOM 요소를 넘기기 때문)   

```
const $app = document.querySelector("#app");

for(let i = 0; i < 2000; i++) {
    $app.innerHTML += "<div>Hello</div>";
}

ms>
Loading 1765 ms
Scripting 1127 ms
Rendering 51 ms
Painting 1 ms
```

```
const $app = document.querySelector("#app");

let template = "";
for(let i = 0; i < 2000; i++) {
    template += "<div>Hello</div>";
}

$app.innerHTML = template;

ms>
Loading 20 ms
Scripting 53 ms
Rendering 54 ms
Painting 1 ms
```   

2. insertAdjacentHTML   
```
<div class="woowaTech">Frontend</div>

<div>의 앞 = beforeBegin
<div>의 뒤 = afterBegin
</div>의 앞 = beforeEnd
</div>의 앞 = afterEnd
```
* 장점
    * 기존 요소를 제거하지 않으면서 위치를 지정해 추가 가능하다.
    * 기존 자식을 모두 제거하고 자식을 새로 생성하는 innerHTML보다 빠르다.
* 단점
    * 크로스 사이팅 스크립팅 공격에 취약하다. (DOM String을 인자로 받기 때문)   

```
const $app = document.querySelector("#app");

for(let i = 0; i < 2000; i++) {
    $app.insertAdjacentHTML("beforeEnd", "<div>Hello</div>");
}

ms>
Loading 31 ms
Scripting 95 ms
Rendering 50 ms
Painting 1 ms
```

```
const $app = document.querySelector("#app");

let template = "";
for(let i = 0; i < 2000; i++) {
    template += "<div>Hello</div>";
}

$app.insertAdjacentHTML("beforeEnd", template);

ms>
Loading 19 ms
Scripting 48 ms
Rendering 48 ms
Painting 1 ms
```   

두 개의 속도를 비교했을 때, 크게 차이가 나지 않는다. 그 이유는 innerHTML과 다르게 기존 노드를 제거하지 않고 추가하기 때문이다.   

3. appendChild   
* 장점
    * 기존 요소를 제거하지 않으면서 위치를 지정해 추가 가능하다.
    * innerHTML과 insertAdjacentHTML과 비교하여 보안 이슈가 없다.
* 단점
    * 자식 노드를 생성해야 한다.   

```
const $app = document.querySelector("#app");

let $container = document.createDocumentFragment();
for(let i = 0; i < 2000; i++) {
    const $element = document.createElement("div");
    $element.appendChild(document.createTextNode("Hello"));
    $container.appendChild($element);
}

$app.appendChild($container);

ms>
Loading 18 ms
Scripting 48 ms
Rendering 68 ms
Painting 2 ms
```   

appendChild는 DOM String의 인자가 아닌 노드를 받기 때문에 보안 이슈가 없다.   

## 렌더링 과정
DOM 조작을 최소화해야 성능을 올릴 수 있다는 사실을 이해하기 위해 가장 기본적인 HTML 코드를 따라가면서 렌더링 과정을 이해하는 것이 좋다.   

```
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <link rel="stylesheet" href="index.css" />
        <title>Document</title>
    </head>
    <body>
        <h1>Hello</h1>
        <div>WoowaTech</div>
    </body>
    <script type="module" src="index.js"></script>
</html>
```
1. 브라우저의 렌더링 엔진은 클라이언트가 서버로부터 요청한 HTML부터 순차적으로 파싱하며 DOM을 생성해 나갑니다.   
2. 렌더링 엔진은 DOM을 생성해 나가다가 CSS를 로드하는 링크 태그나 스타일 태그를 만나면 CSSOM을 생성한다.   
3. UCSS 파싱을 완료하면 HTML 파싱 중단된 지점부터 다시 HTML 파싱하기 때문에 DOM 생성을 재개한다.   
4. DOM과 CSS 생성이 끝나면 이 둘을 합쳐서 렌더트리를 구성한다. 이 때, 렌더트리는 렌더링을 위한 트리구조이기 때문에 렌더링에 포함되지 않은 노드들(metaTag, scriptTag, CSS)등 디스플레이 등과 같은 것을 제외하고 구성된다.   
5. CSS 파싱 과정과 마찬가지로 렌더링 엔진은 HTML을 한 줄씩 순차적으로 파싱하며, DOM을 생산하다가 스크립트 태그를 만나면 DOM 생성을 일시 중단한다.   
6. 브라우저는 자바스크립트 코드를 실행하기 위해 렌더링 엔진에서부터 자바스크립트 엔진으로 제어권을 넘긴다. 이후에 자바 스크립트 파싱과 실행이 종료되면 렌더링 엔진으로부터 다시 제어권을 넘겨 HTML 파싱이 중단된 지점부터 DOM을 생성한다.   
7. 만약 자바스크립트 코드에 의해 DOM이나 CSSOM이 이용되는 경우 DOM과 CSSOM을 다시 렌더 트리로 결합하고 이를 기반으로 레이아웃과 페인트 과정을 거쳐 브라우저 화면에 렌더링한다. (이를 리플로우와 리페인트라고 한다.)   

CSS 경우, 브라우저는 동기적으로 위에서 아래 방향으로 순차적으로 HTML과 CSS, JAVASCRIPT를 파싱하고 실행한다. 즉, 자바스크립트의 위치에 따라 HTML 파싱이 중단되어 DOM 생성이 지연될 수 있다.   

## 렌더링 과정
### CSS 태그를 상단에 위치시키는 이유
* 사용자가 흰 화면을 응시하는 시간을 줄이기 위해 (CSS는 렌더링 차단 리소스로 취급)
* Link를 이용하여 스타일 시트를 다운 받는 경우, 최대한 빠르게 다운로드 받기 위해 (브라우저는 모든 외부 스타일 시트가 다운로드 후 CSSOM 트리가 구성될 때까지 웹 페이지 렌더링을 차단)   

### Script 태그를 하단에 위치시키는 이유
* HTML 파서는 Script 태그를 만나면 파싱을 멈추고 스크립트를 읽기 때문에 웹 페이지의 로딩이 그 만큼 늦춰질 수 있다.
* 생성되지 않은 DOM 노드를 읽거나 조작하는 것이 불가능하기 때문에 예상치 못한 오류가 발생할 수 있다.   

```
<html>
    <head>
        <script>
            ....
        </script>
    </head>
    <body>
    </body>
</html>
```
위 처럼 head 안에 자바스크립트 태그가 위치할 경우, 아직 DOM 생성이 미처 완료되지 않은 상태이기 때문에 DOM을 호출하는 경우에 찾지 못한다는 것을 확인할 수 있다.

```
<html>
    <head>
    </head>
    <body>
    </body>
    <script>
        ....
    </script>
</html>
```
위 처럼 하단에 자바스크립트 태그를 위치했을 경우, DOM이 생성된 이후이기 때문에 정상적으로 노드 요소를 확인하는 것을 알 수 있다.   

## 렌더링의 문제점
* 동적 UI 관리에 약하다.
    * DOM 트리가 수정될 때마다 새로운 렌더 트리와 레이아웃을 생성하고 Repaint를 한다.
* 단 하나의 요소가 바뀌어도 전체 페이지 정보를 갱신한다.
* 하나의 페이지에서 많은 동작이 이뤄지는 SPA(Single Page Application)에서 비효율적이다.   

## Virtual DOM
이전 DOM과 가상 DOM에 있는 내용을 비교하여 바뀐 부분만 실제 DOM에 적용한다.   

React에서는 변경된 부분, <b>diff</b>만 계산하여 실제 DOM에 적용한다.   

결과적으로, 브라우저는 불필요한 렌더링 횟수를 줄이고 한 번만 렌더링 할 수 있다.   

## BOM (Browser Object Model)
웹 브라우저 환경의 다양한 기능을 객체처럼 다루는 모델이며 대부분의 브라우저에서 구현되어 있지만, 정의된 표준이 없어 브라우저 제작사마다 세부사항이 다르다. Window 객체는 자바스크립트의 최상위 객체이자 전역 객체이면서 모든 객체가 소속된 객체이다. (Var 키워드로 선언한 일반 변수도 모두 Window 객체의 속성이 되며 최상이 객체이기 때문에 생략이 가능하다.)   

```
window - 현재 브라우저 창 (탭)
    document - 현재 로드된 웹 페이지
    history - 브라우저 히스토리에 기록된 웹 페이지들
    location - 현재 페이지 URL
    navigator - 브라우저 관련 정보
    screen - 장치의 디스플레이 정보
```

위에서 볼 수 있듯이, document 객체는 DOM 트리에서 <b>최상단 객체</b>이다. 따라서 DOM은 BOM에 <B>포함되는 관계</B>이다. 그렇기 때문에 DOM API를 통해 노드 요소를 불러올 때, 윈도우를 앞에 붙여도 정상적으로 실행이 된다.

