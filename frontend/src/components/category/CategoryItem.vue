<template>
  <v-card>
    <v-img
      :src="categoryItem.image"
      aspect-ratio="1"
      class="grey lighten-2"
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
    <v-card-title class="pa-0 pl-3 pt-3">{{ categoryItem.name }}</v-card-title>
    <v-card-text>
      <div class="pb-2 subtitle-1 red--text text--darken-4">
        {{ categoryItem.price }}
      </div>
      <v-row
        align="center"
        class="mx-0 py-1"
      >
        <v-rating
          :value="categoryItem.rating"
          color="amber"
          dense
          half-increments
          readonly
          size="17"
        ></v-rating>

        <div class="grey--text ml-1">
          ({{ categoryItem.reviews }})
        </div>
      </v-row>
    </v-card-text>    
  </v-card>
</template>

<script>
export default {
  name: 'CategoryItem',
  props: {
    categoryItem: Object,
  }
}
</script>

<style>

</style>