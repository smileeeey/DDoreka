import { request } from "./index.js";

const sellerDomain = "http://k4d104.p.ssafy.io:8088/seller/";

export const seller = {
  // 셀러 등록
  create(name, pw, email, phone, bank_company, bank_account) {
    return request(sellerDomain, "post", `add`, {
      name,
      pw,
      email,
      phone,
      bank_company,
      bank_account,
    });
  },

  //id로 셀러 정보 삭제
  delete(id) {
    return request(sellerDomain, "delete", `deleteById${id}`);
  },

  //모든 셀러를 조회
  fetch() {
    return request(sellerDomain, "get", `getAll`);
  },

  //email로 셀러를 조회
  findByEmail(email) {
    return request(sellerDomain, "get", `getByEmail/${email}`);
  },

  //id로 셀러를 조회
  findById(id) {
    return request(sellerDomain, "get", `getById/${id}`);
  },

  // 셀러 정보 수정
  update(bank_account, bank_company, email, id, name, phone, pw) {
    return request(sellerDomain, "put", `update`, {
      bank_account,
      bank_company,
      email,
      id,
      name,
      phone,
      pw,
    });
  },
};
