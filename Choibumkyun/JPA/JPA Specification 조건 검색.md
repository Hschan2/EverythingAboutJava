# JPA, Specification을 이용한 검색 조건 지정하기

## Specification

<b>Specification</b>은 ```검색 조건을 생성하는 인터페이스```를 말한다. 이는 <b>Criteria</b>를 이용해 검색 조건을 생성해 사용한다.   

```
public interface Specification<T> extends Serializable {

    @Nullable
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);
}
```

그리고 <b>Repository</b>는 Specification을 이용한 검색 조건을 지정할 수 있다. (List<T> findAll(Specification<T> spec))   

```
public interface userRepository extends Repository<User, String> {

    List<User> findAll(Specification<User> spec);
}
```

<b>spec</b>은 검색 조건을 설정하는 인터페이스로 정한다.   

## Specification 구현과 사용

구현하는 예는 다음과 같다.   

```
public class UserNameSpecification implements Specification<User> {

    private final String value;

    public UserNameSpecification(String value) {
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.like(root.get("name"), "%" + value + "%");
    }
}
```

```
UserNameSpecification spec = new UserNameSpecification("이름"); // 이름을 포함한 모든 객체 찾기

List<User> users = userRepository.findAll(spec);
```

## 람다로 간결하게 구현하기

<b>Specification</b>을 구현한 클래스를 매번 생성하기 보다 람다식을 이용해 스펙을 생성한다.   

```
public class UserSpecs {
    public static Specification<User> nameLike(String value) {
        return (root, query, cb) -> cb.like(root.get("name"), "%" + value + "%");
    }
}
```

```
UserNameSpecification spec = UserSpecs.nameLike("이름");

List<User> users = userRepository.findAll(spec);
```

## 검색 조건 조합하기

<b>Specification</b>의 ```or```, ```and``` 메소드로 이용해서 조합이 가능하다.   

```
Specification<User> nameSpec = UserSpecs.nameLike("이름1");
Specification<User> afterSpec = UserSpecs.createdAfter(LocalDateTime.now().minusHours(1));
Specification<User> compositeSpec = UserSpecs.and(afterSpec);

List<User> user2 = userRepository.findAll(compositeSpec);
```

```
Specification<User> spec3 = UserSpecs.nameLike(keyword).and(UserSpecs.createdAfter(dateTime));
List<User> user3 = userRepository.findAll(spec3);
```

조합할 때, 선택적으로 조합할 수도 있다.   

```
Specification<User> spec = Specification.where(null);

if (keyword != null && !keyword.trim().isEmpty()) {
    spec = spec.and(UserSpecs.nameLike(keyword));
}
if (dateTime != null) {
    spec = spec.and(UserSpecs.createdAfter(dateTime));
}

List<User> users = userRepository.findAll(spec);
```

<b>Specification</b>의 ```where 메소드```로 before 값을 준다. 그리고 조건을 주어 원하는 값을 저장할 수 있다.   

## Specification로 페이징, 정렬하기
* List<User> findAll(Specification<T> spec, Sort s)
* Page<User> findAll(Specification<T> spec, Pageable p)
* List<User> findAll(Specification<T> spec, Pageable p)

## SpecBuilder
```if 절```을 덜 사용하기 위해 <b>SpecBuilder</b>으로 구현할 수 있다.   

```
Specification<User> specs = SpecBuilder.builder(User.class)
    .ifHasText(keyword, str -> UserSpecs.nameLike(str))
    .ifNotNull(dt, value -> UserSpecs.createdAfter(value))
    .toSpec();
```

```
Specification<User> spec = Specification.where(null);

if (keyword != null && !keyword.trim().isEmpty()) {
    spec = spec.and(UserSpecs.nameLike(keyword));
}
if (dateTime != null) {
    spec = spec.and(UserSpecs.createdAfter(dateTime));
}
```

## 정리
* Specification 인터페이스를 이용해 검색 조건을 생성할 수 있다.
* Specification의 and(), or() 메소드로 검색 조건을 조합할 수 있다.   

[Specification을 이용한 검색 조건 지정](https://www.youtube.com/watch?v=-znDGy-BQJk)
