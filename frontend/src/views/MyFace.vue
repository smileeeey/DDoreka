<template>
  <v-container fluid>
    <v-row>
      <v-col
        cols="12"
      >
        <v-data-table
          :headers="headers"
          :items="items"
          :items-per-page="5"
          class="elevation-1"
          @click:row="handleClick"
        >

          <template v-slot:item.happyscore="{ item }">
            {{ item.happyscore}}%
          </template>
          <template v-slot:item.surprisescore="{ item }">
            {{ item.surprisescore}}%
          </template>
          <template v-slot:item.face.createdAt="{ item }">
            {{ item.face.createdAt|moment('YYYY년 MM월 DD일 HH시 mm분 ss초') }}
          </template>
          

        </v-data-table>

      </v-col>

      <v-col
        v-if="selectdata"
        cols="12"
        md="4"
      >
        <v-card
          class="mx-auto"
          @click="gotoDetailpage"
        >
          <v-img
            :src="`data:image/jpeg;base64,${selectdata.img}`"
            width="450"
            height="450"
          ></v-img>
          <v-card-title>{{selectdata.product.name}}</v-card-title>
          <v-card-text>
            <v-row
              align="center"
              class="mx-0"
            >
              <v-rating
                :value="selectdata.product.rating"
                color="amber"
                dense
                half-increments
                readonly
                size="14"
              ></v-rating>
              <div class="grey--text ml-4">
                {{selectdata.product.rating}} ({{selectdata.product.reviewCnt}})
              </div>
            </v-row>
          </v-card-text>
          <v-divider class="mx-4"></v-divider>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        md="5"
        v-if="selectdata"
      >
        <v-card>
          <div>
            <DoughnutChart 
              :key="componentKey"
              :chartdata="selectdata.doughnutChartdata"
            />
          </div>
          <v-divider></v-divider>
          <div class="mx-3 my-3">
            <v-icon  class="mr-1">mdi-clock-outline</v-icon>
            <span class=" mb-3">updated {{selectdata.face.createdAt | moment("from", "now")}}</span>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DoughnutChart from '../components/seller/chart/DoughnutChart.js'

import { mapState } from 'vuex'
import axios from 'axios'
export default {
  name: 'MyFace',
  components: {
    DoughnutChart,
  },
  data () {
    return {
      componentKey: 0,
      headers: [
        {
          text: '갱신일',
          align: 'start',
          value: 'face.createdAt',
        },
        { text: '상품명', value: 'product.name' },
        { text: '행복지수', value: 'happyscore' },
        { text: '놀람지수', value: 'surprisescore' },
        { text: '제품감상시간 (초)', value: 'face.time' },
      ],
      items: [

      ],
      selectdata: null,
    }
  },
  computed: {
    ...mapState([
      'login',
      'userId'
    ]),
  },
  created () {
    if (!this.login) {
      this.$router.push({ name: 'Login' })
    }
    let items = [];
    axios.get(`http://i4d106.p.ssafy.io:8088/face/getAllByUser/${this.userId}`)
      .then(res => {
        res.data.forEach(item => {
          axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${item.product}`)
            .then(pres => {
              axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe/${pres.data.data.images[0].fileId}`)
                .then(ires => {
                  let array = [item.neutral, item.happy, item.sad, item.angry, item.fearful, item.disgusted, item.surprised]
                  let sumscore = array.reduce(function (a, b) {
                    return a+b
                  })
                  items.push({
                    img: ires.data.data.imageBytes,
                    product: pres.data.data,
                    face: item,
                    happyscore: Math.round((item.happy/sumscore).toFixed(2)*100),
                    surprisescore: Math.round((item.surprised/sumscore).toFixed(2)*100),
                    doughnutChartdata: {
                      labels: ['중립', '행복', '슬픔', '분노', '두려움', '역겨움', '놀람'],
                      datasets: [
                        {
                          label: '표정',
                          backgroundColor: ['yellow', 'green', 'pink', 'red', 'blue', 'black', 'purple'],
                          data: array
                        }
                      ]
                    }
                  })
                })
            })
        })
      this.items = items
      })
  },
  methods: {
    handleClick: function (value) {
      this.selectdata = null;
      this.selectdata = value;
      this.componentKey += 1
    },
    gotoDetailpage: function () {
      let routeData = this.$router.resolve({ name: 'ItemDetail', params: {
        id: this.selectdata.product.category3Id,
        productid: this.selectdata.product.id
      }})
      window.open(routeData.href, '_blank')
    }
  }
}
</script>

<style>

</style>