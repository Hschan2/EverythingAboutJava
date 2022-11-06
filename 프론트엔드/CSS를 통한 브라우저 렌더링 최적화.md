# CSS를 통한 브라우저 렌더링 최적화
<b>CSS</b>는 HTML, XML으로 작성된 ㅁ누서의 표기 방법을 기술하기 위한 <b>스타일시트</b> 언어이며, 웹 페이지를 꾸미기 위한 코드이다.   

CSS는 요소가 화면, 종이, 음성 또는 다른 매체 상에 <b>어떻게 렌더링</b>되어야 하는지 지정한다.   

## CSS Transform
```
- Parse - Layout(Reflow) - Paint(Repaint) - Composite -
```

Reflow를 방지하는 방법 중 가장 먼저 생각나는 것은 ```Transform: translate(x, y);```일 것이다. 그러나 이를 사용해도 프레임이 끊기는 문제가 발생하게 된다.   

이럴 때, ```Transform: translate3d(x, y, z);```를 활용하는 것이다. 이렇게 활용하면 프레임이 정상적으로 동작하게 되는 것을 볼 수 있다.   

## Transform 3D()가 빠른 이유
브라우저의 Composite 단계는 아래처럼 진행이 된다.
```
- Layout - Paint -> Layer Tree -> 레이어를 이미지로 변환해 GPU로 합성 후 출력
```

레이어는 <b>Paint Layer</b>와 <b>Graphic Layer</b>가 존재하는데 이 때, <b>Graphic Layer</b>에 집중해야 한다.

### Graphic Layer 특징
<b>Graphic Layer</b>는 GPU를 활용해 이미지로 변한되며 병렬 처리에 특화되어 있기 때문에 더 빠르게 변환이 된다.   

기존 레이어에서 새롭게 분리되어 주변 레이어에 영향 없이 해당 레이어만 빠르게 출력할 수 있으며, GPU에서 작업이 이루어져 CPU와 그래픽 작업을 분리할 수 있어 저사양 기기에서의 CPU 부담을 덜어주기도 한다.   

예를 들어, 배경과 인물 레이어가 있을 때, 배경을 따로 그리고 인물 레이어를 따로 분리한다는 것이다.   

Paint Layer에서 Graphic Layer를 분리할 수 있는 조건은 다음과 같다.   

* Transform 3D 관련 값을 사용하는 경우
* Video 태그, 3D, 하드에ㅜ어 가속된 2D Canvas 태그
* Animation, Transition이 적용되어 있고 Opacity, Transform, Filter 속성이 적용되어 있는 경우
* Backdrop-filter
* Will-change
* 플래시와 같은 하드웨어 가속 플러그인

## 모두 GPU를 사용해도 되나?
레이어 정보를 GPU의 비디오 메모리에도 저장하게 되기 때문에 추가적인 메모리 점유가 발생한다. 그리고 CPU에서 GPU 데이터 전달 과정에서 지연 시간이 발생하게 된다. 또한, 일부 연산의 경우 CPU에서의 연산이 더 빠른 경우가 존재할 수 있다.   

그리고 모바일 기준, GPU의 전력 소모량이 1.5 ~ 2배 정도의 추가적인 소모가 발생할 수 있다.

## 중간 정리
* 1. Transform의 3D 요소와 같이, 일부 CSS 속성과 태그에서는 Graphic Layer로 분리
* 2. Graph Layer로 분리되었을 때, 기존 레이어에서 분리되어 별도로 렌더링 진행
* 3. Paint Layer는 CPU의 Raster Thread에서 레이어를 이미지로 변환해 Graphic Layer에서는 GPU에서 레이어를 이미지로 변환
* 4. 레이어가 레스터화 될 때마다, Composite Thread는 GPU를 활용하여 쌓임 맥락을 통해 결정된 순서에 따라 이미지를 합성해 화면에 출력   

```
- Parse - Layout - Paint --- Composite ---
CPU의 Main Thread에서 작동 --- 레이어에 따라 CPU, GPU에서 이미지로 변환 후 완성된 이미지를 GPU에서 순차적으로 Compositing
```

위의 레이어를 활용할 때, 브라우저(크롬)의 개발자 도구에서 레이어를 확인하면 정보를 확인할 수 있다.   

## CSS will-change
CSS의 <b>will-change</b> 속성은 즉각적으로 예상되는 변화의 종류에 관한 힌트를 브라우저에 CSS 속성을 제공한다.   

```
#a {
    will-change: transform;
}
```

will-change 속성 값으로 transform 입력 시, 브라우저는 transform이 변경되는 상황을 미리 준비한다.   

```
*, *::after, *::before {
    will-change: transform;
}
```

위의 코드는 잘못된 사용 예시이다. 변경되지 않을 모든 요소까지 미리 적용하게 될 시, ```Graphic Layer가 계속 유지되고 메모리를 점유```하여 성능 저하가 발생한다.   

```
.component-map-box {
    &:active {
        cursor: pointer;

        img {
            will-change: transform;
        }
    }
}
```

위의 코드는 실제로 활용할 수 있는 코드이다. 마우스 커서가 이동하고 있는 경우라면 적용되도록 만들 수 있다.   

## 정리
* Graphic Layer는 상황을 고려하여 사용
    * 메모리가 적은 저사양 모바일 기기에서도 잘 작동되어야 할 때는 X
    * 레이어의 크기가 너무 클 때는 X
    * 너무 많은 레이어가 생성될 때는 X
    * GPU가 잘 하는 경우(Opacity, Filter, Rotate, Scale, 3D)일 때는 O
    * 레이어 분리를 통해 연산에 이점을 얻을 수 있으면 O
    * 많은 요소의 이동이 발생해 프레임 저하를 예측할 수 있으면 O
* Will-change 속성은 즉각적인 변화가 예측될 때 사용
    * Will-change의 값이 짧은 순간에 너무 자주 바뀐다면 X
    * 확실하지 않은 변경을 미리 준비하는 경우이면 X
    * 한 번에 너무 많은 요소에 적용한다면 X
* CSS Animation과 Transition 속성
    * Will-change보다 CSS를 통한 Animation과 Transition을 우선 시도할 것
    * 애니메이션이 종료된 이후에 Graphic Layer가 그대로 남아있어도 되는지 고민해볼 것
* Reflow 연산 시간을 줄이기 위한 CSS Contain 속성
    * 레이아웃 과정 중 Reflow 시간을 단축시킬 수 있는 CSS Contain 속성, 이는 특정 요소를 문서 트리에서 독립되어 있음을 나타낼 때 연산 과정을 격리 시키는 것이 가능   