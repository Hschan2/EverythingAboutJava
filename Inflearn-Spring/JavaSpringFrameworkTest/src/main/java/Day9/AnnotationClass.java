package Day9;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		XML을 이용한 스프링 설정 파일 제작을 Java 파일로 제작할 수 있는 방법
//		
//		Java 파일 분리
//		=> 기능별로 분리한다. 예를 들어, Dao | Service 등으로 분리
		
//		각자 역할마다 분리한 Bean 클래스를 불러오기
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DaoClass.class, DataBaseClass.class, ServiceClass.class);
	
//		DaoClass 클래스에서 DataBaseClass 클래스와 ServiceClass 클래스를 Import를 했기 때문에 이처럼 사용할 수 있다.
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DaoClass.class);
	}

}
