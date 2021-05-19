import { product } from '../../api/product.js';
import { order } from '../../api/order.js';
import { file } from '../../api/file.js';
import { cos } from '@tensorflow/tfjs';

const mainStore = {
  namespaced: true,
  state: {
    todayItemSumnailUrlList: [], //오늘의 상품 썸네일
    item: {},
    sFileIds: [],
    sumnailUrl: '',
    todayItems: [], //todayItemList
    steadySellerProductIds: [],
    hotItemProductIds: [],
    categories: {},
    categoryKeys: [],
    keywords: {},
  },
  getters: {},
  mutations: {
    SET_HOT_ITEM_PRODUCT_IDS(state, productIds) {
      state.hotItemProductIds = productIds;
    },
    SET_CATEGORIES(state, categories) {
      state.categories = categories;
      state.categoryKeys = Object.keys(state.categories).sort();
    },
    SET_STEADY_SELLER_PRODUCT_IDS(state, productIds) {
      state.steadySellerProductIds = productIds;
    },
    SET_ITEM(state, item) {
      state.sFileIds.length = 0; //강제초기화
      state.item = item;
      state.item.images.forEach((image) => {
        if (image.imageType === 'S') {
          state.sFileIds.push(image.fileId);
        }
      });
    },
    SET_SUMNAILURL(state, sFiles) {
      if (sFiles.length > 0) {
        state.sumnailUrl = `data:image/jpeg;base64,${sFiles[0].imageBytes}`;
      }
    },
    SET_TODAY_ITEMS(state, todayItems) {
      state.todayItems = todayItems;
    },
    SET_KEYWORDS(state, keywords) {
      state.keywords = keywords;
    },
    SET_TODAY_ITEM_LIST(state, todayItemList) {
      //오늘의 상품
      todayItemList.forEach((todayItem) => {
        state.todayItemSumnailUrlList.push(`data:image/jpeg;base64,${todayItem.thumbnail}`);
      });
    },
  },

  actions: {
    async FIND_DETAIL_PRODUCT_AND_FETCH_FILE({ commit, dispatch, state }, productId) {
      let res = await product.find(productId);
      commit('SET_ITEM', res.data.data);
      let sFiles = await file.fetch(state.sFileIds.join(','));
      commit('SET_SUMNAILURL', sFiles.data.data);
      return;
    },

    async FETCH_RECOMMEND_TODAYHOT({ commit, dispatch, state }) {
      //todayItemList
      let res = await order.fetchRecommendTodayHot();
      commit('SET_TODAY_ITEMS', res.data.data);
      return;
    },
    FETCH_RECOMMEND_HOTPRODUCT({ commit, dispatch, state }) {
      let res = order.fetchRecommendHotProduct();
      commit('SET_HOT_ITEM_PRODUCT_IDS', res.data.data);
      return;
    },
    async FETCH_RECOMMENDS_STEADY_SELLER({ commit, dispatch, state }) {
      let res = await order.fetchRecommendSteadySeller();
      commit('SET_STEADY_SELLER_PRODUCT_IDS', res.data.data);
      return;
    },
    async FETCH_RECOMMEND_LATEST_PRODUCT({ commit, dispatch, state }) {
      let res = await product.fetchRecommendLatestproduct();
      commit('SET_CATEGORIES', res.data.data);
      return;
    },
    async FETCH_RECOMMEND_REALTIME_SEARCH_WORD({ commit, dispatch, state }) {
      let res = await product.fetchRecommendRealtimesearchword();
      commit('SET_KEYWORDS', res.data.data);
      return;
    },

    async FETCH_MAIN_INFO({ commit, dispatch, state }) {
      //오늘의 상품
      let res = await product.fetchMainInfo();
      commit('SET_TODAY_ITEM_LIST', res.data['day-hot']);
    },
  },
};

export default mainStore;
