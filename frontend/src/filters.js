import Vue from "vue"

Vue.filter("comma", (val) => {
  return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
})