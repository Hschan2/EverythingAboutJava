package com.example.demo;

import org.junit.jupiter.api.Test;

public class SampleTests {

    // 테스트 순서는 JUnit 4.11버전부터 정해진 순서대로 실행, 이전에는 랜덤
    @Test
    public void testA() {
        System.out.println("A");
    }

    @Test
    public void testBBB() { // testBBB일 경우 첫 번째 실행, testB일 경우 두 번째 실행
        System.out.println("B");
    }

    @Test
    public void testC() {
        System.out.println("C");
    }
}
