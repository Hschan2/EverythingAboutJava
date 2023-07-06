// YouTube 영상을 고정시키는 함수
function fixVideoPosition() {
  const videoElement = document.querySelector("#player-wide-container");
  const playerElement = document.querySelector("#player");

  if (videoElement) {
    videoElement.style.position = "fixed";
    videoElement.style.top = "0";
    videoElement.style.left = "0";
    videoElement.style.zIndex = "9999";
  }
  if (playerElement) {
    playerElement.style.position = "fixed";
    playerElement.style.top = "1";
    playerElement.style.left = "1";
    playerElement.style.zIndex = "9999";
  }
}

// YouTube 영상 고정 클래스를 제거하는 함수
function unfixVideoPosition() {
  const videoElement = document.querySelector("#player-wide-container");
  const playerElement = document.querySelector("#player");

  if (videoElement || playerElement) {
    videoElement.style.position = "";
    videoElement.style.top = "";
    videoElement.style.left = "";
    videoElement.style.zIndex = "";
  }
  if (playerElement) {
    playerElement.style.position = "";
    playerElement.style.top = "";
    playerElement.style.left = "";
    playerElement.style.zIndex = "";
  }
}

// 스크롤 이벤트 핸들러
function handleScroll() {
  const videoElement = document.querySelector("#player-wide-container");
  const playerElement = document.querySelector("#player");
  const scrollY = window.scrollY;
  const threshold = 100; // 맨 위로 올릴 때 고정 클래스를 제거할 스크롤 임계값

  if (videoElement) {
    if (scrollY > threshold) {
      fixVideoPosition();
    } else if (scrollY <= threshold) {
      unfixVideoPosition();
    }
  }
  if (playerElement) {
    if (scrollY > threshold) {
      fixVideoPosition();
    } else if (scrollY <= threshold) {
      unfixVideoPosition();
    }
  }
}

// 스크롤 이벤트 리스너 등록
window.addEventListener("scroll", handleScroll);

// 페이지 로드 시 YouTube 영상 고정
window.addEventListener("load", fixVideoPosition);
