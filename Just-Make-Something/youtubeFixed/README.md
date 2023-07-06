# 유튜브 영상 고정
유튜브를 시청할 때, 스크롤을 움직이면 상단에 동영상이 고정이 됩니다.   

이 확장 프로그램의 장점은 스크롤을 내려도 영상을 계속해서 시청할 수 있다는 것입니다.   

그러나 단점도 있습니다. 스크롤을 움직여서 동영상의 위치가 상단에 고정이 되면, 영상의 제목, 설명을 확인할 수 없습니다. 영상의 제목과 설명을 확인하기 위해서는 다시 스크롤을 상단으로 이동해야 합니다.   

![동작 확인하기](./YoutubeFixed.PNG)

# Quick News

![banner](https://github.com/Hschan2/ToyProject/blob/master/quick-news/public/title_image.PNG?raw=true)

<br/>

### **퀵뉴스** - 빠르게 볼 수 있는 주제별 주요 뉴스 제공 서비스

<br/>

퀵뉴스는 오늘의 주요 뉴스와 각 주제별 뉴스들을 빠르게 볼 수 있도록 도와줍니다.   

또한, 버튼 하나로 뉴스 데이터의 갯수를 정할 수 있으며, 클릭 하나로 해당 사이트에서 뉴스를 확인할 수 있습니다.   

![quick-news](https://github.com/Hschan2/ToyProject/assets/39434913/8e2bcb21-6328-43d4-bd01-38a1cb4d320b)

<br/>

## 체험
[Quick-News](https://quick-news-tau.vercel.app/)
* 무료 API 사용으로 배포 시 데이터 확인이 불가능합니다.   

## 퀵뉴스 개발자

### 프론트엔드
| [홍성찬](https://github.com/Hschan2) |.
| :---: |
| ![](https://avatars.githubusercontent.com/u/39434913?v=4) |
| 퀵뉴스 전체 개발 <br/> 전체 디자인 |

<br/>
<br/>

## 기술 스택

#### 프레임워크
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![React](https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![NextJS](https://img.shields.io/badge/Next.js-000000?style=flat-square&logo=Next.js&logoColor=white)

#### 라이브러리
![React Router](https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=react-router&logoColor=white)
![Recoil](https://img.shields.io/badge/recoil-000000?style=flat-square&logo=recoil&logoColor=white)
![Styled-Components](https://img.shields.io/badge/styled components-DB7093?style=flat-square&logo=styled-components&logoColor=white)
![Eslint](https://img.shields.io/badge/Eslint-4B0082?style=flat-square&logo=Eslint&logoColor=white)
![Prettier](https://img.shields.io/badge/prettier-FF69B4?style=flat-square&logo=prettier&logoColor=white)

#### 통신
![Axios](https://img.shields.io/badge/axios-%23323330.svg?style=for-the-badge)

#### 개발 도구
![VSCode](https://img.shields.io/badge/VSCODE-007ACC?style=for-the-badge&logo=visual studio code&logoColor=white)
![NPM](https://img.shields.io/badge/NPM-CB3837?style=for-the-badge)
![Webpack](https://img.shields.io/badge/Webpack-8DD6F9?style=for-the-badge)

#### 배포
![Vercel](https://img.shields.io/badge/Vercel-000000?style=flat-square&logo=Vercel&logoColor=white)

<br/>
<br/>

## 폴더 구조
```
src
├─ constants
├─ interfaces
│
├─ pages
│  ├─ api
│  ├─ components
│  ├─ _app.tsx
│  ├─ _document.tsx
│
├─ public
├─ styles
│
├─ types
├─ App.tsx
└─ index.tsx
```

<br/>
<br/>

## 프로젝트 공통 색상
* #4D7653

<br/>
<br/>

## 페이지 및 주요 기능

#### 공통
* 상단 이동 버튼 구현
* 오늘 날짜 출력(Date)
* 내 위치 기반 현재 날씨 출력(openWeatherMap API)
* 페이지 상단에 스크롤 위치 출력

#### 메인 페이지
* 네이버 뉴스 검색 API에서 가져온 데이터로 "오늘의 주요 뉴스" 목록 출력

#### 각 주제별 뉴스 페이지
* News API에서 가져온 데이터로 각 주제별 뉴스 목록 출력
* 뉴스 데이터 갯수 20~40개 설정 버튼 구현

## 기술 선택
#### Next JS를 선택한 이유
* 최근 프론트엔드 개발자를 준비한다면, 학습할 필요가 있다고 느꼈기 때문에 학습 목적으로 선택했습니다.
* React에서 설치해야 사용가능한 기능들을 바로 사용할 수 있어서 선택하였습니다.
* CSR, SSR, SSG, ISR 등 클라이언트 사이드 렌더링 또는 서버 사이드 렌더링 중 프로젝트에 맞게 선택하여 코드 구현이 가능하기 때문에 선택하였습니다.

#### CSR을 사용한 이유
* Recoil 라이브러리를 사용할 때, 주로 클라이언트 측에서 상태를 관리하기 때문에 CSR을 선택하였습니다.
* 특별하게 SEO(검색엔진 최적화)가 필요하지 않는 서비스라고 생각하였기 때문입니다.
    * 뉴스 모음 사이트이기 때문에 API 호출로 가져온 데이터를 검색엔진을 통해 사이트에 접속하는 것을 의도하지 않습니다.

#### Recoil을 사용한 이유
* 뉴스 데이터 갯수를 뉴스 컴포넌트에서 공통적으로 사용해야 했습니다.
* 해당 변수를 모든 컴포넌트에 직접 전달하는 것으로 구현하기에는 코드가 복잡해졌습니다.
* 위의 문제를 해결하기 위해 상태 관리 라이브러리를 찾아보았고, Redux보다 편리하게 사용할 수 있다고 판단하여 Recoil을 사용하였습니다.
    * Redux보다 편리하게 사용할 수 있다고 판단한 이유는 Store, Dispatch, Reduce, Action을 모두 구현할 필요가 없었기 때문입니다.

#### Naver Search API와 News API를 선택한 이유
* "오늘의 주요 뉴스"와 같은 특정 데이터를 가져오고 싶었지만, 다른 API를 찾지 못했고, 네이버 검색 API를 활용해 "오늘의 주요 뉴스" 쿼리를 전달하여 해당 데이터를 가져오는 것을 선택하였습니다.
* 다음, 네이버에서 크롤링하여 각 카테고리별 뉴스들을 가져오려고 시도하였으나, 목록을 제대로 가져오지 못하거나 한글이 깨지는 등 여러 문제들이 발생하여 무료 News API를 사용하였습니다.

## 이 프로젝트를 개발한 이유
오늘 봐야할 주요 뉴스들을 빠르게 확인하거나, 다양한 카테고리 별로 주요한 뉴스들을 빠르게 보고 싶어 이 프로젝트를 개발하였습니다.   

## 프로젝트 주요 타겟
* 출퇴근, 통학에 주요 뉴스를 포함한 다양한 뉴스들을 빠르게 읽고 싶은 사람

## 실행
```
npm install
npm run dev
```