<template>
  <div>
    <h3 class="mt-10 mb-3">상품평</h3>
    <v-slide-group class="pa-2" show-arrows>
      <ReviewCard v-for="(review, idx) in detailproductReviewList" :key="idx" :review="review" />
    </v-slide-group>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import ReviewCard from './ReviewCard.vue';
export default {
  name: 'Reviews',
  components: {
    ReviewCard,
  },
  data: () => ({
    nowUserId: 'notLogin',
    productId: 1,
    reviews: [],
  }),
  computed: {
    ...mapState(['userId']),
    ...mapState('mainStore', ['detailproductReviewList']),
  },
  created() {
    this.productId = this.$route.params.productid;
  },
};
</script>

<style></style>
