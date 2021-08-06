// Nullish Coalescing Operator

// Bad Code
function printMessage(text) {
    let message = text;

    if (text == null || text == undefined) {
        message = 'Nothing to display';
    }
    console.log(message);
}

// Good Code
function printMessage(text) {
    // null, undefined일 경우에만 오른쪽 코드 실행
    // 오른쪽 코드에 함수 호출도 가능
    const message_1 = text ?? 'Nothing to display';
    // falsy(undefined, null, false, 0, -0, NaN, "", '', ``)일 경우에 모두 오른쪽 코드 실행
    const message_2 = text || 'Nothing to display';
    console.log(message_1);
}

printMessage('Hello'); // Hello
printMessage(undefined); // Nothing to display
printMessage(null); // Nothing to display