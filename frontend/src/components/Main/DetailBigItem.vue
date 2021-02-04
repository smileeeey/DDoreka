<template>
  <v-carousel
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
    style="border-box"
  >
    <v-carousel-item
      v-for="(bigItem, idx) in bigItems"
      :key="idx"
    >
      <v-img
        :src="bigItem.image"
        height="100%"
      >
      </v-img>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  name: 'DetailBigItem',
  props: {
    bigItems: Array,
  }
}
</script>

<style>

</style>