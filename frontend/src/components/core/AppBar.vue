<template>
  <v-card
    class="mx-auto"
    width="100%"
    style="display: flex; flex-direction:column; justify-content: center; align-items: center; min-width: 1300px;"
    flat
  >
    <!-- status bar -->
    <StatusBar />
    <!-- main appbar -->
    <v-card-text
      class="pa-0"
      style="display: flex; justify-content: space-between; width: 75%;"
    >
      <!-- Categories -->
      <Categories />

      <!-- logo(temporary) -->
      <v-img
        src="@/assets/temporary_logo.png"
        class="my-auto"
        max-height="80"
        max-width="187"
      >
      </v-img>

      <!-- SearchBar -->
      <SearchBar class="my-auto" />

      <!-- icon -->
      <AppBarIcons style="z-index: 0;" />
      
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