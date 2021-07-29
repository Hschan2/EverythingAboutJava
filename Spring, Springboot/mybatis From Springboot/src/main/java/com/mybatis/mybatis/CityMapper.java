package com.mybatis.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper // Mapper 설정
public interface CityMapper {
    @Insert("INSERT INTO city (name, state, country) VALUES(#{name}, #{state}, #{country})") // 데이터 추가
    @Options(useGeneratedKeys = true, keyProperty = "id") // Autoincrement True
    void insert(City city);

    @Select("SELECT id, name, state, country FROM city WHERE id = #{id}") // 데이터 읽기
    City findById(long id);
}
