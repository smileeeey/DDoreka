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
    component: () => import('../views/Home.vue'),
    children: [
      {
        path: '',
        name: 'Main',
        component: () => import('../views/Main.vue')
      },
      {
        path: '/myface',
        name: 'MyFace',
        component: () => import('../views/MyFace.vue')
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
        path: '/category/:id/detail/:productid',
        name: 'ItemDetail',
        component: () => import('../views/ItemDetail.vue'),
        props: true,
      },
      // search
      {
        path: '/search/:category1id/:keyword',
        name: 'Search',
        component: () => import('../views/Search.vue'),
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
            path: 'orderlist',
            name: 'OrderList',
            component: () => import('../views/mypage/OrderList.vue'),
          },
          {
            path: 'createreview/:orderId/:optionId/:productId/:userId',
            name: 'CreateReview',
            component: () => import('../views/mypage/CreateReview.vue'),
            props: true,
          },
          {
            path: 'cancel-return-exchange/list',
            name: 'CancelReturnExchangeList',
            component: () => import('../views/mypage/CancelReturnExchangeList.vue')
          },
          {
            path: 'productreview',
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

  // seller page
  {
    path: '/sell',
    component: () => import('../views/sell/Index.vue'),
    children: [
      {
        name: 'Dashboard',
        path: '',
        component: () => import('../views/sell/Dashboard.vue')
      },
      {
        name: 'SellerProfile',
        path: '/user/profile',
        component: () => import('../views/sell/SellerProfile.vue')
      },
      {
        name: 'SellerProductList',
        path: '/product/list',
        component: () => import('../views/sell/SellerProductList.vue')
      },
      {
        name: 'SellerRegister',
        path: '/product/register',
        component: () => import('../views/sell/SellerRegister.vue')
      },
      {
        name: 'SellerProductDeliver',
        path: '/product/deliver',
        component: () => import('../views/sell/SellerProductDeliver.vue')
      },
      {
        name: 'SellerNotification',
        path: '/notification',
        component: () => import('../views/sell/SellerNotification.vue')
      },
    ]
  },

  // seller accounts (Login & Signup page)
  {
    name: 'SellerLogin',
    path: '/sell/login',
    component: () => import('../views/sell/sellaccounts/SellerLogin.vue')
  },
  {
    name: 'SellerSignup',
    path: '/sell/signup',
    component: () => import('../views/sell/sellaccounts/SellerSignup.vue')
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => {
    if (err.name !== 'NavigationDuplicated') throw err;
  });
};

export default router
