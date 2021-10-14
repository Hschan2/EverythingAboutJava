// 중복 제거하기
const array = ['Dog', 'Cat', 'Rabbit', 'Dog', 'Puppy', 'Cat'];
console.log(array);

// 정답
// Set은 중복을 허용하지 않음
// [...]을 이용해서 새로운 배열을 생성
console.log([...new Set(array)]);