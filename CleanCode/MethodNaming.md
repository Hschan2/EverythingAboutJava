### 좋은 자바 메서드 네이밍

변수 혹은 메서드의 이름을 잘 지어야 하는 이유는 의도가 분명해야 하며 목적이 잘 드러날 수 있도록 만들어야 주변 사람들이 잘 이해할 수 있기 때문이다. <br>
그리고 잘 만든 네이밍은 가독성이 좋아진다. <br>
좋은 네이밍은 다른 사람이 코드를 이해하는 시간을 줄여준다. 

```
 public List<int[]> getThem() {
      List<int[]> list1 = new ArrayList<int[]>();
      for (int[] x : theList) {
          if (x[0] == 4) {
              list1.add(x);
          }
      }
      return list1;
  }
```
위 코드를 보자. 메소드가 무엇을 말하고자 하는지 알 수 없다. 그리고 list1이 무엇을 담고자 하는지 알 수 없다. 이는 좋은 네이밍이 아니다.

```
public List<int[]> getFlaggedCells() {
      List<int[]> flaggedCells = new ArrayList<int[]>();
      for (int[] cell : gameBoard) {
          if (cell.isFlagged()) {
          	flaggedCells.add(cell);
          }
      }
      return flaggedCells;
  }
```
위 코드를 좋은 네이밍을 가졌다. 메소드의 이름처럼 FlaggedCells을 반환하고 List에 Flag된 Cell을 담고 있는 것을 확실하게 알 수 있다.

##### 네이밍 고려사항
네이밍을 할 때 고려해야 할 사항이 대략 3가지가 있다. <br>
- 이 메서드가 왜 존재해야 하는가?
- 이 메서드로 무슨 작업을 할 것인가?
- 이 메서드로 무슨 작업을 할 것인가?

코드를 보며 고려사항을 다시 보자
```
public List<Piece> findPiecesByColor(Color color) {
  ...
}
```
고려사항을 위 코드로 다시 보자면 <br>
- 이 메서드가 왜 존재해야 하는가? color에 대해 존재하는 piece들을 알기 위해
- 이 메서드로 무슨 작업을 할 것인가? color에 맞는 piece을 가져온다.
- 이 메서드로 무슨 작업을 할 것인가? 흑색(혹은 흰색)의 piece들을 가져온다.

##### 메서드 명명 규칙
메서드의 네이밍은 lowerCamelCase 규칙으로 한다. <br>
메서드의 이름에서 맨 첫 번째 글자는 소문자로 시작하며 그 다음에 오는 단어의 첫 글자는 대문자로 시작한다. <br>
메서드의 이름은 동사 혹은 전치사로 한다.
```
// 동사 규칙
public void getUserByName(){}
public void setDisplayName(){}
public void inputData(String input){}
        
// 전치사 규칙
public String toString(){}
public User of(){}
```

JUnit 테스트 코드는 메소드 이름에 _(언더스코어)가 표시되어 이름의 논리 컴포넌트를 구분하고 각 메소드는 lowerCamelCase 규칙으로 한다.
```
@Test
void isAdult_AgeLessThan18_False() { // MethodName_StateUnderTest_ExpectedBehavior (메서드명_테스트상태_기대행위)
  ...
}

@Test
void isAdult_False_AgeLessThan18() { // MethodName_ExpectedBehavior_StateUnderTest (메서드명_기대행위_테스트상태)
  ...
} 
```

##### 메서드 네이밍에서 자주 사용하는 동사
1. get / set <br>
먼저 getter/setter 관련하여 자바빈 규약이 있다.
```
public class Student {
    private String name;
    private int age;
        
    public String getName() {
        return name;
    }
        
    public int getAge() {
        return age;
    }
        
    public String setName(String name) {
        this.name = name;
    }
        
    public int setAge(int age) {
        this.age = age;
    }
}
```

이러한 상황 외에는 기억해야 할 것이 있다. <br>

- setter 하는 일이 단순하고, 객체 생성 시점에 필요한 모든 값들을 주입하지 않아 개발자의 실수가 발생할 수 있다. 또한, public 으로 공개해놓은 set 메서드는 코드 다른 부분에서 언제 호출되어 값이 바뀔지 알기 힘들다. 고민 없이 자바빈 규약에 따른 getter, setter는 객체지향적인 코드작성에 가장 큰 적이라고 할 수 있다.
- 객체의 데이터에 마음대로 접근할 수 있다면 메소드를 통해 만들어진 데이터는 의미가 없게 되는 문제가 생긴다.

되도록 상태 데이터를 가지고 있는 객체에서 데이터를 get하지 말고 객체에 메세지를 보내는 것을 추천한다.

```
private boolean isMaxPosition(Car car) {
	return car.getPosition() == maxDistance;
}
```

위의 getPosition()처럼 데이터를 get 하지 말고

```
car.isMaxPosition(maxDistance);
```
위 처럼 Car에 메세지로 보내서 구현하는 것을 추천한다. <br>

2. init <br>
데이터를 초기화하는 메소드에서 사용한다.

```
// 만약 User 클래스 안에 있다면 User 데이터를 초기화하는 메소드
public void initData() {
  ...
}
```

3. is / has / can <br>
이는 boolean 값을 return 한다. <br>

- is는 맞는지 아닌지 판단하는 메소드에 사용한다.
```
// 숫자가 맞는지 아닌지 확인
public boolean isNumber() {
  ...
}
```

- has는 데이터를 가지고 있는지 아닌지 판단하는 메소드에 사용한다.
```
// Data를 가지고 있는지 아닌지 확인
public boolean hasData() {
  ...
}
```

- can은 할 수 있는지 없는지 판단하는 메소드에 사용한다.
```
// 주문을 할 수 있는지 없는지 확인
public boolean canOrder() {
  ...
}
```

4. create <br>
이는 새로운 객체를 생성하고 return하는 메소드에 사용한다.
```
// Board를 만들고 return 해주는 메소드
public Board create() {
  ...
}
```

5. find <br>
이는 데이터를 찾는 메소드에 사용한다.
```
// number에 해당하는 Element를 return한다.
public Element findElement(int number) {
  ...
}
```

6. to <br>
이는 해당 객체를 다른 형태의 객체로 변환하는 메소드에 사용한다.
```
// String으로 변환해주는 메소드
public String toString(){}
```

7. A-By-B <br>
이는 B를 기준으로 A를 하겠다는 메소드에 사용한다.
```
// name을 기준으로 User를 get하는 메소드
public void getUserByName(String name) {
  ...
}
```

메소드 네이밍 참조 - https://woowacourse.github.io/javable/2020-04-26/Method-Naming
