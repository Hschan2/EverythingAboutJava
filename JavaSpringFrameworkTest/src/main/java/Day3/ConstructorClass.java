package Day3;

public class ConstructorClass {
	// 생성자를 이용한 의존 객체 주입
	
//	<bean id="studentDao" class="ems.member.dao.StudentDao" ></bean>
//	위에는 아래 생성자가 가져와 사용하기 위한 선언
	
//	public StudentRegisterService(StudentDao studentDao) {
//		this.studentDao = studentDao;
//	}
//	위를 스프링에서 생성자 xml로 표한혀려면 아래처럼
//	<bean id="registerService" class="ems.member.service.StudentRegisterService">
//		<constructor-arg ref="studentDao" ></constructor-arg>
//	</bean>
//	
//	public StudentModifyService(StudentDao studentDao) {
//		this.studentDao = studentDao;
//	}
//	<bean id="modifyService" class="ems.member.service.StudentModifyService">
//		<constructor-arg ref="studentDao" ></constructor-arg>
//	</bean>
//	
//	public StudentDeleteService(StudentDao studentDao) {
//		this.studentDao = studentDao;
//	}
//	<bean id="deleteService" class="ems.member.service.StudentDeleteService">
//		<constructor-arg ref="studentDao" ></constructor-arg>
//	</bean>
//	
//	public StudentSelectService(StudentDao studentDao) {
//		this.studentDao = studentDao;
//	}
//	<bean id="selectService" class="ems.member.service.StudentSelectService">
//		<constructor-arg ref="studentDao" ></constructor-arg>
//	</bean>
//	
//	public StudentAllSelectService(StudentDao studentDao) {
//		this.studentDao = studentDao;
//	}
//	<bean id="allSelectService" class="ems.member.service.StudentAllSelectService">
//		<constructor-arg ref="studentDao" ></constructor-arg>
//	</bean>
}
