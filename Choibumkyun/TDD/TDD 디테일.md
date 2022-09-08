# TDD 테스트 작성 순서, 기능 명세, 시연

## TDD (Test-Driven Development, 테스트 주도 개발)
* 테스트로부터 시작하는 개발 방식
    * (실패하는) 테스트 코드 작성
    * 테스트를 통과시킬 만큼 구현
    * 코드 정리 (리팩토링)
    * 반복   

### TDD 시작의 어려운 점
<b>테스트 코드를 어디부터 작성해야 하는가?</b>은 가장 어려운 질문이다. 테스트의 작성 순서는 ```쉬움/예외 -> 어려움/정상```으로 가는 것이 좋다.   

### 암호 검사 예시
* Null 값 입력, 빈 값 입력 시 예외적인 것 (정상이 아닌 것) 처리
* 모두 충족하는 경우 -> 가장 쉬운 단계 (첫 번째)
* 겟 중 둘을 충족하는 경우 -> 그 다음으로 쉬운 단계
* ...
* 모두 충족하지 않는 경우 -> 가장 어려운 단계 (마지막)   

### 어려운 단계 혹은 정상적인 테스트부터 시작하면?
* 어려운 단계부터 먼저 하게 된다면?
    * 시간이 오래 걸리고 피드백이 느려진다.
    * 예시. 만약에 다음 순서대로 진행하게 된다면?
        * 대문자 포함 규칙만 충족하는 경우
        * 모든 규칙을 충족하는 경우
        * 숫자를 포함하지 않고 나머지 규칙은 충족하는 경우   

* 예외적인 상황을 뒤에서 테스트를 하게 되면?
    * 이미 구현한 코드 구조에 영향을 준다.
        * 예외 상황은 IF 구조에 영향을 준다.   

### 순서 예시
* 회원 가입의 예시
    * 동일 이메일이 이미 존재한다면? vs 동일 이메일이 존재하지 않으면?
* 만료일 계산의 예시
    * 1월 31일에서 한달 뒤? vs 1월 25일에서 한달 뒤?
* 회원 주소 변경의 예시
    * 회원이 없는 경우? vs 회원이 있는 경우?   

### TDD는 완급 조절이 중요
* TDD로 통과시키는 과정
    * 정해진 값을 Return
    * 값 비교를 통해서 정해진 값을 Return
    * (다양한 테스트를 추가하면서) 구현을 일반화
* 구현이 생각날 경우 빠르게 구현
    * 단, 테스트를 통과시킬 만큼만 구현
    * 앞서 가지 말 것!
* 구현이 막히면 다시 뒤로 돌아가서 천천히 진행   

## TDD 기능과 설계
### 기능
* 기능의 구성: 입력, 결과
    * 예. 로그인 기능
        * 입력: 아이디, 암호
        * 결과: (Return) 일치하면 True, 일치하지 않으면 False
    * 예. 회원 가입
        * 입력: 아이디, 암호, 이름
        * 결과 (Return) 회원 일련 번호, 회원 정보 DG에 저장
* 기능 명세 -> 설계로 연결
    * 이름(입력), 파라미터, Return Type 등 결정   

### TDD 설계
* TDD는 설계를 지원

```
테스트를 만들려면?
-> 테스트할 기능 실행
    -> 클래스, 메서드, 함수 이름
    -> 파라미터
-> 결과를 검증
    -> Return 값
```

### 다른 예. 관리자 차단 여부
* 차단 기능을 실행하면 관리자는 차단 상태로
* 단위 테스트
    * 차단되지 않은 상태
    * 차단하면 차단된 상태
    * 차단된 상태에서 다시 차단하면 예외
    * 차단된 상태에서 해제하면 차단 해제   

```
public class AdminBlockTest {

    @Test
    void notBlocked() {
        Admin admin = new Admin();
        assertThat(admin.isBlocked())
            .isFalse();
    }
}
```

## 예시 코드로 확인
* AdminBlockTest
```
public class AdminBlockTest {

    @DisplayName("새로 생성한 관리자는 차단 상태가 아님")
    @Test
    void newCreatedAdmin_NonBlock() {
        Admin admin = new Admin();
        assertThat(admin.isBlocked()).isFalse();
    }

//    Block 처리할 때
    @Test
    void block() {
        Admin admin = new Admin();
        admin.block();
        assertThat(admin.isBlocked()).isTrue();
    }

//    이미 Block 처리 상태일 때
    @Test
    void alreadyBlocked() {
        Admin admin = new Admin();
        admin.block();
        Assertions.assertThatCode(
                () -> admin.block()
        ).isInstanceOf(AlreadyBlockedException.class);
    }

//    차단도 안되었는데 unBlock할 때
    @Test
    void unBlock_WhenNotBlocked() {
        Admin admin = new Admin();
        Assertions.assertThatCode(() -> admin.unblock())
                .isInstanceOf(NonBlockedException.class);
    }

//    unBlock 하기
    @Test
    void unBlock() {
        Admin admin = new Admin();
        admin.block();
        admin.unblock();
        assertThat(admin.isBlocked()).isFalse();
    }

    public static class Admin {

        private boolean blocked = false;

//        block 기본값
        public boolean isBlocked() {
            return blocked;
        }

//        block 처리
        public void block() {
            if (blocked) {
                throw new AlreadyBlockedException();
            }

            blocked = true;
        }

//        unBlock 처리
        public void unblock() {
            if (!blocked) {
                throw new NonBlockedException();
            }

            blocked = false;
        }
    }
}
```

* NonBlockedException
```
public class NonBlockedException extends RuntimeException {

}
```

* AlreadyBlockedException
```
public class AlreadyBlockedException extends RuntimeException {
    
}
```

[TDD 테스트 디테일](https://www.youtube.com/watch?v=rs_ReNmLISw)