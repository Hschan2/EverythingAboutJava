# JPA, 정렬과 페이징 그리고 @Query

## 정렬
* find 메소드에 OrderBy를 붙인다.
    * OrderBy 뒤에 프로퍼티 이름과 ASC 혹은 DESC를 사용한다.
    * 여러 프로퍼티 지정이 가능하다.

```
List<User> findByNameLikeOrderByNameDesc(String keyword);
=> order by u.name desc

List<User> findByNameLikeOrderByNameAsc(String keyword);
=> order by u.name asc

List<User> findByNameLikeOrderByNameAscEmailDesc(String keyword);
=> order by u.name asc, email desc
```

* Sort 타입 사용이 가능하다.
```
List<User> findByNameLike(String keyword, Sort sort);

Sort sort1 = Sort.by(Sort.Order.asc("name"));
List<User> users1 =  userRepository.findByNameLike("이름%", sort1);
=> order by u.name asc

Sort sort2 = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("email"));
List<User> users2 =  userRepository.findByNameLike("이름%", sort2);
=> order by u.name asc, email desc
```

## 페이징
* Pageable, PageRequest 사용이 가능하다.
```
List<User> findByNameLike(String keyword, Pageable pageable);
```

```
Pageable pageable = PageRequest.ofSize(10).withPage(1);
List<User> users3 =  userRepository.findByNameLike("이름%", pageable);
```

page는 0부터 시작하며 한 페이지에 10개 기준으로 두 번째 페이지 조회가 가능하도록 한다.

```
Sort sort3 = Sort.by(Sort.Order.asc("name"), Sort.Order.desc("email"));
Pageable pageable = PageRequest.ofSize(10).withPage(1).withSort(sort3);
List<User> users3 =  userRepository.findByNameLike("이름%", pageable);
```

페이징 조회 결과를 Page로 구하는 방법은 다음과 같다.   

* <b>Page 타입</b>은 페이징 처리에 필요한 값을 함께 제공한다.
    * 이는 전체 페이지 개수와 전체 개수 둥을 제공한다.
* Pageable을 사용하는 메소드의 리턴 타입을 Page로 지정하면 된다.

```
Page<User> findByNameLike(String keyword, Pageable pageable);
```

```
Pageable pageable = PageRequest.ofSize(10).withPage(1).withSort(sort);
Page<User> users =  userRepository.findByEmailLike("email%", pageable);

long totalElements = page.getTotalElements(); // 조건에 해당하는 전체 개수
int totalPages = page.getTotalPages(); // 전체 페이지 개수
List<User> content = page.getContent(); // 현재 페이지 결과 목록
int size = page.getSize(); // 페이지 크기
int pageNumber = page.getNumber(); // 현재 페이지
int numberOfElements = page.getNumberOfElements(); // 데이터 개수
```

## @Query
* @Query는 메소드 명명 규칙이 아니라 JPQL을 직접 사용한다.
    * 이는 메소드 이름이 간결해진다.

```
@Query("SELECT u FROM User u WHERE u.createDate > :since order by u.createDate DESC")
List<User> findRecentUsers(@Param("since") LocalDateTime since);

@Query("SELECT u FROM User u WHERE u.createDate > :since")
List<User> findRecentUsers(@Param("since") LocalDateTime since, Sort sort);

@Query("SELECT u FROM User u WHERE u.createDate > :since")
List<User> findRecentUsers(@Param("since") LocalDateTime since, Pageable pageable);
```

<b>since</b>으로 입력한 값을 가지고 쿼리를 사용할 수 있다.

## 정리
* 메소드 이름으로 정렬을 지정할 수 있지만 가능하면 <b>Sort</b>를 사용하는 것이 낫다.
* Pageable, PageRequest로 페이징 처리가 가능하다.
    * findTop, findFirst, findTopN, findFirstN으로 쉽게 원하는 데이터를 가져올 수 있다.
* @Query를 사용해서 JPQL 지정이 가능하다.   

[정렬, 페이징, @Query](https://www.youtube.com/watch?v=2-f9RFCT9Ik)