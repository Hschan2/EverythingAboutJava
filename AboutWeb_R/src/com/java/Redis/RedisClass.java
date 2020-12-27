package com.java.Redis;

public class RedisClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Redis (Remote Dictionary Server)
//			외부에 있는 딕셔너리를 사용하는 서버
//			데이터 베이스 캐시, 메시지 브로커 (Database Cache, Message Broker)
//			인-메모리 데이터 구조 저장 (In-memory Data Structure Store)
//			다양한 자료 구조 제공	(Supports Rich Data Structure)
//			
//			Cache : 나중에 요청에 데한 결과를 미리 저장했다가 빠르게 사용
//			Database : HDD, SSD에 저장했지만 Main Memory (DRAM)에 저장하는 것이 나옴 => Redis
//			
//			In-memory Database (Cache), Database보다 더 빠른 Memory에 더 자주 접근하고 덜 자주 바뀌는 데이터를 저장
//			
//			Data Structure
//				Collection 자료 구조 제공
//				String : Key-Value 한 쌍을 제공
//				List : Key-Set 한 쌍을 제공
//				Sorted Set : Key-Set 한 쌍을 제공
//				Hash : Key-Hash 한 쌍을 제공
//				
//		Java - Redis
//			Hashmap에 데이터를 저장할 수는 있지만 서버가 여러 대일 경우 서버마다 다른 데이터를 가지고 있기 때문에 일관성 문제 발생, Multi-Threaded 환경에서 Race Condition 문제 발생
//		
//			Race Condition : 여러 개의 Thread가 경합하는 것, Context Switching에 따라 원하지 않는 결과 발생
//			
//			Race Condition 해결
//				Redis는 기본적으로 Single Threaded
//				Redis 자료 구조는 Atomic Critical Section에 대한 동기화를 제공 (Atomic Critical Section : 동시에 프로세스가 여러 개가 접근하면 안되는 것)
//				서로 다른 Transaction Read / Write를 동기화
//		
//		Redis 어디에서 사용하나?
//			여러 서버에서 같은 데이터를 공유할 때
//			Single Server일 때, Atomic 자료 구조 및 Cache
//			
//		Redis 사용 시 주의해야 할 점
//			Single Thread 서버이기 때문에 시간 복잡도를 고려해야 할 것
//			In-memory 특성 상 메모리 파편화, 가상 메모리 등의 이해가 필요
//		
//			Single Threaded
//				Event Driven (비동기)
//				IO-bound Process
//				Context Switching의 효율이 적음
//				Single Threaded
//				=> Simple
//				
//				빨리 처리해야 할 것 => O(N) 명렁어는 주의해서 처리할 것 (Keys, Flush, GetAll 연산 주의할 것)
//				
//		Redis
//			네트워크로부터 요청을 받아서 명령어 처리
//		
//		Redis의 Momory 관리
//			메모리 파편화 => 사용할 때 메모리를 여유롭게 사용해야 할 것
//			가상메모리 Swap => 일부만 메모리에서 사용하고 덜 사용하는 것은 디스크에 넣고 있다가 필요할 때 메모리에서 사용
//			Replication - Fork => 항상 데이터가 유실될 것을 인지하고 있어야 한다. 데이터를 복사해서 디스크 등에 전송해서 저장하는 방법 (복사가 일어날 때 프로세스를 동일하게 복제하여 메모리 상에서 허용하는 것 = Fork, 메모리가 가득 차있다면 문제 발생)
//			
//		그 외 찾아볼 것
//			Redis 저장소처럼 사용 : Redis Persistent, RDB, AOP
//			Redis의 메모리는 제한되어있기 때문에 주기적으로 Scale out Back up을 해야 함 => Redis Cluster
//			부하 분산 : Constant Hashing
//			Data Grid : Spring Gemfire, Hazlecast
		
	}

}
