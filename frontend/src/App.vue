<template>
  <v-app>
    <!-- app-bar -->
    <AppBar />
    <v-main>
      <router-view />
    </v-main>
    <!-- bottom-nav -->
    <BottomBar />
  </v-app>
</template>

<script>
import AppBar from './components/core/AppBar.vue'
import BottomBar from './components/core/BottomBar.vue'

export default {
  name: 'App',

  components: {
    AppBar,
    BottomBar,
  },

  data: () => ({
    //
  }),
};
</script>
