import {request} from './index.js';

const authDomin = "http://localhost:9999/";

export const auth = {
  login(email, password) {
    return request(authDomin ,"post", "login", { email, password });
  },
};