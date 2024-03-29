# SemVer
배포 버전을 'Major(주).Minor(부).Patch(수)'로 한다.   

기존 버전과 호환되지 않게 API가 바뀌면 Major를 올리고, 기본 버전과 호환되면서 새로운 기능을 추가할 때는 Minor를 올리고, 기존 버전과 호환되면서 버그를 수정한 것이라면 Patch를 올린다.   

위 형식에 정식 배포 전 버전이나 빌드 메타데이터를 위한 라벨을 덧붙이는 방법도 있다.   

이 버전을 체계성이 없어 발생하는 '의존성 지옥'에서 벗어난 '유의적 버전'이라고 불리우며, 이 체계를 따르면 버전 번호와 그 번호를 바꾸는 방법을 통해 특정 버전에서 다음 버전으로 넘어가면서 코드가 어떻게 바뀌는지 드러낼 수 있다.   

## 유의적 버전 명세
1. 유의적 버전을 사용하는 소프트웨어는 반드시 공개 API를 선언   
    * 해당 API는 코드 자체로 선언하거나 문서로 엄격히 명시
    * 어떤 방식으로든, 정확하고 이해하기 쉽게 명시   
2. 보통 버전 번호는 반드시 'X.Y.Z' 형태로 하고, 'X.Y.Z'는 각각 자연수(정수)이고, 절대로 0이 앞에 붙지 않음   
    * X는 주, Y는 부, Z는 수 번호이며 각각 반드시 증가하는 수   
3. 특정 버전으로 패키지를 배포하고 나면, 그 버전의 내용은 절대 변경 불가   
    * 변경분이 있따면 반드시 새로운 버전으로 배포   
4. Major(주) 0은 초기 개발을 위해서 사용   
    * 아무 때나 마음대로 변경이 간으하며, 해당 공개 API는 안정판으로 보지 않음   
5. 1.0.0 버전은 공개 API를 정의   
    * 이후의 버전 번호는 이 때 배포한 공개 API에서 어떻게 바뀌는지에 따라 올림   
6. Patch(수)은 반드시 그전 버전 API와 호환된느 버그 수정의 경우에만 올림   
    * 버그 수정은 잘못된 내부 기능을 고치는 것이라 정의   
7. 공개 API에 기존과 호환되는 새로운 기능을 추가할 때 반드시 Minor 버전 올림   
    * 공개 API의 일부를 앞으로 제거할 것으로 표시한 경우에도 반드시 올림
    * 내부 비공개 코드에 새로운 기능이 대폭 추가되거나 개선사항이 있을 때도 올림 가능
    * Minor 버전 올릴 때 Patch 버전을 올릴 때만큼의 변화 포함 가능
    * Minor 버전이 올라가면 Patch 버전은 반드시 0에서 다시 시작   
8. 공개 API에 기존과 호환되지 않은 변화가 있을 때는 반드시 Major 버전 올림   
    * Minor 버전 또는 Patch 버전 변화 포함 가능
    * Major 버전 번호를 올릴 때는 반드시 Minor 버전과 Patch 버전을 0으로 초기화   
9. Patch 버전 바로 뒤에 붙임표(-)를 붙이고 마침표(.)로 구분된 식별자를 더해서 정식 배포를 앞둔 (Pre-release) 버전 표기 가능   
    * 식별자는 반드시 아스키 문자, 숫자, 붙임표로만 구성([0-9A-Za-z-])
    * 식별자는 반드시 한 글자 이상
    * 숫자 식별자의 경우 절대 앞에 0을 붙인 숫자로 표기하지 않음
    * 정식배포 전 버전은 관련한 보통 버전보다 우선순위가 낮음
    * 정식배포 전 버전은 아직 불안정하며 연관된 일반 버전에 대해 호환성 요구사항 충족되지 않음   
10. 빌드 메타데이터는 수버전이나 정식배포 전 식별자 뒤에 + 기호를 붙인 뒤 마침표로 구분된 식별자를 덧붙여 표현 가능   
    * 식별자는 반드시 아스키 문자와 숫자와 붙임표로만 구성([0-9A-Za-z-])
    * 식별자는 반드시 한 글자 이상
    * 빌드 메타데이터는 버전 간 우선순위를 판단하고자 할 때 반드시 무시
    * 빌드 메타데이터만 다른 두 버전의 우선순위는 같음   
11. 우선순위는 버전 순서를 정렬할 때 서로를 어떻게 비교할 지 나타냄   
    * 우선순위는 반드시 Major, Minor, Patch 그리고 정식 배포 전 버전의 식별자를 나누어 계산
    * 빌드 메타데이터는 우선순위에 영향없음
    * 우선순위는 차례로 비교하면서 차이가 나는 부분이 나타나면 결정
    * Major, Minor, Patch는 수자로 비교
    * 숫자로만 구성된 식별자는 수의 크기로 비교하고 알파벳이나 붙임표가 포함된 경우에 아스키 문자열 정렬
    * 숫자로만 구성된 식별자는 어떤 경우에도 문자와 붙임표가 있는 식별자보다 낮은 우선순위
    * 앞선 식별자가 모두 같은 배포 전 버전의 경우에는 필드 수가 많은 쪽이 더 높은 우선순위   

## 유효한 유의적 버전 BNF(Backus-Naur Form) 문법
```
<유의적 버전> ::= <버전 몸통>
             | <버전 몸통> "-" <배포 전 버전>
             | <버전 몸통> "+" <빌드>
             | <버전 몸통> "-" <배포 전 버전> "+" <빌드>
```

```
<버전 몸통> ::= <주> "." <부> "." <수>
```

```
<주> ::= <숫자 식별자>

<부> ::= <숫자 식별자>

<수> ::= <숫자 식별자>
```

```
<배포 전 버전> ::= <마침표로 구분된 배포 전 식별자들>
```

```
<마침표로 구분된 배포 전 식별자들> ::= <배포 전 식별자>
                              | <배포 전 식별자> "." <마침표로 구분된 배포 전 식별자들>
```

```
<빌드> ::= <마침표로 구분된 빌드 식별자들>
```

```
<마침표로 구분된 빌드 식별자들> ::= <빌드 식별자>
                            | <빌드 식별자> "." <마침표로 구분된 빌드 식별자들>
```

```
<배포 전 식별자> ::= <숫자와 알파벳으로 구성된 식별자>
                | <숫자 식별자>
```

```
<빌드 식별자> ::= <숫자와 알파벳으로 구성된 식별자>
             | <숫자들>
```

```
<숫자와 알파벳으로 구성된 식별자> ::= <숫자 아닌 것>
                             | <숫자 아닌 것> <식별자 문자들>
                             | <식별자 문자들> <숫자 아닌 것>
                             | <식별자 문자들> <숫자 아닌 것> <식별자 문자들>
```

```
<숫자 식별자> ::= "0"
             | <양의 숫자>
             | <양의 숫자> <숫자들>
```

```
<식별자 문자들> ::= <식별자 문자>
               | <식별자 문자> <식별자 문자들>
```

```
<식별자 문자> ::= <숫자>
             | <숫자 아닌 것>
```

```
<숫자 아닌 것> ::= <문자>
              | "-"
```

```
<숫자들> ::= <숫자>
         | <숫자> <숫자들>
```

```
<숫자> ::= "0"
        | <양의 숫자>
```

```
<양의 숫자> ::= "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
```

```
<문자> ::= "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J"
        | "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" | "S" | "T"
        | "U" | "V" | "W" | "X" | "Y" | "Z" | "a" | "b" | "c" | "d"
        | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" | "n"
        | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x"
        | "y" | "z"
```

## 유의적 버전을 사용해야 하는 이유
이는 혁신적인 아이디어가 아니다. 이미 비슷한 방식으로 버전을 정해서 사용하고 있을 수 있다. 문제는 '비슷한' 방식으로는 충분하지 않는 것이다. 어떤 형태로 정식 명세를 정해서 따르지 않는다면, 버전 번호는 의존성 관리에서 무의미하다. 이상의 아이디어에서 이름을 정하고 명시적인 정의를 내림으로써, 소프트웨어 사용자에게 제작자의 의도를 전달하기 쉬워진다. 의도가 명확해야지만, 융통성 있는 의존성 명세를 만들 수 있다.   

예를 들어, "Firetruck" 라이브러리가 있을 때, 해당 라이브러리는 유의적 버전이 붙은 "Ladder"라는 패키지를 의존한다. 해당 라이브러리를 만들었을 때, Ladder는 버전 3.1.0일 때, Firetruck이 3.1.0에 처음으로 추가된 기능을 사용했기 때문에, Ladder의 의존성을 3.1.0 이상, 4.0.0 미만으로 지정할 수 있다. 이제 Ladder의 3.1.1 버전과 3.2.0 버전이 공개된다면, 패키지 관리 시스템에 그 버전을 넣을 수 있고 기존 소프트웨어와 호환될 것이다.   

당연히, 책임감 있는 개발자는 패키지가 업그레이드된 부분이 홍보된 대로 제대로 작동하고 있는지 검증할 것이다. 실상은 지저분하며, 조심해서 관리하는 것 외에는 방법이 없다. 유의적 버전을 사용함으로써 의존하는 패키지들의 새 버전들과 충돌없이, 시간 낭비 없이 패키지를 공개하고 업그레이드할 수 있다.   

만약, 유의적 버전을 쓰기 시작하기 위해서는 그렇게 하고자 마음먹고 규칙을 따르기만 하면 된다. README 파일에 배포한 웹사이트의 링크를 추가해서 다른 사람들도 유용하게 사용할 수 있도록 하자.   

## 추가 내용
* 초기 개발 단계 배포는 0.1.0, 이후 배포마다 Minor 버전 올림
* 소프트웨어가 실 서비스에 쓰이기 시작했다면 이미 1.0.0, 또는 사용자들이 믿고 쓸 수 있는 안정적인 API가 있다면 1.0.0, 하위 버전 호환성에 대해 우려하고 있다면 1.0.0
* 제거하는 기능들이 있다면, 문서를 업데이트해서 사용자들에게 변화를 알리고, 해당 기능이 제거될 것이라는 표시된 새 Minor 버전을 적절한 시기에 배포
    * 새 Major 버전에서 완전히 기능을 제거하기 전에, 제거될 것이라고 표시한 Minor 버전 배포를 최소 한 번 진행해서 사용자들이 원활하게 새로운 API 사용할 수 있도록 진행
* 유의적 버전을 확인할 수 있는 정규식(RegEx)
    * 첫 번째는 캡처 그룹 이름 지정 방식의 정규식
    * 두 번째는 캡처 그룹 이름 지정 방식을 지원하지 않는 경우의 정규식
```
^(?P<major>0|[1-9]\d*)\.(?P<minor>0|[1-9]\d*)\.(?P<patch>0|[1-9]\d*)(?:-(?P<prerelease>(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+(?P<buildmetadata>[0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?$
```

```
^(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?$
```

[참고 문서](https://semver.org/lang/ko/)