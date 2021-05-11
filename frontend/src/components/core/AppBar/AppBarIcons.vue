<template>
  <div style="display: flex; justify-content: flex-end">
    <div class="ml-5 mx-3" style="margin-top: 26px">
      <div
        @click="$router.push({ name: 'MyFace' })"
        style="cursor: pointer"
      >
        <v-icon x-large class="ml-5" style="height: 40px">
          mdi-account-outline
        </v-icon>
        <p>마이유레카</p>
      </div>
    </div>

    <div class="mx-3" style="margin-top: 26px">
      <div @click="gotoCart" style="cursor: pointer">
        <v-badge
          :content="cartLength"
          color="blue"
          overlap
          bordered
          style="height: 40px"
        >
          <v-icon large class="mx-3" style="margin-right: 0px !important">
            mdi-cart-outline
          </v-icon>
        </v-badge>
        <p>장바구니</p>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: "AppBarIcons",
  data: () => ({}),
  computed: {
    ...mapState('accountStore',["userData","wishlist"]),
    
    cartLength() {
      let count = "0";

      if (this.wishlist.length > 0) {
        count = this.wishlist.length;
      }

      return count;
    },
  },

  methods: {
    gotoCart() {
      this.$router.push({ name: "Cart" }).catch(() => {});
    },
  },
};
</script>

<style scoped>
>>> p {
  white-space: nowrap;
}
</style>