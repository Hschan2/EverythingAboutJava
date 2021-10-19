package com.example.singleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SingletonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SingletonApplication.class, args);
		
//		싱글톤 (Singleton) 패턴
//			인스턴스를 오직 한 개만 제공하는 클래스
//				* 시스템 런타임, 환경 세팅에 대한 정보 등 잉ㄴ스턴스가 여러 개일 때 문제가 생길 수 있는 경우 존재
//				그럴 경우, 인스턴스를 오직 한 개만 만들어 제공하는 클래스 필요

//		Singleton
//				- instance: Singleton
//				+ getInstance(): Singleton

//		생성한 Settings 클래스를 인스턴스로 가져올 수 있다.
		Settings settings = new Settings(); // Settings 클래스에서 private 선언하여 직접 가져올 수 없는 에러
//		개수와 상관없이 만들 수 있다. 그러나 이전에 생성한 인스턴스와 같지 않다.
		Settings settings1 = new Settings(); // Settings 클래스에서 private 선언하여 직접 가져올 수 없는 에러
		System.out.println(settings == settings1); // false
//		그러나 new를 사용하여 생성하는 것은 싱글톤과 맞지 않다.
//		Settings 클래스를 private로 설정하면 new로 가져올 수 없다
//		그러나 Settings 클래스에서 getInstance를 선언했을 시 가져오는 방법은 아래와 같다.
		Settings settings2 = Settings.getInstance();

//		만약에 Settings 클래스에서 instance 변수를 사용하여 조건문을 모두 넘어갔을 경우, 이미 생성된 객체가 있으므로 같은 객체이다
		System.out.println(settings2 == settings2.getInstance()); // true
	}

}
