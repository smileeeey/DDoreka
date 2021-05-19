import axios from 'axios';
import router from '../router';

export const request = (DOMAIN, method, url, data = {}) => {
  return axios({
    method,
    url: DOMAIN + url,
    data,
  })
    .then((result) => {
      return result;
    })
    .catch((err) => {
      return err;
    });
};
