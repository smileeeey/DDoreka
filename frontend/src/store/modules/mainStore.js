import { product } from '../../api/product.js';
import { order } from '../../api/order.js';

const mainStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},

  actions: {
    FIND_DETAIL_PRODUCT({ commit, dispatch, state }, { productId }) {
      console.log('FIND_DETAIL_PRODUCT');
      return product.find(productId);
    },
    FETCH_RECOMMEND_TODAYHOT({ commit, dispatch, state }) {
      console.log('FETCH_RECOMMEND_TODAYHOT 실행');
      return order.fetchRecommendTodayHot();
    },
    FETCH_RECOMMEND_HOTPRODUCT({ commit, dispatch, state }) {
      console.log('FETCH_RECOMMEND_HOTPRODUCT 실행');
      return order.fetchRecommendHotProduct();
    },
  },
};

export default mainStore;
