## Getter를 사용하는 대신 객체에 메시지를 보내자
getter를 사용하는 대신 객체에 메시지를 보내자는 말이 무엇일까? <br> getter를 사용하지 말자는 것일까? <br> 그리고 객체에 메시지를 보내자는 뜻이 무엇일까? <br><br> 이러한 의문을 해결하기 위해 계속 학습하였다. <br>

- getter는 멤버 변수의 값을 호출하는 메소드, setter는 멤버 변수의 값을 변경하는 메소드
- <b>자바 빈 설계 규약</b>에 따르면 자바 빈 크래스 설계 시 멤버 변수의 접근 제어자는 private이며 모든 멤버 변수에 대해 getter와 setter가 존재해야 한다.<br>
  get 메소드는 매개 변수가 없어야 하며 set 메소드는 하나 이상의 매개 변수가 있어야 한다.

상태 값을 갖는 객체는 상태 값을 외부에서 직접 접근해 변경하지 못하도록 메소드만 노출시킨다. 이 때, 멤버 변수(상태 값)는 접근 제한자를 private로 설정해 적접적인 접근을 막고, getter와 setter를 사용해 변수에 접근이 가능하게 한다.
```
private String name; // 외부에서 직접적인 접근 불가를 위해 private

// setter - name의 값을 변경
public void setName(String name){
    this.name = name;
}

// getter - name의 값을 호출
public String getName(){
    return this.name;
}
```

<b>상태를 갖는</b> 객체는 getter를 통해 가져온 상태 값을 통해 로직을 수행하는 경우가 있는데 무조건적으로 모든 멤버 변수를 호출하는 getter 메소드를 생성하는 것이 맞을까?

### 무분별한 Getter?? 객체에 메시지를 보내 객체가 로직을 수행하게 만들자
객체는 캡슐화된 상태와 외부에 노출되어 있는 행동을 갖고 있고 다른 객체와 메시지를 주고 받으면서 협력한다. 객체는 메시지를 받으면 그에 따른 로직을 수행하게 되며 필요하면 객체 스스로 내부의 상태 값도 변경한다.<br>
<b>간단히 말해서 객체지향 프로그래밍은 객체가 스스로 일을 하게 만드는 프로그래밍이다.</b> <br>

모든 멤버 변수에 getter를 생성하고 상태 값을 꺼내 그 값을 객체 외부에서 로직을 수행한다면, 객체가 로직을 갖고 있는 형태가 아니면서 메시지를 보내고 받는 형태도 아니게 된다. 그리고 객체 스스로 상태 값을 변경하는 것도 아니며 외부에서 상태 값을 변경할 수 있는 문제가 발생할 수 있다.<br>

따라서 이는 객체스럽지 못한 것이라고 할 수 있다.<br>

그리고 getter를 남용하면 디미터의 법칙을 위반할 수 있고 가독성이 떨어질 수 있다.<br>

Cars 클래스를 예를 들어서 학습해보자 (Cars 클래스는 여러 자동차의 역할을 한다)
```
public class Cars {
     public static final String DELIMITER = ",";
     public static final int MINIMUM_TEAM = 2;
     private List<Car> cars;

     public Cars(String inputNames) {
         String[] names = inputNames.split(DELIMITER, -1);
         cars = Arrays.stream(names)
                 .map(name -> new Car(name.trim()))
                 .collect(Collectors.toList());
         validateCarNames();
     }
         ...

    public List<String> findWinners() {
        final int maximum = cars.stream()
                  .map(car -> car.getPosition())	
                  .max(Integer::compareTo)
                  .get();
           
        return cars.stream()
                .filter(car -> car.getPosition() == maximum)
                .map(Car::getName)
                .collect(Collectors.toList());
    } 
         ...
}
```

자동차 중에서 position 값이 가장 큰 자동차를 구하는 findWinners() 메소드를 보자면

```
public List<String> findWinners() {
    final int maximum = cars.stream()
              .map(car -> car.getPosition()) // Car객체의 position = 자동차가 움직인 거리
              .max(Integer::compareTo)
              .get();
           
    return cars.stream()
            .filter(car -> car.getPosition() == maximum)
            .map(Car::getName)
            .collect(Collectors.toList());
} 
```
위 코드를 보면 cars 객체에서 getter를 사용하여 getPosition을 사용해 position 값을 직접 가져와서 비교한다. 과연 이 방법이 옳은 방법일까?

<br>

위는 Car 객체의 접근 제한자가 private인 변수 position 상태 값 끼리 비교하는 로직이다. 따라서 Car 객체에 position 값을 비교할 때 또 다른 Car 객체를 넘겨주고 Car 끼리 position을 비교해야 한다.<br>
<b>Cars에서 해야 하는 일이 아니라 Car에서 해야 하는 일이다.</b>

Car 객체 내에서 같은 자동차끼리 position 값을 비교하고 Car 객체 내에서 maximum 과 일치하는가 비교하도록 Cars 로직을 Car 안으로 옮기자.<br>

말하자면 Car 객체에 position 값을 비교할 수 있도록 메시지를 보내고 Car 객체에 maximum 값과 자신의 position 값이 같은가 물어보는 메시지를 보내어 getPosition()을 사용하지 않도록 바꿔보자.

```
public class Car implements Comparable<Car> {
         ...
    public boolean isSamePosition(Car other) {
        return other.position == this.position;
 	}
 	
    @Override
    public int compareTo(Car other) {
        return this.position - other.position;
    }
         ...
}

public class Cars {
         ...
    public List<String> findWinners() {
        final Car maxPositionCar = findMaxPositionCar();
        return findSamePositionCars(maxPositionCar);
    }
    
    private Car findMaxPositionCar() {
        Car maxPositionCar = cars.stream()
            .max(Car::compareTo)
            .orElseThrow(() -> new IllegalArgumentException("차량 리스트가 비었습니다."));
    }

    private List<String> findSamePositionCar(Car maxPositionCar) {
        return cars.stream()
            .filter(maxPositionCar::isSamePosition)
            .map(Car::getName)
            .collect(Collectors.toList());
    }
}
```
위는 getPosition()을 없애는 방향응로 재설정한 코드이다. Car에서 Comparable을 상속받아서 compareTo()로 구현하여 Car 객체 내에서 자동차끼리 비교한다. max를 통해서 cars 중, 최대 길이의 position을 가진 Car를 찾을 수 있다. 그리고 isSamePosition()을 구현하여 Car 내에서 직접 position 값을 비교할 수 있다.<br>

```
상태를 가진 객체를 추가했다면 객체가 제대로 된 역할을 하도록 구현해야 한다. 객체가 로직을 구현하도록 해야 한다.
상태 데이터를 꺼내서 로직을 처리하도록 구현하지 말고 객체에 메시지를 보내서 일을 하도록 하자.
```

### Getter를 무조건 사용하지 말라는 것은 아니다.
Getter를 무조건 사용하지 않고 기능을 구현하기는 힘들 수 있다. 출력을 위한 값처럼 순수 값 프로퍼티를 가져오려면 어느 정도 getter를 사용해도 된다. 그러나 Collection 인터페이스를 사용하는 경우에는 외부에서 getter 메서드로 얻은 값을 통해 상태 값을 변경할 수 있다.<br>

```
비추천하는 방법
public List<Car> getCars() {
		return cars;
	}

추천하는 방법
public List<Car> getCars() {
		return Collections.unmodifiableList(cars);
	}
```
unmodifiableList는 외부에서 값을 변경하지 못하도록 하는 기능이다. 이처럼 Collections.unmodifiableList()처럼 Unmodifiable Collecion을 사용해서 외부에서 변경하지 못하도록 하는 것이 좋다.<br>

[참조 사이트](http://onair.jtbc.joins.com/)
