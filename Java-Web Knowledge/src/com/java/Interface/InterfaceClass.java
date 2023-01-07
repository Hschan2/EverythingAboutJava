package com.java.Interface;

public class InterfaceClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
			자바와 스프링 공부를 하면서 인터페이스를 사용하는 이유가 궁금해졌다.
			자바와 스프링에서 인터페이스를 사용하는 이유는 무엇일까?
		*/
		
	}
	
	// 1. Cooperation, 협업에서 필요하다.
	
	// 팀원 1이 만든 기능
	public class EnglishTranslate {
		public String KoreanToEnglish(String str) {
			// 한국어를 영어로 번역하는 기능
			return str;
		}
		public String EnglishToKorean(String str) {
			// 영어를 한국어로 번역하는 기능
			return str;
		}
	}
	
	// 팀원 2가 만든 기능
	public class ChineseRendering {
		public String translateChinese(String str) {
			// 한국어를 중국어로 번역하는 기능
			return str;
		}
		public String translateKorean(String str) {
			// 중국어를 한국어로 번역하는 기능
			return str;
		}
	}
	
	/*
		아래에서 보듯이 똑같이 번역 기능을 만드는데 메서드명과 클래스명을 다르게 개발하였다.
		만약에 아래의 클래스를 가져와서 Main Class에서 개발을 시작하게 된다면 아래처럼 좋은 코드로 작성되지 못한다.
		
		EnglishTranslate trans = EnglishTranslate();
		String str1 = trans.koreanToEnglish(str);
		
		ChineseRendering rendering = ChineseRendering();
		String str2 = rendering.translateChinese(str);	
	*/

	// 만약 인터페이스를 이용한다면 아래처럼 작성할 수 있다.
	
	public class EnglishTranslaton implements Translation {
		
		@Override
		public String translate(String str) {
			// TODO 한국어를 번역
			return null;
		}

		@Override
		public String translateInto(String str) {
			// TODO 한국어로 번역
			return null;
		}
	}
	
	public class ChineseTranslation implements Translation {

		@Override
		public String translate(String str) {
			// TODO 한국어를 번역
			return null;
		}

		@Override
		public String translateInto(String str) {
			// TODO 한국어로 번역
			return null;
		}
	}
	
	/*
		위에서 볼 수 있듯이 클래스명을 제외한 메소드 명은 동일하다. 즉, 혼란을 줄일 수 있다.
		그리고 아래처럼 Main Class에서 호출할 때, 아래처럼 좋은 코드로 불러올 수 있다.
		로직을 재활용하기 좋다.
	*/
	
	/*
		Translation trans = EnglishTranslation();
		String str = trans.translate(str);
		
		Translation trans = ChineseTranslation();
		String str = trans.translate(str);
	*/
	
	// 2. 교체가 용이하다.
	
	public class SHA512Crypto implements Crypto {

		@Override
		public String encrypt(String str) {
			// TODO SHA512 방식으로 암호화
			return null;
		}

		@Override
		public String decrypt(String str) {
			// TODO SHA512 방식으로 복호화
			return null;
		}	
	}
	
	/*
		기존 SHA512 방식으로 호출할 때
			Crypto crypto = new SHA512Crypto();
			String enc = crypto.encrypt("1234");
		
		기존 SHA512 방식이 안전하지 않아 다른 것으로 교체하려고 할 때
		아래처럼 개발하기 위해 이름을 쉽게 바꿀 수 있다.
			Crypto crypto = new SuperPowerCrypto();
			String enc = crypto.encrypt("1234");
	*/

	// 3. 다중 상속
	
	/*
	 	실제로 자바에서 사용하고 있는 부분
	 	HashMap을 사용하여 Map 인터페이스로 구현한다.
	 	Cloneable로 인스턴스를 복제하고 Serializable로 직렬화를 한다.
	 	기본적으로 Cloneable, Serializable를 구현하지 않아도 자바가 내부적으로 적용
	 	즉, 아래처럼 필요한 인터페이스를 상속받아서 구현하면 여러 기능들을 구조적으로 포함할 수 있다.
	 	
		public class HashMap<K,V> extends AbstractMap<K,V>
			implements Map<K,V>, Cloneable, Serializable {
		}
	*/
	
}
