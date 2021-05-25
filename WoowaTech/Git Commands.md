# Git Commands

## Branch (브랜치)
소프트웨어를 개발할 때, 개발자들간에 동일한 소스 코드를 함께 공유하고자 할 때 다루게 된다. 특히, 서로 다른 작업을 할 때 동시에 다양한 작업을 할 수 있게 만들어주는 기능이다.   

각자 독립적인 작업 영역을 생성하고 작업 후 원래 버전과 비교해 새로운 버전을 만들어내는 작업을 할 수 있다.   


* 브랜치 생성하기 (터미널 이용)   
```
$ git branch step 1 (브랜치 생성, step 1과 다르게 이름을 사용해도 가능)
$ git switch step 1 (step 1로 이동, git branch -m [브랜치 이름] 도 가능)
$ git commit (커밋)
```

### 브랜치 작업 내역 합치기 (Merge)
```
$ git switch step 1 (step 1로 이동)
$ git merge bugFix (bugFix 브랜치와 합치기, 필요한 부분만 합쳐야 한다)
$ git switch bugFix (bugFix 브랜치를 최신회하기 위해 이동)
$ git merge step 1
```

### 다른 방법으로 깔끔하게 합치기 (Rebase)
```
$ git rebase step 1
$ git switch step 1
$ git rebase butFix (step 1을 가리키는 커밋의 위치만 이동)
```

Merge는 히스토리를 기록하는 특성이 있지만 Rebase는 새롭게 히스토리를 쓴다는 차이가 있다. (한국에서는 이전 기록이 사라지는 Rebase를 선호)   

### 브랜치에서 숨겨진 것 찾기 (Head)
Head는 현재 체크아웃된 커밋을 가르치며 현재 작업중인 커밋이다. Head는 항상 최신 커밋을 가리킨다.
```
$ git switch 커밋1의Hash
$ git switch main (Head가 main 브랜치를 가리킨다)

```

### 특정 지점 기준으로 몇 번째 이전 커밋 가리키기 (상대 참조)
```
$ git switch HEAD^ (전 커밋으로 HEAD가 위치)
$ git switch HEAD^^ (전전 커밋으로 HEAD가 위치)
$ git branch -f step2 step1~2 (step2 브랜치가 다음 커밋을 가리키게 이동)
```

### 작업 되돌리기
기록 완전히 지우는 방법을 추천한다.
```
$ git reset HEAD~1 (최근 커밋 1개 삭제)
$ git revert HEAD (해당 커밋을 그대로 두고 해당 커밋을 다시 생성)
```

### 삭제된 커밋, 브랜치 복구하기
```
$ git reflog (log 확인)
$ reset --hard 해당커밋의Hash (복구)
$ git switch -c step3 커밋3의Hash (삭제 전 가리킨 브랜치 취이로 새로운 브랜치가 가리키게 만들기)
```

### 브랜치를 최신화하지 않고 작업했을 때
* 원격 저장소를 등록하기 (원격 저장소 다루기, clone과 remote 활용)
* 원격 저장소의 브랜치 얻기 (원격 저장소 다루기, fetch와 pull, push 활용)
* 커밋 로그를 마음대로 (체리-픽 = 좋은 것만 골라서 사용한다, cherry-pick 활용)

[자세한 내용 확인](https://www.youtube.com/watch?v=JsRD2AWxxFg)