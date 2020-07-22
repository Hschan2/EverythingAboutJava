package Day6;

public class AutowiredRequireClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 의존 객체 자동 주입 체크
		
		// @Autowired를 했는데 xml파일에 bean 객체가 없을 때 Exception이 안 생기게 하는 방법
		// @Autowired(require = "false")...
		
//		@Autowired(required = false) xml파일에 자동 의존할 bean객체가 있으면 가고 없으면 넘어가라
//		private WordDao wordDao;
	}

}
