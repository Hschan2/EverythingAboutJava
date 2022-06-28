# JPQL (JPA Query Language)
<b>JPQL</b>은 JPA Query Language의 약자이다. 이는 SQL 쿼리와 유사하며 테이블 대신에 엔티티 이름과 속성을 사용한다.   

예시 코드는 다음과 같다.   


```
TypedQuery<Review> query = em.createQuery(
    "SELECT r FROM Review r WHERE r.hotelId = :hotelId order by r.id DESC", Review.class
);

query.setParameter("hotelId", "H-001");
List<Review> reviews = query.getResultList();
```
```
@Entity
public class Review {

    @Id
    @Column(name="review_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="hotel_id")
    private String hotelId;
}
```

## JPQL 기본 사용법
* JPQL 기본 구조: SELECT 별칭 FROM 엔티티명 별칭 ...
    * 예시. SELECT r FROM Review r, SELECT r FROM Review as r
* 쿼리 생성: TypedQuery<T> EntityManager#createQuery(String ql, Class<T> resultClass)
```
TypedQuery<Review> query = em.createQuery(
    "SELECT r FROM Review r", Review.class
);

* "SELECT r FROM Review r" => 쿼리
* Review.class => 결과 타입

List<Review> reviews = query.getResultList();
```

## 검색 조건 지정
* WHERE + AND, OR, 괄호 등을 사용할 수 있다.   

예시 코드.
```
SELECT r FROM Review r WHERE r.hotelId = :hotelId
SELECT r FROM Review r WHERE r.hotelId = ?
SELECT r FROM Review r WHERE r.hotelId = :hotelId and r.mark > :minMark
SELECT p FROM Review p WHERE p.hotelId = :pos or p.team.id = :teamId
```

* Parameter를 사용할 수 있다.
    * 이름을 사용하는 경우: query.setParameter("hotelId", "H-001")
    * 인덱스 기반인 경우: query.setParameter(0, "H-001")   

예시 코드.
```
TypedQuery<Review> query = em.createQuery(
    "SELECT r FROM Review r WHERE r.hotelId = :hotelId order by r.id DESC", Review.class
);

query.setParameter("hotelId", "H-001");
```

## 정렬 순서
* ORDER BY
    * SELECT r FROM Review r ORDER BY r.id
    * SELECT r FROM Review r ORDER BY r.id ASC
    * SELECT r FROM Review r ORDER BY r.id DESC
    * SELECT p FROM Player p ORDER BY p.position, p.name
    * SELECT p FROM Player p ORDER BY p.team.id, p.name

## 비교 연산자
* "=" (예. u.name = 'JPA')
* "<>" (예. o.state <> ?)
* ">", ">=", "<", "<=" (예. p.salary > 2000)
* "between" (예. mc.expiryDate between ? and ?)
* "in", "not in" (예. o.mark in (1, 2, 3))
* "like", "not like" (예. u.name like '%유%')
* "is null", "is not null" (예. u.name is null)

## 페이징 처리
```
TypedQuery<Review> query = em.createQuery(
    "SELECT r FROM Review r WHERE r.hotelId = :hotelId ORDER BY r.id DESC", Review.class
);

query.setParameter("hotelId", "H-001");
query.setFirstResult(8); // 0부터 시작, 시작행
query.setMaxResults(4); // 최대 결과 개수

List<Review> reviews = query.getResultList();
```

## 잘 사용하지 않지만 존재하는 것들
* Join
* 집합 함수
    * COUNT, MAX, MIN, AVG, SUM
* GROUP BY, HAVING
* 콜렉션 관련 비교
    * member of, not member of, is empty, is not empty
    * exists, all, any
* JPQL 함수
    * CONCAT, SUBSTRING, TRIM, ABS, SQRT
    * 콜렉션 함수
        * SIZE, INDEX...

## 정리
주의할 것은 다음의 경우는 JPQL이 아닌 일반 쿼리를 사용하는 것이 좋다.   

* 여러 개의 테이블을 조인할 때
    * 레거시 테이블 조인
* DMBS에 특화된 쿼리가 필요할 때
    * 예. 오라클 힌트
* 서브 쿼리가 필요할 때
* 통계나 대량 데이터를 조회하거나 처리할 때   

[JPQL](https://www.youtube.com/watch?v=UtEhC68GTH0)