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