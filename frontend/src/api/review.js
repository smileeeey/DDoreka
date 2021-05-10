import { request } from "./index.js";

const reviewDomain = "http://k4d104.p.ssafy.io:8083/review";

export const review = {
  // 리뷰 삭제
  delete(reviewId) {
    return request(reviewDomain, "delete", `/delete/${reviewId}`);
  },

  // 리뷰 좋아요 취소
  dislike(reviewId, userId) {
    return request(reviewDomain, "delete", `/dislike/${reviewId}/${userId}`);
  },

  // 리뷰 정보 조회
  fetch(productId, userId) {
    return request(reviewDomain, "get", `/get/${productId}/${userId}`);
  },

  // 사용자별 리뷰 정보 조회
  findUser(userId) {
    return request(reviewDomain, "get", `/get/${userId}`);
  },

  // 리뷰 종아요 등록
  like(reviewId, userId, likeox) {
    return request(reviewDomain, "post", `/like`, {reviewId, userId, likeox});
  },

  // 리뷰 등록
  create(reviewObj, image) {
    return request(reviewDomain, "post", `/write`, { reviewObj, image });
  },
};