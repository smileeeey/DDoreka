<template>
  <div>
    <h3 class="my-5">오늘의 상품 | <span style="font-size: 70%">우리가 오늘 추천하는 상품!</span></h3>
    <v-row>
      <v-col v-for="(todayItem, idx) in todayItemsList" :key="idx" class="d-flex child-flex" cols="4">
        <TodayItem :todayItem="todayItem" />
      </v-col>
    </v-row>
    <div class="loadingDiv">
      <spinner :loading="loding"></spinner>
    </div>
  </div>
</template>

<script>
import TodayItem from './TodayItem.vue';
import { mapState } from 'vuex';
import Spinner from '../Spinner.vue';
export default {
  name: 'TodayItems',
  components: {
    TodayItem,
    Spinner
  },
  computed: {
    ...mapState('mainStore', ['todayItemsList', 'loding']),
  },
};
</script>
