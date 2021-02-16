<template>
  <v-card
    height="1000"
    width="100%"
    class="mx-auto"
  >
    <v-navigation-drawer permanent style="width: 100%">
      <SideBarTree />
      <SideBarCheck />
      <SideBarRating />
      <SideBarList />
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import SideBarTree from './SideBarTree.vue'
import SideBarCheck from './SideBarCheck.vue'
import SideBarRating from './SideBarRating.vue'
import SideBarList from './SideBarList.vue'
export default {
  name: 'SideBar',
  components: {
    SideBarTree,
    SideBarCheck,
    SideBarRating,
    SideBarList,
  },
  data: () => ({
    
  })
}
</script>

<style>

</style>