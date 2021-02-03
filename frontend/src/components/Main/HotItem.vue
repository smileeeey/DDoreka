<template>
  <div>
    <h3 class="my-5">요즘 뜨는 상품 | <span style="font-size: 70%">최근 가장 HOT한 상품</span></h3>
    <v-sheet
      class="mx-auto"
      flat
      outlined
      rounded
    >
      <v-slide-group
        class="pa-2"
        show-arrows
      >
        <v-slide-item
          v-for="n in 15"
          :key="n"
        >
          <v-card
            class="ma-4"
            height="200"
            width="200"
          >
            <v-img src="'https://picsum.photos/500/300?image=15'"></v-img>
          </v-card>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>    
  </div>
</template>

<script>
export default {
  name: 'HotItem',
}
</script>

<style>

</style>