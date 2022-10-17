# 객체의 Computed Property Name과 Lookup Table 활용

<h4>Computed Property Name</h4>은 객체의 Key 값을 대괄호로 묶인 표현식으로 정의할 수 있는 문법을 말한다. 즉, 프로퍼티 이름이 계산이 될 수 있다.   

```
const funcName0 = 'func0';
const funcName1 = 'func1';
const funcName2 = 'func2';

const obj = {
    [funcName0]() {
        return 'func0';
    },
    [funcName1]() {
        return 'func1';
    },
    [funcName2]() {
        return 'func2';
    },
};

for (let i = 0; i < 3; i++) {
    console.log(obj[`func${i}`]);
}

/*
[Function: func0]
[Function: func1]
[Function: func2]
*/
```

<h4>Object Lookup Table</h4>은 실제 있는 문법은 아니다. 그러나 Switch 문이나 If else 문이 길게 늘어질 경우르르 대비해 명시적이며 효율적인 코드로 작성 할 수 있도록 할 수 있음믈 말한다.   

```
function getUserType(type) {
    switch (type) {
        case 'ADMIN':
            return '관리자';
        case 'INSTRUCTOR':
            return '강사';
        case 'STUDENT':
            return '수강생';
        default:
            return '해당 없음';
    }
}

console.log(getUserType('INSTRUCTOR')); // 강사
console.log(getUserType('a')); // 해당 없음
```

Switch 문으로 이루어진 위의 코드를 Object와 상수를 이용해 일종의 Lookup Table처럼 리팩토링하여 작성할 수 있다.   

```
function getUserType(type) {
    const USER_TYPE = {
        ADMIN: '관리자',
        INSTRUCTOR: '강사',
        STUDENT: '수강생',
        UNDEFINED: '해당 없음',
    };

    // type에 해당하는 값이 있으면 해당 값을 출력 아니면 해당 없음 출력
    return USER_TYPE[type] ?? USER_TYPE.UNDEFINED;
}

console.log(getUserType('INSTRUCTOR')); // 강사
console.log(getUserType('a')); // 해당 없음
```