# JPA, Flush() 영속 컨텍스트 반영하기
JPA를 사용하다보면 JPA, SQL 코드를 동시에 사용해야 할 경우가 있다. 그러나 JPA에서 데이터를 저장하고 해당 데이터를 쿼리에서 사용해야 하는 경우가 있다. 이 때, 데이터가 없는 문제가 발생할 수 있다.   

```
@Transactional
public void order(OrderRequest req) {
    ...

    Order order = ...;
    repository.save(order);
    callPostOrderProcedure(order.getId()); // 프로시저에서 데이터 조회 실패
}
```

DB 프로시저를 호출하는 코드라고 했을 때, 주문 ID에 해당하는 데이터를 프로시저가 결정하지 않는 문제가 발생한다. 이런 문제가 발생하는 이유는 JPA가 쿼리를 실행하는 시점에 대한 문제이기 때문이다.   

즉, 트랜잭션을 커밋하는 시점에 쿼리를 실행하는데 

```
@Transactional
public void order(OrderRequest req) {
    ...

    Order order = ...;
    repository.save(order);
    callPostOrderProcedure(order.getId());
}
```

스프링의 경우 @Transactional이 붙은 메소드 <b>실행 종료 시점</b>에 커밋을 한다. 즉, order() 메소드 실행 종료 시점에 커밋을 하는 것이다. JPA도 order() 메소드 실행 종료 시점에 INSERT 쿼리를 실행한다. 여기서 문제는 DB 프로시저를 실행하는 시점은 <b>INSERT 쿼리 실행 이전</b>에 발생한다는 것이다. 그래서 ```데이터가 없다```라는 문제가 발생하게 되는 것이다.   

## 문제 해결
이 문제를 해결할 수 있는 방법은 ```Flush()```를 사용하는 것이다.   

<b>Flush()</b>는 영속 컨텍스트 데이터를 미리 반영한다. 이는 JPA와 SQL을 함께 사용할 때 필요하다.   

Flush() 메소드를 사용할 때는 ```EntityManger#flush()```처럼 사용하는데 스프링 데이터 JPA의 Repository에 flush() 메소드를 추가해서 사용할 수 있다.   

```
@Transactional
public void order(OrderRequest req) {
    ...

    Order order = ...;
    repository.save(order);
    repository.flush(); // INSERT 쿼리 실행
    callPostOrderProcedure(...); // SELECT 쿼리에서 조회
}
```