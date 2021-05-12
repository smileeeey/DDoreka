import axios from 'axios';
import router from '../router';

const UNAUTHORIZED = 401;
const onUnauthorized = () => {
  router.push('/login');
};

export const request = (DOMAIN, method, url, data = {}) => {
  console.log(DOMAIN + url);
  console.log(data);
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
      // console.logs(err);
      // const status = err.response.status;
      // if (status === UNAUTHORIZED) return onUnauthorized();
      //  throw Error(result)
    });
};
