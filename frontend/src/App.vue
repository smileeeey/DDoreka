<template>
  <router-view />
</template>

<script>
import "@/assets/css/common.css"
export default {
  name: 'App',

  components: {
    
  },

  data: () => ({
    //
  }),
  created: function () {
    const token = localStorage.getItem('jwt')
    if (token) {
      this.$store.dispatch('LOGIN')
    }
  }
};
</script>
