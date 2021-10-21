package com.example.singleton;

public class Settings {

    private static Settings instance;

//    double checked locking 방법을 사용할 때 선언하는 방법. volatile로 선언하기
    private static volatile Settings instanceDCL;

//    synchronized를 사용하지 않을 때는 처음부터 생성하여 사용 (이른 초기화, eager initialization)
//    그러나 미리 만들어 나중에 사용하지 않는 경우가 발생할 수 있는 것이 단점
//    getInstance 함수에서 조건문 없이 return INSTANCE; 하면 된다.
    private static final Settings INSTANCE = new Settings();

//    static inner class 사용하는 방법 (권장하는 방법 중 하나)
//    위의 모든 변수 선언은 필요 없으며 아래 생성자만 필요
//    getInstance에서 조건문 모두 제거하고 return SettingsHolder.INSTANCE; 하면 된다.
//    멀티 스레드 환경에서 효율적이고 안전하다. 호출될 때 로딩이 되기 때문에 Lazy Loading 가능
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

//    외부에서 바로 가져올 수 없도록 private 선언
    private Settings() {

    }

//    외부에서 선언하여 가져올 수 있도록
//    synchronized = (멀티스레드 환경) 한 번에 하나의 스레드만 들어오도록 허락 아래의 문제를 해결 가능. 동기화 처리 작업으로 성능 문제 발생 단점
    public static synchronized Settings getInstance() {
//        그러나 instance 변수를 사용하여 선언한 객체가 없을 때만 새로 생성하도록 한다면
        if (instance == null) {
//            그러나 첫 인스턴스가 if문을 통과를 완료하자마자 다음 인스턴스는 if문을 통과하게 된다. 그래서 각각 다른 객체가 생성될 문제가 나온다.
            instance = new Settings();
        }

//        위의 함수 자체에 synchronized를 사용하지 않을 경우
//        두 번 체크한다고 하여 double checked locking 방법
//        조건문 내부에서 synchronized 사용
//        함수 자체에 synchronized를 사용하는 것보다 효율적인 이유 - 매번 synchronized를이 실행되는 것이 아니기 때문. 즉 해당 메서드에 들어오는 모든 스레드에 동기화하는 것이 아닌 것이 장점
//        그리고 필요한 시점에 만들 수 있는 장점
        if (instanceDCL == null) {
            synchronized (Settings.class) {
                if (instanceDCL == null) {
                    instanceDCL = new Settings();
                }
            }
        }

//        new를 사용하였듯이 생성한 모든 인스턴스는 각각 다르다.
//        그러나 instance 변수를 사용하고 나면 조건문을 넘어가고 나서 이미 생성된 객체가 있을 경우 기존 객체를 가져오도록 한다.
        return instance;
    }

}
