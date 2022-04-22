# JPA, @Embeddable 다른 테이블에 매핑하기

## 첫 번째 방법 - @SecondaryTable + 테이블명
```
@Embeddable
public class Intro {

    @Column(table="writer_intro", name="content_type")
    private String contentType;

    @Column(table="writer_intro")
    private String content;
}
```

```
@Entity
@SecondaryTable(name="writer_intro",
    pkJoinColumns=@PrimaryKeyJoinColumn(
        name="writer_id",
        referencedColumnName="id"
    )
)
public class Writer {

    ...

    @Embedded
    private Intro intro;
}
```

@SecondaryTable Annotation의 pkJoinColumns으로 저장한 @PrimaryKeyJoinColumn의 name은 <b>writer_intro 테이블 컬럼</b>로 테이블을 참조할 때 사용하는 것을 말하며, referencedColumnName은 <b>writer 테이블 컬럼</b>으로 Join이 된 테이블을 참조할 때 사용하는 것을 의미한다. 즉, @SecondaryTable Annotation을 사용해서 writer_intro 테이블을 같이 사용할 것이라는 것을 의미한다.   

## 두 번째 방법 - @SecondaryTable + @AttributeOverride
```
@Embeddable
public class Address {

    @Column(name="addr1")
    private String address1;

    @Column(name="addr2")
    private String address2;

    @Column(name="zipcode")
    private String zipcode;
}
```

```
@Entity
@SecondaryTables({
    @SecondaryTable(name="writer_address",
        pkJoinColumns=@PrimaryKeyJoinColumn(
            name="writer_id",
            referencedColumnName="id"
        )
    ),
    ...
})
public class Writer {

    ...

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
            name="address1",
            column=@Column(table="writer_address", name="addr1")
        ),
        @AttributeOverride(
            name="address2",
            column=@Column(table="writer_address", name="addr2")
        ),
        @AttributeOverride(
            name="zipcode",
            column=@Column(table="writer_address")
        )
    })
    private Address address;
}
```

```@SecondaryTable```으로 참조할 다른 테이블을 지정하고 사용할 컬럼을 지정한다. 그리고 ```@AttributeOverride```을 사용하여 name은 사용할 인스턴스를 설정하고 ```@SecondaryTable```으로 참조한 테이블을 컬럼으로 지정하고 원하는 데이터를 name으로 가져온다.   

### 이를저장한다면

```
Writer w = new Writer("name",
    new Address("주소1", "주소2", "12345"),
    new Intro("text/plain", "소개글")
);

em.persist(w);
```

Writer에서 참조한 Address와 Intro에서 값을 넣어 저장하게 된다. 데이터 베이스에서는 아래처럼 동작한다.   

```
insert into writer (name) values (?)
insert into writer_address (addr1, addr2, zipcode, writer_id) values(?, ?, ?, ?)
insert into writer_intro (content, content_type, writer_id) values(?, ?, ?)
```

혹은 writer_intro 테이블에는 저장하지 않는다고 가정한다면   

```
Writer w = new Writer("name",
    new Address("주소", "주소2", "12345"),
    null
);

em.persist(w);
```

Address 테이블에는 저장할 값을 넣고 Intro 테이블에는 null을 주게 되면 Intro 테이블을 제외한 Address 테이블에만 데이터를 저장하게 된다.   

```
insert into writer (name) values (?)
insert into writer_address (addr1, addr2, zipcode, writer_id) values(?, ?, ?, ?)
```

### 조회한다면
```
Writer writer = em.find(Writer.class, id);
```

writer 테이블의 데이터와 함께 이를 참조한 writer_address와 writer_intro 테이블을 조인한 값이 출력이 된다.   

```
SELECT w1_0.id, w1_0.name, w1_1.addr1, w1_1.addr2, w1_1.zipcode, w1_2.content, w1_2.content_type FROM WRITER w1_0
LEFT JOIN WRITER_ADDRESS w1_1 on w1_0.id=w1_1.writer_id
LEFT JOIN WRITER_INTRO w1_2 on w1_0.id=w1_2.writer_id
WHERE w1_0.id=?
```

### 변경한다면
```
Writer writer = em.find(Writer.class, id);
writer.setAddress(new Address("새주소1", "새주소2", "23456"));
```

```
UPDATE writer_address SET addr1=?, addr2=?, zipcode=? WHERE writer_id=?

INSERT INTO writer_address (addr1, addr2, zipcode, writer_id) values (?, ?, ?, ?)
```

만약에 address의 값이 NULL이 아니면. 즉, 테이블에 데이터가 있다면 UPDATE를 실행하고 NULL이면 INSERT를 실행한다.   

```
Writer writer = em.find(Writer.class, id);
writer.setAddress(null);
```

```
DELETE FROM writer_address WHERE writer_id=?
```

만약 address가 NULL이 아니면. 즉, 테이블에 데이터가 있다면 DELETE를 실행한다.   

### 삭제한다면
```
Writer writer = em.find(Writer.class, id);
em.remove(writer);
```

writer의 데이터가 있다면 해당 데이터를 삭제하게 된다. (물론 참조된 데이터도 삭제가 된다.)   

```
DELETE FROM writer_intro WHERE writer_id=?
DELETE FROM writer_address WHERE writer_id=?
DELETE FROM writer WHERE id=?
```

## 정리
```@SecondaryTable Annotation```은 다른 테이블에 저장된 데이터를 ```@Embeddable```으로 매핑이 가능하다. 그리고 다른 테이블에 저장된 데이터가 개념적으로 <b>Value(값)</b>일 때 사용이 가능하다. (1-1 관계인 두 테이블을 매핑할 때 종종 사용한다.)   

[@Embeddable 다른 테이블에 매핑](https://www.youtube.com/watch?v=3_sdQGfL2Lg)