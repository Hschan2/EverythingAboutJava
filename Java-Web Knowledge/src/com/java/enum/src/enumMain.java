import java.util.*;

public class enumMain {
    public static void main(String[] args) {
        
        // 정의된 enum list를 이용하여 switch문으로 조회하기
        // enum 클래스는 클래스 타입이지만 new 키워드로 객체 생성 불가
        enumLecture list = enumLecture.SPRING_FRAMEWORK_CORE;

        // enumLecture에서 생성자를 사용하기 전
        int amount = 0;

        switch (list) {
            case THE_JAVA_JAVA_8: amount = 55000; break;
            case THE_JAVA_CODE_MANIPULATION: amount = 49500; break;
            case THE_JAVA_APPLICATION_TEST: amount = 66000; break;
            case SPRING_FRAMEWORK_INTRODUCTION: amount = 0; break;
            case SPRING_FRAMEWORK_INTRODUCTION_REVISED_EDITION: amount = 0; break;
            case SPRING_FRAMEWORK_CORE: amount = 55000; break;
            case SPRING_FRAMEWORK_WEB_MVC: amount = 110000; break;
            case SPRING_BOOT: amount = 110000; break;
            case SPRING_BOOT_UPDATED: amount = 66000; break;
            case SPRING_AND_JPA_BASED_WEB_APPLICATION_DEVELOPMENT: amount = 330000; break;
            case SPRING_SECURITY: amount = 88000; break;
            case REST_API: amount = 99000; break;
            case SPRING_DATA_JPA: amount = 88000; break;
            case INTERVIEW_GUIDE_SOFTWARE_DEVELOPMENT_ENGINEER: amount = 220000; break;
        }

        System.out.println(list + "수강료는 " + amount + "(원)입니다.");

        // enumLecture에서 생성자를 사용하기 후
        System.out.println(list.getKorDesc() + " 수강료는 " + list.getAmount() + "(원)입니다.");

        // enum이 제공하는 메서드, values()
        // enum 안에 선언된 모든 상수들을 배열로 반환
        enumLecture[] lists = enumLecture.values();

        for (enumLecture value : lists) {
            System.out.println(value.toString() + " (" + value.getAmount() + " 원) => " + value.getKorDesc());
        }
        
        // enum이 제공하는 메서드, valueOf
        // enum 안에 존재하는 상수를 가져올 때 사용
        // 실제로 열거된 상수와 대소문자, 공백까지 모두 완벽히 일치할 경우 해당 상수를 반환하고 아닐 경우 예외 발생
        String exists_in_enum = "THE_JAVA_JAVA_8";
        String not_exists_in_enum = "THE_JAVA_JAVA_5";
        enumLecture myList = enumLecture.valueOf(exists_in_enum);
        
        try {
            enumLecture another_myList = enumLecture.valueOf(not_exists_in_enum);
            System.out.println(another_myList.getKorDesc());
        } catch (IllegalArgumentException e) {
            System.out.println("존재하지 않는 상수를 사용하면 예외 발생");
        }

//        enum 키워드를 사용한 enum 클래스는 클래스를 기본적로 상속 받으며 java.lang.Enum는 Object 클래스를 상속 받는다.
//        enum 클래스는 기본적으로 Object 클래스에 정의도어 있는 메소드를 사용 가능하며, 일부는 오버라이드를 하지 못하도록 막아두었다.
//        equals(), hashCode(), finalize() 등은 final로 정의되어 있다.
        
//        java.lang.Enum
//                다중 상속을 지원하지 않는 java에서 enum  클래스는 별도의 상속을 받을 수 없다. => java.lang.Enum을 상속받기 때문에
//                toString 메소드는 Object로부터 상속받은 메소드를 재정의한 것들 중에서 유일하게 final로 재정의하지 않는 메소드

        enumLecture myLec_1 = enumLecture.SPRING_FRAMEWORK_CORE;
        enumLecture myLec_2 = enumLecture.SPRING_DATA_JPA;

//      ordinal = enum 클래스에 나열된 상수가 몇 번째에 나열되어 있는지 zero base로 넘버링한 위치를 반환
//      ordinal을 쉽게 말해서 enum의 순서를 의미한다.
        System.out.println("myLec_1 ordinal :: " + myLec_1.ordinal());
        System.out.println("myLec_2 ordinal :: " + myLec_2.ordinal());

//      compareTo = ordinal 값의 차이를 반환
        System.out.println("diff ordinal :: " + (myLec_1.ordinal() - myLec_2.ordinal()));
        System.out.println("compareTo :: " + myLec_1.compareTo(myLec_2));

//      name = 상수의 값 그 자체를 반환
//      toStirng = 오버라이드하지 않으면 name과 같은 결과 반환
        System.out.println(myLec_1.name());
        System.out.println(myLec_1.toString());

//        EnumSet
//               Set 인터페이스를 구현하고 있으며, 일반적으로 알고 있는 Set 자료구조의 특징을 가지고 있다.
//                (예. 중복을 허용하지 않는다 등)

        EnumSet<MyEnum> enumSet = EnumSet.allOf(MyEnum.class);
        System.out.println(enumSet); // 전체 출력

        // 특정 상수 범위 선언하기
        EnumSet newEnumSet = EnumSet.of(MyEnum.MON, MyEnum.TUE, MyEnum.WED, MyEnum.THU, MyEnum.FRI);
        System.out.println(newEnumSet); // 특정 상수만 출력
        System.out.println(EnumSet.complementOf(newEnumSet)); // 특정 상수 제외하고 출력
        System.out.println(EnumSet.range(MyEnum.WED, MyEnum.FRI)); // 범위 출력
        
//        이 클래스는 모든 메소드가 static 키워드를 사용하여 정의되어 있어 객체 생성없이 사용 가능하다.
//        객체 생성없이 사용할 수 있다고 했지만 사실 객체를 생성할 수 없다. => 이 클래스는 abstract 키워드를 사용한 추상 클래스이기 때문

//        EnumSet 외에 EnumMap이 있다.
//        Map 인터페이스를 구현하고 있으며, Map 인터페이스를 구현한 HashMap 또는 TreeMap 등과 비교했을 때,
//                정해진 상수를 사용하기 때문에 해싱을 하지 않고, enum을 정의할 때 이미 순서가 정해져 있기 때문에 성능상 이점이 많다.
        EnumMap<enumLecture, String> enumMap = new EnumMap<>(enumLecture.class);
        enumMap.put(enumLecture.REST_API, "수강하고 싶다");
        enumMap.put(enumLecture.SPRING_FRAMEWORK_CORE, "이것도 수강하고 싶다");

        for (Map.Entry<enumLecture, String> entry : enumMap.entrySet()) {
            System.out.println(entry.getKey().getKorDesc() + " :: " + entry.getValue());
        }
    }
}
