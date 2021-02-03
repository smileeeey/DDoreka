<template>
  <v-container>
    <v-row>
      <v-col class="pa-0 text-center font-weight-bold" cols=2>{{category.name}}</v-col>
      <v-col class="pa-0" cols=4>
        <DetailBigItem :bigItems="bigItems" />
      </v-col>
      <v-col class="pa-0" cols=6>
        <DetailSmallItem :smallItems="smallItems" />
      </v-col>
    </v-row>    
  </v-container>
</template>

<script>
import DetailBigItem from './DetailBigItem.vue'
import DetailSmallItem from './DetailSmallItem.vue'
export default {
  name: 'CategoryRecommendDetail',
  components: {
    DetailBigItem,
    DetailSmallItem,
  },
  props: {
    category: Object,
  },
  data: () => ({
    bigItems: [],
    smallItems: [],
  }),
  created() {
    this.bigItems = this.category.bigItems
    this.smallItems = this.category.smallItems
  }
}
</script>

<style>

</style>