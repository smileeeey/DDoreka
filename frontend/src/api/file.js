import { request } from "./index.js";

const filehDomin = "http://k4d104.p.ssafy.io:8082/file";

export const file = {
  // 이미지 삭제
  delete(fileId) {
    return request(filehDomin, "delete", `/delete/${fileId}`);
  },

  // 이미지 서빙 (n개)
  fetch() {
    return request(filehDomin, "get", `/fileServe`);
  },

  // 이미지 서빙 (1개)
  find(fileId) {
    return request(filehDomin, "get", `/fileServe/${fileId}`);
  },

  // 이미지 등록
  upload() {
    return request(filehDomin, "post", `/upload`);
  },

  // 이미지 한개 등록
  uploadOne() {
    return request(filehDomin, "post", `/upload`);
  },
};
