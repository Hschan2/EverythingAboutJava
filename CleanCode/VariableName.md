### JAVA 변수명 네이밍 규칙

읽기 좋은 코드는 좋은 코드라고 한다.

#### 그러나 변수명 네이밍 규칙을 잘 지키지 못한다면 어떤 단점이 생기는가?

- 코드의 유지 보수가 어렵다
```
int a = 33;
int age = 33;
```
위 변수명에서 a는 어떤 의미를 가지고 있는지 알 수 없다. 그러나 age는 나이를 의미하는 것을 확실하게 알 수 있다.

- 협업자에게 배려가 필요하다
변수명이 무엇을 의미하는지 확실하게 나타나야 한다. 그래야 햅업자가 그 변수가 무슨 의미를 담고 있는지 확인할 수 있고 사용할 수 있다.

##### 기본적인 변수명 네이밍 컨벤션
- 컴파일러에서 제한하는 규칙
1. 대소문자는 구분되고 길이 제한은 없다.
2. 예약어를 사용하면 안 된다.
3. 숫자로 시작하면 안 된다.
4. 특수문자는 _과 $만 가능하다.

- JE22에서 권장하는 규칙
1. 첫 글자는 소문자로 명사로 짓는다.
2. 여러 단어로 이루어진 경우 단어의 첫 글자를 대문자로 한다. (카멜 표기법)
```
String userName;
```

#### 좋은 변수명 짓기
- 변수를 지을 때 따로 주석이 필요하면 부적절한 이름이다. 변수명을 지을 때 정확하게 확실하게 의미를 나타내야 한다.
```
String s;
String boardSquare;
```
위 코드에서 볼 때 s는 어떤 것을 의미하는지 정확하게 알 수 없다. 그러나 boardSquare는 적절하게 의미를 나타낼 수 있는 좋은 변수명이다.

- 협업을 염두하자
```
for (int i = 0; i < expressionAsArray.length; i += 2) {
  ...
}
```
위에 반복문에서 i += 2의 2가 무엇을 의미하는지 알 수 없다. 그러나
```
int numberIndex = 2;
for (int i = 0; i < expressionAsArray.length; i += numberIndex) {
  ...
}
```
변수를 선언해서 의미를 두면 이해하기 확실해진다.

- 맥락을 고려해서 짓자
```
public class User{
  String userName;
  int userAge;
  ...
}
```
위 코드는 유저 클래스에서 유저의 이름과 나이를 변수로 선언하는 것이다. <br>
변수를 지을 때 불분명한 짧은 변수 이름보다 의미를 알 수 있도록 긴 이름을 짓는 것이 좋다. <br>
그러나 쓸 데 없이 구구절절한 긴 이름은 멀리하는 것이 좋다.
```
public class User{
    String name;
    int age;
    ...
}
```
위에 클래스처럼 이미 User라는 것을 알 수 있는 클래스 이름에서는 name과 age처럼 표시하는 것이 더 좋다.

- Boolean 타입 변수명 네이밍

전형적인 Boolean 변수 이름을 사용한다. <br>
done, error, found, success, ok과 같이 성공했다는 의미를 알 수 있는 구체적인 이름이 있다면 다른 것으로 대체해도 좋다. <br>
예를 들어서 found, processingComplete <br>
<br>
참 혹은 거짓을 나타낼 때 Boolean 변수의 이름을 사용한다. <br>
status, sourceFile처럼 참인지 거짓인지 정확하게 알 수 없는 이름은 좋지 않다. <br>
statusOK, sourceFileAvailable 또는 sourceFileFound처럼 참 혹은 거짓을 확실하게 알 수 있는 이름으로 짓는 것이 좋다. <br>
변수 이름에 부정적인 의미가 들어있으면 해석하기 어렵다
```
if(notFound == false) {...}
```
위 처럼 not이 들어간 부정적인 이름보다
```
if(found == true) {...}
```
처럼 쉽게 알 수 있는 이름이 좋다.
<br>
자바에서는 접두어 is가 있다. 접두어 is는 자주 사용하지만 때로는 is가 없는 이름이 더 좋을 때가 있다.
```
if(isfound) {...}
if(found) {...}
```
위 코드에서 isfound보다는 found가 더 알기 쉽다.

- 메소드가 여러 번 호출되어 한 눈에 알아보기 힘든 코드
```
private static final String FORMAT_SYMBOL = "//(.*)\\\\n(.*)";

public static List<String> customSplit(String input) {
    Matcher matcher = Pattern.compile(FORMAT_SYMBOL).matcher(input);
    if (matcher.find()) {
        return Arrays.asList(matcher.group(2).split(Pattern.quote(matcher.group(1))));
    }
    ...
}
```

위 처럼 return하는 것에 많은 메소드를 호출해서 알아보기 힘들다. 이럴 때는 나누어서 return하는 것이 좋다.

```
private static final String FORMAT_SYMBOL = "//(.*)\\\\n(.*)";

public static List<String> customSplit(String input) {
    Matcher matcher = Pattern.compile(FORMAT_SYMBOL).matcher(input);
    if (matcher.find()) {
        String splitSymbol = matcher.group(1);
        String content = matcher.group(2);
        return Arrays.asList(content.split(Pattern.quote(splitSymbol)));
    }
    ...
}
```
위 처럼 메소드를 한 번에 return하지 않고 변수를 선언하여 나눈 뒤 return하는 것이 읽기 더 좋다.

- 변수 이름에 자료형이 들어간다면?
```
private List<Double> numberList = new ArrayList<>();
private List<String> operatorList = new ArrayList<>();
```

만약 위 처럼 변수를 선언했을 때 자료형을 List가 아닌 Set을 썼을 때 어떻게 해야 할까? <br>
아마도 변수 이름을 수정해야 할 것이다. <br>
<br>
만약 이렇게 변수 이름을 지었다면 어떨까?

```
private List<Double> numbers = new ArrayList<>();
private List<String> operators = new ArrayList<>();
```

이미 자료형이 List인 것을 알기 때문에 변수명에 List를 넣을 필요가 없다. 그렇기 때문에 자료형이 Set으로 바꾸어도 이름을 바꿀 필요가 없다. <br>
List, Collection등의 자료형은 변수 이름을 지을 때 복수형으로 짓는 것이 좋다. <br>
<br>

변수명 네이밍 출처 - https://woowacourse.github.io/javable/2020-04-24/variable_naming
