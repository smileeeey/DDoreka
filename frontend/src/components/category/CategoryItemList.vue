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
  props: {
    items: Array,
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
        name: '민트위니 여성용 미키기모셋',
        image: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4fcd/162c5d78b17078cfb5c2759e809c320b051b2989d2e6256bbab43cee3393.jpg',
        itemUrl: 'http://localhost:8080/',
        price: '22,900원',
        rating: 4.5,
        reviews: 115,
      },
      {
        name: '무로 남녀공용 다이어트 슈트',
        image: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1571800841199365-7a666d1f-2e6d-40d8-9068-0cb6995ea52c.jpg',
        itemUrl: 'http://localhost:8080/',
        price: '29,800원',
        rating: 4.5,
        reviews: 1593,
      },
    ]
  })
}
</script>

<style>

</style>