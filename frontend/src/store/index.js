import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    login: false,
    userId: null,
    wishlist: [],
    name: null,
    currentItem: {},
    email: '',
    phone: '',
    seller: {},
    sellerstore: {},
    searchData: [],
    category1Name: {
      '001': '패션의류/잡화',
      '002': '뷰티',
      '003': '출산/유아동',
      '004': '식품',
      '005': '주방용품',
      '006': '생활용품',
      '007': '홈인테리어',
      '008': '가전디지털',
      '009': '스포츠/레저',
      '010': '자동차용품',
      '011': '도서/음반/DVD',
      '012': '완구/취미',
      '013': '문구/오피스',
      '014': '반려동물용품',
      '015': '헬스/건강식품'
    }
  },
  mutations: {
    logout(state) {
      state.login = false;
      state.userId = null;
      state.wishlist = [];
      state.name = null;
      state.email = null;
      state.phone = null;

    },
    login(state, data) {
      state.login = true;
      state.userId = data.id
      state.name = data.name;
      state.email = data.email;
      state.phone = data.phone
    },
    selectItem(state, item) {
      state.currentItem = item
    },
    setwishlist(state, data) {
      state.wishlist = data
    },
    deletewishlist(state, idx) {
      state.wishlist.splice(idx, 1)
    },
    setsellerinfo(state, data) {
      state.seller = data
    },
    setsellerstore(state, data) {
      state.sellerstore = data
    },
    searchData(state, data) {
      state.searchData = data
    },
    paywishlist(state, data) {
      for (let i=0; i<state.wishlist.length; i++) {
        if (state.wishlist[i].id == data) {
          state.wishlist.splice(i, 1);
          break;
        }
      }
    },
    changephone(state, data) {
      state.phone = data
    },
  },
  actions: {
    LOGOUT({ commit }) {
      commit("logout");
      localStorage.removeItem("eureka-authorization")
    },
    LOGIN({ commit }, data) {
      commit("login", data);

    },
    SELECTITEM({ commit }, item) {
      commit("selectItem", item)
    },
    SETWISHLIST({ commit }, data) {
      commit("setwishlist", data)
    },
    DELETEWISHLIST({ commit }, idx) {
      commit("deletewishlist", idx)
    },
    SETSELLERINFO({ commit }, data) {
      commit("setsellerinfo", data)
    },
    SETSELLERSTORE({ commit }, data) {
      commit("setsellerstore", data)
    },
    SEARCHDATA({ commit }, data) {
      commit("searchData", data)
    },
    PAYWISHLIST({ commit }, data) {
      commit("paywishlist", data)
    },
    CHANGEPHONE({ commit }, data) {
      commit("changephone", data)
    },
  },
  modules: {
  },
  plugins: [
    createPersistedState()
  ]
})
