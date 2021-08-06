// Template Literals (Template String)
const person = {
    name: 'Hong',
    score: 4,
};

// Bad Code
console.log(
    'Hello ' + person.name + ', Your current score is: ' + person.score
);

// Good Code
console.log(`Hello ${person.name}, Your current score is: ${person.score}`);

const { name, score } = person;
console.log(`Hello ${name}, Your current score is: ${score}`);

// 재사용 활용 가능
function greetings(person) {
    const { name, score } = person;
    console.log(`Hello ${name}, Your current score is: ${score}`);
}