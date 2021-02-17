<template>
  <v-card>
    <v-container>
      <v-row>
        <v-col
          cols="12"
          md="7"
        >
          썸네일 이미지 등록
          <v-file-input
            v-model="simages"
            multiple
            solo
            chips
            show-size
            counter
            outlined
            dense
            prepend-icon="mdi-camera"
            label="썸네일 이미지"
          ></v-file-input>
        </v-col>
        <v-col
          cols="12"
          md="7"
        >
          상품 상세 이미지 등록
          <v-file-input
            v-model="mimages"
            multiple
            solo
            chips
            show-size
            counter
            outlined
            dense
            prepend-icon="mdi-camera"
            label="상세 이미지"
          ></v-file-input>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: 'RegisterProductImage',
  data() {
    return {
      simages: [],
      mimages: [],
    }
  },
  watch: {
    simages: function () {
      this.$emit("simage", this.simages)
    },
    mimages: function () {
      this.$emit("mimage", this.mimages)
    },
  }
}
</script>

<style>

</style>