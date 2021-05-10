// actions에 맡게 import 하세요~~~
import { auth, setAuthInHeader } from "../../api/auth.js";
import { user, cart } from "../../api/user.js";
import { seller } from "../../api/seller.js";

const accountStore = {
  namespaced: true,
  state: {
    login: false,
  },
  getters: {},
  mutations: {
    SET_LOGOUT(state) {
      state.login = false;
      state.userId = null;
      state.wishlist = [];
      state.name = null;
      state.email = null;
      state.phone = null;
    },
    SET_LOGIN(state, data) {
      state.login = true;
      state.userId = data.id;
      state.name = data.name;
      state.email = data.email;
      state.phone = data.phone;
    },
  },
  actions: {
    async LOGIN({ commit, rootState }, { email, password }) {
      let loginData = await auth.login(email, password);

      localStorage.setItem("eureka-authorization", loginData.headers["eureka-authorization"]);

      setAuthInHeader(loginData.headers["eureka-authorization"]);

      loginData = await user.login(email, password);

      // 추후 삭제~~!!!!
      rootState.login = true;
      rootState.userId = loginData.data.data.id;
      rootState.name = loginData.data.data.name;
      rootState.email = loginData.data.data.email;
      rootState.phone = loginData.data.data.phone;

      commit("SET_LOGIN", loginData.data.data);

      let cartData = await cart.fetch(email);

      //추후 삭제~~~!!!!
      rootState.wishlist = cartData.data.data;

      return;
    },

    // 로그아웃
    LOGOUT() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => {
          if (this.$route.path !== "/") this.$router.replace("/");
          localStorage.removeItem("seller-eureka-authorization");
        })
        .catch(() => {
          console.error("logout error occured!!");
        });
    },

    // 판매자 회원 가입
    async SELLER_SIGNUP({}, { name, pw, email, phone, bank_company, bank_account }) {
      await seller.create(name, pw, email, phone, bank_company, bank_account);
      await auth.loginAdd(pw, "SELLER" , email);

      return;
    },

    // 유저 회원가입
    async USER_SIGNUP({}, { email, pw, name }) {
      await user.create(email, pw, name);
      await auth.loginAdd( pw, "USER", email );

      return;
    },
  },
};

export default accountStore;
