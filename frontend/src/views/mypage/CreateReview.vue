<template>
  <div>
    <h2 class="mb-5">리뷰작성</h2>
    <v-row justify="center" align="center">
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
    <v-text-field></v-text-field>
    <v-text-field></v-text-field>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'CreateReview',
  data: () => ({
    files: [],
    orderId: '',
    optionId: '',
    productId: '',
    userId: '',
    rating: '',
    createdDate: '',
    edited: 0,
    title: '',
    content: '',
    reviewlikeCnt: 0,
    liked: false,
    images: [],
  }),
  methods: {
    upload: function() {
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
        res.data.data.forEach(image => {
          this.images.push({ fileId: image.id })
        })
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