# CSR과 SSR

## SPA와 MPA
오늘 날 웹 애플리케이션을 개발할 때는 자바스크립트 프레임워크를 사용하여 SPA를 개발한다.   

* SPA (Single Page Application): 하나의 페이지를 사용하며 헤더는 고정되어 있고 메뉴에 따라 하단의 내용이 변화하는 페이지   
* MPA (Multi Page Application): 메뉴에 따라 서버로부터 새로운 HTML을 새로 받아 페이지 전체를 렌더링하는 전통적인 방법   

MPA의 방법은 비효율적인 면이 많아서 SPA를 사용하는 추세이다.   

## CSR과 SSR
CSR과 SSR은 SPA와 MPA과 연관성을 가지고 있다.   

SPA에서 렌더링 방식으로 CSR을 채택하고 있고, MPA에서 렌더링 방식은 SSR을 채택하고 있다.   

SPA에서는 필요한 정적 데이터를 초반에 모두 가져오고 이 후 새로운 요청이 있을 때, 필요한 데이터만 가져와 클라이언트에서 페이지를 갱신하여 자연스럽게 렌더링 방식으로 CSR을 사용하고 있다.   

반대로 MPA는 새로운 요청이 있을 때마다 서버에서 이미 렌더링이 되어 있는 정적 리소스를 가져오기 때문에 렌더링 방식으로 SSR을 사용하고 있다.   

* CSR 렌더링
    * React
    * Angular
    * Vue
* SSR 렌더링
    * PHP
    * JSP   

## CSR과 SSR 내용 들어가기
<b>Client Side Rendering</b>은 클라이언트에서 렌더링을 하는 방식이다.   

<b>Server Side Rendering</b>은 서버에서 렌더링을 하는 방식이다.   

여기서 볼 때, 어디서 렌더링을 하는가에 따라 차이가 발생한다.   

CSR과 SSR을 보면 SSG도 언급된다. SSG는 <b>Static Site Generation</b>이며, SSR처럼 서버에서 요청을 하는 것은 같으나 즉시 만드는가 미리 만들어 놓는가를 따진다. 즉, 언제 만들어 주는가의 차이가 존재한다.   

SSR은 요청할 때 즉시 만들어 데이터가 달라져서 미리 만들기 어려운 페이지에 적합하다. 반대로 SSG는 미리 만들어두기 때문에 바뀔 일이 거의 없는 페이지에 적합하다. (캐싱)   

### CSR의 동작 과정과 특징
사용자가 웹 사이트에 방문하여 브라우저에 컨텐츠를 서버에 요청을 한다. 그리고 서버는 빈 뼈대만 갖추어져 있는 HTML을 응답한다. 브라우저가 연결된 자바스크립트 링크를 통해서 서버로부터 다시 자바스크립트 파일을 받고 이를 이용하여 동적으로 페이지를 만들어서 브라우저에 띄운다.   

* CSR 특징
    * 브라우저에서 자바스크립트를 다운로드하고 동적으로 페이지를 생성하여 초기 로딩 속도가 느리다. => 이 후 구동 속도는 빠르다.
    * 서버에서 처음으로 응답을 할 때, 빈 뼈대의 HTML를 보내기 때문에 서버 부하가 적고 사용자 경험을 향상시킨다.
    * 그러나 빈 뼈대를 보내는 것은 웹 크롤러가 봤을 때, 내용이 없다는 것으로 인식하여 검색 엔진 최적화에 불리하다. (SEO 불리)
    * 웹 크롤러가 읽을 수 없는 자바스크립트 파일을 위해서 읽을 수 있도록 SSR 렌더링을 사용하는 것을 추천하기도 한다.

### SSR의 동작 과정과 특징
사용자가 웹 사이트를 방문하여 브라우저에서 서버로 컨텐츠를 요청한다. 서버는 즉시 페이지에 CSS 등 필요한 데이터 갖추고 렌더링 준비를 마친 HTML과 자바스크립트 코드를 브라우저도 보내고 브라우저는 바로 전달 받은 페이지를 띄운다. 그리고 브라우저가 자바스크립트 코드를 다운로드하고 HTML에 자바스크립트 로직을 연결한다.   

* SSR 특징
    * 모든 데이터가 담겨진 채로 브라우저로 보내기 때문에 검색 엔진 최적화에 유리하다.
    * 자바스크립트 코드를 다운로드하고 실행하기 전에 사용자는 화면을 볼 수 있다. => 초기 구동 속도가 빠르다.
    * 그러나 버튼을 누르거나 이동하려고 할 때, 자바스크립트 코드를 다운로드 받을 때까지 반응이 없는 문제가 발생할 수 있다. (TTV !== TTI)

### CSR과 SSR의 장단점
* CSR
    * 장점
        * 화면 깜빡임이 없다.
        * 초기 로딩 이후에 구동 속도가 빠르다.
        * TTV와 TTI 사이 간극이 없다.
        * 서버 부하를 분산할 수 있다.
    * 단점
        * 초기 로딩 속도가 느리다.
        * 검색 엔진 최적화(SEO)에 불리하다.
* SSR
    * 장점
        * 초기 구동 속도가 빠르다.
        * 검색 엔진 최적화(SEO)에 유리하다.
    * 단점
        * 화면 깜빡임이 존재한다.
        * TTV와 TTI 사이 간극이 있다.
        * 서버 부하가 있다.   

## CSR 단점 보완 방법
* Code Splitting, Tree-Shaking, Chunk 분리하기
    * 자바스크립트 번들의 크기를 줄여 초기 DOM 생성 속도를 줄인다.
    * 초기 로딩 속도를 보완할 수 있다.
* Pre-Rendering 하기
    * 검색 엔진 최적화(SEO)를 개선할 수 있다.
* SSR, SSG 도입하기
    * 초기 로딩 속도를 보완할 수 있다.
    * 검색 엔진 최적화(SEO)를 개선할 수 있다.

## CSR에 SSR, SSG 도입하기
* Express.js 사용하여 별도의 서버를 직접 운영하기
* 타입스크립트 설정을 고려해야 할 경우에 Next.js를 사용하여 운영하기
    * 그러나 위의 방법들은 프론트엔드 개발자가 어려움을 느낄 수 있다.   

* ReactJS 기반
    * Next.js 프레임워크 사용
        * 페이지별로 SSR과 SSG를 선택할 수 있다.
    * GatsbyJS 프레임워크 사용
        * SSG에 최적화되어 있다.
        * 다양한 플러그인이 존재하여 사용할 수 있다.
        * 빌드 시점에 Static HTML를 만들어주어 페이지가 적다.
* AngularJS 기반
    * Universal 프레임워크 사용
        * Angular에서 SSR을 사용할 수 있도록 만들어준다.
        * Angular 4버전부터 코어 모듈에 포함되어 있다.
* VueJS 기반
    * Nuxt.js 프레임워크 사용
        * Next.js 프레임워크에서 영감을 받은 프레임워크   

위의 프레임워크들이 CSR에 SSR 혹은 SSG 도입을 훨씬 편리하게 만들어주지만 csr에 비해 코드 복잡도가 높고 직접 제어할 수 없는 블랙박스 영역이 존재하는 단점이 존재한다.   

## Isomorphic App & Universal Rendering
* Isomorphic App
    * 서버와 클라이언트에서 동일한 코드가 동작하는 것을 의미한다.
    * 예상과 다른 결과를 가져올 수 있다.
    * 그러나 초기 로딩 속도를 보완할 수 있다.
    * 검색 엔진 최적화(SEO)를 개선할 수 있다.
    * CSR의 장점을 그대로 가져올 수 있다.

## 결론
서비스의 셩걱에 따라 렌더링 방식을 고려하면 된다.   

검색 엔진 최적화(SEO)를 고려하지 않는 애플리케이션이라면 CSR 렌더링 방식을 사용하는 것이 적합하다.   

반대로 회사 홈페이지며 모두 같은 내용을 봐야하며 업데이트가 잦다면 SSR 렌더링 방식을 사용하는 것이 적합하다.   

그러나 회사 홈페이지며 모두 같은 내용을 봐야하지만 업데이트가 거의 없다면 SSG 렌더링 방식을 사용하는 것이 적합하다.   

그리고 빠른 인터랙션이 필요하고 화면 깜빡임이 존재하면 안되고 사용자에 따라 페이지 내용이 달라져야 하며 상위 노출을 원한다면 CSR 렌더링 방식과 SSR 렌더링 방식을 같이 사용하는 것이 적합하다. (Universal Rendering)   

[CSR & SSR](https://www.youtube.com/watch?v=YuqB8D6eCKE)