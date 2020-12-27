package Day2;

public class DependencyInjection {
	// 의존성 주입 => 프로그래밍에서 객체를 만들어서 외부에서 적절하게 주입하는 방식 (어느 객체에 의존한다)
	// 객체를 이용하는 방식, 생성자를 이용하는 방식 등이 있다.
	
	// private StudentDAO StudentDao; => 학생 테이블의 각 데이터 정보를 get, set한 DAO
	
	public DependencyInjection() {
		// DAO를 이용해서 데이터를 관리할 때 -> CRUD
		// 예시 ↓
		// RegisterService = new StudentRegisterService(StudentDao); // 학생 데이터 정보를 get, set한 DAO를 학생 등록을 하기 위해 인자로 넣어준다
								// => 즉, StudentDAO에 의존하고 있다. 의존성 주입이 이루어지고 있다.
		
		// => 이는 기존 자바 방식
		// 스프링 방식은 xml의 bean을 이용한다
	}
	
}
