# 전략 패턴 (Strategy Pattern)
<b>전략</b>이란, 특정한 목표를 수행하기 위한 행동 계획   

예시. 로봇 전략   
```
이동 전략 Move Strategy

Walk 걸어서, Run 뛰어서, Fly 날아서, Rocket 로켓으로
```

```
온도 전략 Temp Strategy

Cold 차갑게, Warm 따뜻하게, Frozen 냉동으로, Hot 뜨겁게
```

## 디자인 패턴
(소프트웨어) 디자인 + (공통적으로 마주치는 문제를 해결하는 방법의) 패턴이다.

## 전략 패턴
<b>디자인 패턴</b> 중에 하나로, 객체가 할 수 있는 <b>행위</b>들 각각을 <b>전략</b>으로 만들어 놓고 사용하며 동적으로 <b>전략 수정</b>이 가능한 패턴이다.

## 전략 패턴의 의도 (by GoF의 디자인 패턴)
동일 계열의 알고리즘군을 정의하고, 각 알고리즘을 캡슐화한다. 그리고 이들을 <b>상호 교환</b>이 가능하도록 만든다.

```
알고리즘군 정의 - walk, run, fly, rocket
캡슐화 - Move Strategy
```

* 초기 배달 로봇 (전략 패턴 전)
```
public class Robot {
    public void display() {
        System.out.println("배달 로봇");
    }

    public void move() {
        System.out.println("걸어서 배달하기");
    }
}
```

* 초기 배달 로봇 분류 (전략 패턴 전)
```
public class WalkingRobot {
    public void display() {
        System.out.println("걷기 로봇");
    }

    public void move() {
        System.out.println("걸어서 배달하기");
    }
}

public class RunningRobot {
    public void display() {
        System.out.println("뛰기 로봇");
    }

    public void move() {
        System.out.println("뛰어서 배달하기");
    }
}
```

```
public class Main {
    public static void main(String[] args) {
        WalkingRobot robot1 = new WalkingRobot();
        RunningRobot robot2 = new RunningRobot();

        robot1.move();
        robot2.move();
    }
}
```

FlyingRobot, RocketRobot을 만든다면? 아래와 같이 상속을 이용한다.   

```
public abstract class Robot {
    public abstract void display();
    public abstract void move();
}
```

```
public class WalkingRobot extends Robot {
    @Override
    public void display() {
        System.out.println("걷기 로봇")
    }

    @Override
    public void move() {
        System.out.println("걸어서 배달")
    }
}

public class RunningRobot extends Robot {
    @Override
    public void display() {
        System.out.println("뛰기 로봇")
    }

    @Override
    public void move() {
        System.out.println("뛰어서 배달")
    }
}

public class FlyingRobot extends Robot {
    @Override
    public void display() {
        System.out.println("비행 로봇")
    }

    @Override
    public void move() {
        System.out.println("날아서 배달")
    }
}
```

```
public class Main {
    public static void main(String[] args) {
        Robot robot = new WalkingRobot();
        robot.move();
    }
}
```

만약에, 다른 기능을 추가하기 위해서는 어떻게 해야 할까? 간단하다. Robot 클래스에서 기능을 추가하며 된다.   

```
public abstract class Robot {
    public abstract void display();
    public abstract void move();
    public abstract void temperature();
}
```

```
public class WalkingRobot extends Robot {
    @Override
    public void display() {
        System.out.println("걷기 로봇")
    }

    @Override
    public void move() {
        System.out.println("걸어서 배달")
    }

    @Override
    public void temperature() {
        System.out.println("차가움")
    }
}

public class RunningRobot extends Robot {
    @Override
    public void display() {
        System.out.println("뛰기 로봇")
    }

    @Override
    public void move() {
        System.out.println("뛰어서 배달")
    }

    @Override
    public void temperature() {
        System.out.println("따뜻함")
    }
}

public class FlyingRobot extends Robot {
    @Override
    public void display() {
        System.out.println("비행 로봇")
    }

    @Override
    public void move() {
        System.out.println("날아서 배달")
    }

    @Override
    public void temperature() {
        System.out.println("뜨거움")
    }
}
```

```
public class Main {
    public static void main(String[] args) {
        Robot robot = new WalkingRobot();
        robot.move();
        robot.temperature();
    }
}
```

그러나, 위는 몇 가지 문제점을 가지고 있다.   
1. Method 수정이 어렵다.   
2. 새로운 기능의 추가가 어렵다.   

### 전략 패턴 사용
위의 사용에서 전략 패턴을 도입해보면 문제점을 해결할 수 있다.

```
public interface MoveStrategy {
    void move();
}

public class Walk implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("걸어서 배달")
    }
}

public class Run implements MoveStrategy {
    @Override
    public void move() {
        System.out.println("뛰어서 배달")
    }
}
```

```
public interface TemperatureStrategy {
    void temperature();
}

public class Cold implements TemperatureStrategy {
    @Override
    public void temperature() {
        System.out.println("차가움")
    }
}

public class Warm implements TemperatureStrategy {
    @Override
    public void temperature() {
        System.out.println("따뜻함")
    }
}
```

```
public class Robot {
    private MoveStrategy moveStrategy;
    private TemperatureStrategy temperatureStrategy;

    public Robot(MoveStrategy moveStrategy, TemperatureStrategy temperatureStrategy) {
        this.moveStrategy = moveStrategy;
        this.temperatureStrategy = temperatureStrategy;
    }

    public void move() {
        moveStrategy.move();
    }

    public void temperature() {
        temperatureStrategy.temperature();
    }
}
```

```
public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot(new Walk(), new Cold());

        robot.move();
        robot.temperature();
    }
}
```

전략 패턴을 사용한 후, 기능을 추가해보는 것은 아래처럼 기능을 추가하면 된다.

```
public interface SpeakStrategy {
    void speak();
}

public class Korean implements SpeakStrategy {
    @Override
    public void speak() {
        System.out.println("한국말")
    }
}

public class English implements SpeakStrategy {
    @Override
    public void speak() {
        System.out.println("영어")
    }
}
```

```
public class Robot {
    ...
    private SpeakStrategy speakStrategy;
}

public Robot(..., SpeakStrategy speakStrategy) {
    ...
    this.speakStrategy = speakStrategy;
}

...

public void speak() {
    speakStrategy.speak();
}
```

```
public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot(..., new Korean());

        ...

        robot.speak();
    }
}
```

### 상호 교환의 기능 추가
만약에, 실내에서는 걷는 로봇으로, 실외에서는 나는 로봇으로 기능을 만들려면 어떻게 해야 할까?

```
public abstract class Robot {
    private MoveStrategy moveStrategy;
    private TemperatureStrategy temperatureStrategy;

    public Robot(MoveStrategy moveStrategy, TemperatureStrategy temperatureStrategy) {
        this.moveStrategy = moveStrategy;
        this.temperatureStrategy = temperatureStrategy;
    }

    public void move() {
        moveStrategy.move();
    }

    public void temperature() {
        temperatureStrategy.temperature();
    }

    // Setter를 추가해줌으로써 객체 생성 후 전략을 변경한다.
    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public void setTemperatureStrategy(TemperatureStrategy temperatureStrategy) {
        this.temperatureStrategy = temperatureStrategy;
    }
}
```

```
public class Main {
    public static void main(String[] args) {
        Robot robot = new Robot(new Walk(), new Cold(), new Korean());

        // Walk, Cold
        robot.move();
        robot.temperature();

        // Fly, Cold
        robot.setMoveStrategy(new Fly());
        robot.move();
        robot.temperature();

        // Fly, Hot
        robot.setTemperatureStrategy(new Hot());
        robot.move();
        robot.temperature();
    }
}
```

## 전략 패턴 UML
![UML]('../Image/UML.PNG')

## 전략 패턴 in JDK

### 전략 패턴 in JDK - Comparator
```
public interface Comparator<T> {
    int compare(T o1, T o2);
    ...
}
```

```
public class PersonCompareByAge implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getAge() - person2.getAge();
    }
}
```

개발 상황에 맞게 비교 전략을 구현할 수 있게 하여 기존 코드의 수정 없이 확장할 수 있다.