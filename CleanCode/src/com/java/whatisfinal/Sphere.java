package com.java.whatisfinal;

public class Sphere {
	public static final double PI = 3.141592653589793; // final로 선언되어 수정불가
	// 위 변수에서 static이 붙어 있다. static => 메모리에 딱 한 번만 할당되어 메모리를 효율적으로 사용할 수 있다.
	// 즉, 같은 주소값을 공유한다. 여러 곳에서 변수 하나를 공유할 수 있다.
	// 왜 final과 static은 같이 자주 쓰일까?
	// final 변수는 초기화한 값을 그대로 사용하겠다는 의미 => 메모리 낭비할 필요 없이 계속 오래 사용하겠다.
	// 그렇기 때문에 static과 final을 같이 사용하면 효율성이 높아진다고 함
	
	// final 변수는 한 번 값을 할당하면 수정할 수 없다. 초기화는 한 번만 가능
	public final double radius;
	public final double xPos;
	public final double yPos;
	public final double zPos;
	
	public Sphere(double x, double y, double z, double r) {
		radius = r;
		xPos = x;
		yPos = y;
		zPos = z;
	}
	
}
