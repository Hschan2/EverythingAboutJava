// Controls
const hatcheck = document.querySelector("#hat");
const glassescheck = document.querySelector("#eyeglasses");
const tiecheck = document.querySelector("#tie");

// 모자, 선글라스, 넥타이
const hat = document.querySelector(".hat");
const glasses = document.querySelector(".glasses");
const tie = document.querySelector(".tie");

// 모자 생성
hatcheck.addEventListener("change", hatfun);

function hatfun() {
    if (hatcheck.checked == true) {
        hat.style.bottom = "165px";
    } else {
        hat.style.bottom = "400px";
    }
}

// 선글라스 생성
glassescheck.addEventListener("change", glassesfun);

function glassesfun() {
    if (glassescheck.checked == true) {
        glasses.style.right = "50%";
    } else {
        glasses.style.right = "-50%";
    }
}

// 넥타이 생성
tiecheck.addEventListener("change", tiefun);

function tiefun() {
    if (tiecheck.checked == true) {
        tie.style.bottom = "10px";
    } else {
        tie.style.bottom = "-80px";
    }
}