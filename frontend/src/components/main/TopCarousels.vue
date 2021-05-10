<template>
  <v-carousel cycle hide-delimiter-background>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
      @click="moveItemDetail(item)"
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
        category3Id: 244,
        productId: 223,
      },
      {
        src: require('@/assets/img/main/topcarousels/sample2.png'),
        category3Id: 212,
        productId: 41,
      },
      {
        src: require('@/assets/img/main/topcarousels/sample3.png'),
        category3Id: 243,
        productId: 209,
      },
    ],
  }),
  methods: { 
    moveItemDetail: function (item) {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push({name: 'ItemDetail', params: {id: item.category3Id, productid: item.productId}})
    },
  },
}
</script>

<style>

</style>