<template>
  <v-app>
    <SellerAppBar />
    <SellerDrawer />
    <SellerView />
  </v-app>
</template>

<script>
import SellerAppBar from '../../components/core/SellerComponent/SellerAppBar.vue'
import SellerDrawer from '../../components/core/SellerComponent/SellerDrawer.vue'
import SellerView from '../../components/core/SellerComponent/SellerView.vue'
export default {
  components: { SellerAppBar, SellerDrawer, SellerView },
  name: 'DashboardIndex',
  created: function () {
    const token = localStorage.getItem('seller-eureka-authorization')
    if (!token) {
      this.$router.push({ name: 'SellerLogin' })
    }
  }
}
</script>

<style>

</style>