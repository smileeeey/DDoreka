<template>
  <div>
    <h3 class="my-5">판매자의 다른 상품</h3>
    <v-sheet
      class="mx-auto"
      flat
      outlined
      rounded
    >
      <v-slide-group
        class="pa-2"
        show-arrows
      >
        <OtherItemCard 
          v-for="(item, idx) in items"
          :key="idx"
          :item="item"
        />

      </v-slide-group>
    </v-sheet>    
  </div>
</template>

<script>
import axios from 'axios'
import OtherItemCard from './OtherItemCard.vue'
export default {
  name: 'OtherItems',
  components: {
    OtherItemCard,
  },
  props: {
    storeId: Number,
  },
  data: () => ({
    items: [],
    hotItems: [
      {
        id: 1,
        name: '메리바움 투피스',
        src: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/0a29/c85290a3550019e026132bdd6c75e624fafed101cb57c28e0e422354400e.jpg',
        rating: 4.5,
        reviews: 94,
      },
      {
        id: 2,
        name: '남녀공용 라운드 반팔',
        src: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/133486661280692-c4f29af2-e640-44aa-9cd3-d5eeef4e91eb.jpg',
        rating: 4.5,
        reviews: 7095,
      },
      {
        id: 3,
        name: '닥터방기원 클렌징',
        src: 'https://thumbnail10.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/541334555949120-8af82c67-2cf2-4286-8138-420342a82f2e.jpg',
        rating: 5,
        reviews: 3013,
      },
      {
        id: 4,
        name: 'CK 오드뚜왈렛',
        src: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/product/image/vendoritem/2018/05/30/3000052594/4e358de2-16f1-4ff9-b18c-99bec5d7cba6.jpg',
        rating: 4.5,
        reviews: 3233,
      },
      {
        id: 5,
        name: '코멧 키친 세라믹',
        src: 'https://thumbnail9.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/63844780983698-167b35c7-4bb9-40b1-8055-cf9d7fdfc92a.jpg',
        rating: 4.5,
        reviews: 1228,
      },
      {
        id: 6,
        name: 'LG전자 그램16',
        src: 'https://thumbnail7.coupangcdn.com/thumbnails/remote/230x230ex/image/retail/images/1057299165805067-ec72a8fe-ea1b-4f92-9d23-104658211548.jpg',
        rating: 5,
        reviews: 79,
      },
      {
        id: 7,
        name: '갤럭시 버즈 프로',
        src: 'https://thumbnail8.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/1284662220851-b9be2bb7-7649-4027-9725-9db9d4e62ae8.jpg',
        rating: 5,
        reviews: 1649,
      },
      {
        id: 8,
        name: '샤오미 미밴드',
        src: 'https://thumbnail6.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/2020/08/24/17/7/7b8681b4-2445-49b5-a977-42721f00a2dc.jpg',
        rating: 5,
        reviews: 8812,
      },                                                                                   
    ]
  }),
  methods: {
    getItems() {
      console.log(this.storeId)
      axios.get(`http://i4d106.p.ssafy.io:8081/product/seller/all/${this.storeId}`)
      .then(res => {
        console.log(res.data.data)
        this.items = res.data.data.slice(0, 10)
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  created() {
    this.getItems()
  },
}
</script>

<style>

</style>