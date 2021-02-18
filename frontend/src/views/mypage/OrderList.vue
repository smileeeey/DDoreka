<template>
  <div>
    <h2>주문목록</h2>
    <div v-for="(item, idx) in items.slice().reverse()" :key="idx">
      <PastOrderCard :item="item" />
    </div>
  </div>
</template>

<script>
import PastOrderCard from '../../components/mypage/PastOrderCard.vue'
import { mapState } from 'vuex';
import axios from 'axios';
export default {
  name: 'OrderList',
  components: {
    PastOrderCard
  },
  computed: {
    ...mapState([
      'userId',
    ])
  },
  data () {
    return {
      items: [
      // {
      //   img: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4fcd/162c5d78b17078cfb5c2759e809c320b051b2989d2e6256bbab43cee3393.jpg',
      //   name: '민트위니 여성용 미키기모셋',
      //   cost: '22900',
      //   amount: 2,
      //   date: new Date()
      // },
      // {
      //   img: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86815902988186-18c5ec56-8775-4476-ae6f-d1bae7e2dcc5.jpg',
      //   name: '캐럿 여성 와이드 밴딩 팬츠',
      //   cost: '9900',
      //   amount: 3,
      //   date: new Date()
      // },
      // {
      //   img: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/358954270343111-663ca6b1-cd2c-4c03-9228-1ffbf43b93cb.jpg',
      //   name: '루나걸 여성용 니트',
      //   cost: '22900',
      //   amount: 1,
      //   date: new Date()
      // },
    ]
  
    }
  },
  created: function () {
    let dataArray = [];
    axios.get(`http://i4d106.p.ssafy.io:8084/order/userid/${this.userId}/all`)
      .then(res => {
        let array = res.data.data;
        for (let j=0; j<array.length; j++) {
          let date = array[j].datetime
          var y = date.substr(0, 4);
          var m = date.substr(5, 2);
          var d = date.substr(8, 2);
          let newD = new Date(y, m-1, d)
          axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${array[j].productId}`)
            .then(resp => {
              var categoryId = resp.data.data.category3Id
              for (let k=0; k<resp.data.data.options.length; k++) {
                if (resp.data.data.options[k].optionId == array[j].optionId) {
                  var optionprice = resp.data.data.options[k].price
                  var optionname = resp.data.data.options[k].name
                  break;
                }
              }
              axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe/${resp.data.data.images[0].fileId}`)
                .then(respo => {
                  dataArray.push({
                    img: respo.data.data.imageBytes,
                    name: resp.data.data.name + '  ' + optionname,
                    cost: optionprice,
                    amount: array[j].quantity,
                    date: newD,
                    status: array[j].orderDetail.orderStatus,
                    productId: resp.data.data.id,
                    categoryId: categoryId,
                    optionId: array[j].optionId,
                    orderId: array[j].id,
                    userId: array[j].userId,
                    orderDetail: array[j].orderDetail
                  })
                })
            })
        }
        this.items = dataArray

      })
  }
}
</script>

<style>

</style>