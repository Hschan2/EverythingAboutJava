package com.example.managingtransactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
class AppRunner implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final BookingService bookingService;

	public AppRunner(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@Override
	public void run(String... args) throws Exception { // 예약
		bookingService.book("Alice", "Bob", "Carol");
		Assert.isTrue(bookingService.findAllBookings().size() == 3,
				"First booking should work with no problem");
		logger.info("Alice, Bob and Carol have been booked");
		try {
			bookingService.book("Chris", "Samuel"); // // Samuel이 지정된 5 Byte가 넘으므로 Chris마저 롤백 => 예외 발생 = 트랙잭션 설정되어 있기 때문
 		} catch (RuntimeException e) {
			logger.info("v--- The following exception is expect because 'Samuel' is too " +
					"big for the DB ---v");
			logger.error(e.getMessage());
		}

		for (String person : bookingService.findAllBookings()) {
			logger.info("So far, " + person + " is booked.");
		}
		logger.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
				"and Chris was rolled back in the same TX");
		Assert.isTrue(bookingService.findAllBookings().size() == 3,
				"'Samuel' should have triggered a rollback");

		try {
			bookingService.book("Buddy", null); // DB 설정 시, Not NULL이기 때문에 예외 발생 => Buddy마저 롤백
		} catch (RuntimeException e) {
			logger.info("v--- The following exception is expect because null is not " +
					"valid for the DB ---v");
			logger.error(e.getMessage());
		}

		for (String person : bookingService.findAllBookings()) {
			logger.info("So far, " + person + " is booked.");
		}
		logger.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
				"Buddy was rolled back in the same TX");
		Assert.isTrue(bookingService.findAllBookings().size() == 3,
				"'null' should have triggered a rollback");
	}

}
