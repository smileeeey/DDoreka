<template>
  <v-container class="mb-5 category-recommend-container">
    <v-row>
      <v-col class="pa-0" cols=2>
        <CategoryRecommendInfo :category="category" :keywords="keywords" />
      </v-col>
      <v-col class="pa-0" cols=4>
        <DetailBigItem :bigItems="bigItems" />
      </v-col>
      <v-col class="pa-0" cols=6>
        <DetailSmallItem :smallItems="smallItems" />
      </v-col>
    </v-row> 
  </v-container>
</template>

<script>
import CategoryRecommendInfo from './CategoryRecommendInfo'
import DetailBigItem from './DetailBigItem'
import DetailSmallItem from './DetailSmallItem'
export default {
  name: 'CategoryRecommendDetail',
  components: {
    CategoryRecommendInfo,
    DetailBigItem,
    DetailSmallItem,
  },
  props: {
    category: Object,
  },
  data: () => ({
    bigItems: [],
    smallItems: [],
    keywords: ['#니트', '#기모 바지', '#기모 맨투맨', '#마스크걸이', '#아우터', '#나이키', '#스웨터']
  }),
  created() {
    this.bigItems = this.category.bigItems
    this.smallItems = this.category.smallItems
  }
}
</script>

<style>

</style>