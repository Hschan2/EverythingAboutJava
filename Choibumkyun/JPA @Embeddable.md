# JPA, @Embeddable

## 테이블과 매핑 예
```
hotel_info
hotel_id varchar(50)
nm varchar(50)
year integer(10)
grade varchar(2)
addr1 varchar(100)
addr2 varchar(100)
zipcode varchar(5)
created timestamp
modified timestamp

<->

ORM Persistable
-id: String
-name: String
-year: int
-grade: Grade
-addr1: String
-addr2: String
-zipcode: String
-created: LocalDateTime
-modified: LocalDateTime
```

### 실제 모델
```
-addr1: String
-addr2: String
-zipcode: String

->

-address: Address

-->

Address
-address1: String
-address2: String
-zipcode: String
```

## @Embeddable
엔티티가 아닌 타입을 한 개 이상의 필드와 매핑할 때 사용한다. 예를 들어, Address, Money 등을 매핑할 때 사용한다.   

엔티티의 한 속성으로 @Embeddable 적용 타입을 사용한다.   

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

protected Address() {

}

...생성자, Getter()...
```

```
@Entity
@Table(name="hotel_info")
public class Hotel {

    @Id
    @Column(name="hotel_id")
    private String id;

    ...

    @Embeddable
    private Address address;
}
```

실제로 저장을 한다면 주소를 먼저 입력을 하고 이 후 전체 데이터를 저장한다.

```
tx.begin();
Address address = new Address("주소1", "주소2", "12345");
Hotel hotel = new Hotel("H00", "HN", 2022, Grade.S7, address);
em.persist(hotel);
tx.commit();
```

그리고 조회를 한다면 오브젝트 객체로 확인이 가능하다.   

```
Hotel hotel = em.find(Hotel.class, "H00");
logger.info("주소: {}", hotel.getAddress());
```

만약, 주소 값에 Null 값을 넣는다면 데이터 베이스에 Null 값으로 저장된다.   

```
tx.begin();
Address address = null;
Hotel hotel = new Hotel("H00", "HN", 2022, Grade.S7, address);
em.persist(hotel);
tx.commit();
```

```
Hotel hotel = em.find(Hotel.class, "H00");
boolean nullAddr = hotel.getAddress() == null;
```

주소 값을 Null로 저장하고 나서 주소 값이 Null인지 확인하면 <b>true</b> 값이 나오는 것을 확인할 수 있다.   

## 만약 같은 @Embeddable 타입 필드가 두 개라면?
```
@Entity
public class Employee {

    @Id
    private String id;

    @Embeddable
    private Address homeAddress;

    @Embeddable
    private Address workAddress;
}
```

만약 @Embeddable 타입 필드가 두 개라면 오류창이 뜨는 것을 확인할 수 있다. 오류창에서 확인할 수 있는 내용은 ```Repeated column in mapping for entity```를 확인할 수 있다. 같은 컬럼을 중복으로 매핑을 시도하기 때문에 발생하는 것이다.   

## @AttributeOverride으로 설정 재정의
```
@Entity
public class Employee {

    @Id
    private String id;

    @Embeddable
    private Address homeAddress;

    @AttributeOverrides({
        @AttributeOverride(name="address1", column=@Column(name="waddr1")),
        @AttributeOverride(name="address2", column=@Column(name="waddr2")),
        @AttributeOverride(name="zipcode", column=@Column(name="wzipcode"))
    })

    @Embeddable
    private Address workAddress;
}
```

```@AttributeOverride```로 재정의를 하고 나서 값을 저장을 하면   

```
insert into Employee (addr1, addr2, zipcode, waddr1, waddr2, wzipcode, id) values (?, ?, ?, ?, ?, ?, ?);
```

값이 제대로 저장이 되는 것을 확인할 수 있다.   

## 정리
```@Embeddable```을 사용하면 모델을 더욱 잘 표현할 수 있다. 개별 속성을 모아서 이해할 수 있고 타입으로 더욱 쉽게 이해가 가능하다.   

예시. (addr1, addr2, zipcode)를 모아서 "이게 주소구나" -> "주소"네   

[@Embeddable](https://www.youtube.com/watch?v=WtS5IszIueA)