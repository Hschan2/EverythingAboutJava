# Else if와 Else 피하기

## Else if 문 피하기
Else if 문이 If문과 연결되어 차례대로 실행된다고 생각하는 것은 옳지 않다. Else if 문은 Else 문 처리가 한 번 되고 If 문이 동작하는 것과 같다.   

```
const x = 1;

if (x >= 0) {
    console.log('x는 0과 같거나 크다');
} else if (x > 0) {
    console.log('x는 0보다 크다');
}

// x는 0과 같거나 크다
```

```
const x = 1;

if (x >= 0) {
    console.log('x는 0과 같거나 크다');
} else {
    if (x > 0) {
        console.log('x는 0보다 크다');
    }
}

// x는 0과 같거나 크다
```

위의 두 개의 코드는 논리적으로 같으며 결과도 똑같다.   

위의 코드보다 Else if 문을 사용하지 말고 새로운 If 문을 사용해 조건을 분리하는 것이 가독성에도 좋다.   

```
const x = 1;

if (x >= 0) {
    console.log('x는 0과 같거나 크다');
}

if (x > 0) {
    console.log('x는 0보다 크다');
}

// x는 0과 같거나 크다
// x는 0보다 크다
```

만약에 조건이 너무 많아서 Else if 문을 많이 사용해야 하는 경우라면 Switch 문을 사용하는 것이 낫다.   

## Else 문 피하기
Else 문을 사용하지 않아도 조건을 논리적으로 분리할 수 있다.   

```
function getActiveUserName(user) {
    if (user.name) {
        return user.name;
    } else {
        return '이름이 없습니다.';
    }
}
```

위 코드처럼 Else 문을 사용하는 것보다 If 문 하나만 사용하는 것이 낫다.   

```
function getActiveUserName(user) {
    if (user.name) {
        return user.name;
    }

    return '이름이 없습니다.';
}
```

Else 문을 사용하지 않는 것이 좋은 이유는 가독성, 스타일의 문제도 있지만 반전된 로직을 작성하게 되는 논리적인 문제가 발생하기 때문이다. 하나의 함수가 두 가지 이상의 기능을 할 때 Else 문을 무분별하게 사용하게 되면 문제가 생길 수 있다.   

```
function getHelloCustomer(user) {
    if (user.age < 20) {
        report(user);
    } else {
        return '환영합니다.';
    }
}
```

위 코드는 나이가 20 미만일 때, report 함수를 실행하며 인사하는 로직을 작성하는 것이다. 만약 위 처럼 Else 문으로 처리했을 때는 20 미만일 경우에만 인사하지 않는 문제가 발생한다.   

위 처럼 Else 문을 사용해서 처리하지 말고 If 문으로만 처리하는 것이 논리적인 문제가 발생하지 않는다.   

```
function getHelloCustomer(user) {
    if (user.age < 20) {
        report(user);
    }

    return '환영합니다.';
}
```

## Early Return 활용
<b>Early Return</b>는 조건문에서 우선적으로 Return할 수 있는 부분은 분리하여 If 문 내에서 우선적으로 Return하여 함수를 미리 종료하는 것을 말한다. 이럴 경우, 다음 코드로 진입하지 않아서 Else 문을 사용하지 않아도 된다.   

If - Else 문이 많으면 가독성을 헤치고 조건에 대해 명시적이지 않을 수 있다. 이럴 때 Early Return을 사용하는 것이 좋다.   

또한, Early Return으로 코드를 분리하면 로직은 변하지 않고 가독성은 좋아진다. 그래서 더욱 명시적인 코드로 리팩토링이 가능해진다. 최상위에서 Early Return을 통해 거르는 로직을 넣으면 조건문을 확인할 때 헷갈리는 경우가 줄어든다.   

```
function loginService(isLogin, user) {
    /** 로그인 여부 확인 */
    if (!isLogin) {
        /** 토큰의 존재 확인 */
        if (checkToken()) {
            /** 가입 여부 재확인 */
            if (!user.nickName) {
                return registerUser(user);
            } else {
                refreshToken();

                return '로그인이 완료되었습니다.';
            }
        } else {
            throw new Error('토큰이 존재하지 않습니다.');
        }
    }
}
```

위의 코드는 If 문 안에 If 문과 Else 문을 중복하여 사용하기 때문에 가독성을 헤친다. 그래서 이해하기 어렵다. 이럴 때, Early Return을 사용하는 것이 좋다.   

```
function loginService(isLogin, user) {
    /** 로그인 여부 확인 */
    if (isLogin) {
        return;
    }

    /** 토큰의 존재 확인 */
    if (!checkToken()) {
        throw new Error('토큰이 존재하지 않습니다.');
    }

    /** 가입 여부 재확인 */
    if (!user.nickName) {
        return registerUser(user);
    }

    refreshToken();

    return '로그인이 완료되었습니다.';
}
```

모든 것을 If 문으로 처리하여 Early Return로 처리할 경우 가독성도 좋아지고 논리적인 로직으로 사용이 가능하다.   

```
function 오늘하루(condition, weather, isJob) {
    if (condition === 'GOOD') {
        공부();
        게임();
        유튜브보기();

        if (weather === 'GOOD') {
            운동();
            빨래();
        }

        if (isJob === 'GOOD') {
            야간업무();
            조기취침();
        }
    }
}
```

위 처럼 If 문 내에 If 문을 사용하여 로직을 처리할 수도 있지만 이를 Early Return으로 처리하여 더 좋은 로직으로 만들 수 있다.   

```
function 오늘하루(condition, weather, isJob) {
    /** 호출한 곳으로 돌아가라 (return;) */
    if (condition === 'GOOD') {
        return;
    }

    공부();
    게임();
    유튜브보기();

    if (weather === 'GOOD') {
        운동();
        빨래();
    }

    if (isJob === 'GOOD') {
        야간업무();
        조기취침();
    }
}
```