<template>
<div style="width: 100%;">
  <v-row style="width: 100%;">
    <v-col cols="2">
      <MypageNav />
    </v-col>
    <v-col cols="10">
      <router-view />
    </v-col>
  </v-row>
</div>
</template>

<script>
import MypageNav from '../components/mypage/MypageNav.vue'
export default {
  components: { MypageNav },
  name: 'Mypage',
  created: function () {
    const token = localStorage.getItem('jwt')
    if (!token) {
      this.$router.push({ name: 'Login' })
    }
  }
}
</script>

<style>

</style>