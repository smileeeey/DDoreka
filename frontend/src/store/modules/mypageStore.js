// actions에 맡게 import 하세요~~~
import { order } from "../../api/order";

const mypageStore = {
  namespaced: true,
  state: {
    prepareCnt: 0,
    deliveryCnt: 0,
    completeCnt: 0,
    
    orderList: [],
    // orderId: 19,
    // userId: "20",
    // productId: 1,
    // productName: "'빅볼청키 A 뉴욕양키스'",
    // optionId: 2,
    // optionName: "블랙",
    // quantity: "5",
    // price: 345000,
    // datetime: "2021-05-18T07:10:01.513+0000",
    // thumbnail: "iVBORw0KGgoAAAANSUh~~~~",
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

    SET_ORDER_LIST(state, data) {
      state.orderList = data;
    },
  },
  actions: {
    FIND_MYPAGE_CNT({ commit }, userId) {
      console.log(userId);
      order.fetchUserStatus(userId, 0).then((res) => {
        commit("SET_PREPARE_CNT", res.data.data != null ? res.data.data.length : 0);
      });

      order.fetchUserStatus(userId, 1).then((res) => {
        commit("SET_DELIVERY_CNT", res.data.data != null ? res.data.data.length : 0);
      });

      order.fetchUserStatus(userId, 2).then((res) => {
        commit("SET_COMPLETE_CNT", res.data.data != null ? res.data.data.length : 0);
      });
    },

    async FETCH_ORDER_LIST({ commit }, { userId }) {
      //categoryId: categoryId, 없다 ㅠㅠ
      let orderData = await order.fetchOrderList(userId);
      commit("SET_ORDER_LIST", orderData.data.data);
    },
  },
};

export default mypageStore;
