## Notice

* package는 **yarn add** 로 install 해주시기 바랍니다. ~~(npm install)~~
* router-view에 보여질 화면은 views에, 화면을 구성하는 Component는 components 폴더에 vue 파일을 생성해 주시기 바랍니다.
* components\core에는 공통으로 사용할 AppBar, Footer 등등을 제작합니다. 이처럼 Component를 제작할 때 components 폴더에 다른 폴더를 생성해 주시면 감사하겠습니다.



## views 폴더 구조

* views
  * accounts
    * Login.vue
    * Signup.vue
  * mypage
    * CancelReturnExchangeList.vue
    * CSCenter.vue
    * OrderList.vue
    * ProductReview.vue
    * UserModify.vue
    * WishList.vue
  * payment
    * Cart.vue
    * Checkout.vue



## Router 주소

> 새로운 vue 페이지를 생성하면 페이지명에 `(filename.vue)` 을 기재해 주시기 바랍니다. (업데이트 필수)
>
> router/index.js 파일을 수정할 일이 생길 경우 반드시 조원과 상의해 해주시기 바랍니다.

#### 구매자 페이지

##### accounts

* 로그인 (Login.vue)

  `/login`

* 회원가입 (Signup.vue)

  `/signup`



##### mypage

*  마이페이지(최초접속 - 주문목록/배송조회) (OrderList.vue)

  `/mypage/order/list`

*  마이페이지 (취소/반품/교환/환불 내역) (CancelReturnExchangeList.vue)

  `/mypage/cancel-return-exchange/list`

* 마이페이지 (할인쿠폰(?))

  `/mypage/discount-coupon`

*  마이페이지 (문의하기/내역확인) -> 고객센터 (CSCenter.vue)

  * mypage가 아닌 다른페이지로 이동할 것이기에 앞에 /mypage가 붙지 않습니다.

  `/cs-center/chat`

*  마이페이지 (구매후기) (ProductReview.vue)

  `/mypage/product/review`

* 마이페이지 (찜리스트) (WishList.vue)

  `/mypage/wishlist`

* 마이페이지 (개인정보확인/수정) (UserModify.vue)

  `/mypage/usermodify`

  

##### payment

* 장바구니 (Cart.vue)

  `/cart` 

*  주문 / 결제 (Checkout.vue)

  `/cart/checkout`

*  결제완료 (example)

  `Undeclared`

  ``https://mc.coupang.com/ssr/desktop/thank-you?orderId=7000091801895&price=21150&retailPrice=21150&payType=ROCKET_BANK&checkoutId=1611839932638&hasCoupangGlobal=false&agentType=WEB&isDirectOrder=false&checkoutType=DEFAULT`



##### Undeclared

* 카테고리별 검색 화면

  `/categories/:categoryname(or code)`

*  제품 상세 화면

  `/products/:productcode`

*  배송지 선택 창

  `Undeclared`

  

___



#### 판매자 페이지

> 추후 작성예정