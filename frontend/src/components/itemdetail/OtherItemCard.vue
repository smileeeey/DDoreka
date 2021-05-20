<template>
  <v-slide-item @click.native="moveItemDetail">
    <v-card class="ma-4" height="200" width="200">
      <template slot="progress">
        <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
      </template>
      <v-img :src="`data:image/jpeg;base64,${detailProductSellerOtherProduct.thumbnail}`" height="150" width="200"></v-img>
      <v-card-text class="pa-1 ml-2">
        <div class="mb-1 subtitle-1 font-weight-bold text-truncate" color="black">
          <!-- 상품이름 -->
          {{ detailProductSellerOtherProduct.name }}
        </div>
        <v-row align="center" justify="start" class="mx-0">
          <!-- 평균평점 -->
          <v-rating :value="detailProductSellerOtherProduct.rating" color="warning" background-color="warning lighten-1" dense half-increments readonly size="16"></v-rating>

          <!-- 평균평점, 리뷰개수 -->
          <div class="ml-1">({{ detailProductSellerOtherProduct.review_cnt }})</div>
        </v-row>
      </v-card-text>
    </v-card>
  </v-slide-item>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  name: 'OtherItemCard',
  props: {
    detailProductSellerOtherProduct: Object,
  },
  methods: {
    ...mapActions('mainStore', ['FETCH_DETAIL_PRODUCT']),
    moveItemDetail: function() {
      // 추후 Item Detail페이지 url을 입력
      this.FETCH_DETAIL_PRODUCT(this.detailProductSellerOtherProduct.productId);
      // console.log(this.$route.path.indexOf('detail'));
      // if (this.$route.path.indexOf('detail') != -1) {
      //   window.location.reload();
      //   return;
      // }
      window.scrollTo(0, 0);
      this.$router.push({ name: 'ItemDetail', params: { id: 1, productid: this.detailProductSellerOtherProduct.productId } });
    },
  },
};
</script>

<style></style>
