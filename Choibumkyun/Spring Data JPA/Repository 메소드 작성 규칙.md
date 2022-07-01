# Spring Data JPA Repository 메소드 작성 규칙

## 이전 내용
Repository 인터페이스를 상속하고 정해진 규칙에 맞게 메소드를 사용하면 된다.

## 식별자로 엔티티 조회하기
* findById
    * T findById(ID id), 만약 없다면 Null을 되돌려준다.
    * Optional<T> findById(ID id), 만약 없다면 empty Optional을 되돌려준다.

```
ID - String 타입
User - Entity 타입

public interface UserRepository extends Repository<User, String> {
    Optional<User> findById(String email);
}
```

## 엔티티 삭제하기
* Delete
    * void delete(T entity)
    * void deleteById(ID id), 내부적으로 findById()로 엔티티를 조회하고 나서 delete()로 삭제한다. 즉, 실질적으로 위와 같은 역할을 수행한다.
* 삭제할 대상이 존재하지 않으면 에러 발생하도록 한다.

```
public interface UserRepository extends Repository<User, String> {
    Optional<User> findById(String email);
    
    void delete(User user);
}
```

```
Optional<User> userOpt = userRepository.findById("email@email.com");

//User가 있다면
userOpt.ifPresent(user -> {
    userRepository.delete(user);
});
```

## 엔티티 저장 메소드
* Save
    * void save(T entity)
    * T save(T entity)

```
public interface UserRepository extends Repository<User, String> {
    User save(User user); // 혹은 void save(User user);
}
```

```
User savedUser = userRepository.save(new User(...));

User user = new User(...);
userRepository.save(user);
```

그러나 save() 호출 시 실행 쿼리를 보게 되면 Select 쿼리가 실행이 되고 Insert 쿼리가 실행이 된다. 이는 JPA이 제공하는 Repository의 구현 때문이다.   

### save() 동작 방식
* 새 엔티티면 EntityManager#persist()가 실행이 된다.
* 새 엔티티가 아니라면 EntityManager#merge()가 실행이 된다.
* 새 엔티티인지 판단하는 기준은 다음과 같다.
    * Persistable을 구현한 엔티티, isNeew()로 판단한다.
    * @Version 속성이 있는 경우, 버전 값이 Null이면 새 엔티티로 판단한다.
    * 식별자가 참조 타입이면 식별자가 Null이면 새 엔티티로 판단한다.
    * 식별자가 숫자 타입이면 0이면 새 엔티티로 판단한다.

```
public <S extends T> S save(S entity) {
    Assert.notNull(entity, "Entity must not be null.");

    if (this.entityInformation.isNew(entity)) {
        this.em.persist(entity);
        return entity;
    } else {
        return this.em.merge(entity);
    }
}
```

그래서 만약 ```userRepository.save(new User("a@a.com", ...))```를 실행하면 <b>a@a.com</b>은 참조타입이지만 Null이 아니기 때문에 새 엔티티로 판단하지 않는다. 그래서 merge를 실행하여 Select 쿼리로 기존 유저를 확인하고 Insert 쿼리를 실행한다.   

만약 이를 원하지 않는 경우 Persistable 구현을 알아봐야 한다.   

```
@Entity
@Table(name="user")
public class User implements Persistable<String> {
    
    @Id
    private String email;

    ...

    @Transient
    private boolean isNew = true;

    @Override
    public String getId() {
        return email;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PrePersist
    void markNotNew() {
        this.isNew = false;
    }
}
```

```Persistable 인터페이스```를 직접 구현해서 Select 쿼리가 실행되지 않도록 구현할 수 있다.   

## 특정 조건으로 찾기
* findBy프로퍼티(값), 프로퍼티가 특정 값인 대상을 찾는다.
    * List<User> findByName(String name);
    * List<Hotel> findByGradeAndName(Grade grade, String name);
* 조건 비교하기
    * List<User> findByNameLike(String keyword);
    * List<User> findByCreatedAtAfter(LocalDateTime time);
    * List<Hotel> findByYearBetween(int from, int to);
    * LessThan, isNull, Containing, In 등 스프링 레퍼런스 문서에 참고하여 더욱 알아볼 수 있다.
* findAll(), 모두 조회할 수 있다.

## 정리
정해진 규칙에 따라 인터페이스만 작성하면 구현이 가능하다. 그러나 ```findBy 메소드```를 남용하면 안 된다. findBy 메소드가 많아지면 복잡해지고 쉽게 파악하기 어려워진다. 그리고 검색 조건이 단순하지 않으면 ```@Query```, ```SQL```, ```스펙/QueryDSL```을 사용하는 것이 좋다.   

[Repository 메소드 작성 규칙](https://www.youtube.com/watch?v=qTiHaxVc6GY)