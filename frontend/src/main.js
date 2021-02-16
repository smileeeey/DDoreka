import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import vuetify from './plugins/vuetify';

import "./filters"

Vue.config.productionTip = false

new Vue({
  store,
  router,
  vuetify,
  render: h => h(App),
  mounted () {
    const IMP = window.IMP;
    IMP.init("imp06841011")
  }
}).$mount('#app')
