<template>
  <div>
    <h3 class="my-5"><span style="color: blue;">카테고리별 추천 상품</span> | <span style="font-size: 70%">카테고리별 인기상품을 한눈에</span></h3>
    <CategoryRecommendDetail v-for="(categoryKey, idx) in categoryKeys" :key="idx" :categoryKey="categoryKey" :items="categories[categoryKey]" :keywords="keywords[categoryKey]" />
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import CategoryRecommendDetail from './CategoryRecommendDetail.vue';
export default {
  name: 'CategoryRecommend',
  components: {
    CategoryRecommendDetail,
  },
  computed: {
    ...mapState('mainStore', ['categories', 'categoryKeys']),
  },
  data: () => ({
    categories: {},
    categoryKeys: [],
    keywords: {},
  }),
  methods: {
    ...mapActions(['FETCH_RECOMMEND_LATEST_PRODUCT']),
    getCategories() {
      this.FETCH_RECOMMEND_LATEST_PRODUCT();
    },
    getKeywords() {
      axios
        .get('http://k4d104.p.ssafy.io:8081/product/recommend/realtimesearchword')
        .then((res) => {
          // console.log('키워드 획득')
          // console.log(res.data.data)
          this.keywords = res.data.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.getCategories();
    this.getKeywords();
  },
};
</script>

<style></style>
