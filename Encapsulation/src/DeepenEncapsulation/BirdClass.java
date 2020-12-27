package DeepenEncapsulation;

public class BirdClass implements FlyingInterface {
	
	@Override
	public void fly() {
		System.out.println("새는 날고 있다.");
	}
}
