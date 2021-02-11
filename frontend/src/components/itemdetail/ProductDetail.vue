<template>
  <!-- <div>{{ detailInfoList }}</div> -->
  <div>
    <div class="my-10">
      <h3 class="my-5">필수표기정보</h3>
      <v-card>
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr v-for="(info, idx) in detailInfoList" :key="idx">
                <td class="" style="background-color: #dee6ed;">{{ info[0] }}</td>
                <td>{{ info[1] }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card>
    </div>
    <v-img
      src="https://thumbnail9.coupangcdn.com/thumbnails/remote/q89/image/retail/images/64059785646730-5ded04e1-33ef-4ea3-975e-e35b1b3417a9.jpg"
    ></v-img>
    <v-img
      src="https://thumbnail10.coupangcdn.com/thumbnails/remote/q89/image/retail/images/64059590756473-1720ee5d-e979-4b7a-ab3c-216d9963f8b8.jpg"
    ></v-img>
    <v-img
      src="https://thumbnail7.coupangcdn.com/thumbnails/remote/q89/image/retail/images/64059660725985-3955092f-1a92-4506-83ad-4413141c6573.jpg"
    ></v-img>
  </div>
</template>

<script>
export default {
  name: 'ProductDetail',
  props: {
    item: Object,
  },
  data: () => ({
    detailInfo: {},
    detailInfoList: [],
    reviews: [
      {
        id: 1,
        orderId: 1,
        optionId: 1,
        productId: 1,
        userId: 1,
        rating: 4.5,
        datetime: '2021.02.09',
        editedOX: 'X',
        title: '강추',
        content: '이 제품 정말 좋아요 강추합니다.'
      },
      {
        id: 2,
        orderId: 2,
        optionId: 1,
        productId: 1,
        userId: 2,
        rating: 4,
        datetime: '2021.02.09',
        editedOX: 'X',
        title: '후회 없는 선택',
        content: '왜 이제 샀나 후회'
      },
    ]
  }),
  methods: {
    getDetailInfo: function() {
      this.detailInfo = JSON.parse(this.item.detailInfo)
      this.detailInfoList = Object.entries(this.detailInfo)
    }
  },
  created: function() {
    this.getDetailInfo()
  }
}
</script>

<style>

</style>