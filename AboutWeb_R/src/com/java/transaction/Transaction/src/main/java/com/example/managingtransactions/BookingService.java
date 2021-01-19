package com.example.managingtransactions;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component // Bean
public class BookingService {

	private final static Logger logger = LoggerFactory.getLogger(BookingService.class);

	private final JdbcTemplate jdbcTemplate;

	public BookingService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Transactional // 트랜잭션 선언, 이번 내용에서 가장 중요한 부분
	public void book(String... persons) { // 예약, 배열로 persons
		for (String person : persons) {
			logger.info("Booking " + person + " in a seat..."); // 기록
			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
		}
	}

	public List<String> findAllBookings() {
		return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
				(rs, rowNum) -> rs.getString("FIRST_NAME")); // 람다 함수로 출력
	}

}
