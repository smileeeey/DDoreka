<template>
  <div style="display: flex; border: 3px skyblue solid">
    <v-select
      v-model="e1"
      :items="items"
      solo
      flat
      style="width: 130px"
    ></v-select>

    <v-text-field
      
      placeholder="찾고 싶은 상품을 검색해보세요!"
      clearable
      solo
      flat
      style="width: 280px"
    ></v-text-field>
    <v-icon large color="blue">
      mdi-magnify
    </v-icon>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data: () => ({
      e1: '전체',
      items: ['전체', '여성패션', '남성패션', '뷰티'],
    }),
}
</script>

<style scoped>
  >>> .v-text-field__details {
    display: none;
  }

  >>> .v-input__slot {
    margin: 0;
  }
</style>