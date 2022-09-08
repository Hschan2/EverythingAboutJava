# What / How 구분

## What과 How로 나눠서 생각하기
* 하려는 것과 의미 그리고 의도(What) - What의 실제 구현(How)   

예시.
```
What               How
가입한지 1년 미만 - user 테이블에서 reg 칼럼 값 기준으로 count

추가 지급 내역 남김 - log 테이블에 insert
```

하려는 것과 구현을 나눠서 생각하는 자세는 양질의 코드로 개발할 수 있다.   

### 코드를 What으로 표현하고 구현하기
```
int addPointRate = 0;
if (userRegisteredLessThanOneYear(userId)) {
    addPointRate = 1;
}

...

if (addPointRate > 0) {
    recordAddPointHistory(userId, addPointRate);
}
```

첫 번째 조건문은 1년 미만 가입자면 실행할 구문이며, 두 번째 조건문은 추가 포인트 비율이 0보다 크면 추가 포인트 비율 내역을 기록하는 구문이다.   

userId가 1년 미만 가입자 사용자라면 추가 포인트 비율을 1로 바꾸고 추가 포인트 비율이 0보다 크면 추가 포인트 내역을 기록한다.   

만약에 What을 생각하지 않고 기능을 구현하는데에만 집중했다면   

```
int count userDao.countUsersByReg(userId);

if (count > 0) {
    pointRate += 1;
}

...

if (count > 0) {
    logDao.insert(new Log(..., "AR", 1, ..)); // 기록
}
```

userId와 Reg 기준으로 count를 하여 count가 0보다 크면 pointRate를 1 증가하고 count가 0보다 크면 타입이 "AR", 값이 1인 로그를 삽입하는 것의 코드이다.   

만약에 기존 진행 과정에서 수정이 생긴다면 위의 코드에 문제가 발생할 수 있다. 그래서 What을 생각하고 작성한 코드처럼 작성하는 것이 효율적이다. 그 이유는 위 코드에서 봤을 때, Log 내 인자가 무엇을 의미하는지 제대로 파악하기 힘들다. 또한, 변수 count가 어떤 횟수를 의미하는지 알 수 없다. 그래서 의도성을 제대로 파악할 수 있도록 작성해야 한다.   

## What과 How의 분리 결과
* 구현을 잠시 잊고 실제 하려는 것이 무엇인지 생각하게 된다.
    * 실제 하려는 것이 코드에 표현될 가능성이 높아진다.
    * 코드의 가독성이 향상된다.
    * 유지 보수성이 좋아진다.
* 구현 제약 등의 이유로 표현력이 떨어질 가능성이 있다.   

### 초보자라면 What과 How를 구분하는 연습을 해야 한다.
* 평소에 의식의 흐름대로 구현에만 신경쓰면 안 된다.
    * 의미와 의도가 드러나는 코드를 작성하도록 노력해야 한다.
    * 경력이 쌓인다고 자연스럽게 늘어나는 것이 아니다.   

[What과 How의 분리](https://www.youtube.com/watch?v=4xg4OeGzGIw)