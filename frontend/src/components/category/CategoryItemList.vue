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
        name: '에어쿠션',
        image: 'https://picsum.photos/500/300?image=10',
        itemUrl: 'http://localhost:8080/',
        price: '10,900원',
        rating: 4.5,
        reviews: 671,
      },
      {
        name: '모이브닝 대두 볼캡',
        image: 'https://picsum.photos/500/300?image=10',
        itemUrl: 'http://localhost:8080/',
        price: '12,900원',
        rating: 5,
        reviews: 111,
      },
      {
        name: '포락스 인솔 정',
        image: 'https://picsum.photos/500/300?image=10',
        itemUrl: 'http://localhost:8080/',
        price: '16,800원',
        rating: 4,
        reviews: 61,
      },
      {
        name: '엘레스 여성 티셔츠 박시핏',
        image: 'https://picsum.photos/500/300?image=10',
        itemUrl: 'http://localhost:8080/',
        price: '19,900원',
        rating: 4.5,
        reviews: 671,
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