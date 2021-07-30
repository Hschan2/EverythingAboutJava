package com.jpaMapping.jpaMapping;

import javax.persistence.*;

@Entity
@Table(name = "RECORD")
public class Record {

    @OneToOne(mappedBy = "record") // 외래키를 제공하는 속성을 지정, 일대일
    public Customer customer;

    @ManyToOne // 다대일, mappedBy 방식
    @JoinColumn(name = "CUSTOMER_ID")
    public Customer customer2;

    @ManyToOne // 다대일, targetEntity 방식
    @JoinColumn(name = "CUSTOMER_ID", nullable = false) 
    public Customer customer3;
}
