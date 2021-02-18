<template>
  <div>
    <h2 class="mb-5">리뷰작성</h2>
    <v-card>
    <v-row class="ml-10" justify="center" align="center">
      <v-col cols="8">
        <v-file-input
          v-model="files"
          label="리뷰사진 등록"
          multiple
          counter
          show-size
          small-chips
          prepend-icon="mdi-camera"
        ></v-file-input>
      </v-col>
      <v-col cols="4" class="pl-4">
        <v-btn color="success" dark small @click="upload">
          Upload
          <v-icon right dark>mdi-cloud-upload</v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-rating
      class="ml-10"
      background-color="warning lighten-1"
      color="warning"
      empty-icon="mdi-star-outline"
      full-icon="mdi-star"
      half-icon="mdi-star-half-full"
      half-increments
      hover
      length="5"
      size="60"
      v-model="rating"
    ></v-rating>
    <v-form class="mx-10 mb-5">
      <v-text-field label="리뷰 제목" v-model="title"></v-text-field>
      <v-textarea label="리뷰 작성" v-model="content"></v-textarea>
      <v-btn text class="success mb-10" @click="saveReview()">리뷰 저장</v-btn>
    </v-form>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CreateReview',
  props: {
    orderId: String,
    optionId: String,
    productId: String,
    userId: String,
  },
  data: () => ({
    files: [],
    rating: 0,
    title: '',
    content: '',
    images: [],
  }),
  methods: {
    upload() {
      const formData = new FormData()
      // for (let i = 0; i < this.files.length; i++) {
      //   let file = this.files[i]
      //   formData.append('files', file)
      // }
      this.files.forEach(file => {
        formData.append('files', file)
      })
      // for (let [key, value] of formData.entries()) {
      //   console.log(`${key}: ${value}`)
      // }
      axios.post('http://i4d106.p.ssafy.io:8082/file/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then(res => {
        // console.log('success')
        console.log(res.data.data)
        alert('이미지 저장 성공')
        res.data.data.forEach(image => {
          this.images.push({ fileId: image.id })
        })
      })
      .catch(err => {
        console.log(err)
      })
    },
    saveReview() {
      console.log('saveReview!')
      axios.post('http://i4d106.p.ssafy.io:8083/review/write', {
        review: {
          orderId: this.orderId,
          optionId: this.optionId,
          productId: this.productId,
          userId: this.userId,
          rating: this.rating,
          title: "'" + this.title + "'",
          content: "'" + this.content + "'",
        },
        image: this.images
      })
      .then(res => {
        console.log(res)
        alert('리뷰 등록 성공')
        this.$router.push({ name: 'ProductReview' })
      })
      .catch(err => {
        console.log(err)
      })
    }
  },
  // watch: {
  //   files() {
  //     console.log(this.files)
  //   }
  // }
}
</script>

<style>

</style>