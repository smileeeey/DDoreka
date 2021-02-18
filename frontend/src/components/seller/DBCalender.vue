<template>
  <div>
    <v-sheet
      tile
      height="54"
      class="d-flex"
    >
      <v-btn
        icon
        class="ma-2"
        @click="$refs.calendar.prev()"
      >
        <v-icon>mdi-chevron-left</v-icon>
      </v-btn>

      <v-toolbar-title v-if="$refs.calendar" style="padding: 0.75rem;">
        {{ $refs.calendar.title }}
      </v-toolbar-title>
      <v-btn
        icon
        class="ma-2"
        @click="$refs.calendar.next()"
      >
        <v-icon>mdi-chevron-right</v-icon>
      </v-btn>
      
    </v-sheet>
    <v-sheet height="600">
      <v-calendar
        ref="calendar"
        v-model="value"
        :weekdays="weekday"
        :type="type"
        :events="events"
        :event-overlap-mode="mode"
        :event-overlap-threshold="30"
        :event-color="getEventColor"
        @change="getEvents"
      ></v-calendar>
    </v-sheet>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: 'DBCalender',
  data: () => ({
    type: 'month',
    mode: 'stack',
    weekday: [0, 1, 2, 3, 4, 5, 6],
    value: '',
    events: [],
    colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
    names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
  }),
  methods: {
    getEvents ({ start, end }) {
      const orders = [];
      axios.get(`http://i4d106.p.ssafy.io:8084/order/sellerid/${this.seller.id}/${start.month}/`)
        .then(res => {
          const dayInfo = res.data.data;
          for (let i=0; i<30; i++) {
            if (dayInfo[i].length > 0) {
              let totalIncome = 0;
              let totalQuantity = 0;
              for (let j=0; j<dayInfo[i].length; j++) {
                totalIncome += Number(dayInfo[i][j].price)
                totalQuantity += Number(dayInfo[i][j].quantity)
              }
              orders.push({
                name: '판매 개수 : ' + totalQuantity + '개',
                start: new Date(start.year, start.month-1, i+1, 0, 0, 0),
                end: new Date(start.year, start.month-1, i+1, 23, 59, 59),
                color: 'orange',
                timed: false,
              })
              orders.push({
                name: '총 판매액 : ' + totalIncome + '원',
                start: new Date(start.year, start.month-1, i+1, 0, 0, 0),
                end: new Date(start.year, start.month-1, i+1, 23, 59, 59),
                color: 'green',
                timed: false,
              })
            }
          }
          
          this.events = orders
        })


      // const events = []

      // const min = new Date(`${start.date}T00:00:00`)
      // const max = new Date(`${end.date}T23:59:59`)
      // const days = (max.getTime() - min.getTime()) / 86400000
      // const eventCount = this.rnd(days, days + 20)

      // for (let i = 0; i < eventCount; i++) {
      //   const allDay = this.rnd(0, 3) === 0
      //   const firstTimestamp = this.rnd(min.getTime(), max.getTime())
      //   const first = new Date(firstTimestamp - (firstTimestamp % 900000))
      //   const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
      //   const second = new Date(first.getTime() + secondTimestamp)

      //   events.push({
      //     name: this.names[this.rnd(0, this.names.length - 1)],
      //     start: first,
      //     end: second,
      //     color: this.colors[this.rnd(0, this.colors.length - 1)],
      //     timed: !allDay,
      //   })
      // }

      // this.events = events
    },
    getEventColor (event) {
      return event.color
    },
    rnd (a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a
    },
  },
  computed: {
    ...mapState([
      'seller',
    ])
  }
}
</script>

<style>

</style>