# THIS

## THIS란?
일반적으로 객체지향 언어에서 <b>this(예약어)</b>는 함수가 속해있는 객체이며, 자기 자신과 같다.   

그러나 자바스크립트에서는 자기 자신가 같다는 말은 모호하다. 자바스크립트 함수가 조금 특별하기 때문이다. 자바스크립트의 함수는 객체, 그 중에서도 <b>일급 객체</b>이다.

```
1. 변수나 데이터에 저장   
const myFunc = func   

2. 함수의 인수로 전달   
function func1(func2) {}   

3. 함수의 반환 값으로 사용   
function func1() {
    ...
    return func1
}
```

자바스크립트에서 this로 호출했을 경우, 어느 객체에 의해 호출되었는지 알기 어렵다.   

내 자신. 즉, 나와 같은 <b>1인칭 소유격 표현</b>과 관련이 있다. 같은 문장이라도 누가 하느냐에 따라 1인칭 소유격 표현의 의미가 달라진다. 자바스크립트도 이와 비슷하다. 같은 함수라도 ```어떤 객체에 의해 호출되느냐```에 따라 this의 의미가 달라진다.

## 중간 정리
자바스크립트의 <b>모든 함수</b>는 this를 가지고 있다. 그리고 함수가 호출이 되면 상황에 따라 this가 가리키는 객체가 결정된다. 이렇게 함수가 호출될 때마다 this가 동적으로 결정되는 것을 this가 해당 객체에 ```바인딩``` 된다고 표현한다.   

```
자바스크립트 엔진 --> 실행 가능한 코드 --> 실행 문맥

실행 가능한 코드 - 전역 코드, 함수 코드, eval 코드
실행 문맥 - 렉시컬 환경 컴포넌트, 디스 바인딩 컴포넌트 등
```

<b>this는 바인딩 컴포넌트에 담긴다.</b>   

프로그램이 실행이 되면 자바스크립트 엔진은 <b>전역 코드</b>를 평가에서 전역 실행 문맥을 생성한다. 그리고 함수가 실행이 되면 전역 코드 실행을 잠시 멈추고 함수 실행 문맥을 생성한다. 바로 이 타이밍에서 <b>this 바인딩 컴포넌트</b>의 값이 결정이 된다.   

## This Binding Rules
1. 기본 바인딩   
이는 기본적으로 전역 객체에 바인딩된다.
```
function showThis() { // 비엄격 모드
    console.log(this)
}

function showThisStrictMode() { // 엄격 모드
    'use strict' // 전역 객체가 바인딩 대상에서 제외
    console.log(this)
}

showThis() // window
showThisStrictMode() // undefined
```
다만 노드 환경에서는 조금 다르다.

```
function showThis() { // 비엄격 모드
    console.log(this)
}

showThis() // object [global]
console.log(this); // {}
console.log(this === module.exports); // true
```

노드 JS 환경에서 this가 두 가지 방향으로 바인딩이 된다.   

2. 암시적 바인딩   
this가 점 바로 앞에 있는 객체에 바인딩이 된다. (Object.Function()의 .)

```
const obj = {
    name: "woowa",
    getName() {
        return this.name;
    },
};

console.log(obj.getName()); // woowa
```
그러나 주의해야 할 경우가 존재한다.

```
const obj = {
    name: "woowa",
    getName() {
        return this.name;
    },
};

function showReturnValue(callback) {
    console.log(callback());
}

showReturnValue(obj.getName()); // undefined
```
위 코드를 보면 name이라는 프로퍼티를 반환하고 있으며, 전역 코드 상에는 showReturnValue 함수가 있어 콜백을 인자로 받고 그 콜백에 대한 반환값을 콘솔에다가 출력하는 함수이다. 결과적으로, undefined가 나오는 이유는 <b>점 연산(Object.Function)</b>에 있다. 점 연산이나 대괄호 연산을 통해 객체의 프로퍼티에 접근하면 <b>참조 타입(Reference Type)</b>이라는 특별한 값을 반환한다. 이는 프로퍼티 뿐만 아니라 객체와 strict 모드의 여부를 가지고 있다. 

```
obj.getName(); // 참조 타입을 통해 암시적 바인딩

function showReturnValue(callback) { // 함수의 참조값만 전달
    callback(); // 함수 단독 실행
}
```
그러나 점 연산이나 대괄호 연산 외 다른 연산들은 참조 타입이 아닌 <b>해당 프로퍼티의 값</b>만 전달한다. 따라서 점 연산을 통해 얻은 값은 바로 함수로 호출하지 않고서는 암시적 바인딩을 기대할 수 없게 된다. 마찬가지로 인수로 전달된 콜백 참조도 객체에 대한 어떤 정보도 포함하지 않기 때문에 함수로 <b>단독 호출</b>한 것과 같이 동작하게 된다. 

3. new 바인딩   
이는 <b>new 연산자</b>를 이용하여 호출한다. 자바스크립트 함수를 new 연산자와 함께 호출하게 되면 생성자 함수로서의 역할을 수행하게 된다. new 바인딩의 순서는 아래와 같다.   

1. 새로운 객체 생성   
2. 함수 코드 실행   
3. 새로 생성한 객체 반환   

이 과정을 pseudo code로 간단하게 표현한다면
```
{
    obj = {} // 객체 생성
    this = obj // 바인딩

    this.name = "woowa" // obj: { name: "woowa" }

    return this
}
```

4. 명시적 바인딩   
```call```, ```apply```, ```bind```를 통해 this가 소실되는 문제를 해결할 수 있다. this를 바인딩할 객체를 지정한 상태로 함수를 호출할 수 있다.

```
call(context, arg1, arg2)
apply(context, args)

=> 첫 번째 인자로 this를 바인딩 할 객체를 전달
```

bind 메소드는 <b>this가 참조하는 객체</b>를 고정시킨다. 위와 마찬가지로 첫 번째 인자로 this를 <b>바인딩할 객체</b>를 넣는다. 다만, 위 두 가지의 메소드와 다르게 항상 this가 어떤 특성 객체에 바인딩 되어있는 것과 같은 함수처럼 행동한다. 이는 항상 같은 객체에 바인딩되도록 강제하는 방법인데 이를 <b>하드 바인딩</b>이라고 한다.   

### 바인딩 우선 순위
```
new 바인딩 > 암시적 바인딩 > 명시적 바인딩 > 기본 바인딩
```

## 화살표 함수

### 예제로 먼저 확인해보기

```
const obj = {
    name: "woowa"
    showNameInSec(sec) {
        setTimeout(() => {
            console.log(this.name);
        }, sec);
    },
};

obj.showNameInSec(1000); // woowa
```
위 코드를 화살표 함수가 아닌 이전 함수처럼 사용했다면, undefined 에러가 발생했을 것이다. 전에 언급했듯이 callback으로 인자를 넘기면 this가 의미를 많이 잃었기 때문이다. 이 처럼 화살표 함수는 많은 것의 차이점을 가지고 있고 사용 목적도 다르다. 화살표 함수는 ```상위 실행 문맥```을 <b>유지</b>하는 것이 목적이라고 생각이 된다. 기존의 바인딩 규칙은 화살표 함수 내에 this와는 의미가 다르다. 화살표 함수 안에서 this는 선언될 당시 상위 실행 문맥(상위 스코프)의 ```this 바인딩 컴포넌트```를 <b>참조</b>한다. 즉, 상위 스코프의 this를 가리킨다. 이런 특징을 갖는 this를 <b>어휘적 this</b>, <b>렉시컬 this</b>라고 부른다.