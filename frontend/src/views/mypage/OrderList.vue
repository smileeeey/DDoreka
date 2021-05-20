<template>
  <div>
    <h2>주문목록</h2>
    <div v-for="(item, idx) in payList.concat(this.shippingList)" :key="idx">
      <PastOrderCard :item="item" />
    </div>
  </div>
</template>

<script>
import PastOrderCard from "../../components/mypage/PastOrderCard.vue";
import { mapState } from "vuex";
export default {
  name: "OrderList",
  components: {
    PastOrderCard,
  },
  computed: {
    ...mapState('mypageStore',["payList","shippingList"]),
  },
  data() {
    return {
      items: [],
    };
  },
};
</script>

<style>
</style>