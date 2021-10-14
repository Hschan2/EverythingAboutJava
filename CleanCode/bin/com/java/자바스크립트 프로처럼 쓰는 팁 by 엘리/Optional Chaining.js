// Optional Chaining
const bob = {
    name: 'Hong',
    age: 20,
};

const anna = {
    name: 'Chan',
    age: 25,
    job: {
        title: 'Programmer',
    },
};

// Bad Code
function displayJobTitle(person) {
    if (person.job && person.job.title) {
        console.log(person.job.title);
    }
}

// Good Code
function displayJobTitle(person) {
    // job? => job이 있다면
    if (person.job?.title) {
        console.log(person.job.title);
    }
}

function displayJobTitle(person) {
    const title = person.job?.title ?? 'No Job Yet';
    console.log(title);
}