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
import {mapActions} from 'vuex'
export default {
  name: 'TodayItems',
  components: {
    TodayItem,
  },
  data: () => ({
    todayItems: [],
  }),
  methods: {
    ...mapActions(['FETCH_RECOMMEND_TODAYHOT']),

    getTodayItems() {
      this.FETCH_RECOMMEND_TODAYHOT().then((res)=>{
         this.todayItems = res.data.data
      })
      .catch(err=>{console.log(err);});
    },
  },
  created() {
    this.getTodayItems()
  },
}
</script>

<style>

</style>