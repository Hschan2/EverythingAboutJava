// Spread Syntax
const item = { type: '옷', size: 'M' };
const detail = { price: 20, made: 'Korea', gender: 'M' };

// Bad Code
item['price'] = detail.price;

const newObject = new Object();
newObject['type'] = item.type;
newObject['size'] = item.size;
newObject['price'] = detail.price;
newObject['made'] = detail.made;
newObject['gender'] = detail.gender;

const newObject2 = {
    type: item.type,
    size: item.size,
    print: detail.price,
    made: detail.made,
    gender: detail.gender,
};

// Good Code
// Old
const shirts0 = Object.assign(item, detail);

// New
// price: 40 => 새로운 값 할당
const shirt = { ...item, ...detail, price: 40};

// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
// Spread Syntax - Array
let fruits = ['Apple', 'Banana', 'Orange'];

// 배열 맨 뒤에 새로운 값 추가
fruits = [...fruits, 'Strawberry'];

// 배열 맨 앞에 새로운 값 추가
fruits = ['Grape', ...fruits];

// 배열 합치기
const fruits2 = ['Melon', 'Peach', 'Carrot'];

// Old
let combined = fruits.concat(fruits2);

// New
combined = [...fruits, ...fruits2];