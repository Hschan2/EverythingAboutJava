window.onload = function () {
  // 버튼 가져오기
  const button = document.querySelector(".button");
  const modal = document.getElementById("modal");
  const modalContent = document.querySelector(".modal-content");
  const closeBtn = document.querySelector(".close");
  const colorValue = document.getElementById("colorValue");
  const copyButton = document.getElementById("copyButton");

  // 색상 선택하기
  const pickColor = async () => {
    const eyeDropper = new EyeDropper();
    const { sRGBHex } = await eyeDropper.open();

    console.log(sRGBHex);
    colorValue.textContent = sRGBHex;
    modal.style.display = "block";
  }

  // 색상 값 복사하기
  const copyColor = async () => {
    try {
      await navigator.clipboard.writeText(colorValue.textContent);

      copyButton.textContent = "복사 성공";

      setTimeout(() => {
        copyButton.textContent = "복사";
      }, 3000);

    } catch (err) {
      console.error("클립보드 복사 실패:", err);
      alert("클립보드 복사에 실패했습니다.");
    }
  };

  button.addEventListener("click", pickColor);

  closeBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });

  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  });

  copyButton.addEventListener("click", copyColor);
}