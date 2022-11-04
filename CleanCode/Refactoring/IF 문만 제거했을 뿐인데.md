# IF 문만 제거했을 뿐인데 리팩토링이 된다?

조건문을 사용할 때, 아래와 같이 사용하는 경우가 생각보다 많다.   

```
function executePayment(paymentType) {
    if (paymentType === "KAKAO_PAYMENT") {
        return "카카오 결제 처리";
    } else if (paymentType === "NAVER_PAYMENT") {
        return "네이버 결제 처리";
    } else if (paymentType === "COUPANG_PAYMENT") {
        return "쿠팡 결제 처리";
    } else if (paymentType === "PAYCO_PAYMENT") {
        return "페이코 결제 처리";
    } else if (paymentType === "APPLE_PAYMENT") {
        return "애플 결제 처리";
    }
}
```

위의 코드는 5개의 조건문, If와 Else If문이 사용되었고 5개만 사용했기 때문에 복잡해보이지 않을 수 있지만 더 길어지면 가독성이 떨어지게 된다.   

가독성을 높이고 더 좋은 코드로 만들기 위해 ```Key-Value```를 활용하는 것이 좋다.   

첫 번째로 아래처럼 기본적인 사용 예가 있다.
```
const paymentMap = {
    "KAKAO_PAYMENT": "카카오 결제 처리",
    "NAVER_PAYMENT": "네이버 결제 처리",
    "COUPANG_PAYMENT": "쿠팡 결제 처리",
    "PAYCO_PAYMENT": "페이코 결제 처리",
    "APPLE_PAYMENT": "애플 결제 처리"
}

function executePayment(paymentType) {
    return paymentMap[paymentType];
}

executePayment("KAKAO_PAYMENT"); // "카카오 결제 처리"
```

만약 해당 타입에 따라 요청할 함수가 있을 때, 조건문을 여러 개 사용한다면   
```
function payOnKakao() {...};
function payOnNaver() {...};
function payOnCoupang() {...};
function payOnPayco() {...};
function payOnApple() {...};

function executePayment(paymentType) {
    if (paymentType === "KAKAO_PAYMENT") {
        payOnKakao();
    } else if (paymentType === "NAVER_PAYMENT") {
        sendLog();
        payOnNaver();
    } else if (paymentType === "COUPANG_PAYMENT") {
        sendLog();
        payOnCoupang();
    } else if (paymentType === "PAYCO_PAYMENT") {
        sendLog();
        payOnPayco();
    } else if (paymentType === "APPLE_PAYMENT") {
        sendLog();
        payOnApple();
    }
}

executePayment("KAKAO_PAYMENT");
```

처럼 작성하게 될 것이다. 역시나 보기 어려운 코드가 된다. 전처럼 ```Key-Value```으로 처리할 수 있다.   

```
function payOnKakao() {...};
function payOnNaver() {...};
function payOnCoupang() {...};
function payOnPayco() {...};
function payOnApple() {...};

<!-- 객체에서 함수를 사용할 때, function 생략 가능 -->
const paymentMap = {
    KAKAO_PAYMENT() {
        payOnKakao();
    },
    NAVER_PAYMENT() {
        sendLog();
        payOnNaver();
    },
    COUPANG_PAYMENT() {
        sendLog();
        payOnCoupang();
    },
    PAYCO_PAYMENT() {
        sendLog();
        payOnPayco();
    },
    APPLE_PAYMENT() {
        sendLog();
        payOnApple();
    },
}

function executePayment(paymentType) {
    <!-- 함수를 불러오는 것이기 때문에 []()로 작성 -->
    paymentMap[paymentType]();
}

executePayment("KAKAO_PAYMENT");
```