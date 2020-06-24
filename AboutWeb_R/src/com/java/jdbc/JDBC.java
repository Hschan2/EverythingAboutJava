package com.java.jdbc;

public class JDBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// JDBC, SQLMAPPER, ORM
		
		/*
			JDBC, SQLMAPPER, ORM의 공통점은 무엇일까?
				- Persistence (영속성). 데이터를 생성한 프로그램의 실행이 종료되어도 사라지지 않는 데이터의 특성 => 영구적으로 저장
				
				* 기술을 제대로 이해하려면 역사와 배경을 아는 것이 효율적 *
				
				1. 1997년 JDBC의 등장 -> 인터넷 보급과 DB 산업 성장 -> 온라인 비즈니스의 투자 증가, DB Connector에 대한 니즈 -> JAVA 진영 데이터 베이스 연결 표준 인터페이스 등장(JDBC)
					(사용하는 API를 변경하지 않고 드라이버 매니저만 교체하면 어떠한 제품에도 접근할 수 있게 만드는 것 -> JDBC API)
					
				2. SQLMAPPER 
					-하지만 코드 중복성, 쿼리 작성, 커넥션 관리를 계속해야 하는 불편함이 발생 -> Spring JDBC에서 보완할 기술을 제공
					-Spring JDBC를 사용하면  연결을 알아서 오픈하고 SQL, Parameter, Statement 등을 해준다. 즉, 불편한 것을 추상화하였고 SQLMAPPER에 속하게 되었다.
					-Mybatis는 SQL을 분리하자는 개념 -> 쿼리를 Java에서 xml로 / 복잡한 JDBC 코드 X, ResultSet과 같이 결과값을 매핑하는 객체 X (간단한 설정과 관심사를 분리) => SQL를 JAVA와 분리해서 더 간편하게 쓰자
				
				3. ORM (Object Relational Mapping)
					- JDBC가 불편하다. 코드가 너무 많다 -> Spring JDBC(JDBC Template, RowMapper 클래스 등) 등장 -> 쿼리를 관리하기가 불편하다 -> Ibatis, MyBatis 등장 -> 복잡한 것은 계속 있다 객체지향적으로 풀어가자
					- 물리적으로 SQL과 JDBC API를 데이터 접근 계층을 숨기는 데에 성공했어도 논리적으로는 엔티티와 강한 의존 관계를 가지고 있다. 객체지향에 사용하는 로직, 기술, 추상화 등 관계형 DB에는 패러다임의 서로 연관 관계와 상속 등을 표현하기 어려움. 즉, 패러다임 불일치 -> SQL, DB 의존 개발 -> 객체지향을 못하겠다 -> 이를 깨버리자는 것이 ORM
					- JPA의 등장(예. Hibernate)
					- ORM 기준의 표준 인터페이스 JPA를 Hibernate가 구현해서 사용
					- 핵심 모델은 엔티티 매니저와 영속성 컨텍스트
					- 쿼리를 관리하지 않겠다 -> 엔티티 매니저에게 역할을 맡기겠다
					- 엔티티 매니저가 여러 엔티티를 영속성 컨텍스트에 들여보내고 추적하지 않으려면 떼내고 하는 것이 JPA
					- 핵심 컨셉은 Lazy Loading, Dirty Checking, Caching
					- Lazy Loading -> 프록시 객체를 사용해 필요한 것만 가져온다.
					- Dirty Checking -> 엔티티 매니저가 변화된 곳을 검사하는 것
					- Caching -> 성능상으로 DB에 커넥션을 조금 덜 열기 위해 DB 연결을 열지 않고 기술적으로 요청한 캐시를 준다
					- Spring Dta의 JPA (코어 컨셉의 Repository) -> 위 기능의 복잡함을 한 단계 더 추상화 하겠다.
					- Spring Data JDBC -> 컨셉은 심플. 객체지향으로 설계된 것을 가져다 놓아 많은 기술이 필요 -> 매니저 엔티티 쓰지 말자 -> DDD 구조를 디자인에 따라 한 번에 집어넣고 끊을 수 있겠다 -> ORM이라고 단정하기엔 애매하기 때문에 Opinionated ORM으로 정의
						(Hibernate 등을 사용하지 않고 JDBC API를 그대로 스스로 구현한다. 다만 Data가 붙어 있으니 Repasitory 개념은 가지고 있다)
				
					
				* SQLMAPPER => SQL을 매핑
				* ORM => 객체지향 기술을 다 넣으려는 시도를 하고 쿼리를 되도록 안 쓰도록 해주는 역할
				* JDBC => 위의 것들의 기반 API
		*/
	}

}
