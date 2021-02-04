<template>
  <!-- <div>{{ todayItem.src }}</div> -->
  <v-img
    :src="todayItem.src"
    aspect-ratio="1"
    class="grey lighten-2"
    @click="moveItemDetail"
  >
    <template v-slot:placeholder>
      <v-row
        class="fill-height ma-0"
        align="center"
        justify="center"
      >
        <v-progress-circular
          indeterminate
          color="grey lighten-5"
        ></v-progress-circular>
      </v-row>
    </template>
  </v-img>
</template>

<script>
export default {
  name: 'TodayItem',
  props: {
    todayItem: Object,
  },
  methods: {
    moveItemDetail: function () {
      // 추후 Item Detail페이지 url을 입력
      this.$router.push('/')
    }
  }
}
</script>

<style>

</style>