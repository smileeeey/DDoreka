<template>
  <div>
    <h3 class="my-5">요즘 뜨는 상품 | <span style="font-size: 70%">최근 가장 HOT한 상품</span></h3>
    <v-sheet class="mx-auto" flat outlined rounded>
      <v-slide-group class="pa-2" show-arrows>
        <HotItemCard v-for="(hotItem, idx) in hotItemsList" :hotItem="hotItem" :key="idx" />
      </v-slide-group>
    </v-sheet>
  </div>
</template>

<script>
import HotItemCard from './HotItemCard.vue';
import { mapState } from 'vuex';
export default {
  name: 'HotItem',
  components: {
    HotItemCard,
  },
  computed: {
    ...mapState('mainStore', ['hotItemsList']),
  },
};
</script>

<style></style>
