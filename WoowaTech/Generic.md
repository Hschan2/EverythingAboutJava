# Generic (제네릭)
클래스나 메소드에서 사용할 내부 데이터 타입을 외부에서 지정하는 기법

```
class FruitBox<T> {
  List<T> fruits = new ArrayList<>();

  public void add(T fruit) {
    fruits.add(fruit);
  }
}

FruitBox<Apple> appleBo = new FruitBox<>();
```

## 제네릭 클래스
클래스 선언에 <b>타입 매개변수</b>가 쓰이면, 이를 <b>제네릭 클래스</b>라고 한다.
```
class FruitBox<T> {}
```

## 제네릭을 사용하는 이유
제네릭을 사용하지 않았을 때의 문제점은 자료형에 대한 검증이 컴파일 타임에 이루어지지 않는다는 것이다. 다음은 제네릭의 장점이다.   

1. 타입 안정성
```
일반 클래스

class Apple {}
class Banana {}
class FruitBox {
  private Object fruit;
  public FruitBox(Object fruit) {
    this.fruit = fruit;
  }

  public Object getFruit() {
    return fruit;
  }
}
```

```
에러 코드

public static void main(String[] args) {
  FruitBox appleBox = new FruitBox(new Apple());
  FruitBox bananaBox = new FruitBox(new Banana());
  Apple apple = (Apple) FruitBox.getFruit();
  Banana banana = (Banana) FruitBox.getFruit(); // 런타임 에러 발생
}
```

```
제네릭 클래스

class Apple {}
class Banana {}
class FruitBox<T> {
  private T fruit;
  public FruitBox(T fruit) {
    this.fruit = fruit;
  }

  public T getFruit() {
    return fruit;
  }
}
```

```
에러 코드

public static void main(String[] args) {
  FruitBox<Apple> appleBox = new FruitBox<>(new Apple());
  FruitBox<Banana> bananaBox = new FruitBox<>(new Banana());
  Apple apple = FruitBox.getFruit();
  Banana banana = FruitBox.getFruit(); // 런타임 에러 발생
}
```

2. 캐스팅 삭제
```
비제네릭 클래스

public static void main(String[] args) {
  FruitBox appleBox = new FruitBox(new Apple());
  FruitBox bananaBox = new FruitBox(new Banana());
  Apple apple = (Apple) FruitBox.getFruit();
  Banana banana = (Banana) FruitBox.getFruit(); // 런타임 에러 발생
}
```

```
제네릭 클래스

public static void main(String[] args) {
  FruitBox<Apple> appleBox = new FruitBox<>(new Apple());
  FruitBox<Banana> bananaBox = new FruitBox<>(new Banana());
  Apple apple = FruitBox.getFruit();
  Banana banana = FruitBox.getFruit(); // 런타임 에러 발생
}
```

제네릭을 사용하는 이유는. 즉, 컴파일 타임에 자료형의 오류에 대한 검증을 수행하여 런타임에 자료형에 안전한 코드를 실행한다. 그리고 반환값에 대한 타입 변환 및 타입 검사에 들어가는 노력을 줄일 수 있고, 형변환이 없어지기 때문에 가독성이 좋아진다.   

## 제네릭 메소드의 형태
* <> 안의 타입으로 매개변수의 데이터 타입을 지정한다.
* 타입 파라미터의 범위는 메소드 블록 이내로 한정한다.   

```
public <T> void add(T t) {
  ...
}
```

제네릭 메소드는 제네릭 클래스가 아니더라도 정의할 수 있다. 다만, 제네릭 클래스와 제네릭 메소드의 타입 매개변수가 같다면 메소드의 타입 매개변수를 우선으로 한다.

```
class Name {
  public <T> void printClassName(T t) {
    System.out.println(t.getClass().getName());
  }
}
```

```
public static void main(String[] args) {
  Name name = new Name();
  name.printClassName(1);
  name.printClassName(3.14);
}

console>
java.lang.Integer
java.lang.Double
```

## 타입 매개변수의 제한
예를 들어서, 과일 박스 클래스의 매개변수 타입을 야채로 했을 경우에 이는 컴파일 에러를 발생한다. 이와 같은 이유로 타입 매개변수에 대해 제한을 둔다.

```
class FruitBox<Vegetable> {
  private List<Fruit> fruits = new ArrayList<>();

  public void add(Vegetable fruit) {
    fruits.add(fruit); // 컴파일 에러
  }
}
```

### 상한 경계
* T extends Fruit   
타입 매개변수의 클래스는 Fruit 클래스이거나 Fruit의 하위 클래스이어야 한다. (예. Fruit Class, Apple Class, Banana Class)   

* T super Fruit   
타입 매배견수의 클래스는 Fruit 클래스이거나 Fruit의 상위 클래스이어야 한다. (예. Fruit Class, Food Class, Object)   

위의 컴파일 에러를 해결하기 위해서 <b><T extends Fruit></b>으로 Fruit 혹은 Fruit의 하위 클래스만이 올 것을 보장하게 만들어야 한다.   

```
class FruitBox<T extends Fruit> {
  private List<Fruit> fruits = new ArrayList<>();

  public void add(T fruit) {
    fruits.add(fruit); // 추가 완료
  }
}
```

## 비경계 와일드카드
* ? 의 형태로 사용한다. (예. List<?>)
* 모든 타입이 인자가 될 수 있다.   

### 비경계 와일드카드의 사용
```
public static void PrintList(List<Object> list) {
  for(Object elem: list) {
    System.out.println(elem + " ");
  }

  System.out.println();
}
```

```
List<String> strings = new ArrayList<>();
printList(strings); // 컴파일 에러
```

위 코드의 컴파일 에러에 대한 이유는 String 타입이기 때문이다. List<Object>로 선언했기 때문에 String이 올 수 없다.   

임의의 타입 A의 리스트 List<A>는 List<Object>의 서브 타입이 아니라는 것이다. 이유는 상속 관계가 아니기 때문이다. 그렇기 때문에 이럴 경우에 비경계 와일드카드를 사용해야 한다.   

```
public static void PrintList(List<?> list) {
  for(Object elem: list) {
    System.out.println(elem + " ");
  }

  System.out.println();
}
```

위 코드처럼, 임의의 타입 A의 리스트 List<A>는 List<?>의 서브 타입이다.   

### 비경계 와일드카드 - Get
* List<?>에서 Get한 원소는 Object 타입이다.   
비경계 와일드카드의 원소는 어떤 타입도 될 수 있다. 어떤 타입이 와도 읽을 수 있도록, 모든 타입의 공통 조상인 Object로 받는다.   

```
public static void get(List<?> list) {
  Object object = list.get(0);
  Integer integer = list.get(0); // 컴파일 에러
}
```

Integer로 선언한 변수가 컴파일 에러가 나는 이유는 위 설명처럼 Object로 받지 않았기 때문이다.   

### 비경계 와일드카드 - Add
* List<?>에는 Null만 삽입할 수 있다.   
비경계 와일드카드의 원소가 어떤 타입인지 알 수 없다. 그러므로 타입 안정성을 지키기 위해 Null만 삽입할 수 있다.   

```
public static void main(String[] args) {
  List<Integer> ints = new ArrayList<>();
  addDouble(ints);
}

private static void addDouble(List<?> ints) {
  ints.add(3.14);
}
```

만약에 ```ints.add(3.14)```에서 값을 추가할 수 있다면, List<Integer>에 Double을 추가하는 모순이 발생한다.   

## 상한 경계 와일드카드 (Upper Bounded Wildcards)
* ? extends T의 형태로 사용한다. (예. List<? extends T>)
* T 혹은 T의 하위 클래스만 인자로 올 수 있다.   

? extends T는 T 상한 와일드카드이며, List<? extends T>는 T 상한 와일드카드의 리스트이다.   

### 상한 경계 와일드카드 - Get
List<? extends T>에서 Get한 원소는 T이다.   
* 상한 경계 와일드카드의 원소는 T 혹은 T의 하위 클래스이다.
* 원소들의 최고 공통 조상인 T로 읽으면, 어떤 타입이 오든 T로 읽을 수 있다.   

```
public static void printList(List<? extends Fruit> fruits) {
  for (Fruit fruit: fruits) {
    System.out.println(fruits + " ");
  }

  System.out.println();
}
```

위 코드에서 Fruit으로 타입을 설정했을 경우, 어떤 타입이 오든 읽을 수 있을 것이다. 그러나 Apple 혹은 Banana로 설정했을 경우에는 컴파일 에러가 발생한다.   

### 상한 경계 와일드카드 - Add
List<? extends T>에는 Null만 삽입할 수 있다.   
* 상한 경계 와일드카드의 원소가 어떤 타입인지 알 수 없다.   

```
List<Apple> apples = new ArrayList<>();
List<? extends Fruit> fruits = apples;

fruits.add(new Banana()); // 컴파일 에러
```

위 코드에서 에러가 나는 이유는 List<Apple>에 Banana Class가 들어갔기 때문이다.   

## 하안 경계 와일드카드 (Lower Bounded Wildcards)
* ? super T의 형태로 사용한다. (예. List<? super T>)
* T 혹은 T의 상위 클래스만 인자로 올 수 있다.   

### 하안 경계 와일드카드 - Get
List<? super T>에서 Get한 원소는 Object이다.   
* T 하안 경계 와일드카드의 원소는 T의 상위 클래스 중 어떤 타입도 될 수 있다. 어떤 타입이 와도 읽을 수 있도록, T들의 공통 조상인 Object로 받는다.   

```
public static void printList(List<? super Fruit> fruits) {
  for (Object fruit: fruits) {
    System.out.println(fruits + " ");
  }

  System.out.println();
}
```

List<Fruit>, List<Food>, List<Object>가 매개변수로 오면 Object 타입으로 모두 읽을 수 있다.   

### 하안 경계 와일드카드 - Add
List<? super T>에는 T 혹은 T의 하위 클래스만 삽입할 수 있다.   
* 하안 경계 와일드카드의 원소는 T 혹은 T의 상위 클래스이다.   

```
List<? super Fruit> fruits = new ArrayList<>();
fruits.add(new Apple());
fruits.add(new Fruit());
fruits.add(new Food()); // 컴파일 에러
```

위 코드에서 컴파일 에러가 발생하는 이유는 Fruits가 List<Fruit>일 경우 Food는 Fruit의 상위 클래스이므로 추가할 수가 없다.   

하안 경계 와일드카드에서 T 혹은 T의 하위 클래스만 삽입할 수 있는 이유는 List<Fruit>, List<Food>, List<Object> 중 어떤 리스트가 오는지 fruits는 모르기 때문이다. 그러나 그 중 어떤 리스트여도, Fruit 혹은 Fruit의 하위 클래스를 원소로 추가할 수 있다.   

