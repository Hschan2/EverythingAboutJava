# Flyway

## Flyway 정의
<b>Flyway</b>는 "오픈소스 데이터 베이스 마이그레이션 툴"이라는 말로써 자주 사용된다. DB를 마이그레이션 한다는 의미를 자세히 알기 위해 아래와 같은 내용을 알아보기로 한다.

* <b>DB 형상 관리</b>   
소프트웨어 구성 관리 또는 <b>형상 관리는 소프트웨어의 변경사항을 체계적으로 추적하고 통제하는 것</b>으로, 형상 관리는 일반적인 단순 버전관리 기반의 소프트웨어 운용을 조금 더 포괄적인 학술 분야의 형태를 넓히는 근간을 말한다.   

여기에서 "형상 관리는 소프트웨어의 변경사항을 체계적으로 추적하고 통제하는 것"에서 생각할 수 있는 것은 개발을 하면서 자주 사용하게 되는 <b>Git (깃)</b>이다.   

즉, Flyway라는 툴로 DB 변경을 관리하는 것을 말한다. 우리가 Git으로 코드 히스토리를 다루듯이 말이다.   

## Flyway 툴을 사용하는 상황
예시 코드.
```
@Entity
public class Member {
    #Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age; // 새로운 기능 추가로 생긴 새로운 변수

    protected Member() {

    }
}
```
위 처럼 계속해서 개발하다 DB 스키마가 변경되어야 할 때, 보통 아래와 같은 방법을 사용한다.   

* spring.jpa.hibernate.ddl-auto = create or update
    * create: 이전 기록을 날려버려 배포 환경에서 쓰지 못함
    * update: 테이블을 수정하지 않고, 부족한 부분만 추가
* 각 배포 환경(local, dev, prod 등)을 돌아다니며 직접 스키마 변경
    * 실수가 많아지며 사용이 어려움

위의 상황을 Flyway로 해결할 수 있다. 예시를 활용하여 이해한다.
* 아무것도 없는 상황을 가정한다.
    * Flyway 는 Metadata table이라는 것을 하나 생성한다.
    * 버전을 꾸준히 변경하면서 해당 버전 마이그레이션 스크립트에 맞게 DB를 계속 변경한다.
    * 변경을 꼬리를 물면서 하기 때문에 주의가 필요하다.
        * 마이그레이션은 최신 버전보다 높은 경우에만 진행한다.
        * 변경 기록 테이블이나 혹은 마이그레이션 스크립트 파일을 지우거나 변경하면 안 된다.
    * 즉, 테이블을 하나 생성해서 변경 이력을 쌓고 최신보다 높은 버전으로 변경 사항 파일을 작성되면, 그것을 반영하여 DB를 변경한다.   

## 영상을 보며 실습하기 [Link](https://youtu.be/pxDlj5jA9z4?t=352)
* Flyway Default 경로: main/resources/db/migration/
* 경로 변경이 가능
    * 예1. spring.flyway.locations=classpath:db/migration/test
    * 예2. spring.flyway.locations=classpath:/db/migration/{vender}, classpath:db/seed

* 이미 배포가 되어 있는 상황을 가정한다.
    * Flyway Baseline 사용 고려하기
    * [참고 블로그](https://ecsimsw.tistory.com/entry/Flyway%EB%A1%9C-DB-Migration)

## 정리 - Flyway를 사용하는 이유
* 번거로움과 휴먼 에러를 줄일 수 있다.
* 히스토리 관리가 편리하다.
* 환경 분리 전략에 도움이 된다.

[Flyway](https://www.youtube.com/watch?v=pxDlj5jA9z4)