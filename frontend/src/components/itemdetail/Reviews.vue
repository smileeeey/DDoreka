<template>
  <div>
    <h3 class="mt-10 mb-3">상품평</h3>
    <!-- <v-row>
      <v-col 
        v-for="(review, idx) in reviews"
        :key="idx"
        cols="4"
      >
        <ReviewCard :review="review" />
      </v-col>
    </v-row> -->
    <v-slide-group
      class="pa-2"
      show-arrows
    >
      <ReviewCard
        v-for="(review, idx) in reviews"
        :key="idx"
        :review="review"
      />
    </v-slide-group>    
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
import ReviewCard from './ReviewCard.vue'
export default {
  name: 'Reviews',
  components: {
    ReviewCard,
  },
  data: () => ({
    nowUserId: 'notLogin',
    productId: 1,
    reviews: [],
  }),
  methods: {
    getReviews() {
      if (this.userId != null) {
        this.nowUserId = this.userId
      }
      // console.log(this.nowUserId)
      axios.get(`http://i4d106.p.ssafy.io:8083/review/get/${this.productId}/${this.nowUserId}`)
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
  computed: {
    ...mapState ([
      'userId'
    ])
  },
  created() {
    this.productId = this.$route.params.productid
    this.getReviews()
  },
}
</script>

<style>

</style>