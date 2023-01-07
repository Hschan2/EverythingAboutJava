package Web.opentutorials;

public class ExtendsCalClass {
	int a, b;
	
	public ExtendsCalClass(int a, int b) {
		System.out.println("ExtendsCalClass");
		this.a = a;
		this.b = b;
	}
	
	public int sum() {
		return a+b;
	}
}