<template>
  <div>
    <h3 class="my-5">스테디 셀러 | <span style="font-size: 70%">꾸준히 잘 팔리는 상품</span></h3>    
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
        <SteadySellerCard
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
import SteadySellerCard from './SteadySellerCard.vue'
export default {
  name: 'SteadySeller',
  components: {
    SteadySellerCard,
  },
  data: () => ({
    productIds: [],
  }),
  methods: {
    getItems() {
      axios.get('http://i4d106.p.ssafy.io:8084/order/recommend/steadyseller')
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