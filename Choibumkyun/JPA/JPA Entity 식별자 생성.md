# JPA, Entity 식별자 생성

## 식별자 생성 방식
* 직접 할당
* 식별 컬럼 방식
* 시퀀스 사용 방식
* 테이블 사용 방식   

## 직접 생성 방식
* @Id Annotation 설정 대상에 직접 값을 설정
    * 사용자가 입력한 값, 규칙에 따라 생성한 값 등
    * 예시. 이메일, 주문 번호
* 저장하기 전에 생성자 할당 (보통 생성 시점에 전달)   

```
@Entity
@Table(name="hotel_info")
public class Hotel {

    @Id
    @Column(name="hotel_id")
    private String id;

    protected Hotel() {

    }

    public Hotel(String id, String name, int year, Grade grade) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.grade = grade;
    }
}

...Getter()...
```

```
main.class

Hotel hotel = new Hotel("H-001", ...);
entityManager.persist(hotel); // 데이터 베이스에 저장
```

## 식별 컬럼 방식
* 데이터 베이스의 식별 컬럼에 매핑 (예시. MySQL 자동 증가 컬럼)
    * 데이터 베이스가 식별자를 생성하여 객체 생성 시 식별 값을 설정하지 않음
* 설정 방식
    * @GeneratedValue(strategy=GenerationType.IDENTITY) 로 설정
* Insert 쿼리를 실행해야 식별자 파악 가능
    * EntityManager#persist() 호출 시점에 Insert 쿼리가 실행
    * persist() 실행할 때 객체에 식별자 값이 할당   

```
@Entity
public class Review {

    @Id
    @Column(name="review_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY) // 식별 컬럼 설정
    private Long id;

    @Column(name="hotel_id")
    private String hotelId;

    private int mark;

    @Column(name="writer_name")
    private String writerName;

    private String comment;

    private LocalDateTime created;

    protected Review() {

    }

    public Review(int mark, String hotelId, String writerName, String comment) {
        this.mark = mark;
        this.hotelId = hotelId;
        this.writerName = writerName;
        this.comment = comment;
    }
}

public Long getId() {
    return id;
}

...
```

```
Review review = new Review(5, "H-01", "작성자", "댓글"); // 생성 시점에 식별자 지정 X

entityManger.persist(review); // 저장 시점에 Insert 쿼리 바로 실행

Long genId = review.getId(); // persist() 이후 식별자 사용 가능
```

## 시퀀스 사용 방식
* 시퀀스를 사용해 식별자 생성
    * JPA가 식별자 생성 처리 -> 객체 생성 시 식별값을 설정하지 않음
* 설정 방식
    * @SequenceGenerator Annotation으로 시퀀스 생성기 설정
    * GeneratedValue Annotation의 generator로 시퀀스 생성기 지정
* EntityManager#persist() 호출 시점에 시퀀스 사용
    * persist() 실행할 때 객체에 식별자 값 할당
    * Insert 쿼리는 실행하지 않음   

```
@Entity
@Table(schema="crm", name="activity_log")
public class ActivityLog {

    @Id
    @SequenceGenerator(
        name="log_seq_gen",
        sequenceName="activity_seq",
        schema="crm",
        allocationSize=1
    )
    @GeneratedValue(generator="log_seq_gen") // log_seq_gen로 시퀀스 생성기 지정
    private Long id;

    @Column(name="user_id")
    private String userId;

    private LocalDateTime created;

    protected ActivityLog() {

    }

    public ActivityLog(Long id, String userId, LocalDateTime created) {
        this.id = id;
        this.userId = userId;
        this.created = created;
    }
}

...Getter()...
```

```
ActivityLog log = new ActivityLog("U01", "VISIT"); // 생성 시점에 식별자 지정하지 않음

em.persist(log); // persist() 시점에 시퀀스로 식별자 구하기

tx.commit(); // 커밋 시점에 Insert 쿼리 실행
```

## 테이블 사용 방식
* 테이블을 시퀀스처럼 사용
    * 테이블에 Entity를 위한 키 보관
    * 해당 테이블을 이용해 다음 식별자 생성
* 설정 방식
    * @TableGenerator Annotation으로 테이블 생성기 설정
    * @GeneratedValue의 generator로 테이블 생성기 지정
* EntityManager#persist()의 호출 시점에 테이블 사용
    * persist() 할 때 테이블을 이용해 식별자를 구하고 이를 엔티티에 할당
    * Insert 쿼리는 실행하지 않음   

```
create table id_seq (
    entity varchar(100) not null primary key,
    nextval bigint
) engine innodb character set utf8mb4;
```

```
@Entity
@Table(name="access_log")
public class AccessLog {

    @Id
    @TableGenerator(
        name="accessIdGen",
        table="id_seq", // 생성한 테이블 명으로 설정
        pkColumnName="entity", // 기본키 설정
        pkColumnValue="accesslog",
        valueColumnName="nextval", // 식별자 설정
        initialValue=0,
        allocationSize=1
    )
    @GeneratedValue(generator="accessIdGen") // accessIdGen로 테이블 생성기 지정
    private Long id;

    private String path;

    private LocalDateTime accessed;

    protected AccessLog() {

    }

    public AccessLog(Long id, String path, LocalDateTime accessed) {
        this.id = id;
        this.path = path;
        this.accessed = accessed;
    }
}

...Getter()...
```

테이블 사용 방식에서 식별자를 생성하고 사용할 때 테이블 구조는 <b>엔티티 이름 컬럼</b>과 <b>식별자 보관 컬럼</b>이 필요하다.   

## 정리
식별자 생성 방식은 네 가지가 있다.   

* 직접 할당 방식
* 식별 컬럼 방식 - 저장 시점에 Insert 쿼리 실행
* 시퀀스 사용 방식 - 저장 시점에 시퀀스 사용하여 식별자 생성
* 테이블 저장 방식 - 저장 시점에 테이블 사용하여 식별자 생성   

[Entity 식별자 생성](https://www.youtube.com/watch?v=Xw9uTs72SVo&t=1s)