package com.jpaMapping.jpaMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Employee {

    @Id
    Integer id;

    @OneToOne
    @MapsId // Employee.id 값으로 연결될 EmployeeInfo를 지정, 동일한 Primary Key를 공유
    EmployeeInfo info;
}
