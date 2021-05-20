# Stream (스트림)
스트림은 데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 값 요소이다. (By 모던 자바 인 액션)   

스트림은 컬렉션의 요소를 하나씩 참조해 람다식으로 처리할 수 있는 반복자이다. (By 이것이 자바다)   

즉, <b>데이터 컬렉션 반복</b>을 멋지게 처리하는 기능이라고 생각하는 것이 좋다.   

* Java8 전
```
int sum = 0;
int count = 0;
for(Employee emp: emps) {
    if(imp.getSalary() > 100_000_000) {
        sum += emp.getSalary();
        count++;
    }
}

double average = (double) sum / count;
```
위 처럼 연봉을 구하기 위해 많은 변수들을 사용하여 코드를 구현해야 했다. 그러나   

* Java8 후
```
double average = emps.stream()
    .filter(emp -> emp.getSalary() > 100_000_000)
    .mapToInt(Employee::getSalary)
    .average()
    .roElse(0);
```
이 후, 스트림을 사용한 코드에서는 필요없는 변수들을 제거하여 연봉을 구하는 코드만 구현할 수 있다.   

즉, 이전 코드에서는 외부 반복(How 중심)을 사용하여 구현하였고, 이후 코드에서는 내부 반복(What 중심)으로 코드를 구현하였다.   

### 스트림을 사용하는 이유
1. 자료구조가 포함하는 모든 값을 메소드에 포함하는 컬렉션과 다르게, 스트림은 요청할 때만 요소를 계산하는 고정된 자료구조를 가진다.   
2. 스트림은 여러 개의 조건이 중첩된 상황에서 값이 결정나면 불필요한 연산을 진행하지 않고 조건문을 빠져나와 실행 속도를 높인다. (특정 연산자를 사용할 때 해당한다.)   

### 스트림을 사용하는 방법
1. 컬렉션을 스트림으로 만들어주는 연산자, 스트림 생성 (Stream)   
2. 데이터 처리하는 중간 연산 (Filter, Map)   
3. 연산을 정리하고 결과를 도출하는 최종 연산 (Collect)   

위의 방법을 파이프를 닮았다고 하여 <B>파이프라이닝</B>이라고 부른다.   

그리고 중간 연산 결과 요소를 하나씩 Filter -> map -> Filter -> map 순서로 붙여서 데이터 처리 플로우를 <b>루프 퓨전</b>이라고 부른다.   

만약에 중간 연산에 sorted를 추가로 사용하게 됐을 때, sorted는 <b>중간연산자</b>이면서 <b>최종연산자</b>처럼 이전 연산자들이 실행되도록 한다. 그래서 루프 퓨전이 Filter -> Filter -> sorted -> map -> sorted -> map처럼 실행이 된다.   

### 스트림을 어떻게 사용할까?
* flatMap   
리스트나 컬렉션이 중첩이 되었을 때 사용한다.   

예. List<List<Fish>>
List<Fish>가 여러 개가 있고 이를 하나의 리스트로 만들고 싶을 때.   

Stream을 사용하여 List<Fish>를 Stream List<Fish>로 만드는 것이다. 이 때, flatMap을 사용한다.   

flatMap(List::stream)   

그리고 흩어져 있는 것을 모으기 위해 collect.(Collectors.toList())로 묶는다.   

```
List<Fish> manyFish = fishes.stream()
    .map(Net::getFishes)
    .flatMap(List::stream)
    .collect(Collectors.toList());
```   

* Reduce   
[쉽게 이해하기](https://www.youtube.com/watch?v=Uq4UbhAnsDM)   

```
reduce(10, (acc, x) -> acc + x)

10은 초기화된 숫자이며, 뒤의 식은 람다식으로 정해진 숫자를 반복하여 덧셈을 하는 것이다.

만약 10. 즉, 초기화 숫자를 작성하지 않을 때는 Optional를 반환한다.
```

스트림의 요소는 <b>소모</b>가 된다. <b>Reduce</b>는 각 요소를 소모해가면서 연산을 처리해가는 식이기 때문이다.

[Stream 출처](https://www.youtube.com/watch?v=wsvhgrCGW78)