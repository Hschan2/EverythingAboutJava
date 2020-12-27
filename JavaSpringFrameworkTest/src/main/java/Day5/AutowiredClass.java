package Day5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AutowiredClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 의존객체 자동 주입
//			스프링 설정 파일에서 의존 객체를 주입할 때 <constructor-org> 또는 <property> 태그로 의존 대상 객체를 명시하지 않아도 스프링 컨테이너
//			가 자동으로 필요한 의존 대상 객체를 찾아서 의존 대상 객체가 필요한 객체에 주입해 주는 기능이다.
//			구현 방법은 @Autowired와 @Resource 어노테이션을 이용해서 쉽게 구현할 수 있다
		// 객체를 만들어 놓으면 스프링 컨테이너가 자동으로 삽입을 한다
		
		// @Autowired => 주입하려고 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다
		
		String[] keyWords = {"c", "c++", "java", "spring"};
		String[] values = {"C언어", "C플러스플러스", "자바", "스프링"};
 		
		// Autowired.xml 사용
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:Autowired.xml");
		
		// Autowired.xml에서 registerService의 id를 가진 bean을 가져와라
		WordRegisterService registerService = ctx.getBean("registerService", WordRegisterService.class);
		
		for(int i=0; i<values.length; i++) {
			WordSet wordSet = new WordSet(keyWords[i], values[i]);
			registerService.register(wordSet);
		}
		
		// Autowired.xml에서 searchService의 id를 가진 bean을 가져와라
		WordSearchService searchService = ctx.getBean("searchService", WordSearchService.class);
		
		System.out.println("\n\n-----------------------------------");
		
		for(int i=0;i<keyWords.length; i++) {
			WordSet wordSet = searchService.searchWord(keyWords[i]);
			System.out.print(wordSet.getWordKey() + "\t: "); // \t => 탭(공백), wordSet.getWordKey() => key값을 가져와라
			System.out.println(wordSet.getWordValue()); // value 값을 가져와라
		}
		
		System.out.println("\n\n");
	}

}
