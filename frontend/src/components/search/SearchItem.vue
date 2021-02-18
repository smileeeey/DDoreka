<template>
  <v-card :to="{ name: 'ItemDetail', params: { id: item.category3Id, productid: productId }}">
    <v-img
      :src="sumnailUrl"
      aspect-ratio="1"
      class="grey lighten-2"
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
    <v-card-title class="pa-0 pl-3 pt-3">{{ item.name }}</v-card-title>
    <v-card-text>
      <div class="pb-2 subtitle-1 red--text text--darken-4">
        {{ item.options[0].discountPrice | comma }}
      </div>
      <v-row
        align="center"
        class="mx-0 py-1"
      >
        <v-rating
          :value="item.rating"
          color="warning"
          background-color="warning lighten-1"
          dense
          half-increments
          readonly
          size="17"
        ></v-rating>

        <div class="grey--text ml-1">
          ({{ item.reviewCnt }})
        </div>
      </v-row>
    </v-card-text>    
  </v-card>
</template>

<script>
import axios from 'axios'
// import qs from 'qs'

export default {
  name: 'SearchItem',
  props: {
    item: Object,
  },
  data: () => ({
    sFileIds: [],
    mFileIds: [],
    sFiles: [],
    mFiles: [],
    sumnailUrl: '',
    productId: '',
  }),
  methods: {
    getFileIds() {
      this.sFileIds = [],
      this.mFileIds = [],
      this.item.images.forEach(image => {
        if (image.imageType === 'S') {
          this.sFileIds.push(image.fileId)
        } else {
          this.mFileIds.push(image.fileId)
        }
      })
    },
    getSFiles() {
      this.sFiles = [],
      this.sumnailUrl = '',
      axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
        params: {
          fileIds: this.sFileIds.join(',')
        },
        // paramsSerializer: params => {
        //   return qs.stringify(params)
        // }
      })
      .then(res => {
        // console.log(res.data)
        this.sFiles = res.data.data
        if (this.sFiles.length > 0) {
          // this.sumnailUrl = `file:i4d106.p.ssafy.io/home/upload/image/${this.sFiles[0].path}/${this.sFiles[0].systemName}`
          this.sumnailUrl = `data:image/jpeg;base64,${this.sFiles[0].imageBytes}`
        }
        
      })
      .catch(err => {
        console.log(err)
      })
    },
    getMFiles() {
      this.mFiles = [],
      axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
        params: {
          fileIds: this.mFileIds.join(',')
        },
      })
      .then(res => {
        // console.log(res.data)
        this.mFiles = res.data.data
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  watch: {
    item: function() {
      // console.log(this.item.name)
      this.productId = this.item.id
      this.getFileIds()
      this.getSFiles()
      this.getMFiles()
    },
  },
  created() {
    // console.log(this.item)
    this.productId = this.item.id
    this.getFileIds()
    this.getSFiles()
    this.getMFiles()
  },
}
</script>

<style>

</style>