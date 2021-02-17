<template>
  <div>
    <p class="ml-5 mt-5 text-h6 font-weight-bold">검색결과<span class="font-weight-light" style="font-size: 75%;">({{ totalElements }}개 상품)</span></p>
    <v-chip-group class="ml-5">
      <v-chip @click="orderByRating">평점 높은순</v-chip>
      <v-chip @click="orderByDate">최신 등록순</v-chip>
      <v-chip @click="orderByReviewCnt">리뷰 많은순</v-chip>
      <v-chip @click="orderByPriceDescend">가격 높은순</v-chip>
      <v-chip @click="orderByPriceAscend">가격 낮은순</v-chip>
    </v-chip-group>
  </div>
</template>

<script>
export default {
  name: 'TopInfo',
  props: {
    totalElements: Number,
  },
  data: () => ({
    
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