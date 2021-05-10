import { product } from '../../api/product.js';
import { order } from '../../api/order.js';
import { file } from '../../api/file.js';

const mainStore = {
  namespaced: true,
  state: {
    item: {},
    sFileIds: [],
    sumnailUrl: '',
  },
  getters: {},
  mutations: {
    SET_ITEM(item) {
      state.item = item;
      state.item.images.forEach((image) => {
        if (image.imageType === 'S') {
          state.sFileIds.push(image.fileId);
        }
      });
    },
    SET_SUMNAILURL(sFiles) {
      if (sFiles.length > 0) {
        state.sumnailUrl = `data:image/jpeg;base64,${this.sFiles[0].imageBytes}`;
      }
    },
  },

  actions: {
    async FIND_DETAIL_PRODUCT_AND_FETCH_FILE({ commit, dispatch, state }, { productId }) {
      console.log('FIND_DETAIL_PRODUCT');
      let res = await product.find(productId); //여기서 res 받아서
      commit('SET_ITEM', res.data.data);
      let sFiles = await file.fetch(state.sFileIds.join(','));
      commit('SET_SUMNAILURL', sFiles);
      return;
    },

    FETCH_RECOMMEND_TODAYHOT({ commit, dispatch, state }) {
      console.log('FETCH_RECOMMEND_TODAYHOT 실행');
      return order.fetchRecommendTodayHot();
    },
    FETCH_RECOMMEND_HOTPRODUCT({ commit, dispatch, state }) {
      console.log('FETCH_RECOMMEND_HOTPRODUCT 실행');
      return order.fetchRecommendHotProduct();
    },

    FETCH_FILE() {},
    //file
  },
};

export default mainStore;
