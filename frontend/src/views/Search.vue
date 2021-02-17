<template>
  <v-container class="ma-0">
    <v-row>
      <v-col class="pa-0" cols=2>
        <SideBar />
      </v-col>
      <v-col cols=10>
        <TopInfo 
          :totalElements="totalElements"
          @order-by-rating="orderByRating"
          @order-by-date="orderByDate"
          @order-by-review-cnt="orderByReviewCnt"
          @order-by-price-descend="orderByPriceDescend"
          @order-by-price-ascend="orderByPriceAscend"
        />
        <v-divider class="my-5"></v-divider>
        <SearchItemList v-if="items.length > 0" :items="items" />
        <div class="mb-16 py-5 text-center">
          <v-pagination
            v-model="page"
            :length="totalPages"
          ></v-pagination>
        </div>        
      </v-col>
    </v-row>
  </v-container>  
</template>

<script>
import { mapState } from 'vuex'
import SideBar from '@/components/search/SideBar.vue'
import TopInfo from '@/components/search/TopInfo.vue'
import SearchItemList from '@/components/search/SearchItemList.vue'
export default {
  name: 'Search',
  components: {
    SideBar,
    TopInfo,
    SearchItemList,
  },
  data: () => ({
    items: [],
    page: 1,
    totalPages: 0,
    totalElements: 0,
  }),
  methods: {
    orderByRating() {
      console.log('평점높은순')
      this.items = _.orderBy(this.items, 'rating', 'desc')
      // console.log(this.items)
    },
    orderByDate() {
      console.log('최신 등록순')
      this.items = _.orderBy(this.items, 'registerDate', 'desc')
      // console.log(this.items)
    },
    orderByReviewCnt() {
      console.log('리뷰 많은 순')
      this.items = _.orderBy(this.items, 'reviewCnt', 'desc')
    },
    orderByPriceDescend() {
      console.log('가격 높은순')
      this.items.sort(function(item1, item2) {
        return item2.options[0].discountPrice - item1.options[0].discountPrice
      })
    },
    orderByPriceAscend() {
      console.log('가격 낮은순')
      this.items.sort(function(item1, item2) {
        return item1.options[0].discountPrice - item2.options[0].discountPrice
      })
    },
  },
  computed: {
    ...mapState([
      'searchData'
    ]),
  },
  watch: {
    searchData() {
      this.items = this.searchData.content
      this.totalPages = this.searchData.totalPages  
    }
  },
  created() {
    console.log(this.searchData)
    this.items = this.searchData.content
    this.totalPages = this.searchData.totalPages
    this.totalElements = this.searchData.totalElements
  },
  updated() {
    this.items = this.searchData.content
    this.totalPages = this.searchData.totalPages
    this.totalElements = this.searchData.totalElements
  }
}
</script>

<style>

</style>