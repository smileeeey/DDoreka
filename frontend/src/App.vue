<template>
  <router-view />
</template>

<script>
import "@/assets/css/common.css";

export default {
  name: "App",

  components: {},

  data: () => ({
    //
  }),
  methods: {},
  created() {},
  beforeDestroy() {
    sessionStorage.removeItem("seller-eureka-authorization");
    sessionStorage.removeItem("eureka-authorization");
  },
};
</script>
