# 캡슐화
- 캡슐화란    
캡슐화는 일반적으로 변수와 클래스를 하나로 묶는 작업, 캡슐화는 중요한 데이터를 보호하고 보존하기 위해 사용한다.

```
package Encapsulation;
public class encapsulationClass {
	private String name;
	private int id;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

```

위 코드를 보면 private로 변수를 선언한다. 이는 자기 클래스에서만 사용할 수 있다는 것을 의미한다. 즉, 외부에서 접근하지 못하게 막아 보호해준다.

```
package Encapsulation;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		encapsulationClass object = new encapsulationClass();
		
		object.setName("홍성찬");
		object.setId(20150903);
		object.setAge(27);
		
		System.out.println("이름 : " + object.getName());
		System.out.println("아이디 : " + object.getId());
		System.out.println("나이 : " + object.getAge());
	}

}

```

위 코드는 캡슐화한 클래스를 호출해서 사용할 때 모습이다. 캡슐화가 되지 않은 public 상태라면 object.name 등으로 호출할 수 있었겠지만
지금 상황에 만약 이렇게 할 경우 오류가 난다. 이유는 private로 선언했기 때문이다. 즉, 캡슐화 (은닉화)를 하였기 때문에 외부에서 직접 호출을 할 수 없다.   
호출하기 위해서는 간접적으로 가져와야 한다. encapsulationClass에서 setName... getName...을 이용해서 호출한다.   

<br/>

자바 기본 강의의 데이터 은닉이라는 강좌에서 배울 수 있는 부분이었다. 그러나 이를 사용해야 하는 이유 그리고 목적을 제대로 알지 못했다. 그래서 제대로 이해하고 넘어가기 위해 복습을 하기 위해 찾아보았다.   

캡슐화(데이터 은닉)은 클래스에 담아 있는 것 중 중요한 데이터나 기능을 외부에서 접근하지 못하게 막아 보안과 보존을 위해 사용하는 것을 배우게 됐다.   

<br/>

## 조금 더 다양하게 캡슐화를 해보기로 하자.
정보은닉은 변경에 유연하게 대처하기 위해 사용한다고 한다.   

먼저 상속을 이용해보자

```
package DeepenEncapsulation;

public class CarClass {
	int speed;
	
	CarClass(int speed) {
		this.speed = speed; // CarClass의 speed에 매개변수로 받은 값을 넣는다.
	}
	
	public void driving() {
		System.out.println("현재 " + speed + "km로 달리고 있습니다.");
	}
}

```

위 코드는 자동차의 속력의 값을 받고 출력하는 상위 클래스이다.

```
package DeepenEncapsulation;

public class SportsCarClass extends CarClass {
	SportsCarClass() {
		super(200); // 부모클래스(CarClass)에 int speed 값을 보낸다.
	}
}

```

그리고 상위 클래스를 상속받을 하위 클래스이다. CarClass를 상속받아서 speed를 200의 값으로 하여 super로 상위 클래스에 보낸다.

```
package DeepenEncapsulation;

public class SedanClass extends CarClass {
	SedanClass() {
		super(100); // 부모클래스(CarClass)에 int speed 값을 보낸다.
	}
}
```

또 다른 하위 클래스를 만들었다.

```
package DeepenEncapsulation;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 정보은닉을 위한 방법
		
		// 상속을 사용
		CarClass SportsCar = new SportsCarClass();
		CarClass Sedan = new SedanClass();
		
		SportsCar.driving();
		Sedan.driving();
	}

}
```

그리고 메인 클래스에서 이를 호출한다. 이는 변경에 유연하게 대처하기 위한 방법 중 하나이다. 그러면 상위 클래스는 풍성할 수록 좋은가? 답은 맞다라고 한다. 특히 재사용할 수 있는 것이 많을수록 좋다고 한다. 왜냐하면 상위가 풍성해지면 하위가 반복해야 할 일이 줄어들어 '중복'을 제거할 수 있기 때문이다.   

이번에는 인터페이스를 이용해보자

```
package DeepenEncapsulation;

public interface FlyingInterface {
	public void fly();
}
```

먼저 인터페이스에서 fly를 선언한다. (인터페이스에서는 선언만 가능하다)

```
package DeepenEncapsulation;

public class BirdClass implements FlyingInterface {
	
	@Override
	public void fly() {
		System.out.println("새는 날고 있다.");
	}
}
```

그리고 인터페이스를 상속받은 BirdClass 클래스에서 fly를 재정의한다. (@Override => 재정의)   

변경에 유연하게 대처하기 위한 방법 중 상속을 받는 방법 외에 인터페이스를 사용하는 방법이다.   

<br/>

그렇다면 인터페이스는 풍성할 수록 좋은가? 답은 아니다. 인터페이스는 빈약할 수록 좋고 여러개 만드는 방법이 좋다.   

마지막으로는 이전에 학습했던 set, get을 이용한 방법이다.

```
package DeepenEncapsulation;

public class PersonClass {
	private String name; // private는 현재 클래스에서만 사용 가능. 외부 호출 불가
	private int age;
	
	PersonClass(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	// get, set으로 값을 설정하고 호출할 수 있음
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
```

private로 변수를 선언. 즉, 현재 클래스에서만 사용이 가능하고 외부에서 호출이 불가능하다.   

호출 하기 위해서는 메인 클래스에서 아래 방법으로 사용할 수 있다.

```
// 메소드를 이용해 노출(Setter, Getter)
PersonClass Person = new PersonClass("HONG", 27);
System.out.println(Person.getName() + "은 " + Person.getAge() + "살이다.");
```

PersonClass Person = new PersonClass("HONG", 27); 에서 값을 set 해주고 get해서 값을 호출할 수 있다.   

오늘 캡슐화를 할 수 있는 다양한 방법을 알 수 있었다. 이전에는 private로 선언하여 set, get하는 방법만 알고 있었지만 상속과 인터페이스를 이용한 방법을 알 수 있었다.
