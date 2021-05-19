import axios from 'axios';
import router from '../router';

const UNAUTHORIZED = 401;
const onUnauthorized = () => {
  router.push('/login');
};

export const request = (DOMAIN, method, url, data = {}, headers={}  ) => {
  console.log(DOMAIN + url);
  console.log(data);
  return axios({
    method,
    url: DOMAIN + url,
    data,
    headers,
  })
    .then((result) => {
      return result;
    })
    .catch((err) => {
      return err;
    });
};
