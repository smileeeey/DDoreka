<template>
  <div>
    <v-container>
      <v-row>
        <v-col
          v-for="(item, idx) in items"
          :key="idx"
          class="d-flex child-flex"
          cols="4"
        >
          <CategoryItem :item="item" />
        </v-col>
      </v-row>
    </v-container>
    <v-divider class="my-16"></v-divider>
  </div>
</template>

<script>
import CategoryItem from './CategoryItem.vue'
export default {
  name: 'CategoryItemList',
  components: {
    CategoryItem,
  },
  props: {
    items: Array,
  },
  data: () => ({

  })
}
</script>

<style>

</style>