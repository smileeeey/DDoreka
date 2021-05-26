// actions에 맡게 import 하세요~~~
import { auth, setAuthInHeader } from '../../api/auth.js';
import { user, cart } from '../../api/user.js';
import { seller } from '../../api/seller.js';

const accountStore = {
  namespaced: true,
  state: {
    isLogin: false,
    userData: {
      userId: '',
      name: '',
      email: '',
    },
    wishlist: [],
  },
  getters: {
    get_isLogin(state) {
      return state.isLogin;
    },
  },
  mutations: {
    SET_LOGOUT(state) {
      state.isLogin = false;
      state.userData.userId = '';
      state.userData.name = '';
      state.userData.email = '';
      state.wishlist = [];
      sessionStorage.removeItem('eureka-authorization');
      sessionStorage.removeItem('seller-eureka-authorization');
    },

    SET_LOGIN(state, data) {
      state.isLogin = true;
      state.userData.userId = data.id;
      state.userData.name = data.name;
      state.userData.email = data.email;
    },

    SET_PHONE(state, data) {
      state.userData.phone = data;
    },

    SET_CART(state, data) {
      state.wishlist = data;
    },

    SET_UPDATE(state, data) {
      state.wishlist.push = data;
    },

    SET_DELETE_CART(state, data) {
      let wishlist = state.wishlist;

      for (let i = 0; i < wishlist.length; i++) {
        if (wishlist[i].id === data) {
          wishlist.splice(i, 1);
        }
      }
    },
  },
  actions: {
    async LOGIN({ state, commit, dispatch }, { email, password }) {
      let loginData = await auth.login(email, password);
      //error 처리 해줘라~
      if (loginData.status === undefined) {
        alert('아이디 비밀번호가 틀립니다.');
        return false;
      }

      sessionStorage.setItem('eureka-authorization', loginData.headers['eureka-authorization']);

      setAuthInHeader(loginData.headers['eureka-authorization']);

      loginData = await user.login(email, password);
      console.log('로그인 정보');
      console.log(loginData);
      commit('SET_LOGIN', loginData.data.data);

      dispatch('FETCH_CART', { email });

      return state.isLogin;
    },

    // 판매자 회원 가입
    async SELLER_SIGNUP({ commit }, { name, pw, email, phone, bank_company, bank_account }) {
      await seller.create(name, pw, email, phone, bank_company, bank_account);
      let a = await auth.loginAdd(pw, 'SELLER', email);
      console.log('dd');

      return 0;
    },

    // 유저 회원가입
    async USER_SIGNUP({ commit }, { email, pw, name }) {
      await user.signUp(email, pw, name);
      await auth.loginAdd(pw, 'USER', email);

      return 0;
    },

    // 장바구니 추가
    async UPDATE_CART({ commit, dispatch }, { userEmail, productId, optionId, quantity }) {
      let cartData = await cart.create(userEmail, productId, optionId, quantity);
      if (cartData.data.response == 'error') return false;
      commit('SET_CART', cartData.data.data);

      return true;
    },

    // 휴대폰 번호 변경
    UPDATE_PHONE({ commit }, { email, phone }) {
      user.phoneUpdate(email, phone);
      commit('SET_PHONE', phone);

      return 0;
    },

    // 사용자 정보 변경
    async UPDATE_USER_DATA({}, { email, pw, name, phone }) {
      let userData = await user.update(email, pw, name, phone);
      console.log(userData);
    },

    // 장바구니 가져오기
    async FETCH_CART({ commit }, { email }) {
      let cartData = await cart.fetch(email);
      console.log(cartData);
      commit('SET_CART', cartData.data.data);

      return 0;
    },
  },
};

export default accountStore;
