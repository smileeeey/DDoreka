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
        <Pagination />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import SideBar from '@/components/category/SideBar.vue'
import TopInfo from '@/components/category/TopInfo.vue'
import CategoryItemList from '@/components/category/CategoryItemList.vue'
import Pagination from '@/components/category/Pagination.vue'
export default {
  name: 'Category',
  components: {
    SideBar,
    TopInfo,
    CategoryItemList,
    Pagination,
  },
  data: () => ({
    id: '',
    depth: '',
    items: [],
  }),
  methods: {
    getId() {
      this.id = this.$route.params.id  
    },
    getDepth() {
      this.depth = this.$route.params.depth  
    },
    getItems() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/findByCategory/${this.id}/${this.depth}`)
      .then(res => {
        console.log(res.data)
        this.items = res.data.data
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