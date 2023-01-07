package Day9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBaseClass.class, ServiceClass.class}) // DataBaseClass와 ServiceClass 클래스를 참조하겠다
public class DaoClass {
	
//	@Bean
//	public StudentDao studentDao() {
//		return new StudentDao();
//	}
//	
//	@Bean
//	public StudentDao studentDao() {
//		return new StudentDao(studentRegister());
//	}
}
