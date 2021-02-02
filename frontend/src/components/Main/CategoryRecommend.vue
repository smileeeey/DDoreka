<template>
  <div>
    <h3 class="my-5">카테고리별 추천 상품 | <span style="font-size: 70%">카테고리별 인기상품을 한눈에</span></h3>
    <CategoryRecommendDetail 
      v-for="(category, idx) in categories"
      :key="idx"
      :category="category"
    />
  </div>
</template>

<script>
import CategoryRecommendDetail from './CategoryRecommendDetail.vue'
export default {
  name: 'CategoryRecommend',
  components: {
    CategoryRecommendDetail,
  },
  data: () => ({
    categories: [
      { 
        name: '여성패션',
        bigItems: [
          {
            image: 'https://picsum.photos/500/300?image=20',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://picsum.photos/500/300?image=25',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://picsum.photos/500/300?image=30',
            itemUrl: 'http://localhost:8080/'
          }
        ],
        smallItems: [
          {
            name: '에어쿠션',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '10,900원'
          },
          {
            name: '모이브닝 대두 볼캡',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '12,900원'
          },
          {
            name: '포락스 인솔 정',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '16,800원'
          },
          {
            name: '엘레스 여성 티셔츠 박시핏',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '19,900원'
          },
          {
            name: '크로커다일',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '7,660원'
          },
          {
            name: '나이키 브라질리아 더플백',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '23,520원'
          },
          {
            name: '에어쿠션',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '10,900원'
          },
          {
            name: '모이브닝 대두 볼캡',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '12,900원'
          },
          {
            name: '포락스 인솔 정',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '16,800원'
          },
          {
            name: '엘레스 여성 티셔츠 박시핏',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '19,900원'
          },
          {
            name: '크로커다일',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '7,660원'
          },
          {
            name: '나이키 브라질리아 더플백',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '23,520원'
          },          
        ]
      },
      { 
        name: '남성패션',
        bigItems: [
          {
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://picsum.photos/500/300?image=15',
            itemUrl: 'http://localhost:8080/'
          },
          {
            image: 'https://picsum.photos/500/300?image=20',
            itemUrl: 'http://localhost:8080/'
          }
        ],
        smallItems: [
          {
            name: '에어쿠션',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '10,900원'
          },
          {
            name: '모이브닝 대두 볼캡',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '12,900원'
          },
          {
            name: '포락스 인솔 정',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '16,800원'
          },
          {
            name: '엘레스 여성 티셔츠 박시핏',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '19,900원'
          },
          {
            name: '크로커다일',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '7,660원'
          },
          {
            name: '나이키 브라질리아 더플백',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '23,520원'
          },
          {
            name: '에어쿠션',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '10,900원'
          },
          {
            name: '모이브닝 대두 볼캡',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '12,900원'
          },
          {
            name: '포락스 인솔 정',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '16,800원'
          },
          {
            name: '엘레스 여성 티셔츠 박시핏',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '19,900원'
          },
          {
            name: '크로커다일',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '7,660원'
          },
          {
            name: '나이키 브라질리아 더플백',
            image: 'https://picsum.photos/500/300?image=10',
            itemUrl: 'http://localhost:8080/',
            price: '23,520원'
          },          
        ]
      }
    ],
  })
}
</script>

<style>

</style>