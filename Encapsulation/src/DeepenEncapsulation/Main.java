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
		
		// 인터페이스를 사용
		BirdClass Bird = new BirdClass();
		
		Bird.fly();
		
		// 메소드를 이용해 노출(Setter, Getter)
		PersonClass Person = new PersonClass("HONG", 27);
		System.out.println(Person.getName() + "은 " + Person.getAge() + "살이다.");
	}

}
