### null 반환보다는 Optional을 활용하자

##### null 반환의 문제점

```
public static Integer findMinMultiple(List<Integer> numbers, int anyNumber) {
    Collections.sort(numbers);
    for (Integer number : numbers) {
        if (number % anyNumber == 0) {
            return number;
        }
    }
    return null; 
}
```

위 코드는 List<Integer> numbers에서 anyNumber 배수가 없으면 null을 반환한다. <br>
여러 상황에서 null을 반환할 때 다음의 문제가 발생한다. <br>

- NPE(NullPointerException)를 발생시킬 위험이 있다.
- NPE(NullPointerException) 방어를 위한 null 체크 로직이 필요하다.
- null 체크 로직 때문에 코드 가독성이 떨어진다.

```
Member member = findMember("tiger");
if (member != null) {
    Address address = member.getAddress();
    if (address != null) {
        String city = address.getCity();
        if (city != null) {
            return city;
        }
    }
}
```

위 처럼 방어적인 코드를 작성하기 위해 null을 사용하게 된다면 가독성이 떨어진다. <br><br>

이를 해결하기 위해 Optional를 사용하는 것이 좋다.

### Optional이란?
JAVA8부터 제공하는 null을 포함하거나 null을 포함하지 않을 수도 있는 객체<br><br>

null 대신 Optional을 반환하면 좋은 이유
- NPE(NullPointerException)를 발생시킬 수 있는 null을 직접 다루지 않아도 된다.
- null 체크 로직이 필요 없다.
- 명시적으로 해당 변수가 null일 가능성을 표현할 수 있다.
<br>

따라서 null 대신 Optional를 사용한 코드를 보면 아래처럼 사용할 수 있다.
```
public static OptionalInt findMinMultiple(List<Integer> numbers, int anyNumber) {
    Collections.sort(numbers);
    for (Integer number : numbers) { numbers 길이 만큼 반복
        if (number % anyNumber == 0) { 
            return OptionalInt.of(number);
        }
    }
    return OptionalInt.empty();
}
```
or
```
public static OptionalInt findMinMultiple(List<Integer> numbers, int anyNumber) {
    return numbers.stream()
        .sorted()
        .filter(number -> number % anyNumber == 0)
        .findFirst()
        .map(OptionalInt::of)
        .orElseGet(OptionalInt::empty);
}
```

### 결론
- 특별한 경우가 아니라면 null대신 Optional을 사용하는 것이 좋다. <br>
- 다만 Optional을 잘 사용해야 한다. <br>
- Optional은 값을 포장하고 다시 풀고, 값이 없을 때 대체하는 값을 넣는 등의 오버헤드가 있기 때문에 무분별하거나 적절하지 않게 사용된다면 성능 저하가 생긴다.
<br>
부적절한 사용 예로 Optional의 orElse가 있다.<br>
orElse(...)은 Optional에 값이 있으나 없으나 무조건 실행을 한다. 만약 값이 있다면 orElse의 인자로서 실행된 값은 무시되고 버려진다.<br>

사용 예를 들면
```
public static OptionalInt findMinMultiple(List<Integer> numbers, int anyNumber) {
    return numbers.stream()
        .sorted()
        .filter(number -> number % anyNumber == 0) number를 anyNumber로 나누어서 나머지 값이 0일 경우
        .findFirst()
        .map(OptionalInt::of)
        .orElse(OptionalInt.empty());
}
```

##### 이럴 경우 orElseGet 메소드를 사용하면 좋다.
사용 예를 들면
```
public static OptionalInt findMinMultiple(List<Integer> numbers, int anyNumber) {
    return numbers.stream()
        .sorted()
        .filter(number -> number % anyNumber == 0)
        .findFirst()
        .map(OptionalInt::of)
        .orElseGet(OptionalInt::empty);
}
```
