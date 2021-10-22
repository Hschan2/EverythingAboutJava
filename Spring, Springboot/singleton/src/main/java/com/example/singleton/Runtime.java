package com.example.singleton;

public class Runtime {
    public static void main(String[] args) {

//        Runtime은 이미 정의갇 되어있기 때문에 get으로 가져와야 한다
        java.lang.Runtime runtime = java.lang.Runtime.getRuntime();

//        현재 메모리 가져오기
        System.out.println(runtime.maxMemory());
        System.out.println(runtime.freeMemory());
    }
}
