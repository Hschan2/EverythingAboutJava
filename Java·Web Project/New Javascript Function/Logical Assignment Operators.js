// Logical Assignment Operators
// 조건문을 쉽게

// 이전 JS
let before_1 = 1;

// 변수가 거짓이 아니라면
if (before_1) {
    before_1 = 2;
}

let before_2 = false;
if (!before_2) {
    before_2 = true;
}

let before_3 = undefined;
if (before_3 === undefined) {
    before_3 = "Hello";
}

// 이후 JS
let after_1 = 1;

// 변수가 거짓이 아니라면
after_1 &&= 2;

let after_2 = false;
after_2 ||= true;

let after_3 = undefined;
after_3 ??= "Hello";