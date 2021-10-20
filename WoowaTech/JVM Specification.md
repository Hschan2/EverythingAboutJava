# JVM Specification

## JVM
<b>표준</b>이란 무엇일까? 여기서 표준이란 누구든 ```무엇```의 표준을 만족하는 <b>무언가</b>를 만든다면 그 <b>무언가</b>는 ```무엇```인 것을 말한다.   

<b>JVM</b>은 표준의 역할을 하는 Specification이 존재한다. (거대 기관에서 표준으로 인정받지 못해 Standard라는 표현을 사용하지 못한다.)   

그래서 <b>De Facto Standard</b>. 즉, 높은 영향력으로 사실상 표준에 해당한다.   

이를 활용한 구현체들은 다음과 같다.   
* Oracle - HotSpot
* IBM - Open J9
* AZUL SYSTEMS - Zulu
* MANCHESTER 1824 - Maxine
* Oracle - GraalVM

여기까지 표준을 다시 말하자면 표준이란 누구든 ```JVM```의 표준을 만족하는 <b>무언가</b>를 만든다면 그 <b>무언가</b>는 ```JVM```인 것을 말한다.   

그렇다면 무언가라는 것은 무엇일까? 아마도 소프트웨어가 나와야 할 것이다.   

```JVM```은 Class 파일을 읽어 실행할 수 있는 추상적(가상) 컴퓨터이며 이론적으로 전체에 대한 하드웨어 구현도 가능하다.   

즉, 정리하자면, 누구든 JVM을 만들 수 있고 구현된 형태에 대해서는 어떠한 제약도 없다는 것이다.   

가장 유능한 사람들이 작성한 JVM와 관련된 문서가 있다. 이 문서에는 Garbage-Collection과 관련된 내용이 포함되어 있지 않다. 그 이유는 구현의 창의성을 막을 수 있기 때문이라고 한다.   

즉, 예로 들자면, 데이터 영역의 메모리 레이아웃이나 Garbage-Collection에 사용되는 알고리즘, JVM 명령어 실행과 관련된 내부의 최적화 (예. 기계어로 번역하는 과정)에 관한 것들은 <b>구현자의 재량</b>이라는 것을 말한다.   

여기서 떠올릴 수 있는 것은 <b>추상</b>이다. 추상은 필요한 최소한의 역할만을 외부에 명시함으로써 구현자의 개별적인 역량을 가능한 저해하지 않을 수 있다.   

그래서 <b>JVM 표준</b>도 JVM이라면 반드시 수행해야 할 필요 최소한의 투입 값과 결과만을 명시하여 이를 구현하는 기업체들은 높은 기술력 (테크닉)을 사용해서 뛰어난 JVM을 만들어낼 수 있었다. 결과적으로 <b>Garbage-Collection 알고리즘</b>과 <b>JIT 컴파일러</b>가 구현되었다.   

## 정리
* JVM은 표준의 역할을 하는 문서가 존재하여 누구나 만들 수 있고 구현된 형태에 대해서는 제약이 없다.
* JVM Specification 문서는 상당히 많은 부분들이 추상적으로 작성되어 있다.
* JVM은 추상화 / 다형성으로부터 얻을 수 있는 장점을 폭 넓게 누릴 수 있도록 고안되었다.
* 현재 JVM은 폭 넓은 재량 하에 이루어진 구현자들의 고민과 노력의 산물이다.

[JVM Specification (우아한 테크 유튜브)](https://www.youtube.com/watch?v=6reapO0gLPs)