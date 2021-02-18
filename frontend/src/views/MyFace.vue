<template>
  <v-container fluid>
    <v-row>
      <v-col
        cols="12"
      >
        <v-data-table
          :headers="headers"
          :items="items"
          :items-per-page="10"
          class="elevation-1"
          @click:row="handleClick"
        >

          <template v-slot:item.created_at="{ item }">
            {{ item.created_at|moment('YYYY년 MM월 DD일 HH시 mm분 ss초') }}
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
            :src="selectdata.img"
            width="450"
            height="450"
          ></v-img>
          <v-card-title>{{selectdata.productname}}</v-card-title>
          <v-card-text>
            <v-row
              align="center"
              class="mx-0"
            >
              <v-rating
                :value="selectdata.rating"
                color="amber"
                dense
                half-increments
                readonly
                size="14"
              ></v-rating>
              <div class="grey--text ml-4">
                {{selectdata.rating}} ({{selectdata.reviewCnt}})
              </div>
            </v-row>
          </v-card-text>
          <v-divider class="mx-4"></v-divider>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
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
          <div class="mx-3">
            <v-icon small class="mr-1">mdi-clock-outline</v-icon>
            <span class="caption grey--text font-weight-light mb-3">updated {{selectdata.created_at}} minutes ago</span>
          </div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DoughnutChart from '../components/seller/chart/DoughnutChart.js'

import { mapState } from 'vuex'

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
          text: '목차',
          align: 'start',
          sortable: false,
          value: 'index',
        },
        { text: '상품명', value: 'productname' },
        { text: '행복지수', value: 'doughnutChartdata.datasets[0].data[1]' },
        { text: '갱신일', value: 'created_at' },
      ],
      items: [
        {
          created_at: new Date(),
          index: 1,
          img: 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png',
          productname: '제품명123',
          pdid: 1,
          ctid: 1,
          rating: 0,
          reviewCnt: 0,
          doughnutChartdata: {
            labels: ['중립', '행복', '슬픔', '분노', '두려움', '역겨움', '놀람'],
            datasets: [
              {
                label: '표정',
                backgroundColor: ['yellow', 'green', 'pink', 'red', 'blue', 'black', 'purple'],
                data: [0.2, 0.3, 0.1, 0.1, 0.1, 0.1, 0.1]
              }
            ]
          }
        }
      ],
      selectdata: null,
    }
  },
  computed: {
    ...mapState([
      'login'
    ])
  },
  created () {
    if (!this.login) {
      this.$router.push({ name: 'Login' })
    }
  },
  methods: {
    handleClick: function (value) {
      this.selectdata = null;
      this.selectdata = value;
      this.componentKey += 1
    },
    gotoDetailpage: function () {
      let routeData = this.$router.resolve({ name: 'ItemDetail', params: {
        id: this.selectdata.ctid,
        productid: this.selectdata.pdid
      }})
      window.open(routeData.href, '_blank')
    }
  }
}
</script>

<style>

</style>