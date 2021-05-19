<template>
  <v-carousel-item @click.native="moveItemDetail">
    <v-img :src="`data:image/jpeg;base64,${bigItem.thumbnail}`" height="100%"> </v-img>
  </v-carousel-item>
</template>

<script>
export default {
  name: 'DetailBigItemOne',
  props: {
    bigItem: Object,
  },
  methods: {
    moveItemDetail: function() {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push({ name: 'ItemDetail', params: { id: 1, productid: this.bigItem.productId } });
    },
  },
};
</script>
