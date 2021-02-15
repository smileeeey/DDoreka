<template>
  <div class="mb-16 py-5 text-center">
    <v-pagination
      v-model="page"
      :length="10"
    ></v-pagination>
  </div>  
</template>

<script>
export default {
  name: 'Pagination',
  data: () => ({
    page: 1,
  }),
}
</script>

<style>

</style>