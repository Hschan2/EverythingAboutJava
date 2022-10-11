# @Transactional
<b>트랜잭션</b>은 데이터 베이스에 모두 반영되거나 모두 반영되지 않은 단위를 말한다.   

요청할 때는 예를 들어 게임방과 게임 요소들이 있을 경우, 개별로 요청할 것이 아니라 한 번에 모두 요청을 해야 하는 것이 중요하다. 이유는 커넥션으로 데이터를 요청하는데 이는 하나의 세션으로 처리하는데 이를 별개로 인식하기 때문이다.   

그래서 요청이 즉시 반영은 하지 않도록 처음에 설정하고 게임방과 요소들을 생성해달라는 요청을 하고 모두 처리되었을 때 반영되도록 해야 한다.

```
@Override
public GameCreateResponse create(GameCreateRequest gameCreateRequest) {
    gameCreateRequest.setRoomPassword(passwordEncoder.encode(gameCreateRequest.getRoomPassword()));
    Room room = roomRepository.save(gameCreateRequest);
    
    boardRepository.save(connection, room.getId());

    return new GameCreateResponse(room.getId());
}
```

위 처럼 게임방, 요소들을 따로 요청하는 것처럼 작성하는 것이 아니라 아래처럼 필요한 데이터. 게임방, 게임 요소들을 한 번에 요청하되 즉시 반영되도록 하는 것이 아니라 요청이 모두 완료가 되면 반영되도록 하고 오류가 생기면 모두 되돌리는 방식으로 작성해야 한다.

```
@Override
public GameCreateResponse create(GameCreateRequest gameCreateRequest) throw SQLException {
    Connection connection = dataSource.getConnection();
    connection.setAutoCommit(false);

    try {
        gameCreateRequest.setRoomPassword(passwordEncoder.encode(gameCreateRequest.getRoomPassword()));
        Room room = roomRepository.save(connection, gameCreateRequest);
        boardRepository.save(connection, room.getId());
        GameCreateResponse gameCreateResponse = new GameCreateResponse(room.getId());

        connection.commit();

        return gameCreateResponse;
    } catch (Exception e) {
        connection.rollback();
        throw new ChessException();
    }
}
```

그러나 이렇게 매 번 작성한다면 코드 작성에 실수가 발생할 수 있다. 그리고 중복 코드가 늘어나게 된다. 또한, 주된 관심사가 아닌 코드가 서비스 레이어에 반영이 되고 특정 기술에 종속적인 코드가 된다.   

## AOP 활용
트랜잭션이 시작하고 ```관심사 로직 수행```을 진행하고 커밋하거나 롤백하는 흐름으로 작성하게 된다.   

이 때, 프록시 객체로 생성해서 사용하게 된다면 위의 문제들에 대해 보완이 가능하다.   

컨트롤러에 의존하고 타겟 소스를 상속받아 재정의한 서비스(프록시)은 부모 타입으로 주입이 가능하다. (컨트롤러 -> 서비스(프록시) -> 타겟 소스)   

트랜잭션을 활용해 타겟 메서드를 실행하기 위한 트랜잭션의 예시를 통해 확인할 수 있다.   

```
PlatformTransactionManager ptm = asPlatformTransactionManager(tm);
final String joinPointIdentification = methodIdentification(method, targetClass, txAttr);

if (txAttr == null || !(ptm instanceof CallbackPreferringPlatformTransactionManger)) {
    // getTransaction과 커밋, 롤백을 요청하는 표준 트랜잭션 경계 선언
    TransactionInfo txInfo = createTransactionIfNecessary(ptm, txAttr, joinPointIdentification);

    Object retVal;

    try {
        // 다음 인터셉터 호출
        // 호출된 타겟 오브젝트에서의 결과 값
        retVal = invocation.proceedWithInvocation();
    } catch (Throwable ex) {
        ...
    } finally {
        ...
    }

    if (retVal != null && vavrPresent && VavrDelegate.isVavrTry(retVal)) {
        ...
    }

    commitTransactionAfterReturning(txInfo);
    return retVal;
}
```

이는 트랜잭션이 시작되고 타겟 메서드를 실행하고 실행이 완료가 되면 트랜잭션이 종료된다.   

그리고 트랙잭션을 서비스에 적용해서 사용할 수 있게 된다.

```
@Transactional
@Service
public ChessService {
    ...
}
```

아래와 같은 코드를 ```선언적 트랜잭션 관리```라고 한다.

```
@Override
public GameCreateResponse create(GameCreateRequest gameCreateRequest) {
    gameCreateRequest.setRoomPassword(passwordEncoder.encode(gameCreateRequest.getRoomPassword()));
    Room room = roomRepository.save(gameCreateRequest);
    
    boardRepository.save(connection, room.getId());
    return new GameCreateResponse(room.getId());
}
```

그리고 아래와 같이 코드를 직접 작성한 것을 ```프로그래밍 방식 트랜잭션 관리```라고 한다.

```
@Override
public GameCreateResponse create(GameCreateRequest gameCreateRequest) throw SQLException {
    Connection connection = dataSource.getConnection();
    connection.setAutoCommit(false);

    try {
        gameCreateRequest.setRoomPassword(passwordEncoder.encode(gameCreateRequest.getRoomPassword()));
        Room room = roomRepository.save(connection, gameCreateRequest);
        boardRepository.save(connection, room.getId());
        GameCreateResponse gameCreateResponse = new GameCreateResponse(room.getId());

        connection.commit();

        return gameCreateResponse;
    } catch (Exception e) {
        connection.rollback();
        throw new ChessException();
    }
}
```

```@Transactional```을 적용한 상태에서 사용하게 된다면 <b>선언적 트랜잭션 관리</b> 코드로도 대부분 충분하다고 권장된다.   

## 요약
* Bean 생성 시, ```@Transactional```이 있으면 프록시 객체가 Bean으로 등록
* 스프링이 제공하는 선언적 트랜잭션 관리를 통해서 서비스 레이어에 트랜잭션과 관련된 코드 혹은 특정 기술에 종속된 코드를 분리
* 트랜잭션 매니저, 트랜잭션 동기화 매니저, AOP, JDK Dynamic Proxy, CGLIB에 대해 추가적으로 알아둘 것   

[@Transactional](https://www.youtube.com/watch?v=taAp_u83MwA)