<template>
  <div>
    <h3 class="my-5">오늘의 상품 | <span style="font-size: 70%">우리가 오늘 추천하는 상품!</span></h3>
    <v-row>
      <v-col
        v-for="(todayItem, idx) in todayItems"
        :key="idx"
        class="d-flex child-flex"
        cols="4"
      >
        <TodayItem :todayItem="todayItem" />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import TodayItem from './TodayItem.vue'
export default {
  name: 'TodayItems',
  components: {
    TodayItem,
  },
  data: () => ({
    // 추후 todayItems는 빈 배열로 하고 created()에서 axios을 통해 6개의 추천 아이템을 채워넣는다.
    // Item에는 이미지 경로, 상품명, 평점, 리뷰개수, 상품 디테일 페이지 경로가 필요
    todayItems: [
      {src: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/86848698996868-a741b76d-8b36-4572-80e2-2f869d9c2624.jpg'},
      {src: `https://picsum.photos/500/300?image=20`},
      {src: `https://picsum.photos/500/300?image=25`},
      {src: `https://picsum.photos/500/300?image=30`},
      {src: `https://picsum.photos/500/300?image=35`},
      {src: `https://picsum.photos/500/300?image=40`},
    ],
  }),
  created() {
    console.log('Main created')
  }
}
</script>

<style>

</style>