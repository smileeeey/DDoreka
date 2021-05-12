<template>
  <v-img :src="sumnailUrl" aspect-ratio="1" class="grey lighten-2" @click="moveItemDetail">
    <template v-slot:placeholder>
      <v-row class="fill-height ma-0" align="center" justify="center">
        <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
      </v-row>
    </template>
  </v-img>
</template>

<script>
import { mapActions, mapState } from 'vuex';
export default {
  name: 'TodayItem',
  props: {
    todayItem: Object,
  },
  computed: {
    ...mapState('mainStore', ['item', 'sumnailUrl']),
  },
  methods: {
    ...mapActions('mainStore', ['FIND_DETAIL_PRODUCT', 'FETCH_FILE']),

    getItem() {
      // 상품 상세조회
      this.FIND_DETAIL_PRODUCT_AND_FETCH_FILE(this.todayItem.id);
    },
    moveItemDetail: function() {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push({
        name: 'ItemDetail',
        params: { id: this.item.category3Id, productid: this.item.id },
      });
    },
  },
  created() {
    this.getItem();
  },
};
</script>
