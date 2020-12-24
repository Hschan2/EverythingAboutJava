package com.java.RealtimeWeb;

public class RealtimeWebClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Real Time Web (실시간 웹을 구현하는 기술)
//		인터넷에서 사용자들로 하여금 창작자가 정보를 만들어내는 즉시 수신할 수 있는 기술 혹은 서비스
//		
//		Ajax의 등장
//		사용자 인터페이스 나머지 부분을 방해하지 않고 *비동기로* 데이터를 송신/수신 할 수 있다.
//		전체 페이지를 다시 로딩하는 것이 아니라 일부분만 변경하는 것으로 빠른 화면 전환이 가능
//		
//		Polling (폴링)
//		전송할 데이터의 유무 관계없이 주기적으로 요청 수행하는 방식
//		클라이언트는 지정된 시간 간격에 맞춰 서버에 지속적으로 요청
//		서버는 각 요청마다 가용 데이터나 데이터가 없을 경우 빈 데이터를 보내거나 실패와 같은 응답을 한다.
//		
//		코드 예시
//		const delay = (ms) => new Promise((resolve) => setTimeout(reslove, ms))
//		
//		const fetchPosts = (cursor) => 
//			fetch(`/api/posts/cursor=${cursor}`)
//			.then((response) => response.json())
//			.then(prependPosts)
//			
//		async function polling() {
//			while (true) {
//				await delay(1000)
//				await fetchPosts($posts.firstElemnetChild.dataset.postId)
//			}
//		}
//		fetchPosts(0).tehn(polling)
//		
//		
//		서버 쪽
//		// PostController
//		@GetMapping
//		fun getPosts(@RequestParm cursor: Long): ResponseEntity<List<PostResponse>> {
//			return ResponseEntity.ok(postService.getPosts(cursor))
//		}
//		
//		// postService
//		fun getPosts(cursor: Long = 0L): List<PostResponse> = postResponse {
//			.findByIdGreaterThan(cursor)
//			.map {it.toResponse()}
//		}
//		
//		장단점
//		데이터 유무에 상관없이 요청하여 부필요한 네트워크 비용 발생
//		클라이언트와 서버 자원을 많이 낭비
//		요청의 간격이 길면 실시간성이 떨어짐
//		반대로 간격이 짧으면 많은 자원 소비
//		서버 이벤트가 일정한 주기로 발생하면 효율적
//		
//		Long Polling (롱 폴링)
//		서버 이벤트가 발생할 때까지 응답을 미룸
//		폴링과 다르게 클라이언트 요청에 대해 서버가 전송할 데이터가 있거나 타임아웃이 될 때까지 연결을 끊지 않고 지속
//		서버로부터 응답을 받는 그 즉시 롱 폴링 요청을 다시 수행
//		
//		장단점
//		폴링과 다르게 불필요한 네트워크 비용이 덜 발생
//		서버 이벤트가 발생하는 즉시 응답을 하기 때문에 실시간성이 높음
//		반대로 서버 이벤트가 빈번하게 발생하면 폴링보다 더 많은 요청으 수행
//		
//		Server-sent events (SSE)
//		HTTP를 사용해 서버 푸시를 구현하는 방법
//		HTTP 스트리밍 방식이라고도 하며 SEE 사양에 따라 구현한 방법
//		클라이언트가 요청을 보내면 서버는 무기한 연결을 유지하고 준비가 되면 데이터를 보냄
//		서버 이벤트가 발생하면 응답을 완료하여 연결을 끊는 것이 아니라 응답 스트림에 청크 단위 데이터를 계속 보냄
//		
//		장단점
//		HTTP를 사용하기 때문에 기존 개발 방식과 다르지 않음
//		HTTP 헤더로 인한 오버헤드가 거의 없음
//		연결이 유지되기 때문에 실시간성이 매우 높음
//		연결이 끊긴 경우 재접속 처리를 자동으로 해줌
//		서버에서 클라이언트로 단방향 통신만 가능
//		
//		Websocket (웹소켓)
//		HTTP가 아닌 웹소켓을 사용하여 실시간 양방향 통신을 지원하는 방식
//		HTTP를 통해 웹소켓 프로토콜로 전환하는 handshake 과정이 필요
//		이 후 웹소켓을 통해 통신이 이루어짐 => HTTP의 한계를 넘을 수 있음
//		
//		장단점
//		HTTP를 사용하지 않아 전체 메세지 크기가 줄어듬
//		클라이언트와 서버의 자원을 최소화 가능
//		양방향으로 빠른 요청과 응답이 가능
//		
//		기술 선택
//		Polling
//		서버 개발을 할 수 없는 상황일 때
//		외부 API가 서버 푸시를 지원하지 않는다
//		
//		Long Polling
//		더 이상 사용 하지 않음
//		
//		Server-sent events
//		새로운 데이터를 즉시 수신만 해도 된다
//		알림, 실시간 댓글 등을 구현해야 한다
//		
//		Websocket
//		네트워크 지연을 최소화해야 할 때
//		채팅, 게임과 같이 사용자 간 빠른 피드백이 이루어져야 한다
		
	}

}
