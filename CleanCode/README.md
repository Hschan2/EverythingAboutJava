# 의식적으로 TDD와 리팩토링 연습하기

## TDD 연습하기
- 토이 프로젝트에서 연습하자
- UI와 DB에 의존관계를 갖기 않는 요구사항으로 연습하자
- 목적 난이도가 낮은 것고 익숙한 것부터 연습하자

## 리팩토링 연습하기
- 명확하고 구체적인 목적을 가지자
- 피드백과 피드백에 따른 행동 변경을 수반하자
- 측정 가능한 방법으로 추진하자

1. 한 메서드에 오직 한 단계의 들여쓰기를 하자   
1. else 예약어를 쓰지 않는다 (IF 문에서 바로 return하는 것이 간결하고 명확하다)   

## 메소드가 한 가지 일을 하도록 구현해보는 예
```
public static int sum(String[] values) {
  int result = 0;
  for (String value : values) {
    result += Integer.parseInt(value);
  }
  return result;
}
```
위 sum 메소드는 2가지 역할을 하고 있다. 문자열을 숫자로 변환하는 역할과 합을 계산하는 역할   

이를 쪼개어보자
```
public static int[] toInts(String[] values) {
  int[] numbers = new int[values.length];
  for (int i = 0; i < values.length; i++) {
    numbers[i] = Integer.parseInt(values[i]);
  }
  return numbers;
}

public static int sum(int[] numbers) {
  int result = 0;
  for (int number : numbers) {
    result += number;
  }
  return result;
}
```

toInts 메소드는 int[]로 선언하고 매개변수를 배열로 받아 여러 개의 값을 받아온다. 그리고 매개변수로 받은 값들을 , 혹은 :로 분리해 문자열을 숫자로 변환시켜주는 단 하나의 역할을 한다.   

<br/>

sum 메소드는 모든 값을 받아 합계를 구해주는 단 하나의 역할을 한다

### 로컬 변수로 설정하지 말자
```
String[] values = text.split(",|:");
int[] numbers = toInts(values);
return sum(numbers);
```

위는 값을 나눠주는 값을 넣은 변수와 문자열을 숫자로 변환시켜주는 값을 담는 변수 그리고 마지막으로 합계를 리턴한다. 그러나 이렇게 많은 변수를 선언하면 효율적이지 않다.   

이를

```
return sum(toInts(text.split(",|:")));
```

로 바로 return하면 간략하면서도 효율적이다.

### compose method 패턴과 추상화 단계를 적용하자
```
public static int splitAndSum(String text) {
    if (text == null || text.isEmpty()) { //추상화 단계 0
      return 0;
    }
    return sum(toInts(text.split(",|:"))); //추상화 단계 1
```
위의 추상화 단계가 0인 if절을 수정하여 추상화 단계를 1로 바꿔보자
```
public static int splitAndSum(String text) {
  if (isBlank(text)) {
    return 0;
  }
  return sum(toInts(text.split(",|:")));
}

public static boolean isBlank(String text) {
  return text == null || text.isEmpty();
}
```
추상화 단계의 0으로 text == null || text.isEmpty()을 사용했다면 이를 추상화 하기 위해 메소드를 하나 만들어 분리해주자.   

isBlank로 메소드를 만들어 text == null || text.isEmpty();를 return 해주자.   

이렇게 메소드로 분리해 추상화 단계를 진행하면 보기에 더 간략하고 깔끔하다   

- 한 번에 리팩토링 하려고 하지 말자. 하나에 한 번씩 천천히 진행해보자
- 메소드의 라인 길이를 한 번에 줄이려고 하지 말자. 20줄을 15줄로, 15줄을 10줄로 천천히 줄여보자   

### 추상화를 클래스로 하면 더 객체지향적이 된다
```
public static int[] toInts(String[] values) {
  int[] numbers = new int[values.length];
  for (int i = 0; i < values.length; i++) {
    numbers[i] = toInt(values[i]); //메소드 분리
  }
  return numbers;
}

public static int toInt(String value) { //이 부분이 모든 원시값과 문자열이다.
  int number = Integer.parseInt(value);
  if (number < 0) {
    throw new RuntimeException();
  }
  return number;
}
```
위 toInt 메소드는 받은 문자열의 값을 숫자로 변환시켜주고 양수인지 음수인지 확인시켜주는 역할을 한다. 이를 클래스로 만들어 본다면?
```
class Positive {
  private int number;

  public Positive(String value) {
    int number = Integer.parseInt(value);
    if (number < 0) {
      throw new RuntimeException(); // 음수면 에러를 던진다
    }
    this.number = number;
  }
}
```
Positive 클래스를 사용하는 것은 0보다 큰 것이 보장된다는 것이다. 이를 public static int, public static String으로 선언하지 않고 public static Positive[]로 선언한다면 객체지향적인 코드가 된다. 이렇게 사용하는 것은 0보다 크다는 것이 보장되기 때문이다.   

### 클래스로 분리하기 위한 방법을 쓰는 연습을 하기 위한 규칙
- 일급 콜렉션을 사용하자 => 클래스로 래핑하라는 것. 원시적인 int 혹은 String 등으로 래핑했지만 클래스로 래핑한다면 콜렉션들(자바의 자료구조)을 매핑하는 데에 관련된 로직이 들어올 수 있다
- 3개 이상의 인스턴스 변수를 선언하지 말자 => 인스턴스 변수는 int, String 등을 말한다. 이를 줄이는 연습을 한다면 객체적인 코드를 작성할 수 있다

### 그 다음은?
1. 게임을 이용해 더욱 많은 연습을 하면 좋다. 로또, 사다리 타기, 볼링 게임 점수판, 체스 게임, 지뢰 찾기 게임 등   
1. 웹, 모바일 UI, DB와 같은 의존 관계를 추가해서 연습하자   

## 총 정리
1. 한 메서드에 오직 한 단계의 들여쓰기만 한다.   
1. else 예약어를 쓰지 않는다.   
1. 모든 원시값과 문자열을 포장한다.   
1. 한 줄에 점을 하나만 찍는다.   
1. 줄여쓰지 않는다(축약 금지).   
1. 모든 엔티티를 작게 유지한다.   
1. 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다. (int, String...)   
1. 일급 콜렉션을 쓴다. (클래스)   
1. 게터/세터/프로퍼티를 쓰지 않는다.   
1. 메소드의 인자의 효율적인 갯수는 0개이지만 1개 그리고 2개까지 사용해도 좋다. 다만 3개 이상은 비효율적이다.   
1. 클래스의 크기는 작아야 한다. 작아야 하고 더 작아야 좋다.   

<br/>

- 조급하지 않게 천천히 하는 것이 좋다.
- 토이 프로젝트로 아주 낮은 단계부터 연습해보자.
- 반복적으로 연습해서 익히자.

<br/>

## 폴더 목록
|타이틀|폴더로 이동|
|---|:---:|
|**포코의 클린코드**|[이동](https://github.com/Hschan2/EverythingAboutJava/tree/master/CleanCode/%ED%8F%AC%EC%BD%94%EC%9D%98%ED%81%B4%EB%A6%B0%EC%BD%94%EB%93%9C)|
|**백기선의 리팩토링**|[이동](https://github.com/Hschan2/EverythingAboutJava/tree/master/CleanCode/Refactoring)|
|**객체지향 클린코드**|[이동](https://github.com/Hschan2/EverythingAboutJava/tree/master/CleanCode/src/com/java/cleancode)|

<br/>

## 파일 목록
|타이틀|폴더로 이동|
|---|:---:|
|**Collection**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/Collection.md)|
|**Comparable**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/Comparable.md)|
|**Getter 사용하지 말기**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/DoNotGetter.md)|
|**하드코딩 하지 말 것**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/DoNotHardCoding.md)|
|**메소드 이름 짓기**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/MethodNaming.md)|
|**하나의 메소드는 하나의 기능으르 할 것**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/OneMethodOneFunction.md)|
|**Optional**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/Optional.md)|
|**전략 패턴**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/StrategyPattern.md)|
|**변수명 짓기**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/VariableName.md)|
|**실무에서 바로 사용하는 프론트엔드 클린코드**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/%EC%8B%A4%EB%AC%B4%EC%97%90%EC%84%9C%EB%B0%94%EB%A1%9C%EC%93%B0%EB%8A%94%ED%94%84%EB%A1%A0%ED%8A%B8%EC%97%94%EB%93%9C%ED%81%B4%EB%A6%B0%EC%BD%94%EB%93%9C.md)|
|**코딩 잘하는 방법 세 가지**|[이동](https://github.com/Hschan2/EverythingAboutJava/blob/master/CleanCode/%EC%BD%94%EB%94%A9%20%EC%9E%98%ED%95%98%EB%8A%94%20%ED%8C%81%20%EC%84%B8%EA%B0%80%EC%A7%80.md)|