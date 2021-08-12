# G1 GC

## G1 GC 배우기 전 발생한 문제점 예시
특정 상황의 문제점이 발견이 되면 이를 탐지하는 스카우터 플러그인을 이용한 이상 감지 기능을 개발된다.   

그러나 문제점은 1초 이상 걸리는 Full GC가 발생하는 것이 확인되고 간혹 2초 이상 소요되는 Full GC도 발생한다. 이는 트래픽을 감안하면 짧지 않는 시간이다.   

이를 해결하기 위해 Full GC 시간을 감소하고 싶으며 AS-IS는 Parallel GC를 사용하고 있으며 G1 GC 적용을 위한 비교 진행을 실시한다.   

## G1 GC 정의
<b>G1 GC</b>는 Garbage First(G1)을 의미하며 목표는 GC로 인해 발생하는 중지 시간을 짧게 만드는 것이다. 구성은 여러 개의 작은 영역으로 힙으로 구성된다.   

```
Eden 영역 | S 영역 | Old 영역
```

## G1 GC 테스트
G1 GC를 테스트 하기 위해서 같은 기능을 하는 3개의 프로세스로 테스트를 진행한다. 2개는 기존 GC 설정 유지를 하고 나머지 1개는 G1 GC 설정 사용을 한다.   

G1 GC 적용을 위해 단순한 설정을 사용한다. 최대 GC 중지 시간을 설정하고 (-XX: MaxGCPauseMillis) Young 세대 크기 설정을 제거한다. (-Xmn, -XX: NewRatio, -XX: NewSize 등) 그리고 문자열 중복 제거 옵션을 설정한다. (-XX: +UseStringDeduplication)   

## 결과 정리
발생한 문제점의 현재 서비스의 수준에서는 Full GC가 발생하지 않았으며 더 높은 트래픽에서 비교가 필요하다. 그리고 모든 GC 중지 시간 합도 짧았으며 이는 GC 로그 분석 도구 활용이 필요하다. 마지막으로 사용 범위를 넓히는 것이 좋다.

[G1 GC (최범균 유튜브)](https://www.youtube.com/watch?v=H4yuuYXK8_o)