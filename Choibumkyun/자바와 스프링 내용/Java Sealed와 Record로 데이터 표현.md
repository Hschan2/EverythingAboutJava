# 자바, Sealed와 Record로 데이터 표현
* Sealed: 하위 타입을 제한
* Record: 불변 (Immutable)
* ADT (Algebraic Data Types): 대수 데이터 타입, 다른 타입을 좋바해 만든 타입    

<b>Sealed</b>과 <b>Record</b>을 함께 사용하는 것이 <b>ADT</b>이다.   

## ADT의 곱셈(Product) 탕비과 합(Sum) 타입
* 곱셈 타입   
곱셈 타입은 여러 필드로 구성된 값을 가진다. 예를 들어서 ('A', 10, "CODE"), ('Z', 0, "ZERO")로 사용할 수 있다. 곱셈 타입의 전체 값은 각 필드가 가질 수 있는 값의 조합을 말한다. 그리고 이는 자바의 Record 타입이다.   

* 합 타입   
합 타입은 여러 Variant로 구성되어 있다. (OP: PLUS | MINUS) 합 타입의 값은 Variant 중 하나이다. 그리고 이는 자바의 Sealed, Enum 타입이다.   

## 곱셈 타입과 합 타입의 조합은 데이터 표현
예를 들어서
```
Opt: Some(value) | Empty

Opt: 합 타입으로 표현
Some, Empty: 곱 타입으로 표현
```

```
Option: Input | Output | Encoding
Encoding: Aes(KeyLength) | Sha(KeyLength)
```

## Sealed와 Record를 조합한 예시
<b>Sealed</b>는 하위 타입을 제한하기 때문에 합 타입으로 표현한다. 반대로 값을 가지고 있는 <b>Record</b>는 곱셈 타입으로 표현할 수 있다.

```
public sealed interface Opt<T> {
    record Some<T>(T value) implements Opt<T> {

    }

    final class Empty<T> implements Opt<T> {

    }
}
```

```
public sealed interface Option permits Input, Output, Encoding {

}

public record Input(Path path) implements Option {}
public record Output(Path path) implements Option {}
public sealed interface Encoding extends Option {
    record Sha(int keyLength) implements Encoding {}
    record Aes(int keyLength) implements Encoding {}
}
```

## 패턴 매칭
Sealed와 패턴을 매칭할 수 있다.   

```
instanceof을 이용한 패턴 매칭

public static <T, R> Opt<R> map(Opt<T> opt, Function<T, R> mapper) {
    if (opt instanceof Opt.Some<T> s) {
        return new Opt.Some(s.value());
    } else {
        return new Opt.Empty<>();
    }
}
```

```
Opt<String> opt1 = new Opt.Some<>("1");
Opt<Integer> opt2 = map(opt1, str -> Integer.parseInt(str));

System.out.println(opt2);
```

그리고 Case를 활용한 패턴 매칭이 가능하다. 그러나 이는 현재 Preview 형태로만 가능하다.   

```
public sealed interface Opt<T> {
    default <R> Opt<R> map(Function<T, R> mapper) {
        return switch (this) {
            case Opt.Some<T> s -> new Opt.Some<>(mapper.apply(s.value()));
            case Opt.Empty e -> new Opt.Empty<>();
        }
    }
}

record Some<T>(T value) implements Opt<T> {}
final class Empty<T> implements Opt<T> {}
```

```
Opt<String> opt1 = new Opt.Some<>("1");
Opt<Integer> opt2 = opt1.map(Integer::parseInt);

System.out.println(opt2);
```

## 정리
<b>Sealed</b>와 <b>Record</b>를 조합한 패턴 매칭은 데이터를 데이터로 모델링을 할 수 있다. 이는 불변 데이터(Record)로 모델링에 좋은 작업이고 데이터를 다루는 로직을 별도 함수(메소드)로 분리할 수 있다.   

이는 OOP와 조합해서 사용과는 다른 방식이다. 위의 방식으로 데이터와 데이터로 모델링하는 방식을 조합해서 적재적소에 사용하면 코드가 더 좋아질 수 있다.   

[Sealed와 Record로 데이터 표현](https://www.youtube.com/watch?v=aK9JVg2_k_Q)