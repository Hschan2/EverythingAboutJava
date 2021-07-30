package com.jpaMapping.jpaMapping;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Student {
    
    @ManyToMany(targetEntity = Course.class)
    // 다대다 관계. 두 Entity가 서로를 Collection으로 가지게 되는 관계
    // 다대다 관계는 다른 형태로 변형할 수 있는 여지가 많은 관계
    // @JoinTable로 @ManyToMany 관계를 지정할 수 있고, @ManyToOne 두 개로 관계를 변화시킬 수 있음
    Set<Course> selectedCourses;
}
