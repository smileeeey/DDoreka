<template>
  <v-col cols="10">
    <v-carousel
      cycle
      height="400"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item
        v-for="(bigItem, idx) in bigItems"
        :key="idx"
      >
        <v-sheet
          color="black"
          height="100%"
          outlined
        >
          <v-row
            class="fill-height"
            align="center"
            justify="center"
          >
            <v-img
              :src="bigItem.image"
            >
            </v-img>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>
  </v-col>
</template>

<script>
export default {
  name: 'DetailBigItem',
  props: {
    bigItems: Object,
  }
}
</script>

<style>

</style>