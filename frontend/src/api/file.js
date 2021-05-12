import { request } from './index.js';

const fileDomin = 'http://k4d104.p.ssafy.io:8082/file';

export const file = {
  // 이미지 삭제
  delete(fileId) {
    return request(fileDomin, 'delete', `/delete/${fileId}`);
  },

  // 이미지 서빙 (n개)
  fetch(fileIds) {
    console.log('fileIds');
    console.log(fileIds);
    return request(fileDomin, 'get', `/fileServe?fileIds=${fileIds}`);
  },

  // 이미지 서빙 (1개)
  find(fileId) {
    return request(fileDomin, 'get', `/fileServe/${fileId}`);
  },

  // 이미지 등록
  upload() {
    return request(fileDomin, 'post', `/upload`);
  },

  // 이미지 한개 등록
  uploadOne() {
    return request(fileDomin, 'post', `/upload`);
  },
};
