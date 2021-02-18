<template>
  <v-carousel-item @click.native="moveItemDetail">
      <v-img
        :src="sumnailUrl"
        height="100%"
      >
      </v-img>
    </v-carousel-item>
</template>

<script>
import axios from 'axios'
export default {
  name: 'DetailBigItemOne',
  props: {
    bigItem: Object,
  },
  data: () => ({
    sFileIds: [],
    sFiles: [],
    sumnailUrl: '',
  }),
  methods: {
    getItem() {
      this.bigItem.images.forEach(image => {
        if (image.imageType === 'S') {
          this.sFileIds.push(image.fileId)
        }
      })
      // console.log(this.sFileIds)
      axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
        params: {
          fileIds: this.sFileIds.join(',')
          // fileIds: '1, 2'
        },
      })
      .then(res => {
        // console.log(res.data.data)
        this.sFiles = res.data.data
        if (this.sFiles.length > 0) {
        this.sumnailUrl = `data:image/jpeg;base64,${this.sFiles[0].imageBytes}`
      }
      })
      .catch(err => {
        console.log(err)
      })
    },
    moveItemDetail: function () {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push({name: 'ItemDetail', params: {id: this.bigItem.category3Id, productid: this.bigItem.id}})
    },
  },
  created() {
    this.getItem()
  }
}
</script>

<style>

</style>