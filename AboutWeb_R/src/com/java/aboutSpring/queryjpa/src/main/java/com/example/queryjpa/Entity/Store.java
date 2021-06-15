package com.example.queryjpa.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) // 일대다 관계, LAZY 설정 (EAGER보다 필요한 객체 리스트 얻기 위해, 비용 절약)
//    @JoinColumn(name = "store_id") // store_id 기준으로 관계 설정, mapperBy 사용해도 무관
//    private Collection<Staff> staff;

    @Builder
    public Store(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
