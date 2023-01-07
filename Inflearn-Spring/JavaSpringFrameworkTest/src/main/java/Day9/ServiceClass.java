package Day9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceClass {
	
//	@Bean
//	public DataBaseConnectionInfo dataBaseConnectioninfoDev() {
//		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
//		infoDev.setJdbcUrl("jdbc:oracle:thin:localhost:1521:xe");
//		infoDev.setUserId("masterid");
//		infoDev.setUserPw("masterpw");
//		return infoDev;
//	} 
//	dataBaseConnectioninfoDev()를 사용하기 위해서는 위의 부분을 가지고 와야 한다
	
//	@Autowired 자동으로 해당 객체를 찾아 주입하기 위해
//	DataBaseConnectionInfo dataBaseConnectioninfoDev 를 사용하여 가져온다.
	
//	@Bean
//	public StudentinformationService informationService() {
//		StudentinformationService info = new StudentinformationService();
//		
//		info.setInfo("Infomation Value");
//		info.setCopyRight("copyRight Value");
//		info.setsYear(2015);
//		
//		return info;
//	}
//	
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
//	
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
//	
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
//	dataBaseConnectioninfoDev()를 사용하기 위해서는 가지고 와야 한다. (참조)
	
}
