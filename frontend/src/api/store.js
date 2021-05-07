import { request } from "./index.js";

const storeDomain = "http://k4d104.p.ssafy.io:8088/store/";

export const store = {

  // 스토어 추가
  create(id, main_address, name, phone, sellerId, sub_address, zipcode) {
    return request(storeDomain, "post", `add`, { id, main_address, name, phone, sellerId, sub_address, zipcode});
  },
  //id로 스토어 삭제
  delete(id) {
    return request(storeDomain, "delete", `deleteById${id}`);
  },
  
   //모든 스토어 조회
   fetch() {
    return request(storeDomain, "get", `getAll`);
  },
   
  //id로 셀러를 조회
  findById(id) {
    return request(storeDomain, "get", `getById/${id}`);
  },

   //이름으로 셀러를 조회
   findByName(name) {
    return request(storeDomain, "get", `getByName/${name}`);
  },
   
   //셀러 아이디로 스토어 조회
   findBySllerId(sellerId) {
    return request(storeDomain, "get", `getBySellerId/${sellerId}`);
  },
   
  // 스토어 수정
  update(id, main_address, name, phone, sellerId, sub_address, zipcode) {
    return request(storeDomain, "put", `update`, { id, main_address, name, phone, sellerId, sub_address, zipcode});
  },
};
