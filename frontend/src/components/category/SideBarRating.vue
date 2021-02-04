<template>
  <div>
    <p class="ma-0 mt-3 ml-3 font-weight-bold">별점</p>
    <v-list-item class="pl-2 py-0">
      <v-list-item-content>
        <v-rating
          v-for="n in 4"
          :key="n"
          color="warning"
          empty-icon="mdi-star-outline"
          full-icon="mdi-star"
          half-icon="mdi-star-half-full"
          length="5"
          readonly
          size="12"
          :value="n"
        ></v-rating>          
      </v-list-item-content>
    </v-list-item>
    <v-divider></v-divider>
  </div>  
</template>

<script>
export default {
  name: 'SideBarRating',
}
</script>

<style>

</style>