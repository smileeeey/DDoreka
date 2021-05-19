<template>
  <div>
    <h2>주문목록</h2>
    <div v-for="(item, idx) in items.slice().reverse()" :key="idx">
      <PastOrderCard :item="item" />
    </div>
  </div>
</template>

<script>
import PastOrderCard from "../../components/mypage/PastOrderCard.vue";
import { mapState } from "vuex";
import axios from "axios";
export default {
  name: "OrderList",
  components: {
    PastOrderCard,
  },
  computed: {
    ...mapState('accountStore',["userData"]),
  },
  data() {
    return {
      items: [],
    };
  },
  created: function () {
    let dataArray = [];
    axios
      .get(`http://k4d104.p.ssafy.io:8084/order/userid/${this.userData.email}/all`)
      .then((res) => {
        let array = res.data.data;
        console.log(res);
        for (let j = 0; j < array.length; j++) {
          let date = array[j].datetime;
          var y = date.substr(0, 4);
          var m = date.substr(5, 2);
          var d = date.substr(8, 2);
          let newD = new Date(y, m - 1, d);
          axios
            .get(
              `http://k4d104.p.ssafy.io:8081/product/detail/${array[j].productId}`
            )
            .then((resp) => {
              var categoryId = resp.data.data.category3Id;
              for (let k = 0; k < resp.data.data.options.length; k++) {
                if (resp.data.data.options[k].optionId == array[j].optionId) {
                  var optionprice = resp.data.data.options[k].price;
                  var optionname = resp.data.data.options[k].name;
                  break;
                }
              }
              axios
                .get(
                  `http://k4d104.p.ssafy.io:8082/file/fileServe/${resp.data.data.images[0].fileId}`
                )
                .then((respo) => {
                  dataArray.push({
                    img: respo.data.data.imageBytes,
                    name: resp.data.data.name + "  " + optionname,
                    cost: optionprice,
                    amount: array[j].quantity,
                    date: newD,
                    status: array[j].orderDetail.orderStatus,
                    productId: resp.data.data.id,
                    categoryId: categoryId,
                    optionId: array[j].optionId,
                    orderId: array[j].id,
                    userId: array[j].userId,
                    orderDetail: array[j].orderDetail,
                  });
                });
            });
        }
        this.items = dataArray;
      });
  },
};
</script>

<style>
</style>