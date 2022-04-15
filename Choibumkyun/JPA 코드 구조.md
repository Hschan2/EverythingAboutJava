# 코드 구조
```
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabegin");

...

emf.close();
```
첫 번째 코드는 persistence.xml 파일에 정의한 영속 단위 기준으로 초기화한다. (<persistence-unit name="jpabegin">) 그리고 필요한 자원을 생성한다.   

마지막 코드는 팩토리를 닫고 사용한 자원을 반환한다.   

그리고 EntityManager로 데이터 베이스를 연동한다.
```
EntityManager entityManager = emf.createEntityManager();
EntityTransaction transaction = entityManager.getTransaction();

try {
    transaction.begin();
    
    ...entityManager로 데이터 베이스 작업

    transaction.commit();
} catch(Exception e) {
    e.printStackTrace();
    transaction.rollback();
} finally {
    entityManager.close();
}
```

EntityManager를 생성하고 EntityTransaction을 구한다. 그리고 try/catch 구문으로 트랜잭션을 시작하고 데이버 베이스 작업을 시작한다. 그리고 트랜잭션을 커밋한다. 만약 에러가 발생한다면 트랜잭션을 롤백한다. 마지막으로 EntityManager를 닫는다.   

쿼리를 <b>저장</b>하고 실행하는 시점은 다음과 같다.   
```
transaction.begin();
User user = new User("user@user.com", "user", LocalDateTime.now());
entityManager.persist(user);

logger.info("EntityManager.persist 호출");

transaction.commit();

logger.info("EntityTransaction.commit 호출");
```

persist 시점에 쿼리가 실행될 것이라고 파악할 수 있지만 실제로는 commit되는 시점에 실행을 하게 된다.   

그리고 쿼리를 <b>수정</b>하고 실행하는 시점이다.   
```
transaction.begin();
User user = entityManager.find(User.class, "user@user.com");
if (user == null) {
    System.out.println("User 존재하지 않음");
} else {
    String newName = "이름" + (System.currentTimeMilles() %s 100);
    user.changeName(newName);

    logger.info("User.changeName 호출");
}
transaction.commit();

logger.info("EntityTransaction.commit 호출");
```
우선 find 메소드로 select 쿼리로 데이터를 찾는다. 그리고 첫 번째 Logger가 호출되고 그 다음에 데이터가 수정된다.   

이런 일이 발생하는 이유는 <b>영속 컨텍스트 (Persistence Context)</b> 때문이다. 저장하거나 읽어온 매핑된 객체는 바로 데이터 베이스에 저장하는 것이 아닌 영속 컨텍스트에 보관이 되었다가 커밋하는 시점에 데이터 베이스에 반영을 한다.   

* 영속 컨텍스트
    * EntityManager 단위로 영속 컨텍스트 관리
    * 커밋 시점에 영속 컨텍스트의 변경 내역을 데이터 베이스에 반영 (변경 쿼리 실행)   

## 정리
* 기본 구조
    * EntityManagerFactory 초기화 진행
    * 데이터 베이스 작업이 필요할 때마다
        * EntityManager 생성
        * EntityManager를 이용해 데이터 베이스 조작
        * EntityTransaction으로 트랜잭션 관리
    * 스프링과 연동할 때
        * 대부분 스프링이 대신 처리하기 때문에 매핑 설정 중심으로 작업
* 영속 컨텍스트
    * Entity를 메모리에 우선 보관
    * 변경(추가, 수정 등)을 추적해서 트랜잭션 커밋 시점에 데이터 베이스에 반영   

[JPA 코드 구조](https://www.youtube.com/watch?v=7ljqL8ThUts)