<template>
  <v-container class="ma-0">
    <v-row>
      <v-col class="pa-0" cols=2>
        <SideBar />
      </v-col>
      <v-col cols=10>
        <TopInfo />
        <v-divider class="my-5"></v-divider>
        <CategoryItemList :items="items" />
        <div class="mb-16 py-5 text-center">
          <v-pagination
            v-model="page"
            :length="10"
          ></v-pagination>
        </div>        
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import SideBar from '@/components/category/SideBar.vue'
import TopInfo from '@/components/category/TopInfo.vue'
import CategoryItemList from '@/components/category/CategoryItemList.vue'
// import Pagination from '@/components/category/Pagination.vue'
export default {
  name: 'Category',
  components: {
    SideBar,
    TopInfo,
    CategoryItemList,
    // Pagination,
  },
  data: () => ({
    id: '',
    depth: '',
    items: [],
    page: 1,
    size: 3,
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
          page: this.page,
          size: this.size,
        }
      })
      .then(res => {
        // console.log(res.data.data.content)
        this.items = res.data.data.content
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  watch: {
    page: function() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/findByCategory/${this.id}/${this.depth}`, {
        params: {
          page: this.page,
          size: this.size,
        }
      })
      .then(res => {
        console.log('페이지 변경')
        this.items = res.data.data.content
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    this.getId()
    this.getDepth()
    this.getItems()
  }
}
</script>

<style>

</style>