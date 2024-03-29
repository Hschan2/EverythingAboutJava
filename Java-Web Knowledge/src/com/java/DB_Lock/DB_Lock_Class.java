package com.java.DB_Lock;

public class DB_Lock_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		데이터 베이스 락
//			데이터 베이스의 일관성과 무결성을 유지하기 위해서 트랜잭션의 순차적 진행을 보장할 수 있는 직렬화 장치
//			
//			예를 들어서
//			데이터 베이스에 요청이 들어왔다 -> 한 요청은 Amount에서 -40을 한 값을 다른 한 요청은 Amount에서 -30을 한 값을 요청하였다
//			-> 데이터는 - 값을 가지면 안되는데 동시에 온 두 요청으로 인해 - 값을 갖게 되어 사이드 이펙트가 발생함
//				
//			=> 이와 같은 문제를 방지하며 데이터의 일관성을 지키기 위해서 LOCK(잠금)을 걸고 이를 관리하는 것이 Locking
//				동시성, 일관성, 잠금
//				
//			예. 3000원이 있는 계좌에 동시에 다른 두 사람이 3000원 씩 입금을 하였지만 확인 결과 6000원이 들어와 있는 상황
//				한 권의 책을 다른 두 사람이 동시에 대출하여 두 사람이 빌린 것으로 되어 있는 문제
//			
//		데이터 베이스 락 종류
//			Optimistic Lock
//				낙관적인
//					기본적으로 데이터 갱신 시 충돌이 발생하지 않을 것이라고 낙관적으로 보는 것
//				비선점적인
//					데이터 갱신 시 충돌이 발생하지 않을 것으로 예상하기 때문에 우선적으로 LOCK을 하지 않음
//			
//				Version을 사용해서 관리
//				애플리케이션 락이라고도 함
//				충돌 방지
//				
//			Pessimistic Lock
//				비관적인
//					기본적으로 데이터 갱신 시 충돌이 발생할 것이라고 비관적으로 보고 미리 잠금을 거는 것
//				선점적인
//					데이터 갱신 시 충돌이 발생할 것이라고 예상했기 때문에 우선적으로 LOCK (조회할 때부터)
//					
//				가져올 때 데이터 베이스에 LOCK을 건다
//				데이터 베이스 락
//				무결성의 장점
//				DeadLock(데드락)의 위험성
//				
//				다양한 추가 설정
//					Shared Lock : 다른 사용자가 동시에 읽을 수 있지만, Update Delete를 방지
//					Exclusive Lock : 다른 사용자가 읽기, 수정, 삭제 모두 불가능하게 만듬
//				
//			간단하게 정리
//					Optimistic Lock										Pessimistic Lock
//			정의		충돌이 없을 것이라고 예상									충돌을 예상하고 미리 락
//			사용법	JPA를 사용한다면 @Version. 동작 원리가 단훈하니 직접 만들어도 된다	Mode 설정 및 쿼리에 직접 사용. 데이트 베이스 단에서 설정 가능
//			별명		낙관적인 락 / 비선점인 락									비관적인 락 / 선점적인 락
//			장점		DeadLock 가능성이 적으며 성능의 이점							충돌에 대한 오버헤드가 줄어듬. 무결성을 지키기 용이
//			단점		충돌이 바랭하면 오버헤드가 발생								충돌이 없으면 오버헤드 발생
//	
//		Optimistic Lock	vs Pessimistic Lock
//			상황에 따라 사용해야 하는 것이 중요. Lock은 가격이 비싸기 때문
//				- 충돌이 자주 발생하는 상황인가?
//				- 읽기와 수정 비율은 어디에 가까운가?
//				- 일반적으로 웹 애플리케이션은 Optimistic Lock(읽기가 대부분)을 주로 사용
//	
//		추가 내용 알아볼 것
//			도메인에 Lock 사용? 비사용? => 게시판 개발(댓글, 좋아요 등), 게시물을 가져올 때 몇 개를 가져올 것인가?
//			PessimisticLock Scope - Normal / Extended
//			Lock 관련 예외 처리 전략
//			다양한 상황 연출
		
	}

}
