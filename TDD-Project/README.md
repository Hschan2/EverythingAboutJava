# TDD란 무엇인가?
Test Driven Development의 약자이며 메인 클래스를 실행하기 전 잘 작동되는지 확인하기 위해 테스트 코드를 먼저 만들어서 확인하는 방법

## TDD를 Java로 할 수 있도록 도와주는 오픈 소스 도구 JUnit
JUnit은 개발 툴, 이클립스 등과 같은 툴에서 TDD를 Java로 할 수 있도록 도와준다.<br>
(프로젝트 오른쪽 마우스 -> 속성 -> Java Build Path -> Add Library -> JUnit 추가)

## TDD를 하기 위한 테스트 클래스는 어떻게 만드나?
프로젝트 -> src -> 메인 패키지 -> 테스트 패키지 오른쪽 마우스 -> new -> JUnit Test Case -> name 설정 -> Class under test의 browse.. -> 테스트할 원본 클래스를 선택하면 된다. <br><br>

## TDD를 간단하게 체험해보자
업로드한 프로젝트에서 합계를 구하는 클래스를 만들어보았다.
```
public class Calculator {
  public int sum(int num1, int num2) {
    return num1 + num2;
  }
}
```

메인 클래스에서 sum 클래스를 작성하였고 이를 테스트를 해서 제대로 확인해보기 위해 Test 클래스를 만들어보자
```
class CalculatorTest {
  @Test
  void testSum() {
    Calculator calculator = new Calculator();
    assertEquals(30, calculator.sum(10, 20));
  }
}
```
Calculator calculator = new Calculator();는 테스트 할 원본 클래스를 선언한다.   

assertEquals(30, calculator.sum(10, 20));는 assertEquals(a, b)는 객체 a와 b의 값이 같은지 확인해달라의 의미이다. 즉, 30과 10+20이 같은가?   

만약 assertEquals의 결과가 True 이면 JUnit의 상태가 초록색이 될 것이다. 반대로 False라면 빨간색이 될 것이다.   

TDD를 이용하면 이전에 제대로 작동되고 있는지 확인하기 위해 각 클래스마다 println을 할 필요가 없어진다.

## JUnit을 더 알아보자
- 단정문
  * assertArrayEquals(a, b) => 배열 a와 b의 값을 비교<br>
  * assertEquals(a, b) => 객체 a와 b의 값을 비교<br>
  * assertSame(a, b) => 배열 a와 b가 같은 객체인지 확인<br>
  * assertTrue(a) => a가 참인지 확인<br>
  * assertNotNull(a) => a객체가 null이 아닌지 확인<br>
  * [Assert를 더 알아보기 위해](http://junit.sourceforge.net/javadoc/org/junit/Assert.html)

- 어노테이션
  * @Test => 메소드가 테스트 용임을 알린다.<br>
  * @Test(timeout=5000) => 메소드의 연산이 5초가 넘으면 실패를 알린다.<br>
  * @Test(expected=RuntimeException.class) => 원하는 Exception이 제대로 동작하는지 확인하기 위해 사용. scanner로 잘못된 값을 하나씩 넣어서 확인할 일이 줄어든다. (메소드가 실행될 때 딱 한번만 수행되도록 지정하기)<br>
  * @BeforeClass => @Test를 "실행하는 과정에서" 맨 먼저 수행되어야 할 경우를 지정. 예를 들어 데이터 베이스를 연결할 때 드라이버 로딩하기<br>
  * @AfterClass => @Test를 "실행하는 과정에서" 맨 마지막에 수행되어야 할 경우를 지정. 예를 들어 데이터 베이스 사용이 다 끝나고 드라이버를 반납할 때. (메소드 앞과 뒤로 '독립적인' 수행하도록 지정)<br>
  * @Before => @Test로 지정된 메소드가 "실행되기 전"에 수행 (공동으로 생성할 객체가 있는 경우)<br>
  * @After => @Test로 지정된 메소드가 "다 끝난 후" 수행<br>

## TDD의 장점과 단점은 무엇일까?
- 장점
  1. 요구 사항이 매번 바뀔 수 있는 경우   
  1. 개발 도중에 코드를 많이 바꿀 확률이 있을 경우   
  1. 개발한 코드를 유지 보수할 경우   
  1. 불확실성이 큰 경우   

- 단점
  1. 테스트할 클래스를 위해 테스트 클래스를 생성   
  1. 테스트할 클래스를 작게 쪼개어 일일이 성공하는지 확인하고 추가적 코드를 작성해야 한다   
  1. 즉, 시간이 오래 걸릴 수 있다   



