window.onload = function () {
  // 버튼 가져오기
  const button = document.querySelector(".button");
  const modal = document.getElementById("modal");
  const modalContent = document.querySelector(".modal-content");
  const closeBtn = document.querySelector(".close");
  const colorValue = document.getElementById("colorValue");

  // 색상 선택하기
  const pickColor = async () => {
    const eyeDropper = new EyeDropper();
    const { sRGBHex } = await eyeDropper.open();

    console.log(sRGBHex);
    colorValue.textContent = sRGBHex;
    modal.style.display = "block";
  }

  button.addEventListener("click", pickColor);
  closeBtn.addEventListener("click", () => {
    modal.style.display = "none";
  });
  window.addEventListener("click", (event) => {
    if (event.target === modal) {
      modal.style.display = "none";
    }
  });
}