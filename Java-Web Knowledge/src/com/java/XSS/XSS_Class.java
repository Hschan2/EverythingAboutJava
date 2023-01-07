package com.java.XSS;

public class XSS_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		XSS (Cross-Site Scripting)
//		가장 널리 알려진 웹 보안 취약점 중 하나
//		악의적인 사용자가 공격하려는 특정 사이트에 악성 스크립트를 삽입할 수 있는 보안 취약점
//		XSS를 통해 C&C(좀비 PC에 명령을 내리거나 악성 코드를 제어하는 서버)로 리다이렉트하거나 사용자의 쿠키를 탈취하여 세션 하이재킹 공격을 할 수 있다.
//		
//		1. Stored XSS
//		공격자가 제공한 데이터가 *서버에 저장된 후* 지속적으로 서비스를 제공하는 정상 페이지에서 *다른 사용자에게 스크립트가 노출되는* 기법
//		해커가 스크립트를 서버에 전송하고 서버는 스크립트 자체를 저장 -> 사용자가 스크립트가 담긴 게시물을 읽음 -> 해킹
//		
//		2. Reflected XSS
//		웹 애플리케이션의 지정된 파라미터를 사용할 때 발생하는 취약점을 이용한 공격법
//			'검색어'같은 쿼리스트링을 URL에 담아 전송했을 때, 서버가 필터링을 거치지 않고 쿼리에 포함된 스크립트를 응답 페이지에 담아 전송함으로써 발생 
//		공격용 스크립트가 대상 웹 사이트에 있지 않고 다른 매체(타 사이트나 이메일)에 포함될 수 있다
//		Stored XSS와 달리 데이터 베이스에 스크립트가 저장되지 않고 응답 페이지로 바로 클라이언트에 전달
//		해커가 악의적인 스크립트를 URL에 담아 공유 -> 사용자가 클릭하면 스크립트가 삽입된 페이지로 연결 -> 해킹
//		
//		XSS 공격 방지 기법
//		HTML5에서는 innerHTML을 통해 주입한 스크립트는 실행되지 않음
//			예. <script>alert("hello");</script>
//		onerror이벤트 속성을 통한 스크립트 주입 공격이 가능
//			<img src=x onerror=alert('xss attack')>
//		
//		그러므로 꼭 필요한 경우가 아니라면 innerHTML을 통해 검증되지 않은 데이터를 넣지 말 것
//		textContent, innerText를 사용하면 스크립트가 주입되지 않음
//		
//		실 사용 서비스에서 처리하는 방법
//		예. <img src=x onerror=alert('xss attack')>라고 입력을 했을 때, 서버에서 사용하지 못하도록 치환하여 처리
//		
//		Vue.js처럼 프레임워크 사용 시 문제 발생하는가?
//		v-html 디렉티브 사용 시 보안 취약점 발생할 수 있음
//		
//		쿠키에 httpOnly 옵션을 활성화
//		이를 활성화 하지 않을 경우 스크립트를 통해 쿠키에 접근할 수 있어 Session Hijacking 취약점 발생
//			예. https://hacker.site?name=<script>alert(document.cookie);</script>
//		악의적인 클라이언트가 쿠키에 저장된 정보 (Session ID, Token)에 접근하는 것을 차단
//		LocalStorage에 Session ID 등 민감한 정보를 저장하지 말아야 함
//		
//		XSS 특수 문자를 치환
//		from  &    <    >     *      '      /
//		to 	&amp; &lt; &gt; &quot; &#x27; &#x2F;
//		
//		Spring에서는 직접 구현하지 않아도 오픈소스 라이브러리를 사용하면 처리 가능
//		Lucy XSS Servlet Fliter.
//		@RequestBody로 전달되는 JSON 요청에 대해서는 처리해주지 않음 (처리법 -> jojoldu 개발자님 블로그 참조 (470번))
		
	}

}
