<template>
  <v-carousel cycle>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
  </v-carousel>
</template>

<script>
import '@/assets/css/common.scss'
export default {
  name: "TopCarousels",
  data: () => ({
    // items는 추후에 axios요청을 통해 채워 넣을 예정
    items: [
      {
        src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
      },
      {
        src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
      },
      {
        src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
      },
      {
        src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
      },
      {
        src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
      },      
    ],    
  })
}
</script>

<style>

</style>