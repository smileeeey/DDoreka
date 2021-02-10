<template>
  <div>
    <h2>주문목록</h2>
    <div v-for="(item, idx) in items.slice().reverse()" :key="idx">
      <CancelOrderCard :item="item" />
    </div>
  </div>
</template>

<script>
import CancelOrderCard from '../../components/mypage/CancelOrderCard.vue'

export default {
  name: 'CancelReturnExchangeList',
  components: {
    CancelOrderCard
  },
  data () {
    return {
      items: [
      {
        img: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/4fcd/162c5d78b17078cfb5c2759e809c320b051b2989d2e6256bbab43cee3393.jpg',
        name: '민트위니 여성용 미키기모셋',
        cost: '22900',
        amount: 2,
        date: new Date()
      },
      {
        img: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/86815902988186-18c5ec56-8775-4476-ae6f-d1bae7e2dcc5.jpg',
        name: '캐럿 여성 와이드 밴딩 팬츠',
        cost: '9900',
        amount: 3,
        date: new Date()
      },
      {
        img: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/358954270343111-663ca6b1-cd2c-4c03-9228-1ffbf43b93cb.jpg',
        name: '루나걸 여성용 니트',
        cost: '22900',
        amount: 1,
        date: new Date()
      },
    ]
  
    }
  }
}
</script>

<style>

</style>