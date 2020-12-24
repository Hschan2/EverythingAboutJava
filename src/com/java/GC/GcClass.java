package com.java.GC;

import java.awt.List;

public class GcClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		GC(Garbage Collection)란?
//		JVM의 Heap 영역(메모리)에서 사용하지 않는 객체를 삭제하는 프로세스
//			* Heap 영역에는 Object 타입이 들어간다 (String, List...)
//			
//		1. GC의 수거 대상 - Reachability
//		어떠한 객체에 유효한 참조가 존대한다면 Reachable, 그렇지 않다면 Unreachable
//		(특정 시점에 참조되고 있는 객체들을 탐색하다가, 즉 GC Roots에 연결되어 있다면)
//		
//		GC 루트가 될 수 있는 조건 - Stack 영역의 데이터, Method 영역의 Static 데이터, JNI(자바 네이티브 메서드 인터페이스)에 의해 생선된 데이터
//		Unreachable 객체가 바로 GC의 수거 대상
//		
//		2. GC의 동작 순서
//		Mark and Sweep 알고리즘으로 동작
//		
//		Mark - GC Roots로부터 모든 변수를 스캔하면서 각각 어떤 객체를 참조하고 있는지 찾아서 마킹 (Reachable와 Unreachable를 구별)
//		Sweep - Unreachable한 객체를 수거하는 단계
//		
//		* Compact - Sweep 단계를 거치고 나서 분산된 남은 객체들을 Heap 시작 주소로 모아 메모리가 할당된 부분과 그렇지 않은 부분을 나누는 단계 (메모리 단편화를 막아주는 작업)
//		
//		3. GC가 일어나는 때
//		Heap의 구조
//		Young Generation 		| Old Generation
//		새로운 객체들이 할당되는 영역	| Young Generation에서 수거되지 않은 객체들이 존재하는 영역
//		
//		Young Generation 영역
//		Eden
//		Survivor 0 | Survivor 1
//		
//		* Meta Space - Heap 영역에 있는 것은 아니지만 CG에 필요한 클래스 메소드의 요약 정보가 있는 영역
//		
//		먼저 Eden 영역에 새로운 객체가 할당 - 영역이 꽉 찰 경우 Minor GC 발생 - Mark and Sweep 단계 - 수거되지 않은 객체 Survivor로 이동(0에 있으면 1은 비워있어야 하며 반대도 같다)
//		- Survivor에 있는 수거되지 않은 객체의 age값이 1 증가 - 과정 반복 - 만약 age 값이 특정 임계점(Age Threshold)에 도달하면 Old Generation 이동 (Promoted)
//		- Old Generation이 꽉 차면 Major GC 발생
//		
//		왜 이렇게 나누나?
//		1. 대부분 객체는 금방 접근 불가능한 상태가 된다 (금방 grrbage 상태가 된다)
//		2. 오래된 객체에서 젊은 객체로의 참조는 아주 적게 존재
//		
//		Young Generation에서 객체가 수거되는 양이 많다 -> 자주 작동해도 좋다. 메모리 낭비를 막을 수 있다. But Old Generation는 반대
//		
//		4. Stop the World
//		GC를 실행하기 위해 JVM이 아닌 애플리케이션 실행을 멈추는 것 - GC를 실행하는 쓰레드 외 모든 쓰레드 작업 중단
//		GC에 따라 실행 시간이 다르다.
//		
//		5. GC 종류
//		Serial GC - 가장 기본적인 GC, 처리하는 쓰레드가 1개 (싱글 쓰레드), Stop The World 시간이 김, Mark-Compact(Sweep 포함) 알고리즘 사용
//		Parallel GC - 자바 8의 기본 GC, Young 영역의 GC를 멀티 쓰레드로 수행, Serial GC 비해 Stop The World 시간이 짧음
//		Parallel Old GC - Parallel GC를 개선, Old 영역에서도 멀티 쓰레드로 수행, Mark-Summary-Compact 알고리즘 사용
//		CMS GC - Stop The World 시간 감소를 위해 고안, Compact 과정 없음, Reachable 객체를 순차적으로 찾는 것이 특징, 메모리 단편화가 아쉬움
//		G1 GC - CMS GC를 개선, 자바 9+의 기본 GC, Heap을 일정한 크기의 Region으로 나눔, 전체 Heap이 아닌 Region 단위로 탐색, Compact 진행, Garbage만 있는 Region은 처음에 수거하기 때문에 Garbage First라는 이름이 붙음

	}

}
