<template>
  <v-card
    class="mx-auto"
    width="100%"
    style="display: flex; flex-direction: column; align-items: center;"
    flat
  >
    <!-- status bar -->
    <StatusBar />
    <!-- main appbar -->
    <v-container class="ma-0 px-0" style="max-width: initial;">
      <v-row
        class="mx-auto"
        justify="center"
        align="center"
        style="width: 75%; border-bottom: solid 1px grey;"
      >
        <v-col class="pa-0" cols="1">
          <!-- Categories -->
          <Categories />
        </v-col>
        <v-col class="pa-0" cols="2" style="height: 115px;">
          <!-- logo(temporary) -->
          <a @click="$router.push({ name: 'Main' })">
            <v-img
              style="cursor: pointer; height: 115px;"
              src="@/assets/eureka_logo(blank).png"
              class="my-auto"
              contain
            >
            </v-img>
          </a>
        </v-col>
        <v-col class="pa-0 mx-auto" cols="7">
            <!-- SearchBar -->
            <SearchBar />
        </v-col>
        <v-col class="pa-0" cols="2">
          <!-- icon -->
          <AppBarIcons style="z-index: 1;" />
        </v-col>
      </v-row>
    </v-container>
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