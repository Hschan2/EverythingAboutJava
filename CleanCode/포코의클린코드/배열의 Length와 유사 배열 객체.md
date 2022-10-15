# 배열의 Length와 유사 배열 객체

## Array.length
Array.length는 배열의 길이보다 <b>배열의 마지막 인덱스</b>를 의미하는 것이라고 알고 있으면 좋다.   

```
const arr = [1, 2, 3];

console.log(arr.length); // 3

arr.length = 10;

console.log(arr.length); // 10

console.log(arr);  // [1,2,3, , , , , , , ,]

arr[15] = 4;

console.log(arr);  // [1,2,3, , , , , , , , , , , 4]
```

위의 배열의 길이를 만약 0으로 설정하면 배열이 <b>초기화</b>가 된다.   

```
const arr = [1, 2, 3];
arr.length = 0;

console.log(arr); // []
```

이러한 Array.length의 특성을 염두하고 주의해서 사용해야 한다.   

## 유사 배열 객체
이는 <b>배열</b>이 아닌 <b>객체</b>를 말한다. 정확하게 ```length``` 속성과 ```인덱싱 된 요소```를 가진 객체를 말한다. 그러나 length 속성과 인덱싱된 요소를 가진 유사 배열 객체를 ```Array.from()``` 메소드를 사용해 새로운 배열을 만들 수 있다.   

```
const arrayLikeObject = {
    0: 'HELLO',
    1: 'WORLD',
    length: 2,
};

console.log(Array.isArray(arrayLikeObject)); // false

const arr = Array.from(arrayLikeObject);

console.log(Array.isArray(arr)); // true
console.log(arr); // [ 'HELLO', 'WORLD' ]
```

Argument 혹은 WebAPI의 Node List도 <b>유사 배열 객체</b>이다. 그리고 유사 배열 객체는 배열의 고차함수 메소드를 사용할 수 없다. ```Array.from()``` 메소드를 통해 배열로 변환해야 배열의 고차함수 메소드를 사용할 수 있으니 알고 있는 것이 좋다.   