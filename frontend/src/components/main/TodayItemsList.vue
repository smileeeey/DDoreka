<template>
  <div>
    <h3 class="my-5">오늘의 상품 | <span style="font-size: 70%">우리가 오늘 추천하는 상품!</span></h3>
    <v-row>
      <v-col v-for="(sumnailUrl, idx) in todayItemSumnailUrlList" :key="idx" class="d-flex child-flex" cols="4">
        <TodayItem :sumnailUrl="sumnailUrl" />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import TodayItem from './TodayItem.vue';
import { mapState } from 'vuex';
export default {
  name: 'TodayItems',
  components: {
    TodayItem,
  },
  computed: {
    ...mapState('mainStore', ['todayItemSumnailUrlList']),
  },
};
</script>
