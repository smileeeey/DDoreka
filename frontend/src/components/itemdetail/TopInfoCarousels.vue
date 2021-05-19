<template>
  <v-carousel cycle height="500" hide-delimiter-background show-arrows-on-hover style="border-box">
    <v-carousel-item v-for="(detailThumbnailImage, idx) in detailThumbnailImages" :key="idx">
      <v-img :src="detailThumbnailImage" height="100%"> </v-img>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
import { mapState } from 'vuex';
export default {
  name: 'TopInfoCarousels',
  props: {
    sFiles: Array,
  },
  computed: {
    ...mapState('mainStore', ['detailThumbnailImages']),
  },
};
</script>

<style></style>
