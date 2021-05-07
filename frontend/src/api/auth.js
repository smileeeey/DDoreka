import {request} from './index.js';

const authDomin = "http://localhost:8088/login";

export const auth = {
  //로그인
  login(password, role, username) {
    return request(authDomin ,"post", "add", { password, role, username });
  },
  update(password, role, username) {
    return request(authDomin ,"put", "update", { password, role, username });
  }
};