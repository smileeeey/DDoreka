<template>
  <div>
    <div class="mt-5 pl-3 text-h6 font-weight-bold">{{category.name}}</div>
    <div class="pa-4">
      <div>HOT키워드</div>
      <v-chip-group
        column
      >
        <v-chip
          v-for="(keyword, idx) in keywords"
          :key="idx"
        >
          {{ keyword }}
        </v-chip>
      </v-chip-group>
    </div>    
  </div>
</template>

<script>
export default {
  name: 'CategoryRecommendInfo',
  props: {
    category: Object,
    keywords: Object,
  },
}
</script>

<style>

</style>