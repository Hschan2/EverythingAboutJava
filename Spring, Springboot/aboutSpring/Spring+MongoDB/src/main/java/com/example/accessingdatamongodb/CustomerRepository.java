package com.example.accessingdatamongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

// Mongo DB 사용하기 전 Mongo DB 설치하기
//		xcode-select --install 
//		brew tap mongodb/brew
//		brew install mongodb-community@4.4
// Mongo DB 사용 => MongoRepository 의존
public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);

}
