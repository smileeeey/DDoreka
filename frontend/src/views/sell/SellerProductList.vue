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
          :items-per-page="10"
          class="elevation-1"
          @click:row="handleClick"
        >
          
          <template v-slot:item.cost="{ item }">
            {{ item.cost|comma }}
          </template>
          <template v-slot:item.totalincome="{ item }">
            {{ item.totalincome|comma }}
          </template>
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
        @click="gotoDetailpage"
      >
        <v-img
          :src="`data:image/jpeg;base64,${selectdata.img}`"
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
import axios from 'axios'
import { mapState } from 'vuex'
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
        ],
        selectdata: null,
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
      } })
      window.open(routeData.href, '_blank')
    }
  },
  computed: {
    ...mapState([
      'seller',
    ])
  },
  created: function () {
    axios.get(`http://i4d106.p.ssafy.io:8081/product/seller/all/${this.seller.id}`)
      .then(res => {
        let productslist = res.data.data;
        const productsize = productslist.length
        let newItems = [];
        let idx = 1;

        for(let i=0; i<productsize; i++) {
          let ctid = productslist[i].category1Id
          axios.get(`http://i4d106.p.ssafy.io:8084/order/prodcut/${productslist[i].id}/dayofweek`)
            .then(r => {
              let data = r.data.data
              let sumdata = data.reduce(function(a, b) {
                return a + b
              })
              if (productslist[i].images.length > 0) {
                axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe/${productslist[i].images[0].fileId}`)
                  .then(res => {
                    let tmp = productslist[i].registerDate.split('T')
                    for (let j=0; j<productslist[i].options.length; j++) {
                      if (productslist[i].options[j].stockQuantity - sumdata > -1) {
                        var computedStock = productslist[i].options[j].stockQuantity - sumdata
                      } else {
                        var computedStock = 0
                      }
                      newItems.push({
                        index: idx,
                        created_at: tmp[0] + ' / ' + tmp[1].split('.')[0],
                        totalincome: sumdata * productslist[i].options[j].price,
                        productname: productslist[i].name + '  [' + productslist[i].options[j].name + ']',
                        cost: productslist[i].options[j].price,
                        amount: computedStock,
                        img: res.data.data.imageBytes,
                        rating: productslist[i].rating,
                        reviewCnt: productslist[i].reviewCnt,
                        pdid: productslist[i].id,
                        ctid: ctid,
                        chartdata: {
                          labels: ['SUN', 'MON', 'TUE', 'WED', "THU", "FRI", 'SAT'],
                          datasets: [
                            {
                              label: '주간 매출',
                              backgroundColor: 'purple',
                              borderColor: 'skyblue',
                              data: data,
                              fill: false,
                              lineTension: 0
                            }
                          ],
                        },
                        doughnutChartdata: {
                          labels: ['판매수', '재고'],
                          datasets: [
                            {
                              label: 'Something',
                              backgroundColor: ['red', 'green'],
                              data: [sumdata, computedStock]
                            }
                          ]
                        }
                      })
                      idx += 1
                    }
                    
                  })
                  .catch(res => {
                    console.log(res)
                  })
              } else {
                    let tmp = productslist[i].registerDate.split('T')
                    for (let j=0; j<productslist[i].options.length; j++) {
                      newItems.push({
                        index: idx,
                        created_at: tmp[0] + ' / ' + tmp[1].split('.')[0],
                        totalincome: '1000000',
                        productname: productslist[i].name + '  [' + productslist[i].options[j].name + ']',
                        cost: productslist[i].options[j].price,
                        amount: productslist[i].options[j].stockQuantity,
                        img: res.data.data.imageBytes,
                        rating: productslist[i].rating,
                        reviewCnt: productslist[i].reviewCnt,
                      })
                      idx += 1
                    }
              }
            })

        }
        this.items = newItems
        
      })
  }
}
</script>

<style>

</style>