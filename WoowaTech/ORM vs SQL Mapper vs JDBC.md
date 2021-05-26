# ORM vs SQL Mapper vs JDBC

## Persistence?
데이터를 생성한 프로그램이 종료되어도 사라지지 않는 데이터의 특성이다. 즉, 영속성을 의미한다.   

도메인 모델인 객체에 영속성을 부여하는 역할을 한다.   

Persistence Layer를 어떻게 구현하느냐에 관점으로 세 가지를 비교할 수 있다.   

## JDBC (Java DataBase Connectivity)
자바 애플리케이션에서 DBMS의 종류에 상관없이, 하나의 JDBC API를 이용하여 DB 작업을 처리한다. 그리고 각각의 DBMS는 이를 구현한 JDBC 드라이버를 제공한다. 즉, 드라이버만 설치하면 어느 데이터 베이스에서든 접근할 수 있다.   

### JDBC 설정 과정
1. 연걸 파라미터 정의
2. 연결 오픈
3. SQL 문 지정
4. 파라미터 선언과 파라미터 값 제공
5. Statement 준비와 실행
6. 결과를 반복하는 루프 설정
7. 각 이터레이션에 대한 작업 수행
8. 모든 예외 처리
9. 연결, Statement, resultSet 닫기

### JDBC 단점
* 간단한 SQL을 실행하는 데 중복된 코드를 반복적으로 사용
* DB에 따라 일관성 없는 정보를 가진 채로 Checked Exception (SQLException) 처리
* Connection과 같은 공유 자원을 제대로 릴리즈(반환)해주지 않으면 시스템의 자원이 바닥나는 버그

## Persistence Framework
JDBC의 단점을 보완할 수 있는 도구이다. DB와 빠르게 연결할 수있고 안정적인 구동을 보장한다. 내부적으로 JDBC API를 사용하며 SQL Mapper와 ORM으로 나뉜다.   

### SQL Mapper
SQL을 직접 작성하며 SQL 문과 객체(Object)의 필드를 매핑하여 데이터를 객체화한다.   

#### JDBC Template
JDBC Template를 사용하면 기존 JDBC만 사용할 때와 달리 과정이 단순해진다. 개발자는 핵심적인 일만 작성하면 된다.   

##### 설정 과정
1. 연결 파라미터 정의
2. SQL 문 지정
3. 파라미터 선언과 파라미터 값 제공
4. 각 이터레이션에 대한 작업 수행

##### JDBC Template 장점
* 쿼리 수행 결과와 객체의 필드를 맵핑 & RowMapper 재활용
* JDBC에서 반복적으로 해야하는 많은 작업들을 대신 수행

#### SQL Mapper 프레임워크
* MyBatis
반복적인 JDBC 프로그래밍을 단순화하고, SQL 쿼리들을 XML 파일에 작성하여 코드와 SQL을 분리하여 관리한다. 그리고 JDBC만 사용하면 결과를 가져와서 객체의 인스턴스에 매핑하기 위한 많은 코드가 필요하지만, 그 코드를 사용하지 않아도 실행하게 해준다.   

MyBatis는 동적인 쿼리를 지원한다. (동적 쿼리는 상황에 따라 분기 처리를 통해 쿼리를 동작하는 것을 의미)   

##### MyBatis 장점
* 자동으로 Connection 관리를 해주면서 JDBC를 사용할 때의 중복 작업 대부분을 제거
* 복잡한 쿼리나 다이나믹하게 변경되는 쿼리 작성이 단순
* 관심사 분리: DAO로부터 SQL 문을 분리하여 코드의 간결성 및 유지보수성 향상

그러나, JDBC와 SQL Mapper를 동시에 사용한다고 해서 단점이 없는 것이 아니다.   

* 특정 DB에 종속적으로 사용하기 쉽다.
* 테이블마다 비슷한 CRUD SQL = DAO 개발이 매우 반복되는 작업
* 테이블 필드 변경 시, 이와관련된 모든 DAO의 SQL 문, 객체의 필드 등을 수정 필요
=> 코드상으로 SQL과 JDBC API를 분리했다고 하더라도 논리적으로 강한 의존성을 가지고 있다.   

직접 SQL문을 작성하면 결국 SQL 의존적인 설계를 할 수 밖에 없다.   

* SQL 의존적인 개발
1. 테이블 설계
2. 테이블로부터 객체 도출
3. 객체와 테이블 매핑
-- 데이터 베이스 서버가 없는 상태에서 개발 가능 --
4. 데이터베이스 접근 로직 구현
5. 비즈니스 로직 구현
6. 객체 속성 추가 및 테이블 컬럼 추가
-- 데이터 베이스 서버 필요 --
위 단계를 반복하게 된다. 결국 데이터 베이스에 항상 의존 관계를 갖게 된다.   

관계형 데이터 베이스와 객체는 각각 지향이 다르기 때문에 패러다임 불일치 문제가 발생한다.   

* 객체 지향: 추상화, 상속, 다형성   
* 관계형 데이터 베이스: 데이터 중심의 구조   

즉, 각각 지향하는 목적이 다르기 때문에 사용 방법과 표현방식에 차이가 존재한다.   

### ORM (DataBaseData <-> Object)
Persistence Framework의 종류 중 하나이며, 객체와 관계형 데이터 베이스를 맵핑한다. SQL Query가 아닌 직관적인 코드(메소드)로 데이터를 조작한다.   

```
SELECT * FROM USER; -> USER.findAll()
```

ORM은 객체 간의 관계를 바탕으로 SQL 문을 자동으로 생성하고 직관적인 메소드로 데이터를 조작하게 만들어 불편함을 줄인다.   

#### ORM - JPA
패러다임 불일치 문제를 보완하기 위해 JPA를 사용한다. JPA가 해당 문제를 어떻게 해결하는지 알아보기로 한다.   

##### 패러다임 불일치 - 상속
* 관계형 데이터 베이스의 테이블은 객체의 상속이라는 개념이 없다.
```
abstract class Person {
    Long id;
    String name;
}
```
Person은 슈퍼 타입으로 공통 부분을 가지고 있다.
```
class Crew extends Person {
    String nickName;
}
```
Crew는 서브 타입으로 공통으로부터 상속을 받아 다른 엔티티와 차이가 있는 속성을 가진다.   

여기서 Crew 객체를 저장하기 위해서는
```
INSERT INTO person ...
INSERT INTO crew ...
```
처럼 쿼리를 두 번 보내야 한다. 만약에 Crew 객체를 조회하기 위해서는 어떻게 해야 할까?
```
SELECT person.*, crew.* FROM person JOIN crew ON person.id = crew.id;
```
JOIN 문이 삽입된 쿼리를 보내야 한다. 이는 쿼리문이 증가하고 복잡하게 될 것이다.   

이렇게 패러다임 불일치 문제는 JPA를 통해 쉽게 해결할 수 있다.   

```
개발자가 할 일

jpa.persist(album);

Crew crew = jpa.find(Crew.class, crewId);
```

```
JPA가 해주는 일

INSERT INTO person ...
INSERT INTO crew ...

SELECT person.*, crew.* FROM person JOIN crew ON person.id = crew.id;
```

##### 
* 객체는 잠조를 사용해 다른 객체와 연관 관계를 가지고 테이블은 외래키를 사용해 다른 테이블과 연관 관계를 가진다.   

```
class Crew {
    Long id;
    String Nickname;
    Team team;

    ...
}

class Team {
    Long id;
    String name;

    ...
}
```

Crew는 Crew PK와 Team Fk를 가지고 있고, Team은 Team PK를 가지게 된다.   

만약에 아래와 같이 사용할 경우, 테이블에서 저장하거나 조회할 때는 편리함을 느낄 수 있다. 이유는 각각의 객체를 각각의 테이블에 대응하여 처리하면 되기 때문이다.   

```
Team team = crew.getTeam();
```
이는 객체를 테이블 연관 관계에 맞추어 모델링하는 방식으로 객체지향 특징을 버리게 되게 만든다. 이유는 Crew 객체가 Team 객체의 참조를 가지고 있지 않기 때문에 Team을 조회하기 어렵다.   

만약 객체지향을 모델링하게 됐을 때는 CRUD를 하기가 어렵다.   

```
class Crew {
    Long id;
    String Nickname;
    Team team;

    Team getTeam() {
        return team;
    }
}

class Team {
    Long id;
    String name;

    ...
}
```
이를 해결하기 위해, 코드를 변경해주면 된다.

```
class Crew {
    Long id;
    String Nickname;
    Team team;

    ...
}

class Team {
    Long id;
    String name;

    ...
}

JPA가 해주는 일
crew.setTeam(team);
jpa.persist(crew);
// JPA가 team의 참조를 외래키로 변환하여 INSERT 문을 전달하고 조회할 때도 마찬가지로 수행한다.

Crew crew = jpa.find(Crew.class, id);
Team team = crew.getTeam();
```
개발자는 Crew와 Team의 관계를 설정하고 객체를 저장하는 역할만 하면 된다.   

##### 패러다임 불일치 - 객체 그래프 탐색
* 반환한 엔티티를 신뢰하고 사용할 수 없다.   
이는 객체 그래프 탐색 문제이다. 객체에서 Crew가 소속된 Team을 조회할 때, 참조를 사용하여 연관된 팀을 찾으면 되는데 이것을 객체 그래프 탐색이라고 한다.

```
public void process() {
    // 직접 구현한 DAO에서 객체를 가져온 경우
    Crew crew = crewDao.findCrew(crew_id);
    crew.getMission(); => ?
    crew.getTeam().getCoach(); => ?

}
```
여기서 CrewDao의 findCrew가 Mission 객체를 가져온다고 확신할 수 있을까? 그리고 Crew.getTeam()으로 getCoach()까지 가져올 수 있을까?   

확신할 수 없다. 객체 내에서 그패트 탐색을 자유롭게 할 수 있지만 SQL 다룰 때 작성하는 쿼리에 따라 객체 그래프 탐색의 범위가 제한되는 문제가 발생한다. 이 문제 또한 JPA로 해결할 수 있다.

```
public void process() {
    // 직접 구현한 DAO에서 객체를 가져온 경우
    Crew crew = jpa.find(Crew.class, crewId);
    crew.getMission(); // 자유로운 객체 그래프 탐색
    crew.getTeam().getCoach();

}
```
연관된 객체를 신뢰하며 자유로운 탐색을 가능하게 한다.   

##### 패러다임 불일치 - 비교
* DB는 기본 키의 값으로 각 Row를 구분하며, 객체는 동일성 비교와 동등성 비교로 구분한다.   

```
class CrewDao {
    ...

    public Member getCrew(String id) {
        String sql = "SELECT * FROM Crew WHERE = id = ?";

        ...
        JDBC API, SQL 실행
        return new Crew(...);
    }
}

String id = "1";
Crew crew1 = crewDao.getCrew(id);
Crew crew2 = crewDao.getCrew(id);

crew1 == crew2; // X
```
두 개의 crew의 객체를 불러올 때, 이를 비교했을 때는 False의 값이 나온다. 그 이유는 데이터 베이스에서는 같은 데이터이지만 객체의 측면에서 볼 때는 다른 인스턴스이기 때문이다. 하지만 JPA는 같은 트랜잭션 일 때, 같은 객체가 조회되는 것을 보장한다.

```
String id = "1";
Crew crew1 = jpa.find(Crew.class, id); // DB에서 가져오기
Crew crew2 = jpa.find(Crew.class, id); // 1차 캐시에서 가져오기

crew1 == crew2; // O
```
위 처럼 JPA를 사용하면 True의 값을 출력받을 수 있다.

### ORM 장점과 단점
#### 장점
* 패러다임 불일치 문제 해결: 객체지향 언어가 가진 장점 활용 가능
* 생산성: 지루하고 반복적인 CRUD 용 SQL을 개발자가 직접 작성하지 않아도 가능
* 데이터 접근 추상화, 벤더 독립성: 데이터 베이스 벤더마다 살짝 다른 데이터 타입, SQL를 손쉽게 해결
* 유지보수 : 필드 추가, 삭제 시 관련된 CRUD 쿼리를 직접 수정하지 않고, 엔티티를 수정 (간결한 코드)

#### 단점
* 복잡한 쿼리 사용이 어렵다(?): JPA에서는 SQL과 유사한 기술인 JPQL를 지원하며 SQL 자체 쿼리를 작성할 수 있도록 지원한다. 그리고 SQL Mapper와 혼용해서 사용도 가능하다.
