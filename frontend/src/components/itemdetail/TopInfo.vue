<template>
  <v-container class="mt-10">
    <v-row>
      <v-col cols="6">
        <TopInfoCarousels />
      </v-col>
      <v-col cols="6">
        <TopInfoCard />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TopInfoCarousels from './TopInfoCarousels.vue';
import TopInfoCard from './TopInfoCard.vue';
export default {
  name: 'TopInfo',
  components: {
    TopInfoCarousels,
    TopInfoCard,
  },
  props: {
    item: Object,
    sFiles: Array,
  },
};
</script>

<style></style>
