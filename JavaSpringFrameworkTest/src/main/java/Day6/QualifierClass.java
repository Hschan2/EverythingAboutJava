package Day6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class QualifierClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 다수의 빈(bean) 객체 중 의존 객체의 대상이 되는 객체를 선택하는 방법
		
		// 동일한 객체가 2개 이상일 때 스프링 컨테이너는 자동 주입 대상 객체를 판단하지 못해 에러(Exception) 발생
		// xml에서 가장 우선적으로 사용할 <bean> 안에 <qualifier>
		
//		@Autowired
//		@Qualifier("usedDao") 동일한 객체를 가진 bean이 있을 때 qualifier를 usedDao를 지정했을 때 QualifierANDInjection.xml의 해당 bean이 찾아올 곳
//		private WordDao wordDao;
	}

}
