# DTO vs VO
간단하게 정리하자면, DTO는 데이터 전달용이며 VO는 값 표현용이라고 할 수 있다.

## DTO (Data Transfer Object)
데이터를 전달하기 위해 사용하는 객체이다. 즉, 데이터를 담아서 전달하는 바구니 역할을 한다.   

DTO는 <b>계층 간</b> 데이터를 전달하기 위한 객체이다.   

```
Controller(Web Layer) <-- 전달(DTO) --> Service(Service Layer)
```
위에서 보듯이 DTO는 Web Layer와 Service Layer 간에 담고 있는 데이터를 서로 전달하는 역할을 하고 있다.   

### DTO의 특징
1. 오직 Getter/Setter 메소드 만을 갖는다.
2. 다른 로직을 갖지 않는다.   

* DTO 사용 예시를 위한 클래스
```
public class CrewDto {
  private String name;
  private String nickname;

  public String getName() {
    return name;
  }

  public String setName(String name) {
    this.name = name;
  }

  public String getNickname() {
    return nickname;
  }

  public String setNickname(String nickname) {
    this.nickname = nickname;
  }
}
```

* DTO 사용 예시를 위한 Service Layer 측 메소드
```
public CrewDto createNewCrew() {
  String newName = "우아";
  String newNickname = "테크";

  CrewDto crewDto = new CrewDto();
  crewDto.setName(newName);
  crewDto.setNickname(newNickname);

  return crewDto;
}
```

* DTO 사용 예시를 위한 Web Layer 측 메소드
```
public String createNewCrew() {
  CrewDto newCrewDto = sampleService.createNewCrew();
  String nameOfNewCrew = newCrewDto.getName();
  String nicknameOfNewCrew = newCrewDto.getNickName();
  return nameOfNewCrew + nicknameOfNewCrew;
}
```

### DTO를 불변 객체로 만들기
* 불편 객체로 만들기 전
```
public class CrewDto {
  private String name;
  private String nickname;

  public String getName() {
    return name;
  }

  public String setName(String name) {
    this.name = name;
  }

  public String getNickname() {
    return nickname;
  }

  public String setNickname(String nickname) {
    this.nickname = nickname;
  }
}
```

* 불변 객체로 만든 후
```
public class CrewDto {
  private String name;
  private String nickname;

  public CrewDto(String name, String nickname) {
    this.name = name;
    this.nickname = nickname;
  }

  public String getName() {
    return name;
  }

  public String getNickname() {
    return nickname;
  }
}
```

### DTO Class와 Entity Class

#### DTO Class와 Entity Class를 분리
* 요청이나 응답 값을 전달하는 클래스는 어떤 것을 사용해야 할까?   
   
<b>Entity Class</b>는 값을 전달하거나 받는 클래스로 사용하면 안 된다. 그 이유는 데이터 베이스와 매핑되어 있는 핵심 클래스이기 때문이다. Entity Class를 기준으로 테이블이 생성되고 스키마가 변경된다.   

만약에 Entity Class를 값을 전달하거나 받는 클래스로 사용하게 될 경우에는 View가 바뀔 때마다 Entity Class도 그에 맞춰 바꿔져야 하는 일이 생긴다.   

그러므로, 값을 전달하거나 받는 클래스로 사용할 때는, DTO Class를 사용하는 것이 좋다. DTO Class는 이러한 역할을 할 때, 다른 클래스에 영향을 끼치지 않고 자유롭게 변경할 수 있기 때문이다.   

## VO (Value Object)
값, 그 자체를 표현하는 객체이다. (예. 돈) 값으로만 분류되는 것을 의미한다.   

* Money VO Class 구현
```
public class Money {
  private final int value;

  public Money(int value) {
    this.value = value;
  }

  public int getHalfValue() {
    return value / 2;
  }
}
```

VO는 값을 표현하는 객체이기 때문에 불변 객체로 개발해야 한다.   

### Money VO 테스트 에러
```
public class MoneyTest {

  @DisplayName("속성값이 같으면 두 객체는 같은지 테스트")
  @Test
  void Should_Be_Equal_CompareEqualsAndHashCode() {
    final int MONEY_VALUE = 10_000;
    Money money1 = new Money(MONEY_VALUE);
    Money money2 = new Money(MONEY_VALUE);

    assertThat(money1).isEqualTo(money2);
    assertThat(money1).hasSameHashCodeAs(money2);
  }
}
```
위 코드는 money1과 money2 값을 비교하는 것이다. Equal()과 HashCode()로 비교해본 결과, 두 개 모두 에러가 발생한다.   

### HashXXX (HashSet, HashMap, Hashtable)의 동등 비교 방식
```
HashCode() 리턴값 -- 같음 --> equals() 리턴값 -- true --> 동등 객체
HashCode() 리턴값 -- 같음 --> equals() 리턴값 -- false --> 다른 객체
HashCode() 리턴값 -- 다름 --> 다른 객체
```

### Equals() & hashCode() 오버라이딩
값을 기준으로 비교하도록 Equals()와 hashCode() 오버라이딩한다.   

```
public class Money {
  private final int value;

  public Money(int value) {
    this.value = value;
  }

  public int getHalfValue() {
    return value / 2;
  }

  @Override
  public boolean equals(Object o) {
    if(this == 0) {
      return true;
    }
    if (!(o instanceof Money)) {
      return false;
    }

    Money money = (Money) o;
    return value == money.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
```

## DTO와 VO 비교 정리
||DTO|VO|
|------|---|---|
|용도|레이어 간 데이터 전달|값 자체 표현|
|동등 결정|속성값이 모두 같다고 해서 같은 객체가 아니다.|속성값이 모두 같으면 같은 객체다.|
|가변 / 불변|Setter 존재 시 가변, Setter 비 존재 시 불변|불변|
|로직|Getter/Setter 외의 로직을 갖지 않는다.|Getter/Setter 외의 로직을 가질 수 있다.|
