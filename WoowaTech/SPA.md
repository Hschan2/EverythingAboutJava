# SPA

## MPA (Multi-page Application)
두 개 이상으로 구성된 애플리케이션을 말한다. 사용자의 클릭과 같이 인터랙션이 발생할 때마다 해당 링크로 이동하여 앱이 다시 새로고침이 되는 전통적인 방식으로 작동한다.   

이 방식은 렌더링 방식으로 서버사이드 렌더링을 채택한다. (서버로부터 완전하게 만들어진 HTML 파일을 받아 페이지 전체를 렌더링하는 방식)   

* MPA 렌더링   
1. 클라이언트에서 초기 화면을 로드하기 위해 서버에 요청   
2. 서버는 HTML로 화면에 표시하는데 필요한 완전한 리소스 응답   
3. 클라이언트에서 CSS 등 일부 요청   
4. 서버에서 필요한 완전한 리소스를 응답   

즉, 서버 사이드 렌더링은 모든 것을 다시 받아서 새로고침을 한다.   

### MPA 장점과 단점
* 장점   
    * SEO (검색 엔진 최적화)에 유리
    * 빠른 초기 로딩 속도   
* 단점
    * 불편한 사용자 경험
    * 서버 부하 증가   

## CSA (Single-page Application)
하나의 페이지로 구성된 애플리케이션을 의미한다. 그리고 MPA와 달리 클라이언트 사이드 렌더링 방식을 채택한다.   

요청할 때마다 완전한 것을 응답하는 서버 사이드 렌더링과 달리 사용자의 요청에 따라 필요한 부분만 응답받아서 렌더링하는 방식이다.   

1. 클라이언트가 서버에 요청   
2. 서버가 화면에 표시하는데 필요한 완전한 리소스를 응답   
* 응답할 때, CSS, JAVASCRIPT 등 필요한 리소스를 받기 위한 초기 로딩 시간이 오래 걸린다.   
3. 다시 클라이언트가 필요한 부분을 서버에 요청   
4. 서버는 변경된 부분만 관련된 리소스를 응답한다.   

### CSA 장점과 단점
* 장점
    * 빠른 속도 및 서버 부하 감소
    * 사용자 친화적   
* 단점
    * SEO (검색 엔진 최적화)에 불리
    * 느린 초기 로딩 속도   

### CSA에서 서버 사이드 렌더링이 불가능할까?
아니다. 구현하려는 페이지가 여러 개일 경우, 전부 서버 사이드 렌더링으로 구현하는 것은 불가능하다. 이런 상황이 발생하면 더 이상 싱글페이지 애플리케이션이 아니라 멀티 페이지 애플리케이션이 되기 때문이다.

## 어떤 방식으로 개발해야 할까?
<B>콘텐츠</B> 중심으로 개발해야 한다. 클라이언트 렌더링 방식으로 개발한다고 해서 모든 부분을 클라이언트 사이드 렌더링 방식으로 구현해야 하는 것은 아니다. 단순 정보 제공과 같은 부분은 서버 사이드 렌더링으로 구현하고 동적인 변화가 필요한 부분은 클라이언트 사이드 렌더링으로 구현하면 된다.

[출처](https://www.youtube.com/watch?v=vM_zQLnlyKw)