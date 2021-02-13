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
// import axios from 'axios'
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
      {src: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/03/17/10/7/3e411590-854f-4eb7-84b3-2e7504840b3f.jpg'},
      {src: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2323776566774729-86661932-eb66-43bd-ae2b-2b1c548930b3.jpg'},
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/08/28/19/1/044baecd-cc95-4835-b0aa-2bf042bb6906.jpg'},
      {src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/292x292ex/image/vendor_inventory/3cb3/670006af555057e4c12263865c27eb5a816a20c266152c948ea8199e6833.jpg'},
      {src: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/292x292ex/image/retail/images/88522104879397-a25cef30-7726-474c-a851-c4a4fc987c81.jpg'},
    ],
  }),
  // methods: {
  //   getTodayItemsList: () => {
  //     axios.get('url')
  //     .then(res => {
  //       console.log(res.data)
  //     })
  //     .catch(err => {
  //       console.error(err)
  //     })      
  //   },
  // },
  // created() {
  //   getTodayItemsList()
  // },
}
</script>

<style>

</style>