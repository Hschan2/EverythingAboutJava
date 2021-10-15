# Generator & Iterator

## Generator
순회가 가능한 데이터 컬렉션인 Iterable과 Iterator 객체를 말한다.

### Iteration Interface
일반적으로 알고 있는 배열 순회 방법이 있다. 예를 들어서 <b>For 문</b>이 존재한다.   

For 문은 배열의 길이를 통해 구한 Index에 의존해 순회하는 방법이다. 이 방법에는 순회를 위한 길이가 필요하다. 이 방법은 사용하기 좋지만 코드가 명령적이고 의도가 덜 드러나보인다는 것이 단점이다.   

그러나 자바스크립트에서는 또 다른 방법이 존재한다. <b>For object문</b>이다. 이 방법은 길이 기반의 순회와 달리 배열 그 자체만으로 순회를 할 수 있다. 이 방법은 무엇을 하려는 의도가 보다 선언적으로 잘 알 수 있다.   

### Iteration Interface의 세가지 인터페이스
* Iterable
    * Iterator 객체를 반환하는 함수를 값으로 갖는 @@iterator (가상) 프로퍼티를 포함해야 한다. (실제로는 symbol.iterator)
* Iterator
    * IteratorResult를 반환하는 next 메서드를 포함해야 한다.
* Iterator Result
    * Boolean 타입의 값을 갖는 done과 모든 타입의 값을 가질 수 있는 value 프로퍼티를 갖는다.
    * iterator가 끝에 도달했을 시 done이 true가 되고 value는 iterator의 반환값이 된다.
    * done은 순회 완료 여부를 말하고 value는 최근 순회 요소를 말한다.

### Iterable을 활용한 다른 자바스크립트 문법
* Spread 연산자 (...)
    * Math.max(): 전달받은 인자 중 가장 큰 값을 반환하는 메서드 ( 예. (Math.max(...[1, 2, 3])) )

## Iterator을 활용하기
* Generator: Generator 함수는 Generator 인스턴스를 생성한다. 이를 활용하여 배열의 길이을 가져올 수 있다.
* Generator 인스턴스: Iterable 인터페이스와 Iterator 인터페이스를 동시에 구현한다.
    * Iterable 인터페이스: @@iterator (Symbol.iterator) 프로퍼티에 Iterator 객체인 자기 자신을 반환하는 함수를 저장
    * Iterator 인터페이스: value와 done을 반환하는 next 메서드를 포함

## Generator Syntax (문법)
* function*: 일반적인 함수와 똑같이 사용이 가능하다. Generator 함수 선언 키워드이다.
* yield: Generator 함수 내부에서 사용한다. next 메서드 호출 시, value로 반환할 값에 사용하는 키워드이다. 다음 next가 호출될 때까지 해당 키워드에서 함수 실행이 멈춘다.
* yield*: 또 다른 Iterable 객체를 위임해서 사용하기 위한 키워드이다.

## Lazy Evaluation (지연 평가)
계산의 결과값이 필요할 때까지 계산을 늦추는 기법을 말한다. 그리고 필요한 데이터를 필요한 순간에 생성한다. 평소에 자주 사용하는 함수 기법보다 Lazy Evaluation를 사용하면 필요한 값만 가져올 수 있는 효율적인 함수형 코드 작성이 가능하다.

## Generator 함수와 Generator 인스턴스와 프로토타입 (Generator Inside)
new 키워드를 사용한 인스턴스 생성 ( [constructor] )이 아닌 일반적인 함수호출( [[call]] )을 통해서 인스턴스를 생성한다.   

## Generator 인스턴스의 Internal Slot
* Generator의 상태 (실행 중인가? 중단이 됐는가?) 정보가 ( [[GeneratorState]] )에 저장된다.
* 실행 컨텍스트가 ( [[GeneratorContext]] )에 저장된다.

## 실행이 중단된 Generator가 next()가 호출되면 중단 지점부터 다시 실행될 수 있는 이유
두 개의 Internal Slot을 갖고 있기 때문이라고 말할 수 있다.   

## 영상을 꼭 시청해서 예시를 확인하자

[Generator & Iterator](https://www.youtube.com/watch?v=3uuBHt_SNTA)