<template>
  <div>
    <p class="ml-5 mt-5 text-h6 font-weight-bold">카테고리별 조회<span class="font-weight-light" style="font-size: 75%;">({{ totalElements }}개 상품)</span></p>
    <v-chip-group class="ml-5">
      <v-chip @click="orderByRating">평점 높은순</v-chip>
      <v-chip @click="orderByDate">최신 등록순</v-chip>
      <v-chip @click="orderByReviewCnt">리뷰 많은순</v-chip>
      <v-chip @click="orderByPriceDescend">가격 높은순</v-chip>
      <v-chip @click="orderByPriceAscend">가격 낮은순</v-chip>
    </v-chip-group>
    <v-container class="ml-3 pb-0">
      <v-row>
        <v-col cols=3>
          <v-text-field
            v-model="lowestPrice"
            label="최저 가격"
          ></v-text-field>        
        </v-col>
        <v-col cols=3>
          <v-text-field
            v-model="bestPrice"
            label="최고 가격"
          ></v-text-field>        
        </v-col>
      </v-row>
    </v-container>
    <v-container class="pt-0 mt-0">
      <v-row align="center">
        <v-rating
          class="ml-3 pr-5"
          background-color="warning lighten-1"
          color="warning"
          empty-icon="mdi-star-outline"
          full-icon="mdi-star"
          half-icon="mdi-star-half-full"
          half-increments
          hover
          length="5"
          size="40"
          v-model="rating"
        ></v-rating>
        <v-btn class="primary ml-16">필터 적용</v-btn>
    </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'TopInfo',
  props: {
    totalElements: Number,
  },
  data: () => ({
    rating: 0,
    lowestPrice: 0,
    bestPrice: 0,
  }),
  methods: {
    orderByRating() {
      this.$emit('order-by-rating')
    },
    orderByDate() {
      this.$emit('order-by-date')
    },
    orderByReviewCnt() {
      this.$emit('order-by-review-cnt')
    },
    orderByPriceDescend() {
      this.$emit('order-by-price-descend')
    },
    orderByPriceAscend() {
      this.$emit('order-by-price-ascend')
    }
  },
}
</script>

<style>

</style>