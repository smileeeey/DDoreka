<template>
  <v-card
    class="mx-auto"
    max-width="100%"
    v-if="item.select == true"
  >
    <v-card-text class="my-3" style="display: flex; margin-bottom: 0 !important;">
      <div>
        <v-img
          :src="`data:image/jpeg;base64,${item.img}`"
          height="120"
          width="120"
        ></v-img>
      </div>
      <div>
        <v-card-title
          style="font-size: 1.25rem; color: black;"
        >{{item.name}}</v-card-title>
        <v-card-subtitle
          style="font-size: 1rem;"
        >
          {{item.cost|comma}}원 · {{item.amount}}개
        </v-card-subtitle>
      </div>
      
    </v-card-text>

    
  </v-card>
</template>

<script>
export default {
  name: 'PaymentCompleteCard',
  props: {
    item: Object,
  }
}
</script>

<style>

</style>