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
        <RegisterProductInfo @options="val => options=val" @productname="val => productname=val" />
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
        <RegisterProductImage
          @simage="val => simages=val" @mimage="val => mimages=val"
        />

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
        <RegisterDetailInfo @detailInfo="val => detailInfo=val" />
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
              @click="registerItem"
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
import axios from 'axios'
import { mapState } from 'vuex'
import RegisterCategory from '../../components/seller/RegisterComponent/RegisterCategory.vue'
import RegisterDetailInfo from '../../components/seller/RegisterComponent/RegisterDetailInfo.vue'
import RegisterProductImage from '../../components/seller/RegisterComponent/RegisterProductImage.vue'
import RegisterProductInfo from '../../components/seller/RegisterComponent/RegisterProductInfo.vue'
export default {
  name: 'SellerRegister',
  components: {
    RegisterCategory,
    RegisterProductInfo,
    RegisterProductImage,
    RegisterDetailInfo,
  },
  computed: {
    ...mapState([
      'seller',
      'sellerstore',
    ])
  },
  created() {
    if (!this.sellerstore.id) {
      this.$router.push({ name: 'SellerProfile' })
    }
  },
  data () {
    return {
      e1: 1,
      maincode: '001',
      subcode: '101',
      detailcode: '201',
      options: null,
      simages: null,
      mimages: null,
      productname: null,
      detailInfo: null,
    }
  },
  methods: {
    registerItem () {
      var formData = new FormData();
      var images = [];
      this.simages.forEach(file => {
        formData.append('files', file)
      })
      axios.post('http://i4d106.p.ssafy.io:8082/file/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
        .then(res => {
          res.data.data.forEach(image => {
            images.push({
              'fileId': image.id,
              'imageType': 'S'
            })
          })
          formData = new FormData();
          this.mimages.forEach(file => {
            formData.append('files', file)
          })
          axios.post('http://i4d106.p.ssafy.io:8082/file/upload', formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          })
            .then(resp => {
              resp.data.data.forEach(image => {
                images.push({
                  'fileId': image.id,
                  'imageType': 'M'
                })
              })

              axios.post('http://i4d106.p.ssafy.io:8081/product/add', {
                'product': {
                  'storeId': this.seller.id,
                  'name': "'" + this.productname + "'",
                  'category1Id': this.maincode,
                  'category2Id': this.subcode,
                  'category3Id': this.detailcode,
                  'category4Id': this.detailcode,
                  'category5Id': this.detailcode,
                  'detailInfo': "'" + this.detailInfo + "'",
                },
                'option': this.options,
                'image': images
              })
                .then(r => {
                  console.log(r)
                  console.log(r.data)
                  console.log('router go')
                  this.$router.push({ name: 'SellerProductList' })
                })

            })
        })
      

    }
  }
}
</script>

<style scoped>
  >>> .v-stepper__header {
    box-shadow: none !important;
  }
</style>