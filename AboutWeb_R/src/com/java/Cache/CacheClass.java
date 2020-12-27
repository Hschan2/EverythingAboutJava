package com.java.Cache;

public class CacheClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		캐시
//		데이터나 값을 미리 복사해 놓는 임시 저장소
//		
//		언제 사용?
//		원본 데이터에 접근하는 시간이 오래 걸리는 경우
//		값을 다시 계산하는 시간을 절약하고 싶은 경우
//		
//		왜 사용?
//		데이터를 미리 캐시에 복사해 놓으면 계산 혹은 접근 시간 없이 빠른 속도로 데이터에 접근 가능
//		속도는 높이고 비용은 낮추고
//		
//		캐시는 컴퓨터 전반에 걸쳐 사용
//		
//		1. HTTP 캐시
//			클라이언트가 서버에 리소스를 요청했을 때 서버에 저장되어 있는 자바스크립트와 CSS가 같이 호출
//			그러나 서버 안에 리소스가 변경이 된다면 문제 발생
//			서버는 리소스를 응답할 때 해당 리소스를 캐싱할 수 있는 시간을 명시해서 전달 (캐시컨트롤이라는 HTTP 헤더에)
//			서버는 헤더에 있는 Cache-Control에 시간을 명시 (max-age = 최대 시간) 
//			다만 이 방법만으로는 부족할 수 있음 (만료 시간이 되었더라도 캐시 업데이트가 필요 없을 수 있다)
//			만료 시간 이후 다시 요청이 갈 때 서버의 데이터 변경이 없다는 것
//			즉, 브라우저 캐시와 서버 캐시가 같다
//			이 과정은 반복되기 때문에 네트워크 비용에서 불필요함이 발생 => 비효율적
//			
//			ETag (문제 해결을 위해 사용)
//			데이터의 해시 값
//			리소스를 응답할 때 해싱 알고리즘을 돌려 해시값을 얻어내고 해시 값이 같으면 데이터가 같고 아니면 다르다
//			데이터의 내용을 기준으로 해시값을 만드는 것. 만든 해시 값을 ETag에 담아 헤더에 보낸다
//			요청할 때 If-None-Match에 해시 값을 담아 보내고 있으면 같은 리소스르 보내고 아니면 X
//			리소스가 업데이트 되었을 때 해시 값도 바뀐다. 바뀐 리소스를 요청할 때 200 OK이라는 문구와 함께 응답
//			
//			정리
//			서버는 리소스 요청이 오면 ETag HTTP 헤더를 사용해 유효성 검사 토큰을 전달
//			브라우저 캐시 내 리소스가 만료되면 ETag를 서버에 보내 해당 리소스에 변경 사항이 있는지 확인
//			ETag 값이 같으면 변경이 없다는 의미로 캐시에 있는 것 그대로 응답
//			ETag 값이 다르면 변경 사항이 있다는 의미로 업데이트된 리소스가 새로운 ETag값을 응답
//			ETag 값이 같으면 이미 있는 캐시 정보를 재사용 (변경도지 않은 리소스를 서버에서 다운X) => ETag를 사용하면 효율적인 리소스 업데이트 검사 가능
//			
//			ETag 문제점
//			기존 캐시가 만료되어야 응답 받을 수 있다
//			즉, 브라우저에 캐싱된 리소스는 만료될 때까지 사용! => 만료될 때까지 새로운 요청을 보내지 않겠다
//			예. CSS와 같은 정적파일은 캐시 만료 기간을 길게 잡는다. 만약 하나의 문제가 생겨 CSS 파일을 수정하였을 때 기존 사용자는 만료 기간 이후에 확인 가능 (직접 캐시를 삭제하고 다시 들어가지 않는 한)
//			
//			ETag 문제 해결 방법
//			URL 리소스 변경
//			파일의 디지털 지문이나 버전 번호를 파일 이름에 포함하는 방식으로 수행 (예. style.css -> style.3da37df.css)
//			이런 방식으로 하면 새로운 데이터로 인식해 이를 다운로드
//			
//		2. Application Cache (StringBoot)
//			스트링부트에서 캐싱을 하기 위해서는 의존성을 추가해야 한다 (build.gradle -> spring-boot-starter-cache)
//			사용할 클래스에 @EnableCaching 을 추가
//				1) @Cacheable - 캐시 저장 (예. @Cacheable(value = 'book') -> 서버 메모리에 book이라는 해쉬테이블 생성)
//				2) @CachePut - 캐시 수정 (예. @CachePut(value = 'book', key = '#book.id') -> key값을 book의 id로 찾아 수정)
//				3) @CacheEvict - 캐시 삭제 (예. @CacheEvict(value = 'book', key = '#id') -> id를 이용해 book 해쉬 테이블에서 찾아 삭제)
				
	}

}
