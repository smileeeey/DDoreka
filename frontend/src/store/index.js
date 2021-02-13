import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    login: false,
    name: null,
    currentItem: {},
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
    selectItem(state, item) {
      state.currentItem = item
    }
  },
  actions: {
    LOGOUT({ commit }) {
      commit("logout");
      localStorage.removeItem("jwt")
    },
    LOGIN({ commit }) {
      commit("login");
    },
    SELECTITEM({ commit }, item) {
      commit("selectItem", item)
    },
  },
  modules: {
  }
})
