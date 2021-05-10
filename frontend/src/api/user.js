import { request } from "./index.js";

const userDomain = "http://k4d104.p.ssafy.io:8085/user";

export const user = {
  // 구매자 삭제 있음 but 사용하지는 않음

  // 로그인
  login(email, pw) {
    return request(userDomain, "post", `/login`, { email, pw });
  },

  // 회원가입
  signUp(email, pw, name) {
    return request(userDomain, "post", `/signup`, { email, pw, name });
  },

  // 구매자 정보 수정
  update(email, pw, name, phone) {
    return request(userDomain, "put", `/update`, { email, pw, name, phone });
  },

  // 구매자 핸드폰번호 수정
};

export const address = {
  // 구매자별 주소 조회
  fetch(id) {
    return request(userDomain, "get", `/address/${id}`);
  },

  // 주소 등록
  create(nickname, mainAddress, subAddress, zipcode, recipientPhone, recipientName, deliveryMsg) {
    return request(userDomain, "post", `/address/${id}`, {
      nickname,
      mainAddress,
      subAddress,
      zipcode,
      recipientPhone,
      recipientName,
      deliveryMsg,
    });
  },

  // 주소 수정 없음

  // 주소 삭제
  delete(id, addressId) {
    return request(userDomain, "delete", `/address/${id}/${addressId}`);
  },
};

export const cart = {
  // 장바구니 저장
  create(userEmail, productId, optionId, quantity) {
    return request(userDomain, "post", `/cart`, { userEmail, productId, optionId, quantity });
  },

  // 장바구니 수정
  update(userEmail, productId, optionId, quantity) {
    return request(userDomain, "put", `/cart`, { userEmail, productId, optionId, quantity });
  },

  // 장바구니 비우기
  deleteAll(userEmail) {
    return request(userDomain, "delte", `/cart/all/${userEmail}`);
  },

  // 장바구니 개별 항목 삭제
  delete(cartId) {
    return request(userDomain, "delete", `/cart/${cartId}`);
  },

  // 장바구니 조회
  fetch(userId) {
    return request(userDomain, "get", `/cart/${userId}`);
  },
};
