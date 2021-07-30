package com.jpaMapping.jpaMapping;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Course {
    @ManyToMany(targetEntity = Student.class)
    Set<Student> registeredStudents;
}
