package com.java.ProcessThread;

public void checkJoin {
	SleepThread thread - new SleepThread(2000);
	
	try {
		thread.start();
	        thread.join(5000);
	        thread.interrupt();
	        System.out.println("thread state(after join)="+thread.getState());

	} catch (InterruptedException ie) {
	        ie.printStackTrace();
	}
}
