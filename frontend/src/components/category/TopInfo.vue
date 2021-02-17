<template>
  <div>
    <p class="ml-5 mt-5 text-h6 font-weight-bold">카테고리별 조회<span class="font-weight-light" style="font-size: 75%;">({{ totalElements }}개 상품)</span></p>
    <h4 class="ml-5 mb-1">상품 정렬</h4>
    <v-chip-group class="ml-5">
      <v-chip @click="orderByRating">평점 높은순</v-chip>
      <v-chip @click="orderByDate">최신 등록순</v-chip>
      <v-chip @click="orderByReviewCnt">리뷰 많은순</v-chip>
      <v-chip @click="orderByPriceDescend">가격 높은순</v-chip>
      <v-chip @click="orderByPriceAscend">가격 낮은순</v-chip>
    </v-chip-group>
    <h4 class="mt-10 ml-5">상품 필터</h4>
    <v-container class="ml-3 pb-0">
      <v-row>
        <v-col cols=3>
          <v-text-field
            v-model="minPrice"
            label="최저 가격"
          ></v-text-field>        
        </v-col>
        <v-col cols=3>
          <v-text-field
            v-model="maxPrice"
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
        <v-btn class="primary" @click="filterOn">필터 적용</v-btn>
        <v-btn class="primary ml-8" @click="filterOff">필터 해제</v-btn>
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
    minPrice: 0,
    maxPrice: 0,
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
    },
    filterOn() {
      const filterInfo = {'rating': this.rating, 'minPrice': this.minPrice, 'maxPrice': this.maxPrice}
      this.$emit('filter-on', filterInfo)
    },
    filterOff() {
      this.rating = 0
      this.minPrice = 0
      this.maxPrice = 0
      this.$emit('filter-off')
    }
  },
  // watch: {
  //   lowestPrice: function(newValue) {
  //     const result = newValue.replace(/\D/g, "")
  //       .replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  //     Vue.nextTick(() => this.price = result);
  //   }
  // }
}
</script>

<style>

</style>