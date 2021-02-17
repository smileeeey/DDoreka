<template>
  <div>
    <h3 class="my-5">요즘 뜨는 상품 | <span style="font-size: 70%">최근 가장 HOT한 상품</span></h3>
    <v-sheet
      class="mx-auto"
      flat
      outlined
      rounded
    >
      <v-slide-group
        class="pa-2"
        show-arrows
      >
        <HotItemCard
          v-for="(productId, idx) in productIds"
          :productId="productId"
          :key="idx"
        />
      </v-slide-group>
    </v-sheet>    
  </div>
</template>

<script>
import axios from 'axios'
import HotItemCard from './HotItemCard.vue'
export default {
  name: 'HotItem',
  components: {
    HotItemCard,
  },
  data: () => ({
    productIds: [],
  }),
  methods: {
    getItems() {
      axios.get('http://i4d106.p.ssafy.io:8084/order/recommend/hotproduct')
      .then(res => {
        // console.log(res.data.data)
        this.productIds = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    this.getItems()
  }
}
</script>

<style>

</style>