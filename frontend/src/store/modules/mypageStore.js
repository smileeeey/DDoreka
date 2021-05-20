// actions에 맡게 import 하세요~~~
import { order } from "../../api/order";
import { face } from "../../api/face";

const mypageStore = {
  namespaced: true,
  state: {
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

    payList: [],
    shippingList: [],

    doneList: [],
    reFundList: [],
  },
  getters: {},
  mutations: {
    SET_ORDER_LIST(state, data) {
      state.orderList = data;
    },

    SET_PAY_LIST(state, data) {
      state.payList = data;
    },
    SET_SHIPPING_LIST(state, data) {
      state.shippingList = data;
    },
    SET_DONE_LIST(state, data) {
      state.doneList = data;
    },
    SET_REFUND_LIST(state, data) {
      state.reFundList = data;
    },
  },
  actions: {
    async FETCH_ORDER_LIST({ commit }, { userId }) {
      //categoryId: categoryId, 없다 ㅠㅠ
      let payList = [];
      let shippingList = [];
      let doneList = [];
      let reFundList = [];

      let orderData = await order.fetchOrderList(userId);

      let list = orderData.data.data;
      console.log(list);

      for (let i = 0; i < list.length; i++) {
        if (list[i].status === "PAY") payList.push(list[i]);
        if (list[i].status === "SHIPPING") shippingList.push(list[i]);
        if (list[i].status === "DONE") doneList.push(list[i]);
        if (list[i].status === "REFUND") reFundList.push(list[i]);
      }

      commit("SET_PAY_LIST", payList);
      commit("SET_SHIPPING_LIST", shippingList);
      commit("SET_DONE_LIST", doneList);
      commit("SET_REFUND_LIST", reFundList);
    },

    async FETCH_EMOTION({ commit }, userId) {
      let res = await face.fetchEmotion(userId);
      console.log("겟 이모션");
      console.log(res);
    },
  },
};

export default mypageStore;
