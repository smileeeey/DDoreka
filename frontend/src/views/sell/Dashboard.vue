<template>
  <v-container fluid style="width: 75%;">
    {{$route.params.change}}
    <v-row class="my-5">
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>총 매출</strong>
              <span style="text-align: right">{{totalIncome | comma}}원</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>이번달 매출</strong>
              <span style="text-align: right">{{linedata.datasets[0].data[todayDate] | comma}} 원</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>등록한 상품</strong>
              <span style="text-align: right">75 개</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>총 판매량</strong>
              <span style="text-align: right">8450 개</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
    </v-row>
    <v-row class="my-5">
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>오늘 매출</strong>
              <span style="text-align: right" v-if="todayDate > -1 == true" >{{linedata.datasets[0].data[todayDate] - linedata.datasets[0].data[todayDate-1] | comma}} 원</span>
              <span style="text-align: right" v-else>{{linedata.datasets[0].data[todayDate] - linedata.datasets[0].data[todayDate-1] | comma}} 원</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>주문 접수</strong>
              <span style="text-align: right">{{callCnt}} 건</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>배송중</strong>
              <span style="text-align: right">{{deliveryCnt}} 건</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
      <v-col
        cols="6"
        md="3"
      >
        <v-card>
          <v-list-item>
            <v-list-item-content>
              <strong>배송 완료</strong>
              <span style="text-align: right">{{completeCnt}} 건</span>
            </v-list-item-content>
          </v-list-item>

        </v-card>
      </v-col>
    </v-row>

    <v-row style="my-5">
      <v-col
        cols="12"
        md="4"
        offset-md="1"
      >
        <BarChart
          :key="componentKey"
          :chartdata="chartdata"
        />
      </v-col>
      <v-col
        cols="12"
        md="4"
        offset-md="2"
      >
        <LineChart 
          :key="componentKey"
          :chartdata="linedata"
        />
      </v-col>
      
    </v-row>

    <v-row>
      <v-col
        cols="12"
      >
        <DBCalender />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DBCalender from '../../components/seller/DBCalender.vue'
import LineChart from '../../components/seller/chart/LineChart.js'
import BarChart from '../../components/seller/chart/BarChart.js'

import { mapState } from 'vuex'
import axios from 'axios'

export default {
  components: { LineChart, BarChart, DBCalender },
  name: 'Dashboard',
  data: () => ({
    componentKey: 0,
    chartdata: {
      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      datasets: [
        {
          label: '월별 매출',
          borderColor: 'skyblue',
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          data: [300, 500],
          fill: false,
          lineTension: 0
        }
      ]
    },
    linedata: {
      labels: [
        1,2,3,4,5,6,7,8,9,10,  
        11,12,13,14,15,16,17,18,19,20,  
        21,22,23,24,25,26,27,28,29,30,
      ],
      datasets: [
        {
          label: '이번달 매출',
          borderColor: 'skyblue',
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          data: [],
          fill: false,
          lineTension: 0
        }
      ]
    },
    thismonthIncome: 0,
    productCnt: 0,
    totalSellCnt: 0,
    callCnt: 0,
    deliveryCnt: 0,
    completeCnt: 0,
    
  }),
  watch: {

  },
  computed: {
    ...mapState([
      'seller',
    ]),
    totalIncome: function () {
      const result = this.chartdata.datasets[0].data.reduce(function add(a, b) {
        return a + b
      })
      return result
    },
    todayDate: function () {
      return new Date().getDate() - 1
    },
  },
  created: function () {
    this.datarendering()
    this.getOrderInfo()
  },


  methods: {
    datarendering() {
          let now = new Date()
          let todayMonth = now.getMonth() + 1
          // let todaydate = now.getDate()
          let tmpdata = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
          let tmpthismonthdata = [
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            ];
          for (let i=1; i<13; i++) {
            
            axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/${i}/`)
              .then(res=> {
                var monthtotal = 0;
                for (let j=0; j<30; j++) {
                  if (res.data.data[j].length > 0) {
                    for (let k=0; k<res.data.data[j].length; k++) {
                      monthtotal += Number(res.data.data[j][k].price)
                    }
                  }
                  if (i == todayMonth) {
                    tmpthismonthdata[j] = monthtotal
                  }
                  tmpdata[i-1] = monthtotal
                }
                
              })
          }
          this.chartdata.datasets[0].data = tmpdata
          this.linedata.datasets[0].data = tmpthismonthdata
    },
    check() {
      console.log(this.$route)
      this.componentKey += 1
    },
    getOrderInfo() {
      axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/status/0`)
        .then(res => {
          this.callCnt = res.data.data.length
          axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/status/1`)
            .then(delres => {
              this.deliveryCnt = delres.data.data.length
              axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/status/2`)
                .then(comres => {
                  this.completeCnt = comres.data.data.length
                })
            })
        })
    }
  }


}
</script>

<style scoped>
  .v-sheet--offset {
    top: -24px;
    position: relative;
  }
</style>