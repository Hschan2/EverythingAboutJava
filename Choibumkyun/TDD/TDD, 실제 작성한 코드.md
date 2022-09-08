* PasswordMeterTest.class

```
public class PasswordMeterTest {

    private PasswordMeter passwordMeter = new PasswordMeter();

//    패스워드가 Null 값일 때 발생하는 에러 처리
//    PasswordMeter 클래스를 생성하고 실행하면 Expecting code to raise a throwable 출력
    @DisplayName("Null 입력이면 Exception 발생")
    @Test
    void nullInput() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(null));
    }

    //    빈 값 입력 시 에러 처리
    @DisplayName("빈 값 입력이면 Exception 발생")
    @Test
    void emptyInput() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(""));
    }

//    자주 사용하는 코드를 반복하는 것의 방지를 위해 리팩토링
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

* PasswordMeter.class

```
public class PasswordMeter {

    // PasswordMeterTest에서 실행하고 나서 처리할 것이 있으면 여기에서 처리
    public PasswordStrength meter(String pw) {
//        PasswordMeterTest에서 Expecting code to raise a throwable에러를 통과시키기 위해
        if (isEmptyorNull(pw)) {
            throw new IllegalArgumentException();
        }

//        비밀번호가 8미만 일 경우의 테스트를 통과하기 위해 임의로 작성하지만 이는 좋은 방법X
//        if ("abcC123".equals((pw)) || "123abcC".equals((pw)) || "Cabc12".equals((pw))) {
//            return PasswordStrength.NORMAL;
//        }

//        metCount를 제외한 나머지 변수들은 한 번씩만 사용하기 때문에 사실상 필요 없다.
        boolean lengthRule = meetLength(pw);
        boolean containsUp = containsUppercase(pw);
        boolean containsDi = containsDigit(pw);

//        NORMAL 부분, 숫자로 판단하는 부분에서 처리
//        패스워드가 8미만
//        if (!lengthRule) {
//            return PasswordStrength.NORMAL;
//        }

//        비밀번호에 대문자가 포함되지 않는 경우 처리
//        if (!containsUp) {
//            return PasswordStrength.NORMAL;
//        }

//        비밀번호에 숫자가 포함되지 않는 경우 처리
//        if (!containsDi) {
//            return PasswordStrength.NORMAL;
//        }

//        비밀번호 8글자 이상 조건만 충족
//        숫자로 판단하는 방법으로 처리 변경
//        if (!lengthRule && containsUp && !containsDi) {
//            return PasswordStrength.WEAK;
//        }

//        비밀번호에 대문자 조건만 충족
//        숫자로 판단하는 방법으로 처리 변경
//        if (lengthRule && !containsUp && !containsDi) {
//            return PasswordStrength.WEAK;
//        }

//        숫자로 비밀번호 안전성 판단하기
//        lengthRule, containsUp, containsDi 변수를 사용했지만 한 번씩만 사용해서 의미 없다.
        int metCount = calculateMetCount(pw);

//        if (meetLength(pw)) metCount++;
//        if (containsUppercase(pw)) metCount++;
//        if (containsDigit(pw)) metCount++;

        if (metCount == 1 || metCount == 0) {
            return PasswordStrength.WEAK;
        }

        if (metCount == 2) {
            return PasswordStrength.NORMAL;
        }

//        meetAllRules를 통과시키기 위해, 위의 조건이 해당되지 않으면
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

    private boolean containsUppercase(String pw) {
//        패스워드에 대문자가 없는 경우
        for (char ch : pw.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String pw) {
//        패스워드에 숫자가 없는 경우
        for (char ch : pw.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private int calculateMetCount(String pw) {
        int metCount = 0;

        if (meetLength(pw)) metCount++;
        if (containsUppercase(pw)) metCount++;
        if (containsDigit(pw)) metCount++;

        return metCount;
    }

}
```

* PasswordStrength.class

```
public enum PasswordStrength {
    NORMAL, WEAK, STRONG
}
```