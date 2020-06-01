### 캡슐화
- 캡슐화란<br>
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
  
  위 코드를 보면 private로 변수를 선언한다. 이는 자기 클래스에서만 사용할 수 있다는 것을 의미한다.<br> 즉, 외부에서 접근하지 못하게 막아 보호해준다.
  
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
지금 상황에 만약 이렇게 할 경우 오류가 난다. 이유는 private로 선언했기 때문이다. 즉, 캡슐화 (은닉화)를 하였기 때문에 외부에서 직접 호출을 할 수 없다.<br>
호출하기 위해서는 간접적으로 가져와야 한다. encapsulationClass에서 setName... getName...을 이용해서 호출한다.<br><br>

자바 기본 강의의 데이터 은닉이라는 강좌에서 배울 수 있는 부분이었다. 그러나 이를 사용해야 하는 이유 그리고 목적을 제대로 알지 못했다. 그래서 제대로 이해하고 넘어가기 위해 복습을 하기 위해 찾아보았다.<br>
캡슐화(데이터 은닉)은 클래스에 담아 있는 것 중 중요한 데이터나 기능을 외부에서 접근하지 못하게 막아 보안과 보존을 위해 사용하는 것을 배우게 됐다. 
