## 전략 패턴이란?
- 행위(기능)을 클래스로 캡슐화하여 동적으로 행위를 자유롭게 수정할 수 있게 만드는 패턴
- 같은 문제를 해결하는 다양한 알고리즘이 클래스별로 캡슐화되어 있으며 필요할 때 교체할 수 있도록 만들어 동일한 문제를 다른 알고리즘으로 해결할 수 있게 만들어 주는 디자인 패턴
- 즉, 전략(행위, 기능)을 쉽게 바꿀 수 있게 만들어주는 디자인 패턴이다. (전략 => 목적을 달성하기 위해 일을 수행하는 방식, 규칙, 문제 해결 알고리즘)
- 게임 프로그래밍에서 게임 캐릭터가 자신의 공격 기능 혹은 행동을 바꾸고 싶을 때 유용

### * 참고 *
<b>행위 패턴</b>
- 객체 혹은 클래스 사이의 알고리즘이나 책임 분배에 관련된 패턴
- 하나의 객체가 혼자 수행할 수 없는 작업을 여러 개의 객체로 어떻게 분리하면서 객체 사이 결합도를 최소화하는가에 중점

<b>집약 관계</b>
- 참조값을 인자로 받아 필드를 세팅하는 경우
- 전체 객체의 라이프 타임과 부분 객체의 라이프 타임은 독립적
- 즉, 전체 객체가 메모리에서 사라져도 부분 객체는 유지

### 프로젝트 예시로 확인
기능을 담은 로봇.class
```
public abstract class Robot {
  private String name;
  public Robot(String name) { this.name = name; }
  public String getName() { return name; }
  // 추상 메서드
  public abstract void attack();
  public abstract void move();
}

public class TaekwonV extends Robot {
  public TaekwonV(String name) { super(name); }
  public void attack() { System.out.println("I have Missile."); }
  public void move() { System.out.println("I can only walk."); }
}
public class Atom extends Robot {
  public Atom(String name) { super(name); }
  public void attack() { System.out.println("I have strong punch."); }
  public void move() { System.out.println("I can fly."); }
}
```
실제로 실행될 클라이언트.class
```
public class Client {
  public static void main(String[] args) {
    Robot taekwonV = new TaekwonV("TaekwonV");
    Robot atom = new Atom("Atom");

    System.out.println("My name is " + taekwonV.getName());
    taekwonV.move();
    taekwonV.attack();

    System.out.println()
    System.out.println("My name is " + atom.getName());
    atom.move();
    atom.attack();
  }
}
```
##### 이에 대한 문제점은?
- 기존 로봇의 공격과 이동 방법을 수정하고 싶다면? (Atom은 날 수 없고 지상으로 이동한다면? TaekwonV를 날게 한다면?)
- 기능을 변경하려고 기존 코드의 내용을 수정해야 하므로 OCP에 위배
- TaekwonV와 Atom의 메서드의 내용이 중복이 된다.(둘 다 지상 이동) 이는 중복에 대한 문제
- 만약 걷는 방식에 문제가 있거나 새로운 방식으로 수정하려면 모든 중복 코드를 일관성있게 수정해야 한다.
- 만약 새로운 로봇을 생성하고 미사일 공격을 한다면? => TaekwonV의 공격과 중복 된다.<br>
		 - 현재 캡슐화 자체는 Robot이므로 새로 로봇을 추가하는 것은 쉽다. 그러나 로봇의 기능을 추가하거나 변경하려면 문제가 발생한다
     
##### 그렇다면 문제를 해결할 수 있는 방법은?
- 변화된 것을 찾아 이를 클래스로 '캡슐화' 하여야 한다.
- 로봇 예제에서는 공격과 이동의 추가 및 수정이 문제를 발생시키는 요인이다.
- 이를 캡슐화하려면 욉에서 구체적인 이동 방식과 공격 방식을 담은 구체적인 클래스를 은닉해야 한다.<br>
  -공격과 이동 인터페이스를 각각 생성하고 이를 실제로 실현할 클래스를 만든다<br>
	-로봇 클래스가 공격과 이동 기능을 사용하는 역할을 수행<br>
	-공격은 AttackStrategy 인터페이스, 이동은 MovingStrategy 인터페이스에 캡슐화 되어 있다<br>
	-이 인터페이스들이 일종의 방화벽 수행을 하여 로봇 클래스의 변경을 차단<br>
- 전략 패턴을 사용하면 새로운 기능을 추가가 기존 코드에 영향을 미치지 못함으로 OCP를 만족<br>
  -새로운 구조에서는 외부에서 로봇의 이동 및 공격 방식을 임의대로 바꾸도록 하는 Setter 메서드가 필요(setMovingStrategy, setAttackStrategy)<br>
  -이렇게 변경이 가능한 이유는 상속 대신 '집약 관계'를 이용했기 때문
  
### 문제를 해결한 프로젝트 예시를 보자
```
  // 인터페이스
interface AttackStrategy { public void attack(); }
// 구체적인 클래스
public class MissileStrategy implements AttackStrategy {
  public void attack() { System.out.println("I have Missile."); }
}
public class PunchStrategy implements AttackStrategy {
  public void attack() { System.out.println("I have strong punch."); }
}
```
먼저 공격 기능의 인터페이스를 생성하고 난 후 이를 사용할 미사일과 펀치 클래스를 만든다.

```
// 인터페이스
interface MovingStrategy { public void move(); }
// 구체적인 클래스
public class FlyingStrategy implements MovingStrategy {
  public void move() { System.out.println("I can fly."); }
}
public class WalkingStrategy implements MovingStrategy {
  public void move() { System.out.println("I can only walk."); }
}
```
똑같이 이동 기능의 인터페이스와 이를 사용할 클래스를 만든다.

```
public abstract class Robot {
private String name;
private AttackStrategy attackStrategy;
private MovingStrategy movingStrategy;

public Robot(String name) { this.name = name; }
public String getName() { return name; }
public void attack() { attackStrategy.attack(); }
public void move() { movingStrategy.move(); }

// 집약 관계, 전체 객체가 메모리에서 사라진다 해도 부분 객체는 사라지지 않는다.
// setter 메서드
public void setAttackStrategy(AttackStrategy attackStrategy) {
  this.attackStrategy = attackStrategy; }
public void setMovingStrategy(MovingStrategy movingStrategy) {
  this.movingStrategy = movingStrategy; }
}
```
그리고 Robot 클래스를 수정한다. 이전에 생성한 공격 인터페이스와 이동 인터페이스를 선언하고 공격 기능과 이동 기능을 임의대로 바꿀 수 있도록 Setter 메서드를 생성한다.

```
public class TaekwonV extends Robot {
public TaekwonV(String name) { super(name); }
}
public class Atom extends Robot {
public Atom(String name) { super(name); }
}
```
각 로봇 클래스 안에 있었던 공격 메서드와 이동 메서드는 각 로봇 클래스에서 이제는 필요 없으므로 제거한다. (인터페이스를 이용해 임의대로 수정할 수 있도록 만들었으므로)

```
public static void main(String[] args) {
  Robot taekwonV = new TaekwonV("TaekwonV");
  Robot atom = new Atom("Atom");

  /* 수정된 부분: 전략 변경 방법 */
  taekwonV.setMovingStrategy(new WalkingStrategy());
  taekwonV.setAttackStrategy(new MissileStrategy());
  atom.setMovingStrategy(new FlyingStrategy());
  atom.setAttackStrategy(new PunchStrategy());

  /* 아래부터는 동일 */
  System.out.println("My name is " + taekwonV.getName());
  taekwonV.move();
  taekwonV.attack();

  System.out.println()
  System.out.println("My name is " + atom.getName());
  atom.move();
  atom.attack();
}
```
그리고 이를 메인 클래스에서 사용할 때는 인터페이스와 이를 구체적으로 사용하는 클래스를 이용해 구현한다.
