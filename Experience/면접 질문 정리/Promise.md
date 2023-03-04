# Promise
자바스크립트에서 <b>Promise</b>는 무엇일까?   

아마, 자바스크립트 콜백 형태를 보완하기 위해 나온 비동기 통신 방식으로 콜백 헬의 문제인 상황을 해결하기 위해 나온 패턴이라고 말할 것이다. ```resolve```, ```pending```, ```reject```를 가지며 반환은 ```resolve```나 ```reject```를 받는다는 말을 더할 수 있다.   

틀린 말이 아니다. 그러나 Promise가 무엇인지 제대로 알고 갈 필요가 있다.   

## 정의
<b>Promise</b>는 자바스크립트에서 비동기 처리를 하기 위한 패턴이다. 기존 전통적인 콜백 패턴이 가진 단점을 보완하여 <u>비동기 처리 시점을 명확히 표현</u>할 수 있다는 것이 장점이다.   

기존 콜백 패턴의 단점은 비동기 처리 시점을 명확하게 표현할 수 없다는 단점이 있었다. 비동기 통신은 병렬로 요청을 처리한다는 것이다. 그렇기 때문에 처리 순서를 보장하기 어렵다. 이를 해결하기 위해서 ```네스팅즉 중첩```을 이용한다. 이는 중첩을 처리하는 과정에서 복잡도가 높아지면 콜백 헬이 발생한다. 콜백 헬은 가독성을 나쁘게 만들며 복잡하게 만든다.   

```
step1(function(value1) {
    step2(value1, function(value2) {
        step3(value2, function(value3) {
            step4(value3, function(value4) {
                step5(value4, function(value5) {
                    // ...
                });
            });
        });
    });
});
```

비동기 모델은 실행 완료(응답)를 기다리지 않고 바로 다음 태스크(요청)을 처리하기 때문에 원하는 동작이 발생하지 않을 수 있다. 이를 위해서 순서를 보장할 수 있는 반환된 결과를 통해 다른 처리를 실행하기 위해 콜백 함수를 사용한다.   

## 에러 처리의 한계
에러 처리는 서비스 완성도 면에서 굉장히 중요한 부분이다. 개발자들이 이를 간과하는 일이 많은데 서비스를 사용하는 사용자에게는 에러 처리가 굉장히 중요하다.   

콜백 방식의 비동기 처리는 에러처리가 어려운 부분이 있다.   

```
try {
    setTimeout(() => { throw new Error('Error!'); }, 1000);
} catch (e) {
    console.log('에러를 캐치하지 못한다..');
    console.log(e);
}
```

위 예시에서 ```Try문```을 보면, 콜백함수가 예외를 발생시키지만 ```Catch문```에서 잡히지 않는다. 이유는 ```setTimeout```의 콜백함수의 호출자는 ```setTimeout```이 아니기 때문이다. 콜백함수의 호출자가 ```setTimeout```이라면 호출 스택에 ```setTimeout```함수가 존재해야 한다.   

예외는 호출자 방향으로 전파된다. 그러나 위 예시는 호출자와 부합하지 않기 때문에 ```Catch문```에서 잡을 수 없는 것이다. 이를 개선하기 위해 ```Promise```가 나타났다.   

## Promise
이는 생성자 함수를 통해 인스턴스화 한다. 생성자 함수는 비동기 작업을 수행할 콜백함수를 인자로 전달받으며, ```resolve```, ```reject```를 함수로 전달받는다. 상태는 ```pending```, ```fulfilled```, ```rejected```, ```settled```가 있다.   

```
const promiseAjax = (method, url, payload) => {
    return new Promise((resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url);
        xhr.setRequestHeader('Content-type', 'application/json');
        xhr.send(JSON.stringify(payload));

        xhr.onreadystatechange = function () {
            // 서버 응답 완료가 아니면 무시
            if (xhr.readyState !== XMLHttpRequest.DONE) return;

            if (xhr.status >= 200 && xhr.status < 400) {
                // resolve 메소드를 호출하면서 처리 결과를 전달
                resolve(xhr.response); // Success!
            } else {
                // reject 메소드를 호출하면서 에러 메시지를 전달
                reject(new Error(xhr.status)); // Failed...
            }
        };
    });
};
/*
비동기 함수 promiseAjax은 Promise 객체를 반환한다.
Promise 객체의 후속 메소드를 사용하여 비동기 처리 결과에 대한 후속 처리를 수행한다.
*/
promiseAjax('GET', '<http://jsonplaceholder.typicode.com/posts/1>')
    .then(JSON.parse)
    .then(
        // 첫 번째 콜백 함수는 성공(fulfilled, resolve 함수가 호출된 상태) 시 호출
        render,
        // 두 번째 함수는 실패(rejected, reject 함수가 호출된 상태) 시 호출
        console.error
    );
```

위의 예제처럼 ```reject```를 통해 에러 처리를 할 수 있다. ```then()```은 Promise의 후속 처리를 위한 메소드로 두 개의 콜백함수를 인자로 전달받는다. 첫 번째 콜백함수는 성공 시 호출되고, 두 번째 함수는 실패 시 호출된다. 그리고 ```then()```은 promise를 반환한다.   

```
promiseAjax('<https://jsonplaceholder.typicode.com/todos/1>')
    .then(res => console.xxx(res))
    .catch(err => console.error(err)); // TypeError: console.xxx is not a function
```

위의 예제에서는 ```Catch문```을 이용해 에러를 처리할 수 있다. 이는 가독성도 콜백에 비해 훨씬 명확하다.   

## Promise 체이닝
<b>Promise</b>는 호출이 중첩되어 복잡도가 높아지는 콜백 헬을 해결할 수 있다. 

```then()``` 메소드를 통해 중첩이 아닌 체이닝 형식으로 여러 함수를 중첩할 수 있다.   

## Promise 면접 질문 시, 대답 요약
<b>Promise</b>는 자바스크립트에서의 비동기 통신 패턴이다. 기존 사용하던 콜백 방식의 약점을 보완하여 나온 패턴으로 가독성을 높이고 콜백 지옥을 피할 수 있으며, 비동기 처리 시점을 명확하게 표현할 수 있다.   

<br/>
<hr/>

[참고](https://taltube.tistory.com/39)