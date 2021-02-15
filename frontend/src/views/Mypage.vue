<template>
  <div style="width: 100%;">
    <v-row style="width: 100%;">
      <v-col cols="12" style="padding-bottom: 0;">
        <MypageAppBar />
      </v-col>
      <v-col cols="2" style="padding-right: 0;">
        <MypageNav />
      </v-col>
      <v-col cols="10" style="padding: 2rem;">
        <router-view />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import MypageAppBar from '../components/mypage/MypageAppBar.vue'
import MypageNav from '../components/mypage/MypageNav.vue'
export default {
  components: { MypageNav, MypageAppBar },
  name: 'Mypage',
  created: function () {
    const token = localStorage.getItem('eureka-authorization')
    if (!token) {
      this.$router.push({ name: 'Login' })
    }
  }
}
</script>

<style>

</style>