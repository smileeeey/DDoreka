<template>
  <v-app style="background-color: #f0f0f0; width: 100%">
    <v-container fluid style="width: 75rem">
      <v-row justify="center">
        <v-col cols="12">
          <v-img
            @click="$router.push({ name: 'Main' })"
            style="cursor: pointer"
            src="@/assets/eureka_logo(blank).png"
            class="my-auto"
            max-height="90"
            max-width="300"
          >
          </v-img>
          <input
            id="flag"
            type="text"
            v-model="e1"
            style="visibility: hidden"
          />
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <div class="box">
            <v-row>
              <v-col cols="4" style="display: flex">
                <v-icon x-large color="black" @click="e1 = 1">
                  mdi-cart-outline
                </v-icon>
                <p>
                  {{ article[e1] }}
                </p>
              </v-col>

              <v-col
                cols="5"
                offset="3"
                style="padding-left: 0; padding-right: 0"
              >
                <div>
                  <v-stepper style="margin-top: 0.25rem" v-model="e1">
                    <div>
                      <v-stepper-header>
                        <v-stepper-step step="1" :complete="e1 > 1">
                          장바구니
                        </v-stepper-step>
                        <v-divider></v-divider>
                        <v-stepper-step step="2" :complete="e1 > 2">
                          주문결제
                        </v-stepper-step>
                        <v-divider></v-divider>
                        <v-stepper-step step="3"> 주문완료 </v-stepper-step>
                      </v-stepper-header>
                    </div>
                  </v-stepper>
                </div>
              </v-col>
            </v-row>

            <!-- 1단계 -->
            <div v-show="e1 == 1">
              <CartList
                :items="cartItems"
                style="width: 100%"
                @updateTotalCost="updateTotalCost"
              />
              <div class="div-e1">
                <v-btn
                  style="
                    background-color: white;
                    color: #0275d8;
                    width: 200px;
                    height: 50px;
                    font-size: 1.5rem;
                    font-weight: bold;
                    border-color: #0275d8;
                    margin-right: 1rem;
                  "
                  @click="$router.push({ name: 'Main' })"
                >
                  계속 쇼핑하기
                </v-btn>
                <v-btn
                  style="
                    background-color: #0275d8;
                    color: white;
                    width: 200px;
                    height: 50px;
                    font-size: 1.5rem;
                    font-weight: bold;
                    margin-left: 1rem;
                  "
                  @click="e1 = 2"
                >
                  구매하기
                </v-btn>
              </div>
            </div>

            <!-- 2단계 -->
            <div v-show="e1 == 2">
              <BuyerInfo />

              <DestinationInfo/>

              <PaymentTable :totalCost="totalCost" />
              <div
                style="display: flex; justify-content: center; margin-top: 3rem"
              >
                <v-btn
                  style="
                    background-color: white;
                    color: #0275d8;
                    width: 200px;
                    height: 50px;
                    font-size: 1.5rem;
                    font-weight: bold;
                    border-color: #0275d8;
                    margin-right: 1rem;
                  "
                  @click="$router.push({ name: 'Main' })"
                >
                  계속 쇼핑하기
                </v-btn>
                <v-btn
                  style="
                    background-color: #0275d8;
                    color: white;
                    width: 200px;
                    height: 50px;
                    font-size: 1.5rem;
                    font-weight: bold;
                    margin-left: 1rem;
                  "
                  @click="buyitems"
                >
                  결제하기
                </v-btn>
              </div>
            </div>

            <!-- 결제 완료 창 -->
            <div v-show="e1 == 3">
              <v-card class="mx-auto paymentcard" outlined>
                <v-list-item three-line>
                  <v-list-item-content>
                    <v-list-item-title
                      class="headline mb-1"
                      style="text-align: center"
                    >
                      <strong>주문이 완료</strong> 되었습니다. 감사합니다 !
                    </v-list-item-title>
                    <v-list-item-subtitle style="text-align: center">{{
                      new Date()
                    }}</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item style="display: flex; justify-content: center">
                  <v-btn
                    style="
                      background-color: white;
                      color: #0275d8;
                      width: 200px;
                      height: 50px;
                      font-size: 1.5rem;
                      font-weight: bold;
                      border-color: #0275d8;
                      margin-right: 1rem;
                      margin-bottom: 1rem;
                    "
                    @click="$router.push({ name: 'Main' })"
                  >
                    계속 쇼핑하기
                  </v-btn>
                </v-list-item>
              </v-card>

              <v-divider></v-divider>
              <div v-for="(item, idx) in cartItems" :key="idx">
                <PaymentCompleteCard :item="item" />
              </div>
            </div>
          </div>
        </v-col>
      </v-row>
    </v-container>
    <Footer />
  </v-app>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";
import DestinationInfo from "../../components/cart/DestinationInfo.vue";
import BuyerInfo from "../../components/cart/BuyerInfo.vue";
import CartList from "../../components/cart/CartList.vue";
import PaymentTable from "../../components/cart/PaymentTable.vue";
import Footer from "../../components/core/Footer.vue";
import PaymentCompleteCard from "../../components/cart/PaymentCompleteCard.vue";

export default {
  components: {
    Footer,
    CartList,
    PaymentTable,
    BuyerInfo,
    DestinationInfo,
    PaymentCompleteCard,
  },
  name: "Cart",

  computed: {
    ...mapState("accountStore", ["wishlist","userData"]),
    ...mapState("paymentStore", ["cartItems"]),
  },

  data() {
    return {
      e1: 1,
      article: [null, "장바구니", "주문결제", "주문완료"],
      totalCost: 0,
      flag: false,
      name: "",
    };
  },

  methods: {
    ...mapActions('paymentStore',['FETCH_CART','ORDER_FROM_CART']),
    ...mapMutations('accountStore',['SET_DELETE_CART']),
    updateTotalCost(cost) {
      this.totalCost = cost;
    },
    buyitems() {
      const IMP = window.IMP;
      IMP.request_pay(
        {
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: "merchant_" + new Date().getTime(),
          name: "EUREKA: 주문",
          amount: this.totalCost/100,
          buyer_email: this.userData.email,
          buyer_name: this.userData.name,
          buyer_tel: this.userData.phone,
          buyer_addr: "서울특별시 강남구 삼성동",
          buyer_postcode: "123-456",
        },
        function (rsp) {
          var msg = "결제가 완료되었습니다.";
          if (rsp.success) {
            msg += "고유ID : " + rsp.imp_uid;
            msg += "상점 거래ID : " + rsp.merchant_uid;
            msg += "결제 금액 : " + rsp.paid_amount;
            msg += "카드 승인번호 : " + rsp.apply_num;
            let FLAG = document.getElementById("flag");
            FLAG.value = 3;
            FLAG.dispatchEvent(new Event("input"));
          } else {
            msg = "결제에 실패하였습니다.";
            msg += "에러내용 : " + rsp.error_msg;
          }
          alert(msg);
        }
      );
    },

    orderFromCartItems(){
      let orders = this.getOrders();
      let shoppings = this.getShoppings();
      console.log(shoppings.length);
      console.log(typeof shoppings)
      
      if(shoppings.length !== 0) {
        this.ORDER_FROM_CART({orders, shoppings});
      }
    },

    getShoppings(){
      let shoppings = [];

      for (let i = 0; i < this.cartItems.length; i++) {
        if (this.cartItems[i].select == true) {
          console.log("선택이용");
          shoppings.push({
            cartId : this.cartItems[i].cartId,
            quantity : this.cartItems[i].amount,
            optionId : `${this.cartItems[i].optionId}`,
            productId : `${this.cartItems[i].productId}`,
            sellerId : `${this.cartItems[i].storeId}`,
          });

          // 카트에서도 삭제
          this.SET_DELETE_CART(this.cartItems[i].cartId);
        }
      }

      return shoppings;
    },

    getOrders(){
      let orders = {};
      
      let address = document.getElementById("uniqueaddress").textContent;
      let name = document.getElementById("uniquename").textContent;
      let comment = document.getElementById("uniquecomment").textContent;
      let phonenumber = document.getElementById("uniquephonenumber").textContent;
      
      orders.userId = this.userData.userId;
      orders.addressMain = address.split(", ")[0];
      orders.addressSub = address.split(", ")[0];
      orders.recipientName = name;
      orders.zipcode = "0";
      orders.deliveryMsg = comment,
      orders.recipientPhone = phonenumber;
      orders.paymentMethod = "Online";

      return orders;
    },

    getCartItems(){
      let products =[];
      let options =[];
      console.log(this.wishlist.length);
      for(let i=0; i<this.wishlist.length; i++){
        products.push(this.wishlist[i].productId);
        options.push(this.wishlist[i].optionId);
      }

      this.FETCH_CART({productIds : products, optionIds : options, wishlist: this.wishlist});
    }
  },
  watch: {
    e1: function () {
      console.log(this.wishlist);
      if (this.e1 == 3) this.orderFromCartItems();
    },
  },
  created() {
    this.getCartItems();
  },
};
</script>

<style scoped>
.paymentcard {
  border-top: 3px solid #0275d8;
  border-bottom: 3px solid #0275d8;
  border-left: 0;
  border-right: 0;
}

.box {
  border: 1px solid #e0e0e0;
  border-top: 2px solid #8a8a8a;
  border-bottom: 2px solid #8a8a8a;
  margin: 0 auto 70px;
  padding: 40px 39px;
  background: #fff;
}

.box p {
  font-size: 1.75rem;
  margin-top: 1rem;
  margin-bottom: 0;
  font-weight: bold;
}

>>> .v-stepper {
  box-shadow: none !important;
}

>>> .v-stepper__step {
  padding-top: 0;
}

>>> .v-divider {
  padding-top: 0;
  padding-bottom: 5%;
}

.v-footer >>> hr {
  padding: 0;
}

payBtn {
  background-color: #0275d8;
  color: white;
  width: 200px;
  height: 50px;
  font-size: 1.5rem;
  font-weight: bold;
  margin-left: 1rem;
}

.div-e1 {
  display: flex;
  justify-content: center;
  margin-top: 3rem;
}
</style>