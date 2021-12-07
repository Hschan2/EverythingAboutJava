# 데이터베이스 Lock

## Lock
사전적으로 <b>무엇인가 열리지 않도록 막는 장치</b>라고 정의되어 있다. 데이터 베이스에서의 Lock은 <b>테이블의 레코드 데이터 접근을 막는 것</b>을 말한다.   

만약 Lock이 없다면 원하는 데이터를 얻지 못하는 데이터 무결성과 일관성의 문제가 발생할 수 있다. 그러므로 많은 사용자 환경에서는 트랜잭션의 실행 순서를 제어하는 것. 즉, <b>동시성 제어</b>를 위해 반드시 필요하다.   

## Lock 종류 (InnoDB 기준)
* <b>Exclusive Locks(배타 잠금)</b>: 혼자 볼 수 있도록 잠금
    * Exclusive Lock(X Lock)은 Write에 대한 Lock이다.
    * SELECT ... FOR UPDATE, UPDATE, DELETE 등의 수정 쿼리를 날릴 때 각 Row에 걸리는 Lock이다.
    * X Lock이 걸려있다면 다른 트랜잭션은 S Lock, X Lock 둘 다 걸 수 없다.
```
CREATE TABLE booth (
    id int auto_increment primary key,
    name varchar(255),
    booth_no int ) ENGINE=InnoDB;
```

Lock에 대한 정보를 확인하고 싶을 때는 아래와 같다.
```
SELECT * FROM information_schema.INNODB_LOCKS;
```

* <b>Shared Locks(공유 잠금)</b>
    * S Lock은 여러 개가 동시에 걸릴 수 있다.
    * Shared Lock(S Lock)은 Read에 대한 Lock이다.
    * 일반적인 SELECT 쿼리가 아닌 SELECT ... LOCK IN SHARE MODE 또는 SELECT ... FOR SHARE(8버전)를 사용하여 Read 작업을 수행할 때 사용한다.
    * S LOCK이 걸려있는 Row에 대한 다른 트랜잭션이 S Lock은 걸 수 있으나 X Lock은 걸 수 없다.

Shared Lock을 걸면서 조회하는 방법은 아래처럼 <b>lock in share mode</b>을 명시하면 된다. (MySQL 8버전 부터는 <b>for share</b>를 명시해도 가능하다.)
```
SELECT * FROM booth WHERE booth_no = 0 lock in share mode
```

* <b>Record Lock(레코드 락)</b>
    * InnoDB는 테이블 레코드가 아닌 인덱스 레코드에 걸린다. (위의 Lock도 같음)
    * InnoDB에서 Record Lock은 Row가 아닌 데이터 베이스의 Index Record에 걸리는 Lock
    * 테이블에 Index가 없다면 숨겨져 있는 Clustered Index를 사용하여 Record를 잠근다.   

* <b>Gap Locks(갭 락)</b>: 각 데이터 사이의 공간에 잠금
    * Index Record의 Gap에 걸리는 Lock이다.
    * Gap은 Index Record가 없는 부분을 말한다.
    * 조건에 해당하는 새로운 Row가 추가되는 것을 방지하기 위한 것이다.   

* <b>Next Key Lock(Record Lock + Gap Lock)</b>
* <b>Table Lock</b>   

## 데이터베이스에서 발생하는 Deadlock(교착상태) 예제
데이터 베이스에서 발생하는 교착상태. 즉, Deadlock은 서로의 자원을 원하고 있는 상황을 말한다.   

예시를 들면 1번 공간에 있는 트랜잭션이 3번 공간에 있는 트랜잭션을 원하고 3번 공간에 있는 트랜잭션 또한 1번 공간에 있는 트랜잭션을 원한다. 그러나 각각 공간은 X Lock으로 서로의 공간을 계속 기다리고 있는 상황이 있을 때 <b>Deadlock</b>이 발생한다.   

자세한 Deadlock의 상황을 알기 위해서는 InnoDB에서 ```show engine innodb status``` 쿼리를 사용하면 확인할 수 있다.   

* [예시 상황](https://youtu.be/onBpJRDSZGA?t=743)   

### Deadlock 해결 방식
* MySQL, Oracle
    * Deadlock Detection
    * Lock Wait Timeout
* Deadlock Detection이 활성화가 되었을 경우 Rollback할 작은 트랜잭션을 선택
    * 트랜잭션 크기는 INSERT, UPDATE, DELETE된 행 수에 의해 결정되지만 Oracle은 제외
    * 활성화가 되어 있지 않은 경우 설정된 Lock Wait Timeout으로 해결

## Lock 정리
트랜잭션의 데이터 일관성과 무결성을 유지하고 동시성 제어에 필요한 것이 <b>Lock</b>이다.   

Lock을 알고 있어야 하는 이유는 Lock 때문에 장애가 발생하는 상황이 생기는데 이를 빠르게 대처가 필요하기 때문이다.   

[데이터베이스 Lock](https://www.youtube.com/watch?v=onBpJRDSZGA)