# Nesting CSS를 사용하는 방법

```
@use postcss-preset-env
```

위의 코드를 추가해서 중첩 CSS(Nesting CSS)를 사용할 수 있다.   

예시 코드를 활용해서 어떻게 사용할 수 있는지 확인하기로 한다.   

```
<body>
    <article id="article">
        <p>This is some dummy text</p>
        <p class="red">
            This is has the class red
            <a href="#">Link</a>
        </p>
    </article>
</body>
```

```
@use postcss-preset-env

* {
    font-size: 2rem;
}

article {
    background-color: red;
}
```

과거에는 article 내부의 p 요소에 스타일을 사용하려면 아래처럼 작성했다.   

```
article {
    background-color: red;
}

article p {
    color: green;
}
```

그러나 이제는 아래처럼 사용할 수 있다.   

```
article {
    background-color: red;

    & p {
        color: green;
    }
}
```

외부에서 사용하는 것이 아니라 내부에서 사용할 수 있는 것이 가능해졌다. 또한 아래처럼 중첩의 중첩이 가능하다.   

```
article {
    background-color: red;

    & p {
        color: green;

        & a {
            color: gray;
        }
    }
}
```

지금까지 기본적인 사용법을 확인해봤다.   

중첩 CSS는 ```@media```도 활용될 수 있다.   

```
article {
    background-color: red;

    @media (min-width: 500px) {
        & {
            background: blue;
        }
    }
}
```

그리고 중첩 CSS의 특징 중 하나는 동시에 적용이 가능하다는 것이다.   

```
<body>
    <article id="article">
        <p>This is some dummy text</p>
        <p class="red">
            This is has the class red
            <a href="#">Link</a>
        </p>
    </article>

    <section>
        <p>This is some dummy text</p>
    </section>
</body>
```

```
#article, section {
    background-color: red;

    & p {
        color: green;
    }
}
```

위 처럼 요소가 다른 위치에 있어도 적용은 똑같이 된다.   

혹은 다른 방법으로 아래처럼 사용할 수도 있다.   

```
#article, section {
    background-color: red;
}

:is(#article, section) p {
    color: green;
}
```

전체 요소 스타일을 지정하고 그 안에 또 다른 전체 요소 스타일을 지정해도 적용이 된다.   

```
p {
    color: red;

    article & {
        color: green;
    }
}
```

```p```요소의 모든 글자 색상을 빨간색으로 설정을 하고 ```p```요소 안에 ```p```요소를 제외한 ```article```의 모든 요소는 초록색으로 지정이 된다.   

만약 ```@nest```를 지정해서 사용할 경우, ```@nest```를 우선적으로 처리한다.   

```
p {
    color: red;

    @nest article & {
        color: green;
    }
}
```

위 처럼 작성할 경우, ```article```의 모든 요소의 글자 색상은 초록색으로 지정이 된다. ```p``` 요소의 스타일이 우선적으로 지정이 되었지만 ```@nest```를 설정하여 이를 결과적으로 지정하게 된다.   

[Nesting CSS](https://www.youtube.com/watch?v=SVScyx4cJi8)