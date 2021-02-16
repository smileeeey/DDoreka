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
          <SearchItem :item="item" />
        </v-col>
      </v-row>
    </v-container>
    <v-divider class="my-16"></v-divider>
  </div>
</template>

<script>
import SearchItem from './SearchItem.vue'
export default {
  name: 'SearchItemList',
  components: {
    SearchItem,
  },
  props: {
    items: Array,
  },
  data: () => ({

  }),
}
</script>

<style>

</style>