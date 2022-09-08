# 초보자가 흔히 실수하는 DB 코딩 세 가지

## 첫 번째 - 프라이머리 / 리플리카
* 실수 - 무조건 변경은 프라이머리, 읽기는 리플리카   

예시 코드.
```
@PostMapping("...")
public CompData handleUri(Req req) {
    someService.createData(req);
    return queryService.getData(req.getId());
}
```

```
@Transactional
public void createData(Req req) {
    Data data = toData(req);
    primaryDao.insert(data); // Dao에 값 추가
}
```

```
public CompData getData(Long id) {
    Data d = replicaDao.select(id); // 데이터 읽어오기
    Other o = otherReplicaDao.select(d.getOtherId());
    return CompData.from(data, o); // 결과 내보내기
}
```

* 증상
    * 간헐적으로 Null point error 발생
    * 앞에서 넣은 다음 조회하는데 이해가 가지 않음
* 원인
    * 프라이머리와 리플리카 간 데이터 동기화에는 지연이 있음 (실시간이 아닌 문제)
    * insert 쿼리는 프라이머리, select 쿼리는 리플리카에 요청
* 해결
    * 변경하고 바로 읽을 때는 프라이머리에서 읽기   
```
@PostMapping("...")
public CompData handleUri(Req req) {
    someService.createData(req);
    return primaryService.getData(req.getId());
}
```

```
@Transactional
public void createData(Req req) {
    Data data = toData(req);
    primaryDao.insert(data);
}
```

```
public CompData getData(Long id) {
    Data d = primaryDao.select(id);
    Other o = otherPrimaryDao.select(d.getOtherId());
    return CompData.from(data, o);
}
```

## 두 번째 - @Transactional과 try-catch
* 스프링 @Transactional 편리
    * 메서드 단위로 트랜잭션 범위 지정
    * Exception을 이용한 롤백 처리   
    ```
    @Transactional
    public void register(Req req) {
        anyRepository.save(toData(req));
        if (check()) {
            throw new RuntimeException("!");
        }
        Other o = otherRepository.findById(req.getOtherId());
        o.doSome();
    }
    ```
    * 중첩된 @Transactional과 try-catch
    * 아래 코드에서 성공하면 문제가 되지 않지만 에러가 발생하면 문제가 생긴다. 하나의 트랜잭션에 문제가 생겼을 때, 모두 롤백이 된다. 그 다음 트랜잭션은 이미 롤백된 상태에서 실행되는 것.   
    ```
    @Transactional
    public void registerMembers(List<Req> reqs) {
        reqs.forEach(req -> {
            try {
                registerService.register(req);
                logDao.insert(createSuccessLog(req, ex)); // 성공하면
            } catch(Exception ex) {
                logDao.insert(createFailLog(req, ex)); // 에러가 발생하면
            }
        })
    }
    ```
    * 다음 조건이면 확인이 필요
        * 메서드에 @Transactional 설정
        * 안에서 try-catch로 Exception을 처리하고 전파하지 않음
    * 확인 필요한 요소
        * 중첩된 @Transactional이 있는가?
        * 의도하지 않은 롤백 가능성은 없는가?
        * 의도한 롤백이 안 될 가능성은 없는가?   

## 세 번째 - this @@Transactional 메서드 호출
* 같은 객체의 @Transactional이 붙은 다른 메서드 호출
```
public void call(Req req) {
    if (req.getTarget() === 1) {
        call1(Req);
    } else {
        call2(req);
    }
}

@Transactional
public void call1(Req req) {

}

@Transactional
public void call2(Req req) {

}
```
@Transactional은 각각 붙어있고 콜 세머드 자체는 서로 붙어있지 않은 상태이다. 위 코드는 실제로 트랜잭션 범위에서 실행되지 않는다.

* 스프링 @Transactional은 프록시 기반 AOP로 구현
* @Transactional이 붙은 this 메서드를 호출하면 트랜잭션이 "적용이 필요한가?" "적용이 되는가?" 확인 필요. 즉 트랜잭션 범위여야 한다.   

## 정리
* 프라이머리 / 리플리카 간 동기화는 지연이 있음
* @Transactional 메서드에 안에서 try-catch 사용 주의 (원하지 않은 롤백, 롤백이 되지 않은 상황 발생 가능성)
* this의 @Transactional 메서드 호출할 때, 트랜잭션 적용을 확인할 것

[초보자가 흔히 하는 DB 코딩](https://www.youtube.com/watch?v=n85UzIReFjY)