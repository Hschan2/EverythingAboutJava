// Object Destructuring
const person = {
    name: 'Hong',
    age: 20,
    phone: '00011112222',
}

// Bad Code
function displayPerson(person) {
    displayAvatar(person.name);
    displayName(person.name);
    displayProfile(person.name, person.age);
}

function displayPerson(person) {
    const name = person.name;
    const age = person.age;

    displayAvatar(name);
    displayName(name);
    displayProfile(name, age);
}

// Good Code
function displayPerson(person) {
    const { name, age } = person;

    displayAvatar(name);
    displayName(name);
    displayProfile(name, age);
}