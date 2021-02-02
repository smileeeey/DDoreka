<template>
  <div>
    <v-row>
      <v-col cols=6>
        <v-row class="d-flex flex-column">
          <v-col cols="2">
            {{category.name}}
          </v-col>
          <DetailBigItem :bigItems="bigItems" />
        </v-row>
      </v-col>
      <v-col cols=6>
        소상품이미지
      </v-col>
    </v-row>    
  </div>
</template>

<script>
import DetailBigItem from './DetailBigItem.vue'
export default {
  name: 'CategoryRecommendDetail',
  components: {
    DetailBigItem,
  },
  props: {
    category: Object,
  },
  data: () => ({
    bigItems: [],
  }),
  created() {
    this.bigItems = this.category.bigItems
  }
}
</script>

<style>

</style>