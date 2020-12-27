package com.java.servletandspring;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Servlet vs Spring
		
		/*
		
		1. Servlet - 웹 애플리케이션을 만들 때 필요한 인터페이스 (Spring Web MVC -> Spring Framework에 있는 모듈의 Web Service를 MVC 패턴을 사용해서 만든다 => Servlet 사용)
		
		역사
		1. 초기 웹 프로그래밍은 정적 데이터만 전달(html) -> 사람들은 본인이 접속했음을 인식하는 처리를 바람 ->  CGI 등장 (동적 데이터 처리 방식), Web Server(ex. Apache)와 CGI 구현체(ex. C, PHP)의 서로 규약을 만들기 위해 등장 -> 본인의 접속을 인식하는 처리 등장
		2. CGI의 문제 -> 1) Request가 올 때마다 프로세스(메모리에 적재된 실행중인 프로그램 인스턴스) 생성 => 쓰레드로 생성해서 효율적으로 해결해야, 2) 같은 구현체를 사용해도 Request, 쓰레드가 다르면 하나씩 구현체가 생김 => 싱글톤 패턴으로 바뀌게 됌(Servlet) -> 웹 애플리케이션 서버가 생김
			* 웹 서버 = 정적 데이터 처리, 웹 애플리케이션 서버 = 동적 데이터 처리
			* 웹 컨테이너가 Request가 들어오면 Request마다 쓰레드 생성하고 Servlet 구현체를 연결 -> Servlet안에 메서드가 정의되어 있고 그 메서드를 호출 (Init, Service, Destory..)
			* Service 메서드 -> Servlet은 인터페이스고 그것을 구현한 것이 http 서블릿. http 메서드에 따라 안에 있는 메소드(Doget(), DoPost() ...)를 웹 컨테이너가 호출. 그 안에 내용은 개발자가 구현
		
		3. Spring Web MVC에서 Servlet을 어떻게 사용하는가? -> Dispatcher Servlet으로 사용
			* 처음에 Request가 오면 Dispatcher Servlet로 이동 (Servlet을 web.xml에 등록하여 사용) -> Dispatcher Servlet도 xml에 등록하여 사용
			* 핸들러 매핑(Handler Mapping)을 통해 Request에 맞는 Controller를 찾아준다 (Spring Framework에서 제공)
				(Servlet을 등록하면 그 Servlet이 사용할 설정 파일이 자동으로 등록. 어떤 핸들러 매핑을 사용하는지 등록하게 되는 것)
			* 자주 사용하는 방식은 어노테이션. @RequestMapping 어노테이션을 지어 놓으면 해당 메서드 요청이 들어오면 해당 메서드를 찾아오게 만듬 -> 그게 바로 HandlerMapping
			* 적절한 컨트롤러를 찾았으면 해당 컨트롤러를 호출하는 데 그것을 HandlerAdapter가 처리 -> 결과를 Model(컨트롤러가 처리)AndView(그 결과를 담는 페이지) 형식으로 생성
				(보통 컨트롤러가 View를 String 타입으로 전달하기 때문에 ViewResolver가 그 이름을 가지고 실제 View를 찾아주고 컨트롤러에서 만든 데이터(Model)를 View에 심어준다 그리고 Dispatcher Servlet가 클라이언트에 전달
			* 스프링 웹 MVC가 없을 때 -> url마다 servlet이 하나씩 필요. 만들 때마다 xml을 다 매핑해야 함. View로 보내 주는 것까지 직접 생성
			* 스프링 웹 MVC가 있을 때 -> Dispatcher Servlet 하나만 있으면 위의 없을 때의 일을 할 필요가 없다. 관리가 줄어든다. View를 강제로 분리시킴
			
		*/
	}

}
