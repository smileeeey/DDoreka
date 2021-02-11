<template>
  <v-carousel
    cycle
    height="500"
    hide-delimiter-background
    show-arrows-on-hover
    style="border-box"
  >
    <v-carousel-item
      v-for="(topInfoItem, idx) in topInfoItems"
      :key="idx"
    >
      <v-img
        :src="topInfoItem.src"
        height="100%"
      >
      </v-img>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  name: 'TopInfoCarousels',
  props: {
    item: Object,
  },
  data: () => ({
    topInfoItems: [
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1398472546668-b33b241a-6f95-435c-a76c-99147e7917c1.jpg'},
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1398472546668-b33b241a-6f95-435c-a76c-99147e7917c1.jpg'},
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1398472546668-b33b241a-6f95-435c-a76c-99147e7917c1.jpg'},
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1398472546668-b33b241a-6f95-435c-a76c-99147e7917c1.jpg'},
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1398472546668-b33b241a-6f95-435c-a76c-99147e7917c1.jpg'},
    ]
  })
}
</script>

<style>

</style>