<template>
  <v-card class="mx-auto" height="500">
    <template slot="progress">
      <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
    </template>

    <v-card-title class="pb-1">{{ item.name }} </v-card-title>
    <v-card-text>
      <v-container class="pl-0">
        <v-row align="center" class="mx-0">
          <v-rating :value="item.rating" color="warning" background-color="warning lighten-1" dense half-increments readonly size="20"></v-rating>

          <div class="grey--text ml-4">({{ item.reviewCnt }})</div>
        </v-row>
      </v-container>

      <v-divider></v-divider>
      <div class="mt-5">
        {{ discountRate }}%<span class="text-decoration-line-through ml-2">{{ item.options[0].price | comma }}원</span>
      </div>
      <div class="subtitle-1 red--text text--darken-2">{{ item.options[0].discountPrice | comma }}원</div>
      <div class="my-4 subtitle-1">무료배송</div>
      <div>
        <!-- 선택한 옵션이 object로 리턴된다. -->
        <v-select v-model="selectOption" :items="item.options" label="옵션선택" item-text="name" solo return-object></v-select>
        <v-select v-model="quantity" :items="numbers" label="수량" solo></v-select>
      </div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title class="pb-0">상품주문</v-card-title>

    <v-container class="ml-2">
      <v-row>
        <v-card-actions>
          <v-btn class="mr-3" color="blue darken-1" @click="addWishList">
            장바구니 담기
          </v-btn>
          <!-- 바로 구매 가능하게 할지 고민 -->
          <v-btn color="blue darken-1" @click="buyNow">
            바로구매
          </v-btn>
        </v-card-actions>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import axios from 'axios';
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
    ...mapState('accountStore', ['userData','isLogin']),
    discountRate: function() {
      // 할인율 계산
      return Math.round(((this.item.options[0].price - this.item.options[0].discountPrice) / this.item.options[0].price) * 100);
    },
  },
  methods: {
    ...mapActions('accountStore',['UPDATE_CART']),
    addWishList() {
      if(this.isLogin == false){
        alert("로그인을 해주세요");
        return;
      }

      this.UPDATE_CART({
      userEmail :this.userData.email, 
      productId : this.item.id, 
      optionId : this.selectOption.optionId, 
      quantity : this.quantity
      })
      .then((res)=>{
        if(res) alert("상품이 장바구니에 추가되었습니다.");
        else alert("이미 있는 상품입니다.");
      });

    },
    buyNow() {
      if(this.isLogin == false) this.$router.push({ name: 'Login' });
      this.addWishList();
      this.$router.push({ name: 'Cart' });
    },
  },
};
</script>

<style></style>
