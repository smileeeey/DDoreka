<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12" md="6">
        <v-card>
          <v-card-text>
            <v-card-title>등록된 후기</v-card-title>
            <div v-if="reviews.length > 0">
              <div v-for="(review, idx) in reviews" :key="idx">
                <div class="box colorbox">
                  <p>{{ review.review.createdDate | moment('YYYY년 MM월 DD일 HH시 mm분 ss초') }}</p>
                  <div>
                    {{ review.product.name }}
                    <v-rating style="display: inline" color="yellow" readonly :value="review.review.rating"></v-rating>
                    <v-icon @click="deleteReview(idx)" style="position: absolute; right: 25px;">mdi-close</v-icon>
                  </div>
                  <p>제목 : {{ review.review.title }}</p>
                  <span>내용 : {{ review.review.content }}</span>
                </div>
              </div>
            </div>
            <div v-else>
              <div class="box colorbox">
                <span>새로 업데이트 된 리뷰가 없습니다.</span>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" md="6">
        <v-card>
          <v-card-text>
            <v-card-title>등록된 주문</v-card-title>
            <div v-if="orders.length > 0">
              <div v-for="(order, idx) in orders" :key="idx">
                <div class="box successbox" :class="{ cancelbox: order.cancel }">
                  <v-icon @click="deleteOrder(idx)" style="position: absolute; right: 25px;">mdi-close</v-icon>
                  <strong>{{ order.time | moment('YYYY년 MM월 DD일 HH시 mm분 ss초') }}</strong>
                  <strong style="margin-left: 20px;">{{ order.csname }} ({{ order.csphone }})</strong>

                  <div>
                    <strong>{{ order.productname }} {{ order.amount }} 개</strong>
                  </div>
                  <strong>[{{ order.ordercode | comma }}원] 계산완료</strong>
                </div>
              </div>
            </div>
            <div v-else>
              <div class="box colorbox">
                <span>새로 업데이트 된 주문이 없습니다.</span>
              </div>
            </div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import httporder from '@/util/http-order.js';
import review from '@/util/http-review.js';
import { mapState } from 'vuex';
export default {
  name: 'SellerNotification',
  data: () => ({
    reviews: [],

    orders: [],
  }),
  computed: {
    ...mapState(['seller']),
  },
  methods: {
    deleteReview(idx) {
      this.reviews.splice(idx, 1);
    },
    deleteOrder(idx) {
      let now = new Date();
      let nowv =
        now.getFullYear() + '-' + now.getMonth() + 1 + '-' + now.getDate() + '-' + now.getHours() + '-' + now.getMinutes() + '-' + now.getSeconds();

      httporder
        .put('/order', {
          orderId: this.orders[idx].orderDetail.orderId,
          checkDatetime: nowv,
          orderStatus: this.orders[idx].orderDetail.orderStatus,
          courier: this.orders[idx].orderDetail.courier,
          invoiceNum: this.orders[idx].orderDetail.invoiceNum,
          deliveryStartDatatime: this.orders[idx].orderDetail.deliveryStartDatatime,
          deliveryCompletionDatatime: this.orders[idx].orderDetail.deliveryCompletionDatatime,
          cancelMsg: this.orders[idx].orderDetail.cancelMsg,
        })
        .then((res) => {
          console.log(res);
          console.log(res.data);
        })
        .then((res) => {
          console.log(res);
          console.log(res.data);
        });
      this.orders.splice(idx, 1);
    },
  },
  created() {
    let dataArray = [];
    httporder.get(`/order/sellerid/${this.seller.id}/unchecked`).then((res) => {
      res.data.data.forEach((order) => {
        let csname = order.recipientName.substr(0, 1) + '*' + order.recipientName.substr(2, 1);
        let csphone = '';
        if (order.recipientPhone.length > 0) {
          csphone = order.recipientPhone.substr(0, 3) + '-****-' + order.recipientPhone.substr(9, 4);
          // console.log(order.recipientPhone.substr(0, 3) + '-****-' + order.recipientphone.substr(9, 4))
        }
        let year = order.datetime.substr(0, 4);
        let month = order.datetime.substr(5, 2);
        let day = order.datetime.substr(8, 2);
        let hour = order.datetime.substr(11, 2);
        let minute = order.datetime.substr(14, 2);
        let second = order.datetime.substr(17, 2);

        product.get(`/product/detail/${order.productId}`).then((resp) => {
          var productname = resp.data.data.name;
          for (let k = 0; k < resp.data.data.options.length; k++) {
            if (resp.data.data.options[k].optionId == order.optionId) {
              var optionprice = resp.data.data.options[k].price;
              var optionname = resp.data.data.options[k].name;
              break;
            }
          }
          dataArray.push({
            orderId: order.id,
            cancel: false,
            productname: productname + '  [' + optionname + ']',
            amount: order.quantity,
            time: new Date(year, month - 1, day, Number(hour) + 9, minute, second),
            ordercode: optionprice * order.quantity,
            csname: csname,
            csphone: csphone,
            orderDetail: order.orderDetail,
          });
          dataArray.sort(function(a, b) {
            return Number(b.orderId) - Number(a.orderId);
          });
        });
      });
    });
    this.orders = dataArray;
    let reviewArray = [];
    product.get(`/product/seller/all/${this.seller.id}`).then((res) => {
      let A = '';
      res.data.data.forEach((item) => {
        (A += item.id), (A += ',');
      });
      review.get(`/review/getbyproductids?productIds=${A}`).then((resp) => {
        let reviewCnt = resp.data.data.length;
        for (let i = 0; i < reviewCnt; i++) {
          product.get(`/product/detail/${resp.data.data[i].productId}`).then((pres) => {
            reviewArray.push({
              product: pres.data.data,
              review: resp.data.data[i],
            });
          });
        }
        this.reviews = reviewArray;
      });
    });
  },
};
</script>

<style scoped>
.box {
  display: block;
  font-size: 16px;
  margin-bottom: 16px;
  padding: 16px;
  position: relative;
  transition: 0.3s cubic-bezier(0.25, 0.8, 0.5, 1);
  border-radius: 4px;
}

.colorbox {
  background-color: beige;
  color: black;
  font-weight: bold;
}

.successbox {
  background-color: lightgreen;
}

.cancelbox {
  background-color: #f1f1f1;
  text-decoration: line-through;
  color: #aa2222;
}
</style>
