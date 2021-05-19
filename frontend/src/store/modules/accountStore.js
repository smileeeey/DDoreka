// actions에 맡게 import 하세요~~~
import { auth, setAuthInHeader } from "../../api/auth.js";
import { user, cart } from "../../api/user.js";
import { seller } from "../../api/seller.js";

const accountStore = {
  namespaced: true,
  state: {
    isLogin: false,
    userData: {
      userId: "",
      name: "",
      email: "",
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
      state.userData.userId = "";
      state.userData.name = "";
      state.userData.email = "";
      state.wishlist = [];
    },

    SET_LOGIN(state, data) {
      state.isLogin = true;
      state.userData.userId = data.id;
      state.userData.name = data.name;
      state.userData.email = data.email;
    },

    SET_CART(state, data) {
      state.wishlist = data;
    },

    SET_UPDATE(state, data) {
      state.wishlist.push = data;
    },
  },
  actions: {
    async LOGIN({ state, commit }, { email, password }) {
      let loginData = await auth.login(email, password);
      console.log(loginData);
      //error 처리 해줘라~
      sessionStorage.setItem("eureka-authorization", loginData.headers["eureka-authorization"]);

      setAuthInHeader(loginData.headers["eureka-authorization"]);

      loginData = await user.login(email, password);

      commit("SET_LOGIN", loginData.data.data);

      let cartData = await cart.fetch(email);

      commit("SET_CART", cartData.data.data);
      return state.isLogin;
    },

    // 판매자 회원 가입
    async SELLER_SIGNUP({ commit }, { name, pw, email, phone, bank_company, bank_account }) {
      await seller.create(name, pw, email, phone, bank_company, bank_account);
      await auth.loginAdd(pw, "SELLER", email);

      return 0;
    },

    // 유저 회원가입
    async USER_SIGNUP({ commit }, { email, pw, name }) {
      await user.signUp(email, pw, name);
      await auth.loginAdd(pw, "USER", email);

      return 0;
    },

    // 장바구니 추가
    async UPDATE_CART({ commit }, { userEmail, productId, optionId, quantity }) {
      let cartData = await cart.create(userEmail, productId, optionId, quantity);
      if (cartData.data.response == "error") return false;

      commit("SET_CART", cartData.data.data);
      return true;
    },
  },
};

export default accountStore;
