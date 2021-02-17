<template>
  <v-container fluid>
    <v-row>
      {{seller}}
      <v-col
        cols="12"
        lg="3"
      >
        <v-card>
          <LineChart 
            :chartdata="chartdata"
          />
          <v-card-title>
            Weekly 매출액
          </v-card-title>
          <v-card-subtitle>
            전일 대비 <span style="color: red;">+10%</span> 증가
          </v-card-subtitle>
          <v-divider />
          <div class="mx-3">
            <v-icon small class="mr-1">mdi-clock-outline</v-icon>
            <span class="caption grey--text font-weight-light mb-3">updated 25 minutes ago</span>
          </div>
        </v-card>
      </v-col>
      <v-col
        cols="12"
        lg="3"
      >
        <DoughnutChart 
          :chartdata="chartdata"
        />
      </v-col>
      <v-col
        cols="12"
        lg="3"
      >
        <BarChart
          :chartdata="chartdata"
          :options="options"
        />

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import LineChart from '../../components/seller/chart/LineChart.js'
import DoughnutChart from '../../components/seller/chart/DoughnutChart.js'
import BarChart from '../../components/seller/chart/BarChart.js'

import { mapState } from 'vuex'

export default {
  components: { LineChart, DoughnutChart, BarChart },
  name: 'Dashboard',
  data: () => ({
    chartdata: {
      labels: ['January', 'February'],
      datasets: [
        {
          label: 'Data One',
          backgroundColor: ['skyblue', 'red'],
          data: [40, 20]
        }
      ]
    },
    
  }),
  computed: {
    ...mapState([
      'seller',
    ])
  },


}
</script>

<style scoped>
  .v-sheet--offset {
    top: -24px;
    position: relative;
  }
</style>