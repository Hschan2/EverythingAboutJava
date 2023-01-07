package Day1;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 지금까지 사용했던 자바 프로젝트의 자바 코드
		/*
		transpotationWalk transpotationWalk = new transpotationWalk();
		
		transpotationWalk.move();
		*/
		
		// 스프링에서 사용하는 효율적 코드(xml파일 이용)
		
		// 컨테이너에 접근하기 위한 방법
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		// bean을 가져오기 (bean의 id값을 가져와서 class를 뿌린다)
		transpotationWalk transpotationWalk = ctx.getBean("tWalk", transpotationWalk.class);
		transpotationWalk.move();
		
		// 쓰고나서 반환(꼭)
		ctx.close();
		
		
	}

}
