# 똑똑한 UI와 바보같은 서버

## 지역 매니저 변경 기능
예시. 기능 요구: 새 매니저로 변경, 이전 매니저에서 새 매니저로 바뀐 내역 저장한다.

```
fetch(url, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(
        {
            areaId: aid,
            oldManagerId: mgrId, // 기능 요구 사용 위치
            newManagerId: selectedId
        }
    )
})
```

```
@PostMapping("/area/manager")
public String changeManager(@RequestBody changeData data) {
    areaService.changeManager(data);
    return "updated";
}
```

```
public void changeManager(ChangeData data) {
    areaDao.update(data.getAreaId(), data.getNewManagerId());
    changeHistoryDao.insert(
        data.getAreaId(), data.getOldManagerId(), // 기능 요구 사용 위치
        data.getNewManagerId()
    );
}
```

위의 코드는 OldManagerId에서 잘못된 값이 전달이 되거나 없을 경우 이를 판단할 수 있는 로직이 없기 때문에 이상한 값이 출력될 가능성이 존재하는 문제가 있다.   

예를 들어서 아이템 구매 기능이 있다. 클라이언트가 보낸 금액을 결제 금액으로 사용하고 해당 파라미터를 변조하여 무료로 구매한 사례가 존재한다.   

## 서버는 UI가 전달하는 데이터를 그대로 믿으면 안 된다.
서버는 최대한 직접 데이터를 조회하여 사용해야 한다. 즉, UI가 전달하는 데이터 사용은 최소화하는 것이 좋다. (위 코드를 예로. 지역 아이디, 새 매니저 아이디, 이전 매이저 아이디 => 지역 아이디, 새 매니저 아이디)   

그리고 이전 매니저 아이디는 서버에서 직접 데이터를 조회해서 사용하는 것이 좋다.   

```
public void changeManager(ChangeData data) {
    Area area = areaDao.select(data.getAreaId()); // 지역 아이디
    Long OldManagerId = area.getManagerId(); // 매니저 아이디
    
    area.changeManager(data.getNewManagerId());
    areaDao.update(area);
    changeHistoryDao.insert(area.getAreaId(), oldManagerId, data.getNewManagerId());

    ... area가 없는 경우도 추가해야 한다.
}
```

## 서버의 논리 로직은 서버가 구현해야 한다.
주요 로직은 값 검증과 계산, 논리적인 상태 검증 등이다.   

* 예. 주문을 취소할 수 있는 상태를 검증하거나 필수 값을 검증하는 것   

UI 또는 클라이언트에서 기본적인 값을 검증하고 서버에서 그 외에 많은 것을 검증하고 규칙을 확인해야 한다.

## 정리
서버의 데이터를 올바른 상태로 유지할 책임은 서버에 있다. 모바일 앱이나 브라우저, 클라이언트가 아니다. 즉, 논리적인 데이터를 맞추는 것은 UI에 기대면 안 된다.

[똑똑한 UI, 바보 서버](https://www.youtube.com/watch?v=XJC12913zv4)