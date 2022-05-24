# JPA, 영속성 전파와 연관 관계 고려 사항

## 영속성 전파가 필요한 예시 코드
```
Player p21 = em.find(Player.class, "P-21");
Player p22 = em.find(Player.class, "P-22");

Set<Player> players = new HashSet<>();

players.add(p21);
players.add(p22);

em.persist(new Team("T-02", "팀2", players));
```

```
Player p21 = em.find("P-21", "선수1");
Player p22 = em.find("P-22", "선수2");

Set<Player> players = new HashSet<>();

players.add(p21);
players.add(p22);

em.persist(new Team("T-02", "팀2", players));
```

=> 오른쪽과 같이 새로운 플레이어를 그냥 생성을 하고 추가하면 jakarta.persistence Error가 발생이 한다. 그 이유는 플레이어가 없기 때문에 Team 입장에서 연관되어 있는 Entity가 존재하지 않은 것으로 인식하기 때문에 에러가 발생한다.   

그러나 가끔식 오른쪽처럼 직접 생성하여 입력하고 싶을 때가 있다.   

## 영속성 전파
<b>영속성 전파</b>는 연관된 엔티티에 영속 상태를 전파를 말한다. 예를 들어서 저장할 때 연관된 엔티티도 함께 저장하는 것이다.   

```
@Entity
@Table(name="team")
public class Team {

    @Id
    private String id;

    private String name;

    @OneToMany(cascade=CascadeType.PERSIST) //  영속성 전파를 위한 관계 설정, 아직 저장되지 않은 플레이어도 함께 저장
    @JoinColumn(name="team_id")
    private Set<Player> players = new HashSet<>();
}
```

```
Player p21 = em.find("P-21", "선수1");
Player p22 = em.find("P-22", "선수2");

Set<Player> players = new HashSet<>();

players.add(p21);
players.add(p22);

em.persist(new Team("T-02", "팀2", players));
```

## 영속성 전파 종류
```
public enum CascadeType {
    ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH
}
```

## 영속성 전파 주의
기본적으로 사용하지 말자는 것이 주의 사항이다. 특별한 이유가 없다면 영속성 전파를 사용하지 말자.   

연관 관계도 특별한 이유가 없다면 사용하지 말고 영속성 전파도 되도록 사용하지 않는 것이 좋다. 만약 둘 모두 사용하게 된다면? 그러지 말자.   

## 연관 고려 사항
* 연관 대신에 ID 값으로 참조 고려하기
    * 객체 탐색이 쉽다는 이유로 연관을 쓰지 않기
* 조회는 전용 쿼리나 구현을 사용하는 것을 고려하기 (CQRS)
* Entity가 아닌 Value인지 확인하기
    * 1:1, 1:N 관계에서는 특히 확인하기
* 1:N보다는 N:1 관계를 사용하기 (단순하기 때문에, 정말로 어쩔 수 없이 써야하는 이유일 경우)
* 양방향은 사용하지 말기

[영속성 전파와 연관 관계 고려 사항](https://www.youtube.com/watch?v=7JAoNNhvsjw)