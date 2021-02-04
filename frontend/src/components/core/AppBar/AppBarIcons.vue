<template>
  <div style="display: flex; justify-content: flex-end;">
    <div class="ml-5 mx-3" style="margin-top: 35px;">
      <div @mouseover="MyEureka=true">
        <v-icon x-large class="mx-3" style="height: 40px;">
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

    <div class="mx-3" style="margin-top: 35px;">
      <div @mouseover="Bucket=true" @click="$router.push({ name: 'Cart' })">
        <v-badge
          :content="cart.number"
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
export default {
  name: 'AppBarIcons',
  data: () => ({
    cart: {
      number: 2
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
}
</script>

<style scoped>
  >>> p {
    white-space: nowrap;
  }
</style>