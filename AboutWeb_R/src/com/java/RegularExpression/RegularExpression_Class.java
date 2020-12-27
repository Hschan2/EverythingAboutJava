package com.java.RegularExpression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		정규표현식
//		
//		특정 패턴을 가지고 문자열을 찾을 수 있다
//		특정한 규칙을 가진 문자열의 집합을 표현하는 데 사용하는 형식 언어
//		
//		능력
//			메타문자 : 문자를 나타내는 문자
//				.: 모든 문자
//				[]: 대괄호 안에 들어가있는 문자를 찾고 대괄호 안에서 ^는 Not을 의미
//				|: or
//				/s: 공백
//				/d: [0-9]
//				/w: 영문자, 숫자, 밑줄[0-9-zA-Z_]
//			수량자 : 앞 문자의 개수
//				?: 앞 문자가 없거나 하나 있음 ({0, 1})
//				+: 앞 문자가 하나 이상 ({1, })
//				*: 앞 문자가 0개 이상 ({0, })
//				{n, m}: 앞 문자가 n개 이상, m개 이하
//				{n. }: 앞 문자가 n개 이상, 위의 형태에서 m이 생략된 형태
//				{n}: 앞 문자가 n개
//				?: 앞 문잦가 없거나 하나 있음 ({0, 1}) | 처음에 발견했을 때 쉬고 다시 찾음
//			그 외
//				^: 찾으려는 문자열의 처음을 뜻함
//				$: 찾으려는 문자열의 끝을 뜻함
//				...
//		
//		정규포현식 = 패턴구분자 시작(/) + 작성할 패턴(패턴) + 패턴구분자 끝(/) + 패턴 변경자(g) 등 다양한 방법 존재
//		
//		전화번호 정규표현식 분석
//		핵심 : 메타문자와 수량자를 잘 파악하자!
//		\d{2,3}-?\d{3,4}-?\d{4}
//		\d => 메타 문자
//		그 외 => 수량자
//		
//		항상 정규표현식을 사용하는 것이 좋은가? 아니다
//		
//		강점
//		패턴으로 검증이 가능하고 IF문을 많이 쓰지 않는 것
//		
//		약점
//		너무 안 좋은 가독성과 유지 보수하기 힘듦
//		
//		그래서
//		간단한 검증일 때는 IF문으로 해결하고 메서드 명을 통해 가독성이 높이는 것.
//		복잡한 검증이 있을 때 정규표현식 사용하고 주석을 달아서 가독성을 높이는 것.
//		
//		정규표현식 활용
//		1. 컴파일러 파서
//		2. CLI 환경을 주로 사용하는 경우, grep, sed, awk를 통해 사용
//		3. 이메일, 주소, 전화번호 규칙 검증
//		4. 입력에서 불필요한 입력 검증
//		5. 개발 도구에서 문자열 치환
//		6. 로깅에서 찾아볼 때
//		7. 코딩 테스트
//		
//		개발 도구에서 문자열 치환
//		Intellij에서 정규표현식 문자열 치환 지원 (Ctrl + r)
//		
//		Java에서 정규표현식 활용
//		1. 문자열에서 알파벳 대문자, 숫자, 더하기(+), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거하라
//		2. 문자열에서 마침표(.)가 3번 이상 연속된 부분을 하나의 마침표로 치환하라
//		
//		String 클래서에서 replaceAll() 메서드
//		Pattern 클래스에서 compile() 메서드
//		
//		String의 replaceAll을 사용할 때, Pattern을 정적으로 만들자
//		예. 
//		private static final Pattern REMOVE_CHAR_PATTERN = Pattern.compile("[^A-Z0-9+_.]"); => 대문자, 숫자, +, _, .을 제외한 모든 것을 공백으로
//		private static final Pattern REMOVE_DOT_PATTERN = Pattern.compile("[.]{3,}"); => 연속으로 .이 3개 이상 나올 시 1개로
//		
//		정적으로 만든 Pattern을 메서드에서 활용
//		return REMOVE_CHAR_PATTERN.matcher(input).replaceAll("");
//		return REMOVE_DOT_PATTERN.matcher(input).replaceAll(".");
//		
//		Pattern 객체는 불변 객체
//		Pattern 클래스는 정저거 팩토리 메서드를 사용하고 있고 Thread-safe 하고 있다.
//		
//		Matcher 클래스 사용 시 주의할 것
//		Matcher 객체는 reset() 하고 다시 input을 넣어 재사용할 수 있지만 Thread-safe를 하지 못함
//		Thread-safe하게 사용하려면 Pattern 객체의 matcher()를 통해 Matcher를 생성한 뒤 사용할 것
//		예.
//		public Matcher matcher(CharSequence input) {
//			if(!compiled) {
//				synchronized(this) {
//					if(!compiled)
//						compile();
//				}
//			}
//			Matcher m = new Matcher(this, input);
//			return m;
//		}
//		
//		정규표현식 어떻게 학습?
//		감을 잃지 않도록 하는 것이 중요하고 IDE에서 문자열을 replace를 할 일이 생긴다면 정규표현식을 사용하여 연습할 것 그리고 분석할 것
//		
//		요약
//		클린코드를 위해 정규표현식 사용 시 가독성을 고민하고 사용
//		정규표현식 분석 핵심은 메타문자와 수량자 파악
//		Java에서 정규표현식 사용 시  Pattern은 변하지 않으므로 정적으로 만들고 사용
//		Java의 Matcher 객체는 Thread-safe 하지 못하기 때문에 멀티스레드 환경이라면 Pattern의 matcher()를 사용할 것
//		정규표현식은 그 때마다 알아볼 것
		
	}

}
