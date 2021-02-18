<template>
  <v-col
    class="pa-0"
    cols="4"
    style="height: 200px;"
    @click="moveItemDetail"
  >
    <v-img
      :src="sumnailUrl"
      style="height: 150px;"
    ></v-img>
    <v-card-text class="pa-0" style="height: 50px;">
      <div class="black--text ml-3 text-truncate">
        <!-- 상품이름 -->
        {{ smallItem.name }}
      </div>
      <v-container>
        <v-row>
          <div class="red--text text--darken-4 ml-3">
            <!-- 상품가격 -->
            <p style="margin-top: 1px;">{{ smallItem.options[0].discountPrice | comma }}</p>
          </div>
          <!-- 평균평점 -->
          <div class="ml-2">
            <v-rating
              :value="smallItem.rating"
              color="warning"
              background-color="warning lighten-1"
              dense
              half-increments
              readonly
              size="16"
            ></v-rating>                 
          </div>
        </v-row>
      </v-container>
    </v-card-text>            
  </v-col>  
</template>

<script>
import axios from 'axios'
export default {
  name: 'DetailSmallItemOne',
  props: {
    smallItem: Object,
  },
  data: () => ({
    sFileIds: [],
    sFiles: [],
    sumnailUrl: '',
  }),
  methods: {
    getItem() {
      this.smallItem.images.forEach(image => {
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
      this.$router.push({name: 'ItemDetail', params: {id: this.smallItem.category3Id, productid: this.smallItem.id}})
    },
  },
  created() {
    this.getItem()
  }
}
</script>

<style>

</style>