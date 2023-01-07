package Day5;

import org.springframework.beans.factory.annotation.Autowired;

public class WordRegisterService {
	
	// @Resource => 객체의 이름이 일치하는 객체를 찾아간다, 생성자에 사용 불가. 프로퍼티에 사용 가능
	@Autowired // 객체에 타입이 일치하는 객체를 찾아간다
	private WordDao WordDao;
	
	// @Autowired를 프로퍼티나 메소드에 쓰고 싶으면 디폴트 생성자를 명시해야 한다! => 객체가 생성 되어야지 사용할 수 있으니까
	// 디폴트 생성자에 사용하면 상관없다.
	
	// @Resource => 생성자에 사용 불가
	@Autowired // xml에서 자동으로 스프링 컨테이너한테 찾아주라고 명령 (wordDao타입을 찾아라)
	public WordRegisterService(WordDao wordDao) {
		this.WordDao = wordDao;
	}
	
	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if(verify(wordKey)) {
			WordDao.insert(wordSet);
		} else {
			System.out.println("이미 등록");
		}
	}
	
	public boolean verify(String wordKey) {
		WordSet wordSet = WordDao.select(wordKey);
		return wordSet == null ? true : false;
	}
		
	@Autowired
	public void setWordDao(WordDao wordDao) {
		this.WordDao = wordDao;
	}
}
