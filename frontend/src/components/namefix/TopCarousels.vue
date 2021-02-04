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
        src: require('@/assets/img/main/topcarousels/sample1.png'),
      },
      {
        src: require('@/assets/img/main/topcarousels/sample2.png'),
      },
      {
        src: require('@/assets/img/main/topcarousels/sample3.png'),
      },
      {
        src: require('@/assets/img/main/topcarousels/sample4.png'),
      },
      {
        src: require('@/assets/img/main/topcarousels/sample5.png'),
      },      
    ],    
  })
}
</script>

<style>

</style>