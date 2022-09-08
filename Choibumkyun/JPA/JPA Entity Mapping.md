# JPA, 엔티티 매핑

## Entity Mapping
* 기본 Annotation
    * @Entity - 엔티티 클래스에 설정하며 필수
    * @Table - 매핑할 테이블 지정
    * @Id - 식별자 속성에 설정하며 필수
    * @Column - 매핑할 컬럼명 지정 (지정하지 않으면 필드명/프로퍼티명 사용할 것)
    * @Enumerated - Enum 타입 매핑할 때 사용   

## @Table Annotation
* 이를 생략하면 클래스 이름과 동일한 이름에 매핑
* 속성
    * name - 테이블 이름 (생략하면 클래스 이름과 동일한 이름)
    * catalog - 카탈로그 이름 (ex. MySQL DB 이름)
    * schema - 스키마 이름 (ex. 오라클 스키마 이름)

* 예.
    * @Table
    * @Table(name="hotel_info")
    * @Table(catalog="point", name="point_history")
    * @Table(schema="crm", name="cust_stat")   

## @Enumerated Annotation
* 설정 값
    * EnumType.STRING - enum 타입 값 이름을 저장
        * 문자열 타입 컬럼에 매핑
    * EnumType.ORDINAL (기본값) - enum 타입의 값의 순서를 저장
        * 숫자 타입 컬럼에 매핑   

```
public enum Grade {
    S1, S2, S3, S4, S5, S6
}

EnumType.STRING -> "S1"
Grade.S1.name()

EnumType.ORDINAL -> 0
Grade.S1.ordinal()
```

매핑 설정을 예로 들면 다음과 같다.   

```
@Entity // 매핑 대상 엔티티
@Table(name="hotel_info") // hotel_info 테이블에 매핑
public class Hotel {

    @Id // 식별자로 매핑
    @Column(name="hotel_id") // hotel_id 컬럼에 매핑
    private String id;

    @Column(name="nm") // nm 컬럼에 매핑
    private String name;

    private int year; // year 컬럼에 매핑

    @Enumerated(EnumType.STRING) // 열거타입 이름을 값으로 저장
    private Grade grade; // grade 컬럼에 매핑

    private LocalDateTime created; // created 컬럼에 매핑

    @Column(name="modified") // modified 컬럼에 매핑
    private LocalDateTime lastModified;

    ...생성자, Getter...
}
```

## 엔티티 클래스 제약 조건 (스펙 기준)
```
@Entity
@Table(name="hotel_info")
public class Hotel {

    @Id
    @Column(name="hotel_id")
    private String id;

    ...

    protected Hotel() { // 인자 없는 기본 생성자

    }

    public Hotel(String id, String name, int year, Grade grade) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.grade = grade;
    }
}
```

제약 조건은 꼭 <b>@Entity</b>를 적용해야 한다. 그리고 <b>@Id</b> 또한 꼭 적용해야 한다. 인자 없는 <B>기본 생성자</B>도 필요하며 기본 생성자는 ```public``` 혹은 ```protecte```d여야 한다. 그리고 <b>최상위 클래스</b>여야 한다. 다만 ```final```이면 안 된다.   

## 접근 타입
두 개의 접근 타입이 존재한다. <b>필드 접근</b>은 필드 값을 사용해서 매핑한다. 그리고 <b>프로퍼티 접근</b>은 Getter/Setter 메소드를 사용해서 매핑한다.   

설정 방법은 다음과 같다.   
* @Id Annotation을 필드에 붙이면 필드에 접근
* @Id Annotation을 Getter 메소드에 붙이면 프로퍼티 접근
* @Access Annotation을 사용해서 명시적으로 지정
    * 클래스/개별 필드에 적용 가능
    * @Access(AccessType.PROPERTY) 혹은 @Access(AccessType.FIELD)
* 필드 접근을 주로 선호
    * 불필요한 Setter 메소드를 만들 필요가 없기 때문   

## 정리
주요 매핑 애노테이션은 <b>@Entity</b>, <b>@table</b>, <b>@Id</b>, <b>@Column</b>, <b>@Enumerated</b>이다.   

엔티티 클래스 제약은 <b>기본 생성자 필요</b> 등 몇 가지 제약이 존재한다.   

접근 타입은 <b>*필드 접근</b>, <b>프로퍼티 접근</b>이 있다.   

[엔티티 매핑](https://www.youtube.com/watch?v=SbMJVuv8Iyo&t=0s)