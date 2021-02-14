<template>
  <v-carousel cycle hide-delimiter-background>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
  </v-carousel>
</template>

<script>
import axios from 'axios'
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
  }),
  methods: {
    getTopCarousels: () => {
      axios.get('url')
      .then(res => {
        console.log(res.data)
        this.items = res.data
      })
      .catch(err => {
        console.error(err)
      })      
    }
  },
  created() {
    
  }
}
</script>

<style>

</style>