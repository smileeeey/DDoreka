<template>
  <v-content>
    <router-view />
    <Footer />
  </v-content>
</template>

<script>
import Footer from '../Footer.vue'
export default {
  components: { Footer },

}
</script>

<style>

</style>