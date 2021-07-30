package com.jpaMapping.jpaMapping;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeInfo {

    @Id // Employy.id 값과 동일한 값으로 설정되므로, @GeneratedValue를 사용하면 안 된다.
    Integer id;
}
