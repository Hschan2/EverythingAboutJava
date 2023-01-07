package Day3;

public class SetterClass {
	// setter를 이용한 의존 객체 주입
	
//	public void setJdbcUrl(String jdbcUrl) {
//		this.jdbcUrl = jdbcUrl;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public void setUserPw(String userPw) {
//		this.userPw = userPw;
//	}
//	
//	위의 setter로 선언한 객체들을 스프링의 xml로 이용한 방법
//	setJdbcUrl에서 set을 없애고 J를 소문자로 바꿔서 property name에 넣는다
//	value는 파라미터로 들어오는 값(string jdbcUrl 등)을 넣는다
	
//	<bean id="dataBaseConnectionInfoDev" class="ems.member.DataBaseConnectionInfo">
//		<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
//		<property name="userId" value="scott" />
//		<property name="userPw" value="tiger" />
//	</bean>
}
