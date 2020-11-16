## 하나의 메서드는 하나의 기능을 수행하도록 만들자

먼저 프로그래밍에서 메서드란 무엇일까? 위키피디아에서는 이렇게 설명하고 있다.
```
Wikipedia

객체 지향 프로그래밍(OOP)에서 메서드는 객체와 관련된 프로시저이다.
객체는 데이터와 동작으로 구성되고 데이터 및 행동은 인터페이스를 포함한다.
따라서 메서드는 클래스 안에서 정의되며, 인터페잇느느 서로 협력하는 객체간의 메시지를 보낼 수 있도록 지정한다.
```

그러나 개발을 하다보면 구현해야 할 사항이 많아지고 객체들의 관계가 복잡해지면 메서드의 길이가 길어지고 하나의 메서드가 여러 기능을 하는 일이 발생한다.
<br>
여기서 전체 메서드의 개수를 줄이고 한 메서드 안에 여러 기능을 구현하는 것이 좋다고 말할 수가 있을까?
<br><br>
답은 <b>아니다!</b>라고 말하고 있다.

많은 개발자들이 왜 아니라고 말하는지 이유를 알아보려고 한다.

```
... (Cars.java in racingcar)

public void moveAllCarOneTime() {
    for (Car car : raceCars) {
        car.increasePositionOrNot(Util.getRandomNumber());
        if (car.checkGreaterThanMaxPos(maxPosition)) {
            ++this.maxPosition;
        }
    }
}
```
위 메서드이 하는 기능이 무엇일까? 먼저 메서드의 이름을 확인해보면 모든 자동차에 대해 move를 수행하는 역할처럼 보인다. 실제로 이 역할로 수행을 할까?
<br>

코드를 확인해보면 반복문을 돌면서 increasePositionOrNot 메서드를 수행하고 있지만 checkGreaterThanMaxPos 메서드를 추가적으로 실행하고 maxPosition을 확인하고 해당 값을 업데이트까지 해주고 있다.
<br>

maxPosition는 우승자를 구하는 로직에 쓰이는 중요한 값이지만 moveAllCarOneTime 메서드가 값을 변경할 책임과 명분이 없다.
<br>

위 코드를 통해서 우승자를 구하기 위해 필요한 maxPosition을 반복문을 돌며 구해야 하기 때문에 한 번에 처리하고 싶은 의도가 있을 수 있다. 그러나 작업 내용이 정확하게 메서드 안에 드러나있지 않기
때문에 메서드 이름만 보고 충분히 오해할 수 있는 일이 발생한다. 그래서 우승자를 구하는 로직을 다른 함수로 통해 작성되어야 한다.
<br>

위의 moveAllCarOneTime 메서드의 문제점이 무엇일까?

- 메서드 명이 메서드의 기능을 포괄하지 못한다.
- 우승자와 관련된 요구 사항을 변경할 때 moveAllCarOneTime 메서드를 수정해야 한다.
- 반환 값이 없고 두 가지 기능을 수행하고 있기 때문에 나중에 테스트하기 어렵다.

위의 moveAllCarOneTime 메서드의 규모가 작기 때문에 와 닿지 않을 수 있다. 그러나 규모가 커지면 로직이 복잡해진다면 문제점들이 커질 것이다.
<br>

그래서 개발을 할 때 하나의 기능만 수행하는 작은 다위의 메서드를 만드는 습관을 가지는 것이 좋다.
<br>

<b>하나의 메서드가 하나의 기능만 구현하도록 코드를 명료하게 작성</b>하는 것의 장점은 무엇일까?

#### 1. 메서드 명만 보고 어떤 기능을 수행하는지 명확하게 알 수 있다.
기능의 단위가 짧고 명확한다면 메서드 이름을 지을 때도 수월할 수 있다.
<br>

잘 지은 메서드 명은 가독성을 증가시키고 유지 보수를 수월하게 만들어 준다. 메서드 이름 길이는 중요하지 않지만 축약된 단어를 쓰지 말고, 
메서드가 수행하는 기능을 잘 대변할 수 있는 이름을 짓는 것이 중요하다
<br>

메서드 네이밍 컨벤션도 지키며 코드를 작성하면 많은 도움이 된다. ([메서드 네이밍 컨벤션](https://woowacourse.github.io/javable/2020-04-26/Method-Naming))
<br>

#### 2. 코드의 재사용이 증가하여 요지 보수가 용이하다.
메서드를 작은 단위로 나눌수록 여러 곳에서 쉽게 가져다 재사용할 수 있다. 예를 들어서 레고 블록과 같다. 단일 블럭은 다른 곳에서 재사용하기 쉽지만
여러 블럭을 붙인 큰 단위가 된 블럭은 본래의 기능으로 재사용하기 쉽지 않다.
<br>

위의 예시 코드에서 우승자를 구하는 코드가 아닌 꼴찌를 구하는 코드로 변경되어야 한다면 아마도 이름만 보고 결과를 구하는 것과 관련이 전혀 없는
moveAllCarOneTime 메서드에서 코드 변경이 이루어져야 할 것이다.
<br>

하지만 maxPosition을 findMaxPosition이라는 이름의 메서드로 따로 분리하여 작은 단위로 사용한다면 우승자와 관련된 요구 사항이 변경될 때마다 해당 메서드에서만
코드 변경이 일어나고 이는 코드의 재사용을 돕는다. 
<br>

이러한 상황을 예시로 다시 알아보자
```
// Cars.java
public int findMaxPosition() {
    return cars.stream()
          .mapToInt(Car::getPosition)
          .max()
          .getAsInt();
}
...

// WinnerCar.java
public Car findWinnerCar(Cars cars) {
    int maxPosition = cars.findMaxPosition();  // findMaxPosition 메서드 재사용
    return cars.getCars().stream()
            .filter(car -> car.isPosition(maxPosition))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
}

public void showWinnerPosition(Cars cars) {
    System.out.println("max position : " + cars.findMaxPosition());  // findMaxPosition 메서드 재사용
}
```
위 예시에서 확인해보면 MaxPosition을 구하는 메서드, findMaxPosition로 따로 분리하고 나니 WinnerCar 클래스 내의 findWinnerCar 메서드와
showWinnerPosition 메서드에서 재사용을 할 수 있게 됐다. 메서드를 작은 단위로 분리할수록 메서드의 응집도를 높일 수 있고 결과적으로 유지 보수를
용이하게 만들어준다.

#### 3. 단위 테스트가 수월해진다.
테스트 주도 개발(TDD)를 할 때 도메인 객체들의 기능을 테스트하기 위해서 단위 테스트를 작성한다. 만약 맨 위의 예시에 있는 moveAllCarOneTime 메서드의 테스트 코드는 어떻게 작성될까?
<br>

어떻게 작성했는지 확인해보기 전에 검증을 제대로 할 수 있는지 생각해보는 것이 먼저다. 현재 메서드는 Car에 메시지를 보내 내부 로직을 수행함과 동시에 maxPosition이라는 전역 변수의 값을 변경하고 있다.
테스트를 수행하는 동안 전역 변수의 값이 변하지 않는다고 보장하지 않는다. 그리고 메서드가 반환 값이 없는 타입이기 때문에 mxPosition을 확인하는 테스트를 작성하기 어렵다.
<br>

하지만 분리된 코드들의 예시와 같이 maxPosition을 구하는 findMaxPostion이라는 메서드를 분리한다면 다음과 같이 테스트 코드를 추가적으로 작성할 수 있다.
```
// CarsTest.java
@DisplayName("Max Position 확인")
@Test
public void maxPosition() {
    Car car1 = new Car("c1");
  
    car1.increasePositionOrNot(4);
    car1.increasePositionOrNot(5);
  
    Cars cars = new Cars(Arrays.asList(car1));
  
    assertThat(cars.findMaxPosition()).isEqualTo(2);
}
```

### 결론
하나의 기능을 하도록 메서드를 구현하면 살펴본 장점들을 확보할 수 있고 전체적인 가독성을 높일 수 있다. 장문의 글보다 짧은 다락으로 구성된 글이 읽기 쉬운 것처럼 말이다.
<br>

메서드를 작성할 때 가장 좋은 라인의 수는 5~10줄이라고 한다. 가능한 10줄 내의 길이를 가지고 하나의 기능을 가진 메서드를 작성하는 습관을 들이자.

[내용 출처](https://woowacourse.github.io/javable/2020-05-10/single-job-method)
