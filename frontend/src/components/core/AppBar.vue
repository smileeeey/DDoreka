<template>
  <v-card
    class="mx-auto"
    width="100%"
    style="display: flex; flex-direction:column; align-items: center; min-width: 75%;"
    flat
  >
    <!-- status bar -->
    <StatusBar />
    <!-- main appbar -->

    <v-card-text
      class="pa-0"
      style="display: flex; justify-content: space-between; width: 75%;"
    >
    <div style="display: flex">
      <!-- Categories -->
      <Categories />

      <!-- logo(temporary) -->
      <v-img
        @click="$router.push({ name: 'Main' })"
        style="cursor: pointer"
        src="@/assets/eureka_logo(blank).png"
        class="my-auto"
        max-height="120"
        max-width="400"

      >
      </v-img>

      <!-- SearchBar -->
      <SearchBar class="my-auto" />
    </div>

      <!-- icon -->
      <AppBarIcons style="z-index: 1;" />
      
    </v-card-text>
  </v-card>
</template>

<script>
import AppBarIcons from './AppBar/AppBarIcons.vue'
import Categories from './AppBar/Categories.vue'
import SearchBar from './AppBar/SearchBar.vue'
import StatusBar from './AppBar/StatusBar.vue'
export default {
  name: 'AppBar',
  components: {
    Categories,
    SearchBar,
    StatusBar,
    AppBarIcons,
  },
  
}
</script>

<style>

</style>