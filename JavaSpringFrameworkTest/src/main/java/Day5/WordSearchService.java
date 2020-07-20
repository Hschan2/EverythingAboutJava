package Day5;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class WordSearchService {
	
	// @Resource => 객체의 이름이 일치하는 객체를 찾아간다, 생성자에 사용 불가. 프로퍼티에 사용 가능
	@Autowired // 객체의 타입이 일치하는 객체를 찾아간다
	private WordDao WordDao;
	
	// @Autowired를 프로퍼티나 메소드에 쓰고 싶으면 디폴트 생성자를 명시해야 한다!
	// 디폴트 생성자에 사용하면 상관없다.
	
	// @Resource => 생성자에 사용 불가
	@Autowired // xml에서 자동으로 스프링 컨테이너한테 찾아주라고 명령 (wordDao타입을 찾아라)
	public WordSearchService(WordDao wordDao) {
		this.WordDao = wordDao;
	}
	
	public WordSet searchWord(String wordKey) {
		if(verify(wordKey)) {
			return WordDao.select(wordKey);
		} else {
			System.out.println("WordKey 정보 사용 가능");
		}
		return null;
	}
	
	public boolean verify(String wordKey) {
		WordSet wordSet = WordDao.select(wordKey);
		return wordSet != null ? true : false;
	}
		
	@Autowired
	public void setWordDao(WordDao wordDao) {
		this.WordDao = wordDao;
	}
}
