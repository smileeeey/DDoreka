<template>
  <div>
    <p class="ml-5 mt-5 text-h6 font-weight-bold">의류<span class="font-weight-light" style="font-size: 75%;">(285,880)</span></p>
    <v-chip-group class="ml-5">
      <v-chip
        v-for="(sort, idx) in sorts"
        :key="idx"
      >
        {{ sort }}
      </v-chip>
    </v-chip-group>
  </div>
</template>

<script>
export default {
  name: 'TopInfo',
  data: () => ({
    sorts: ['랭킹순', '낮은가격순', '높은가격순', '판매량순', '최신순']
  }),
}
</script>

<style>

</style>