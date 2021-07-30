package com.java.SpringBatch;

import java.awt.List;

public class SpringBatch_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Spring Batch
//		
//		배치 애플리케이션은?
//			개발자가 정의한 작업을 한 번에 일괄 처리하는 애플리케이션
//		
//		배치 애플리케이션을 적용한 것은?
//			매출 데이터를 이용한 일매출 집계
//			매우 큰 데이터를 활용한 보험급여 결정
//			트랜잭션 방식으로 포맷, 유효성 확인 및 처리가 필요한 내부 및 외부 시스템에서 수신한 정보를 기록 시스템으로 통합
//			웹 크롤러 (대용량 기반 데이터를 활용한 크롤링, 사용자의 접속이 거의 없는 시간대에 주기적으로 시도)
//			메일 구독 서비스 (정해진 시간에 구독을 신청한 회원에게 일괄 전송, 다른 정보를 열람하는 등 다른 서비스에 영향을 주지 않음)
//			등
//			
//		배치 애플리케이션이 필요한 상황
//			일정 주기로 실행해야 할 때
//			실시간 처리가 어려운 대량의 데이터를 처리해야 할 때
//			-> 이런 작업을 하나의 애플리케이션에서 수행하면 성능 저하를 유발할 수 있어 배치 애플리케이션으로 구현
//			
//		배치 애플리케이션 조건
//			대용량 데이터 - 배치 애플리케이션은 대량의 데이터를 가져오거나 전달하거나 계산하는 등의 처리를 할 수 있어야 함
//			자동화 - 배치 애플리케이션은 심각한 문제 해결을 제외하고는 사용자 개입 없이 실행되어야 함
//			견고성 - 배치 애플리케이션은 잘못된 데이터를 충돌/중단 없이 처리할 수 있어야 함
//			신로성 - 배치 애플리케이션은 무엇이 잘못되었는지 추적할 수 있어야 함 (로깅, 알림)
//			성능 - 배치 애플리케이션은 지정한 시간 안에 처리를 완료하거나 동시에 실행되는 다른 애플리케이션을 방해하지 않도록 수행되어야 함
//		
//		배치와 스케쥴러의 차이
//			Spring Batch vs Quartz
//			
//			스케쥴링 = 매 시간 혹은 지정한 시간에 지정한 동작을 수행
//			(Spring Batch는 스케쥴링 프레임워크가 아니다)
//			
//			배치 애플케이션의 절대적인 목적은 대용량 데이터 처리
//			배치 프레임워크에서 스케쥴링 기능을 제공하지 않음
//			스케쥴링 프레임워크는 배치를 도와주는 보안제 역할
//		
//		배치 도메인 용어
//			배치 작업은 Job이라고 함
//			JobLauncher Job Step <---> JobRepository (JobLauncher Job Step의 정보를 관리하는 것)을 생성해야 한다
//			
//			Job = Job 이름을 정의, Step을 정의하고 순서를 정의, Job의 재사용 가능성을 정의
//			JobInstance = 논리적으로 Job을 실행, JobParameters를 이용하여 구분, JobInstance = Job * Identifying JobParameters
//			JobExecution = Job을 실행하는 단일 시도, 실패했던 JobInstance에 대해 새로운 실행을 하면 새로운 JobExecution이 생성
//			
//			JobExecution Properties
//				BatchStatus : 실행 상태, 실행중이면 started, 실패하면 failed, 성공하면 completed
//				ExitStatus : 실행 결과, ExitCode를 포함
//			
//			Step
//				Job은 하나 이상의 Step으로 구성될 수 있다
//				배치 작업의 독립적이고 순차적인 단계를 캡슐화하는 도메인 객체
//				Step의 내용은 개발자의 재량으로 복잡하거나 단순하게 구현 가능
//				
//			JobExecution
//				Status : 실행 상태
//				ExitStatus : 실행 결과
//				ReadCount, WriteCount, CommitCount, RollbackCount, FillterCount 등 실행에 대한 정보를 담고 잇음
//				
//			JobRepository : Job, Step 구현을 위한 CRUD 작업을 제공
//			JobLauncher : Job을 시작하기 위한 간단한 인터페이스, 구현 시 JobRepository에서 유효한 JobExecution을 획득하고 Job을 실행
//							-> Spring Batch 사용 시 제공 받음
//							
//			Item : 작업에 사용하는 데이터
//			ItemReader : Step에서 한 항목씩 검색. 모든 항목이 소진된 경우 Null 반환
//			ItemWriter : 여러 출력 항목을 나타냄
//			ItemProcessor : 비즈니스 처리 담당. 항목이 유효하지 않다고 판단되는 경우 Null 반환
//			
//		스프링 배치 활용
//			Tasklet과 Chunk가 있고 주로 Chunk를 자주 사용하게 될 것
//			Chunk : 각 커밋 사이에 처리될 row(Item)의 수. 성공 시 Chunk만큼 커밋, 실패 시 Chunk만큼 롤백
//			
//			자바 코드로 이해하기
//			```
//			List items = new Arraylist();
//			for(int i = 0; i > commitInterval; i++) { commitInterval => Chunk 사이즈
//				Object item = itemReader.read();
//				Object processedItem = itemProcessor.process(item);
//				items.add(processedItem);
//			}
//			itemWriter.write(items);
//			```
//			itemReader : Cursor와 Paging 기능을 제공
//			Cursor : 데이터 베이스와 연결을 유지한 채 한 개 씩 가져오는 것
//			Paging : 한 번 연결을 할 때 정해진 페이지 만큼 데이터 가져오는 것
//			
//			Chunk -> ItemReader -> ItemProcessor -> ItemWWriter
//			data
//			
//			Chunk with data : 메모리에 page 사이즈 만큼 데이터 가져오기. Chunk Size == Page Size 권장
//			ItemReader -> ItemProcessor -> ItemWWriter : Chunk를 하나의 트랜잭션에서 처리 -> JPA의 양속성 컨텍스트 사용 가능
//														Chunk Size > Page Size일 때 하나의 트랜잭션 처리를 위해 여러 번 조회하는 문제점 발생
//			
//			ItemWriter : Chunk 단위 만큼 한 번에 작업 수행
//			
//			ItemProcessor(Optional) : Chunk-oriented Tasklet을 구성할 때 선택 요소, 데이터를 가공 및 필터링하는 역할 -> Writer에서도 구현 가능한 역할 -> 비즈니스 코드가 섞이는 것을 방지,
//									Step에 여러 로직이 필요할 때 도입을 고려해 유지 보수성을 증가, 데이터 처리를 실패했을 때 Null을 반환해 Writer에 전달되지 않음
//									
//			Controlling Step Flow
//			```
//			@Bean
//			public Job job() {
//				return this.jobBuilderFactory.get('job')
//						.start(stepA())
//						.next(stepB())
//						.next(stepC())
//						.bulid();
//			}
//			```
//			
//			```
//			@Bean
//			public Job job() {
//				return this.jobBuilderFactory.get('job')
//						.start(stepA())
//						.on('*').to(stepB())
//						.from(stepA()).on('Failed').to(stepC())
//						.end()
//						.build();
//			}
//			```
//			=> BatchStatus가 아닌 ExitStatus로 판단 -> 실행 상태가 아닌 실행 결과로
//			
//		배치 애플리케이션 운영
//			테스트 코드를 반드시 작성
//				QA를 하기 어렵기 때문에 테스트 코드가 필요
//				복잡한 쿼리를 실행한 결과를 처리하고 다시 데이터 베이스에 저장하는 작업이기 때문에 통합 테스트를 실행
//				보통 단위 테스트를 이용해서 내부 작업을 검사하고 전체 테스트 코드를 반드시 작성
//				```
//				JobExecution jobExecution = myTestJobLauncher.launchJob();
//				List<StepExecution> stepExecutions = new ArrayList<>(jobExecution.getStepExecutions());
//				StepExecution stepExecution = stepExecutions.get(0);
//				
//				assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo('FAILED');
//				assertThat(jobExecution.getWriteCount().isEqualTo(2);
//				assertThat(jobExecution.getRollbackCount().isEqualTo(1);
//				```
//			
//			관리 도구
//				Cron : 리눅스 작업 스케쥴러
//				Spring MVC + API Call : 권장하지 않음
//				Spring Batch Admin : Deprecated
//				Quartz + Admin : 스케쥴러 프레임워크 + 관리자 페이지 구현
//				CI Tool(Jenkins) : 자주 사용해서 운영
//				
//				Jenkins 장점
//					Integration(Slack, Email 등)
//					실행 이력 및 로그 관리
//					다양한 실행 방법 존재 (Rest API, 스케쥴링, 수동 실행)
//					파이프라인 : 배치의 잡을 순차적으로 실행할 수 있도록 도와줌, Job 내부에 Step을 여러 개 설계하는 것보다 권장 => Job을 단독으로 실행할 수 있도록 설계하는 것이 유지 보수에 훌륭
//					공통 설정 관리가 쉬움
//			
//				Chunk 최적화
//					구체적인 가이드가 존재하지 않음
//					설계한 비즈니스 로직에 대해서 가장 효율적인 단위를 설정해야 함
//					여러 개의 배치 작업을 구성하는 경우 다른 배치 작업에 영향을 주는 것은 X -> 스프링 배치에서 Chunk 사이즈만큼 메모리에 데이터를 적재해야 하기 때문에 다른 배치에서 사용할 수 있는 메모리가 줄어들 수 있음
//					
//		요약
//		배치 애플리케이션의 절대적인 목적은 대용량 데이터 처리
//		배치와 스케쥴링의 차이를 이해할 것 -> 스케쥴링은 배치의 보완제 역할
//		배치 애플리케이션의 기본 구성은 JobLauncher, Job, Step으로 구성 -> 스프링 배치와 같은 프레임워크를 이용해서 비즈니스 로직에 집중할 수 있음
//		다양한 방법으로 배치 애플리케이션을 운영할 수 있고 설계한 비즈니스 로직에 맞춰서 선택할 것
			
	}

}
