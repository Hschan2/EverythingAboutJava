package com.java.JPA;

public class JPAMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// JPA 전에 ROM
		// ORM => 객체는 객체대로 설계, 관계형 데이터베이스는 관계형 데이터베이스대로 설계 | ORM 프레임워크가 중간에서 매핑
		
		// JPA
//			* EJB - 과거 자바 표준(Entity Bean), 과거의 ORM | 코드가 매우 지저분, API의 복잡성이 높아짐(Interface를 많이 구현해야 함), 속도가 느림
//			* Hibernate - ORM 프레임워크, 오픈소스 SW | Entity Beans 이용 대체 목적
//			1) JPA (Java Persistence API) - 현재 자바 진영의 ORM 기술 표준으로 인터페이스 모음
//				=> 즉 실제로 동작하는 것이 아님, JPA 인터페이스를 구현한 대표적인 오픈소스가 Hibernate라고 할 수 있음
//				=> JPA 2.1 표준 명세를 구현한 3가지 구현체 - Hibernate, EclipseLink, DtaNucleus
//				
//			JPA 동작 과정
//				JPA는 애플리케이션과 JDBC 사이에서 동작
//					JPA를 이용하면 JPA 내부에서 JDBC API를 이용해 SQL을 호출하여 DB와 통신 => 즉, 개발자가 직접 JDBC API를 쓰는 것이 아님
//			
//			JPA 저장 과정
//				ex) MerberDAO에 객체를 저장하고 싶을 때
//					개발자는 JPA에 Member 객체를 넘김
//					JPA - Member 엔티티 분석
//						- INSERT SQL을 생성
//						- JDBC API를 사용해 SQL을 DB에 날림
//			
//			JPA 조회 과정
//				ex) Merber 객체를 조회하고 싶을 때
//					개발자는 member의 pk(기본키) 값을 JPA에 넘긴다
//					JPA - 엔티티의 매핑 정보를 바탕으로 적절한 SELECT SQL을 생성
//						- JDBC API를 사용하여 SQL을 DB에 날림
//						- DB로부터 결과 받음
//						- 결과(ResultSet)를 객체에 모두 매핑
//					* 쿼리를 JPA가 만들어 주기 때문에 Object와 RDB간의 패러다임 불일치를 해결!
//					
//			왜 JPA를 사용해야 하는가??
//				1) SQL 중심적인 개발에서 객체 중심으로 개발
//				2) 생산성
//					JPA를 사용하는 것은  Java Collection에 데이터를 넣다 뺐다하는 것처럼 사용할 수 있게 만듬
//					간단한 CRUD
//						ex)
//						저장 : jpa.persist(member)
//						조회 : Member member = jpa.find(memberId)
//						수정 : member.setName("변경할 이름")
//						삭제 : jpa.remove(member)
//					수정이 굉장히 간단
//						객체를 변경하면 알아서 DB에 UPDATE Query가 나감
//						
//			유지보수
//				기존 : 필드 변경 시 모든 SQL을 수정해야!!
//				JPA : 필드만 추가하면 된다. JPA가 처리하기 때문에 직접 손댈 일 없다.
//				
//			Object와 RDB 간의 패러다임 불일치 해결
//				1) JPA와 상속
//					저장
//						개발자가 할 일 - jpa.persist(...);
//						나머지 JPA가 처리 - INSERT INTO ...
//					조회
//						개발자가 할 일 - 선언 = jpa.find(class, key);
//						나머지 JPA가 처리 - SELECT ... FROM ... JOIN A.ID = B.ID
//								
//				2) JPA와 연관관계
//					객체의 참조로 연관관계 저장 가능
//				
//				3) JPA와 객체 그래프 탐색
//					- 신뢰할 수 있는 엔티티, 계층 (MemberService.class)
//					- 다른 개발자가 직접 구현한 DAO에서 가져오는 경우
//						DAO에서 어떤 쿼리를 날렸는지 확인하지 않는 이상, 그래프 형태의 관련된 객체를 모두 잘 가지고 왔는지 알 수 없음
//						즉, 반환한 엔티티를 신뢰하고 사용할 수 없다
//					- JPA를 통해 가져오는 경우
//						객체 그래프를 완전히 자유롭게 탐색 가능
//						** 지연 로딩 전략(Lazy Loading) 사용
//							관련된 객체를 사용하는 시점에 SELECT Query를 날려서 객체를 가져오는 전략
//			
//			JPA와 비교
//				동일한 트랜잭션에서 조회한 엔티티는 같음을 보장
//					String memberId = "100"; 
//					Member member1 = jpa.find(Member.class, memberId); // DB에서 가져옴 
//					Member member2 = jpa.find(Member.class, memberId); // 1차 캐시에서 가져옴
//					member1 == member2; // 같다
//					
//			JPA의 성능 최적과 기능
//				모아서 쓰는 버퍼링 기능, 읽을 때 쓰는 캐싱 기능으로 성능 개선 가능 (중간 계층이 있는 경우)
//				JPA -> JDBC API와 DB 사이에 존재하여 위 두 기능 모두 존재
	}

}
