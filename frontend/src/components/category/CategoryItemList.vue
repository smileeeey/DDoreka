<template>
  <div>
    <v-container>
      <v-row>
        <v-col
          v-for="(categoryItem, idx) in categoryItems"
          :key="idx"
          class="d-flex child-flex"
          cols="4"
        >
          <CategoryItem :categoryItem="categoryItem" />
        </v-col>
      </v-row>
    </v-container>
    <v-divider class="my-16"></v-divider>
  </div>
</template>

<script>
import CategoryItem from './CategoryItem.vue'
export default {
  name: 'CategoryItemList',
  components: {
    CategoryItem,
  },
  data: () => ({
    categoryItems: [
      {
        name: '캐럿 여성 와이드 밴딩 팬츠',
        image: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86815902988186-18c5ec56-8775-4476-ae6f-d1bae7e2dcc5.jpg',
        itemUrl: 'http://localhost:8080/',
        price: '9,990원',
        rating: 4.5,
        reviews: 1039,
      },
      {
        name: '코스마 루즈핏 브리넷',
        image: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/9738/1a4fea5de62e988a12f50ef5b1e736e5135f8cca108f27258e2da3e9b801.jpg',
        itemUrl: 'http://localhost:8080/',
        price: '7,500원',
        rating: 3.5,
        reviews: 234,
      },
      {
        name: '티탁 여성 빅사이즈 루즈핏',
        image: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/08/11/13/5/9dae1a9d-6a23-4923-91ff-774d8c81fed1.jpg',
        itemUrl: 'http://localhost:8080/',
        price: '9,900원',
        rating: 4,
        reviews: 115,
      },
      {
        name: '캐럿 여성용 브이넥 롱 스웨터',
        image: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/13055501607938-a1f45a70-4558-4445-a845-fd974b82b6d0.jpg',
        itemUrl: 'http://localhost:8080/',
        price: '14,500원',
        rating: 4.5,
        reviews: 680,
      },
      {
        name: '크로커다일',
        image: 'https://picsum.photos/500/300?image=10',
        itemUrl: 'http://localhost:8080/',
        price: '7,660원',
        rating: 3,
        reviews: 437,
      },
      {
        name: '나이키 브라질리아 더플백',
        image: 'https://picsum.photos/500/300?image=10',
        itemUrl: 'http://localhost:8080/',
        price: '23,520원',
        rating: 2,
        reviews: 24,
      },
    ]
  })
}
</script>

<style>

</style>