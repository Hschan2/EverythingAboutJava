package com.example.jpa.member;

import lombok.*;

import javax.persistence.*;

@Getter // Getter 메소드 생성
@Setter // Setter 메소드 생성
@Builder // 빌더 사용
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users") // JPA를 사용할 클래스 명시, 테이블과 매핑
public class MemberEntity {

    @Id // 기본키 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키 값의 자동생성 전략 설정
    private Long id;

    @Column(nullable = false, unique = true, length = 30) // 컬럼 속성값 설정
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    public MemberEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
