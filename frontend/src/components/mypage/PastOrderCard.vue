<template>
  <v-card
    class="mx-auto"
    max-width="100%"
    :class="{cancel: item.status=='REFUND'}"
  >
    <v-card-title class="pb-0" style="font-weight: bold;">
      {{item.date|moment('YYYY년 MM월 DD일')}} 주문
    </v-card-title>

    <v-card-text class="my-3" style="display: flex; margin-bottom: 0 !important;">
      <div>
        <v-img
          :src="`data:image/jpeg;base64,${item.img}`"
          height="120"
          width="120"
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
        v-if="item.status == 'PAY'"
        outlined
        text
        @click="refund"
      >
        결제 취소
      </v-btn>
      <v-btn
        color="blue"
        v-else-if="item.status == 'SHIPPING'"
        outlined
        disblaed
        text
      >
        배송중
      </v-btn>
      <v-btn
        color="blue"
        v-else-if="item.status == 'DONE'"
        outlined
        text
        @click="createReview"
      >
        구매후기 쓰기
      </v-btn>
      <v-btn
        color="blue"
        v-else
        outlined
        text
      >
        취소 완료
      </v-btn>

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
import axios from 'axios'
export default {
  name: 'PastOrderCard',
  props: {
    item: Object,
  },
  methods: {
    createReview() {
      this.$router.push({ name: 'CreateReview', params: { orderId: this.item.orderId, optionId: this.item.optionId, productId: this.item.productId, userId: this.item.userId }})
    },
    refund() {
      this.item.status = 'REFUND';
      axios.put('http://i4d106.p.ssafy.io:8084/order', {
        orderId: this.item.orderId,
        checkDatetime: this.item.checkDatetime,
        orderStatus: 'REFUND',
        courier: this.item.courier,
        invoiceNum: this.item.invoiceNum,
        deliveryStartDatatime: this.item.deliveryStartDatatime,
        deliveryCompletionDatatime: this.item.deliveryCompletionDatatime,
        cancelMsg: this.item.cancelMsg,
      })
        .then(res=>{
          console.log(res)
          console.log(res.data)
        })
    },
    gotoDetail() {
      this.$router.push({ name: 'ItemDetail', params: {id: this.item.categoryId, productid: this.item.productId} })
    },
  },
}
</script>

<style scoped>
  .cancel {
    text-decoration: line-through;
    
  }
</style>