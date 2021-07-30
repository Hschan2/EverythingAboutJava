package com.example.queryjpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class DataBaseConfiguration {
    
    // Request가 올 때, EntityManager 생성
    // Transaction 단위로 생성
    // DB Connection Pool을 사용
    // Transaction이 끝나면 버리고 다른 스레드와 공유하면 안됨
    // PersistenceContext => EntityManager을 영구 저장할 것
    @PersistenceContext
    private EntityManager entityManager;

    // JPAQuery를 생성해서 사용하는 방식
    // EntityManager를 통해서 질의 처리
    // 사용 쿼리문으느 JPQL
    // 최종 목표는 JPA, QueryDSL이기 때문에 사용
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
