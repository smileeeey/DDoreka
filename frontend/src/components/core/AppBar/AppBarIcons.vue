<template>
  <div style="display: flex;">
    <div class="ml-5 mx-3" style="margin-top: 35px;">
      <v-icon x-large class="mx-3" style="height: 40px;">
        mdi-account-outline
      </v-icon>
      <p>마이유레카</p>
    </div>

    <div class="mx-3" style="margin-top: 35px;">
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
  </div>
</template>

<script>
export default {
  name: 'AppBarIcons',
  data: () => ({
    cart: {
      number: 2
    }
  }),
}
</script>

<style scoped>
  >>> p {
    white-space: nowrap;
  }
</style>