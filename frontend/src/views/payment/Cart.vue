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
              <CartList style="width: 100%; margin-bottom: 3rem;" />
              <div style="display: flex; justify-content: center;">
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
              <h1>This is Payment Page</h1>
              <div style="display: flex; justify-content: center;">
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
                    <v-list-item-subtitle style="text-align: center;">주문시간 : {{ new Date() }}</v-list-item-subtitle>
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
            </div>
          </div>
        </v-col>
      </v-row>
  </v-container>
  <Footer />
  </v-app>
</template>

<script>
import CartList from '../../components/cart/CartList.vue'
import Footer from '../../components/core/Footer.vue'
export default {
  components: { Footer, CartList },
  name: 'Cart',
  data () {
    return {
      e1: 1,
      article: [null, '장바구니', '주문결제', '주문완료'],
    }
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
    height: 800px;
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