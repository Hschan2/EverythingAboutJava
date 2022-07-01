# 엘리의 Github Action (깃허브 액션)
특정 이벤트 발생 시, 원하는 일을 자동으로 동작하도록 만들어주는 툴

## Event, Workflows, Jobs, Actions, Runners
* Event (on: push)
    * main branch로 merge 하거나
    * commit을 push 하거나
    * issue를 누군가 열거나 (생성하거나)
* Workflows
    * 이벤트가 발생이 한다면 수행하는 장소
    * 하나 혹은 다수의 Job 존재
    * 자동화하고 싶은 것을 설정
* Job
    * 유닛 테스트를 수행하거나
    * E2E (하나의 기능) 테스트를 수행하거나
    * 순서대로 실행 가능하도록 하거나
    * npm 명령어를 실행하거나
    * Action을 수행하거나
* Action
    * npm을 사용하는 것처럼 만들어진 것을 사용하는 것
    * 재사용이 가능한 Action을 사용하거나
    * check out으로 수행한 것을 확인하거나
    * setup node로 노드 환경을 설치하거나
* Runner
    * VM Machine 혹은 Container
    * 각 Job은 독립적인 Runner에서 실행

## 깃허브 Action
* 파일 생성하기
    * .github/workflows/workflow.yml
    ```
    name: learn-github-actions
    on: [push] // 커밋이 푸시될 때마다
    jobs: // 잡 실행
        check-bats-version: // 어떤 일을 수행하는지
            runs-on: ubuntu-latest // 어떤 VM Machine을 사용할 것인지
            steps:
                - uses: actions/checkout@v3
                - uses: actions/setup-node@v3
                  with:
                    node-version: '14'
                - run: npm install -g bats
                - run: bats -v
    ```

## 예시 코드로 확인하기 [Link](https://youtu.be/iLqGzEkusIw?t=372)

[깃허브 액션](https://www.youtube.com/watch?v=iLqGzEkusIw)