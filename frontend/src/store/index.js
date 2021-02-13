import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    login: false,
    email: '',
    name: '',
    phone: '',
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
  },
  plugins: [
    createPersistedState()
  ]
})
