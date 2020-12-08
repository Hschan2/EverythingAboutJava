# 일급 컬렉션을 사용하는 것. 그리그 그 이유

### 일급 컬렉션이란?
```
일급 컬레션이란 Collection을 Wrapping하면서, Wrapping한 Collection 외 다른 멤버 변수가 없는 상태
```
위의 의미는 정확히 무엇일까?
<br>
아래 단계를 보고 이해해보자
```
public class Person {
  private String name;
  private List<Car> cars;
}

public class Car {
  private String name;
  private String oil;
}
```
위의 코드를
```
public class Person {
  private String name;
  private Cars cars;
}

// List<Car> cars를 Wrapping을 한다
// 일급 컬렉션 사용
public class Cars {
  private List<Car> cars;
}

public class Car {
  private String name;
  private String oil;
}
```
위 처럼 바꾸어 사용하는 것을 의미한다.<br>
위의 코드를 보면 일급 컬렉션은 List<Car> cars 외에는 다른 변수를 사용하지 않는다.

### 왜 사용하는가?
예를 들어서 알아보자
```
public class GSConvenienceStore {
  private List<IceCream> iceCream;
}

public GSConvenienceStore(List<IceCream> iceCream) {
  this.iceCream = iceCream;
}

public class IceCream {
  private String name;
}
```

위 처럼 아이스크림을 판매하기 위한 코드가 있다. <br>
이 곳은 아이스크림을 팔 때, 10개 이상의 종류를 판매할 수 없다. <br>
그렇다면 우리는 List<IceCream> iceCream의 Size가 10개가 넘으면 안되는 검증이 필요하다. <br>

```
public class CUConvenienceStore {
  private List<IceCream> iceCream;
}

public CUConvenienceStore(List<IceCream> iceCream) {
  validateSize(iceCream);
  this.iceCream = iceCream;
}

private void validateSize(List<IceCream> iceCream) {
  if(iceCream.size() >= 10) {
    new throw IllegalArgumentException("아이스크림은 10개 이상의 종류를 판매할 수 없다.")
  }
}
```
그래서 왜 사용하는 것인가? <br><br>

만약 아이스크림 외에 과자, 라면, 음료수 등 다른 물건도 있다고 한다면? <br>
```
  private void validateSize(List<Snack> sncak)
  private void validateSize(List<Noodle> noodle)
  private void validateSize(List<Drink> drink)
```
위 처럼 물건마다 validate 할 것인가? <br>
만약 CUConvenienceStore이 아닌 GSConvenienceStore에서도 판매를 한다면 GS에서도 또 검증을 할 것인가? <br><br>

List<IceCream> iceCream 중에서 하나를 find를 하는 메소드를 만든다고 가정해보자
```
public class CUConvenienceStore {
  private List<IceCream> iceCream;
  ...
  public IceCream find(String name) {
    return iceCreams.stream()
        .filter(iceCream::isSameName)
        .findFirst()
        .orElseThrow(RuntimeException::new)
  }
}
```
위 처럼 find 메소드를 만들었다고 가정했을 때, GS에서도 find 메소드를 만들 것인가? <br>

이럴 경우에 ConvenienceStore Class의 역할이 무거워 지고, 중복 코드가 많아진다. <br>
이것을 해결해줄 수 있는 것이 <b>일급컬렉션</b>이다. <br>
일급컬렉션은 상태와 행위를 각각 관리할 수 있다. <br><br>

IceCream을 일급컬렉션으로 다시 만들어보자

```
public class IceCreams {
  private List<IceCream> iceCreams;
  
  public IceCreams(List<IceCream> iceCreams) {
    validateSize(iceCreams)
    this.iceCreams = iceCreams
  }
  
  public void validateSize(List<IceCream> iceCreams) {
    if(iceCreams.size() >= 10) {
      new throw IllegalArgumentException("아이스크림은 10개 이상의 종류를 판매할 수 없다.")
    }
  }
  
  public IceCream find(String name) {
    return iceCreams.stream()
        .filter(iceCream::isSameName)
        .findFirst()
        .orElseThrow(RuntimeException::new)
  }
}
```

ConvenienceStore Class를 바꿔보자
```
public class CUConvenienceStore {
  private IceCreams iceCreams;
  
  public CUConvenienceStore(IceCreams iceCreams) {
    this.iceCreams = iceCreams;
  }

  private IceCream find(String name) {
    return iceCreams.find(name);
  }
}

public class GSConvenienceStore {
  private IceCreams iceCreams;
  
  public GSConvenienceStore(IceCreams iceCreams) {
    this.iceCreams = iceCreams;
  }

  private IceCream find(String name) {
    return iceCreams.find(name);
  }
}
```
* 만약 find 메소드가 중복 사용하는 것이 신경쓰인다면 부모 클래스를 만들어 상속 받아 사용하자 <br>

위 처럼 코드를 작성한다면 다른 물건이 나오더라도 검증은 각 물건의 일급 컬렉션이 해줄 것이다. <br>
그리고 ConvenienceStore Class가 했던 역할을 각 물건에 위임하여 상태와 로직을 관리할 것이다. <br>

### 정리
<b>일급 컬렉션</b>을 사용하면 <b>상태와 로직을 따로 관리</b>할 수 있기 때문에 로직이 사용되는 <b>클래스의 부담</b>을 줄일 수 있고 <b>중복 코드</b>를 줄일 수 있다. <br>

### Collection의 불변성 보장

일급 컬렉션을 검색할 때, 소개와 써야하는 이유를 자주 볼 수 있다. 이러한 글들의 좋은 점 중 하나는 <b>Collection의 값을 변경할 수 있는 메소드가 없어서 불변성을 보장</b> 해준다는 글을 볼 수 있다. <br>

그러나 <b>일급 컬렉션</b>은 <b>불변성을 보장하지 않으며, 보장하도록 구현할 필요가 없다.</b>

##### 왜 불변성을 보장할 필요가 없고 보장하도록 구현할 필요가 없을까?

일급 컬렉션 사용의 규칙을 보면 <b>일급 컬렉션이 주는 기능의 핵심은 불변이 아니다</b> 를 확인할 수 있다. <br>

일급 컬렉션이 왜 불변이 아닌지 그리고 만약 불변으로 만들고 싶다면 어떻게 만들어야 하는지 예시로 알아보자

```
public class Lotto {
  private final List<LottoNumber> lotto;
  
  public List<LottoNumber> getLotto() {
    return lotto;
  }
}
```
Lotto 클래스처럼 setter를 구현하지 않으면 불변 컬렉션이 된다. <br>

그러나! <br>

setter를 사용하지 않아도 Lotto 클래스 안에 있는 lotto 변수에 변화를 줄 수 있다.

```
public class Lotto {
  private final List<LottoNumber> lotto;
  
  public Lotto(List<LottoNumber> lotto) {
    this.lotto = lotto
  }
  
  public List<LottoNumber> getLotto() {
    return lotto;
  }
}

public class LottoNumber {
  private final int lottoNumber;
  
  public LottoNumber(int lottoNumber) {
    this.lottoNumber = lottoNumber;
  }
  
  public String toString() {
    return "LottoNumber {" +
        "lottoNumber = " + lottoNumber +
        "}";
  }
}
```
위의 코드가 있다고 가정을 한다면
```
public void lotto_test() {
  List<LottoNumber> lottoNumbers = new ArrayList<>();
  lottoNumbers.add(new LottoNumber(1));
  Lotto lotto = new Lotto(lottoNumbers);
  lottoNumbers.add(new LottoNumber(2));
}
```
테스트 코드를 위 처럼 작성했을 때, lotto를 get한다면 어떤 결과를 가져올까?
```
[LottoNumber{lottoNumber = 1}, LottoNumber{lottoNumber = 2}]
```
위의 결과를 가져올 것이다. <br>
lottoNumbers와 lotto class의 멤버 변수의 주소값이 같기 때문에 영향을 받게 될 것이다. <br>
Lotto class의 멤버 변수 lotto가 Parameter로 받은 lottoNumbers의 영향을 받지 않기 위해서는 이렇게 수정하자.

```
public class Lotto {
  private final List<LottoNumber> lotto;
  
  public Lotto(List<LottoNumber> lotto) {
    this.lotto = new ArrayList<>(lotto);
  }
  
  public List<LottoNumber> getLotto() {
    return lotto;
  }
}
```
위 처럼 작성하면 저장되는 주소값을 재할당하기 때문에 영향을 받지 않을 것이다. 그러나!
```
public void lotto_test() {
  List<LottoNumber> lottoNumbers = new ArrayList<>();
  lottoNumbers.add(new LottoNumber(1));
  Lotto lotto = new Lotto(lottoNumbers);
  lottoNumbers.add(new LottoNumber(2));
}
```
다시 테스트 코드를 실행하면
```
[LottoNumber{lottoNumber = 1}, LottoNumber{lottoNumber = 2}]
```
의 결과값이 다시 나올 것이다. <br>
이를 해결하는 방법으로 unmodifiableList를 사용하자.
```
public class Lotto {
  private final List<LottoNumber> lotto;
  
  public Lotto(List<LottoNumber> lotto) {
    this.lotto = new ArrayList<>(lotto);
  }
  
  public List<LottoNumber> getLotto() {
    return Collections.unmodifiableList(lotto);
  }
}
```
위 처럼 unmodifiableList를 사용해서 작성하면 lotto는 불변이 되고, getter로 return을 해서 사용될 때 변경이 불가능하다.
