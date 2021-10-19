package com.example.singleton;

public class Settings {

    private static Settings instance;

//    외부에서 바로 가져올 수 없도록 private 선언
    private Settings() {

    }

//    외부에서 선언하여 가져올 수 있도록
    public static Settings getInstance() {
//        그러나 instance 변수를 사용하여 선언한 객체가 없을 때만 새로 생성하도록 한다면
        if (instance == null) {
            instance = new Settings();
        }

//        new를 사용하였듯이 생성한 모든 인스턴스는 각각 다르다.
//        그러나 instance 변수를 사용하고 나면 조건문을 넘어가고 나서 이미 생성된 객체가 있을 경우 기존 객체를 가져오도록 한다.
        return instance;
    }

}
