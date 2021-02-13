<template>
  <v-card
    class="mx-auto"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <!-- carousels -->
    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-title>{{ review.title }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="review.rating"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ml-4">
          좋아요 : {{ review.reviewlikeCnt}}
        </div>
      </v-row>

      <div class="my-4 subtitle-1">
        {{ review.createdDate }}
      </div>

      <div>{{ review.content }}</div>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: 'ReviewCard',
  props: {
    review: Object,
  },
  imageUrl: [],
}
</script>

<style>

</style>