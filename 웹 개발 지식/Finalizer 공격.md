# Finalizer 공격

## 예시 코드 (Java)
```
public class Account {
    private String name;

    public Account(String name) {
        this.name = name;
        
        if (this.name.equals("푸틴)) {
            throw new IllegalArgumentException("푸틴은 안 돼!");
        }
    }

    public void transfer(int amount, String to) {
        System.out.printf("Transfer %d from %s to %s.", amount, this.name, to);
    }
}
```

만약 아래처럼 Account를 테스트한다고 보자.

```
class AccountTest {

    @Test
    void NotPutin() {
        Account account = new Account("한국대통령");
        account.transfer(100, "일본총리");
    }
}
```

이는 당연히 실행이 된다. 왜냐하면 Account의 인자 값(name)이 푸틴이 아니기 때문이다. 그러나 푸틴이 된다면?   

```
class AccountTest {

    @Test
    void Putin() {
        Account account = new Account("푸틴");
        account.transfer(100, "한국대통령");
    }
}
```

이는 name이 푸틴이기 때문에 생성자에서 코드가 막힌다. 그러나 이를 뚫는 방법이 존재한다. Account를 건들이지 않아도 뚫을 수 있는 방법이 존재한다는 것이다.   

```
public class BrokenAccount extends Account {
    
    public BrokenAccount(String name) {
        super(name);
    }
}
```

먼저 Account의 name을 생성자를 통해 가져온다. 그리고 ```finalize()```를 Override하여 transfer를 설정하면 된다.   

```
public class BrokenAccount extends Account {
    
    public BrokenAccount(String name) {
        super(name);
    }

    @Override
    protected void finalize() throws Throwable {
        this.transfer(100000, "한국대통령");
    }
}
```

그리고 테스트를 할 때 다음처럼 하면 푸틴으로 이름을 입력해도 이를 뚫고 보낼 수 있다.   

```
@Test
    void Putin() {
        Account account = null;

        try {
            account = new BrokenAccount("푸틴");
        } catch (Exception exception) {
            System.out.println("푸틴은 안 되는 건데?");
        }

        System.gc(); // 쓰레기 값 처리
        Thread.sleep(3000L);
    }
```

이 처럼 작성을 하고 실행을 하면 ```푸틴은 안 되는 건데?```이 출력이 되고 다음에 ```BrokenAccount```에서 설정한 account 값인 100000이 한국대통령에게 보내지는 것을 확인할 수 있다.   

이를 막는 방법은 Account 상속을 막는 방법이 있다. 그리고 Account 클래스에서 finalize()에다가 final을 붙이면 된다.   

```
public class Account {
    private String name;

    public Account(String name) {
        this.name = name;
        
        if (this.name.equals("푸틴)) {
            throw new IllegalArgumentException("푸틴은 안 돼!");
        }
    }

    public void transfer(int amount, String to) {
        System.out.printf("Transfer %d from %s to %s.", amount, this.name, to);
    }

    @Override
    protected final void finalize() throws Throwable {

    }
}
```

Account 클래스에 finalize를 final로 재정의하면 이를 상속할 수 없게 만든다. 즉, finalize를 재정의를 할 수 없게 된다.   

[Finalizer 공격](https://www.youtube.com/watch?v=6kNzL1bl1kI)