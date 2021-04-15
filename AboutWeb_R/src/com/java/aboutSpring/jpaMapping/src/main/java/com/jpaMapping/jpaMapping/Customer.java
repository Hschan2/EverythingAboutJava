package com.jpaMapping.jpaMapping;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @OneToOne
    @JoinColumn(name = "record_id") // 외래키를 참조할 곳을 지정
    public Record record;

//    외래키를 제공하는 Entity와 제공받는 Entity로 생성.
//    제공받는 Entity는 반드시 @JoinColumn으로 참조할 곳을 지정해줘야 하고,
//    제공하는 Entity는 제공받는 Entity의 변수명을 mappedBy 값으로 지정해야 한다.

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public int id;

    @OneToMany(mappedBy = "customer") // mappedBy 속성에 customer 속성을 연결
    // @OneToMany는 지정하는 객체는 하나, 연결되는 객체가 여러 개일 때 사용
    // 연결할 때 mappedBy 속성으로 연결 가능, targetEntity 속성으로 연결할 Entity를 지정 가능
    public Set<Order> orders;

    @OneToMany(targetEntity = Order.class) // targetEntity 방식
    public Set<Order> orders2;
}
