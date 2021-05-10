import {request} from './index.js';
import axios from 'axios';

const authDomin = "http://k4d104.p.ssafy.io:8088/login";

export const auth = {
  // 로그인하는데 필요한 토큰값 가져오기
  login(email, password){
    return request(authDomin, "post", "", {username : email, password});
  },

  // 회원가입
  loginAdd(password, role, username) {
    return request(authDomin ,"post", "/add", { password, role, username });
  },
  update(password, role, username) {
    return request(authDomin ,"put", "/update", { password, role, username });
  }
};

export const setAuthInHeader = (token) => {
  console.log("토큰:" + token);
  axios.defaults.headers.common["eureka-authorization"] = token ? `Bearer ${token}` : null;
};