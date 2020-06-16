
import java.util.Arrays;

public class Comparator {
	Actor[] arr = new Actor[] {
			new Actor("박보검", 1993),
			new Actor("김수현", 1990),
			new Actor("차은우", 1996),
			new Actor("서강준", 1994),
	};
	
	public void sort() {
		Arrays.sort(arr, new Comparator<Actor>() {
			@Override
			public int compare(Actor o1, Actor o2) {
				int by1 = ((Actor) o1).age;
				int by2 = ((Actor) o2).age;
				return by1 > by2 ? -1 : (by1 == by2 ? 0 : 1); // by1 > by2일 경우 음수를, 같으면 0을 크면 1을 리턴 => 리턴 값이 양수면 두 객체의 자리를 바꿔주는 역할. 즉, 년도가 클수록 맨 처음으로
			}
		});
		for(Actor result : arr) {
			System.out.println(result);
		}
	}
}
