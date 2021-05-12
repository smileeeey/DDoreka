<template>
  <v-carousel cycle height="500" hide-delimiter-background show-arrows-on-hover style="border-box">
    <v-carousel-item v-for="(imageUrl, idx) in imageUrls" :key="idx">
      <v-img :src="imageUrl" height="100%"> </v-img>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  name: 'TopInfoCarousels',
  props: {
    sFiles: Array,
  },
  data: () => ({
    imageUrls: [],
  }),
  methods: {
    getImageUrl() {
      this.sFiles.forEach((sFile) => {
        // 썸네일 갯수만큼 돌면서 imageUrls에 사진넣어주기
        let imageUrl = 'data:image/jpeg;base64,' + sFile.imageBytes;
        this.imageUrls.push(imageUrl);
      });
    },
  },
  created() {
    this.getImageUrl();
  },
};
</script>

<style></style>
