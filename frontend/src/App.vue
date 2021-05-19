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
  created(){
    // const token = sessionStorage.getItem('seller-eureka-authorization')
  },
  beforeDestroy() {
    sessionStorage.removeItem("seller-eureka-authorization");
    sessionStorage.removeItem("eureka-authorization");
  },
};
</script>
