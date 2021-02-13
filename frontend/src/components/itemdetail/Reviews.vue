<template>
  <div>
    <h3 class="mt-10 mb-3">상품평</h3>
      <v-row>
        <v-col 
          v-for="(review, idx) in reviews"
          :key="idx"
          cols="4"
        >
          <ReviewCard :review="review" />
        </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import ReviewCard from './ReviewCard.vue'
export default {
  name: 'Reviews',
  components: {
    ReviewCard,
  },
  data: () => ({
    userId: 'notLogin',
    productId: 1,
    reviews: [],
  }),
  methods: {
    getReviews() {
      axios.get(`http://i4d106.p.ssafy.io:8083/review/get/${this.productId}/${this.userId}`)
      .then(res => {
        // console.log(res.data.data)
        this.reviews = res.data.data
        // console.log(this.reviews)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    // this.productId = this.$route.params.productid
    this.getReviews()
  },
}
</script>

<style>

</style>