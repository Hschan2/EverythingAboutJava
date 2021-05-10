# Event Loop (이벤트루프)

## Callback Function (콜백 함수)
* 다른 함수의 인자로 넘겨지는 함수
* 콜백 수신 함수에 의해 특정 시점에 실행
* 동기 콜백의 경우 호출 즉시 실행
* 비동기 콜백의 경우 나중에 조건을 만족했을 때 실행
* Event Listener
* Timer / XMLHttpRequest 요청

```
function greeting(name) {
    console.log("안녕, " + name);
}

function processUserInput(callback) {
    const name = prompt("너의 이름은?");
    callback(name);
}

processUserInput(greeting);
```

## 동기적
```
console.log("잘 들어갔어요?");

setTimeout(() => {
    console.log("저기...?");
}, 10000);

console.log("답장 좀 해주세요?");
```
만약 동기적으로 코드가 수행된다고 했을 때, ```잘 들어갔어요?``` = 10초 뒤 => ```저기...?``` => ```답장 좀 해주세요?``` 순으로 실행이 된다. 그냥 보면 문제가 없어보이지만 자바스크립트 코드가 웹 브라우저 환경에서 실행된다는 가정하에 보면 문제가 될 수 있다.   

만약에 중간에 코드가 무한대로 수행된다고 했을 때, 그 다음에 진행될 코드는 이전 코드가 끝날 때까지 기다려야 하는 문제가 발생한다.   

그러나 위의 코드는 비동기적으로 수행되기 때문에 ```잘 들어갔어요?``` => ```답장 좀 해주세요?``` = 10초 뒤 => ```저기...?``` 순으로 실행이 된다. 자바스크립트 엔진이 setTimeout과 같은 코드를 만나면 해당 코드를 잠시 뒤로 보내고 나머지 코드를 실행한다. 그리고 뒤로 보내진 코드가 실행이 된다.   

즉, 이벤트 루프는 이렇게 뒤에서 움직이는 어떤 문맥의 일부로써 동작하는 하나의 장치를 말한다. 

## 자바스크립트 엔진
* Webkit, V8 등
* 자바스크립트 코드를 해석하고 실행하는 인터프리터
* Heap(힙) + Call Stack (호출 스택)
* Heap(힙)은 메모리 할당이 일어나는 부분 (변수, 객체 등이 저장되는 창고)

## Call Stack (호출 스택)
* 함수가 호출되는 순서대로 쌓이는 스택 (정확히는 함수의 실행 문맥)
* 함수 실행 시 호출 스택에 해당 함수를 집어 넣음
* 함수 Return 시 (함수 실행 종료) 호출 스택의 맨 위에 있는 해당 함수를 끄집어냄
* 함수가 아니라 함수의 실행 문맥

호출 스택에서 <b>비어 있다</b>라는 의미는 실행할 함수가 남아있지 않다라는 의미이다.   

다른 코드로 다시 확인해보기로 한다.

```
function second() {
    setTimeout(() => {
        console.log("두 번째");
    }, 2000)
}

function first() {
    console.log("첫 번째_Before");
    second();
    console.log("첫 번째_After");
}

first();
```

위 코드의 실행 순서. 즉 스택이 쌓이는 순서를 알아보자.

1. first() 가 실행이 된다.
```
first()
```

2. first의 첫 번째 console.log가 실행이 된다.
```
console.log("첫 번째_Before");
first()
```

3. first의 첫 번째 console.log가 실행이 완료되어 스택에서 빠져 나온다.
```
first()
```

4. second()가 실행이 된다.
```
second();
first()
```

5. second()의 setTimeout이 실행이 된다.
```
setTimeout(() => {
    console.log("두 번째");
}, 2000)
second();
first()
```

6. second()의 setTimeout이 실행이 완료되어 스택에서 빠져 나온다.
```
second();
first()
```

7. second()이 실행이 완료되어 스택에서 빠져 나온다.
```
first()
```

8. first()의 두 번째 console.log가 실행이 된다.
```
console.log("첫 번째_After");
first()
```

9. first()의 두 번째 console.log가 실행이 완료되어 스택에서 빠져 나온다.
```
first()
```

10. first() 실행이 완료되어 스택에서 빠져 나온다.
```
```

11. second()의 setTimeout의 console.log가 마지막에 실행이 된다.
```
console.log("두 번째");
```

전에 말했던 것처럼, 자바스크립트 엔진에서 setTimeout 코드를 뒤로 잠시 보내놓고 나머지 코드를 실행하고 뒤로 보냈던 setTimeout의 코드를 실행하는 비동기적으로 수행되기 때문이다.

## 싱글 스레드 언어 (자바스크립트)
* 호출 스택을 하나만 사용
* 동시에 하나의 일만 처리   

자바스크립트는 싱글 스레드 언어로 한 번에 하나의 일만 할 수 있다. 그런데 비동기적으로 동작할 수 있는 이유는 웹 구조를 알아야 한다.   

브라우저는 자바스크립트 엔진, Web API, EventLoop, Callback Queue 등을 가지고 있다. setTimeout, DOM, HTTP 요청 등은 Web API에서 제공하는 메소드이다. Web API에서 동작을 멈추면 이는 비동기적 메소드이기 때문에 동작을 마치고 콜백 함수를 콜백 큐(테스트 큐)에 넣는다. 그리고 콜백 함수들이 실행을 위해 대기를 한다. 그렇기 때문에 자바스크립트가 싱글 스레드라도 위의 이유로 멀티 스레드로 동작할 수 있다. (자바스크립트 엔진이 상호 작용하기 위해 콜백 큐와 이벤트 루프가 필요하다.)   

* 더욱 자세한 이해를 위해 동영상으로 시청하길 바란다.   
[출처 10분 40초](https://www.youtube.com/watch?v=wcxWlyps4Vg)   

## EventLoop (이벤트 루프)
* 호출 스택과 콜백 큐를 계속 주시하고 있다.
* 호출 스택이 비어있으면, 먼저 들어온 순서대로 콜백 큐에 있는 콜백 함수들을 호출 스택으로 넣는다.   

이벤트 루프는 ```콜백 큐```에 <b>콜백 함수</b>가 들어왔는지 확인을 하고, ```호출 스택```이 비어있는지 <b>확인</b>을 한다. 즉, 콜백 큐에 콜백 함수가 들어왔고, 호출 스택이 비었다면 콜백 큐에 있는 콜백 함수를 호출 스택으로 보내주는 역할을 한다.

### 비동기와 try / catch
```
$('.btn').click(() => { // A
    try {
        $.getJSON('/주소', (res) => { // HTTP 요청
            // B, 에러 발생하는 구역
        });
    } catch (e) {
        console.log('에러 발생: ', e.message);
    }
});
```

위 코드에서 보면 B 구역에서 에러가 발생한다. 아래 출처 영상을 보면 자세히 알 수 있지만, 간단하게 말해서 클릭 동작을 수행하고 HTTP 요청을 수행하고 나서 B를 이벤트루프가 콜 스택으로 넘길 때, 앞의 일은 <b>이미 수행</b>을 마치고 Return을 한 상황이다. 즉, 이전 수행했던 것과 B는 전혀 <b>다른 문맥 속</b>에서 각각 동작하고 있다고 생각하면 된다. 그렇기 때문에 A 내부에 있는 Try/Catch 문이 B에서 발생하는 문제를 알지 못한다.   

* 이벤트 루프와 Web API 구동에 대해 더 자세한 이해를 위해 영상을 보자.   
[출처 14분 25초](https://www.youtube.com/watch?v=wcxWlyps4Vg)   

이 문제를 해결하는 방법은 Try/Catch 문의 사용 위치를 다르게 하면 된다.   

```
$('.btn').click(() => { // A
    $.getJSON('/주소', (res) => { // B
        try {
            // 에러 발생했던 구역
        } catch(e) {
            console.log('에러 발생: ', e.message);
        }
    });
});
```

## setTimeout (Callback, 0) = setTimeout 0초
* 프론트엔드 환경의 자바스크립트 코드에서 자주 볼 수 있는 코드
* 0초 후에 실행 = 즉시 실행일까?
* 코드 순서대로 출력될까?

```
console.log("Before");

setTimeout(() => {
    console.log("setTimeout 0초");
}, 0);

console.log("After");
```

위 코드는 순서대로 출력될 것 처럼 보이지만 그렇지 않다.   

1. console.log("Before")이 수행되면서 콜 스택에 쌓인다.   
2. setTimeout 내 코드가 콜 스택에 쌓이고 Web API에서 머물게 된다.   
3. console.log("Before")이 수행을 완료하고 콜 스택에서 나간다.   
4. setTimeout이 0초 이기 때문에 바로 콜백 큐로 이동한다.   
5. 아직 남아있는 console.log("After")가 콜백 스택에 쌓인다.   
6. setTimeout 코드는 콜백 스택이 비어있지 않기 때문에 대기한다.   
7. console.log("After")가 수행이 끝나고 콜 스택에서 나온다.   
8. 콜 스택이 비어있기 때문에 setTimeout 코드가 콜 스택로 이동하고 수행한다.   
9. 마지막으로 콜 스택에서 빠져 나온다.   

결국 출력 순서는 ```Before -> setTimeout 0초 -> After```가 아닌 ```Before -> After -> setTimeout 0초``` 순서대로 출력된다.