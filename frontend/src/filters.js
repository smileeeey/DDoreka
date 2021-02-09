import Vue from "vue"

Vue.filter("comma", (val) => {
  return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
})

Vue.filter("phone", (val) => {
  return val.replace(/[^0-9]/g, '')
            .replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
})