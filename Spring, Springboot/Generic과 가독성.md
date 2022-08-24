# Generic
범용 콜렉션의 필요성은 다음 코드를 보면 이해할 수 있다.

```
public class ExamList {
    private Exam[] exams;
    private int current;
    private int capacity;
    private int amount;

    public int size() {
        return current + 1;
    }

    public Exam get(int i) {
        return exams[i];
    }

    public void add(Exam exam) {
        current++;
        
        return exams[currents] = exam;
    }
}
```

```
public class StudentList {
    private Student[] students;
    private int current;
    private int capacity;
    private int amount;

    public Student size() {
        return current + 1;
    }

    public Student get(int i) {
        return students[i];
    }

    public void add(Student stu) {
        current++;
        
        return students[currents] = stu;
    }
}
```

```
public class CourseList {
    private Course[] courses;
    private int current;
    private int capacity;
    private int amount;

    public int size() {
        return current + 1;
    }

    public Course get(int i) {
        return courses[i];
    }

    public void add(Course course) {
        current++;
        
        return courses[currents] = course;
    }
}
```

세 개의 클래스를 확인해보면 공통된 부분이 굉장히 많다. 일부 타입이 다를 뿐이다. 줃복되는 코드를 하나로 만들어 재사용할 수 있고 가독성을 높일 수 있기 때문에 범용 콜렉션은 굉장히 중요하다.   

예를 들어서

```
public class [] List {
    private Object [] datas;
    private int current;
    private int capacity;
    private int amount;
}
```

처럼 공통으로 사용할 수 있도록 오브젝트형의 템플릿을 만들어두는 것이 좋다. 그리고 원하는 자료형을 사용하여 이용할 수 있다.   

만약 사용한다면 아래처럼 예시를 들 수 있다.   
```
public class Exam List {
    private Exam[] datas;
    private int current;
    private int capacity;
    private int amount;
}
```

보통 제네릭 방법으로 클래스를 만들 때 아래의 방법을 활용한다.   

```
public class List<T> {
    private Object [] datas;
    private int current;
    private int capacity;
    private int amount;
}
```

그래서 실제 사용의 예를 들으면   

```
public class GLisT<T> {
    private Object[] nums;
    private int current;

    public GLisT() {
        ...
    }

    public void add(Object ...) {
        ...
    }
}

pubic class Program {
    public static void main(String[] args) {
        GList<String> list = new GList<String>();
    }
}
```

타입은 여러 개를 전달할 수 있지만 ```int```는 넘길 수 없다. 왜냐하면 Object로 만든 형식이기 때문에 변환이 되야하기 때문이다. 그래서 ```Integer```로 보내야 한다. 그리고 ```new GList<String>();```에서 String은 제외가 가능하다.   

그리고 GLisT 클래스에서 선언은 ```Object```로 하되 값이 들어오는 곳에는 ```T```를 사용한다.   

```
private Object[] nums;

public GList() {
    nums = new Object[3];
}

public void add(T ...) {
    ...
}

public T get(int index) {
    ...

    // T타입으로 변환되어 반환
    return (T)nums[index];
}
```

# 가독성 좋은 코드로 변경
개발을 할 때, 나도 모르게 하나의 클래스 안에 메소드에서 모든 것을 구현할 때가 있다. 이런 경우에는 가독성이 나빠져 모든 것을 읽어보고 난 후에야 이해하는 경우가 발생할 수 있다. 그래서 쉽게 이해하도록 문장을 줄이는 것이 좋다.   

```
public ... () {
    T variables = ...

    for (T variable : variables) {
        ...
    }

    Text.~(...)
        .~
        .~;
}
```

문장을 줄인다는 것은 구현한 코드를 줄여버린다는 것이 아닌 메소드로 구현해서 쉽게 읽고 이해할 수 있도록 만든다는 것이다. (물론 코드를 줄여서 구현할 수 있다면 그건 더 최고)   

```
public class 예시클래스() {
    T variables = ...
    String Text = ...;
    기능이름1(variables);
    기능이름2(Text);

    ...

    public static void 기능이름1(인자) {
        for (T variable : 인자) {
            ...
        }

        return ...;
    }

    public static void 기능이름2(인자) {
        return Text.~(...)
        .~
        .~;
    }
}
```

위의 코드가 완벽한 코드가 아니다. 단지 예시를 보여주기 위해 작성한 코드다. 이렇게 해당 코드가 무엇인지 알 수 있는 이름으로 메소드를 선언하고 이를 재사용하면 가독성을 높일 수 있고 쉽게 이해할 수 있다.   

처음에 구현했을 때는 반복문과 원하는 데이터를 가져오는 코드가 한 번에 작성되어 있어서 쉽게 읽히고 이해하기 어려웠지만 메소드로 나누면 해당 코드가 무엇인지 빠르게 이해할 수 있고 가독성도 좋아진다. 그러니 메소드를 적극 활용하자.