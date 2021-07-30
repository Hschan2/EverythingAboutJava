package com.example.queryjpa.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Setter를 사용하지 않는 이유 - 객체의 일관성 보장 못하기 때문.
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 일관성을 위해
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키, 데이터 베이스에 위임
    private Long id;

    private String name;
    private Integer age;

//    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "fk_staff_store_id"))
//    private Store store;

    // 매핑이 안되어있을 경우
    @Column(name = "store_id")
    private Long storeId;

    @Builder // 생성자, 객체 생성할 때는 Builder 사용할 것
    public Staff(Long id, String name, Integer age, Long storeId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.storeId = storeId;
    }
}
