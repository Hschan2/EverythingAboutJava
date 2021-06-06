package com.example.queryjpa.Repository;

import com.example.queryjpa.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

// 데이터베이스 테이블마다 생성 (Entity와 일대일)
// <Entity, Entity_ID>
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);
}
