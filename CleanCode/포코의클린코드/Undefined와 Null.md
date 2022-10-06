# Undefined와 Null 비교
<b>Undefined</b>과 <b>Null</b>의 차이는 비어있는 휴지칸과 휴지칸 속 다 쓴 휴지심이라고 볼 수 있다.   

전역 <b>Undefined</b> 속성은 [undefined] 원시 값을 나타내며, 자바스크립트의 원시 자료형 중 하나이며, <b>Null</b>은 자바스크립트의 원시 값 중 하나로 어떤 값이 의도적으로 비어있다는 것을 표현하며 Boolean에서 False로 표현한다.   

## Null
이는 NOT 연산자를 사용하면 피연산자 Null을 Boolean(Null은 False)으로 변환 후 그 역을 반환한다.   

```
console.log(!null); // true
console.log(!!null); // false
console.log(!null === true); // true
console.log(!!null === false); // true
```

그러나 위와 다르게 (null === false)를 하면 false 결과 값이 출력된다.   

그리고 Null은 수학적으로 0의 값을 갖는다. 그래서 ```Null + 123```을 하면 123의 값을 출력한다.   

## Undefined
이는 아무것도 지정하지 않았을 때의 기본 값을 말한다. 즉, 변수 선언은 했지만 값을 정의(할당, 초기화)하지 않은 상태를 말한다.   

```
let varb
console.log(varb) // undefined
console.log(typeof varb) // undefined
```

Undefined에 대해 NOT 연산자를 사용하면 Null과 같이 True(Boolean)이 결과 값으로 출력된다.   

```
console.log(!undefined); // true
console.log(!!undefined); // false
```

그러나 Undefined와 Null이 같지 않다. 이 둘은 값이 다르다는 결과 값이 출력된다.   

```
console.log(undefined === null); // false
```

그 이유는 Undefined는 수학적으로 Null과 다르게 NaN의 값을 가지고 있기 때문이다.   

## 정리
<b>Undefined</b>과 <b>Null</b>을 함께 활용한 코드를 사용하기보다 팀에서 비어 있는 값을 둘 중 무엇으로 선언할 지 컨벤션을 정해 일관성있게 사용하는 것이 좋다. 그래서 Undefined와 Null 중 아래와 같은 특성을 파악하고 조심해서 사용해야 한다.   

* Undefined
    * 값이 정의되지 않았을 때의 기본으로 정해지는 값
    * 수학적으로 NaN을 가짐
    * Type은 Undefined
* Null
    * 값이 없음을 명시적으로 표현하기 위한 값
    * 수학적으로 0에 가까움
    * Type은 Object