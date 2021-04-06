package com.jap.demojap.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

//ApplicationRunner의 Type에 Bean이 등록되어 있으면 Run이 실행
@Component
public class BookRunner implements ApplicationRunner {

    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Book book = new Book();
        book.setName("Hong JPA");
        book.setCreated(new Date());

//        데이터 저장
        bookRepository.save(book);
    }
}
