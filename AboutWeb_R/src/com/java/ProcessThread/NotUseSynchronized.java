package com.java.ProcessThread;

public class NotUseSynchronized {
	private int amount;
	
    public NotUseSynchronized() {
        amount=0;
    }

    public void plus(int value) {
        amount += value;
    }

    public void minus(int value) {
        amount -= value;
    }

    public int getAmount() {
        return amount;
    }
}

public class ModifyAmountThread extends Thread{
    private NotUseSynchronized calc;
    private boolean addFlag;

    public ModifyAmountThread(NotUseSynchronized calc, boolean addFlag) {
        this.calc = calc;
        this.addFlag = addFlag;
    }

    public void run() {
        for(int loop = 0; loop<10000;loop++){
            if (addFlag) {
                calc.plus(1);
            } else {
                calc.minus(1);
            }
        }
    }
}

public class RunSync {
    public static void main(String[] args) {
        RunSync runSync = new RunSync();
        runSync.runCommonCalculate();
    }

    public void runCommonCalculate() {
    	// thread 생성
    	NotUseSynchronized calc = new NotUseSynchronized();
        ModifyAmountThread thread1 = new ModifyAmountThread(calc, true);
        ModifyAmountThread thread2 = new ModifyAmountThread(calc, true);

        // 쓰레드 실행
        thread1.start();
        thread2.start();
        try {
        	// 스레드 종료될 때까지 대기
            thread1.join();
            thread2.join();
            System.out.println("Final value is " + calc.getAmount()); // 19511와 같은 결과가 나오고 20000이라는 원하는 값이 나오지 않음
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
