// actions에 맡게 import 하세요~~~
import { product } from "../../api/product.js";
import { cart } from "../../api/user.js";
import { order } from "../../api/order.js";

const paymentStore = {
  namespaced: true,
  state: {
    cartItems: [],
  },
  getters: {},
  mutations: {
    SET_CART_ITEMS(state, data) {
      state.cartItems = data;
    },
    SET_DELETE_CART_ITEMS(state, data) {
      let cartItems = state.cartItems;
      for (let i = 0; i < cartItems.length; i++) {
        if (cartItems[i].cartId === data) {
          cartItems.splice(i, 1);
        }
      }
    },
  },
  actions: {
    async FETCH_CART({ commit }, { productIds, optionIds, wishlist }) {
      let cartData = await product.fetchCart(productIds, optionIds);
      console.log(cartData.data);

      for (let i = 0; i < cartData.data.length; i++) {
        cartData.data[i].select = true;
        cartData.data[i].amount = wishlist[i].quantity;
        cartData.data[i].cartId = wishlist[i].id;
      }

      commit("SET_CART_ITEMS", cartData.data);
    },

    DELETE_CART_ITEM({ commit }, cartId) {
      cart.delete(cartId);
      commit("SET_DELETE_CART_ITEMS", cartId);
    },

    //주문하기
    ORDER_FROM_CART({}, { orders, shoppings }) {
      order.saveOrder(orders, shoppings);
    },
  },
};

export default paymentStore;
