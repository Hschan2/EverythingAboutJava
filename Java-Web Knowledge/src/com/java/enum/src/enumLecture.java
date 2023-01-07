//enum = 열거형, 상수집합
// 상수로 사용할 목록 정의한다면 세미콜론 생략 가능
// switch 문으로 사용 가능
// 소괄호(())를 사용하면 생성자를 사용 가능
// 다양한 값 사용 가능
public enum enumLecture {
    THE_JAVA_JAVA_8(50000, "더 자바, Java8"),
    THE_JAVA_CODE_MANIPULATION(49500, "더 자바, 코드를 조작하는 다양한 방법"),
    THE_JAVA_APPLICATION_TEST(66000, "더 자바, 애플리케이션을 테스트하는 다양한 방법"),
    SPRING_FRAMEWORK_INTRODUCTION(0, "스프링 프레임워크 입문"),
    SPRING_FRAMEWORK_INTRODUCTION_REVISED_EDITION(0, "예제로 배우는 스프링 입문(개정판)"),
    SPRING_FRAMEWORK_CORE(55000, "스프링 프레임워크 핵심 기술"),
    SPRING_FRAMEWORK_WEB_MVC(110000, "스프링 웹 MVC"),
    SPRING_BOOT(110000, "스프링 부트 개념과 활용"),
    SPRING_BOOT_UPDATED(66000, "스프링 부트 업데이트"),
    SPRING_AND_JPA_BASED_WEB_APPLICATION_DEVELOPMENT(330000, "스프링과 JPA 기반 웹 애플리케이션 개발"),
    SPRING_SECURITY(88000, "스프링 시큐리티"),
    REST_API(99000, "스프링 기반 REST API 개발"),
    SPRING_DATA_JPA(88000, "스프링 데이터 JPA"),
    INTERVIEW_GUIDE_SOFTWARE_DEVELOPMENT_ENGINEER(220000, "더 개발자, 인터뷰 가이드");

    // private로 선언하는 이유 - enum은 상수이기 때문에 값이 할당되거나 그러한 시도 불가능
    private int amount;
    private String korDesc;

    enumLecture(int amount) {
        this.amount = amount;
    }

    enumLecture(int amount, String korDesc) {
        this.amount = amount;
        this.korDesc = korDesc;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getKorDesc() {
        return this.korDesc;
    }

    @Override
    public String toString() {
        return this.amount + " :: " + this.getKorDesc();
    }
}
