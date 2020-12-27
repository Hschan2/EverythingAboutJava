### Comparable 이란 무엇일까?
Comparable이란 기본적인 정렬을 수행한다. 숫자라면 오름차순으로 문자라면 알파벳 순으로 정렬한다.
- Primitive 타입, Spring, integer, List 등 정렬에 필요한 데이터는 모두 Comparable 인터페이스를 implements 한다

##### 1. Arrays.sort() 기본 타입 정렬
기본 byte[], char[], double[], float[], int[], long[], short[]을 정렬하는 방식이다.

```
String[] s = {"b","A","D","C","a","F"};

Arrays.sort(s);
System.out.println(Arrays.toString(s));
```
위 처럼 배열로 선언한 후 Arrays.sort()를 할 경우 [A, C, D, F, a, b]로 출력하는 것을 알 수 있다. 이 방식은 대소문자 구분을 한 후 정렬한다.

```
Arrays.sort(s, String.CASE_INSENSITIVE_ORDER);
System.out.println(Arrays.toString(s));
```
만약 위 처럼 String.CASE_INSENSITIVE_ORDER를 사용했을 경우 대소문자를 구분 없이 정렬할 수 있다.<br>

정렬한 배열을 반대로 출력하기 위해서는 어떻게 해야 할까?
```
for(int i = 0; i<s.length/2; i++) {
		String tmp = s[i];
		s[i] = s[(s.length - 1) - i]; // 맨 마지막부터 순서대로 대입
		s[(s.length - 1) - i] = tmp;
}
System.out.println(Arrays.toString(s));
```
위 처럼 for문을 이용해서 위치를 바꿀 수 있다.

##### 2. Arrays.sort() 객체 타입 정렬
객체 타입을 정렬하기 위해서는 이렇게 하면 될까?
```
Actor[] arr = new Actor[] {
			new Actor("박보검", 1993),
			new Actor("유승호", 1993),
			new Actor("차은우", 1996),
			new Actor("서강준", 1994)
	};
	
	public void ArraysMethod() { // 이는 오류가 난다. 객체를 Comparable 적용할 수 없다는 이유로
		Arrays.sort(arr);
		for(Actor result : arr) {
			System.out.println(result);
		}
	}
```
결과로 보면 에러가 난다. 이유가 뭘까? 정렬이 필요한 데이터는 정렬할 수 있다고 했는데 말이다.<br>

객체 타입을 정렬하기 위해서는 Comparable 인터페이스의 compareTo() 메서드를 "재정의" 해주어야 한다. 
- 일반적인 Integer, Double, Wrapper class도 여기에 해당한다.

```
public class Actor implements Comparable<Object>{
	String name;
	int age;

	public Actor(String name, int age) {
		this.name = name;
		this.age = age;
	}
	/*
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
	*/
	public String toString() {
		return name + " " + age + "년생";
	}
	
	// Comparable 인터페이스의 comparaTo를 오버라이딩
	public int compareTo(Object o) {
		return name.compareTo(((Actor) o).name); // name으로 오름차순 정렬
	}
}
```

##### 3. Collections.sort():List 타입 정렬
Arrays.sort()와 마찬가지로 ArrayList, Vector, LinkedList에 들어있는 데이터를 오름차순으로 정렬한다.
```
List<String> list = new ArrayList<>();
		
		list.add("z");
		list.add("a");
		list.add("A");
		list.add("c");
    
    System.out.println(list);
```
위 처럼 했을 경우, add한 순서대로 ([z,a,A,c]) 출력된다. 이를 정렬하기 위해서는 Collections.sort()를 사용한다.

```
Collections.sort(list);
System.out.println(list);
```
위의 결과는 [A,a,c,z]로 출력된다. 이는 대소문자를 구분한 정렬이다. 그렇다면 대소문자 구분 없이 정렬하려면 어떻게 해야 할까?
```
Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
System.out.println(list);
```
String.CASE_INSENSITIVE_ORDER를 사용하면 대소문자 구분 없이 정렬할 수 있다.

추가로 역순으로 정렬하는 방법도 있다.
```
Collections.sort(list, Collections.reverseOrder());
```
를 사용하면 List의 데이터를 역순으로 정렬할 수 있다.

##### 4. Comparator : 다른 기준으로 정렬하기
만약 오름차순이 아닌 다른 방식으로 정렬하기 위해서는 어떻게 해야 할까? 예를 들어 Actor 객체에서 나이가 어린 순서대로(내림차순) 정렬하기 위한 방법처럼

그 기준을 정하는 것이 바로 'Comparator'이다. 정렬하기 전 기준을 세우는 것은 딱 한 번만 일어나는 일회성 활동이기 때문에 익명 클래스 형태로 자주 사용한다.

```
public class Comparator {
	Actor[] arr = new Actor[] {
			new Actor("박보검", 1993),
			new Actor("김수현", 1988),
			new Actor("차은우", 1996),
			new Actor("서강준", 1994),
	};
	
	public void sort() {
		Arrays.sort(arr, new Comparator<Actor>() {
			@Override
			public int compare(Actor o1, Actor o2) {
				int by1 = ((Actor) o1).age;
				int by2 = ((Actor) o2).age;
				return by1 > by2 ? -1 : (by1 == by2 ? 0 : 1);
			}
		});
		for(Actor result : arr) {
			System.out.println(result);
		}
	}
}
```
위는 Actor 객체의 데이터 중 나이를 내림차순으로 정렬하기 위함이다. <br>
public int compare 객체는 return 값이 양수면 두 객체의 자리를 바꿔주는 역할을 한다.<br>
by1 > by2일 경우 음수를, 같으면 0을 크면 1을 리턴 => 리턴 값이 양수면 두 객체의 자리를 바꿔주는 역할. 즉, 년도가 클수록 맨 처음으로<br>

###### 출력 값으로<br>
차은우 1996<br>
서강준 1994<br>
박보검 1993<br>
김수현 1988<br>
의 결과가 나오게 된다.

##### 정리
|인터페이스|목적|메서드|사용법|
|----------|----------------|---------------------------|-------------------------------------------------|
|Comparable|데이터를 비교할 때|Arrays.sort()|Primitive 타입|
|Comparable|데이터를 비교할 때|Arrays.sort(), compareTo()|Object, Wrapper Class 타입, compareTo() 오버라이딩|
|Comparable|데이터를 비교할 때|Collections.sort()|Arrays.sort(), LinkedList, Vector 타입|
|Comparator|비교 기준을 세울 때|compare()|compare() 오버라이딩|


#### <span style="color:red">주의!</span>
클래스를 만들 때 Comparable이라고 만들지 말자. 에러가 난다. 만들 때 ComparableClass처럼 다르게 만들자!
