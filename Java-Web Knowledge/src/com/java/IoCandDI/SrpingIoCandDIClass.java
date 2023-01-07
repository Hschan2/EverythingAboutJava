package com.java.IoCandDI;

import java.util.List;

public class SrpingIoCandDIClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		스프링의 대 삼각형
//		스프링 밑에는 3 가지가 있다 - IoC/DI, AOP, PSA
//		
//		Spring IoC/DI
//		스프링 프레임워크의 근간
//		오브젝트의 생명 주기와 의존 관게에 대한 프로그래밍 모델
//		유연하고 확장성이 뛰어난 코드를 만들 수 있도록 도와주는 프로그래밍 모델
//		
//		유연하고 확장성이 뛰어나다는 것은?
//		변경이 있을 때 수정이 쉽고 수정할 부분만 수정하면 된다. 그리고 관심사의 분리가 잘 이루어져 있다.
//		
//		전략 패턴 (스스로에게 제어권이 없다)
//		예시
//		public class Car {
//			private MovingStrategy movingStrategy;
//			private int distance;
//			
//			public Car(MovingStrategy movingStrategy) {
//				this.movingStrategy = movingStrategy;
//				this.distance = 0;
//			}
//			
//			void move() {
//				if(movingStrategy.isMovable()) {
//					distance++;
//				}
//			}
//		}
//		여기서 움직임을 수정하고 싶으면 움직임에 관한 인터페이스만 수정하면 된다.
//		다만 객체 본인이 직접 생성해서 실행하는 것이 아니라 인터페이스를 가져와 사용하는 것이기 때문에 위 객체는 본인이 제어할 수 있는 것을 본인도 모른다는 것이다.
//		그렇다면 제어권이 어딨을까 생각하면 이 객체를 사용하는 쪽에 있을 것이다. => 클라이언트 객체
//		
//		클라이언트 객체
//		예시
//		public class Racing {
//			private static final int NUM_OF_CARS = 10;
//			private List<Car> cars = new ArrayList<>();
//			
//			public Racing() {
//				MovingStrategy strategy = new RandomMovingStrategy(); // 선택한 전략
//				
//				for(int i = 0; i < NUM_OF_CARS; i++) {
//					cars.add(new Car(strategy)); // Car 생성
//				}
//			}
//			
//			public void move() {
//				cars.forEach(Car::move);
//			}
//		}
//		위 코드에서는 클라이언트 객체에서 어떤 Strategy를 사용할 지 직접 정해서 생성해 사용하고 있다.
//		그러나 관심사 분리가 적절하게 이루어있지 않다. 위는 차를 움직이는 객체일 뿐 차를 생성하는 객체가 아니다.
//		
//		제어에는 2가지 관점이 있다
//		Car의 생성에 대한 제어, Car의 움직임을 제어하는 관점
//		위의 클라이언트 객체는 두 번째 관점에는 적합하나 첫 번째와는 거리가 멀다
//		그렇다면 생성에 대한 객체를 만들어 보자
//		
//		생성 객체
//		예시
//		public class CarFactory {
//			public static Car car() {
//				return new Car(movingStrategy());
//			}
//			
//			private static MovingStrategy movingStrategy() {
//				return new RandomMovingStrategy();
//			}
//		}
//		이렇게 생성 객체를 만들고 나면 클라이언트 객체를 다르세 사용할 수 있다.
//		
//		public class Racing {
//			private static final int NUM_OF_CARS = 10;
//			private List<Car> cars = new ArrayList<>();
//			
//			public Racing() {
//				MovingStrategy strategy = new RandomMovingStrategy();
//				
//				for(int i = 0; i < NUM_OF_CARS; i++) {
//					cars.add(CarFactory.car()); // 생성 객체를 만들고 나서
//				}
//			}
//			
//			public void move() {
//				cars.forEach(Car::move);
//			}
//		}
//		위 처럼 생성에 대한 책임을 넘길 수 있다.
//		
//		즉 생성에 대한 제어권은 CarFactory가 행동에 대한 제어권은 Racing이 가지고 있다.
//		만약에 Car의 행동을 바꾸고 싶다면 CarFactory의 movingStrategy()에서 RandomMovingStrategy()부분을 바꾸면 된다.
//		만약 Racing의 방식을 바꾸고 싶다면 move()의 행동에 관한 부분을 바꿔주면 된다.
//		Spring의 IoC가 작동하는 과정이다.
//		
//		제어의 두 가지 관점
//		어떤 연관 관계를 맺는가? 팩토리 -> 빈 컨테이너 (관심사를 맡고 있다)
//		어떻게 사용될 것인가? 클라이언트 -> 스프링 내부 코드
				
	}

}
