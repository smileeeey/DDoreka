<template>
  <div>
    <h2>This is Mypage Page</h2>
    <router-view />
  </div>
</template>

<script>
export default {
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