package com.java.EventSourcing;

public class EventSourcingClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Event Sourcing
//		데이터 저장 방식 중 하나
//		
//		기존 방식 - 최신 상태를 저장하는 방식
//		```
//		public class Car {
//			private String carName;
//			private int position;
//		}
//		```
//		이는 데이터 추적이 어렵고 필요 시 로그 시스템이나 이력관리 시스템을 만들어야 했다.
//		
//		이벤트 소싱 - 이벤트 자체를 기록하는 방식
//		```
//		identifier: car1
//		Type: CarCreated
//		payLoad: {carName: -, position: -}
//		```
//		쉽게 말해서 데이터 베이스에 이벤트를 저장한다는 것
//		
//		그렇다면 객체 상태를 어떻게 알 수 있는가? - 이벤트 리플레이 (Event Replay)
//		```
//		public void apply(CarCreated carCreated) {
//			this.carName = carCreated.getCarName();
//			this.position = carCreated.getPosition();
//		}
//		```
//		이벤트 스토어에서 이벤트를 가져와서 순차대로 재생한다.
//		
//		Snap Shop
//		대량의 데이터의 이벤트가 있을 때 모든 이벤트를 불러오면 비용이 많이 드는 단점을 보완하기 위해
//		특정 위치 이후 발생하는 이벤트에 대해서만 리플레이 한다.
//		일정 시간이 지나거나 일정 개수 이상의 이벤트가 쌓이면 직렬화된 상태 값 등을 스냅샷에 저장해두고 이 후 리플레이할 때 스냅샷에서 정보를 가져온 다음 그 이후 이벤트들만 리플레이하는 방식
//	
//		이벤트 소싱 장점
//		버그 추적이 용이하다.
//		Microservice Architecture와 좋은 시너지를 보인다.
//		객체 지향과 좋은 궁합을 보인다.
//		임피던스 불일치가 발생하지 않는다.
//		
//		임피던스 불일치
//		
//		기본 방식
//		```
//		public class Car {
//			private String carName;
//			private int position;
//			private int fuelEfficiency; // 연료 추가
//		}
//		```
//		fuelEfficiency가 추가 되었지만 데이터 베이스에는 아직 추가하기 전. 실행하면 오류가 발생한다. 데이터 베이스와 매핑하기도 까다롭다.
//		
//		이벤트 소싱 방식
//		위와 똑같이 선언한 후 이벤트 리플레이를 해보면 실행이 된다.
//		```
//		public void apply(CarCreated carCreated) {
//			this.carName = carCreated.getCarName();
//			this.position = carCreated.getPosition();
//			this.fuelEfficiency = 1;
//		}
//		```
//		위와 같이 이벤트 리플레이를 하면 fuelEfficiency 라인이 추가되어 있다.
//		
//		또 이후 발생하는 생성 요청에도 임피던스 불일치가 발생하지 않는다.
//		```
//		public void apply(CarCreated2 carCreated2) {
//			this.carName = carCreated2.getCarName();
//			this.position = carCreated2.getPosition();
//			this.fuelEfficiency = carCreated2.getFuelEfficiency();
//		}
//		```
//		위 처럼 선언하여 불일치를 피할 수 있다.
//		
//		이벤트 소싱 단점
//		사용하기 어렵다. = 구현하기 복잡하다.
//		단순 모델에 적합하지 않다.
//		복합 조회 문제가 발생할 수 있다.
//		
//		CQRS (Command and Query Responsibility Segregation)
//		명령과 조회의 책임 분리
//		상태를 변경하는 커맨드 모듈과 데이터를 조회하는 쿼리 모델을 분리하는 것
//		
//		Command Side(커맨드 사이드)
//		상태 변경 요청이 들어오면 이벤트를 발생하는 곳
//		
//		Query Side(쿼리 사이드)
//		데이터 조회를 처리
//		
//		커맨드 사이드에서 이벤트가 발생이 되면 과정을 거쳐 이벤트 스토어에 이벤트가 저장
//		그리고 쿼리 모델을 위한 데이터 처리 과정을 거치는 처리 과정을 Projection이라고 한다.
//		
//		CQRS 예시
//		이벤트가 발생하여 CarCreated라는 이벤트가 저장된다. -> insert 쿼리문을 이용해서 쿼리 모듈(Query Side)에 데이터를 저장한다
//		-> 그리고 carMoved가 저장되었다면 update 쿼리 문을 이용해서 저장 -> 조회 요청이 왔을 때 Qeury Side가 처리하여 이벤트 로드 필요 없이 조회가 가능
//	
//		Event Sourcing 정리
//		시스템 기록 확보에 용이하고 DB 제약에서 자유롭다.
//		복잡하고 단순 모델에는 적합하지 않다. (코드가 많고 복잡하다.)
	}

}
