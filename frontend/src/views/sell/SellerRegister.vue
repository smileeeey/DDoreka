<template>
  <v-stepper v-model="e1">
    <v-col class="px-5" cols="8" offset="2">
      <v-stepper-header>
        <v-stepper-step
          :complete="e1 > 1"
          step="1"
        >
          카테고리 등록
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e1 > 2"
          step="2"
        >
          상품명/재고 등록
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          :complete="e1 > 3"
          step="3"
        >
          이미지 등록
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step step="4">
          최종 등록
        </v-stepper-step>
      </v-stepper-header>
    </v-col>

    <v-stepper-items>
      <v-stepper-content step="1">
        <RegisterCategory
          @maincode="val => maincode = val"
          @subcode="val => subcode = val"
          @detailcode="val => detailcode = val"
        />
        <v-col
          cols="12"
          class="text-right"
        >
          <div>
            <v-btn
              color="primary"
              @click="e1 = 2"
              style="margin-right: 5rem;"
            >
              다음
            </v-btn>
          </div>
        </v-col>
      </v-stepper-content>

      <v-stepper-content step="2">
        <RegisterProductInfo />
        <v-col
          cols="12"
          class="text-right"
        >
          <div>
            <v-btn text
              style="border: 1px solid #1976d2"
              @click="e1 = 1"
              class="mx-5"
            >
              이전
            </v-btn>
            <v-btn
              color="primary"
              @click="e1 = 3"
              style="margin-right: 2rem;"
            >
              다음
            </v-btn>
          </div>
        </v-col>

        
      </v-stepper-content>

      <v-stepper-content step="3">
        <RegisterProductImage />

        <v-col
          cols="12"
          class="text-right"
        >
          <div>
            <v-btn text
              style="border: 1px solid #1976d2"
              @click="e1 = 2"
              class="mx-5"
            >
              이전
            </v-btn>
            <v-btn
              color="primary"
              @click="e1 = 4"
              style="margin-right: 2rem;"
            >
              다음
            </v-btn>
          </div>
        </v-col>
      </v-stepper-content>

      <v-stepper-content step="4">
        <v-card
          class="mb-12"
          color="grey lighten-1"
          height="200px"
        ></v-card>
        <v-col
          cols="12"
          class="text-right"
        >
          <div>
            <v-btn text
              style="border: 1px solid #1976d2"
              @click="e1 = 3"
              class="mx-5"
            >
              이전
            </v-btn>
            <v-btn
              color="primary"
              style="margin-right: 2rem;"
            >
              등록
            </v-btn>
          </div>
        </v-col>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import RegisterCategory from '../../components/seller/RegisterComponent/RegisterCategory.vue'
import RegisterProductImage from '../../components/seller/RegisterComponent/RegisterProductImage.vue'
import RegisterProductInfo from '../../components/seller/RegisterComponent/RegisterProductInfo.vue'
export default {
  name: 'SellerRegister',
  components: {
    RegisterCategory,
    RegisterProductInfo,
    RegisterProductImage,
  },
  data () {
    return {
      e1: 1,
      maincode: 0,
      subcode: 0,
      detailcode: 0,
    }
  }
}
</script>

<style scoped>
  >>> .v-stepper__header {
    box-shadow: none !important;
  }
</style>