# Form Validation
Form은 사용자가 웹 사이트로 정보를 보낼 수 있는 입력 창구를 만들어 준다. 클라이언트가 Form을 통해서 정보를 서버로 전달하면 서버는 처리한 결과를 응답해서 다시 클라이언트에게 보낸다.   

클라이언트는 Form을 이용하여 생성, 수정, 삭제 등을 할 수 있다.   

## Form Tag
<b>Form Tag</b>는 아래와 같은 형식을 갖추고 있다.

```
<form action="/where-to" method="post" name="login-form" novalidate></form>
```

위의 코드를 자세하게 이해해본다.   
* action: Form의 내용을 처리하기 위한 서버의 RUL
* method: 요청을 처리하기 위한 방법
* name: Form을 식별하기 위한 이름
* novalidate: Form 전송 시 입력된 값에 대해 검증을 수행하지 않음   

Method에서 POST 외 GET 방식도 있다. GET 방식은 Default이며 action 속성에 지정한 URL에 폼의 내용을 추가하여 서버에 송신하는 방식이다. 이 방식은 데이터가 외부에 노출되어 보안에 취약하고 전송할 수 있는 데이터의 길이가 한정되어 있다.   

POST 방식은 데이터가 외부에 노출되지 않는다. (Header의 body에 담겨서 전송된다.) 그리고 전송할 수 있는 데이터의 길이 제한이 없다. 그렇다고 해서 안전한 것은 아니다. 다른 툴로 확인이 가능하기 때문이다. 그래서 Form Validation을 확실하게 배워야 한다.   

현재 Form Tag는 기본적으로 로직을 검증하도록 되어 있다. 그래서 더욱 편리하게 개발할 수 있다. 만약 이를 원하지 않는다면 <b>novalidate</b>를 사용하면 된다.   

## Form Elements
Form에는 하나 이상의 입력 양식을 작성하여 사용할 수 있다.   

* input: 자주 사용하는 입력. 읽기만 가능한 readonly, 정규 표현식을 사용해 지정된 형식의 값을 받게 하는 pattern 등의 속성 사용 가능
* textarea: input과 달리 Multiple Line으로 입력받고 싶을 때 사용
* button: input 태그와 button 태그로 만들 수 있지만 Semantic한 마크업을 작성하기 위해서는 button 태그 사용
* select: 한 가지 옵션 또는 여러 가지 옵션 선택 가능
* output: 결과값 보이기. 다른 태그도 가능하지만 웹 접근성을 위해서 output 사용 추천
* fieldset: form 요소 그룹화. 내부에 legend라는 태그를 첫 번째 요소로 가질 수 있고 이는 form 그룹의 이름을 지정. fieldset으로 form 요소들을 그룹화하면 한 번에 모든 컨텐츠를 비활성화 가능하며 접근성 향상   

## Form Validation
<b>Form Validation</b>은 Form에서 들어온 데이터가 유효한 값인지 검증하는 것을 말한다. 즉, 원하는 양식의 데이터가 사용자로부터 제대로 오는지 확인하는 것이다.   

이는 보안적 측면에서 해커들의 공격을 막아주고 사용자의 올바르지 않은 데이터가 서버로 전송되어 데이터 베이스에 저장되지 않게 해준다. 그리고 유저 경험 측면에서 사용자에 편의성을 제공할 수 있다.   

* Form Validation의 두 가지
    * Client Side Validation: 사용자의 입력을 미리 클라이언트 측에서 검증하여 서버에 전달하는 것
        * 서버에 부하를 주지 않는다.
        * 클라이언트에 의해 검증 결과가 쉽게 조작 가능하다.
        * 잘못된 사용자가 프록시 툴을 이용해 검증을 우회하여 잘못된 입력 값을 서버에 전달하면 잘못된 데이터가 저장된다.
    * Server Side Validation: 사용자의 입력을 서버로 넘겨서 서버에서 검증하는 것
        * 클라이언트에서 어떤 값이 와도 서버에서 검증하고 데이터를 저장하여 신뢰성이 증가한다.
        * 서버를 통해 검증하여 서버 부하가 증가한다.
        * 필터링 우회가 가능하다면 조작된 데이터를 저장할 수 있다.   

## Web Attacks
만약 Form Validation을 제대로 하지 않아 웹 사이트가 공격을 받거나 원하지 않는 데이터가 서버에 저장되는 문제가 발생할 수 있기 때문에 확실하게 검증하고 대응해야 한다.   

대표적인 Web Attacks은 두 가지가 있다.   

* XSS (XCross Site Scripting): 관리자 권한이 없는 사용자가 Form을 통해서 악성 스크립트를 웹 사이트에 삽입하는 공격
    * Stored XSS: 해커가 Form을 사용하여 잘못된 데이터를 저장하게 만들고 사용자가 웹 사이트에서 해당 데이터를 가져와 사용하였을 경우, 사용자의 브라우저에서 쿠키 등 공격을 받는다. 이는 지속적인 공격이라는 뜻도 가지고 있으며 Persistent XSS라고도 부른다.
    * Reflected XSS: 해커가 약점을 가진 사이트를 이용하여 이와 비슷한 공격용 URL를 생성 후 사용자에게 전송한다. 사용자가 해당 URL에 접속할 경우 공격을 당한다.
    * DOM Based XSS: 사용자의 브라우저에서 DOM 환경을 수정하여 클라이언트 측 코드가 예상치 못한 방식으로 동작하게 만든다.   

<b>Stored XSS</b>와 <b>Reflected XSS</b>는 서버에서 사용자의 요청을 처리하는 과정에서 응답 페이지에 해당 악성 스크립트가 포함되어 브라우저에 전달되지만 <b>DOM Based XSS</b>은 서버와 관계없이 클라이언트. 즉, 브라우저에서 DOM을 이용하여 동적으로 페이지를 조작할 때 발생하는 취약점이다.   

* SQL Injection 공격: 클라이언트의 입력 값을 조작하여 서버의 데이터 베이스를 공격. 사용자가 입력한 데이터를 제대로 필터링하지 않고 저장했을 때 문제 발생   

이러한 공격으로 개발한 웹 사이트를 걱정하게 되지만 바닐라 자바스크립트만 사용하지 않는다면 다행히도 프레임워크나 라이브러리에 기본적으로 이러한 공격들을 막아주도록 해준다. (다만, React에서 DDangerously set InnerHTML 사용하지 말 것. Vue에서 V-html 사용하지 말 것)   

## 유저 경험 차원에서 Form Validation을 해야하는 이유
사용자에 좋은 경험을 주기 위해서는 기본적으롯로 세 가지 요소를 갖추고 있어야 한다.   

* 명확하고 쉬운 메세지 전달
    * 잘못된 값이 어떻게 잘못되었고 어떤 식으로 수정해야 하는지 정확하게 전달할 것
    * 옳은 값을 입력했을 때 옳다고 전달할 것
* 사용자의 눈에 잘 띄는 메세지
    * 잘못된 값을 입력했을 때 에러 메세지는 눈에 띄는 색으로 지정할 것
    * 올바른 값을 입력했을 때 텍스트의 색을 명확하게 지정할 것
* 사용자에 적당한 시기에 오류를 전달할 것 (알맞은 시간과 장소에서 에러에 대해 전달)
    * 개발자마다 규칙이 다르다.
    * 사용자가 입력하기 전에 메세지 전달 혹은 입력하고 있는 과정에서 메세지 전달 혹은 입력이 끝나고 나서 메세지 전달
    * 개발자마다 다르지만 사용자가 어느 정도 입력하고 나서 검증을 시작하는 것을 사용   

## 검증 로직 작성
* HTML5에서 기본적으로 제공하는 Built in Form Validation 사용
    * required 등 기본적으로 제공하는 것을 사용 가능
    * Style을 쉽게 다르게 지정 가능 (예. CSS - input:invalid {})
    * 그러나 사용자에 일관적이지 못한 경험을 제공 가능성 존재 (브라우저마다 제공하는 메세지와 스타일이 다름)
* 자바스크립트를 이용하여 직접 검증 로직 구현
    * 브라우저에서 제공하는 Constraint Validation API 활용 (메세지를 요구 사항에 맞게 커스터마이징 가능)
    * 하나하나 검증 로직을 직접 작성 가능하지만 고려할 것이 많고 까다로움   

## 라이브러리
Form의 필요한 검증 로직이 비슷하여 미리 만들어진 라이브러리들이 많다. Form에 대한 상태 관리를 해주고 기본적인 검증도 해준다.   

* React Hook Form
* FORMIK
* Ant Design   

그러나 이러한 라이브러리들을 사용하면 많은 검증 로직들을 넣게 되고 구문과 코드가 길고 복잡해진다. 그래서 보통 Form 태그의 유효성 검증을 도와주는 <b>yub 라이브러리</b>와 함께 사용한다.   

[Form Validation](https://www.youtube.com/watch?v=Z2YJvBw3pPI)