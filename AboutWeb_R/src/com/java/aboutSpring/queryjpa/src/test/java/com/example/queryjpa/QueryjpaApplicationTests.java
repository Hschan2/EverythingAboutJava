package com.example.queryjpa;

import com.example.queryjpa.Entity.Staff;
import com.example.queryjpa.Entity.Store;
import com.example.queryjpa.Repository.StaffRepository;
import com.example.queryjpa.Repository.StoreRepository;
import com.example.queryjpa.Repository.StoreRepositorySupport;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class QueryjpaApplicationTests {

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private StoreRepositorySupport storeRepositorySupport;

	// 하나만 조회하기
	@Test
	void findOneByNameTest() {
		// 필요한 데이터 선언 및 저장
		final Long id = 5L;
		final String address = "주소5";
		final String name = "스토어5";

		Store store = Store.builder()
				.id(id)
				.address(address)
				.name(name)
				.build();

		storeRepository.save(store);

		// querydsl로 값 조회
		Store resultByStore = storeRepositorySupport.findOneByName(name);

		// 데이터 검증(조회한 값과 데이터 베이스에 있는 값 비교)
		Assertions.assertEquals(name, resultByStore.getName());
	}

	@Test
	void findStaffByNameTest_Entity관계매핑안되어있는경우() {
		// 테스트에 필요한 데이터 선언 및 저장
		final Long staffId1 = 10L;
		final String staffName1 = "staffName4";
		final Integer age1 = 30;

		final Long staffId2 = 11L;
		final String staffName2 = "staffName5";
		final Integer age2 = 20;

		final Long id = 8L;
		final String address = "주소6";
		final String name = "스토어6";

		Staff staff1 = Staff.builder()
				.id(staffId1)
				.name(staffName1)
				.age(age1)
				.storeId(id)
				.build();

		Staff staff2 = Staff.builder()
				.id(staffId2)
				.name(staffName2)
				.age(age2)
				.storeId(id)
				.build();

		staffRepository.saveAll(Arrays.asList(staff1, staff2));

		Store store = Store.builder()
				.id(id)
				.address(address)
				.name(name)
				.build();

		storeRepository.save(store);

		// querydsl로 값 조회
		// 관계 매핑일 경우: join(store.staff, staff)
		// 관계 없을 경우: join(staff).on(store.id.eq(staff.storeId)
		List<Staff> staffs = storeRepositorySupport.findStaffsByName(name);

		// 데이터 검증 (비교)
		assertThat(staffs.size()).isGreaterThan(0);
		assertThat(staffs.get(0).getName()).isEqualTo(staffName1);
		assertThat(staffs.get(1).getName()).isEqualTo(staffName2);
	}

//	QueryDSL 적용 전
//	@Test
//	void entity저장후조회() {
//		final Long id = 1L;
//		final String storeName = "스토어_1";
//		final String storeAddress = "주소_1";
//
//		// Given, Builder 생성
//		// storeRepository로 저장
//		Store store = Store.builder()
//				.id(id)
//				.name(storeName)
//				.address(storeAddress)
//				.build();
//		storeRepository.save((store));
//
//		// When, storeRepository에서 name으로 조회
//		Store resultStore = storeRepository.findByName(storeName);
//
//		// 결과, 저장한 데이터와 조회된 데이터 비교
//		Assertions.assertEquals(resultStore.getName(), storeName);
//	}
//
//	@Test
//	@DisplayName("초기 테스트 시, Entity저장후조회 테스트 먼저 진행 후 테스트")
//	void entity수정() {
//		final Long id = 1L;
//		final String storeName = "스토어_2";
//		final String storeAddress = "주소_2";
//
//		Store store = Store.builder()
//				.id(id)
//				.name(storeName)
//				.address(storeAddress)
//				.build();
//
//		// When
//		Store updateStore = storeRepository.save(store);
//
//		// 결과
//		Assertions.assertEquals(updateStore.getName(), storeName);
//	}
//
//	@Test
//	@DisplayName("Store, Staff Entity 저장")
//	void entity저장() {
//		final Long storeId = 2L;
//		final String storeName = "store_1234";
//		final String storeAddress = "storeAddress";
//
//		final Long staffId = 1L;
//		final String staffName = "staff_1234";
//		final Integer staffAge = 30;
//
//		// 일대다 관계로 Store : Staff의 데이터 저장 방법
//		Staff staff = Staff.builder()
//				.id(staffId)
//				.name(staffName)
//				.age(staffAge)
//				.build();
//
//		Store store = Store.builder()
//				.id(storeId)
//				.name(storeName)
//				.address(storeAddress)
//				.build();
//
//		// Staff 객체 생성 후 Store 객체에 넣어 생성 후 Repository를 이용해 저장하면 Store, Staff 테이블에 데이터 적재
//		Store saveStore = storeRepository.save(store);
//
//		Assertions.assertEquals(saveStore.getName(), storeName);

		// 결과값 Store에서 Staff값을 조회. result.getStaff()
//		Collection<Staff> staff1 = saveStore.getStaff();
//		Iterator<Staff> iterator = staff1.iterator();
//		Staff next = iterator.next();
//		Assertions.assertEquals(next.getName(), staffName);
//	}
}
