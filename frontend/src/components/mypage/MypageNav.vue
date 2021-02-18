<template>
  <v-card
    width="100%;"
    flat
    class="card-css"
  >
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title title-padding">
            MY 쇼핑
          </v-list-item-title>
          <v-list-item-subtitle>
            <a 
              @click="gotoOrderList">
              주문목록/배송조회
            </a>
          </v-list-item-subtitle>
          <v-list-item-subtitle>
            <a 
              @click="gotoCRE">
              취소/반품/교환/환불
            </a>
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title title-padding">
            MY 활동
          </v-list-item-title>
          <v-list-item-subtitle>
            <a
              @click="$router.push({ name: 'MyFace' })"
            >
              My유레카
            </a>
          </v-list-item-subtitle>
          <v-list-item-subtitle>
            <a 
              @click="gotoReview">
              구매후기
            </a>
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="title title-padding">
            MY 정보
          </v-list-item-title>
          <v-list-item-subtitle>
            <a
              @click="gotoUserModify"
            >
              개인정보확인/수정
            </a>
          </v-list-item-subtitle>
          <v-list-item-subtitle>
            <a
              @click="gotoAddressBook"
            >
              배송지 관리
            </a>
          </v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

  </v-card>
</template>

<script>
export default {
  name: 'MypageNav',
  methods: {
    gotoOrderList() {
      if (this.$route.path !== "/mypage/order/list") { this.$router.push({ name: 'OrderList' }) }
    },
    gotoCRE() {
      if (this.$route.path !== "/mypage/cancel-return-exchange/list") { this.$router.push({ name: 'CancelReturnExchangeList' }) }
    },
    gotoReview() {
      if (this.$route.path !== "/mypage/product/review") { this.$router.push({ name: 'ProductReview' }) }
    },
    gotoUserModify() {
      if (this.$route.path !== "/mypage/usermodify") { this.$router.push({ name: 'UserModify' }) }
    },
    gotoAddressBook() {
      if (this.$route.path !== "/mypage/addresbook") { this.$router.push({ name: 'AddressBook' })}
    },
  }
}
</script>

<style scoped>
  .card-css {
    background-color: #f0f0f0 !important;
    border-width: 0px 1px 1px !important;
    
    border-color: rgb(225, 228, 230) !important;
  }

  .title-padding {
    padding-top: 1rem;
    padding-bottom: 1rem;
    font-weight: bold;
  }

  a {
    color: black;
    font-size: 1rem;
    text-decoration: none;
  }

  a:hover {
    color: #3D82F7;
  }
</style>