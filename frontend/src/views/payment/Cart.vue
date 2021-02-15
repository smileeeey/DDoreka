<template>
  <v-app 
    style="background-color: #f0f0f0; width: 100%;"
  >
  <v-container fluid style="width: 75rem;">
      <v-row justify="center">
        <v-col cols="12">
          <v-img
            @click="$router.push({ name: 'Main' })"
            style="cursor: pointer;"
            src="@/assets/eureka_logo(blank).png"
            class="my-auto"
            max-height="90"
            max-width="300"
          >
          </v-img>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <div class="box">
            <v-row>
              
              <v-col cols="4" style="display: flex">
                <v-icon x-large color="black">
                  mdi-cart-outline
                </v-icon>
                <p style="font-size: 1.75rem; margin-top: 1rem; margin-bottom: 0; font-weight: bold;">{{article[e1]}}</p>
              </v-col>



              <v-col cols="5" offset="3" style="padding-left: 0; padding-right: 0;">
                <div>
                <v-stepper style="margin-top: 0.25rem;" v-model="e1">
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
                    <v-stepper-step step="3">
                      주문완료
                    </v-stepper-step>
                  </v-stepper-header>
                  </div>
                  
                </v-stepper>
                </div>
              </v-col>
            </v-row>
            <div v-if="e1==1">

              <CartList 
                :items="items"
                style="width: 100%;" 
                @updateTotalCost="updateTotalCost"
              />
              <div style="display: flex; justify-content: center; margin-top: 3rem;">
                <v-btn style="background-color: white; color: #0275d8; width: 200px; height: 50px;
                  font-size: 1.5rem; font-weight: bold; border-color: #0275d8; margin-right: 1rem;"
                  @click="$router.push({ name: 'Main' })"
                >
                  계속 쇼핑하기
                </v-btn>
                <v-btn style="background-color: #0275d8; color: white; width: 200px; height: 50px;
                  font-size: 1.5rem; font-weight: bold; margin-left: 1rem;"
                  @click="e1=2"
                >
                  구매하기
                </v-btn>
              </div>
            </div>
            <div v-else-if="e1==2">
              <BuyerInfo />
              <DestinationInfo />
              <PaymentTable :totalCost="totalCost" />
              <div style="display: flex; justify-content: center; margin-top: 3rem;">
                <v-btn style="background-color: white; color: #0275d8; width: 200px; height: 50px;
                  font-size: 1.5rem; font-weight: bold; border-color: #0275d8; margin-right: 1rem;"
                  @click="$router.push({ name: 'Main' })"
                >
                  계속 쇼핑하기
                </v-btn>
                <v-btn style="background-color: #0275d8; color: white; width: 200px; height: 50px;
                  font-size: 1.5rem; font-weight: bold; margin-left: 1rem;"
                  @click="e1=3"
                >
                  결제하기
                </v-btn>
              </div>
            </div>
            <div v-else>
              <v-card
                class="mx-auto paymentcard"
                outlined
              >
                <v-list-item three-line>
                  <v-list-item-content>
                    <v-list-item-title class="headline mb-1" style="text-align: center;">
                      <strong>주문이 완료</strong> 되었습니다. 감사합니다 !
                    </v-list-item-title>
                    <v-list-item-subtitle style="text-align: center;">{{ new Date() }}</v-list-item-subtitle>
                  </v-list-item-content>
                </v-list-item>
                <v-list-item style="display: flex; justify-content: center;">
                  <v-btn style="background-color: white; color: #0275d8; width: 200px; height: 50px;
                    font-size: 1.5rem; font-weight: bold; border-color: #0275d8; margin-right: 1rem;
                    margin-bottom: 1rem;
                    "
                    @click="$router.push({ name: 'Main' })"
                  >
                    계속 쇼핑하기
                  </v-btn>
                </v-list-item>



              </v-card>
              
              <v-divider></v-divider>
              <div v-for="(item, idx) in items" :key="idx">
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
import { mapState } from 'vuex'
import axios from 'axios'
import DestinationInfo from '../../components/cart/DestinationInfo.vue'
import BuyerInfo from '../../components/cart/BuyerInfo.vue'
import CartList from '../../components/cart/CartList.vue'
import PaymentTable from '../../components/cart/PaymentTable.vue'
import Footer from '../../components/core/Footer.vue'
import PaymentCompleteCard from '../../components/cart/PaymentCompleteCard.vue'
export default {
  components: { Footer, CartList, PaymentTable, BuyerInfo, DestinationInfo, PaymentCompleteCard },
  name: 'Cart',
  data () {
    return {
      e1: 1,
      article: [null, '장바구니', '주문결제', '주문완료'],
      totalCost: 0,
      items: [
      {
        img: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4fcd/162c5d78b17078cfb5c2759e809c320b051b2989d2e6256bbab43cee3393.jpg',
        name: '민트위니 여성용 미키기모셋',
        cost: '22900',
        amount: 1,
        select: true,
      },
      {
        img: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86815902988186-18c5ec56-8775-4476-ae6f-d1bae7e2dcc5.jpg',
        name: '캐럿 여성 와이드 밴딩 팬츠',
        cost: '9900',
        amount: 1,
        select: true,
      },
      {
        img: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/358954270343111-663ca6b1-cd2c-4c03-9228-1ffbf43b93cb.jpg',
        name: '루나걸 여성용 니트',
        cost: '22900',
        amount: 1,
        select: true,
      },
    ]
    }
  },

  methods: {
    updateTotalCost: function (cost) {
      this.totalCost = cost
    }
  },
  computed: {
    ...mapState([
      'wishlist',
    ])
  },
  created: function () {
    let tmpitems = [];
    for (let i=0; i<this.wishlist.length; i++) {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${this.wishlist[i].productId}`)
        .then(detailres => {
          let productName = detailres.data.data.name
          if (detailres.data.data.images.length > 0) {
            axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe/${detailres.data.data.images[0].fileId}`)
              .then(fileres => {
                console.log('getimage')
                tmpitems.push({
                  img: fileres.data.data.imageBytes,
                  name: productName,
                  cost: 1000,
                  amount: this.wishlist[i].quantity,
                  select: true
                })
              })
          } else {
            tmpitems.push({
              img: null,
              name: productName,
              cost: 1000,
              amount: this.wishlist[i].quantity,
              select: true
            })
          }
        })
    }
    this.items = tmpitems
  }
}
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
</style>