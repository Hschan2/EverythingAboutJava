package com.jap.demojap.book;

import javax.persistence.*;
import java.util.Date;

//데이터에 필요한 값 선언
@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
