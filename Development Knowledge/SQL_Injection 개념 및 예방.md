# SQL Injection 개념과 예방
우리가 서비스를 개발할 때, 일반적으로 로그인 기능을 구현하게 된다.   
그리고 로그인을 하기 위해서 데이터 베이스에 저장된 유저의 데이터를 비교해서 로그인 가능 유무를 확인할 수 있는데, 이 때, ```SQL Injection```인 특정 방법으로 해킹할 수 있는 문제가 발생할 수 있다.   

그렇다면, SQL Injection이 이루어질 수 있는 상황은 어떤 것들이 있을까?   

## SQL Injection 상황
[OWASP Juice Shop](juice-shop.herokuapp.com/#/login)

위의 사이트에 접속하게 되면, SQL Injection 상황을 체험해볼 수 있다.   

사이트에 들어가서, 이메일과 비밀번호를 입력할 때, 일반적인 방법으로 입력하게 되면 일반적인 유효성 검사 메시지가 나타난다.   

이 때, 우리가 확인해봐야 할 것이 바로 ```'```, ```"```, ```')```, ```")```를 입력해보는 것이다. 이 4개 중 하나를 이메일 맨 뒤에 입력했을 때 만약 기존과 다른 메시지가 나타난다면 이는 SQL Injection에 걸려든 것이다.   

보통 데이터 베이스에 SQL 문으로 아이디가 일치하는 작업이 실행될 것이다. 이 때, 아이디에 ```'```를 넣게 되면 이는 SQL 문의 일부이기 때문에 문법 에러가 나타나게 되는 것이다. 그래서 출력되는 메시지가 달라지는 것이다.   

이런 경우가 발생하낟면, 이제는 강제 로그인이 가능해지고 테이블 전체 출력이 가능해진다. 또한, 데이터베이스 삭제도 가능해진다.   

### 강제 로그인
강제 로그인은 어떻게 이루어지는 것일까?   

만약, 아이디에 실제 존재하는 값을 입력하고 나서 ```' --```를 추가하면 어떤 비밀번호를 입력해도 로그인이 가능해진다.   

그 이유는 ```--```는 주석처리를 의미하며, 이 뒤에 나오는 조건문으로 구현된 비밀번호 부분이 주석처리가 되는 것이다. 그래서 강제 로그인이 가능해지는 것이다.   

### ADMIN 권한
ADMIN 계정도 강제로 로그인이 가능할까? 가능하다.   

```email' OR 1=1 --```를 입력하게 되면 ```'```의 이전에 값인 email을 만족하거나, ```1=1```을 만족하면 그와 관련된 모든 행을 출력해달라는 내용이다. 여기서 ```1=1```은 항상 True이기 때문에 신기하게 모든 행이 출력이 된다. 그래서 서버는 그 중 맨 위에 있는 계정인 TEST 또는 ADMIN 계정인 경우가 많기 때문에 ADMIN 권한을 가진 계정을 해킹할 수 있게 되는 것이다.   

### 테이블 출력
유니온 셀렉트 문법을 사용하게 되면 굉장히 쉽게 테이블 출력이 가능해진다.   

예를 들어, URL에 특정 값을 가지고 있을 때, 여기에 UNION 문법을 사용하면 허점을 이용해 테이블에 저장되어 있는 다른 데이터를 출력할 수 있게 된다.   

아래 체험해볼 수 있는 URL을 확인해보자.   

```
testphp.vulnweb.com/artists.php?artist=1
```

위에는 일반적인 URL로 첫 번째 아티스트의 데이터를 출력하게 된다. 그러나, 이 URL에 UNION 문법을 사용하게 되면 아래처럼 작성할 수 있는데, 이 때, 첫 번째 아티스트뿐만 아니라 그 외 다른 아티스트의 데이터도 출력할 수 있게 된다.   

```
testphp.vulnweb.com/artists.php?artist=-1 UNION SELECT 1,uname,pass FROM users WHERE uname='test'
```

UNION은 SELECT 문법 2개를 이어서 붙여 출력하라는 명령어이다. 위 처럼 작성한다면 SQL 문은 아래와 처럼 동작할 것이다.   

```
SELECT * FROM ...
WHERE artist=-1 UNION
SELECT 1,uname,pass FROM users
WHERE uname='test'
```

두 번째 SELECT 문은 컬럼 수를 강제로 맞추기 위해 작성되었다. 이렇게 출력된 데이터는 아이디와 비밀번호가 나타나니 쉽게 해킹이 가능해진다.   

## 예방 방법 1 - Parameterized Query

```
pool.query('SELECT * FROM users WHERE id=' + value)
pool.execute('SELECT * FROM users WHERE id=' + value)
```

위 처럼 작성하는 것이 아니라 아래처럼 작성하면 자동으로 입력한 값을 안전하게 바꿔준다.   

```
pool.query('SELECT * FROM users WHERE id=', [value])
pool.execute('SELECT * FROM users WHERE id=', [value])
```

## 예방 방법 2 - Stored Procedure
이 방법은 코드의 수가 길어지기 때문에 원하는 경우에 사용하자. 예시 문법은 아래와 같다.   

```
CREATE PROCEDURE citycount (IN country CHAR(3), out cities INT)
BEGIN
    SELECT * FROM users
    WHERE CountryCode = country;
END
```

## 예방 방법 3 - ORM
* SQL Alchemy
* JPA/Hibernate
* Sequelize
* TypeORM
* Prisma
* Drizzle
* 등

복잡한 SQL 문을 대신 작성해주며, Injection을 알아서 예방해준다.   

다른 방법으로 자주 쓰이는 Injection 용 코드에서 자주 나오는 특수 기호들을 미리 걸러주는 것이 하나의 방법이 될 수 있다.   

[SQL Injection 개념과 예방](https://www.youtube.com/watch?v=FoZ2cucLiDs)