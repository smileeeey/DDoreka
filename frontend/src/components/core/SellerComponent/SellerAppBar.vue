<template>
  <v-app-bar
    absolute
    app
    color="#2D3539"
    dark
    flat
    height="75"
  >
    <v-spacer />

    <v-btn
      class="ml-2"
      min-width="0"
      text
      to="/sell"
    >
      <v-icon>mdi-home</v-icon>
    </v-btn>

    <v-btn
      class="ml-2"
      min-width="0"
      text
      to="/notification"
    >
      <v-icon>mdi-bell</v-icon>
    </v-btn>

    <v-btn
      class="ml-2"
      min-width="0"
      text
      to="/user/profile"
    >
      <v-icon>mdi-account</v-icon>
    </v-btn>
    <v-btn
      class="ml-2"
      min-width="0"
      text
      @click="logout"
    >
      로그아웃
    </v-btn>

  </v-app-bar>
</template>

<script>
export default {
  name: 'SellerAppBar',
  data () {
    return {
    }
  },
  methods: {
    logout() {
      this.$store
        .dispatch("SELLERLOGOUT")
        .then(() => {
          if (this.$route.path !== "/") this.$router.replace("/");
        })
    },
  }
}
</script>

<style>

</style>