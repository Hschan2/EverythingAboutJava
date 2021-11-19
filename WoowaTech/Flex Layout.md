# Flex Layout

## Layout
HTML에서의 레이아웃은 문서 위에서 적절한 위치에 배치하는 방법을 의미한다.

레이아웃은 Normal Flow를 따른다.   
* Block: 자신의 부모 요소 너비를 전부 차지
* Inline: 부모의 너비 전부를 차지하지 않고 자신의 내용 영역만큼만의 너비를 차지하며 부모의 최대 너비를 넘지 않는 선에서 나란히 위치   

## Flex
요소들을 행과 열 단위로 정렬하기 위한 <b>1차원 레이아웃</b>이다. 부모 요소에 ```display: flex;```를 넣는다면 Flex 레이아웃이 적용이 된다.   

* Flex Container: Flex 아이템들의 배치나 정렬을 담당하는 역할을 한다. 행 또는 열방향으로 배치하거나 수직, 수평 정렬 등을 할 수 있다.
    * flex-direction
    * flex-wrap
    * justify-content
    * align-items
    * align-content
* Flex Items: 자기가 차지하는 크기 및 자신들 사이의 순서를 변경할 수 있다.
    * flex
    * flex-flow
    * flex-shrink
    * flex-basis
    * order
    * align-self   

## Flex 용어
* Main axis: Flex item들의 주요 배치 방법과 정렬 등의 기준점이 되는 축. Flex의 아이템들은 이를 기준으로 배치 및 정렬
* Cross axis: Flex item들의 부차적인 정렬에 대한 기준

## Flex 속성
* For Flex Container: 컨테이너에 적용하는 Flex 속성. 아이템 배치와 정렬 관련
    * flex-direction: Main-axis의 방향을 결정하여 main-start 기준점을 잡는 속성
        * row(default): main축이 row 방향으로 설정
            * direction: ltl이 기본이며 direction: rtl로 설정할 경우, row로 설정하여도 row-reverse처럼 반대 방향으로 배치
            * writing-mode: vertical-lr로 설정할 경우, row임에도 불구하고 column으로 배치
        * column: main축을 column 방향으로 설정
        * row-reverse: 기존 row와 반대 방향으로 설정
        * column-reverse: 기존 column과 반대 방향으로 설정   
    * flex-wrap: 컨테이너의 영역을 overflow하였을 때, item 배치 설정
        * nowrap(default): 한 줄로 배치
        * wrap: 일정 너비 이상일 경우 다음 줄로 넘어가서 배치
        * wrap-reverse
    * justify-content: Main-axis를 기준으로 item 정렬
        * center: 가운데 정렬 배치
        * space-between: item 사이에 일정 공간을 유지시키고 배치   
    * align-items: Cross-axis를 기준으로 item 정렬
        * stretch(default): 부모 너비를 모두 차지
        * flex-start: cross 축을 기준으로 가운데 정렬 배치
        * flex-end
        * baseline
* For Flex Item: item에 적용하는 Flex 속성. Item 순서, 크기 관련
    * order: Main-axis를 기준으로 flex-item의 시각적 순서를 변경하는 순서
        * 음수: Main-start 쪽 (순서상 앞)
        * 0 (default)
        * 양수: Main-end 쪽 (순서상 뒤)
* 크기 조절: Flex-item의 크기를 조절하는 속성. 기본 크기 또는 늘리거나 줄일 비율을 설정
    * flex-grow: Flex-item이 늘언알 비율을 결정하는 속성. Flex-container의 빈 공간을 비율에 따라 분배하는 방식으로 결정
        * 0(default): 빈 영역을 채우지 않음, Item 크기가 늘어나지 않음
        * 숫자: 비율
        * 컨테이너 영역의 빈 영역의 크기를 구하기 (container 영역 - 각 item의 Flex-basis 크기의 합)
        * item 들의 Flex-grow 합 구하기
        * (빈 영역이 크기) / (Flex-grow 합)을 적절하게 분배
    * flex-shrink: Flex-item이 줄어드는 비율을 결정하는 속성. Flex-item이 컨테이너 영역을 오버플로우했을 때
        * 0: Item 크기가 줄어드지 않음
        * 숫자: 비율, 1(default)
    * flex-basis: Flex-item의 기본 크기 설정
        * auto(default)
        * 값: px, %, rem, em
    * flex: flex-grow, flex-shrink, flex-basis의 축약형 속성
        * 예. flex: 1 1 auto, flex: 2 0 100px 처럼 위의 순서대로 작성하여 사용 가능
        * 예. flex: 1 === flex: 1 1 0, flex: auto === flex: 1 1 auto, flex: none === flex: 0 0 auto
* Gap (여백): Flex-item 사이의 간격을 설정하는 속성 (Grid에서 파생)
    * gap
    * column-gap
    * row-gap

## 그 외 지식
* Flex-basis와 Width중 우선 순위가 높은 것은?
    * Flex-basis: auto => width > flex-basis
    * Flex-basis: auto가 아닌 값(0, px, % 등) => width < flex-basis
    * Min(Max)-width가 존재한다면 우선 순위가 제일 높음
* Flex는 어떤 박스 형태인가?
    * 외부 환경(Container)은 Block 배치를 따르고 내부 영역은 Flex Formatting Context를 따름
    * display: inline-flex를 활용하면 외부 영역 Inline Context를 내부 영역은 Flex Formatting Context를 따름
* Flex는 언제 사용하는 것이 좋은가?
    * 간단한 레이아웃에 사용하는 것이 좋음
    * 복잡한 레이아웃은 Gird를 사용하는 것이 적합
    * Flex의 배치, 정렬, 크기 변경, 순서 변경 등과 같은 특징을 활용하기 좋은 곳에 사용할 것   

[Flex Layout](https://www.youtube.com/watch?v=JQ0jO3B43YQ)