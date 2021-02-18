<template>
  <v-container class="ma-0">
    <v-row>
      <!-- <v-col class="pa-0" cols=2>
        <SideBar />
      </v-col> -->
      <v-col cols=12>
        <TopInfo
          :totalElements="totalElements"
          @order-by-rating="orderByRating"
          @order-by-date="orderByDate"
          @order-by-review-cnt="orderByReviewCnt"
          @order-by-price-descend="orderByPriceDescend"
          @order-by-price-ascend="orderByPriceAscend"
          @filter-on="filterOn"
          @filter-off="filterOff"
        />
        <v-divider class="my-5"></v-divider>
        <CategoryItemList :items="items" />
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
import axios from 'axios'
import _ from 'lodash'
// import { mapState } from 'vuex'
// import SideBar from '@/components/category/SideBar.vue'
import TopInfo from '@/components/category/TopInfo.vue'
import CategoryItemList from '@/components/category/CategoryItemList.vue'
export default {
  name: 'Category',
  components: {
    // SideBar,
    TopInfo,
    CategoryItemList,
  },
  data: () => ({
    id: '',
    depth: '',
    items: [],
    originItems: [],
    page: 1,
    size: 9,
    totalPages: 0,
    totalElements: 0,
  }),
  methods: {
    getId() {
      this.id = this.$route.params.id  
    },
    getDepth() {
      this.depth = this.$route.params.depth  
    },
    getItems() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/findByCategory/${this.id}/${this.depth}`, {
        params: {
          page: this.page - 1,
          size: this.size,
        }
      })
      .then(res => {
        // console.log(res.data.data.content)
        this.items = res.data.data.content
        this.originItems = this.items
        this.totalPages = res.data.data.totalPages
        this.totalElements = res.data.data.totalElements
      })
      .catch(err => {
        console.log(err)
      })
    },
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
    filterOn(filterInfo) {
      console.log(filterInfo)
      // 평점 필터
      if (filterInfo.rating != 0 ) {
        const items = this.items.filter(function (item) {
          return item.rating >= filterInfo.rating
        })
        this.items = items
      }
      // 최저가 필터
      if (filterInfo.minPrice != 0 ) {
        const items = this.items.filter(function (item) {
          return item.options[0].discountPrice >= filterInfo.minPrice
        })
        this.items = items
      }
      // 최고가 필터
      if (filterInfo.maxPrice != 0 ) {
        const items = this.items.filter(function (item) {
          return item.options[0].discountPrice <= filterInfo.maxPrice
        })
        this.items = items
      }
    },
    filterOff() {
      console.log('filterOff')
      this.items = this.originItems
    },
  },
  // computed: {
  //   ...mapState([
  //     'search'
  //   ])
  // },
  watch: {
    page: function() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/findByCategory/${this.id}/${this.depth}`, {
        params: {
          page: this.page - 1,
          size: this.size,
        }
      })
      .then(res => {
        // console.log('페이지 변경')
        this.items = res.data.data.content
        this.originItems = this.items
      })
      .catch(err => {
        console.log(err)
      })
    },

    '$route.params.id': {
      handler: function () {
        this.getId()
        this.getDepth()
        this.getItems()
      },
      deep: true,
      immediate: true
    }
  },
  created() {
    // console.log(this.search)
    this.getId()
    this.getDepth()
    this.getItems()
  },
  // updated() {
  //   this.getId()
  //   this.getDepth()
  //   this.getItems()
  // },
}
</script>

<style>

</style>