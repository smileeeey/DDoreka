<template>
  <v-card
    class="mx-auto"
    max-width="100%"
  >
    <v-card-title class="pb-0" style="font-weight: bold;">
      {{item.date|moment('YYYY년 MM월 DD일')}} 주문 취소
    </v-card-title>

    <v-card-text class="my-3" style="display: flex; margin-bottom: 0 !important;">
      <div>
        <v-img
          :src="`data:image/jpeg;base64,${item.img}`"
          max-height="120"
          max-width="120"
        ></v-img>
      </div>
      <div>
        <v-card-title
          style="font-size: 1.25rem; color: black; text-decoration: line-through;"
        >{{item.name}}</v-card-title>
        <v-card-subtitle
          style="font-size: 1rem; text-decoration: line-through;"
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
        @click="gotoDetail"
      >
        상품 보러가기
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: 'CancelOrderCard',
  props: {
    item: Object,
  },
  methods: {
    gotoDetail() {
      this.$router.push({ name: 'ItemDetail', params: {id: this.item.categoryId, productid: this.item.productId} })
    },
  }
}
</script>

<style>

</style>