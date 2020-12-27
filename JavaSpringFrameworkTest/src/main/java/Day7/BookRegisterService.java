package Day7;

import org.springframework.beans.factory.annotation.Autowired;

// 사용 방법 예시
public class BookRegisterService implements InitializingBean, DisposableBean {
	
	@Autowired
	private BookDao bookDao;
	
	public BookRegisterService() {}
	
	public void register(Book book) {
		bookDao.insert(book);
	}

	// ex. 데이터베이스 등 무엇을 인증해야 할 때 사용
	// 인터페이스를 사용해 스프링 컨테이너(bean 컨테이너) 생성과 소멸 때 기능 실행 (implements InitializingBean, DisposableBean => 인터페이스 받기)
	@Override
	public void destroy() throws Exception{
		// TODO Auto-generated method stub
		System.out.println("Bean 객체 소멸");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Bean 객체 생성");
	}
	
	// xml파일에서 bean 객체에 적용했을 때 호출하기
	public void initMethod() {
		// TODO Auto-generated method stub
		System.out.println("Bean 객체 생성");
	}
	
	public void destroyMethod() {
		// TODO Auto-generated method stub
		System.out.println("Bean 객체 소멸");
	}
}
