# TDD (Test-Driven Development, 테스트 주도 개발)
테스트로부터 시작하는 개발 방식이다.   

* (실패하는) 테스트 코드 작성하기
* 테스트를 통과시킬 만큼 구현하기
* 코드 정리하기 (리팩토링)   

## 맛보기: 암호 검사기
규칙을 모두 충족하면 매우 강함을 나타낸다.   
* 길이가 8글자 이상
* 0부터 9 사이의 숫자를 포함
* 대문자 포함   

2개의 규칙을 충족하면 보통이고 1개 이하의 규칙을 충족하면 약함을 나타낸다.   

## TDD (테스트 주도 개발)을 하고 나서
테스트가 개발을 주도한다. 그리고 지속적인 코드를 정리할 수 있으며 개발 과정에서 리팩토리를 진행할 수 있다. 또한, 빠른 피드백이 가능하여 만든 코드가 올바른지 바로 알 수가 있다. 그리고 테스트를 통과할 만큼만 코드를 작성하고 필요하지 않은 코드를 미리 작성하지 않을 수 있다. (일부) 설계 진행이 가능하며 테스트할 대상의 이름, 기능 이름, 결과 타입 등이 가능하다.   

```
public class PasswordMeter {

    public PasswordStrength meter(String pw) {
        if (isEmptyorNull(pw)) {
            throw new IllegalArgumentException();
        }

//      숫자로 비밀번호 안전성 판단하기
        int metCount = calculateMetCount(pw);

        if (metCount == 1 || metCount == 0) {
            return PasswordStrength.WEAK;
        }

        if (metCount == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean isEmptyorNull(String pw) {
        if (pw == null || pw.isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean meetLength(String pw) {
        if (pw.length() >= 8) {
            return true;
        }

        return false;
    }

//  패스워드에 대문자가 없는 경우
    private boolean containsUppercase(String pw) {
        for (char ch : pw.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                return true;
            }
        }
        return false;
    }

//  패스워드에 숫자가 없는 경우
    private boolean containsDigit(String pw) {

        for (char ch : pw.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

//  조건에 따라 만족하는 수 Count
    private int calculateMetCount(String pw) {
        int metCount = 0;

        if (meetLength(pw)) metCount++;
        if (containsUppercase(pw)) metCount++;
        if (containsDigit(pw)) metCount++;

        return metCount;
    }

}
```

우선 첫 번째는 빈 값 혹은 Null 값이 발생할 경우 Exception 처리를 하기 위한 코드이다. (PasswordMeterTest에서 Expecting code to raise a throwable에러를 통과시키기 위해)   

```
if ("abcC123".equals((pw)) || "123abcC".equals((pw)) || "Cabc12".equals((pw))) {
    return PasswordStrength.NORMAL;
}
```

임의로 설정하여 테스트를 할 수 있지만 좋은 방법은 아니다. (예.비밀번호가 8미만 일 경우의 테스트를 통과하는 임의 방법)   

```
boolean lengthRule = meetLength(pw);
boolean containsUp = containsUppercase(pw);
boolean containsDi = containsDigit(pw);

// 패스워드가 8미만
if (!lengthRule) {
    return PasswordStrength.NORMAL;
}

// 비밀번호에 대문자가 포함되지 않는 경우 처리
if (!containsUp) {
    return PasswordStrength.NORMAL;
}

// 비밀번호에 숫자가 포함되지 않는 경우 처리
if (!containsDi) {
    return PasswordStrength.NORMAL;
}
```

위 코드처럼 세 개의 Boolean 변수는 테스트 코드에서 딱 한 번만 사용하기 때문에 사실상 선언할 필요가 없다. 여러 번 사용하는 경우, 변수로 담아서 사용하지만 딱 한 번 사용하는 것은 변수로 선언할 필요가 없다.   

```
// 비밀번호 8글자 이상 조건만 충족
if (!lengthRule && containsUp && !containsDi) {
    return PasswordStrength.WEAK;
}

// 비밀번호에 대문자 조건만 충족
if (lengthRule && !containsUp && !containsDi) {
    return PasswordStrength.WEAK;
}
```

만약 변수 처리를 해서 테스트 할 경우, 위 코드처럼 조건을 걸고 열거 타입을 반환할 수 있다.   

```
public enum PasswordStrength {
    NORMAL, WEAK, STRONG
}
```

위는 Enum의 열거 타입을 선언한 것이다.   

## 중간 정리
위 코드를 다시 설명하자면, 비밀번호가 약하면 WEAK, 중간이면 NORMAL, 강하면 STRONG을 반환하는 코드이다.   

비밀번호가 약한 경우는, 비밀번호가 8 길이 이상, 대문자 포함, 숫자 포함 중에서 하나도 만족하지 않거나 하나만 만족하는 경우이다.   

비밀번호가 중간인 경우는, 조건 중에서 두 가지가 만족하는 경우이다.   

그리고 비밀번호가 강한 경우는, 모든 조건을 만족했을 때이다.   

## 실제로 테스트하기 위한 코드
```
public class PasswordMeterTest {

    private PasswordMeter passwordMeter = new PasswordMeter();

    @DisplayName("Null 입력이면 Exception 발생")
    @Test
    void nullInput() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(null));
    }

    // 빈 값 입력 시 에러 처리
    @DisplayName("빈 값 입력이면 Exception 발생")
    @Test
    void emptyInput() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(""));
    }

    // 자주 사용하는 코드를 반복하는 것의 방지를 위해 리팩토링
    private void assertPasswordStrength(String password, PasswordStrength expected) {
        PasswordStrength result = passwordMeter.meter(password);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("모든 조건을 충족하면 강함 출력")
    @Test
    void meetAllRules() {
        assertPasswordStrength("abcABC123", PasswordStrength.STRONG);
        assertPasswordStrength("123abcABC", PasswordStrength.STRONG);
        // PasswordMeter 클래스에서 return PasswordStrength.STRONG; 를 추가하지 않은 상태에서 실행하면 통과시키지 못한다.
    }

    @DisplayName("길이가 8 미만이고 다른 조건 충족")
    @Test
    void digitAndUppercase() {
        assertPasswordStrength("abcA123", PasswordStrength.NORMAL);
        assertPasswordStrength("123abcC", PasswordStrength.NORMAL);
        assertPasswordStrength("Cabc12", PasswordStrength.NORMAL);
    }

    @DisplayName("대문자 없고 다른 조건 충족")
    @Test
    void digitAndLength() {
        assertPasswordStrength("abcd1234", PasswordStrength.NORMAL);
        assertPasswordStrength("1234abcd", PasswordStrength.NORMAL);
        assertPasswordStrength("abc123", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자 없고 다른 조건 충족")
    @Test
    void uppercaseAndLength() {
        assertPasswordStrength("abcABCabc", PasswordStrength.NORMAL);
        assertPasswordStrength("ABCabcABC", PasswordStrength.NORMAL);
    }

    @DisplayName("길이만 충족")
    @Test
    void length() {
        assertPasswordStrength("abcdefghijk", PasswordStrength.WEAK);
    }

    @DisplayName("대문자만 충족")
    @Test
    void uppercase() {
        assertPasswordStrength("ABCdef", PasswordStrength.WEAK);
    }

    @DisplayName("숫자만 충족")
    @Test
    void digit() {
        assertPasswordStrength("abc123", PasswordStrength.WEAK);
        assertPasswordStrength("123abc", PasswordStrength.WEAK);
    }

    @DisplayName("모두 충족하지 않음")
    @Test
    void nothing() {
        assertPasswordStrength("awef", PasswordStrength.WEAK);
        assertPasswordStrength("azd", PasswordStrength.WEAK);
    }
}
```

첫 번째 코드는 패스워드가 Null 값일 때 발생하는 에러 처리를 한다. (Null 입력이면 Exception 발생) PasswordMeter 클래스를 생성하고 실행하면 Expecting code to raise a throwable 출력이 되기 때문이다.   

[테스트 주도 개발](https://www.youtube.com/watch?v=6Vt-wKPBbuc)