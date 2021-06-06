package com.example.queryjpa;

import com.example.queryjpa.Entity.Staff;
import com.example.queryjpa.Entity.Store;
import com.example.queryjpa.Repository.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class QueryjpaApplicationTests {

	@Autowired
	private StoreRepository storeRepository;

	@Test
	void entity저장후조회() {
		final Long id = 1L;
		final String storeName = "스토어_1";
		final String storeAddress = "주소_1";

		// Given, Builder 생성
		// storeRepository로 저장
		Store store = Store.builder()
				.id(id)
				.name(storeName)
				.address(storeAddress)
				.build();
		storeRepository.save((store));

		// When, storeRepository에서 name으로 조회
		Store resultStore = storeRepository.findByName(storeName);

		// 결과, 저장한 데이터와 조회된 데이터 비교
		Assertions.assertEquals(resultStore.getName(), storeName);
	}

	@Test
	@DisplayName("초기 테스트 시, Entity저장후조회 테스트 먼저 진행 후 테스트")
	void entity수정() {
		final Long id = 1L;
		final String storeName = "스토어_2";
		final String storeAddress = "주소_2";

		Store store = Store.builder()
				.id(id)
				.name(storeName)
				.address(storeAddress)
				.build();

		// When
		Store updateStore = storeRepository.save(store);

		// 결과
		Assertions.assertEquals(updateStore.getName(), storeName);
	}
	
	@Test
	@DisplayName("Store, Staff Entity 저장")
	void entity저장() {
		final Long storeId = 2L;
		final String storeName = "store_1234";
		final String storeAddress = "storeAddress";

		final Long staffId = 1L;
		final String staffName = "staff_1234";
		final Integer staffAge = 30;

		// 일대다 관계로 Store : Staff의 데이터 저장 방법
		Staff staff = Staff.builder()
				.id(staffId)
				.name(staffName)
				.age(staffAge)
				.build();

		Store store = Store.builder()
				.id(storeId)
				.name(storeName)
				.address(storeAddress)
				.staff(Arrays.asList(staff))
				.build();

		// Staff 객체 생성 후 Store 객체에 넣어 생성 후 Repository를 이용해 저장하면 Store, Staff 테이블에 데이터 적재
		Store saveStore = storeRepository.save(store);

		Assertions.assertEquals(saveStore.getName(), storeName);

		// 결과값 Store에서 Staff값을 조회. result.getStaff()
		Collection<Staff> staff1 = saveStore.getStaff();
		Iterator<Staff> iterator = staff1.iterator();
		Staff next = iterator.next();
		Assertions.assertEquals(next.getName(), staffName);
	}
}
