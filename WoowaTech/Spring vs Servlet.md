# Spring vs Servlet

## Servlet
동적인 페이지를 만들기 위해 웹 서버에 붙이는 프로그램 중 하나이다.   

Servlet이 요구하는 구현 규칙을 지켜주면서 Servlet을 정의하면 HTTP 요청 정보를 쉽게 사용할 수 있으며, 처리 결과를 쉽게 응답으로 변환할 수 있다. 즉. Servlet를 이용해서 웹 요청을 다루게 되면 개발자등리 집중해야 하는 비즈니스 로직 (처리 로직)에 더 집중할 수 있다.

### Servlet Container와 Servlet이 호출되는 과정
* Servlet Container: Servlet을 담고 관리하는 바구니 역할을 하는 것. 즉, 생명주기를 관리하는 객체 (생성, 소멸)    

1. Servlet Request / Servlet Response 객체 생성   
2. 설정 파일을 참고하여 매핑할 Servlet을 확인   
3. 해당 서블릿 인스턴스 존재의 유무를 확인하여 없으면 생성 (Init())   
4. Servlet Container에 스레드를 생성하고, res와 req 인자로 Servlet 실행   
5. 응답을 처리한 후, Request와 Response 객체를 소멸한 후 종료   

Servlet은 생성된 후, 소멸되지 않는데 이유는 싱글턴이기 때문이다. 한 번 실행한 후 대기하고 있다가 나중에 Servlet Container에서 다시 호출될 때 사용이 된다.   

#### 만약 여러 개의 요청이 들어올 경우
멀티 스레드로 요청을 처리하게 된다. 여러 스레드가 생성이 되고 스레드 당 다른 서블릿이 처리할 수 있고 혹은 여러 스레드에서 한 서블렛의 여러 요청을 동시에 처리할 수도 있다.   

그러나 비용이 많이 들고 Context Switch가 많은 오버헤드를 일으킨다.   

스프링 MVC도 프론트 컨트롤러 패턴을 따르고 모든 요청을 받는 전면 컨트롤러 서블릿을 Dispatcher Servlet이라고 부르게 된다. 즉, 서블릿은 하나만 두고 모든 요청을 다 받을 수 있도록 하는 것이다.   

#### Dispatcher Servlet이 Web 요청을 처리하는 과정
1. Dispatcher Servlet는 모든 요청을 받기   
2. Handler Mapping이 들어오는 요청을 처리할 때, 컨트롤러를 찾아서 반환   
3. Handler Adapter는 컨트롤러의 메소드를 호출하여 처리 로직을 수행   
4. 처리 결과를 Model And View 객체로 변환하여 Dispatcher Servlet에 전달   
5. Dispatcher Servlet는 View Resolver를 이용해 View를 찾거나 생성   
6. View에 Model로 들어왔던 데이터를 넣어서 응답 결과 생성 요청   
7. JSP or Thymeleaf에 데이터를 담은 출력 파일로 출력   

하는 일이 많아진 것 같고 개발자가 해야할 일이 많아진 것 같지만, 사실상 개발자가 집중하면 되는 곳은 Controller와 Handler이다. 특히, Handler Adapter에서 Handler를 처리하는 곳에 집중하면 된다. 이유는 나머지는 스프링이 알아서 처리해주기 때문이다. 나머지 역할을 하는 객체들은 Dispatcher Servlet이 스프링 컨테이너로부터 주입을 받아서 사용하고 동작하게 된다.   

##### Dispatcher Servlet의 두 가지
* Servlet WebApplicationContext: 웹 요청 처리에 필요한 관련 객체들이 담김
* Root WebApplicationContext: 웹 요청 처리 관련된 빈 외에 컴포넌트들 (서비스, 레포지토리) 관련 객체들이 관리   

그리고 해당 컨테이너가 개발에 필요한 부분이나 Dispatcher Servlet이 요청을 처리할 때, 필요한 부분은 알아서 주입하게 된다.   

쉽게 말해서, 서블릿 설정 파일만 잘 작성한다면 설정한 대로 생성된 객체가 스프링 컨테이너에서 관리가 되고 필요한 부분에서 주입 받아서 Dispatcher Servlet가 알아서 사용할 수 있게 된다는 것이다.   

### 스프링으로 웹 요청을 처리하는 것은
스프링 MVC에서 제공하는 Dispatcher Servlet과 웹 요청 처리 관련 구현체들을 사용할 수 있다는 것과 동시에 스프링 컨테이너. 즉, 스프링 IoC를 사용해서 개발할 수 있다는 것이다.   

그리고 최종적인 목적은 개발자에게 Handler. 즉, 개발자가 집중해야 하는 요청 처리 로직에만 신경을 쓸 수 있도록 하기 위함이다.   

[출처](https://www.youtube.com/watch?v=calGCwG_B4Y)