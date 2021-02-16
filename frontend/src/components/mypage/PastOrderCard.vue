<template>
  <v-card
    class="mx-auto"
    max-width="100%"
  >
    <v-card-title class="pb-0" style="font-weight: bold;">
      {{item.date.getUTCFullYear()}}년 {{item.date.getUTCMonth()+1}}월 {{item.date.getUTCDate()}}일 주문
    </v-card-title>

    <v-card-text class="my-3" style="display: flex; margin-bottom: 0 !important;">
      <div>
        <v-img
          :src="item.img"
          max-height="120"
          max-width="120"
        ></v-img>
      </div>
      <div>
        <v-card-title
          style="font-size: 1.25rem; color: black;"
        >{{item.name}}</v-card-title>
        <v-card-subtitle
          style="font-size: 1rem;"
        >
          {{item.cost|comma}}원 · {{item.amount}}개
        </v-card-subtitle>
      </div>
      
    </v-card-text>

    <v-card-actions style="padding-top: 0;">
      <v-btn
        color="blue"
        outlined
        text
        @click="createReview"
      >
        구매후기 쓰기
      </v-btn>

      <v-btn
        color="blue"
        outlined
        text
      >
        장바구니 담기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'PastOrderCard',
  props: {
    item: Object,
  },
  methods: {
    createReview() {
      this.$router.push({ path: 'createreview' })
    }
  }
}
</script>

<style>

</style>