function fixYouTubeVideoPosition() {
    const videoElement = document.querySelector('video'); // 유튜브 영상 요소 선택

    if (videoElement) {
        const originalPosition = videoElement.getBoundingClientRect(); // 영상의 현재 위치 정보 저장

        // 스크롤 이벤트 핸들러
        const handleScroll = () => {
            const scrollY = window.scrollY; // 현재 스크롤 위치

            // 영상의 위치를 고정하기 위해 스크롤 위치에 따라 영상의 스타일을 조정
            videoElement.style.position = 'fixed';
            videoElement.style.top = originalPosition.top - scrollY + 'px';
        };

        window.addEventListener('scroll', handleScroll); // 스크롤 이벤트 리스너 등록
    }
}

// 페이지 로드 완료 시 영상 고정 함수 호출
window.addEventListener('load', fixYouTubeVideoPosition);
