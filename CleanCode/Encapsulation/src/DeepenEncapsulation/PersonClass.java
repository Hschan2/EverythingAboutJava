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
