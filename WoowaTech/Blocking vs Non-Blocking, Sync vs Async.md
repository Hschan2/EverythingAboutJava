# Blocking vs Non-Blocking, Sync vs Async

## Blocking vs Non-Blocking
* Blocking: 자신의 작업을 진행하다가 다른 주체의 작업이 시작되면 다른 작업이 끝날 때까지 기다렸다가 자신의 작업을 시작하는 것

* Non-Blocking: 다른 주체의 작업에 관련없이 자신의 작업을 하는 것   

즉, 다른 주체가 작업할 때, 자신의 <b>제어권</b>이 있는지 없는지로 볼 수 있다.   

## Synchronous vs Asynchronous
* Synchronous: 동기라는 뜻이며 작업을 동시에 수행하거나, 동시에 끝나거나, 끝나는 동시에 시작함을 의미

* Asynchronous: 비동기라는 뜻이며 시작, 종료가 일치하지 않으며, 끝나는 동시에 시작을 하지 않음을 의미   

즉, 결과를 돌려주었을 때, 순서와 결과에 관심이 있는지 아닌지로 판단할 수 있다.   

## Blocking & Synchronous
작업이 시작되는 동안 동작하지 않는다. 그리고 결과를 반환하면 해당 업무를 바로 처리하게 된다.   

자바에서 입력을 요청할 때, 주로 사용이 된다.

## Blocking & Asynchronous
자신의 작업에 대한 제어권이 없다. 그리고 결과를 바로 처리하지 않아도 된다. 굳이 비동기를 Block과 사용하는 이유는 개발자가 Non-Blocking & Asynchronous로 사용하려다가 실수로 사용하는 경우가 있다.

## Non-Blocking & Synchronous
다른 작업이 있어도 자신의 제어권을 가지고 일을 한다. 중간중간 요청에 대해 결과를 물어보며 업무를 계속 다른 작업을 수행한다. 결과가 끝이나서 반환이 되면 다른 작업을 끝나고 나서 반환받은 작업을 수행한다.   

Blocking & Synchronous와 크게 다르지 않다. Blocking & Synchronous는 맵을 가져올 때 사용한다.

## Non-Blocking & Asynchronous
다른 작업이 시작되어도 자신이 하고 있는 작업은 멈추지 않는다. 요청한 작업과 다른 작업은 각자 작업을 알아서 처리하게 된다. 요청한 작업에서 끝난 결과를 바로 처리하지 않고 자기의 작업이 끝나게 되면 그 때 처리를 하게 된다.   

대표적으로 자바스크립트에서 API 요청을 하고 다른 작업을 하다가 콜백을 통해서 추가적인 작업을 처리할 때 사용한다.

## 정리
* Blocking vs Non-Blocking: 제어의 관점
* Synchronous vs Asynchronous: 순서와 결과(처리)의 관점

[출처](https://www.youtube.com/watch?v=oEIoqGd-Sns)