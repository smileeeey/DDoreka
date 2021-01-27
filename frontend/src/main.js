import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import firebase from 'firebase'

Vue.config.productionTip = false


const firebaseConfig = {
  apiKey: "AIzaSyBDBgo904-PEvryrwBZtD8ua-w0S8Taptk",
  authDomain: "saffy-common-pjt.firebaseapp.com",
  projectId: "saffy-common-pjt",
  storageBucket: "saffy-common-pjt.appspot.com",
  messagingSenderId: "955874931224",
  appId: "1:955874931224:web:a0eda1cb370d26b881896d"
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
