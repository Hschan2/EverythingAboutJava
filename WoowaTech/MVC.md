# MVC
MVC를 쉽게 말하자면, 유지 보수가 편해지는 코드 구성 방식이다.

## MVC 단계로 이해하기
1. 사용자가 구글에 "코딩"을 검색한다.   
2. Controller는 "코딩"에 대한 검색 결과 데이터를 Model에 요청한다.   
3. Controller는 Model한테서 받은 검색 결과 데이터를 View에 전달한다.   
4. View는 사용자가 보는 UI(레이아웃)에 검색 결과 데이터를 넣어서 웹 페이지에 보여준다.   

위에서 볼 수 있듯이, Controller는 Model과 View의 <b>중개자 역할</b>을 하고 있다. Model은 <b>데이터</b>와 관련된 일을 하고 있다. 그리고 View는 사용자한테 <b>보여지는 부분</b>을 담당하고 있다.   

## MVC를 지키면서 코딩하는 방법
1. Model은 Controller와 View에 의존하지 않아야 한다. (Model 내부에 Controller와 View에 관련된 코드가 있으면 안 된다.) 즉, Model 내부에 클래스를 import하여 사용하면 안 된다.   

```
public class Student {
  private String name;
  private int age;

  public Student(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }
}
```

2. View는 Model에만 의존해야 하고, Controller에는 의존하면 안 된다. (View 내부에 Model의 코드만 있을 수 있고, Controller의 코드가 있으면 안 된다.)   

```
public class OutputView {
  public void printProfile(Student student) {
    System.out.println("내 이름은" + student.getName() + "입니다.");
    System.out.println("내 나이는" + student.getAge() + "입니다.");
  }
}
```

3. View가 Model로부터 데이터를 받을 때는, 사용자마다 다르게 보여주어야 하는 데이터에 대해서만 받아야 한다.   

위 내용을 정리하자면, View는 사용자한테 보이는 UI와 모델로부터 받은 데이터가 합쳐져 만들어진 화면이다. 여기서 모든 사용자한테 공통으로 보여지는 부분은 Model로부터 받으면 안 된다. (예. 주문하기, 배경색 등) 이는 View 자체에서 보여져야 하는 부분이다.   

4. Controller는 Model과 View에 의존해도 된다. (Controller 내부에는 Model과 View의 코드가 있을 수 있다.)   

5. View가 Model로부터 데이터를 받을 때는 반드시 Controller에서 받아야 한다.   

```
public class Controller {
  public static void main(String[] args) {
    Student student = new Student("우아", 20);
    OutputView.printProfile(student);
  }
}
```

이는 즉, View가 Model로부터 데이터를 받을 때는 Controller 코드 내에서 코드 안에서만 받아야 한다.   