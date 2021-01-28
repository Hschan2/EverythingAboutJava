package com.mybatis.mybatis;

import org.hamcrest.Matcher;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class) // Junit4 실행
@SpringBootTest
class MybatisApplicationTests {

	@ClassRule
	public static OutputCaptureRule out = new OutputCaptureRule(); // Console에 나오는 값 가져오기

	@Test
	void contextLoads() {
		out.expect(containsString("1,San Francisco,CA,US")); // Test 성공 시 내보내는 값
//		Matcher<String> matcher = containsString("1,San Francisco,CA,US");
//		System.out.println("QQQ: " + matcher);
//		out.expect(matcher);
	}

}
