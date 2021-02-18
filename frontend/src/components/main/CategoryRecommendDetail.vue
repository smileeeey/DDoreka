<template>
  <v-container class="mb-5 category-recommend-container">
    <v-row>
      <v-col class="pa-0" cols=2>
        <CategoryRecommendInfo :categoryKey="categoryKey" :keywords="keywords" />
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
    categoryKey: String,
    items: Array,
    keywords: Array,
  },
  data: () => ({
    bigItems: [],
    smallItems: [],
  }),
  created() {
    this.bigItems = this.items.slice(0, 3)
    const slide1 = this.items.slice(3, 9)
    const slide2 = this.items.slice(9, 15)
    // const slide3 = this.items.slice(17, 23)
    this.smallItems.push(slide1, slide2)
  }
}
</script>

<style>

</style>