# Callback (콜백)

## 동기와 비동기
### 동기 (Synchronous)
특정 코드를 수행 완료한 후 다음 코드를 실행하는 것이다.   

```
예. 짜장면 배달

짜장면 배달 -> 손님이 다 먹을 때까지 대기 -> 손님이 다 먹고난 후, 가게로 복귀
```
즉, 동기적으로 움직이는 것은 비효율적이다.   

### 비동기 (Asynchronous)
특정 코드를 수행하는 도중에 다음 코드를 실행하는 것이다.   

```
예. 짜장면 배달

짜장면 배달 -> 손님이 짜장면을 먹을 때까지 다른 일을 수행 -> 손님이 다 먹고나면 그릇을 수거하고 가게로 복귀
```
그릇을 언제 돌려줄 지 모르는 동기보다는 비돋기적 움직임이 더욱 효율적이다. 즉, 같은 시간에 더 많은 것을 할 수 있다는 것이다.   

동기적으로 동작하는 자바스크립트 코드에서 비동기적으로 수행할 수 있는 이유는 <b>콜백 함수</b>를 사용했기 때문이다. (setTimeout() ...)   

## Callback (콜백, 콜백 함수)
직역하자면, 되돌아와서 호출하는 것이다. 그렇다면, 어느 지점에서 호출해야 할까?

```
const arr = ['a', 'b', 'c'];

const printArray = () => {
    console.log(arr.shift());
    if(!arr.length) {
        ClearInterval(timer);
    }
};

const timer = setInterval(printArray, 1000);
```
setInterval로 printArray를 호출하는 코드다. 1초마다 arr의 값인 'a', 'b', 'c'를 호출한다. 여기서 인자로 넘긴 printArray는 콜백 함수다.   

제어권을 받은 setInterval은 스스로 판단하여 호출하며, 콜백 함수의 제어권을 넘겨받은 코드는 콜백 함수의 호출 시점에 대한 제어권을 가지게 된다.   

### 콜백 활용
특정 검색을 이용할 때, 해당 이벤트 콜백 함수를 미리 등록하여 사용할 수 있다. 
```
예. 지하철 노선도 검색

#stationNameSubmit.addEventListener('submit', callback)
```
함수에 제어권을 다른 함수에 위임할 수 있기 때문에 이벤트가 발생할 때마다 콜백 함수를 호출에서 원하는 로직을 실행할 수 있다.   

```
예. 지하철 노선도 삭제

#stationNameSubmit.addEventListener('submit', removeStation);

const removeStation = {{ target }} => {
    const isAdmin = ajax();

    if(!isAdmin) {
        return;
    }

    target.remove();
};
```
위 코드를 실행했을 때, 삭제가 가능한 사용자가 버튼을 클릭한다고 해도 삭제가 되지 않는다. ajax가 실행 완료가 되기 전, 조건문이 실행이 되기 때문이다.

```
#stationNameSubmit.addEventListener('submit', removeStation);

const removeStation = {{ target }} => {
    const isAdmin = ajax();

    setTimeout(() => {
        if(!isAdmin) {
            return;
        }
        target.remove();
    }, 500);
};
```
위 처럼 콜백을 통해 ajax 요청에 응답을 받은 후에 실행하게 만들면 된다.   

## Callback Hell (콜백 지옥)
setTimeout을 이용해 네트워크 통신 등 여러 작업을 할 때, 중첩된 콜백을 사용하게 되면 가독성이 떨엉지고 디버깅이 힘들어진다. 그리고 출력에 문제가 발생할 수 있다.   

## Promise
콜백 지옥에서 발생한 문제들을 해결하기 위해서 사용한다.   

* new Promise(executor)
Promise는 executor를 인자로 받고 다시 매개 변수로 resolve와 reject를 받는다. resolve는 성공, reject는 실패의 원인을 받아낸다.   

Promise의 경우에는 내부에서 resolve 혹은 reject를 호출하기 전에 then, catch 부분으로 넘어가지 않는다. 따라서 비동기 작업이 완료될 때, resolve 혹은 reject를 호출하는 방법으로 비동기 작업에 동기적인 표현을 가능하게 한다.   

## Async / Await
콜백 지옥에서 벗어나게 해주는 방법 중 하나이다. 

```
const fetchUser = async () => {
    return "Hi";
};
```
async는 자동적으로 함수 안에 있는 코드 블럭들이 Promise로 변환이 된다.   

Await를 사용하기 위해서는 앞에 async를 선언해야 한다.

```
const delay = (ms) => {
    return new Promise((resolve) => setTimeout(resolve, ms));
};

const delayPrint = async () => {
    await delay(1000);
    console.log("Hi");
    await delay(1000);
    console.log("There");
};

delayPrint();

console.log>
1초 후, Hi
1초 후, There
```

Await은 비동기 로직이 실행되는 동안 함수의 실행을 멈추고 반환을 기다린다. Await을 사용하여 비동기적 코드로 작성하였지만 동기 코드처럼 위에서 아래로 실행한다. 

[출처](https://www.youtube.com/watch?v=wvEYG6ydAGg)