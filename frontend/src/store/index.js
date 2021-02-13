import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    login: true,
    email: 'sejinkwon@naver.com',
    name: '권세진',
    phone: '01077269318',
  },
  mutations: {
    logout(state) {
      state.login = false;
      state.name = null;
    },
    login(state, data) {
      state.login = true;
      state.name = data.name;
      state.email = data.email;
      state.phone = data.phone
    },
  },
  actions: {
    LOGOUT({ commit }) {
      commit("logout");
      localStorage.removeItem("jwt")
    },
    LOGIN({ commit }, data) {
      commit("login", data);
      console.log("this is data")
      console.log(data)
    },
  },
  modules: {
  }
})
