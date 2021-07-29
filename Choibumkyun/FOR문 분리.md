# For문 분리

## 자주 볼 수 있는 For 문 루프 구조
```
Result res = ...

for (Item item : items) {
    Some s = doSome(item)
    res.apply(s);
}
```

=> 추가

```
Result res = ...

for (Item item : items) {
    Some s = doSome(item)
    res.apply(s);
    doAny(item);
}
```

=> 추가

```
Result res = ...
OtherResult ores = ...

for (Item item : items) {
    Some s = doSome(item)
    res.apply(s);
    doAny(item);
    ores.apply(doOther(s));
}
```

## For 문 루프에서 여러 가지 일을 하려고 하면
* 서로 다른 목적을 가진 코드가 뒤섞일 수 있다.
    * 코드 복잡도가 증가
    * 코드 이해가 어려워짐
    * 코드 수정이 힘들어짐
* For 문 루프가 한 가지 일을 하도록 수정한다.   

## For 문에 들어가는 로직을 분리하면
* 코드 이해가 더 쉬워진다.
    * 논리적인 단위로 구분 가능
* 메서드 추출과 같은 리팩토링이 용이하다.
* 다른 로직 추가가 용이하다.
    * For 문 안에서 삽입할 위치를 찾지 않음

### 그렇다면 성능에는 어떠한 영향을 끼치나?
* 루프를 여러 번 돌게 된다면 문제가 없는가?
    * 지장이 없는 경우가 많다.
* 정말로 문제가 될 때만 개선한다.
* 복잡한 코드보다 이해하기 좋은 코드가 좋다.

[For 문 분리](https://www.youtube.com/watch?v=ZNDDy77WInY)