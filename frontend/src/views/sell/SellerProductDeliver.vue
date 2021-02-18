<template>
  <v-container fluid>
    <v-row>
      <v-col
        cols="12"
        md="4"
      >
        <v-card>
          <v-card-text>
            <v-card-title>주문접수</v-card-title>
            <div v-if="orderlist.length > 0">
              <div v-for="(item, idx) in orderlist" :key="idx">
                <div class="box orderbox">
                  <div>
                    <p>주문시간 : {{item.datetime|moment('YYYY년 MM월 DD일 HH시 mm분 ss초')}}</p>
                    <p>{{item.recipientName}} {{item.recipientPhone}}</p>
                    <p>{{item.addressMain}}, {{item.addressSub}}</p>
                    <p>{{item.deliveryMsg}}</p>
                  </div>
                  <v-col
                    cols="12"
                    class="text-right"
                  >
                    <v-btn color="primary" class="mr-0" @click="deliveryStart(idx)">
                      배송시작
                    </v-btn>
                  </v-col>
                </div>
              </div>
            </div>
            <div v-else>
              <div>
                <span>새로 접수된 주문이 없습니다.</span>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <v-card>
          <v-card-text>
            <v-card-title>배송시작</v-card-title>
            <div v-if="deliverList.length > 0">
              <div v-for="(item, idx) in deliverList" :key="idx">
                <div class="box deliverbox">
                  <div>
                    <p>주문시간 : {{item.datetime|moment('YYYY년 MM월 DD일 HH시 mm분 ss초')}}</p>
                    <p>{{item.recipientName}} {{item.recipientPhone}}</p>
                    <p>{{item.addressMain}}, {{item.addressSub}}</p>
                    <p>{{item.deliveryMsg}}</p>
                  </div>
                  <v-col
                    cols="12"
                    class="text-right"
                  >
                    <v-btn color="primary" class="mr-0" @click="deliveryComplete(idx)">
                      배송완료
                    </v-btn>
                  </v-col>
                </div>
              </div>
            </div>
            <div v-else>
              <div>
                <span>새로 접수된 주문이 없습니다.</span>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <v-card>
          <v-card-text>
            <v-card-title>배송완료</v-card-title>
            <div v-if="deliverComplete.length > 0">
              <div v-for="(item, idx) in deliverComplete" :key="idx">
                <div class="box completebox">
                  <div>
                    <p>주문시간 : {{item.datetime|moment('YYYY년 MM월 DD일 HH시 mm분 ss초')}}</p>
                    <p>{{item.recipientName}} {{item.recipientPhone}}</p>
                    <p>{{item.addressMain}}, {{item.addressSub}}</p>
                    <p>{{item.deliveryMsg}}</p>
                  </div>
                </div>
              </div>
            </div>
            <div v-else>
              <div>
                <span>새로 접수된 주문이 없습니다.</span>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>


    </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  name: 'SellerProductDeliver',
  data: () => ({
    orderlist: [

    ],
    deliverList: [
     
    ],
    deliverComplete: [
  
    ]
  }),
  methods: {
    deliveryStart: function (idx) {
      this.deliverList.push(this.orderlist[idx])
      axios.put('http://i4d106.p.ssafy.io:8084/order', {
        'orderId': this.orderlist[idx].orderDetail.orderId,
        'checkDatetime': this.orderlist[idx].orderDetail.checkDatetime,
        'orderStatus': 'SHIPPING',
        'courier': this.orderlist[idx].orderDetail.courier,
        'invoiceNum': this.orderlist[idx].orderDetail.invoiceNum,
        'deliveryStartDatatime': this.orderlist[idx].orderDetail.deliveryStartDatatime,
        'deliveryCompletionDatatime': this.orderlist[idx].orderDetail.deliveryCompletionDatatime,
        'cancelMsg': this.orderlist[idx].orderDetail.cancelMsg,
      })
        .then(res => {
          console.log(res)
          console.log(res.data)
        })
      this.orderlist.splice(idx, 1)
    },
    deliveryComplete: function (idx) {
      this.deliverComplete.push(this.deliverList[idx])
      axios.put('http://i4d106.p.ssafy.io:8084/order', {
        'orderId': this.deliverList[idx].orderDetail.orderId,
        'checkDatetime': this.deliverList[idx].orderDetail.checkDatetime,
        'orderStatus': 'DONE',
        'courier': this.deliverList[idx].orderDetail.courier,
        'invoiceNum': this.deliverList[idx].orderDetail.invoiceNum,
        'deliveryStartDatatime': this.deliverList[idx].orderDetail.deliveryStartDatatime,
        'deliveryCompletionDatatime': this.deliverList[idx].orderDetail.deliveryCompletionDatatime,
        'cancelMsg': this.deliverList[idx].orderDetail.cancelMsg,
      })
        .then(res => {
          console.log(res)
          console.log(res.data)
        })
      this.deliverList.splice(idx, 1)
    },
  },
  computed: {
    ...mapState([
      'seller',
    ])
  },
  created() {
    axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/status/0`)
      .then(res => {
        this.orderlist = res.data.data
        axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/status/1`)
          .then(delres => {
            this.deliverList = delres.data.data
            axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/status/2`)
              .then(comres => {
                this.deliverComplete = comres.data.data
              })
          })
      })
  }
}
</script>

<style scoped>
  .box {
    display: block;
    font-size: 16px;
    margin-bottom: 16px;
    padding: 16px;
    position: relative;
    transition: .3s cubic-bezier(.25, .8, .5, 1);
    border-radius: 4px;
  }

  .orderbox {
    background-color: #f8f8f8;
    color: black;
  }

  .deliverbox {
    background-color:#f1f1f1;
    color: black;
  }

  .completebox {
    background-color: #C7FFD8;
    color: black;
    text-decoration: line-through;
  }
</style>