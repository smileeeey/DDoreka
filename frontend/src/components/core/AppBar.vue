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
      style="display: flex; width: 75%;"
    >
      <!-- Categories -->
      <Categories />

      <!-- logo(temporary) -->
      <v-img
        src="@/assets/temporary_logo.png"
        class="my-auto mx-5"
        max-height="80"
        max-width="187"
      >
      </v-img>

      <!-- SearchBar -->
      <SearchBar class="my-auto" />
    </v-card-text>
  </v-card>
</template>

<script>
import Categories from './AppBar/Categories.vue'
import SearchBar from './AppBar/SearchBar.vue'
import StatusBar from './AppBar/StatusBar.vue'
export default {
  name: 'AppBar',
  components: {
    Categories,
    SearchBar,
    StatusBar,
  },
  data: () => ({
    items: [
      { title: 'Click Me' },
      { title: 'Click Me2' },
      { title: 'Click Me3' },
    ],
  }),
}
</script>

<style>

</style>