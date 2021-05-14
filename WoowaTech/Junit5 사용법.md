# JUnit5 사용 방법

# JUnit
자바 개발자의 대부분이 사용하는 단위 테스트 프레임워크   
* Junit5는 2017년 10월 공개
* 스프링 부트 2.2 버전 이상부터 기본 제공   

## JUnit5
Jupiter, Vintage, Junit Platform의 세 개의 모듈이 존재한다.   

* Platform: 테스트를 실행해주는 런처 제공. TestEngine API 제공
* Jupiter: Junit5를 지원하는 TestEngine API 구현체
* Vintage: Junit4와 Junit3를 지원하는 TestEngine 구현체   

## JUnit5 시작하기
* 스프링 부트 프로젝트: 스프링 부트 2.2 버전 이상부터는 기본적으로 JUnit5 의존성이 추가된다.
* 스프링 부트 프로젝트가 아닐 경우: 아래처럼 의존성을 추가하면 된다.   

```
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.6.2</version>
    <scope>test</scope>
</dependency>
```

### Annotations - @Test
* 테스트 메소드라는 것을 나타내는 Annotations
* JUnit4와 다르게 어떤 속성도 선언하지 않는다.   

```
JUnit4 ver.
@Test(expected = Exception.class)
void create() throws Exception {
    ...
}

JUnit5 ver.
@Test
void create() {
    ...
}
```

### Annotations - 생명주기(LifeCycle) Annotation
* @BeforeAll: 해당 클래스에 위치한 모든 테스트 메소드 실행 전, 딱 한 번 실행되는 메소드
* @AfterAll: 해당 클래스에 위치한 모든 테스트 메소드 실행 후, 딱 한 번 실행되는 메소드   

JUnit4의 @BeforeClass 그리고 @AfterClass와 유사하다.   

* @BeforeEach: 해당 클래스에 위치한 모든 테스트 메소드 실행 전 실행되는 메소드
* @AfterEach: 해당 클래스에 위치한 모든 테스트 메소드 실행 후 실행되는 메소드   

JUnit4의 @Before 그리고 @After와 유사하다. 그리고 매 테스트 메소드마다 새로운 클래스를 생성(new)하여 실행해야 한다. 즉, 비효율적이다.   

```
@BeforeAll

@Test
@Test
@Test
@Test
@Test
```

```
@BeforeEach
@Test

@BeforeEach
@Test

@BeforeEach
@Test

@BeforeEach
@Test

@BeforeEach
@Test
```

테스트가 조건에 영향을 끼친다면, 매 테스트 실행마다 조건들이 초기화 될 수 있도록 @BeforeEach를 사용하는 것이 좋다.   

### Annotation - @Disabled
* 테스트를 하고 싶지 않은 클래스나 메소드에 붙이는 Annotation
* JUnit4의 @Ignore과 유사   

```
class DisabledExample {
    @Test
    @Disabled("문제가 해결될 때까지 테스트 중단")
    void test() {
        ...
    }

    @Test
    void Test2() {
        ...
    }
}
```

### Annotation - @DisplayName
* 어떤 테스트인지 쉽게 표현할 수 있도록 해주는 Annotation
* 공백, Emoji, 특수문자 등을 모두 지원   

```
@DisplayName("특수 테스트\uD83D\uDE00")
class DisplayNameExample {
    @Test
    @DisplayName("좋은 테스트이다.")
    void test() {

    }
}
```

### Annotation - @RepeatedTest
* 특정 테스트를 반복시키고 싶을 때, 사용하는 Annotation
* 반복 횟수와 반복 테스트 이름을 설정 가능   

```
@RepeatTest(10)
@DisplayName("반복 테스트")
void repeatedTest() {
    ...
}

@RepeatTest(value = 10, name = "{displayName} 중 {currentRepetition" of {totalRepetitions})
@DisplayName("반복 테스트")
void repeatedTest() {
    ...
}
```

### Annotation - @ParameterizedTest
* 테스트에 여러 다른 매개 변수를 대입해가며 반복 실행할 때, 사용하는 Annotation   

```
@ParameterizedTest
@CsvSource(value = {"ACE, ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12"}, delimiter = ':')
@DisplayName("에이스 카드가 여러 개일 때 합 구하기")
void calculateCardSumWhenAceIsTwo(final String input, final int expected) {
    final String[] inputs = input.split(",");

    for (final String number : inputs) {
        final CardNumber cardNumber = CardNumber.valueOf(number);
        dealer.receiveOneCard(new Card(cardNumber, CardType.CLOVER));
    }
    
    assertThat(dealer.calculateScore()).isEqualTo(expected);
}
```

### Annotation - @Nested
* 테스트 클래스 안에서 내부 클래스를 정의하여 테스트를 계층화 할 때 사용
* 내부 클래스는 부모 클래스의 멤버 필드에 접근 가능
* Before / After와 같은 테스트 생명 주기에 관계된 메소드들도 계층에 맞춰 동작   

```
@DisplayName("findResult 메소드는")
@Nested
class findResult {
    @DisplayName("플레이어가 블랙잭일 때")
    @Nested
    class findWhenPlayerBlackjack {
        private Result findResult(Dealer dealer) {
            ...
        }
    }

    @DisplayName("딜러가 블랙잭이면 Draw를 반환")
    @Test
    void returnDraw() {
        final Dealer dealer = new Dealer();
        ...
    }
}
```

## Assertions
* 사전적 의미: 주장, 행사, 단정문
* 테스트 케이스의 수행 결과를 판별하는 메소드
* 모든 JUnit Jupiter Assertions는 static 메소드   

### Assertions - assertAll (executables 등)
* 매개 변수로 받는 모든 테스트 코드를 한 번에 실행
* 오류가 나도 끝까지 실행한 뒤 한 번에 모아서 출력   

```
@Test
public void create_study() {
    Study study = new Study();
    assertNotNull(study);
    assertEquals(Status.STARTED, study.getStatus(), "처음 상태값이 DRAFT");
    assertTrue(study.getLimit() > 0, () -> "최대 인원은 0보다 커야 한다.");
}
```

```
@Test
public void create_study() {
    Study study = new Study();
    assertAll(
        () -> assertNotNull(study),
        () -> assertEquals(Status.STARTED, study.getStatus(), "처음 상태값이 DRAFT"),
        () -> assertTrue(study.getLimit() > 0, () -> "최대 인원은 0보다 커야 한다.")
    )
}
```

### Assertions - assertThrows(expectedType, executable)
* 예외 발생을 확인하는 테스트
* executable의 로직이 실행하는 도중 expectedType의 에러를 발생시키는지 확인   

```
@Test(expected = Exception.class)
void create() throws Exception {
    ...
}

@Test
void exceptionThrow {
    Exception e = assertThrows(Exception.class, () -> new Test(-10));
    assertDoesNotThrow(() -> System.out.println("Do Something"));
}
```

### Assertions - assertTimeout(duration, executable)
* 특정 시간 안에 실행이 완료되는지 확인
* Duration: 원하는 시간
* Executable: 테스트할 로직   

```
@Rule
public Timeout timeout = Timeout.seconds(5);

class TimeoutExample {
    @Test
    @DisplayName("타임아웃 준수")
    void timeoutNotExceeded() {
        assertTimeout(ofMinutes(2), () -> Thread.sleep(10));
    }

    @Test
    @DisplayName("타임아웃 초과")
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> Thread.sleep(100));
    }
}
```

## Assumption
* 전제문이 true라면 실행, false라면 종료
* assumeTrue: false일 때, 이후 테스트 전체가 실행되지 않음
* assumingThat: 파라미터로 전달된 코드 블럭만 실행되지 않음

```
void dev_env_only {
    assumeTrue("DEV".equals(System.getenv("ENV")), () -> "개발 환경이 아닙니다.");
    assertEquals("A", "A"); // 단정문 실행되지 않음
}

void some_test() {
    assumingThat("DEV".equals(System.getenv("ENV")),
        () -> {
            assertEquals("A", "B"); // 단정문 실행되지 않음
        })
    assertEquals("A", "A");
}
```

[출처](https://www.youtube.com/watch?v=EwI3E9Natcw)