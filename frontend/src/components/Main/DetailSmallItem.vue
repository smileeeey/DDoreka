<template>
  <v-carousel
    cycle
    height="300"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <!-- smallItem 6개가 한 슬라이드에 들어 오도록 구성해야한다. -->
    <v-carousel-item
      v-for="(slide, i) in slides"
      :key="i"
    >
      <v-sheet
        :color="colors[i]"
        height="100%"
      >
        <v-row
          class="fill-height"
          align="center"
          justify="center"
        >
        </v-row>
      </v-sheet>
    </v-carousel-item>
  </v-carousel>  
</template>

<script>
export default {
  name: 'DetailSmallItem',
  props: {
    smallItems: Object,
  }
}
</script>

<style>

</style>