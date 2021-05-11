<template>
  <div>
    <h3 class="my-5">스테디 셀러 | <span style="font-size: 70%">꾸준히 잘 팔리는 상품</span></h3>
    <v-sheet class="mx-auto" flat outlined rounded>
      <v-slide-group class="pa-2" show-arrows>
        <SteadySellerCard v-for="(productId, idx) in steadySellerProductIds" :productId="productId" :key="idx" />
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import SteadySellerCard from './SteadySellerCard.vue';
export default {
  name: 'SteadySeller',
  components: {
    SteadySellerCard,
  },
  computed: {
    ...mapState('mainStore', ['steadySellerProductIds']),
  },
  methods: {
    ...mapActions('mainStore', ['FETCH_RECOMMENDS_STEADY_SELLER']),

    getItems() {
      this.FETCH_RECOMMENDS_STEADY_SELLER();
    },
  },
  created() {
    this.getItems();
  },
};
</script>

<style></style>
