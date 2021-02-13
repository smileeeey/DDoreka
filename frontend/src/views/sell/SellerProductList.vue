<template>
  <v-container fluid>
    <v-row>
      <v-col
        cols="12"
        md="12"
      >
        <v-data-table
          :headers="headers"
          :items="items"
          :items-per-page="5"
          class="elevation-1"
          @click:row="handleClick"
        >

        </v-data-table>
      </v-col>
    </v-row>

    <v-row v-if="selectdata">
      <v-col
        cols="12"
        md="4"
      >
      <v-card
        class="mx-auto"
      >
        <v-img
          :src="selectdata.img"
        ></v-img>

        <v-card-title>{{selectdata.productname}}</v-card-title>
        <v-card-text>
          <v-row
            align="center"
            class="mx-0"
          >
            <v-rating
              :value="4.5"
              color="amber"
              dense
              half-increments
              readonly
              size="14"
            ></v-rating>
            <div class="grey--text ml-4">
              4.5 (413)
            </div>
          </v-row>
        </v-card-text>
        <v-divider class="mx-4"></v-divider>
      </v-card>
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <LineChart 
          :key="componentKey"
          :chartdata="selectdata.chartdata"
        />
        
      </v-col>
      <v-col
        cols="12"
        md="4"
      >
        <DoughnutChart 
          :key="componentKey"
          :chartdata="selectdata.doughnutChartdata"
        />
        
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import LineChart from '../../components/seller/chart/LineChart.js'
import DoughnutChart from '../../components/seller/chart/DoughnutChart.js'

export default {
  name: 'SellerProductList',
  components: {
    LineChart, DoughnutChart
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
          { text: '등록일', value: 'created_at' },
          { text: '상품명', value: 'productname' },
          { text: '가격', value: 'cost' },
          { text: '재고', value: 'amount' },
          { text: '총 매출', value: 'totalincome' },
        ],
        items: [
          {
            index: 1,
            created_at: '2020-10-02',
            totalincome: '1180000',
            productname: '민트위니 여성용 미키기모셋',
            cost: 22900,
            amount: 132,
            img: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4fcd/162c5d78b17078cfb5c2759e809c320b051b2989d2e6256bbab43cee3393.jpg',
            chartdata: {
              labels: ['SUN', 'MON', 'TUE', 'WED', "THU", "FRI", 'SAT'],
              datasets: [
                {
                  label: '주간 매출',
                  backgroundColor: 'purple',
                  borderColor: 'skyblue',
                  data: [40, 20, 30, 60, 15, 30, 25],
                  fill: false,
                  lineTension: 0
                }
              ]
            },
            doughnutChartdata: {
              labels: ['무슨', '내용을', '넣어볼까요'],
              datasets: [
                {
                  label: 'Something',
                  backgroundColor: ['yellow', 'green', 'pink'],
                  data: [60, 10, 30]
                }
              ]
            }
          },
          {
            index: 2,
            created_at: '2020-12-12',
            totalincome: '3620000',
            productname: '캐럿 여성 와이드 밴딩 팬츠',
            cost: 9900,
            amount: 84,
            img: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86815902988186-18c5ec56-8775-4476-ae6f-d1bae7e2dcc5.jpg',
            chartdata: {
              labels: ['SUN', 'MON', 'TUE', 'WED', "THU", "FRI", 'SAT'],
              datasets: [
                {
                  label: '주간 매출',
                  backgroundColor: 'purple',
                  borderColor: 'skyblue',
                  data: [20, 120, 80, 40, 65, 10, 25],
                  fill: false,
                  lineTension: 0
                }
              ]
            },
            doughnutChartdata: {
              labels: ['ㅋㅋ', 'ㅎㅎ', '깔깔'],
              datasets: [
                {
                  label: 'Something',
                  backgroundColor: ['green', 'red', 'black'],
                  data: [40, 20, 30]
                }
              ]
            }
          },
          {
            index: 3,
            created_at: '2021-02-13',
            totalincome: '630000',
            productname: '루나걸 여성용 니트',
            cost: 22900,
            amount: 25,
            img: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/358954270343111-663ca6b1-cd2c-4c03-9228-1ffbf43b93cb.jpg',
            chartdata: {
              labels: ['SUN', 'MON', 'TUE', 'WED', "THU", "FRI", 'SAT'],
              datasets: [
                {
                  label: '주간 매출',
                  backgroundColor: 'purple',
                  borderColor: 'skyblue',
                  data: [60, 10, 25, 140, 750, 35, 125],
                  fill: false,
                  lineTension: 0
                }
              ]
            },
            doughnutChartdata: {
              labels: ['어떤', '차트를', '넣어야', '할까요'],
              datasets: [
                {
                  label: 'Something',
                  backgroundColor: ['skyblue', 'red', 'purple', 'yellow'],
                  data: [40, 20, 30, 30]
                }
              ]
            }
          },
        ],
        selectdata: null,
    }
  },
  methods: {
    handleClick: function (value) {
      this.selectdata = null;
      this.selectdata = value;
      this.componentKey += 1
    }
  }
}
</script>

<style>

</style>