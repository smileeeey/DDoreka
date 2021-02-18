<template>
  <v-slide-item @click.native="moveItemDetail">
    <v-card
      class="ma-4"
      height="200"
      width="200"
    >
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>            
      <v-img
        :src="sumnailUrl"
        height="150"
        width="200"
      ></v-img>
      <v-card-text class="pa-1 ml-2">
        <div class="mb-1 subtitle-1 font-weight-bold text-truncate" color="black">
          <!-- 상품이름 -->
          {{ item.name }}
        </div>
        <v-row
          align="center"
          justify="start"
          class="mx-0"
        >
          <!-- 평균평점 -->
          <v-rating
            :value="item.rating"
            color="warning"
            background-color="warning lighten-1"
            dense
            half-increments
            readonly
            size="16"
          ></v-rating>

          <!-- 평균평점, 리뷰개수 -->
          <div class="ml-1">
            ({{ item.reviewCnt }})
          </div>
        </v-row>
      </v-card-text>            
    </v-card>
  </v-slide-item>
</template>

<script>
import axios from 'axios'
export default {
  name: 'HotItemCard',
  props: {
    productId: Number,
  },
  data: () => ({
    item: {},
    sFileIds: [],
    sFiles: [],
    sumnailUrl: '',
  }),
  methods: {
    getItem() {
      axios.get(`http://i4d106.p.ssafy.io:8081/product/detail/${this.productId}`)
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
    // console.log(this.productId)
    this.getItem()
  }
}
</script>

<style>

</style>