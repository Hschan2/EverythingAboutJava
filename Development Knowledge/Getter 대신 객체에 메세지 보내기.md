# Getter를 사용하는 대신 객체에 메세지를 보내자
* Getter: 멤버 변수의 값을 호출하는 메소드
* Setter: 멤버 변수의 값을 변경시키는 메소드   

개념 정의로 빈 클래스 설계를 할 때, 클래스의 멤버 변수의 접근 제어자는 ```Private```이고 모든 멤버 변수에 대해 ```Get 메소드```와 ```Set 메소드```가 존재한다. Get 메소드는 매개변수가 없어야 하고 Set 메소드는 하나 이상의 매개변수가 있어야 하며, 이는 좋은 코드를 위한 자바 메서드 네이밍에도 설명이 되어 있다.   

상태의 값을 갖는 객체는 상태값을 외부에서 직접 접근하여 변경하지 못하도록 메소드만 노출하도록 되어 있다. 멤버 변수(상태값)은 접근 제헌자를 Private으로 설정해 직접적인 접근을 막고 Getter와 Setter를 이용해서만 변수에 접근하도록 한다.   

```
private String name;

// Setter name의 값을 변경
public void setName(String name){
    this.name = name;
}

// Getter name의 값을 호출
public String getName(){
    return this.name;

```

<b>상태를 갖는 객체</b>는 ```Getter```를 통해 가져온 상태값을 통해 로직을 수행하는 경우가 존재하는데 무조건적으로 모든 멤버 변수를 호출하는 Getter 메소드를 생성하는 것이 맞는지 생각해보아야 한다.   

## 무분별한 Getter, 객체에 메세지를 보내 객체가 로직을 수행하도록 하자
객체는 캡슐화된 상태와 외부에 노출되어 있는 행동을 갖는다. 다른 객체와 메세지를 주고 받으며 협력한다. 객체는 메세지를 받으면 객체는 그에 따른 로직을 수행하고 필요하다면 객체 내부의 상태값도 변경한다. 간단히 객체지향 프로그래밍은 객체가 스스로 일하는 것이다.   

모든 멤버변수에 Getter를 생성하고 상태값을 꺼내 그 값으로 객체 외부에서 로직을 수생하면 객체가 로직을 갖고 있는 형태가 아니고 메세지를 주고 받는 형태도 아니다. 객체 스스로 상태값을 변경하는 것도 아니고 외부에서 상태값을 변경할 수 있는 위험도 발생한다. 이는 객체스럽지 못하다.   

Getter를 남용하면 디미터의 법칙을 위반할 수 있고 가독성이 떨어질 수 있다. Getter 남용으로 인한 디미터 법칙을 위반한 예도 존재한다.   

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

```Cars 클래스```는 여러 자동차의 역할을 한다. 그리고 여러 자동차들 중에서 Position 값이 제일 큰 우승 자동차를 구하는 ```findWinners메소드```가 있다.   

```
public List<String> findWinners() {
    // Car 객체의 position = 자동차가 움직인 거리
    final int maximum = cars.stream()
              .map(car -> car.getPosition())
              .max(Integer::compareTo)
              .get();

    return cars.stream()
            .filter(car -> car.getPosition() == maximum)
            .map(Car::getName)
            .collect(Collectors.toList());
}
```

Car 객체에서 ```getPosition()```을 사용해 Position 상태값을 직접 꺼내어 비교한다. 그러나 Cars 클래스에서 position 값을 비교해 로직을 수행하는 것이 좋은 것일까?   

Car의 접근 제한자가 private 멤버변수 position 값 끼리 비교하는 로직이다. 따라서 Car 객체에 position 값을 비교할 다른 Car 객체를 넘겨주고 Car 끼리 position을 비교하도록 해야 한다. Cars가 아닌 Car에서 해야할 일인 것이다.   

Car 객체 내 같은 자동차끼리 position 값을 비교하고 Car 객체 내 maximum과 일치하는지 비교하도록 Cars의 로직을 Car 안으로 옮기는 작업이 필요하다.   

즉, Car 객체에 position 값을 비교할 수 있도록 메세지를 보내고 Car 객체에 maximum 값과 자신의 position 값이 같은지 물어보는 메세지를 보내 ```getPosition()```을 사용하지 않도록 변경해야 한다.   

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

```getPosition()```을 없애는 방향으로 변경할 수 있다. Car에서 Comparable을 상속받아서 ```compareTo()```를 구현해 Car 내 자동차끼리 비교할 수 있다. max를 통해 cars 중 최대 길이의 position을 가진 Car를 찾을 수 있고 ```isSamePosition()```을 구현해 Car 내 직접 position 값을 비교할 수 있다.   

이와 관련해 개발자 박재정(포비)님은 다음과 같이 언급하였다.   

```
상태를 가지는 객체를 추가했다면 객체가 제대로 된 역할을 하도록 구현해야 한다.
객체가 로직을 구현하도록 해야한다.
상태 데이터를 꺼내 로직을 처리하도록 구현하지 말고 객체에 메시지를 보내 일을 하도록 리팩토링한다.
```

## Getter를 무조건 사용하지 말라는 것은 아니다
Getter를 무조건 사용하지 않고 기능을 구현하는 것은 생각보다 어렵다. 출력을 위한 값 등 순수 값 프로퍼티를 가져오기 위해서 어느 정도 Getter는 허용이 된다. 그러나 Collection 인터페이스를 사용하는 경우, 외부에서 Getter 메소드를 얻은 값을 통해 상태값을 변경할 수 있다.   

```
// (x)
public List<Car> getCars() {
	return cars;
}

// (o)
public List<Car> getCars() {
	return Collections.unmodifiableList(cars);
}
```

이처럼 ```Collections.unmodifiableList()```와 같은 ```Unmodifiable Collection```을 사용해서 외부에서 변경하지 못하도록 설정하는 것이 좋다.   

[참고](https://tecoble.techcourse.co.kr/post/2020-04-28-ask-instead-of-getter/)