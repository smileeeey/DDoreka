<template>
  <v-card
    class="mx-auto"
    height="500"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-card-title class="pb-1">{{ item.name }} </v-card-title>
    <v-card-text>
      <v-container class="pl-0">
        <v-row
          align="center"
          class="mx-0"
        >
          <v-rating
            :value="item.rating"
            color="warning"
            background-color="warning lighten-1"
            dense
            half-increments
            readonly
            size="20"
          ></v-rating>

          <div class="grey--text ml-4">
            ({{ item.reviewCnt }})
          </div>
        </v-row>
      </v-container>

      <v-divider></v-divider>
      <div class="mt-5">{{ discountRate }}%<span class="text-decoration-line-through ml-2">{{ item.options[0].price | comma}}원</span></div>
      <div class="subtitle-1 red--text text--darken-2">{{ item.options[0].discountPrice | comma}}원</div>
      <div class="my-4 subtitle-1">무료배송</div>
      <div>
        <!-- 선택한 옵션이 object로 리턴된다. -->
        <v-select
          v-model="selectOption"
          :items="item.options"
          label="옵션선택"
          item-text="name"
          solo
          return-object
        ></v-select>
        <v-select
          v-model="quantity"
          :items="numbers"
          label="수량"
          solo
        ></v-select>
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title class="pb-0">상품주문</v-card-title>

    <v-container class="ml-2">
      <v-row>
        <v-card-actions>
          <v-btn
            class="mr-3"
            color="blue darken-1"
            @click="addWishList"
          >
            장바구니 담기
          </v-btn>
          <!-- 바로 구매 가능하게 할지 고민 -->
          <v-btn
            color="blue darken-1"
            @click="buyNow"
          >
            바로구매
          </v-btn>
        </v-card-actions>
      </v-row>
    </v-container>
  </v-card>  
</template>

<script>
// import { mapState } from 'vuex'
import axios from 'axios'
export default {
  name: 'TopInfoCard',
  props: {
    item: Object,
  },
  data: () => ({
    selectOption: '',
    price: 0,
    discountPrice: 0,
    numbers: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    quantity: 0,
  }),
  computed: {
    // ...mapState ([
    //  'email'
    //   // 'currentItem'
    // ]),
    discountRate: function() {
      return Math.round(((this.item.options[0].price - this.item.options[0].discountPrice) / this.item.options[0].price) * 100)
    }
  },
  methods: {
    addWishList() {
      console.log('장바구니 추가')
      const userEmail = this.$store.state.email
      const productId = this.item.id
      const optionId = this.selectOption.optionId
      const quantity = this.quantity
      const token = localStorage.getItem('eureka-authorization')
      if (token) {
        axios.post('http://i4d106.p.ssafy.io:8080/user/cart', {
          userEmail: userEmail,
          productId: productId,
          optionId: optionId,
          quantity: quantity,
        }, {
          headers: {
            'eureka-authorization': token,
          }
        })
        .then(res => {
          console.log(res.data.response)
          if (res.data.response == 'success') {
            alert('상품이 장바구니에 추가되었습니다.')
            this.$store.dispatch('SETWISHLIST', res.data.data)
          }
        })
        .catch(err => {
          console.log(err)
        })
      } else {
        alert('장바구니에 담으시려면 로그인하세요')
      }
      
    },
    buyNow() {
      const userEmail = this.$store.state.email
      const productId = this.item.id
      const optionId = this.selectOption.optionId
      const quantity = this.quantity
      const token = localStorage.getItem('eureka-authorization')
      if (token) {
        axios.post('http://i4d106.p.ssafy.io:8080/user/cart', {
          userEmail: userEmail,
          productId: productId,
          optionId: optionId,
          quantity: quantity,
        }, {
          headers: {
            'eureka-authorization': token,
          }
        })
        .then(res => {
          console.log(res.data.response)
          if (res.data.response == 'success') {
            this.$store.dispatch('SETWISHLIST', res.data.data)
            this.$router.push({name: 'Cart'})
          }
        })
        .catch(err => {
          console.log(err)
        })      
      } else {
        alert('구매하시려면 로그인 해주세요')
      }
    }
  },
}
</script>

<style>

</style>