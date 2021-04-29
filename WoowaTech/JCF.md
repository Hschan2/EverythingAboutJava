# JCF (Java Collections Framework)

## JCF 정의
다수의 데이터를 쉽고 효과적으로 처리할 수 있는 표준화된 방법을 제공하는 클래스의 집합. 즉, 데이터를 저장하는 자료 구조와 데이터를 처리하는 알고리즘을 구조화하여 클래스를 구현해 놓은 것이다.   

### Framework vs Library
JCF를 이해하기 위해 프레임워크와 라이브러리를 비교하여 이해한다.   
* 프레임워크는 라이브러리를 포함하고 개발자의 코드를 호출한다.   
* 라이브러리는 프레임워크에 포함되고 개발자 코드에 의해 호출된다.   
* 개발자의 코드는 프레임워크에 의해 호출되고 라이브러리를 호출한다.   

### JCF 도입 배경
JCF가 도입되기 전에는 Array, Vector, HashTable을 사용하여 데이터를 그룹핑하였다. Collection의 사용 목적이 같더라도 각각의 Collection에서 사용하는 문법이 다르다는 문제가 있다. 따라서, 공통의 인터페이스가 필요하였고 이것이 JCF이다.   
```
// 인스턴스 생성
// Array 인스턴스 생성: []
// vector, hashtable 인스턴스 생성: ()

int[] arr = new int[]{1, 2, 3, 4};
Vector<Integer> v = new Vector<>();
Hashtable<Integer, String> h = new Hashtable<>();

// vector 요소 추가: addElement()
v.addElement(1);
v.addElement(2);

// Hashtable 요소 추가: put()
h.put(1, "j");
h.put(2, "on");

// 요소 접근
// Array: [].
// vector: elementAt()
//hashtable: get()
System.out.println(arr[0]);
System.out.println(v.elementAt(0));
System.out.println(h.get(1));
```

### List
순서가 있는 데이터의 집합으로, 데이터의 중복을 허용한다. List 인터페이스의 구현 클래스는 ArrayList, LinkedList, Vector, Stack이 있다.   

#### List - ArrayList
<b>ArrayList</b>는 크기가 가변적인 선형 리스트로 <b>저장 용량</b>이라는 것이 존재하여 이 용량을 넘어서면 자동으로 용량을 증감하으로써 추가적으로 요소를 넣을 수 있도록 한다. (초기 용량: 10)   

```
요소 삽입

add(Object o) => O(1)
add(int idx, Object o) => O(N)
```

```
요소 삭제

remove(int idx) => O(N)
remove(Object o) => O(N)
```

#### List - LinkedList
<b>LinkedList</b>는 각 노드가 데이터와 포인터를 가지고 한 줄로 연결되어 있는 자료 구조이다. ㄷ이터를 담고 있는 노드들이 연결되어 있고, 노드의 포인터가 이전 노드와 다음 노드와의 연결을 담당한다.   

```
요소 삽입

add(Object o) => O(1)
add(int idx, Object o) => O(N)
```

```
요소 삭제

remove(int idx) => O(N)
remove(Object o) => O(N)
```

#### List - Vector
<b>Vector</b>는 JDK 1.0부터 있었던 자료 구조이며 호환성을 위해 남겨 놓은 레거시 클래스이다. Vector는 ArrayList와 기능상 거의 동일하다. 그러나 ArrayList는 비동기 방식이고, Vector는 동기 방식이라는 차이점이 있다.   

ArrayList는 멀티 스레드 환경에서 사용하면 안 되고 Vector를 쓰라고 하는데 정말로 Vector는 스레드 안전하고 멀티 스레드 환경에서는 ArrayList를 사용하지 못할까?   

##### List - 비동기와 동기, 그리고 스레드 안전
<b>동기 방식</b>은 요청을 보낸 후, 응답을 받아야 다음 과정이 동작하는 방식이다.   

<b>비동기 방식</b>은 요청을 보낸 후, 응답과 상관 없이 다음 과정이 동작하는 방식이다.   

<b>스레드 안전</b>은 멀티 스레드 환경에서 어떤 함수나 변수, 혹은 객체가 여러 스레드로부터 동시에 접근이 이루어져도 프로그램의 실행에 문제가 없음을 의미한다.   

비동기 방식은 스레드 안전하지 않고, 동기 방식은 스레드 안전을 위해 사용한다.   

#### List - ArrayList vs Vector
ArrayList는 동기화되어 있지 않다.
```
public boolean add(E e) {
  modCount++;
  add(e, elementData, size);
  return true;
}
```

Vector는 synchronized를 선언함으로써 동기화 처리를 하였다.
```
public synchronized void addElement(E obj) {
  modCount++;
  add(obj, elementData, elementCount);
}
```

Vector는 ArrayList와 달리 대부분의 메소드에 동기화 처리가 되어있지만, 스레드 안전은 하지 않는다.

```
if (vector.size() > 0) System.out.println(vector.get(0))
```

Vector는 ArrayList보다 성능이 떨어지고 스레드 안전하지 못하므로 사용하지 않는 것이 좋다. 그리고 멀티 스레드 환경에서 ArrayList를 사용할 때, Collections.synchronizedList()를 사용한다.   

### List - Stack
Stack은 Vector를 상속하여 사용하는 <b>LIFO</b> 방식의 클래스이다. 그러나 Stack 대신에 <b>Deque</b>를 사용하여 구현할 것을 권장한다.

```
Deque<Integer> stack = new ArrayDeque<>();
```

### Queue
Queue는 <b>FIFO</b> 구조를 가진 자료 구조로써, 주로 <b>LinkedList</b>를 이용하여 구현한다.

#### Queue - Priority Queue
<b>Priority Queue</b>는 Queue 인터페이스의 상속을 받으면서 FIFO가 아닌, 특정 <b>우선 순위</b>에 따라 요소가 먼저 나가는 방식이다.   

우선 순위는 Comparator를 통해 정의해 주거나, Comparable 인터페이스를 상속한 객체를 이용해야 한다. (null 요소는 허용하지 않는다.)   

### Deque
<b>Deque</b>는 Double-Ended Queue의 약어로써 Queue의 양쪽 끝에서 추가와 삭제가 일어날 수 있는 자료 구조이다. ```사용 방식에 따라 Stack이 될 수도 있고 Queue가 될 수도 있다.```

#### Deque - 구현 클래스 ArrayDeque
ArrayDeque는 대표적인 Deque의 구현 클래스로 5가지의 특징을 가지고 있다.   
1. 사이즈 제한이 없는 가변 배열   
2. Null 요소는 저장할 수 없다.   
3. 비동기 방식   
4. 원형 큐 방식으로 구현   
5. Stack 목적으로 구현하였을 때, 기존의 Stack보다 빠르고, Queue 목적으로 구현하였을 때는 LinkedList보다 빠르다.   

### Set
<b>Set</b> 자료 구조는 중복된 요소를 저장하지 않고, 요소의 저장 순서를 유지하지 않는 특징이 있다.

#### Set - equals(), hashCode()
중복된 요소를 걸러 내기 위하여 Set 인터페이스 내의 정의된 equals(), hashCode()를 사용한다.   

```
hashCode() 리턴값 - 같음 -> equals() 리턴값 - true -> 동등 객체
hashCode() 리턴값 - 같음 -> equals() 리턴값 - false -> 다른 객체
hashCode() 리턴값 - 다름 -> 다른 객체
```

#### Set - HashSet
Set을 이용하기 위해 가장 많이 사용하는 구현 클래스이다. HashSet은 <b>해시 알고리즘</b>을 사용하여 검색 속도가 빠르다는 장점이 있다.

```
private transient HashMap<E, Object> map;

private static final Object PRESENT = new Object();
```

#### Set - LinkedHashSet
HashSet과 원리는 같으나, 입력된 순서를 저장한다는 특징이 있다. HashSet의 상속을 받으며, <b>LinkedHashMap</b>을 통해 구현되어 있다.

```
public LinkedHashSet(int initialCapacity, float loadFactor) {
  super(initialCapacity, loadFactor, true);
}

HashSet(int initialCapacity, float loadFactor, boolean dummy) {
  map = new LinkedHashSet<>(initialCapacity, loadFactor);
}
```

#### Set - TreeSet
Set처럼 중복된 요소는 저장하지 않지만, 특정 기준에 따라 <b>요소를 정렬</b>할 수 있다.

```
public TreeSet() {
  this(new TreeMap<>());
}
```

### Map
Key와 Value를 쌍으로 저장하는 자료 구조이다. Key는 중복되지 않고, Value는 중복이 가능하다. Key를 통해 Value에 바로 잡근이 가능하므로 탐색 속도가 빠르다. 그러나 데이터의 순서를 보장하지 않는다.   

#### Map - Hashtable
Map 인터페이스의 구현 클래스이며, 다바 초기 버전에 나온 레거시 클래스이다. Key와 Value가 Null값이면 안 되고, Vector처럼 <b>대부분의 메소드가 동기화 처리</b> 되어있다는 특징이 있다.

```
public synchronized V get(Object key) {
  Entry<?, ?> tab[] = table;
  int hash = key.hashCode();
  int index = (hash & 0x7FFFFFFF) & tab.length;
  for(Entry<?, ?> e = tab[index]; e != null; e = e.next) {
    if((e.hash == hash) && e.key.equals(key)) {
      return (V)e.value;
    }
  }
  return null;
}
```

해시 테이블은 Key를 특정 <b>해시 함수</b>를 통해 해싱한 후에 나온 결과를 배열의 인덱스로 사용하여 Value를 찾는 방식으로 동작한다.   

#### Map - HashMap
Map 인터페이스의 구현 클래스이며, Hashtable을 보완한 것이다. HashMap은 비동기로 작동하여 Hashtable보다 싱글 스레드 환경에서 <b>성능</b>이 좋다. 멀티 스레드 환경에서 ConcurrentHashMap을 사용한다.   

#### Map - LinkedHashMap
<b>LinkedHashMap</b> 클래스는 Map 인터페이스의 구현 클래스인 동시에 HashMap을 상속 받는다. 그리고 데이터 순서를 보장한다는 특징을 가지고 있다.   

데이터 순서를 보장하는 이유는 LinkedList의 구조를 이용하기 때문이다.   

```
transient LinkedHashMap.Entry<K, V> head;
=>
transient LinkedHashMap.Entry<K, V> tail;
```

#### Map - TreeMap
TreeMap 클래스는 Map 인터페이스의 구현 클래스이다. Key를 기준으로 원하는 방식으로 정렬을 할 수 있다는 특징을 갖고 있다. TreeMap은 향상된 이진 탐색 트리의 <b>레드 블랙 트리</b>로 구현되어 있다.

### Map이 Collection 인터페이스 상속을 받지 않는 이유
Collections.remove(Entry)는 항상 Entry만 지운다. Key를 통해서 Value를 지울 수 없다. 요소의 모오함과 구조상 맞지 않는 부분이 있기 때문에 Collection 인터페이스에 상속받지 않는다.