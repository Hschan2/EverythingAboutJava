# Deadlock (교착 상태)
문서상의 정의는 ```두 개 이상의 작업이 서로 상대방의 작업이 끝나기 만을 기다리고 있기 때문에 결과적으로 아무것도 완료되지 못하는 상태```를 말한다.   

A 프로세스가 A 리소스를 점유하고 B 프로세스가 B 리소스를 점유하고 있을 때, A 프로세스가 B 리소스에 접근을 B 프로세스가 A 리소스에 접근을 무기한 대기하고 있을 때 발생하는 것을 <b>Deadlock, 교착 상태</b>라고 한다.   

즉, 둘 이상의 프로세스 혹은 스레드가 <b>한정된 자원</b>을 얻지 못해 다음 차례를 진행하지 못하는 상태이다.   

## Deadlock 발생 조건
* 상호 배제 (Mutual Exclusion): 하나의 공유 자원에 대해 두 개 이상의 프로세스가 동시에 접근할 수 없다.
* 점유 대기 (Hold and Wait): 하나의 자원을 점유하고 있는 프로세스가 있고 다른 프로세스에서 자원을 얻기 위해서는 요청하고 대기해야 한다.
* 비선점 (No Preemption): 특정 프로세스가 어떤 자원을 사용하고 있을 때 해당 자원 사용이 끝나기 전에 자원을 뺏을 수 없다.
* 순환 대기 (Circular Wait): 프로세스들이 서로 사용하고 있는 자원에 대해 순환적을 대기하고 있는 형태다.   

위의 네 가지 조건을 모두 만족하면 교착 상태가 발생한다.

## Deadlock 해결 방법
* 교착 상태 예방
* 교착 상태 회피
* 교착 상태 탐지 및 회복
* 교착 상태 무시

### 교착 상태 예방
교착 상태가 발생되지 않도록 사전에 예방하는 방법이다. 네 가지 발생 조건 중에서 <b>하나</b>를 제거함으로써 해결할 수 있다.   

예방 방법은 일반적으로 자원 사용이 효율적이지 않고 비용이 많이 든다.   

* 상호 배제 조건 제거: 하나의 공유 자원에 여러 프로세스가 접근 가능한 것 (구현 등 현실적으로 힘듦)
* 점유 대기 조건 제거: 해당 리소스를 미리 요청하고 할당받은 다음 작업을 수행 (자원의 효율성 하락, 오버헤드 발생)
* 비선점 조건 제거: 다른 프로세스의 자원을 할당받기 위해서는 자신의 자원을 반납하는 것 (작업했던 프로세스 상태 손실 가능성 존재)
* 순환 대기 조건 제거: 각각 자원들에 대해 고유번호를 할당하고 특정 프로세스 내 자신이 할당받은 자원에 번호를 기준으로 오름차순 혹은 내림차순으로만 요청 가능한 것

### 교착 상태 회피
교착 상태 발생 가능성을 검사하여 <b>발생 가능성이 있다면 사전에 회피</b>하는 방식이다.   

* 자원 할당 그래프 알고리즘 (Resource Allocation Graph Algorithm): 그래프 상에 교착 상태를 유발시키는 <b>순환 사이클</b>의 존재 유무를 확인
* 은행원 알고리즘 (Banker's algorithm)   
   
* 전반적인 플로우
    * 프로세스가 자원 요청 시, 자원을 할당한 후에도 안정 상태로 남아있는지 사전 검사
    * 안정 상태라면 자원 할당
    * 불안정 상태일 때는 다른 프로세스가 자원을 해지할 때까지 대기   

자원을 요청할 때마다 시스템 상태를 검사하는 만큼 <b>오버헤드</b>가 크다. 은행원 알고리즘일 경우에는 <b>전제 조건</b>이 많다.   

### 교착 상태 탐지 및 회복
교착 상태를 허용하지만 <b>상태를 탐지하고 회복</b>하는 방식이다. 알고리즘을 주기적으로 실행함으로써 시스템에 발생한 교착 상태를 확인하고 회복한다.   

교착 상태를 일으킨 프로세스를 종료하거나 할당된 자원을 해제함으로써 회복한다.   

* 방법
    * 프로세스 종료
        * 교착 상태의 프로세스를 모두 중지한다.
        * 교착 상태가 제거될 때까지 한 프로세스씩 중지한다.
        * 작업했던 내용을 유실할 수 있는 가능성이 있다.
    * 자원 선점
        * 교착 상태가 제거될 때까지 프로세스가 점유한 자원을 섡머하여 다른 프로세스에 할당한다.
        * 주기적으로 확인해야 하기 때문에 오버헤드 발생 가능성이 있다.   

* 회복 고려 사항
    * 희생자 선택: 교착 상태에서 회복할 때 어떤 프로세스를 죽일 것인가 혹은 어떤 프로세스의 자원을 선점하여 다른 프로세스에 할당할 것인가 (다양한 파라미터 혹은 가중치 등을 고려하여 선택)
    * 후퇴 (Rollback): 프로세스를 완전히 죽이고 새로 다시 킬 것인가 혹은 재시작할 것인가 혹은 교착 상태가 회복될 때까지 롤백할 것인가
    * 기아 상태 (Starvation)

### 교착 상태 무시
교착 상태 자체를 무시하고 특별한 조치를 취하지 않는 방법이다. 교착 상태의 발생 확률이 낮은 상황에서 주로 사용한다. 주로 운영체제에서 사용한다.   

교착 상태가 발생했을 때 시스템을 다시 시작하거나 사용자가 직접 프로세스를 죽이는 방식을 사용한다.   

[Deadlock](https://www.youtube.com/watch?v=Ry_gB34cvwc)