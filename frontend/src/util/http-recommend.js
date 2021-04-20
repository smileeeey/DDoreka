import axios from 'axios';

// axios 객체 생성
export default axios.create({
<<<<<<< HEAD
  baseURL: 'http://localhost:8085/springboot/',
=======
  baseURL: 'http://localhost:8081/springboot/',
>>>>>>> b58d2021f74c55c17b12c042f20ed5ee04e2cb33
  headers: {
    'Content-type': 'application/json',
  },
});
