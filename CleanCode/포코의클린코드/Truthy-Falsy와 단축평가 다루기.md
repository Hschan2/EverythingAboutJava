# Truthy-Falsy와 단축 평가 다루기
Falsy(거짓)과 같은 값은 8개가 존재한다.   

* False
* 0 (숫자 Zero)
* -0 (음수 Zero)
* 0n (BigInt)
* "" (빈 문자열)
* Null
* Undefined
* NaN   

반대로 Truthy(참)의 값은 거짓 같은 값 8개가 아닌 것들이 참 같은 값으로 평가가 된다. 자바스크립트는 Boolean 문맥에서 형 변환이 되어 아래와 같은 경우에는 <b>참 같은 값</b>을 <b>True</b>로 변환한다.   

```
if (true)
if ({})
if ([])
if (42)
if ("0")
if ("false")
if (new Date())
if (-42)
if (12n)
if (3.14)
if (-3.14)
if (Infinity)
if (-Infinity)
```

위의 코드는 모두 True 조건으로 If 조건문 블록에 실행이 된다. 여기서 두 번째처럼 빈 배일이나 빈 객체도 모두 True 값을 반환한다. 그리고 String의 "0"도 True 값을 반환한다.   

Falsy의 활용 예는 다음과 같다.   

```
if (name === undefined || name === null) {
    return '사람이 없네요';
}
```

Undefined 혹은 Null의 경우를 거르기 위한 조건을 Falsy를 이용해 간단하게 리팩토링을 할 수 있다.   

```
if (!name) {
    return '사람이 없네요';
}
```

혹은 다음과 같이 ```!```부정 처리를 하여 같은 처리를 할 수 있다. 이럴 경우에도 Falsy에 null과 undefined 포함된다.   

## 단축 평가
<b>단축 평가(Short-Circuit Evaluation</b>는 좌항에서 오른쪽으로 순차적으로 체크하는 것을 말한다.   

* <b>AND 연산자: 최우항으로 가기 전까지 모두 True여야 최우항으로 도달해서 최우항을 반환</b>   

```
console.log(true && true && '도달 O'); // '도달 O'
```

* <b>AND 연산자는 좌항부터 우항으로 순차적으로 평가하면서 Falsy를 확인한 즉시 그 Falsy의 값을 반환</b>   

```
console.log(true && undefined && false && null && '도달 X'); // undefined
```

* <b>OR 연산자: 최우항으로 가기전까지 모두 Falsy여야 최우항으로 도달해 최우항을 반환</b>   

```
console.log(false || false || '도달 O'); // '도달 O'
```

* <b>OR 연산자는 좌항부터 우항으로 순차적으로 평가하면서 Truthy를 확인한 즉시 해당 Truthy 값을 반환한다.</b>   

```
console.log(false || '값1' || '값2' || '도달 X'); // 값1
```

## 리팩토링
* <b>예시. 단축 평가는 기본값 표현 시에 가장 유용하게 사용할 수 있다.</b>   

```
function fetchData() {
    if (state.data) {
        return state.data;
    } else {
        return 'Fetching...';
    }
}
```

위의 경우를 아래처럼 단축 평가를 활용해서 리팩토링을 할 수 있다.

```
function fetchData() {
    return state.data || 'Fetching';
}
```

state.data가 truthy이면. 즉, 존재하면 state.data가 반환되고, 존재하지 않으면 Falsy로써 최우항의 ```'Fetching'```을 반환한다.   

* <b>두 번째 예시.</b>

```
function favoriteDog(someDog) {
    let favoriteDog;

    if (someDog) {
        favoriteDog = someDog;
    } else {
        favoriteDog = '냐옹';
    }

    return favoriteDog + '입니다';
}

console.log(favoriteDog()); // '냐옹입니다'
console.log(favoriteDog('포메')); // '포메입니다'
```

위의 코드를 아래처럼 단축 평가를 활용해 리팩토링을 할 수 있다.   

```
function favoriteDog(someDog) {
    return (someDog || '냐옹') + '입니다';
}

console.log(favoriteDog()); // '냐옹입니다'
console.log(favoriteDog('포메')); // '포메입니다'
```

* <b>세 번째 예시.</b>

```
function getActiveUserName(user, isLogin) {
    if (isLogin) {
        if (user) {
            if (user.name) {
                return user.name
            } else {
                return '이름없음'
            }
        }
    }
}
```

위의 코드를 아래처럼 단축평가를 활용해 리팩토링을 할 수 있다.   

```
function getActiveUserName(user, isLogin) {
    if (isLogin && user) {
        return user.name || '이름없음';
    }
}
```

