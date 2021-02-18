<template>
  <v-card
    class="mx-auto"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <!-- carousels -->
    <v-carousel
      hide-delimiter-background
      show-arrows-on-hover
      height="300"
    >
      <v-carousel-item
        v-for="(imageUrl, idx) in imageUrls"
        :key="idx"
      >
        <v-img
          :src="imageUrl"
          
          height="100%"
        >
        </v-img>
      </v-carousel-item>
    </v-carousel>    

    <v-card-title>{{ review.title }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="review.rating"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ml-4">
          좋아요 : {{ review.reviewlikeCnt}}
        </div>
      </v-row>

      <div class="my-4 subtitle-1">
        {{ review.createdDate | moment('YYYY년 MM월 DD일 HH시 mm분 ss초') }}
      </div>

      <div>{{ review.content }}</div>
    </v-card-text>
  </v-card>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ReviewCard',
  props: {
    review: Object,
  },
  data: () => ({
    fileIds : [],
    files: [],
    imageUrls: [],
  }),
  methods: {
    getFileIds() {
      this.review.images.forEach(image => {
        // console.log(image.fileId)
        this.fileIds.push(image.fileId)
      })
    },
    getFiles() {
      axios.get(`http://i4d106.p.ssafy.io:8082/file/fileServe`, {
        params: {
          fileIds: this.fileIds.join(',')
        },
      })
      .then(res => {
        // console.log(res.data.data)
        this.files = res.data.data
        this.files.forEach(file => {
          // console.log(file)
          let imageUrl = 'data:image/jpeg;base64,' + file.imageBytes
          // console.log(imageUrl)
          this.imageUrls.push(imageUrl)
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
  },
  created() {
    // console.log(this.review.images)
    this.getFileIds()
    this.getFiles()
    // this.getImageUrl()
  }
  
}
</script>

<style>

</style>