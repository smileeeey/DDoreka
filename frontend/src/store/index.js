import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    login: false,
    name: null,
  },
  mutations: {
    logout(state) {
      state.login = false;
      state.name = null;
    },
    login(state) {
      state.login = true;
      state.name = '안세익';
    },
  },
  actions: {
    LOGOUT({ commit }) {
      commit("logout");
      localStorage.removeItem("jwt")
    },
    LOGIN({ commit }) {
      commit("login");
    },
  },
  modules: {
  }
})
