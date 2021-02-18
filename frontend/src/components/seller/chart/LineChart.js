import { Line } from 'vue-chartjs'

export default {
  extends: Line,
  props: {
    chartdata: {
      type: Object,
      default: null
    },
    options: {
      type: Object,
      default() {
        return {
          responsive: true,
          legend: {
              position: 'top',
          },
          hover: {
              mode: 'label'
          },
          scales: {
              xAxes: [{
                      display: true,
                      scaleLabel: {
                          display: true,
                      }
                  }],
              yAxes: [{
                      display: true,
                      ticks: {
                          beginAtZero: true,
                      }
                  }]
          },
          title: {
              display: false,
              text: 'Chart.js Line Chart - Legend'
          }
      }
      }
    }
  },
  mounted () {
    this.renderChart(this.chartdata, this.options)
  }
}