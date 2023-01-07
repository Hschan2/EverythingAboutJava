package Day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// <bean id="Dao" class="Package.dao.Dao"></bean>를 어노테이션을 사용해 자바에서 만드는 방법
@Configuration // @Configuration => 스프링 컨테이너 역할을 하겠다는 것을 명시
public class ConfigClass {
	
//	<bean id="studentDao" class="Package.dao.StudentDao" />
//	id는 메소드 이름 studentDao(), class는 객체 데이터 타입 studentDao()앞에 있는 StudentDao, 그리고 return
//	@Bean => bean 객체로 만들거에요
//	public StudentDao studentDao() {
//		return new StudentDao();
//	}
	
//	----------------------------------------------------------
	
//	 <constructor-arg ref="studentRegister" />를 포함했을 때
//	@Bean
//	public StudentDao studentDao() {
//		return new StudentDao(studentRegister()); ref할 것을 ()안에 넣어주면 된다
//	}
	
//	----------------------------------------------------------------------
	
//	<bean id="dataBaseConnectioninfoDev" class="DataBaseConnectionInfo">
//	<property name="jdbcUrl" value="jdbc:oracle:thin:localhost:1521:xe" />을 포함할 때
//	<property name="userId" value="masterid" />을 포함할 때
//	<property name="userPw" value="masterpw" />을 포함할 때
//	</bean>
//	@Bean
//	public DataBaseConnectionInfo dataBaseConnectioninfoDev() {
//		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
//		infoDev.setJdbcUrl("jdbc:oracle:thin:localhost:1521:xe");
//		infoDev.setUserId("masterid");
//		infoDev.setUserPw("masterpw");
//		return infoDev;
	
//	------------------------------------------------------------------------
	
//	<bean id="informationService" class="StudentinformationService">
//		<property name="info">
//			<value>Infomation Value</value>
//		</property>
//		<property name="copyRight">
//			<value>copyRight Value</value>
//		</property>
//		<property name="sYear">
//			<value>2020</value>
//		</property>
//	</bean>
//	를 자바 클래스로 만들 때. *** 중요한 것은 ()안에 string이면 ""를 사용한 문자열로, int면 정수형으로 쓴다
//	@Bean
//	public StudentinformationService informationService() {
//		StudentinformationService info = new StudentinformationService();
//		
//		info.setInfo("Infomation Value"); => property의 name인 info를 setInfo로 그리고 value를 ("")안에 넣는다
//		info.setCopyRight("copyRight Value");
//		info.setsYear(2015);
//		
//		return info;
//	}
	
//	----------------------------------------------------------------------
	
//	<bean id="informationService" class="StudentinformationService">
//		<list>
//			<value>Mac</value>
//			<value>James</value>
//			<value>Brown</value>
//		</list>
//	</bean>
//	<list>을 사용할 때
//	@Bean
//	public StudentinformationService informationService() {
//		StudentinformationService info = new StudentinformationService();
//		
//		ArrayList<String> Students = new ArrayList<String>();
//		Students.add("Mac");
//		Students.add("James");
//		Students.add("Brown");
//		info.setStudents(Students);
//	
//		return info;
//	}
	
//	------------------------------------------------------------------
	
//	<bean id="informationService" class="StudentinformationService">
//		<map>
//			<entry>
//				<key>
//					<value>cherry</value>
//				</key>
//				<value>cherry@spring.org</value>
//			</entry>
//		</map>
//	</bean>
//	<map>을 사용할 때
//	@Bean
//	public StudentinformationService informationService() {
//		StudentinformationService info = new StudentinformationService();
//		
//		Map<String, String> administrators = new HashMap<String, String>();
//		administrators.put("cherry", "cherry@spring.org");
//		info.setAdministrators(administrators);
//	
//		return info;
//	}
	
//	--------------------------------------------------------------------
	
//	<bean id="informationService" class="StudentinformationService">
//		<map>
//			<entry>
//				<key>
//					<value>cherry</value>
//				</key>
//				<ref bean="dataBaseConnectioninfoDev" />
//			</entry>
//		</map>
//	</bean>
//	<map>에서 <ref bean="dataBaseConnectioninfoDev" />를 포함했을 때
//	@Bean
//	public StudentinformationService informationService() {
//		StudentinformationService info = new StudentinformationService();
//	
//		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
//		administrators.put("cherry", dataBaseConnectioninfoDev()); => 위의 dataBaseConnectioninfoDev 객체를 가져와 사용한다
//		info.setDbInfos(dbInfos);
//
//		return info;
//	}
}

