# JPA, 영속 컨텍스트 & 라이프 사이클

## 영속 객체와 영속 컨텍스트
<B>영속 (Persistent)</B>는 엔티티, 객체를 말한다. 데이터 베이스 데이터에 매핑되는 메모리상의 객체를 가리킨다. 컨텍스트에 저장이 되어야 영속 객체가 된다.   

<b>영속 컨텍스트</b>는 일종의 메모리 저장소이다. EntityManager가 관리할 엔티티 객체를 보관한다. (엔티티 타입, 식별자)를 키로 하는 맵으로 저장을 하며 엔티티 객체를 가리킨다.   

```EntityManager.close()```는 영속 컨텍스트를 제거하는 코드이다.   

그러나, 배치 처리는 하지 않는다.   

## 영속 컨텍스트와 캐시
동일한 식별자는 동일 객체이다. 영속 컨텍스트에 보관된 객체를 조회할 수 있다.   

<b>Repeatable Read 효과</b>가 존재한다.   

```
logger.info("first find");
User first = em.find(User.class, email); // 조회
logger.info("second find");
User second = em.find(User.class, email);
logger.info("same object: {}", (first == second));
```

=>

```
first find
SELECT u1_0.email, ... FROM user u1_0 WHERE u1_0.email=?

second find
SELECT 쿼리를 실행하지 않는다.

same object: true
```

두 번째 SELECT 쿼리를 실행하지 않는 이유는 이미 첫 번째 조회 쿼리를 실행했을 때, 객체를 실행하고 영속 컨텍스트에 보관을 하기 때문에 동일한 식별자를 찾을 때는 저장된 객체를 Return한다.   

## 영속 객체 라이프 사이클
![영속 객체 라이프사이클](./images/context.PNG)

객체를 생성하여 저장을 하면 이는 관리됨 (managed) 상태가 된다. 관리됨 상태는 영속 컨텍스트를 통해 관리, 변경 추적하는 것을 말한다. 만약 영속 컨텍스트를 종료하면(강제 연결 끊기 포함) 변경 추적을 더 이상 하지 않는다.   

## 영속 컨텍스트와 변경 추적
```
EntityManager em = EMF.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
    tx.begin();
    User u = em.find(User.class, email);
    u.changeName("abc");
    tx.commit(); // 커밋 시점에 변경 반영
} catch (Exception e) {
    tx.rollback();
    throw e;
} finally {
    em.close();
}
```

=>

```
commit 할 때

UPDATE user SET create_date=?, name=?, WHERE email=?
```

```조회(find)```나 ```persist()```를 통해 영속 컨텍스트에 저장된 객체는 관리 대상이 된다.   

## 분리됨 상태는 변경 추적 대상이 아니다.
```
EntityManager em = EMF.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
    tx.begin();
    User u = em.find(User.class, email);
    tx.commit();
} catch (Exception e) {
    tx.rollback();
    throw e;
} finally {
    em.close(); // 연결 해제 = 분리됨 상태로 전환
}

u.changeName("abc"); // u는 분리됨 상태로 변경 추적 대상에서 제외
```

```
EntityManager em = EMF.createEntityManager();
EntityTransaction tx = em.getTransaction();

try {
    tx.begin();
    User u = em.find(User.class, email);
    em.detach(u); // 강제로 분리됨 상태로 전환, u는 분리됨 상태로 변경 추적 대상에서 제외
    u.changeName("abc");
    tx.commit();
} catch (Exception e) {
    tx.rollback();
    throw e;
} finally {
    em.close();
}
```

```detach()```와 반대로 ```em.merge()```는 분리됨 상태 객체를 관리됨 상태로 변경한다.   

## 정리
JPA는 영속 엔티티(객체)를 영속 컨텍스트에 담아서 변경을 추적한다. 변경 내용은 이 후 트랜잭션 커밋 시점에 변경을 반영한다.   

그리고 대량의 변경은 굳이 JPA로 할 필요가 없다. 이는 직접 쿼리로 실행하는 것이 효율적인 방법이 될 수 있다.   

분리됨 상태는 변경을 추적하지 않는다.   

[영속 컨텍스트 & 라이프 사이클](https://www.youtube.com/watch?v=tUwg78VkWJ0)