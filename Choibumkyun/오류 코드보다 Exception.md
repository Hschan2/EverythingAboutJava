# 오류 코드보다 Exception

## 함수가 에러 코드를 Return 한다면
코드가 중첩이 되거나 반복되는 구조를 갖게 될 것이다. 그러면 로직이 복잡해지고 길어진다. 그래서 에러를 바로 처리하는 것이 좋다. 에러 처리 로직과 정상 로직이 섞일 수 있다.   

```
if (registAccount(acc) == OK) {
    if (appendHistory(acc) == OK) {
        logger.info(...);
    } else {
        logger.error(...);
        return ERROR;
    }
} else {
    logger.error(...);
    return ERROR;
}
```
=>
```
if (registAccount(acc) == OK) {
    logger.error(...);
    return ERROR;
}

if (appendHistory(acc) == OK) {
    logger.error(...);
    return ERROR;
}

logger.info(...);
```

## Exception을 사용한다면
정상 처리 로직과 에러 처리 코드를 분리할 수 있다. 이는 코드 구조를 단순화할 수 있다. 그리고 <b>try ... catch</b> 블록을 메서드로 추출하면 각 메서드는 각각 정상과 에러 처리에 집중할 수 있다.   

```
try {
    registAccount(acc);
    appendHistory(acc);
    logger.info(...);
} catch(Exception e) {
    logger.error(...);
}
```
=>
```
try {
    registAccountAndAppendHistory(acc);
} catch(Exception e) {
    logError(e)
}
```

## 그러나 Golang은 다르다.
Go 언어는 실패 값을 Return한다. 그리고 스택 트레이스 단점이 존재한다. 코드가 길어지면 이해하는 데에 시간이 많이 필요하고 컨텍스트 걸여가 발생한다. 그리고 오류를 명시적으로 처리하게 유도해야 한다.   

```
err = json.Unmarshal(...)
if err != nil {
    return nil, fmt/Errorf(...);
}
```

## 함수형
Exception은 사이드 이펙트이다. 순수 함수는 에러를 값으로 표현하고 Either 같은 타입을 사용한다.   

```
Either<Error, Some> body =
    read(...)
        .map(bs -> toStr(bs))
        .map(str ->
            unmarshall(str, Some.class)
        );
```

## 정리
Exception만으로 구현하는 것은 정답이 아니다. 구현 기술과 팀 구성원, 구현 기능 등을 고려하여 상황에 맞게 선택해야 한다. 예를 들어서 스프링의 @Transactional 애노테이션과 같이 상황을 고려하여 사용하는 것이 좋다.   

[클린코드 - 오류 코드보다 익셉션](https://www.youtube.com/watch?v=qMZN1G0MwMs)