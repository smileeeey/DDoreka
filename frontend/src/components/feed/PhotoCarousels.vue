<template>
  <v-card
    class="mx-3 my-2"
    tile

  >
    <v-responsive :aspect-ratio="16/9">
    <v-img
      v-if="items.length == 1"
      :src="items[0]"
      height="230"
    />
    <v-carousel 
      v-else
      :show-arrows="false"
      hide-delimiter-background
      delimiter-icon="mdi-circle-medium"
      delimiter-color="black"
      height="230"
    >
      <v-carousel-item
        v-for="(item, idx) in items"
        :key="idx"
        :src="item.src"
        eager
      >
        <!-- <v-btn color="pink" fab small absolute right>
          {{idx+1}} / {{items.length}}
        </v-btn> -->
      </v-carousel-item>
    </v-carousel>
    
    </v-responsive>
  </v-card>
</template>

<script>
export default {
  name: 'PhotoCarousels',
  data () {
    return {
      items: [
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        },
      ],
    }
  }
}
</script>

<style>

</style>