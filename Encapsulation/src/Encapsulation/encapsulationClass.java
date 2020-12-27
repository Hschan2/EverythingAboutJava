package Encapsulation;
// 캡슬화 = 은닉화 (은닉성)
public class encapsulationClass {
	private String name; // private는 자기 클래스에서만 접근할 수 있는 것, 즉 은닉화(캡슐화)
	private int id;
	private int age;
	
	// 멤버 변수를 꺼내올 수 있는 get, set을 사용해 메소드 생성
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
