import * as order from '../../api/order.js'

const sellStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},



  actions: {
    FETCH_RECOMMEND_TODAYHOT({ commit, dispatch, state }) {
      console.log("FETCH_RECOMMEND_TODAYHOT 실행") 
      return order.fetchRecommendTodayHot();
  
    },

  //   ADD_CARD({ dispatch, state }, { title, listId, pos }) {
  //     return api.card.create(title, listId, pos)
  //         .then(() => dispatch('FETCH_BOARD', {id:state.board.id}))
  // },


  },
};

export default orderStore;
