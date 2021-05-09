# Prototype

## 프로토타입에 대한 얕은 이해
자바스크립트에서는 객체를 상속하기 위하여 프로토타입이라는 방식을 사용한다.   

프로토타입에서의 상속과 클래스에서의 상속은 전혀 <b>다르다</b>. 그 이유는, 자바스크립트에서는 클래스가 없기 때문이다. 프로토타입은 클래스, 객체의 내용 복사 없이도 <b>상속</b>을 구현할 수 있게 해주는 방법이다. 즉, 프로토타입이 하는 일은 상속이 아니라 연결이라고 이해하면 쉽다.

## 클래스가 없다면, 객체를 어떻게 설계대로 찍어낼 수 있을까?
클래스는 객체를 찍어내는 하나의 틀이라고 생각하면 된다. 그러나 자바스크립트에서는 이러한 기능이 없다.

```
자바

class Person {
    constructor(name) {
        this.name = name;
    }

    sayHello() {
        console.log(`${this.name}: hello!`);
    }
}
```

```
자바스크립트

function Person(name) {
    this.name = name;
    this.sayHello = function() {
        console.log(`${this.name}: hello!`);
    }
}
```

### 자바스크립트에는 클래스가 없다.
```
const chris = new Person('chris');
```
new 연산자가 새로운 빈 객체를 메모리 상에 <b>생성</b>한다. 그리고 생선된 빈 객체가 this에 <b>바인딩</b>이 된다. 그리고 this 객체의 속성을 <b>채우는</b> 동작이 수행된다. 마지막으로 return 하는 것이 없다면 그렇게 만들어진 this가 return이 된다.

## 복사 없이 어떻게 상속을 수행할 수 있을까?

### 일반적인 '일부 내용 복사'로서의 상속
Person + Crew의 내용이 <b>복사</b>된 객체이다.

```
class Person {
    constructor(name) {
        this.name = name;
    }

    sayHello() {
        console.log(`${this.name}: hello!`);
    }
}

class Crew extends Person {
    constructor(name) {
        this.name = name;
    }

    doCoding() {
        console.log(`${this.name}: coding!`);
    }
}
```

일반적인 클래스에서는 하나의 클래스가 부모 클래스로부터 상속을 받으면 자식 클래스로 이미 만들어진 인스턴스에서는 부모와 자식 모두의 내용이 합쳐진 부모의 내용이 자식 클래스에 그대로 복사되어 들어간 내용이 인스턴스에도 반영이 된다.   

그러나 자바스크립트에서는 위와 같은 방식이 불가능하다. 객체 자체나 코드 내용을 복하는 일을 수행하지 않기 때문이다. 자바스크립트에서 복사할 수 있는 것은 원시값과 객체를 참조하는 값뿐이다. 그래서 자바스크립트에서 비슷하게 행동하기 위해서 <b>연결(.__poto__)</b>이라는 것을 사용한다.   

* .__poto__ : 객체와 객체를 연결하는 링크   

### 객체와 객체 간의 링크 관계
1. 다른 객체를 바탕으로 만들어진 객체일 때
객체는 자신의 원형이라고 할 수 있는 객체가 있다면 그 객체를 가리키는 __proto__ 링크를 자동으로 가진다.

```
const newObj = Object.create(oldObj)
newObj.__proto__ === oldObj
```

2. 그냥 객체라 아닌 함수일 때
Proto 외에도 하나 더 만들어주는 것이 있는데, 바로 함수의 ProtoType 객체이다. 예를 들어서 Person 함수가 만들어질 때, Person과 자동으로 연결된 ProtoType 객체가 같이 생성된다. Person 함수는 자신의 Prototype 속성을 통해 Person 함수의 ProtoType 객체를 가리키고 Person 함수의 ProtoType 객체는 ProtoType 객체 안 Constructor 속성을 통해 Person 함수를 가리키는 순환 참조 관계를 갖고 있다.   

3. new + 함수로 만들어진 객체일 때
```
const chris = new Person('chris');
+
alpha
```

만들어진 새로운 객체에 __proto__ 링크가 Person 객체의 ProtoType을 가리키게 된다.   

예를 들어서, Person 함수에 의해 Kim 객체가 만들어졌다면 Kim의 Proto 링크는 Person 함수의 ProtoType 객체를 가리킨다.

```
function sayHello() {
    console.log(`${this.name}: hello!`);
}

function Person(name) {
    this.name = name;
}

const chris = new Person('chris');

chris.sayHello();
```

chris 객체는 Person 함수에 의해 생성됐지만 chris 객체 안에 sayHello라는 메소드가 없기 때문에 에러가 발생한다. 그러나 아래처럼 한 줄의 코드를 추가하면 이 문제를 해결할 수 있다.

```
function sayHello() {
    console.log(`${this.name}: hello!`);
}

function Person(name) {
    this.name = name;
}

Person.prototype.sayHello = sayHello;

const chris = new Person('chris');

chris.sayHello();
```

이것이 가능한 이유는, ProtoType Chaining이 발생하기 때문이다.   

* ProtoType Chaining: __proto__를 따라 탐색하기   

### 위 코드와 함께 ProtoType Chaining 이해하기
먼저 chris.sayHello 코드는 chris라는 객체에 sayHello 속성의 존재유무를 확인한다. 없다고 판단이 되면 chris 객체 안에 Proto 속성을 통해 chris 객체를 생성했던 Person 함수의 ProtoType 객체로 이동해서 sayHello를 찾는다. 이 때, 여기서 sayHello를 찾을 수 있어 chris 객체 내부에 sayHello가 있든 없든 sayHello를 실행할 수 있다. 그러나 Person ProtoType 객체에서도 sayHello를 찾을 수 없다면 Person ProtoType 객체의 Proto 링크를 따라 올라간다. 다만, 이럴 경우에 이 Proto 링크는 Person 함수를 생성한 생성자에 ProtoType 객체로 이동하여 sayHello를 찾는다. 만약, 여기서도 발견하지 못한다면 이런 식의 연쇄를 반복한다.   

연쇄는 오브젝트라는 이름에 생성자 함수에 ProtoType 객체에 도달했을 때 멈춘다. 이 때, Proto 링크 안에 Null이 있기 때문에 더 이상 ProtoType Chaining을 진행할 수 없다. 그래서 결국 sayHello를 찾지 못하면 undefined를 반환한다.   

## 프로토타입 체이닝과 Property 할당할 때, 발생하는 일
1. Object.defineProperty(Person.prototype, "sayHello", {
    writeable: false,
    ...
})

읽기 전용일 때, 자바스크립트가 엄격 모드라면 에러가 발생하지만 비엄격 모드라면 아무 일도 발생하지 않는다. sayHello 메소드가 setter라면 그냥 해당 setter가 수행된다. 그러나 일반적인 경우, 읽기 전용이 아니라면 Person ProtoType 객체의 sayHello 메소드에 무엇인가 덮어 씌어지는 것이 아니라 chris 객체의 sayHello 메소드가 추가되는 것이다. 마지막으로 chris 객체를 통해 Person ProtoType의 sayHello에 접근할 수 있는 방법이 사라진다. 이유는, chris 객체 안에 sayHello 메소드를 찾을 수 있기 때문에 ProtoType Chaining이 거기서 멈추기 때문이다. 이렇게 같은 이름의 속성을 객체안에 넣어버리면 기존 ProtoType 객체의 속성에는 접근할 수 없게 되는 경우가 있는데, 이를 <b>가려짐</b>이라고 부른다. 

```
function sayHello() { // Chris 객체에서 해당 함수에 정상적인 방법으로 접근불가
    console.log(`${this.name}: hello!`);
}

function Person(name) {
    this.name = name;
}

Person.prototype.sayHello = sayHello;

const chris = new Person('chris');

chris.sayHello = function() {
    console.log("chris: HI");
}

chris.sayHello();
```



class method overriding 대신 오버라이딩 대신 가려짐이라는 것이 있다.   

## 활용
$querySelector + hide 기능을 추가하여 활용하기   

querySelector로 요소를 불러오는 함수에서 요소 객체가 요소를 가릴 수 있는 method를 포함하도록 만들기   

```
const $1 = (selector, target = document) => {
    const all = target.querySelectorAll(selector);

    const result = all.length > 1 ? [...all] : all[0];

    result.hide = function() {
        result.classList.add('invisible');
    };

    return result;
};

const button1 = $1('#button-1');
button1.hide();
```

그러나 위 코드는, $1 함수가 실행될 때마다 새로운 hide라는 함수가 계속 생성된다. 이러면 메모리 측면에서 효율적이지 못하다.

```
function hide() {
    this.element.classList.add('invisible');
}

function $2(selector, target = document) {
    const all = target.querySelectorAll(selector);
    this.element = all.length > 1 ? [...all] : all[0];
}

$2.prototype.hide = hide;

const button1 = new $2('#button-2');
button1.hide();
```

위 코드에서 $2 함수를 실행하면 만들어진 객체들의 hide 함수 하나만 사용한다. 그렇기 때문에 하나의 메소드를 여러 곳에서 재사용할 수 있기 때문에 효율적이다.   

[ProtoType 출처](https://www.youtube.com/watch?v=RYxgNZW3wl0)