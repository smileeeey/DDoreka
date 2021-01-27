<template>
  <div>
    <v-app-bar
      color="white"
      dense
      app
    >
      <v-spacer></v-spacer>

      <v-img
        class="ml-8"
        src="@/assets/img/logo.png"
        alt="logo"
        max-height="45"
        max-width="70"
      >
      </v-img>

      <v-spacer></v-spacer>

      <v-btn icon>
        <v-icon color="black">mdi-bell</v-icon>
      </v-btn>
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: 'AppBar',
  components: {

  }
}
</script>