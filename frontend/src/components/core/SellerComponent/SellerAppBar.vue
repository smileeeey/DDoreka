<template>
  <v-app-bar
    absolute
    app
    color="#2D3539"
    dark
    flat
    height="75"
  >
    <v-toolbar-title 
      class="font-weight-light"
      v-text="$route.name"
    />

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
      <v-badge
        color="red"
        overlap
        bordered
      >
        <template v-slot:badge>
          <span>{{alarm}}</span>
        </template>
        <v-icon>mdi-earth</v-icon>
      </v-badge>
    </v-btn>

    <v-btn
      class="ml-2"
      min-width="0"
      text
      to="/user/profile"
    >
      <v-icon>mdi-account</v-icon>
    </v-btn>

  </v-app-bar>
</template>

<script>
export default {
  name: 'SellerAppBar',
  data () {
    return {
      alarm: 3,
    }
  }
}
</script>

<style>

</style>