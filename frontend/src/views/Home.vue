<template>
  <v-app>
    <AppBar />
    <v-main class="in-layout mx-auto">
      <router-view />
    </v-main>
    <Footer />
  </v-app>
</template>

<script>
import AppBar from '../components/core/AppBar.vue'
import Footer from '../components/core/Footer.vue'
// @ is an alias to /src

export default {
  name: 'Main',
  components: {
    AppBar,
    Footer,
  }
}
</script>
