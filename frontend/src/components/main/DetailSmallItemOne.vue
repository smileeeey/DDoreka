<template>
  <v-col class="pa-0" cols="4" style="height: 200px;" @click="moveItemDetail">
    <v-img :src="`data:image/jpeg;base64,${smallItem.thumbnail}`" style="height: 150px;"></v-img>
    <v-card-text class="pa-0" style="height: 50px;">
      <div class="black--text ml-3 text-truncate">
        <!-- 상품이름 -->
        {{ smallItem.name }}
      </div>
      <v-container>
        <v-row>
          <div class="red--text text--darken-4 ml-3">
            <!-- 상품가격 -->
            <p style="margin-top: 1px;">{{ smallItem.price }}</p>
          </div>
          <!-- 평균평점 -->
          <div class="ml-2">
            <v-rating :value="smallItem.rating" color="warning" background-color="warning lighten-1" dense half-increments readonly size="16"></v-rating>
          </div>
        </v-row>
      </v-container>
    </v-card-text>
  </v-col>
</template>

<script>
export default {
  name: 'DetailSmallItemOne',
  props: {
    smallItem: Object,
  },
  methods: {
    moveItemDetail: function() {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push({ name: 'ItemDetail', params: { id: 1, productid: this.smallItem.productId } });
    },
  },
};
</script>

<style></style>
