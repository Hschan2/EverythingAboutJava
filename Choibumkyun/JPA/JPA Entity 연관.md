# JPA, Entity 연관 기초

## 연관
<b>연관</b>은 엔티티와 엔티티 간 연결을 말한다. 엔티티가 다른 엔티티를 필드와 프로퍼티로 참조를 한다.   

```
@Entity
public class User {

    @Id
    private String email;

    private String name;
}
```

```
@Entity
public class MembershipCard {

    @Id
    private String number;

    @OneToOne
    @JoinColumn(name="user_email")
    private User owner;
}
```

## 연관의 종류
1. 1-1, 참조키 방식으로 단 반향, 양 방향이 가능하다.   
2. 1-1, 키 공유 방식으로 단 방향, 양 방향이 가능하다.   
3. N-1, 단 방향만 가능하다.   
4. 1-N, 콜렉션(Set, List, Map)을 사용한 것으로 단 방향만 가능하다.   
5. N-1/1-N, 양 방향만 가능하다.   
6. M-N, 단 방향과 양 방향이 가능하다.   

## 주로 다루는 연관
<b>1-1 연관 단방향</b>, <b>N-1 단방향</b>, <b>1-N 단방향</b>을 주로 다루며 엔티티 간 연관은 거의 사용하지 않는 경우가 존재한다. 특히, 양방향은 거의 없는 경우가 많다. <b>M-N 연관</b>은 거의 사용하지 않는다.   

이유는 <b>Value (밸류, @Embeddable 타입)</b>으로 매핑하기 때문이다. 조회는 쿼리를 직접 사용하여 처리하는 경우가 많다.   

[Entity 연관 기초](https://www.youtube.com/watch?v=rZZSYG__8Jc&t=60s)