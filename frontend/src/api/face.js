import { request } from './index.js';

const faceDomain = 'http://k4d104.p.ssafy.io:8088/face';

export const face = {
  // 감정 분석 결과 저장
  create(angry, createdAt, disgusted, fearful, happy, id, neutral, product, sad, surprised, time, user) {
    return request(faceDomain, 'post', `/add`, { angry, createdAt, disgusted, fearful, happy, id, neutral, product, sad, surprised, time, user });
  },

  //감정 분석 결과 삭제
  delete(id) {
    return request(faceDomain, 'delete', `/deleteById${id}`);
  },

  //감정분석 결과 조회
  fetch(user) {
    return request(faceDomain, 'get', `/getAllByUser/${user}`);
  },

  fetchEmotion(userId) {
    return request(faceDomain, 'get', `/product/${userId}`);
  },
};
