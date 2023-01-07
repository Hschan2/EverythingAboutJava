package com.java.ProcessThread;

public class StateThread extends Thread {
	
	Object monitor = new Object();
	StateThread thread = new StateThread(monitor); // Object 클래스 객체 생성
	try {
	    System.out.println("thread state = " + thread.getState());
	    thread.start(); // 쓰레드 객체 생성 후 시작
	    System.out.println("thread state(after start)=" + thread.getState());

	    Thread.sleep(100);
	    System.out.println("thread state(after 0.1 sec)=" + thread.getState());

	    synchronized (monitor) {
	        monitor.notify(); // wait() 메소드가 호출되면 상태는 WAITING 상태. 쓰레드를 깨워야만 상태 해제. notify() 메소드를 호출해서 풀기
	        // 만약 thread가 2개 이상일 때, monitor.notify();를 반복하는 것보다 monitor.notifyAll();를 사용하기
	    }

	    Thread.sleep(100);
	    System.out.println("thread state(after notify)=" + thread.getState());

	    thread.join(); // 쓰레드가 종료될 때까지 대기 후 상태 출력
	    System.out.println("thread state(after join)=" + thread.getState());

	} catch (InterruptedException ie) {
	    ie.printStackTrace();
	}

}
