package com.java.StrategyPattern;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 전략 패턴을 사용하기 전 사용 코드
		/*
		Robot taekwonV = new TaekwonV("TaekwonV");
		Robot atom = new Atom("Atom");
		
		System.out.println("나는 " + taekwonV.getName());
		taekwonV.move();
		taekwonV.attact();
		
		System.out.println("나는 " + atom.getName());
		atom.move();
		atom.attact();
		*/
		
		/*
		 기존 로봇의 공격과 이동 방법을 수정하고 싶다면? (Atom은 날 수 없고 지상으로 이동한다면? TaekwonV를 날게 한다면?)
		 기능을 변경하려고 기존 코드의 내용을 수정해야 하므로 OCP에 위배
		 TaekwonV와 Atom의 메서드의 내용이 중복이 된다.(둘 다 지상 이동) 이는 중복에 대한 문제
		 만약 걷는 방식에 문제가 있거나 새로운 방식으로 수정하려면 모든 중복 코드를 일관성있게 수정해야 한다.
		 만약 새로운 로봇을 생성하고 미사일 공격을 한다면? => TaekwonV의 공격과 중복 된다.
		 현재 캡슐화 자체는 Robot이므로 새로 로봇을 추가하는 것은 쉽다. 그러나 로봇의 기능을 추가하거나 변경하려면 문제가 발생한다
		*/
		
		// 해결 방법
		/*
		 변화된 것을 찾아 이를 클래스로 '캡슐화' 하여야 한다.
		 로봇 예제에서는 공격과 이동의 추가 및 수정이 문제를 발생시키는 요인이다.
		 이를 캡슐화하려면 욉에서 구체적인 이동 방식과 공격 방식을 담은 구체적인 클래스를 은닉해야 한다
		  - 공격과 이동 인터페이스를 각각 생성하고 이를 실제로 실현할 클래스를 만든다 
		  - 로봇 클래스가 공격과 이동 기능을 사용하는 역할을 수행
		  - 공격은 AttackStrategy 인터페이스, 이동은 MovingStrategy 인터페이스에 캡슐화 되어 있다
		  - 이 인터페이스들이 일종의 방화벽 수행을 하여 로봇 클래스의 변경을 차단
		 전략 패턴을 사용하면 새로운 기능을 추가가 기존 코드에 영향을 미치지 못함으로 OCP를 만족
		  - 새로운 구조에서는 외부에서 로봇의 이동 및 공격 방식을 임의대로 바꾸도록 하는 Setter 메서드가 필요
		  	(setMovingStrategy, setAttackStrategy)
		  - 이렇게 변경이 가능한 이유는 상속 대신 '집약 관계'를 이용했기 때문
		*/
		
		// 로봇 생성
		Robot taekwonV = new TaekwonV("TaekwonV");
		Robot atom = new Atom("Atom");
		
		// 전략 변경 방법
		taekwonV.setMovingStrategy(new WalkingStrategy());
		taekwonV.setAttackStrategy(new MissileStrategy());
		atom.setMovingStrategy(new FlyingStrategy());
		atom.setAttackStrategy(new PunchStrategy());
		
		// 출력
		System.out.println("나는 " + taekwonV.getName());
		taekwonV.move();
		taekwonV.attact();
		
		System.out.println();
		
		System.out.println("나는 " + atom.getName());
		atom.move();
		atom.attact();
	}

}
