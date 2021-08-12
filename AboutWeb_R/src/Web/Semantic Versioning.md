# Semantic Versioning

## 들어가기 전
일반적으로 <b>npm</b>로 설치를 하면 (```npm install react —save```) package.json에 ^ 혹은 ~가 붙는다.   

## Semantic Versioning (시맨틱 버저닝) 정의
<b>Semantic Versioning</b>은 소프트웨어의 버전 변경 규칙에 대한 제안을 의미한다. (Node JS, npm은 모두 Semantic Versioning을 따른다.)   

* Semantic Versioning의 특징
    * MAJOR Version이 올라가면 MINOR Version과 PATCH Version은 0이 되어야 한다.
    * MINOR Version이 올라가면 PATCH Version이 0이 반드시 되어야 한다.
    * 정식배포전에 pre-release하는 경우에는 -또는 . 을 사용한다.
    * 정식배포전에 git commit후 난수가 붙는 경우 그대로 배포할 경우를 build metadata라고 한다. (예. 16.9.0-alpha.0)
    * MAJOR에 0으로 시작하는 경우(0.y.z)는 초기 개발을 위해서 사용한다.

## Semantic Versioning 종류
* MAJOR: API 호환성이 깨질만한 변경사항
* MINOR: 하위 호환성 지키면서 API 기능이 추가된 것
* PATCH: 하위 호환성 지키는 범위 내에서 버그가 수정된 것

## 범위 지정 - 틸드 범위(~)
* Minor version이 지정되어 있다면 patch level의 변경을 허용
* Minor version이 지정되어 있지 않으면 minor-level의 변경 허용
* ~1.3.2 : minor version이 지정되어 있으므로 patch level 변경을 허용
* ~2: minor version이 지정되어있지 않으므로 minor- level 변경을 허용

## 범위 지정 - 캐럿 범위(^)
* 가장 왼쪽에 0이 아닌 요소를 수정하지 않는 범위로 변경을 허용
* 1.0.0버전이면 minor, patch level 버전 업데이트 허용
* 0.X 버전이면 patch level 업데이트 허용
* 0.X.X버전이면 업데이트 허용하지 않음
* ^1.3.x: minor, patch level 업데이트 허용
* ^0.1.x: 0이 아닌 처음요소가 minor이므로 patch level 업데이트 허용
* ^0.0.3: 0이 아닌 처음요소가 patch이므로 업데이트 허용하지 않음   

추가로 major version이 0으로 시작하는 경우는 아직 1.0.0이 정식으로
릴리즈된게 아니기 때문에 0.1과 0.2 사이에 호환성이 손상되는 변경(breaking-change)이 발생할 수도 있다.

## npm install 시 버전 관리
보통 <b>npm</b>으로 설치를 진행했을 때, 예로 ```npm install rect —save```로 react를 설치한다면 최신 버전으로 세팅이 되면 캐럿(^)이 추가되는 것을 확일할 수 있다.   

예를 들어서   
```
"react": "^16.13.1"
```
일 경우, 16.13.1 버전이 설치가 되어 있으며 minor.patch 버전까지 업데이트가 되어도 된다는 것을 보여준다. 만약 버전을 고정하고 싶다면 ```npm install react --save-exact```로 설치를 진행하면 캐럿(^)이 붙지 않은 채로 설치가 된다.   

### 중간 정리
* 기존 버전과 호환되지 않게 API가 바뀌면 “MAJOR 버전”을 올림
* 기존 버전과 호환되면서 새로운 기능을 추가할 때는 “MINOR 버전”을 올림
* 기존 버전과 호환되면서 버그를 수정한 것이라면 “PATCH 버전”을 올림

## Semantic Versioning 자동화
<b>시맨틱 릴리즈</b>는 커밋 메시지를 일관적으로 작성하여 <b>시맨틱 버전</b>을 자동화 해주는 도구이다.   

### 컨벤셔널 커밋
<b>컨벤셔널 커밋</b>은 사람과 기계 모두 이해할 수 있는 커밋 메세지를 작성하기 위한 규칙이다.   

* 사람이 커밋 메세지를 읽고 중요한 변화를 바로 이해 가능
* 일관화된 커밋 메세지 양식을 통해 Changelog 등을 자동으로 생성   

컨벤셔널 커밋 메세지는 아래와 같은 양식을 사용해 작성한다.
```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```
예를 들어서
```
fix: correct MINOR typos in code

see the issue for details
on typos fixed.

Reviewed-by: Z
Refs #133
```
라고 작성을 했을 때, <b>fix</b> 키워드를 통해 해당 커밋은 버그 수정임을 보인다. <b>type</b>은 프로젝트마다 다를 수 있지만 <b>fix</b>, <b>feat</b>가 가장 중요한 단어다.   

### Patch 업데이트
```
* 8fffafb fix: somethin
* c87775a refactor: something
```
위와 같은 커밋 메세지는 <b>Patch</b> 버전의 변화를 말한다. 새로운 <b>feat</b>가 추가되지 않았기 때문이다.   

### Minor 업데이트
```
* 8fffafb feat: create a new helper function
* c87775a fix: remove a condition
```
위와 같은 커밋 메세지는 <b>Minor</b> 버전의 변화를 말한다. 새로운 <b>feat</b>의 추가를 의미하는 <b>feat</b> 키워드가 사용되었기 때문이다.   

### Major 업데이트
```
feat: allow provided config object to extend other configs

BREAKING CHANGE: `extends` key in config file is now used for extending other config files.
```
위와 같이 <b>BREAKING CHANGE</b> 키워드를 커밋 메세지를 추가하여 <b>Major</b> 버전의 변화를 의미하는 커밋 메세지를 말한다. 일관된 커밋 메세지를 사용함으로써 <b>CHANGELOG</b> 또한 자동으로 생성할 수 있게 된다. <b>Semantic Versioning</b>를 사용하면 앞으로 시맨틱 버전과 함께 <b>CHANGELOG</b> 또한 자동으로 생성된다.

## 실제 사용 방법
* 컨벤셔널 커밋 메시지를 사용
* 이후 릴리즈가 필요할 때 시맨틱 릴리즈 프로그램을 실행
* 혹은 깃헙 액션을 사용하여 자동화
* [Semantic Release, Node JS ver.](https://github.com/semantic-release/semantic-release)

## 요약
컨벤셔널 커밋을 작성하면 <b>Semantic Release</b>를 사용하여 <B>Semantic Versioning</B>규칙에 맞는 릴리즈를 자동화할 수 있다.