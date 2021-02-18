<template>
  <div>
    <h3 class="mb-3">내가 등록한 구매후기</h3>
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
import { mapState } from 'vuex'
import ReviewCard from './ReviewCard'
export default {
  name: 'ProductReview',
  components: {
    ReviewCard
  },
  data: () => ({
    reviews: [],
  }),
  computed: {
    ...mapState([
      'userId',
    ])
  },
  methods: {
    getReviews() {
      axios.get(`http://i4d106.p.ssafy.io:8083/review/get/${this.userId}`)
      .then(res => {
        console.log(res)
        this.reviews = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  created() {
    this.getReviews()
  }
}
</script>

<style>

</style>