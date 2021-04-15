package com.jpaMapping.jpaMapping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team") // 참조를 당하는 쪽에서 읽기만 가능
    private List<Member> members = new ArrayList<>();
//    => Member : N - Team : 1 (다대일 양방향)
//        Member에서 Team을 참조한다. Team에서도 Member를 참조한다.
//        연관 관계 주인이 FK 관리
//        반대 쪽은 읽기만 가능하여 Team에서 List를 추가하기만 하면 된다.
//        mappedBy로 연관 관계의 주인을 읽을 것이라는 것의 명시가 중요
//        - 외래키가 있는 쪽이 연관 관계의 주인
//        - 양쪽을 서로 참조하도록 개발해야 한다

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members2 = new ArrayList<>();
//    => 일대다 단반향
//        권장하지 않음
//        Team을 중심으로 하며 Team에서 외래키를 관리
//        Team은 Member를 알고 싶지만 Member는 Team을 알고 싶지 않다
//        DB에서 Member에 FK를 설정해야 한다
//        Team의 List 바꾸었을 때, DB의 Member 중에 어떤 것의 TEAM_ID를 바꿔야 한다.
//        DB에는 잘 들어가나 UpdateQuery가 나가는 등 Query가 많이 나간다.
//        team에서 Member list를 저장할 때, Member 테이블에도 team_id를 update를 해주어야 한다.
//        Team을 건드렸지만 Member 테이블에 영향이 간다.
//        - 일대다 단방향은 일대다(N : 1)에서 일(1)이 연관 관계의 주인
//        - 테이블 일대다 관계는 항상 다(N) 쪽에 외래키가 있다.
//        - 객체와 테이블의 차이 때문에 반대편 테이블의 외래 키를 관리하는 특이한 구조
//        - @JoinColumn을 꼭 사용해야 하며 그렇지 않으면 조인 테이블 방식을 사용해야 한다.
//            (중간에 테이블을 하나 추가하는 것)
//            team_member라는 중간 테이블이 생겨버린다. = team_id와 member_id를 갖고 있다.
//            단점은 테이블이 하나 더 들어가서 운영이 어렵다.
//        - 일다대 단반향 매핑의 단점
//            엔티티가 관리하는 외래키가 다른 테이블에 있다.
//            연관 관계 관리를 위해 추가로 Update SQL 실행한다.
//        - 일대다 단반향 매핑보다는 다대일 양방향 매핑을 추천
}
