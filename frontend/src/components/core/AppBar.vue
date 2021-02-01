<template>
  <v-card
    class="mx-auto"
    width="100%"
    style="display: flex; flex-direction:column; align-items: center; min-width: 50%;"
    flat
  >
    <!-- status bar -->
    <StatusBar />
    <!-- main appbar -->
    <v-card-text
      class="pa-0"
      style="display: flex; justify-content: space-between; width: 50%;"
    >
      <!-- Categories -->
      <Categories />

      <!-- logo(temporary) -->
      <v-img
        src="@/assets/eureka_logo(blank).png"
        class="my-auto"
        height="100"
        width="215"
        
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