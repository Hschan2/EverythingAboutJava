// Ternary Operator

// Bad Code
function getResult(score) {
    let result;

    if (score > 5) {
        result = 'good';
    } else if (score <= 5) {
        result = 'bad';
    }
    return result;
}

// Good Code
function getResult(score) {
    return score > 5 ? 'good' : 'bad';
}