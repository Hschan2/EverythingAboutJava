# IsNaN()
이는 ```Is not a number?```를 말하며 어떤 값이 NaN인지 판단하는 것이다. 자바스크립트의 다른 모든 값과 다르게 NaN은 같음 연산(==, ===)을 사용해 판별할 수 없기 때문에 NaN을 판별하는 함수가 필요하여 생성되었다.   

NaN의 타입은 Number(typeof NaN => Number)이지만 isNaN(NaN)의 결과는 True (= Not a number)이기 때문에 헷갈릴 수가 있다. 이 메소드가 숫자가 아니냐고 부정을 묻는 것이기 때문에 결과 값을 예상할 때 헷갈리기 쉽다. False가 반환이 되면 숫자이고 True가 반환되면 숫자가 아니라는 것으로 이해하면 된다. 정확하게는 ```이 값이 숫자값으로 강제(강제 형 변환)이 되는 경우에는 IEEE-754 표준에 따른 Not a number 값인가?```를 묻는 것이다.   

```
console.log(isNaN(123)) // false, 숫자가 아닌 것이 아니다. 즉, 숫자
```

## Number.isNaN()
<b>isNaN</b>과 <b>Number.isNaN</b>의 결과가 다르다.   

```
isNaN("blabla"); // true
```

위 처럼 문자열을 isNaN로 판단했을 때, True 값이 반환되는데 이는 원리상 잘못된 결과이다. 전역 isNaN 메소드는 이를 숫자로 파싱하는 것을 실패해 NaN을 반환하여 True가 반환되는 것이다. 이런 상황때문에 전역 isNaN은 완전히 신뢰할 수 없는 상황이 되었고 이를 대안으로 ```Number.isNaN()```이 나오게 됐다.   

isNaN은 느슨한 검사를 하기 때문에 이를 보완하기 위해 Number.isNaN이 도입이 되었다. ```Number.isNaN()``` 메소드는 전달된 값이 NaN이고 타입이 Number인지 확인하는 메소드이다.   

```
Number.isNaN(NaN); // true
Number.isNaN(Number.NaN); // true
Number.isNaN(0 / 0); // true

전역 isNaN()에서는 원래 True인 사례
Number.isNaN('NaN'); // false
Number.isNaN(undefined); // false
Number.isNaN({}); // false
Number.isNaN('blabla'); // false

아래는 모두 False
Number.isNaN(true);
Number.isNaN(null);
Number.isNaN(37);
Number.isNaN('37');
Number.isNaN('37.37');
Number.isNaN('');
Number.isNaN(' ');
```

따라서 ```isNaN```보다 ```Number.isNaN```을 사용하는 것이 권장된다.