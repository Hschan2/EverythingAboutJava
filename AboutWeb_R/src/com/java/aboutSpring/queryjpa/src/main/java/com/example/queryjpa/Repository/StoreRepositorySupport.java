package com.example.queryjpa.Repository;

import com.example.queryjpa.Entity.QStore;
import com.example.queryjpa.Entity.Staff;
import com.example.queryjpa.Entity.Store;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.queryjpa.Entity.QStaff.*;
import static com.example.queryjpa.Entity.QStore.*;

@Repository
public class StoreRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     *
     * @param jpaQueryFactory must not be {@literal null}.
     */
    public StoreRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Store.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Store> findByName(String name) {
        return jpaQueryFactory
                .selectFrom(store)
                .where(store.name.eq(name))
                .fetch();
    }

    public Store findOneByName(String name) {
        return jpaQueryFactory
                .selectFrom(store)
                .where(store.name.eq(name))
                .fetchOne();
    }

    // Entity 관계 매핑 X
    public List<Staff> findStaffsByName(String name) {
        return jpaQueryFactory
                .select(Projections.fields(StaffVo.class,
                        staff.id
                        , staff.age
                        , staff.name
                ))
                .from(store)
                .join(staff)
                    .on(store.id.eq(staff.storeId))
                .where(store.name.eq(name))
                .fetch();
    }
}
