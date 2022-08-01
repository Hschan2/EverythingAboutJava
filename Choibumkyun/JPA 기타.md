# JPA, 기타 내용

## Count Method
* long count()
* long countByNameLike(String keyword)
* long count(Specification<User> spec)

## @Query Native Query
* @Query Annotation
    * JPQL이 아닌 SQL을 실행한다.

```
@Query(value = "SELECT * FROM user u WHERE u.create_date >= date_sub(now(), interval 1 day)", nativeQuery = true)
List<User> findRecentUsers();

@Query(value = "SELECT MAX(create_date) from user", nativeQuery = true)
LocalDateTime selectLastCreateDate();
```

```nativeQuery = true```를 명시함으로써 네이티브 쿼리를 설정한다.

## 한 개 결과 조회하기
* User findByName(String name)
* Optional<User> findByName(String name)   

리턴 타입이 <b>List</b>가 아니다. 존재하면 해당 값을 반환하고 없으면 <b>Null</b> 혹은 <b>빈 Optional</b>을 반환한다. 그리고 조회 결과 개수가 두 개 이상이면 예외처리를 한다.   

## 인터페이스
* Repository 하위 인터페이스 사용하기   

```
@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    Optional<T> findById(ID id);

    ...
}

@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    Optional<T> findById(ID id);

    ...
}

@NoRepositoryBean
public interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {
    Iterable<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
}

@NoRepositoryBean
public interface JpaRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleEx {
    List<T> findAll();
    List<T> findAll(Sort sort);

    ...
}
```

## 인터페이스 상속은 편리한가?
* Repository 하위 인터페이스를 상속하면 관련 메소드 모두 포함이 된다.
    * 메소드를 따로 추가해줄 필요가 없다는 것이다.

```
public interface UserRepository extends JpaRepository<User, String> {
    메소드를 정의하지 않아도 CrudRepository, JpaRepository에 있는 save(), findById(), findAll() 등 메소드가 제공된다.
}
```

최범균 개발자는 Repository를 상속 받고 딱 필요한 메소드만 만드는 방법을 선호한다.   

## 정리
* Spring Data JPA로 편한 Repository 구현이 가능하다.
* Spring Data JPA로만 만드는 것은 하지 말 것을 추천한다.
    * MyBatis, JDBCTemplete 등을 적절히 사용하는 것이 좋다. (CQRS)   

[JPA 기타](https://www.youtube.com/watch?v=SYqknEb0wag)