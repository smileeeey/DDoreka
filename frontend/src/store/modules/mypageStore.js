// actions에 맡게 import 하세요~~~
import { order } from "../../api/order";

const mypageStore = {
  namespaced: true,
  state: {
    prepareCnt: 0,
    deliveryCnt: 0,
    completeCnt: 0,
  },
  getters: {},
  mutations: {
    SET_PREPARE_CNT(state, cnt) {
      state.prepareCnt = cnt;
    },
    SET_DELIVERY_CNT(state, cnt) {
      state.deliveryCnt = cnt;
    },
    SET_COMPLETE_CNT(state, cnt) {
      state.completeCnt = cnt;
    },
  },
  actions: {
    FIND_MYPAGE_CNT({commit}, userId) {
      console.log(userId);
      order.fetchUserStatus(userId, 0).then((res) => {
        commit("SET_PREPARE_CNT", res.data.data.length);
      });

      order.fetchUserStatus(userId, 1).then((res) => {
        commit("SET_DELIVERY_CNT", res.data.data.length);
      });

      order.fetchUserStatus(userId, 2).then((res) => {
        commit("SET_COMPLETE_CNT", res.data.data.length);
      });
    },
  },
};

export default mypageStore;
