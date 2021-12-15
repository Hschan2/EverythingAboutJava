# 자바 Optional 기초

## Optional
* 값이 있거나 혹은 없는 값을 표현한다.
* Null을 대체한다.
* Java 8 버전 ~

## Optional 사용
* Optional.of(): Null이 아닌 값
```
Optional<String> opt = Optional.of("value");

Optional.of(null) -> NullPointerException
```

* Optional.ofNullable(): Null이 가능한 값으로 생성
```
Optional<String> opt = Optional.ofNullable(someValue);
```

* Optional.empty(): 빈 값
```
Optional<String> opt1 = Optional.empty();
Optional<String> opt2 = Optional.ofNullable(null);
```

### Optional에 담긴 값 구하기
* get()
    * 값이 없는 Optional에 get()을 실행하면 Exception
```
Optional<String> opt = Optional.of("value");
String str = opt.get(); // str: "value"

Optional<String> opt1 = Optional.empty();
opt1.get(); // NoSuchElementException
```

### 값의 유무 확인
* isPresent(): 값이 있는지 확인
* isEmpty(): 값이 없는지 확인 (Java 11 ~)
```
Optional<String> opt = Optional.of("value");
opt.isPresent(); // true
opt.isEmpty(); // false

Optional<String> opt1 = Optional.empty();
opt1.isPresent(); // false
opt1.isEmpty(); // true
```

#### 값이 있을 때
* ifPresent()
```
String value = getValue();

if (value != null) {
    doSome(value);
}

Optional<String> opt = getValue();
opt.ifPresent(value -> doSome(value));
```

* ifPresentOrElse()
```
String value = getValue();

if (value != null) {
    doSome(value);
} else {
    doOther();
}

Optional<String> opt = getValue();
opt.ifPresentOrElse(
        value -> doSome(value),
        () -> doOther()
    );
```

#### 값이 없을 때
* 다른 값 사용하기
```
String value = getValue();
String result = value == null ? "default" : value;
```

* orElse
```
Optional<String> opt = getValue();
String result = opt.orElse("default"); // String result = opt.orElse(null);
```
* orElseGet

```
Optional<String> opt = getValue();
// orElseGet(Supplier<? extends T>)
// 함수형으로 반환
String result = opt.orElseGet(() -> "default");
```

* or
```
Optional<String> opt = getValue();
// or(Supplier<? extends Optional<? extends T>>)
// 함수형으로 반환
String result = opt.or(() -> Optional.of("default"));
```

* orElseThrow: 값이 없으면 Exception, 값이 있으면 값 반환(리턴)
    * 함수형으로 반환
```
Member m = repository.findById("id");

if (m == null) {
    throw new NoMemberException();
}

m.block();
```

위의 코드를 orElseThrow를 사용하여 작성할 수 있다.

```
Optional<Member> opt = repository.findById("id");
Member m = opt.orElseThrow(() -> new NoMemberException());
m.block();
```

## Map
* Map에 전달받은 함수를 실행해서 값을 반환한 Optional을 반환(리턴)
* Map은 값이 없으면 빈 Optional을 반환(리턴)
```
Member m = ...;
LocalDate birth = m.getBirthday();
int passedDays = cal(birth);
```

위의 코드를 map을 사용하여 아래 코드로 변환하여 사용이 가능하다.

```
Optional<Member> memOpt = ...;
Optional<LocalDate> birthOpt = memOpt.map(mem -> mem.getBirthday());
Optional<Integer> pdOpt = birthOpt.map(birth -> cal(birth));

// 위의 코드를 아래처럼 한 줄로 작서이 가능
Optional<Integer> pdOpt2 = memOpt.map(mem -> mem.getBirthday()).map(birth -> cal(birth));

// 빈 Optional은 map으로 전달한 함수를 실행하지 않고 빈 Optional을 반환
Optional<String> empty = Optional.empty();
// empty2.isEmpty() -> true
Optional<String> empty2 = empty.map(str -> str.toUpperCase());
```

### flatMap
* flatMap에 전달받은 함수 이용 값을 변환한 Optional을 반환
    * flatMap에 전달한 함수는 반환 타입이 Optional
```
// Optional<U> flatMap(Function<? super T, ? extends Optional<? extends U>> mapper)

Optional<Member> memOpt = ...;
Optional<LocalDate> birthOpt = memOpt.flatMap(mem -> Optional.ofNullable(mem.getBirthday()));
Optional<Integer> pdOpt = birthOpt.flatMap(birth -> calOptional(birth));
```

## FIlter
* 조건을 충족하면 값 그대로 반환 충족하지 못하면 빈 Optional을 반환
```
String str = ...;

if (str != null && str.length() > 3) {
    System.out.println(str);
}
```

위의 코드를 아래처럼 Filter를 이용하여 작성이 가능하다.

```
Optional<String> strOpt = ...;
Optional<String> filtered = strOpt.filter(str -> str.length() > 3);

filtered.ifPresent(str -> System.out.println(str));
```

위의 코드를 더욱 간결하게 작성할 수 있다.

```
Optional<String> strOpt = ...;
Optional<String> filtered = strOpt.filter(str -> str.length() > 3).ifPresent(str -> System.out.println(str));
```

## 두 개의 Optional의 조합 - 1
```
Member m = ...;
if (m == null) return null;

Company c = getCompany(m);
if (c == null) return null;

Card card = createCard(m, c);
return card;
```

위의 코드를 아래와 같이 Map과 flatMap을 이용하여 작성할 수 있다.

```
Optional<Member> memOpt = ...;
Optional<Company> compOpt = memOpt.map(mem -> getCompany(mem));

Optional<Card> card = memOpt.flatMap(
    mem -> compOpt.map(
        comp -> createCard(mem, comp)
    )
);
```

위의 코드를 더 간결하게 작성할 수 있다.

```
Optional<Member> memOpt = ...;

Optional<Card> cardOpt = memOpt.flatMap(mem -> {
    Optional<Company> compOpt = getCompanyOptional(mem);

    return compOpt.map(comp -> createCard(mem, comp));
});
```

## 두 개의 Optional의 조합 - 2
```
Member m1 = ...;
Member m2 = ...;

if (m1 == null && m2 == null) return null;

if (m1 == null) return m2;
if (m2 == null) return m1;

return m1.year > m2.year ? m1 : m2;
```

```
Optional<Member> mem1Opt = ...;
Optional<Member> mem2Opt = ...;

// m1이 있으면 ... 반환. map의 결과가 없으면 m2를 사용
Optional<Member> result = mem1Opt.flatMap(m1 -> ...).or(() -> mem2Opt)
```

위의 코드를 더욱 상세하게 작성한다면 아래와 같다.

```
Optional<Member> result = mem1Opt.flatMap(m1 -> { // m1이 있으면
    return mem2Opt.map(m2 -> { // m2가 있으면
        return m1.year > m2.year ? m1 : m2
    }).orElse(m1); // m2가 없으면 m1을 반환
})
.or(() -> m2); // flatMap 결과가 없으면 m2를 반환

return result.orElse(null);
```

## 정리
* isPresent()를 사용하면 -> null 사용할 때 유사한 구조를 만들 수 있다.
* 대신 Map, flatMap, Filter, orElse, Or, ifPresent 등 익숙해지는 것이 중요하다.
    * Optional을 조금 더 Optional하게 사용하자!

[자바 Optional 기초](https://www.youtube.com/watch?v=RsUTolCVm_E)