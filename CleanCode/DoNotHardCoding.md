## 하드코딩을 하지 말자
좋지 않은 코딩 습관을 이야기할 때 꼭 나오는 이야기는 하드 코딩이다.

### 하드코딩이 무엇인가?
프로그램의 소스 코드에 데이터를 직접 인력해서 저장하는 것이다. (파일 경로, URL, IP 주소, 비밀번호, 화면에 출력되는 문자열 등)

예시.
```
File file = new File("D:/Eclipse/Java/Output.txt");
```
위 코드는 파일 경로를 직접 넣어준 경우의 하드코딩을 의미한다.

```
public void move(int randomNumber) {
    if (randomNumber > 4) {
        this.position++;
    }
}
```
위 코드는 소스 코드에 직접 입력이 된 특정한 숫자를 의미하는 매직 넘버를 사용한 하드코드를 의미한다. (randomNumber > 4의 4)

### 하드코딩의 문제점은?
1. 의미를 파악하기 어렵다
1. 유지 보수하기 어렵다

#### 의미를 파악하기 어렵다

```
public class Car {
    private final String name;
    private int position;

    public Car(int position) {
        this("orange", position);
    }

    public Car(String name) {
        this(name, 0);
    }

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }
}
```

위 Car 클래스에서 두 개의 생성자를 확인해보면

```
public Car(int position) {
        this("orange", position);
    }

public Car(String name) {
        this(name, 0);
    }
```
무엇을 의미하는가 확실하게 알 수가 없다. 즉, 가독성이 좋지 않은 코드가 되는 것이다.

```
public class Car {
    // private static final => 외부에서 접근할 수 없으며 한 번만 할당하며 컴파일 시간에 메모리 할당을 할 것(프로그램 실행 중에 메모리 수명 유지)
    private static final String DEFAULT_NAME = "orange";
    private static final int DEFAULT_POSITION = 0;

    private final String name;
    private int position;

    public Car(int position) {
        this(DEFAULT_NAME, position);
    }

    public Car(String name) {
        this(name, DEFAULT_POSITION);
    }

    public Car(String name, int position) {
        this.name = name;
        this.position = position;
    }
}
```
위 코드에서 보면 orange가 DEFAULT_NAME를 의미하는 것을 알 수 있고 0이 DEFAULT_POSITION을 의미하는 것을 알 수 있다.

#### 유지 보수하기가 어렵다

```
// 월 원금을 구하는 메서드
public static double calculateMonthlyPrincipal(int principal) {
    return principal / 12;
}

// 월 이자를 구하는 메서드
public static double calculateMonthlyInterest(int principal) {
    return principal * 0.1 / 12;
} 
```
위 코드에서 12를 사용하여 코드를 작성하였다. 만약 실제라면 12를 사용하는 코드들이 매우 많을 것이다.<br>
만약 이 숫자를 변경해야 한다면 어떻게 해야 할까? 만약 하드코딩처럼 모든 것을 하나씩 변경해야 할 것이다.<br>
만약 하드코딩으로 수정하였다면 일부 변경하지 않는 실수를 만들 수 있다.<br>
이렇게 하드코딩을 하게 되면 수정이 어려운 코드를 작성하게 될 것이다. 즉, 유지보수하기가 힘들어질 것이다.

```
private static final int INSTALLMENT_MONTHS = 12;
private static final double INTEREST_RATE = 0.1;

// 월 원금을 구하는 메서드
public double calculateMonthlyPrincipal() {
    return this.principal / INSTALLMENT_MONTHS;
}

// 월 이자를 구하는 메서드
public double calculateMonthlyInterest() {
    return this.principal * INTEREST_RATE / INSTALLMENT_MONTHS;
}
```
위 코드처럼 할부 개월을 상수로 선언하여 사용하는 것이 좋다. 만약 개월 수를 10으로 변경해야 한다면 INSTALLMENT_MONTHS의 값을 10으로 수정하면 되기 때문이다.

### 결론
- 의미를 명확하게 하고 가독성이 좋은 코드, 유지 보수하기 좋은 코드를 작성하기 위해서 상수를 활용하자.
- 하드코딩의 문제점은 위에서 이야기한 것 외에 많은 것이 있다.
- 상수를 활용하는 방법 외에 연관된 상수를 ENUM으로 관리하거나 외부에 데이터를 저장하는 등의 방법을 사용하자
<br>
#### 하드코딩이 무조건 좋지 않다?
그것은 아니다. 값이 절대로 바뀌지 않다면 사용해도 괜찮다. 예를 들어서 원주율, 3.141592...는 값이 변경될 일이 없기 때문에 매직 넘버로 사용해도 된다.<br>
<br>
개발을 할 때 더 좋은 코드를 작성하기 위해 항상 고민하는 습관을 가지자
<br>

[출처](https://woowacourse.github.io/javable/2020-05-07/avoid-hard-coding)
