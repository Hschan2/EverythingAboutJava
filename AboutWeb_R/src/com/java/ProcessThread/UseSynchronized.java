package com.java.ProcessThread;

public class UseSynchronized {
	
	private int amount;
	
    public UseSynchronized() {
        amount=0;
    }

    // 동기화 적용
    public synchronized void plus(int value) {
        amount += value;
    }

 // 동기화 적용
    public synchronized void minus(int value) {
        amount -= value;
    }

    // -------------------------------------------------
    // BLOCK 걸기 (Lock)
    // Synchronized에 this에 사용하는 것은 메소드에 Synchronized에 붙이는 것과 같다.
    // 그러나 this가 아닌 다른 object 별로 lock을 걸게되면 lock을 거는것과 다르다.
    public int getAmount() {
        return amount;
    }
    
    private int amount;
    private int interest;
    public static Object interestLock = new Object();
    public UseSynchronized() {
        amount=0;
    }
    
    // lock 걸기 동기화
    public void addInterest(int value) {
        synchronized (interestLock) {
            interest+=value;
        }
    }

 // lock 걸기 동기화
    public void plus(int value) {
        synchronized (this){
            amount += value;
        }
    }

    public void minus(int value) {
        synchronized (this){
            amount -= value;
        }
    }

    public int getAmount() {
        return amount;
    }
}
