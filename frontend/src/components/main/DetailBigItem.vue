<template>
  <v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
    style="border-box"
  >
    <DetailBigItemOne
      v-for="(bigItem, idx) in bigItems"
      :key="idx"
      :bigItem="bigItem"
    />
  </v-carousel>
</template>

<script>
import DetailBigItemOne from './DetailBigItemOne.vue'
export default {
  name: 'DetailBigItem',
  components: {
    DetailBigItemOne,
  },
  props: {
    bigItems: Array,
  }
}
</script>

<style>

</style>