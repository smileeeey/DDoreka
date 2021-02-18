<template>
  <v-img
    :src="sumnailUrl"
    aspect-ratio="1"
    class="grey lighten-2"
    @click="moveItemDetail"
  >
    <template v-slot:placeholder>
      <v-row
        class="fill-height ma-0"
        align="center"
        justify="center"
      >
        <v-progress-circular
          indeterminate
          color="grey lighten-5"
        ></v-progress-circular>
      </v-row>
    </template>
  </v-img>
</template>

<script>
import axios from 'axios'
export default {
  name: 'TodayItem',
  props: {
    todayItem: Object,
  },
  data: () => ({
    item: {},
    sFileIds: [],
    sFiles: [],
    sumnailUrl: '',
  }),
  methods: {
    getItem() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${this.todayItem.id}`)
      .then(res => {
        // console.log(res.data.data)
        this.item = res.data.data
        this.item.images.forEach(image => {
          if (image.imageType === 'S') {
            this.sFileIds.push(image.fileId)
          }
        })
        // console.log(this.sFileIds)
        axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
          params: {
            fileIds: this.sFileIds.join(',')
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
      })
      .catch(err => {
        console.log(err)
      })
    },
    moveItemDetail: function () {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push({name: 'ItemDetail', params: {id: this.item.category3Id, productid: this.item.id}})
    },
  },
  created() {
    this.getItem()
  }
}
</script>

<style>

</style>