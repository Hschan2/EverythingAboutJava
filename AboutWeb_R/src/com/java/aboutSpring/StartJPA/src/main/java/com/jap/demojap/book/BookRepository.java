package com.jap.demojap.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Book => Entity Type
// Long => Key Type
//@Repository => JpaRepository에 이미 선언되어 있기 때문에 다시 선언할 필요 없음
public interface BookRepository extends JpaRepository<Book, Long> {

}
