package com.example.queryjpa.Repository;

import com.example.queryjpa.Entity.Staff;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class StaffRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */
    public StaffRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Staff.class); // Join 걸 때, Entity에서 관계 설정이 되어 있는 기준으로 설정
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
