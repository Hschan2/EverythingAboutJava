# JPA, 값 콜렉션 주의 사항

![목록 조회](./images/selectRole.png)

실제로 실행을 하게 되면 아래처럼 조회를 시작한다.

```
SELECT r1_0.id, r1_0.name FROM role r1_0 WHERE r1_0.name like ? order by r1_0.id limit ?,?
```

그리고 반복문을 돌 때, 개수만큼 실행하는 실제 데이터 베이스 코드는 다음과 같다.

```
SELECT r1_0.role_id, r1_0.perm FROM role_perm r1_0 WHERE r1_0.role_id=?
```

이는 Role의 개수만큼 실행하는 것을 말한다.   

![목록 조회](./images/selectRole2.png)

위 이미지는 데이터를 조인해서 원하는 값을 가져오는 코드이다. 그리고 페이지 처리를 메모리에 한다. 데이터가 많다면 메모리 부족 에러가 발생할 수 있다.

```
firstResult/maxResults specified with collection fetch; applying in memory!
```

그러나 실제로 코드를 돌려보면 조인한 모든 데이터를 모두 조회를 한다. 아래 데이터 베이스 코드처럼 말이다.

```
SELECT r1_0.id, r1_0.name, r1_0.role_id, r1_0.perm FROM role r1_0 JOIN role_perm p1_0 on r1_0.id=p1_0.role_id WHERE r1_0.name like ? order by r1_0.id
```

## 성능 문제 고려
예를 들어 설문 질문 목록을 보여줄 때 각 질문의 보기 개수를 함께 표시해야 하는가?   

만약 그렇다면 페이징 처리가 필요할 것이다. 여기서 콜렉션 데이터 자체는 필요 없다   

그리고 역할 목록을 표시할 때 가진 권한을 함께 표시할 것인가?   

이도 마찬가지로 페이징 처리가 필요하다. 여기서 각 역할마다 권한 조회 쿼리를 실행하고 싶지 않을 수 있다.   

## 성능 문제를 CQRS로 해결
<b>CQRS</b>은 변경 기능을 위한 모델과 조회 기능을 위한 모델을 분리할 수 있도록 돕는다. 변경 기능은 JPA를 활용하고 조회 기능은 MyBatis, JdbcTemplate, JPA 중 알맞은 기술을 사용하면 된다.   

그리고 모든 기능을 JPA로 구현할 필요는 없다. 특히, 목록과 상세 페이지 같은 조회 기능일 때 말이다.   

## 정리
JPA로 모든 구현이 가능하다. 그러나 이는 고통만 커질 수 있다. 콜렉션과 연관을 다룰 때 특히 더 힘들 수 있다.   

그래서 JPA로 다 구현하겠다는 생각보다는 명령 모델(상태 변경)과 조회 모델을 구분하면서 적용하는 것이 좋은 방법이다.   

[값 콜렉션 주의 사항](https://www.youtube.com/watch?v=yK4Avtxqz-k)