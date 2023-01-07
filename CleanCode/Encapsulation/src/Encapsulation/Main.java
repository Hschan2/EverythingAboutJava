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
