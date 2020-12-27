package Day4;

public class BeanRangeClass {
	// SingleTone(싱글톤)
//	스프링 컨테이너에서 생성된 빈(Bean)객체의 경우 동일한 타입에 대해서는 기본적으
//	로 한 개만 생성이 되며, getBean() 메소드로 호출될 때 동일한 객체가 반환
	
	// ProtoType(프로토타입)
//	싱글톤 범위와 반대의 개념도 있는데 이를 프로토타입(Prototype) 범위라고 한다.
//	프로토타입의 경우 개발자는 별도로 설정을 해줘야 하는데, 스프링 설정 파일에서 빈
//	(Bean)객체을 정의할 때 scope속성을 명시해 주면 된다
	
	// scope를 prototype으로 명시 
	
//	<bean id="dependencyBean" class="scope.ex.DependencyBean"
//	scope="prototype">
//		<constructor-arg ref="injectionBean" />
//		<property name="injectionBean" ref="injectionBean" />
//	</bean>
}
