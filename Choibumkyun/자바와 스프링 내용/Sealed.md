# Sealed Type (자바 17버전)

## Sealed 클래스 & 인터페이스
* sealed
    * 상속할 수 있는 타입을 제한하는 것
    * Class 혹은 Interface를 Sealed Type으로 지정
* Permits
    * 허용하는 하위 타입 목록 지정
    * 하위 타입이 존재 필수
* Sealed와 Permits은 한 몸
    * 즉, Sealed Type은 하위 타입 필수

```
public sealed interface FList<T> permits Cons, Empty {

}

public final class Cons<T> implements FList<T> {
    private T head;
    private FList<T> tail;

    public Cons(T head, FList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    ...
}
```

## Sealed Type을 상속한 타입의 제약
* 다음 중 하나를 지정해야 한다.
    * final
    * sealed (+ permits)
    * non-sealed
* sealed 타입과 같은 패키이에 위치해야 한다.
    * 또는 같은 모듈에 위치해야 한다. (named module)

```
public sealed interface FList<T> permits Cons, Empty {
    
}

public final class Empty implements FList<T> {

}
```

## 같은 Java 파일에 위치한다면 permits은 생략
```
public sealed class Cons<T> implements FList<T> {
    private T head;
    private FList<T> tail;

    public Cons(T head, FList<T> tail) {
        this.head = head;
        this.tail = tail;
    }
}

final class SomeCons<T> extends Cons<T> {
    public SomeCons(T head, FList<T> tail) {
        super(head, tail);
    }
}
```

## Record 타입 지원
```
public sealed interface FList<T> permits Cons, Empty {

}

public record Cons<T>(T head, FList<T> tail) implements FList<T> {

}
```

## Switch에서 패턴 매칭 (프리뷰 (미리보기))
* 자바 17버전
    * preview 옵션
    ```
    return switch(flist) {
        case Cons c -> 1 + c.getTail().size();
        default -> 0;
    };
    ```
* 자바 16버전 패턴 매칭
```
if (a instanceof String t && t.isBlank()) {
    System.out.println("blank");
}
```

[최범균의 Sealed](https://www.youtube.com/watch?v=GJB-RyHKHjY)