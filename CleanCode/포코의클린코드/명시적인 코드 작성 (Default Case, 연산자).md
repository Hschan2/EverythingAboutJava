# 명시적인 코드 작성하기(연산자)

## Default Case
사용자의 실수를 예방하기 위해 <b>Default Case</b> 사용하는 것을 고려해야 한다. 함수에서 들어와야 할 인수가 전달되지 않을 경우 OR 연산자를 사용해 안전하게 Default 값을 미리 설정해두는 방법은 권장된다.   

예시로 다음과 같다.   

```
function sum(x, y) {
    x = x || 0;
    y = y || 0;

    return x + y;
}

console.log(sum()); // 0
```

```
function registerDay(userInputDay) {
    switch (userInputDay) {
        case '월요일': ...
        case '화요일': ...
        case '수요일': ...
        case '목요일': ...
        case '금요일': ...
        case '토요일': ...
        case '일요일': ...
        default:
            throw Error('입력값이 유효하지 않습니다');
    }
}

registerDay('월ㄹ요일'); // Error: 입력값이 유효하지 않습니다
```

사용자의 잘못된 입력. 즉, 오타 케이스로 어떤 케이스에도 해당하지 않아 Default로 설정한 에러를 전달한다.   

```
function safeDecimalParseInt(number, radix) {
    return parseInt(number, radix || 10);
}
```
* radix (Optional): String이 표현하는 정수를 나타내는 2와 36사이의 진수. 기본값이 10이 아니다.   

다음으로 ```parseInt()``` 함수에서 두 번째 매개변수(radix)의 기본값은 10이 아니다. 그럼에도 10진수 정수를 반환하려는 의도로 해당 함수를 사용해 두 번째 매개변수에 10을 생략하는 잘못된 경우도 있다.   

## 명시적 연산자 사용용 지향하기
명시적으로 연산자를 사용해 예측 가능하고 디버깅하기 쉬운 코드를 작성해야 한다. 특히, 우선순위가 먼저인 부분을 소괄호로 묶는 것이 바람직하다.   

```
((x + y) * z)
```

그리고 전위 연산자나 후위 연산자를 사용하는 것보다 되도록 연산자를 명시적으로 사용하는 것이 좋다.   

```
number--;

number = number - 1;
```

```number--;```와 같이 후위 연산자를 사용하는 것보다 ```number = number - 1;```처럼 명시적인 코드를 사용하는 것이 바람직하다.   
