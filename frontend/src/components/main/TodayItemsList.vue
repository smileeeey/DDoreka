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
import axios from 'axios'
import TodayItem from './TodayItem.vue'
export default {
  name: 'TodayItems',
  components: {
    TodayItem,
  },
  data: () => ({
    todayItems: [],
  }),
  methods: {
    getTodayItems() {
      axios.get('http://i4d106.p.ssafy.io:8084/order/recommend/todayhot')
      .then(res => {
        // console.log(res.data.data)
        this.todayItems = res.data.data
      })
      .catch(err => {
        console.error(err)
      })      
    },
  },
  created() {
    this.getTodayItems()
  },
}
</script>

<style>

</style>