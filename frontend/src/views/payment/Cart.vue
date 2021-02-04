<template>
  <v-app 
    style="background-color: #f0f0f0; width: 100%;"
  >
  <v-container fluid style="width: 60%;">
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
              <v-col cols="3" style="display: flex">
                <v-icon x-large color="black">
                  mdi-cart-outline
                </v-icon>
                <p style="font-size: 30px; margin-bottom: 0; font-weight: bold;">장바구니</p>
              </v-col>
              <v-col cols="5" offset="4">
                <div>

                <v-stepper>
                  <v-stepper-header>
                    <v-stepper-step step="1">
                      Ad unit details
                    </v-stepper-step>

                    <v-divider></v-divider>

                    <v-stepper-step step="2">
                      Ad sizes
                    </v-stepper-step>

                    <v-divider></v-divider>

                    <v-stepper-step step="3">
                      Ad templates
                    </v-stepper-step>
                  </v-stepper-header>
                </v-stepper>
                </div>
              </v-col>
            </v-row>
          </div>
        </v-col>

      </v-row>
  </v-container>
    <Footer />
  </v-app>
</template>

<script>
import Footer from '../../components/core/Footer.vue'
export default {
  components: { Footer },
  name: 'Cart',
}
</script>

<style scoped>
  .box {
    height: 800px;
    border: 1px solid #e0e0e0;
    border-top: 2px solid #8a8a8a;
    border-bottom: 2px solid #8a8a8a;
    margin: 0 auto 70px;
    padding: 40px 39px;
    background: #fff;
  }

  /* >>> .v-stepper__step {
    padding: 12px !important;
  } */
</style>