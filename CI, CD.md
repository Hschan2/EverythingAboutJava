# CI/CD
간단하게 애플리케이션 개발부터 배포까지 자동화를 통해 더욱 효율적이고 빠르게 사용자에게 빈번히 배포할 수 있도록 만들어주는 것

## CI (Continuous Integration, 지속적인 통합)
버그 수정이나 새로운 기능이 <b>Repository</b>에 주기적으로 <b>Build</b>되거나 <b>Merge</b>되는 것을 말한다.   

* 코드 변경사항을 주기적으로 빈번하게 머지해야 한다.
    * 동료 개발자들과 충돌 예방
* 통합을 위한 단계 (빌드, 테스트, 머지)의 자동화
    * 메인 Repository에 머지를 할 때, 자동으로 CI Script에서 Build, Test를 실행 => 성공 시 배포
    * 문제점을 빠르게 발견 가능
    * 버그 수정 용이
    * 개발 생산성 향상
    * 코드 퀄리티 향상

## CD (Continuous Delivery, 지속적인 제공), (Continuous Deployment, 지속적인 배포)
마지막 배포 단계에서 자동화하여 어떻게 배포하는가 결정한다.   

CI에서 자동으로 Build, Test가 완료가 되었다면 배포 준비를 마친다. 여기서 개발자나 혹은 검증하는 직원이 배포해도 되는지 확인한다. 확인 후 배포가 가능이 된다면 수동으로 배포하는 것이 <b>Continuous Delivery</b>라고 한다. (혹은 자동으로 배포하는 것을 <b>Continuous Deployment</b>라고 한다.)   

### CI/CD 파이프라인 (단계)
1. CODE (개발) => 
2. BUILD (CI) => 
3. TEST (CI) => 
4. RELEASE (성공, 실패) (CI) => 
5. DEPLOY (수동, 자동) (CD) 
## CI/CD Tools
* Jenkins Buildkite
* GitHub Actions
* Bitbucket Pipelines
* GitLab CI/CD
* CircleCi

[CI/CD](https://www.youtube.com/watch?v=0Emq5FypiMM)