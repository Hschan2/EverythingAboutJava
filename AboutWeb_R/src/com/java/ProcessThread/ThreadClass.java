package com.java.ProcessThread;

public class ThreadClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		멀티 프로세스의 단점을 보완하기 위해 스레드 (프로세스 안에서 실행되는 작업 흐름의 단위)
//		프로세스가 작동한다 = 프로세스 안의 스레드가 작동한다
//		
//		스레드
//		프로세스 구조에서 code, data, heap 영역을 공유. 쓰레드 2개로 작업(stack)을 따로
//		프로세스 안에 최소 1개의 스레드 존재
//		프로세스가 실행된다 = 스레드가 실행된다 -> code, data, heap 영역이 CPU와 메모리에 적재
//		
//		기존에는 프로세스 안에 PCB가 들어가지만 RAM에 적재되어 그 안에 레지스터 값 등이 코어에 있는 레지스터 빈 곳에 채워지고 레지스터 블록이 들어가고 프로세스가 돌아가는 것 = 멀티 프로세스
//		메모리에 적재된 같은 값을 사용하는 code, data, heap을 그대로 두고 레지스터 값에 있는 PCB에 이던 레지스터 블록들만 값이 변경되면 된다 = 멀티 스레드 -> Context Swiching 비용이 저렴
//		
//		멀티 스레드
//		디버깅이 까다롭다
//		한 프로세스 안의 스레드에 문제가 생기면 같은 프로세스 안의 스레드도 같이 문제
//		같은 데이터를 공유하기 때문에 데이터 동기화에 신경!
//		
//		자원은 프로세스 단위로 받고 작업/스케줄링은 스레드 단위로 진행
		
//		쓰레드 생성하는 방법
//		1. Runnable 인터페이스 사용
//		2. Thread 클래스 사용 - Runnable 인터페이스를 구현한 클래스로 어떤 것을 적용하느냐 차이
//		
//		Thread 클래스가 다른 클래스를 확장할 필요가 있을 경우, Runnable 인터페이스를 구현하면 된다.
//		그렇지 않을 경우 Thread 클래스를 사용하는 것이 좋다.
//		
//		runBasic();
		
		
//		Thread 동작 순서
//		runMultiThread();
		
//		Thread sleep 메소드
//		주어진 시간만큼 대기하기
//		항상 try-catch로 묶어서 사용해야 한다.
//		interruptedException으로 예외 처리를 적어도 해야 한다.
//		
//		Thread 상태
//		NEW - 쓰레드 객체는 생성되었지만, 아직 시작하지 않은 상태
//		RUNNABLE - 쓰레드가 실행중인 상태
//		BLOCKED - 쓰레드가 실행 중지 상태, 모니터 락이 풀리기를 기다리는 상태
//		WAITING - 쓰레드가 대기중인 상태
//		TIMED_WAITING - 특정 시간만큼 쓰레드가 대기중인 상태
//		TERMINATED - 쓰레드가 종료된 상태
//		
//		Thread.join()
//		join(), join(0) - 해당 쓰레드가 종료될때 까지 대기
//		join(60000) - 60초 동안 대기
//		매개 변수에 음수를 넣으면 IllegalArgumentException 예외 발생
//		
//		Interrupt()
//		현재 수행중인 쓰레드를 중단
//		단순히 중지 시키는 것이 아닌 InterruptedException의 예외를 발생시키고 중단 (sleep()과 join() 메소드에서 발생한 예외)
//		
//		stop()
//		사용 권장하지 않음
//		
//		Thread 우선 순위
//		static int MAX_PRIORITY - 쓰레드가 가질 수 있는 최대 우선순위를 명시
//		static int MIN_PRIORITY - 쓰레드가 가질 수 있는 최소 우선순위를 명시
//		static int NORM_PRIORITY - 쓰레드가 생성될 때 가지는 기본 우선순위를 명시
//		getPriority(), setPriority() 메소드를 통해 쓰레드의 우선순위를 반환하거나 변경 가능
//		쓰레드의 우선순위가 가질 수 있는 범위는 1 ~ 10, 숫자가 높을수록 우선순위도 높아진다.
//		
//		Thread thread1 = new Thread(new ThreadWithRunnable());
//		Thread thread2 = new Thread(new ThreadWithRunnable());
//		thread2.setPriority(10); // Thread-1의 우선순위를 10으로 변경함.
//		thread1.start(); // Thread-0 실행
//		thread2.start(); // Thread-1 실행
//		System.out.println(thread1.getPriority());
//		System.out.println(thread2.getPriority());
//				
//		위 예제의 결과
//		5 // main() 메소드를 실행하는 쓰레드의 우선순위는 항상 5
//		10 // thread2의 우선순위
//		Thread-1
//		Thread-0
//		Thread-1
//		Thread-0
//		Thread-1
//		Thread-0
//		Thread-1
//		Thread-0
//		Thread-1
//		Thread-0
//		
//		Main 쓰레드
//		JVM 환경에서 돌아간다.
//		하나의 프로세스이며 Java를 실행하기 위해 우리가 실행하는 main() 메소드가 Main 쓰레드
//		
//		멀티 쓰레드 애플리케이션
//		프로세스 안에 메인 쓰레드와 메인 쓰레드 안의 코드가 작업하는 작업 쓰레드가 존재
//		
//		Daemon 쓰레드
//		Main 쓰레드 작업을 돕는 보조적인 역할하는 쓰레드
//		Main 쓰레드가 종료되면 Daemon 쓰레드는 강제적으로 자동 종료
//		setDaemon(ture)를 호출하면 Daemon 쓰레드
//		
//		Daemon 쓰레드를 사용하는 이유
//		예. 모니터링 쓰레드를 별도로 띄어 모니터링을 하다가 Main 쓰레드가 종료되면 관련된 모니터링 쓰레드가 종료되어야 한다.
//		이 때, 모니터링 쓰레드가 Main 쓰레드가 종료될 때 자동으로 종료될 수 있도록 만들기 위해 Daemon 쓰레드를 사용한다
//		
//		동기화(Synchronize)
//		여러 개의 쓰레드가 한 개의 리소스를 사용하려고 할 때, 사용하려는 쓰레드를 제외한 나머지 쓰레드가 접근하지 못하도록 제어
//		쓰레드에 안전하다고 하는 것 (Thread-safe)
//		동기화하는 방법 - 1. Synchronized 키워드, 2. Atomic 클래스, 3. Volatile 키워드
//		
//		Synchronized 키워드
//		Java 예약어
//		변수명 혹은 클래스명으로 사용 불가
//		
//		Synchronized 키워드 사용방법
//		메소드 자체를 Synchronized로 선언하기 (Synchronized Method)
//		메소드 내의 특정 문장만 Synchronized로 감싸기 (Synchronized statements)
//		
//		Synchronized 적용하지 않은 예제
//		NotUseSynchronized 클래스 확인
//		
//		Synchronized 적용한 예제
//		UseSynchronized 클래스 확인
//		
//		Atomic
//		Atomicity(원자성)의 개념, 쪼개질 수 없는 가장 작은 단위
//		Wrapping 클래스의 일종. 참조 타입과 원시 타입의 변수에 모두 적용 가능. CAS 알고리즘을 내부적으로 사용하여 lock없이 동기화 처리 가능
//		volatile, synchronized와 달리 java.util.concurrent.atomic 패키지에 정의된 클래스
//		CAS는 특정 메모리 위치와 주어진 위치의 Value를 비교해 다르면 대체하지 않음
//		
//		Atomic 사용 방법
//		변수를 선언할 때, Type을 Atomic Type으로 선언. (ex. AtomicLong)
//		
//		Atomic 주요 클래스
//		AtomicBoolean
//		AtomicInteger
//		AtomicLong
//		AtomicIntegerArray
//		AtomicDoubleArray
//		
//		Atomic 주요 메서드
//		get() - 현재 값을 반환
//		set(newValue) - newValue로 값을 업데이트한다
//		getAndSet(newValue) - 원자적으로 값을 업데이트하고 원래의 값을 반환한다
//		CompareAndSet(expect, update) - 현재 값이 예상하는 값(expect)과 동일하다면 값을 update 한 후 true를 반환한다. 예상하는 값과 같지 않다면 update는 생략하고 false를 반환
//		Number 타입의 경우 값의 연산을 할 수 있도록 addAndGet(delta), getAndAdd(delta), getAndDecrement(), getAndIncrement(), incrementAndGet() 등의 메소드를 추가로 제공
//		
//		Atomic 사용 예제
//		AtomicTypeSample 클래스 확인
//		
//		Compare-And-Swap (CAS)
//		메모리 위치의 내용을 주어진 값과 비교하고 동일한 경우에만 해당 메모리 위치의 내용을 새로 주어진 값으로 수정
//		현재 주어진 값(현재 쓰레드의 데이터)과 실제 데이터와 저장되니 데이터를 비교해 두 개가 일치할때만 값 업데이트 (compareAndSet() 메소드의 역할)
//		synchronized처럼 임계 영역에서 같은 시점에 두 개 이상의 쓰레드가 접근을 시도하면 쓰레드 자체를 blocking시키는 것은 아니다.
//		
//		Volatile
//		Java 변수를 Main 메모리에 저장한다를 명시
//		매번 변수의 값을 읽을 때마다 CPU cache에 저장된 값이 아닌 Main 메모리에서 읽는다.
//		변수의 값을 작성할 때마다 Main 메모리에도 작성
//		
//		Volatile를 사용하는 이유
//		Volatile 변수를 사용하고 있지 않는 멀티쓰레드 앱은 작업을 수행하는 동안 성능 향상을 위해 Main 메모리에서 읽은 변수를 CPU cache에 저장
//		멀티쓰레드 환경에서 쓰레드가 변수 값을 읽어올 때, 각각 CPU cache에 저장된 값이 다르기 때문에 변수 값 불일치 문제가 발생
//		
//		문제의 예.
//		public class SharedObject { 
//		    public int counter = 0;
//		}
//		Thread-1 (counter 값을 더하고 읽는 연산, read & write)
//		Thread-2 (counter 값을 읽기, Only read)
//			Thread-1만 counter 변수를 증가시키지만 CPU cache에만 반영되어 있고 메모리에 반영은 X
//			Thread-2는 count 값을 계속 읽지만 0을 가져오는 문제가 발생
//		=> 다른 쓰레드에 의해 아직 Main 메모리에 다시 기록되지 않았기 때문에 쓰레드가 변수의 최신 값을 보지 못하는 가시성 문제 발생. 하나의 쓰레드 업데이트는 다른 쓰레드에 표시 X
//		
//		위의 문제를 해결하는 방법
//		volatile 키워드 추가하기, Main 메모리에 저장하고 읽기
//		
//		public class SharedObject { 
//		    public volatile int counter = 0;
//		}
//		
//		volatile 사용하는 시기
//		멀티 쓰레드 환경에서 하나의 쓰레드만 읽고 쓰기만 하고 나머지 쓰레드가 읽는 상황에서 가장 최신 값을 보장
//		
//		volatile 성능의 영향
//		volatile는 변수의 읽기와 쓰기를 Main 메모리에서 진행
//		CPU cache보다 Main 메모리가 비용이 더 크기 때문에 변수 값 일치를 보장해야 하는 경우에 사용하는 것이 좋다.
		
//		Lock 클래스와 Synchronized 키워드의 차이
//		java.util.concurrent - 동기화가 필요한 상황에서 사용할 수 있는 다양한 유틸리티 클래스 제공
//		
//		Locks - 상호 배제를 사용할 수 있는 클래스 제공
//		Atomic - 동기화 되어있는 변수 제공
//		Executors - 쓰레드 풀 생성, 생명주기 관리, Task 등록과 실행 등 간편 처리 가능
//		Queue - Thread-safe한 FIFO Queue 제공
//		Synchronizers - 특수한 목적의 동기화를 처리하는 5개의 클래스를 제공 (Semaphore, CountDownLatch, CyclicBarrier,Phaser, Exchanger)
//		* Semaphore - 동시에 접근이 가능한 쓰레드의 개수를 지정하여 설정이 가능
//		
//		java.util.concurrent.locks - synchronized 블록을 사용했을 떄와 동일한 메커니즘으로 동작, 내부적으로 synchronized를 사용하여 구현되어 있고, synchronized를 더욱 유연하고 세밀하게 처리하기 위해 사용
//		
//		둘의 차이는 공정성(fairness) - 모든 쓰레드가 자신의 작업을 수행할 기회를 공평하게 갖는 것
//		공정한 방법에서는 Queue에서 쓰레드들이 무조건 순서를 지키며 lock 확보.
//		불공정한 방법에서는 특정 쓰레드에 lock이 필요한 순간 release가 발생하면 대기열을 건너뛰는 새치기같은 일이 발생
//		다른 쓰레드에 우선순위가 밀려서 자원을 계속해서 할당받지 못하는 쓰레드가 존재하는 상황을 기아 상태(starvation). 기아 상태를 해결하기 위해 공정성 필요
//		
//		synchronized는 공정성을 지원 X. 즉 후순위인 쓰레드는 실행이 안될 가능성 있음.
//		ReentrantLock은 생성자의 인자를 통해서 Fair/NonFair 설정 가능. 아래처럼 정의 가능
//		public ReentrantLock() {
//		    sync = new NonfairSync();
//		}
//
//		public ReentrantLock(boolean fair) {
//		    sync = fair ? new FairSync() : new NonfairSync();
//		}
//		
//		공정한 lock을 사용할 경우, 가장 오래 기다린 쓰레드에 lock 제공.
//		lock을 요청하는 시간의 간격이 긴 경우가 아닐 때, 쓰레드를 공정하게 관리하는 것보다 불공정하게 관리할 때 성능이 더 우수 => 일반적으로 불공정 방식이 사용되는 이유
//		
//		정리.
//		synchronized는 block 구조 사용. 메소드 안에 임계 영역의 시작과 끝이 존재 => lock은 lock()으로 시작, unlock()으로 끝을 명시하기 때문에 임계 영역을 여러 메소드에서 나눠서 작성 가능 (lock(), unlock() => try-catch-finally 로 작성)
//		synchronized는 lock과 같이 따로 unlock을 하지 않고 구문만 작성하여 코드를 간결하게 작성 가능
		
//		더 자세한 내용은 "https://sujl95.tistory.com/63" 에서 확인 가능
		
	}
	
//	public static void runBasic() {
//		RunnableSample runnable = new RunnableSample();
//        new Thread(runnable).start();
//        ThreadSample thread = new ThreadSample();
//        thread.start();
//        System.out.println("RunThreads.runBasic() method is ended.");
	
	
//		start() 메소드가 끝날 때까지 기다리지 않고, 다음 줄 thread라는 객체의 start() 메소드를 실행
//		새로운 쓰레드를 시작하므로 run() 메소드가 종료될 때까지 기다리지 않고 다음 줄로 넘어간다.
//	}
	
	
//	 public static void runMultiThread() {
//	        RunnableSample[] runnable = new RunnableSample[5];
//	        ThreadSample[] thread = new ThreadSample[5];
//	        for (int loop = 0; loop < 5; loop++) {
//	            runnable[loop] = new RunnableSample();
//	            thread[loop] = new ThreadSample();
//
//	            new Thread(runnable[loop]).start();
//	            thread[loop].start();
//	        }
//
//	        System.out.println("RunMultiThreads.runMultiThread() method is ended");
//	        
//	         순서대로 실행되지 않고 컴퓨터 성능에 따라 실행 순서가 다르다.
//	         run() 메소드가 끝나지 않으면 애플리케이션은 종료되지 않는다.
//	    }
}
