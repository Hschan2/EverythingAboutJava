package Day2;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ElectronicCarToy carToy = new ElectronicCarToy();
		ElectronicRobotToy robotToy = new ElectronicRobotToy();
		
		// DAO의 데이터를 가져오기 위한 xml파일을 사용할 때
		// GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		// 이를 가져다 쓸 때 ctx.getbean하면 된다
	}

}
