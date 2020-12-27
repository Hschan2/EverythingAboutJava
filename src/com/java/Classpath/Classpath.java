package com.java.Classpath;

public class Classpath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
			클래스 패스 (ClassPath)
			JVM이 프로그램을 실행할 때, 클래스를 찾기 위한 기준이 되는 경로
			(JVM = 자바 가상 머신, .class 파일을 OS에 맞는 기계 언어로 변환, 메모리를 괸리하고 최적화 진행)
			자바 파일을 (터미널 환경에서 직접) 컴파일 할 때는 (예. $java -classpath ".:bin" Hello) 처럼 설정해야 한다.
			:을 이용해서 경로를 구분(윈도우는 ;으로 구분)
			최근 운영체제 상에서 클래스 패스를 설정하는 것은 지양, IDE 내에서 클래스 패스 설정 지향
		*/
		
		// 예시.
		
		/*
			컴파일을 하면 Classpath.class, A.class, B.class가 만들어진다.
			만약 A, B class를 다른 디렉토리에 있는 폴더에 넣으면 패스를 따로 설정하지 않아 에러가 발생.
			클래스 패스를 설정하지 않고 디폴트로 설정해두면 현재 경로에서 선언된 클래스를 찾는다. (여기에서 현재 경로 = Classpath.class 파일이 실행되고 있는 경로)
		*/

		A a = new A();
		a.showName();
		
		B b = new B();
		b.showName();
		
		/*
			다른 디렉토리에 있는 class 파일을 컴파일 하기 위해 클래스 패스를 확인하고 설정하자.
			
			윈도우 환경에서 클래스 패스를 확인하는 방법은 명령 프롬프트에서 set classpath를 입력하면 알 수 있다.
			
			클래스 패스를 설정하기 위해 우선 숨겨진 파일로 되어 있는 .bash_profile을 vi로 수정하는 것.
			그리고 classpath 변수를 선언해서 출력하고자 하는 A, B class의 경로를 설정해야 한다.
			
			export CLASSPATH=$CLASSPATH:/A.class(B.class)파일이 존재하는 디렉토리(파일)의 경로
			
			위 처럼 설정하면 찾고자 하는 .class 파일도 찾아달라고 말하는 것과 같다. 다만 이는 고정하는 방법
		*/
		
		/*
			* -classpath *
				클래스 패스를 지정하는 java option 중 하나이다.
				즉, 원하는 클래스를 찾을 때, 사용하는 경로이다.
				
				사용하는 예로. 
				C:\myClass 폴더를 기준으로 패키지를 찾고 A.class를 실행할 때
				java -classpath "C:\myClass" A 라고 입력하면 클래스 패스를 지정할 수 있다.
			
			-d
				-d directory
				클래스 파일을 생성할 루트 디렉토리를 지정한다.
				-d 옵션을 주지 않고 지정을 하게 되면, 소스 파일이 위치한 디렉토리에 클래스 파일을 생성시킨다.
				사용하는 예로.
				Hello.java파일이 C:Java 디렉터리에 존재하고 클래스 파일의 루트디렉터리를 C:JavaClassfiles라고 하면, 
				javac -d C:JavaClassfiles C:JavaHello.java 입니다.

				만약 -d 옵션을 사용하고자 할 때, 루트 디렉토리가 없다면 에러를 볼 수 있다.
				
			-encoding
				소스 파일에 사용된 문자열의 인코딩을 설정한다.ㄴ
				위 옵션이 설정되어 있지 않다면 기본적인 converter가 사용된다.
				
			-g
				모든 디버깅 정보를 생성한다.
				위 설정이 있지 않다면, 기본적으로 Line Number만 생성한다.
				g:{lines, vars, source}: => 명시적으로 디버깅 정보를 생성시킬 수 있다.
				(g:{라인 정보, 지역 변수, 소스 파일 정보}
				
			-nowarn
				경고 메세지를 생성시키지 않는다.
				
			-verbose
				컴파일러와 링커가 현재 어느 소스 파일이 컴파일 되어 있고, 어느 파일이 링크되어 있는지 정보를 출력한다.
				
			-deprecation
				소스 코드 내 사용된 deprecated API 위치를 출력한다.
				
			-sourcepath
				소스 파일의 위치를 지정
				
			-target
				지정된 자바 버전의 VM에서 작동되어 지도록 클래스 파일을 생성한다.
				
			-bootclasspath
				특정한 bootstrap 혹은 확장 클래스를 지정할 수 있다.
				기본적으로 자바 컴파일러는 javac이 설치된 플랫폼에 bootstrap과 확장 클래스를 통해 컴파일 작업을 수행하지만
				위 옵션을 사용하면 cross-compiling로 다른 자바 플랫폼의 bootstrap과 확장 클래스를 통해 컴파일 할 수 있는 기능을 지원한다.
				모바일 JVM에 맞도록 소스 코드를 컴파일 하기 위해 주로 사용되는 옵션
				
			-extdirs
				특정한 확장 디렉토리를 지정한다.
				주로 사용되는 옵션일 때, 각 디렉토리는 :로 의해 분리된다.
				컴파일할 시, 기술한 디렉토리의 클래스 파일을 참조한다.
		*/
		
		/*
			인텔리제이에서 확인하고 실행해보기
			
			File > Project Structure의 경로로 이동하면 파일을 찾을 수 있도록 클래스 패스로 알려주는 화면이 뜬다.
			찾고자 하는 파일이 들어있는 src/test/resource를 Test Resource Folders라고 인텔리제이한테 알려준다.
			
			그리고 경로를 설정할 때,
			@ContextConfiguration(locations = "classpath:spring/applicationContext-test.xml")
			를 사용해서 찾고자 하는 파일의 경로를 설정할 수 있다.
			
		*/
		
		/*
			접근 제어 지시자 (public > default > protected > private)
			
			public
				어디서든 접근 가능한 인스턴스 변수 및 메소드
				
			private
				인스턴스 변수와 메소드는 선언된 클래스 내부에서 접근 가능
				
			protected
				상속 받은 클래스에서 접근을 허용
			
			default (접근 제어 지시자를 선언하지 않은 경우)
				동일 패키지 내에서 접근 가능
				
			지시자		클래스 내부		동일 패키지		상속받은 클래스		이외의 영역
			private		가능			불가능		불가능			불가능
			default		가능			가능			불가능			불가능
			protected	가능			가능			가능				불가능
			public		가능			가능			가능				가능
		*/
	}

}
