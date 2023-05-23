// YouTube 영상을 고정시키는 함수
function fixVideoPosition() {
  const videoElement = document.querySelector("#player-theater-container");

  if (videoElement) {
    videoElement.style.position = "fixed";
    videoElement.style.top = "0";
    videoElement.style.left = "0";
    videoElement.style.zIndex = "9999";
  }
}

// YouTube 영상 고정 클래스를 제거하는 함수
function unfixVideoPosition() {
  const videoElement = document.querySelector("#player-theater-container");

  if (videoElement) {
    videoElement.style.position = "";
    videoElement.style.top = "";
    videoElement.style.left = "";
    videoElement.style.zIndex = "";
  }
}

// 스크롤 이벤트 핸들러
function handleScroll() {
    const scrollY = window.scrollY;
    const threshold = 100; // 맨 위로 올릴 때 고정 클래스를 제거할 스크롤 임계값

    if (scrollY > threshold) {
      fixVideoPosition();
    } else if (scrollY === 0) {
      unfixVideoPosition();
    }
}

// 스크롤 이벤트 리스너 등록
window.addEventListener("scroll", handleScroll);

// 페이지 로드 시 YouTube 영상 고정
window.addEventListener("load", fixVideoPosition);