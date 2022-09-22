package Web.opentutorials;

public class ExtendsCalClassChild extends ExtendsCalClass {
	
	public ExtendsCalClassChild(int a, int b) {
		super(a, b); // ExtendsCalClass로 보내라
		System.out.println("ExtendsCalClassChild");
	}
	
	public int minus() {
		return this.a-b;
	}
}
