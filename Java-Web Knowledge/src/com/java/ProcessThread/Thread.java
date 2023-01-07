package com.java.ProcessThread;

// 쓰레드를 사용하기 위해 Thread 클래스 생성 후 Runnable 인터페이스 사용
public class Thread implements Runnable {
	private static native void registerNatives();

	static {
		registerNatives();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void sleep(int i) {
		// TODO Auto-generated method stub
		
	}

}
