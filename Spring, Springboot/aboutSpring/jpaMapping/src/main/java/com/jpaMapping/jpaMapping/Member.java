package com.jpaMapping.jpaMapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
    
    @ManyToOne // 다대일 단방향
    @JoinColumn(name = "TEAM_ID") // 외래키
    private Team team;
//    => 외래키가 있는 곳에 참조를 걸고 연관 관계를 매핑
//    DB에서 N에서 FK가 있어야 한다.
//    가장 많이 사용하고 다대일의 반대는 일대다

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 여기 설정이 중요
    private Team team2;
//    => 일대다 양방향
//        - Team, Member 모두 @JoinColumn이 붙어서 둘 다 연관 관계의 주인이 된다.
//        - JoinColumn의 옵션을 사용해서 Mapping은 되어 있고 값은 다 쓰는데
//            insertable, updatable을 막아서 read 전용으로 만든다.
//        - 관리는 Team으로 하고 Member는 읽기만
//        - 이런 매핑은 공식적으로 존재하지 않는다.
//        - @JoinColumn(insertable = false, updatable = false)
//        - 읽기 전용 필드를 사용해서 양방향처럼 사용하는 방법
//        - 다대일 양방향을 사용하자
}
