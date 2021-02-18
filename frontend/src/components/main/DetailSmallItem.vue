<template>
  <v-carousel
    cycle
    height="400"
    hide-delimiter-background
    show-arrows-on-hover
  >
    <!-- smallItem 6개가 한 슬라이드에 들어 오도록 구성해야한다. -->
    <v-carousel-item
      v-for="(slide, idx) in smallItems"
      :key="idx"
    >
      <v-container class="fill-height" style="box-sizing: border-box;">
        <v-row
          class=""
          align="center"
          justify="center"
          style="height: 400px;"
        >
          <DetailSmallItemOne
            v-for="(smallItem, idx) in slide"
            :key="idx"
            :smallItem="smallItem"
          />
        </v-row>
      </v-container>
    </v-carousel-item>
  </v-carousel>  
</template>

<script>
import DetailSmallItemOne from './DetailSmallItemOne.vue'
export default {
  name: 'DetailSmallItem',
  components: {
    DetailSmallItemOne,
  },
  props: {
    smallItems: Array,
  }
}
</script>

<style>

</style>