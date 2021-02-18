<template>
  <div style="display: flex; justify-content: flex-end;">
    <div class="ml-5 mx-3" style="margin-top: 26px;">
      <div @mouseover="MyEureka=true" @click="$router.push({ name: 'MyFace' })" style="cursor: pointer">
        <v-icon x-large class="ml-5" style="height: 40px;">
          mdi-account-outline
        </v-icon>
        <p>마이유레카</p>
      </div>
      <!-- <div style="display: relative;" >
        <v-card
          class="mx-auto"
          width="110"
          height="150"
          tile
          style="position: absolute; z-index: 1;"
          v-show="MyEureka"
        >
          <v-list-item v-for="(item, idx) in items" :key="idx">
            <v-list-item-content>
              <v-list-item-title v-text="item"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </div> -->
    </div>

    <div class="mx-3" style="margin-top: 26px;">
      <div @mouseover="Bucket=true" @click="gotoCart" style="cursor: pointer;">
        <v-badge
          :content="cartLength"
          color="blue"
          overlap
          bordered
          style="height: 40px;"
        >
          <v-icon large class="mx-3 cart" style="margin-right: 0px !important;">
            mdi-cart-outline
          </v-icon>
        </v-badge>
        <p>장바구니</p>
      </div>
      <!-- <div style="display: relative;" >
        <v-card
          class="mx-auto"
          width="110"
          height="150"
          tile
          style="position: absolute; z-index: 1;"
          v-show="Bucket"
        >
          <v-list-item v-for="(item, idx) in carts" :key="idx">
            <v-list-item-content>
              <v-list-item-title v-text="item"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </div> -->
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'AppBarIcons',
  data: () => ({
    cart: {
      number: 1
    },
    items: [
      '주문목록',
      '취소/반품',
      '찜 리스트',
    ],
    carts: [
      'item1',
      'item2',
      'item3',
    ],
    MyEureka: false,
    Bucket: false,
  }),
  computed: {
    ...mapState([
      'login',
      'email',
      'wishlist',
    ]),
    cartLength () {
      if (this.wishlist.length > 0) {
        return this.wishlist.length
      } else {
        return '0'
      }
    }
  },

  methods: {
    gotoCart: function () {
      if (this.login) {
        this.$router.push({ name: 'Cart' })
      } else {
        this.$router.push({ name: 'Login', query: { next: 'Cart' } })
      }
      
    }
    
  }
}
</script>

<style scoped>
  >>> p {
    white-space: nowrap;
  }
</style>