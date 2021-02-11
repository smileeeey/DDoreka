import Vue from 'vue'
import VueRouter from 'vue-router'
// import Main from '../views/Main.vue'

Vue.use(VueRouter)

const routes = [
  // accounts
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/accounts/Login.vue')
  },
  {
    path: '/signup',
    name: 'Signup',
    component: () => import('../views/accounts/Signup.vue')
  },

  // home
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    children: [
      {
        path: '/',
        name: 'Main',
        component: () => import('../views/Main.vue')
      },
      // category
      {
        path: '/category/:id/:depth',
        name: 'Category',
        component: () => import('../views/Category.vue'),
        props: true,
      },
      // ItemDetail
      {
        path: '/category/:name/:code',
        name: 'ItemDetail',
        component: () => import('../views/ItemDetail.vue'),
        props: true,
      },
      // mypage
      {
        path: '/mypage',
        name: 'Mypage',
        component: () => import('../views/Mypage.vue'),
        redirect: { name: 'OrderList' },
        children: [
          {
            path: 'order/list',
            name: 'OrderList',
            component: () => import('../views/mypage/OrderList.vue')
          },
          {
            path: 'cancel-return-exchange/list',
            name: 'CancelReturnExchangeList',
            component: () => import('../views/mypage/CancelReturnExchangeList.vue')
          },
          {
            path: 'product/review',
            name: 'ProductReview',
            component: () => import('../views/mypage/ProductReview.vue')
          },
          {
            path: 'wishlist',
            name: 'WishList',
            component: () => import('../views/mypage/WishList.vue')
          },
          {
            path: 'usermodify',
            name: 'UserModify',
            component: () => import('../views/mypage/UserModify.vue')
          },
          {
            path: 'addressbook',
            name: 'AddressBook',
            component: () => import('../views/mypage/AddressBook.vue')
          },

        ]
      },

      {
        path: '/cs-center/chat',
        name: 'CSCenter',
        component: () => import('../views/mypage/CSCenter.vue')
      },


      // payment
      {
        path: '/checkout',
        name: 'Checkout',
        component: () => import('../views/payment/Checkout.vue')
      },
    ]
  },
  // Cart
  {
    path: '/cart',
    name: 'Cart',
    component: () => import('../views/payment/Cart.vue')
  },
  // popup
  {
    path: '/popup/address',
    name: 'AddressPopup',
    component: () => import('../components/address/AddressPopup.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
