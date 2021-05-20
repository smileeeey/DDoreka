<template>
  <div>
    <h2>주문목록</h2>
    <div v-for="(item, idx) in orderList" :key="idx">
      <PastOrderCard :item="item" />
    </div>
  </div>
</template>

<script>
import PastOrderCard from "../../components/mypage/PastOrderCard.vue";
import { mapActions, mapState } from "vuex";

export default {
  name: "OrderList",
  components: {
    PastOrderCard,
  },
  computed: {
    ...mapState('accountStore',["userData"]),
    ...mapState('mypageStore',["orderList"]),
  },
  data() {
    return {
    };
  },
  methods: {
    ...mapActions('mypageStore',['FETCH_ORDER_LIST'])
  },
  created() {
    this.FETCH_ORDER_LIST({userId : this.userData.userId});
  },
};
</script>

<style>
</style>