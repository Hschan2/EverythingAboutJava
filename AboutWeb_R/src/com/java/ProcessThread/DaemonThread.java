package com.java.ProcessThread;

public class DaemonThread extends Thread {
	
	public void run() {
        try {
            Thread.sleep(Long.MAX_VALUE); // Long의 최대값 만큼 대기
        } catch (Exception e){
            e.printStackTrace();
        }
    }
	
}

public void runCommonThread() {
	
    DaemonThread thread = new DaemonThread();
    thread.start();
    
}

// Daemon 쓰레드 실행
public void runDaemonThread() {
	
    DaemonThread thread = new DaemonThread();
    thread.setDaemon(true);
    thread.start();
    
}