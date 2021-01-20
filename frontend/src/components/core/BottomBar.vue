<template>
  <v-bottom-navigation
    color="teal"
    app
  >
    <v-btn class="pa-0" to="/">
      <v-icon large color="black">mdi-home</v-icon>
    </v-btn>

    <v-btn class="pa-0">
      <v-icon large color="black">mdi-magnify</v-icon>
    </v-btn>

    <v-btn class="pa-0">
      <v-icon large color="black">mdi-plus-box-outline</v-icon>
    </v-btn>

    <v-btn class="pa-0">
      <v-icon large color="black">mdi-telegram</v-icon>
    </v-btn>

    <v-btn class="pa-0" to="/user/:username">
      <v-icon large color="black">mdi-account-circle-outline</v-icon>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
export default {
  name: 'BottomBar',
  components: {

  }
}
</script>