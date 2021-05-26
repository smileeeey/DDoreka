<template>
  <div>
    <h3 class="my-5">판매자의 다른 상품</h3>
    <v-sheet class="mx-auto" flat outlined rounded>
      <v-slide-group class="pa-2" show-arrows>
        <OtherItemCard v-for="(detailProductSellerOtherProduct, idx) in detailProductSellerOtherProducts" :key="idx" :detailProductSellerOtherProduct="detailProductSellerOtherProduct" />
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import OtherItemCard from './OtherItemCard.vue';
import { mapState } from 'vuex';
export default {
  name: 'OtherItems',
  components: {
    OtherItemCard,
  },
  computed: {
    ...mapState('mainStore', ['detailProductSellerOtherProducts']),
  },
  methods: {},
};
</script>

<style></style>
