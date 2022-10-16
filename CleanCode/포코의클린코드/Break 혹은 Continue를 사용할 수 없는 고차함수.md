# 배열 고차함수에서 Break 혹은 Continue를 사용할 수 없다
배열 고차함수(forEach, map, filter ...)에서 ```Break``` 혹은 ```Continue```를 사용할 수 없다. 그 이유는 해당 고차함수들은 이들을 문법적으로 지원하지 않기 때문이다.   

```
const orders = ['first', 'second', 'third'];

orders.forEach(function (order) {
    if (order === 'second') {
        break; // SyntaxError: Illegal break statement
    }

    console.log(order);
});
```

위에서 보듯이 ```forEach```에서 Break을 사용하게 되면 ```SyntaxError: Illegal break statement```가 발생하는 것을 확인할 수 있다.   

고차함수를 사용하면서 반복을 중간에 종료하고 최적화를 하고 싶다면 조건에 따라 예외를 던지는 ```Try Catch```문을 사용하는 방법이 있다.   

```
const orders = ['first', 'second', 'third'];

try {
    orders.forEach(function (order) {
        if (order === 'second') {
            throw error;
        }

        console.log(order);
    });
} catch (error) {
    ...
}
```

그러나 중간에 멈춰야 한다면 ```forEach```문보다 다른 방법을 사용하는 것이 좋다. 혹은 ```For```문, ```For of```문, ```For in```문을 선택해서 원하는 ```Break```이나 ```Continue```를 사용하는 편이 더 효율적일 수도 있다.   

그리고 다음과 같은 방법으로 조기에 루프 반복을 종료할 수 있다. 해당 메소드들은 배열 요소를 판별해 함수에 전달하고 그 결과의 참과 거짓 여부에 따라 반복의 종료 여부를 결정한다.   

Array의 ```every()```, ```some()```, ```find()```, ```findIndex()```가 있다.   

* every() 메소드: 배열 안의 모든 요소가 주어진 판별 함수를 통과하는지 테스트를 하며 Boolean 값을 반환
* some() 메소드: 배열 안의 어떤 요소라도 주어진 판별 함수를 통과하는지 테스트
* find() 메소드: 주어진 판별 함수를 만족하는 <b>첫 번째 요소의 값</b>을 반환. 만약 반환할 값이 없다면 Undefined를 반환
* findIndex() 메소드: <b>주어진 판별 함수를 만족</b>하는 배열의 첫 번째 요소에 대한 <b>인덱스</b>를 반환. 만약 반환할 요소가 없다면 -1를 반환   