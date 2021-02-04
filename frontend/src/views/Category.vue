<template>
  <v-container class="ma-0">
    <v-row>
      <v-col class="pa-0" cols=2>
        <SideBar />
      </v-col>
      <v-col cols=10>
        <TopInfo />
        <v-divider class="my-5"></v-divider>
        <CategoryItemList />
        <Pagination />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
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

  }),
}
</script>

<style>

</style>