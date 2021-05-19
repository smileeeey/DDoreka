import { product } from '../../api/product.js';
import { order } from '../../api/order.js';
import { file } from '../../api/file.js';

const mainStore = {
  namespaced: true,
  state: {
    todayItemsList: [], //오늘의 상품
    hotItemsList: [], //요즘 뜨는 상품
    steadySellerList: [], //스테디 셀러
    categories: {}, //카테고리별 추천 상품
    categoryKeys: [], //카테고리별 추천 상품
    keywords: {}, //일주일 내 다수 검색어
    item: {},
    sFileIds: [],
    sumnailUrl: '',
    steadySellerProductIds: [],
    hotItemProductIds: [],
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
      state.todayItemsList = [];
      state.todayItemsList = todayItemList;
    },
    SET_HOT_ITEM_LIST(state, hotItemsList) {
      //요즘 뜨는 상품
      console.log('요즘 뜨는 상품 ');
      console.log(hotItemsList);
      state.hotItemsList = [];
      state.hotItemsList = hotItemsList;
    },
    SET_STEADY_SELLER_ITEM_LIST(state, steadySellerList) {
      //스테디 셀러
      state.steadySellerList = [];
      state.steadySellerList = steadySellerList;
    },
    SET_CATEGORY_KEYWORD_ITEM_LIST(state, keywords) {
      //카테고리 검색어
      state.keywords = keywords;
    },
    SET_CATEGORY_RECOMMEND_ITEM_LIST(state, recommendItem) {
      //카데고리 추천 상품
      state.categories = recommendItem;
      state.categoryKeys = Object.keys(state.categories).sort();
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
      console.log('MainInfo');
      console.log(res);
      console.log(res.data['month-hot']);
      commit('SET_TODAY_ITEM_LIST', res.data['day-hot']);
      commit('SET_HOT_ITEM_LIST', res.data['week-hot']);
      commit('SET_STEADY_SELLER_ITEM_LIST', res.data['month-hot']);
      commit('SET_CATEGORY_KEYWORD_ITEM_LIST', res.data['category-keyword']);
      commit('SET_CATEGORY_RECOMMEND_ITEM_LIST', res.data['category-recommend']);
    },
  },
};

export default mainStore;
