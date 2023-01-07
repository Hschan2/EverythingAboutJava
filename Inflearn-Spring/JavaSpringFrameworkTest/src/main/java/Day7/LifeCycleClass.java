package Day7;

import org.springframework.context.support.GenericXmlApplicationContext;

public class LifeCycleClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 스프링 컨테이너 생명주기 => xml 파일을 불러오기 할 때 생성
		
		// 스프링 컨테이너 객체의 생성 시기와 bean 객체 생성 시기는 같다
		// .close() => 컨테이너 (+ bean객체) 객체 소멸. 메모리에서 소멸
		
		// 스프링 컨테이너 생성
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:LifeCycle.xml");
		
		
	}

}
