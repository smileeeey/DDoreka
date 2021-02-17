<template>
  <div>
    <div class="mt-5 pl-3 text-h6 font-weight-bold">{{ category1Name[categoryKey] }}</div>
    <div class="pa-4">
      <div>HOT키워드</div>
      <v-chip-group
        column
      >
        <v-chip
          v-for="(keyword, idx) in keywords"
          :key="idx"
          @click="moveSearchHotKeyword(keyword)"
        >
          {{ keyword }}
        </v-chip>
      </v-chip-group>
    </div>    
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'CategoryRecommendInfo',
  props: {
    categoryKey: String,
    keywords: Array,
  },
  computed: {
    ...mapState([
      'category1Name'
    ])
  },
  methods: {
    moveSearchHotKeyword(keyword) {
      this.$router.push({name: 'Search', params: {category1id: this.categoryKey, keyword: keyword}})
    }
  },
}
</script>

<style>

</style>