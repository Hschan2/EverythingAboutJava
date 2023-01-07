# A태그 타겟 설정 보안 방지
A태그를 사용할 때 우리는 <b>target="_blank"</b> 설정을 사용하는 경우가 많다. 이는 링크를 클릭했을 때 새로운 브라우저 탭을 연다. 그러나 이를 그냥 사용할 경우 보안상 문제가 발생할 수 있다.   

새로운 탭은 <b>window.opener</b>를 사용하여 연결 페이지에 제한된 접근 권한을 얻는다. 그리고 <b>window.opener.location</b>을 통해서 연결 페이지의 URL을 변경하는 데 사용할 수 있다.   

만약 외부 소스가 신뢰받지 않는 것이라면 문제가 발생할 수 있다.   

예를 들어서, 해킹을 당한다거나 도메인이 오랜 기간을 걸쳐 변경이 되는 등 큰 문제가 발생할 수 있다.   

그래서 우리는 꼭 <b>rel = "noopener noreferrer"</b>를 A 태그에 추가해서 사용해야 한다. 우리가 A태그에서 <b>target = "_blank"</b>를 사용할 때 말이다.   

이전에 A태그를 사용할 때는 아래처럼 사용하였다.
```
<a href="https://externalresource.com/some-page target="_blank">External Resource</a>
```

그러나 이제는 위 처럼 작성하지 말고 아래처럼 사용하는 것이 좋다. 해킹의 위험에서 예방하기 위해서 말이다.
```
<a href="https://externalresource.com/some-page target="_blank" rel="noopener noreferrer">External Resource</a>
```

결과적으로 새로운 브라우저 탭을 열고 싶을 때, 어떤 위험으로부터 A 태그를 예방하기 위해서 <b>rel = "noopener noreferrer"</b>를 꼭 작성하도록 하자.   

이전에 만든 토이 프로젝트에서 A 태그를 위 처럼 바꿀 것이며 앞으로 사용할 때도 위 처럼 작성하여 사용하겠다.