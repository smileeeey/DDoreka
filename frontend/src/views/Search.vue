<template>
  <v-container class="ma-0">
    <v-row>
      <v-col class="pa-0" cols=2>
        <SideBar />
      </v-col>
      <v-col cols=10>
        <TopInfo />
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
    totalPages: 10,
  }),
  methods: {

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
    // console.log(this.searchData)
    this.items = this.searchData.content
    this.totalPages = this.searchData.totalPages
  },
}
</script>

<style>

</style>