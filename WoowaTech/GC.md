# GC

## GC가 필요한 이유
GC는 메모리 관리 기법 중 하나이다. <b>프로그램이 동적으로 할당했던 메모리 영역</b>(Heap 영역) 중 <b>필요 없는 영역</b>(어떤 변수도 가리키지 않는 영역)을 알아서 해제시키는 역할을 한다.   

* 장점
    * 메모리 누수를 방지
    * 해제된 메모리에 접근 불가
    * 해제한 메모리 다시 해제 불가
* 단점
    * GC 작업은 순수 오버헤드
    * 개발자는 언제 GC가 메모리를 해제하는지 모름   

## GC 알고리즘
### Reference Counting
들어가기 전, <b>Root Space</b>는 스택 변수, 전역 변수 등 Heap 영역 참조를 담은 변수를 말한다.   

<b>Reference Counting</b>은 Heap 영역에 선언된 객체들이 각각 Reference Count라는 별도 숫자를 가지고 있다라고 생각하면 된다.   

* Reference Count: 몇 가지 방법으로 해당 객체에 접근할 수 있는가. 해당 숫자가 0이면 GC가 필요하다.   

Reference Counting는 순환 참조 문제가 존재한다. Heap Space의 객체들이 서로 이어지고 있어 Count가 1로 유지가 된다. 그래서 사용하지 않는 메모리 영역을 해제하지 못하고 메모리 누수가 발생한다.

### Mark And Sweep
루트에서부터 해당 객체에 접근이 가능한지를 해제의 기준으로 삼는다. 루트부터 그래프 순회를 통해서 연결된 객체를 찾아내는 것을 <b>Mark</b>라고 하며 연결이 끊어진 객체들을 지우는 방식을 <b>Sweep</b>이라고 한다.   

해당 방식을 사용하면 루트로부터 연결이 끊어진 순환 참조되는 객체들도 모두 지울 수 있다.

* Mark And Sweep 특징
    * 의도적으로 GC를 실행시켜야 한다.
    * 애플리케이션 실행과 GC 실행이 병행되어야 한다.   

## JVM의 GC
### JVM 구조
* 바이트코드를 읽고 클래스 정보를 메모리의 Heap / Method Area에 저장하는 클래스 로더
* 실행중인 프로그램의 정보가 올라가 있는 메모리
* 바이트 코드를 네이티브 코드로 변환시켜 주고 GC를 실행하는 실행 엔진   

JVM은 OS로부터 메모리를 할당 받은 후에 해당 메모리를 용도에 다라서 여러 영역으로 나누어서 관리한다. 이는 총 5가지로 나뉘어지며 크게 2가지로 분류할 수 있다.   

* 모든 쓰레드가 공유하는 영역
    * Method Area: 메타 데이터처럼 가지고 메서드의 코드들을 저장
    * Heap: 애플리케이션 실행 중 생성되는 객체 인스턴스를 저장 (GC에 의해 관리)
* 각 쓰레드마다 고유하게 생성되고 쓰레드 종료 시 소멸되는 스택 영역
    * JVM Language Stacks: 메서드 호출을 스택 프레임이라는 블록으로 쌓고 로컬 변수, 중간 연산 결과들이 저장
    * PC Register: 스택 프레임의 주소 저장
    * Native Method Stack: Low Level 코드를 실행   

### Root Space
* Stack의 로컬 변수
* Method Area의 Static 변수
* Native Method Stack의 JNI 참조

### Heap 영역
* Young Generation
    * Minor GC
        * Eden: 새롭게 생성된 객체들이 할당되는 영역
        * Survival 0: GC로부터 살아남은 객체가 존재하는 영역
        * Survival 1: GC로부터 살아남은 객체가 존재하는 영역
* Old Generation
    * Major GC   

Young Generation에서 Survival의 특징으로는 Survival 0 혹은 Survival 1 중 하나는 비어있어야 한다.   

여기서 오래 남아있는 객체일 경우, 오래 참조되었다고 인식되어서 이 후 Old Generation 영역으로 이동되어 진다. (이 과정은 Promotion이라고 한다.)   

만약 Old Generation 영역이 가득 찰 경우, Mark and Sweep 방식을 통해 필요없는 메모리를 비운다. 그러나 이 방식은 시간이 오래 걸린다. 그래서 되도록 Young Generation 영역에서 최대한 처리하도록 한다.   

### Stop the World
<b>Stop the World</b>는 GC를 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 것을 말한다. (Stop the World의 시간을 최소화하는 것이 좋다.)   

### GC 방식 - Serial GC
* 하나의 쓰레드로 GC를 실행한다.
* Stop the World의 시간이 길다.
* 싱글 쓰레드 환경 및 Heap이 매우 작을 때 사용한다.   

### GC 방식 - Parallel GC
* 여러 개의 쓰레드로 GC를 실행한다.
* 멀티코어 환경에서 사용한다.
* Java 8의 Default GC 방식이다.   

### GC 방식 - CMS GC
* Stop the World의 최소화를 위해 고안되었다.
* GC 작업을 애플리케이션과 동시에 실행한다.
* G1 GC 등장에 따라 더 이상 사용되지 않는다.   

### GC 방식 - G1 GC
* Garbage First
* Heap을 지역으로 나누어 사용한다. (어떤 영역은 Young Generation, 어떤 영역은 Old Generation | Stop the World 최소화 가능)
* Java 9부터 Default GC 방식이다.   

## GC 튜닝 예시
* 목표
    * Old Generation으로 넘어가는 객체 최소화하기
    * Major GC 시간을 짧게 유지하기
* 튜닝 과정
    * GC 상태 모니터링하기
    * 알맞은 GC 방식과 메모리 크기 설정하기
    * 적용하기
* 기본 제공인 Jstat Tool 사용하여 JVM 모니터링하기
* Heap 크기 설정
    * -Xms: JVM 시작 시 힙 영역의 크기
    * -Xmx: 최대 힙 영역 크기
* New 영역이 크기 설정
    * -XX: NewRatio: New 영역과 Old 영역의 비율
        * -XX: NewRatio = 1: New 영역:Old 영역 == 1:1
        * -XX: NewRatio = 2: New 영역:Old 영역 == 1:2
    * -XX: NewSize: New 영역의 크기
    * -XX: SurvivorRatio: Eden 영역과 Survivor 영역의 비율
* GC 실행 방식 설정
    * -XX: +UseSerialGC: Serial GC 사용
    * -XX: ParallelGCThreads = Value: Parallel GC 사용
    * -XX: +UseParallelOldGC: Parallel GC + Compacting
    * -XX: +UseGMSInitialingOccupancyOnly: CMS GC 사용
    * -XX: +UseG1GC: G1 GC 사용

[GC](https://www.youtube.com/watch?v=FMUpVA0Vvjw)