<template>
  <v-card
    class="mx-auto"
    width="100%"
    flat
  >
    <!-- status bar -->
    <v-card-text
      style="text-align: right; color: black; background-color: #ECECEC"
      class="pa-2"
    >
      <a href="#" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">로그인</a>
      <a href="#" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">회원가입</a>
      <a href="#" class="mx-1" style="color: black; text-decoration:none; word-break: keep-all">고객센터</a>
    </v-card-text>

    <!-- main appbar -->
    <v-card-text
      class="pa-0"
      style="display: flex;"
    >
      <!-- Categories -->
      <Categories />

      <!-- logo(temporary) -->
      <v-img
        src="@/assets/temporary_logo.png"
        class="my-auto mx-5"
        max-height="80"
        max-width="187"
      >
      </v-img>

      <!-- SearchBar -->
      <SearchBar class="my-auto" />
    </v-card-text>
  </v-card>
</template>

<script>
import Categories from './AppBar/Categories.vue'
import SearchBar from './AppBar/SearchBar.vue'
export default {
  name: 'AppBar',
  components: {
    Categories,
    SearchBar,
  },
}
</script>

<style>

</style>