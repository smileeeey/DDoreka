<template>
  <div>
    <h3 class="my-5"><span style="color: blue;">카테고리별 추천 상품</span> | <span style="font-size: 70%">카테고리별 인기상품을 한눈에</span></h3>
    <CategoryRecommendDetail v-for="(categoryKey, idx) in categoryKeys" :key="idx" :categoryKey="categoryKey" :items="categories[categoryKey]" :keywords="keywords[categoryKey]" />
    <div class="loadingDiv">
      <spinner :loading="loding"></spinner>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import Spinner from '../Spinner.vue';
import CategoryRecommendDetail from './CategoryRecommendDetail.vue';
export default {
  name: 'CategoryRecommend',
  components: {
    CategoryRecommendDetail,
    Spinner,
  },
  computed: {
    ...mapState('mainStore', ['categories', 'categoryKeys', 'keywords', 'loding']),
  },
  methods: {
    ...mapActions('mainStore', ['FETCH_RECOMMEND_LATEST_PRODUCT']),

    // getCategories() {
    // this.FETCH_RECOMMEND_LATEST_PRODUCT();
    // },
  },
  created() {
    // this.getCategories();
  },
};
</script>

<style></style>
