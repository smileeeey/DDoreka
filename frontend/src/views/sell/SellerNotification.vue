<template>
  <v-container
    fluid
  >
    <v-row>
      <v-col
        cols="12"
        md="6"
      >
        <v-card>
          <v-card-text>
            <v-card-title>등록된 후기</v-card-title>
            <div v-if="reviews.length > 0">
              <div v-for="(review, idx) in reviews" :key="idx">
                <div
                  class="box colorbox"
                >
                  <div>{{review.productname}}
                    <v-rating
                      style="display: inline"
                      color="yellow"
                      readonly
                      :value="review.rating"
                    ></v-rating>
                    <v-icon @click="deleteReview(idx)" style="position: absolute; right: 25px;">mdi-close</v-icon>
                  </div>
                  
                  <span>{{review.content}}</span>
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
      

      <v-col
        cols="12"
        md="6"
      >
        <v-card>
          <v-card-text>
            <v-card-title>등록된 주문</v-card-title>
            <div v-if="orders.length > 0">
              <div v-for="(order, idx) in orders" :key="idx">
                
                <div
                  class="box successbox"
                  :class="{ cancelbox: order.cancel }"
                >
                  <v-icon @click="deleteOrder(idx)" style="position: absolute; right: 25px;">mdi-close</v-icon>
                  <span>{{order.time.getFullYear()}}.{{order.time.getMonth()+1}}.{{order.time.getDate()}} {{order.time.getHours()}}:{{order.time.getMinutes()}}:{{order.time.getSeconds()}}</span>
                  <span style="margin-left: 20px;">{{order.ordercode}}</span>

                  <div>{{order.productname}}, {{order.amount}} 개
                  </div>
                  
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
export default {
  name: 'SellerNotification',
  data: () =>({
    reviews: [
      {
        color: 'success',
        content: '잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다잘쓰고있습니다',
        rating: 5,
        productname: '루나걸 여성용 니트'
      },
      {
        color: 'error',
        content: 'ipsum lorem',
        rating: 4,
        productname: '캐럿 여성 와이드 밴딩 팬츠'
      },
      {
        color: 'warning',
        content: 'ㅋㅋ',
        rating: 3,
        productname: '루나걸 여성용 니트'
      },
    ],

    orders: [
      {
        cancel: false,
        productname: '루나걸 여성용 니트',
        amount: 3,
        time: new Date(),
        ordercode: 'AC1398E0123E',
      },
      {
        cancel: true,
        productname: '루나걸 여성용 니트',
        amount: 3,
        time: new Date(),
        ordercode: 'AC1398E0123E',
      },
      {
        cancel: false,
        productname: '캐럿 여성 와이드 밴딩 팬츠',
        amount: 1,
        time: new Date(),
        ordercode: '92103EN1WE',
      },
    ]
  }),
  methods: {
    deleteReview(idx) {
      // delete noti axios request
      this.reviews.splice(idx, 1)
    },
    deleteOrder(idx) {
      // delete noti axios request
      console.log(idx)
      this.orders.splice(idx, 1)
    },
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
    color:#aa2222;
  }
</style>