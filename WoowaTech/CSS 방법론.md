# CSS 방법론

## CSS
* HTML 혹은 XML로 쓰여진 문서의 스타일을 나타내기 위해 사용되는 언어
* 문서의 구조와 스타일(디자인)을 분리하여 HTML이나 XML의 각 요소를 꾸미는 역할   

### CCS 등장 전
```
<font color="#00f">각 요소의 전용 속성이나 style 속성으로 스타일을 일일이 지정</font>
```

문제점은 같은 스타일 요소를 여러 페이지마다 사용할 경우 하나를 수정할 때마다 그 만큼 반복하여 수정해야 한다. 그리고 HTML은 본래 문서 구조를 의미하여 HTML에 직접 스타일을 지정하는 것은 바람직하지 않다.   

### CSS 등장 후
CSS 파일을 생성하여 클래스나 아이디로 요소를 지정한 후 스타일을 설정할 수 있게 됐다.   

### CSS 문제점
* 모든 것이 전역 범위로 모든 스타일링이 서로 간섭할 가능성 존재
* 파일이 분리되어도 해당 파일을 읽어들인 HTML에서는 모든 스타일링이 동일한 범위 안에 존재
* 복잡해지는 웹 사이트로 유지보수가 용이한 CSS를 작성하는 것이 어려워지고 CSS 방법론이 여러 가지 대두   

## OOCSS
* Object Oriented CSS: 객체 지향 CSS
* 니콜 설리반 제창
* 주요 발상
    * 레고처럼 자유로운 조합이 가능한 모듈의 집합
    * 해당 모듈을 조합해 페이지 생성
    * 신규 페이지를 만드는 경우에 기본적으로 추가적인 CSS 생성 필요 없음   

### 레고와 같은 모듈 구현을 위한 두 가지 언칙
1. 구조와 화면을 분리   
2. 컨테이너와 컨텐츠 분리   

#### 구조와 화면 분리 전
```
<main id="main">
    <button class="btn-accept">기본 버튼</button>
    <button class="btn-cancel">취소 버튼</button>
</main>
```
그리고 CSS 파일에 각 설정한 클래스마다 스타일을 다르게 지정할 것이다.   

#### 구조와 화면 분리 후
```
<!-- 스트럭쳐 -->
#main .btn {
    ...
}

<!-- 스킨 -->
#main .accept {
    ...
}

#main .cancel {
    ...
}
```
위 처럼 공통된 구조를 나타낼 스타일과 화면에 표시되는 스타일을 분리하여 설정할 수 있다.   

만약 새로운 버튼을 추가하게 된다면
```
<button class="btn-danger">위험 버튼</button>
```

```
#main .danger {
    ...
}
```
위 처럼 버튼을 추가하고 화면에 보일 스타일만 새로 지정하면 된다. 구조 스타일은 이미 같기 때문에 따로 설정할 필요가 없다.   

### 컨테이너와 컨텐츠 분리
* 컨테이너는 "영역", 컨텐츠는 "모듈"
* 특정한 컨텍스트에 지나치게 의존하지 않음   

```
<!-- 컨테이너 -->
<main id="main">
    <!-- 컨텐츠 -->
    <button class="btn-accept">기본 버튼</button>
    <button class="btn-cancel">취소 버튼</button>
    <button class="btn-danger">위험 버튼</button>
</main>
```
만약 CSS를 아래처럼 작성하게 된다면
```
#main {
    ...
}

#main .btn {
    ...
}
```
버튼들은 main에 의존적이게 된다. 이를 해결하기 위해서

```
.btn {
    ...
}
```
처럼 main의 밖에서도 동작할 수 있게 만들면 된다.   

## OOCSS 정리
* OOCSS의 역사는 매우 길고 명확하게 규칙이라고 불리는 게 많지 않음
* 다른 CSS 방법론은 기본적으로 OOCSS를 참조하면서 개선
* 오늘 날 OOCSS는 한 가지만으로 실질적인 CSS 설계를 수행하는 것은 현실적이지 않음   

## SMACSS
* Scalable and Modular Architecture for CSS
* 조나단 스눅 제창
* CSS 코드를 해당 역할에 따라 분류한 것
    * 베이스 (Base)
    * 레이아웃 (Layout)
    * 모듈 (Module)
    * 스테이트 (State)
    * 테마 (Theme)   

* 베이스 (Base) 규칙
    * 프로젝트 표준 스타일 정의
    * 리셋 CSS도 베이스 규칙에 포함
    ```
        body {
            바탕화면 색깔 지정
        }

        a:hover {
            텍스트 스타일 지정
        }
    ```
* 레이아웃 (Layout) 규칙
    * 헤더, 메인 영역, 사이드 바 등 웹 사이트의 레이아웃을 구성하는 큰 모듈에 관한 규칙
    * 대부분 특정 페이지에서 한 차례만 사용해 ID 셀렉터를 활용한 스타일링 허용
    * 반복적으로 사용하는 모듈의 경우 클래스 셀렉터 이용
    ```
    <header id="header">...</header>
    <main id="main">
        <section class="section">...</section>
    </main>

    <footer id="footer">...</footer>
    ```

    ```
    <!-- 아이디 셀렉터 -->
    #header {
        ...
    }

    #main {
        ...
    }

    #footer {
        ...
    }

    <!-- 클래스 셀렉터 -->
    .section {
        ...
    }
    ```
특정한 페이지에서만 레이아웃을 변경하고 싶은 경우에는 손자 셀렉터를 이용해 레이아웃 모듈의 스타일을 덮어쓰면 된다.
```
    <body class"l=narrow">
        <header id="header"></header>
        <main id="main"></main>
        <footer id="footer"></footer>
    </body>
```
```
.l-narrow #header,
.l-narrow #main,
.l-narrow #footer {

}
```

* 모듈 (Module) 규칙
    * 타이틀, 버튼, 카드, 네비게이션 등
    * 모든 모듈은 레이아웃 규칙 안에 배치되는 것을 가정
    * 다른 페이지로 이동하거나 다른 레이아웃 안에 삽입하더라도 형태가 부서지거나 달라지지 않고 사용할 수 있어야 함
        * 특정 컨텍스트에 지나치게 의존하지 않도록 작성해야 함
    * 모듈의 루트 요소에는 반드시 클래스 셀렉터를 사용
    ```
    <div class="media">
        <figure>
            <p>...</p>
        </figure>
    </div>
    ```

    ```
    .media {
        ...
    }

    <!-- 자녀 셀렉터 .media > figure, 손자 셀렉터X .media figure -->
    <!-- 자녀 셀렉터를 사용하여 영향 범위 줄이기 -->
    .media > figure {
        ...
    }
    ```

* 스테이트 (State)
    * 기존 스타일을 덮어쓰거나 확장하기 위해 사용
    * 기존 스타일을 모두 덮어써서 스테이트 스타일을 반영하는 것을 기대하여 필요한 경우에는 <b>!important</b> 사용도 권장
    * 스테이트는 레이아웃이나 모듈에 할당 가능
    * 스테이트 규칙에 따른 클래스 이름은 모두 <b>is-</b> 접두사 붙임
    * 자바스크립트에 의존
    ```
    .is-tab-active {
        배경화면 색 설정
        글자 색 설정
        등
    }
    ```

    ```
    <!-- tab 클래스 요소를 클릭 시 요소에 is-tab-active 클래스 실행 -->
    document.querySelector(".tab").addEventListener("click", (event) => {
        event.target.classList.toggle("is-tab-active");
    })
    ```

* 테마 (Theme)
    * 사이트 내 레이아웃이나 색상, 텍스트 처리 등을 일정한 규칙에 따라 덮어쓰는 것
    * 기존의 다양한 스타일링이 덮어쓰기의 대상
    * 예. 다크모드 전환, 테마 컬러 변경
    * <b>theme</b> 접두사를 붙일 것을 권장

## SMACSS 정리
* 프로젝트에서 고려해야 하는 대부분 CSS 규칙을 포함
* 각 규칙이 엄격하지 않아 유연하지만 경우에 따라 규칙이 너무 유연하여 실제 코드의 지침으로 삼기 어려움
* 모듈 규칙에 OOCSS를 적용하거나 다음에 설명할 BEM의 규칙을 일부 적용하는 등 다른 설계 기법과 조합하는 경우 다수

## BEM
* Block, Element, Modifier
* 러시아의 Yandex 사가 제창한 컴포넌트 기반 웹 개발 접근법
* UI를 독립된 블록으로 분리함으로써 복잡한 페이지에서도 간단하고 신속하게 개발을 수행하는 것이 목적
* 기본적으로 모듈 기반의 방법이지만 그 내용이 다른 설계 기법에 비해 엄격하고 강력하여 세계적으로 일므이 알려져 실제로 널리 사용   

### Block
* 재사용할 수 있는 기능적으로 독립적인 페이지 구성 요소
* BEM을 사용할 때 ID 셀렉터 또는 요소 셀렉터 사용 불가
* Block 이름은 상태(State)가 아닌 용도(Purpose)를 나타내는 네이밍 사용
    * 이것은 무엇인가? - menu or button => O
    * 이것은 어떻게 생겼는가? - red or big => X
    ```
    <!-- error 블록은 시맨틱한 의미 => O -->
    <div class="error"></div>

    <!-- red-text는 글자가 빨갛다는 상태 => X -->
    <div class="red-text></div>
    ```
    * 환경에 영향을 미치지 않아야 함. 즉, Block 자체에 대한 외부 지오메트리(margin) 또는 Block의 위치(position)를 설정하지 않아야 함
    ```
    <header class="header">
        <div class="logo"></div>
        <form class="search-form"></form>
    </header>
    ```
    * Block들은 서로 중첩 가능

### Element
* Block의 복합 부품으로 Block과 별도로 사용 불가
* Element 이름은 상태(state)가 아닌 용도(purpose)를 나타내어 설정
    * 이것은 무엇인가? - item, text, etc => O
    * 이것은 어떻게 생겼는가? 어떤 타입인가? - red, big, etx => X
* 명명법: <b>block-name__element-name</b>
```
<form class="search-form">
    <!-- search-form 안에 input -->
    <input class="search-form__input" />
    <!-- search-form 안에 button -->
    <button class="search-form__button>Search</button>
</form>
```
* Element는 항상 Block의 부분이어야 하며, Block으로부터 분리하여 사용 불가
* 모든 Block이 Element를 가지는 것은 아님
* Element는 서로 중첩 가능
    * 다만, Element는 Block의 부분일 뿐
    * Element의 이름은 <b>block__element1__element2</b>처럼은 불가
    ```
    <!-- 이름 구조가 block-name__element-name을 따르지 않은 예 -->
    <form class="search-form">
        <div class="search-form__content>
            <!-- 안 좋은 예 -->
            <input class="search-form__content__input" />
            <button class="search-form__content__button>Search</button>
        </div>
    </form>
    ```

#### Block vs Element
* 구현된 다른 페이지 컴포넌트에 의존하지 않고 코드가 재사용 가능 - Block
* 부모 엔티티(Block)없이 구분해서 사용 불가 - Element
* 더 작은 부분으로 나뉘어져야 하는 Element - Block / Mix
    * BEM에서는 Elements의 Elements 생성 불가


### Modifier
* Block 또는 Element의 모양, 상태, 동작 정의
* Modifier 이름은 모양(appearance), 상태(state), 동작(behavior)를 나타낸 네이밍 설정
    * 어떤 사이즈?, 어떤 테마? - size_s or theme_islands => O
    * 어떻게 다른 것들과 다른가? - disabled, focused, etc => O
    * 어떻게 행동할 것인가? - directions_left-rop => O
* Modifier는 단독 사용 불가

#### Modifier 유형
* Boolean
    * Modifier 유무만 중요하고 그 값이 무관할 때 사용: <b>disabled</b>, <b>focused</b>
    * Boolean modifier가 있으면 해당 값이 참으로 간주
    * 명명법: <b>block-name_modifier-name</b>, <b>block-name__element-name_modifier-name</b>
    ```
    <form class="search-form search-form_focused">
        <input class="search-form__input />
        <button class="search-form__button search-form__button_disabled">
            Search
        </button>
    </form>
    ```
* Key-Value
    * Modifier 값이 중요한 경우에 사용: <b>size_s</b>, <b>theme_islands</b>
    * 명명법: <b>block-name--modifier-name_modifier-value</b>, <b>block-name__element-name_modifier-name_modifier-value</b>
    ```
    <form class="search-form search-form_theme_islands">
        <input class="search-form__input />
        <button class="search-form__button search-form__button_size_m">
            Search
        </button>
    </form>
    ```

    ```
    <!-- X 동일한 유형의 Modifier를 동시에 사용 불가 -->
    <form class="search-form search-form_theme_islands search-form-theme_lite">
        <input class="search-form__input />
        <button class="search-form__button search-form__button_size_s search-form__button_size_m">
            Search
        </button>
    </form>
    ```

* Mix
    * Block과 Element가 하나의 HTML 요소에 존재하는 것을 의미
    * 코드 중복을 피하면서 여러 BEM 엔티티의 동작과 스타일을 결합
    * 기존 BEM 엔티티를 기반으로 의미상 새로운 인터페이스 컴포넌트를 작성
    * 가급적 상세도를 높이지 않고 Block의 독립성을 유지 가능
    ```
    <div class="header">
        <!-- search-form은 search-form과 header로 Mix -->
        <div class="search-form header__search-form></div>
    </div>
    ```

#### MindBEMding
* Modifier  전후의 구분 문자를 언더바 한 개에서 하이픈 두개로 변경한 스타일
    * <b>block-name--modifier-name</b>
    * <b>block-name__element-name--modifier-name</b>
    * <b>block-name--modifier-name--modifier-value</b>
    * <b>block-name__element-name--modifier-name--modifier-value</b>

## 기존 CSS 방법론 문제점
* CSS가 HTML 구조와 강하게 결합
* HTML에 의존하는 CSS
    * HTML에서 스타일이 필요한 요소에 클래스명을 부여
    * 클래스명이 부여된 요소에 대하여 CSS에서 스타일링
* CSS에 의존하는 HTML
    * CSS에서 HTML과 독립적으로 스타일 선언
    * HTML에서는 선언되어 있는 스타일에 한하여 마크업 작성

## 새로운 CSS 방법론
* Utility-First CSS / Function CSS
    * 시멘틱하고 컨텍스트에 의존하지 않는 CSS 작성 X
    * 클래스명만 보아도 CSS 속성과 값을 바로 유추할 수 있도록 단 하나의 속성과 값을 나타내는 CSS를 사전에 미리 정의
    * 미리 정의된 클래스를 마치 HTML 요소에 제공하는 API로 생각하여 API(클래스명)을 HTML에서 조합해 사용 가능
    ```
    <button class="w-1/2 rounded-md border border-gray-300>Button</button>
    ```
    ```
    .w-q/2 {
        width: 50%;
    }

    .rounded-md {
        border-radius: 0.375rem;
    }

    .border {
        border-width: 1px;
    }

    .border-gray-300 {
        border-color: rgb(209, 213, 219);
    }
    ```

* inline style과의 차이점
    * 아무 값이나 지정할 수 있는 것이 아님. 사전에 정해진 리스트에서 선택해야 하므로 전체적인 일관성을 높일 수 있음
        * X: font-size: 14px, font-size: 13px, font-size: 0.9rem, font-size: 0.85rem => 일관성 없는 폰트 스타일
        * O: text-sm, text-xs, py-3, py-4, text-dark, text-light
    * hover, focus 등의 의사 클래스 셀렉터 사용 가능
    * media query가 사용 가능하여 반응형 디자인 대응에 수월

## Utility-First CSS
* Tailwind CSS
* Tachyons
* Atomic CSS

[CSS 방법론](https://www.youtube.com/watch?v=B70h37mpD74&t=290s)