<template>
  <!-- <div>{{ detailInfoList }}</div> -->
  <div>
    <div class="my-10">
      <h3 class="my-5">필수표기정보</h3>
      <v-card>
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr v-for="n in detailInfoLength" :key="n">
                <td class="" style="background-color: #dee6ed;">{{ detailInfoList[2 * n - 2] }}</td>
                <td>{{ detailInfoList[2 * n - 1] }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
      </v-card>
    </div>
    <v-img
      v-for="(imageUrl,idx) in imageUrls"
      :key="idx"
      :src="imageUrl"
    ></v-img>
  </div>
</template>

<script>
export default {
  name: 'ProductDetail',
  props: {
    item: Object,
    mFiles: Array,
  },
  data: () => ({
    detailInfo: '',
    detailInfoList: [],
    detailInfoLength: 0,
    imageUrls: [],
  }),
  methods: {
    getDetailInfo: function() {
      // console.log(this.item.detailInfo)
      this.detailInfo = this.item.detailInfo
      this.detailInfoList = this.item.detailInfo.split('&^%')
      this.detailInfoLength = this.detailInfoList.length / 2
      // this.detailInfo = JSON.parse(this.item.detailInfo)
      // this.detailInfoList = Object.entries(this.detailInfo)
    },
    getImageUrl() {
      this.mFiles.forEach(mFile => {
        // console.log('getImageUrl')
        let imageUrl = 'data:image/jpeg;base64,' + mFile.imageBytes
        // console.log(imageUrl)
        this.imageUrls.push(imageUrl)
      })
    },
  },
  created: function() {
    // console.log(this.mFiles)
    this.getDetailInfo()
    this.getImageUrl()
  },
}
</script>

<style>

</style>