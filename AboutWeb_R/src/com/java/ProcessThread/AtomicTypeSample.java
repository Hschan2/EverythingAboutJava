package com.java.ProcessThread;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicTypeSample {
	public static void main(String[] args) {
		
        AtomicLong atomicLong = new AtomicLong();
        AtomicLong atomicLong1 = new AtomicLong(123);
        long expectedValue = 123;
        long newValue = 234;

        System.out.println(atomicLong.compareAndSet(expectedValue,newValue));
        atomicLong1.set(234);

        System.out.println(atomicLong1.compareAndSet(234,newValue));
        System.out.println(atomicLong1.compareAndSet(expectedValue,newValue));
        System.out.println(atomicLong.get());
        System.out.println(atomicLong1.get());
        
    }
}
