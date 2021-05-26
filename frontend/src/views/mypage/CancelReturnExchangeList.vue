<template>
  <div>
    <h2>주문취소목록</h2>
    <div v-for="(item, idx) in reFundList" :key="idx">
      <CancelOrderCard :item="item" />
    </div> -->
  </div>
</template>

<script>
import CancelOrderCard from '../../components/mypage/CancelOrderCard.vue'
import { mapState } from 'vuex';
export default {
  name: 'CancelReturnExchangeList',
  components: {
    CancelOrderCard
  },
  computed: {
    ...mapState('mypageStore',['reFundList'])
  },
  data () {
    return {
    }
  },
}
</script>

<style>

</style>