import axios from 'axios';

// axios 객체 생성
export default axios.create({
  baseURL: 'http://k4d104.p.ssafy.io:9999/auth/',
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-type': 'application/json',
  },
});
