<template>
  <v-container class="mb-5 category-recommend-container">
    <v-row>
      <v-col class="pa-0" cols=2>
        <div class="mt-5 pl-3 text-h6 font-weight-bold">{{category.name}}</div>
        <div class="pa-4">
          <div>HOT키워드</div>
          <v-chip-group
            column
          >
            <v-chip
              v-for="keyword in keywords"
              :key="keyword"
            >
              {{ keyword }}
            </v-chip>
          </v-chip-group>
        </div>        
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
import DetailBigItem from './DetailBigItem.vue'
import DetailSmallItem from './DetailSmallItem.vue'
export default {
  name: 'CategoryRecommendDetail',
  components: {
    DetailBigItem,
    DetailSmallItem,
  },
  props: {
    category: Object,
  },
  data: () => ({
    bigItems: [],
    smallItems: [],
    keywords: ['#니트원피스', '#기모 바지', '#기모 맨투맨', '#마스크걸이', '#아우터', '#앵클부츠', '#스웨터']
  }),
  created() {
    this.bigItems = this.category.bigItems
    this.smallItems = this.category.smallItems
  }
}
</script>

<style>

</style>