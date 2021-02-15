<template>
  <div style="display: flex; border: 3px skyblue solid">
    <!-- {{ e1 }} -->
    <v-row>
      <v-col class="" cols="3">
        <v-select
          v-model="e1"
          :items="items"
          solo
          flat
          item-text="name"
          return-object
          
        ></v-select>
      </v-col>
      <v-col class="" cols="9">
        <v-text-field
          placeholder="찾고 싶은 상품을 검색해보세요!"
          clearable
          solo
          flat
          append-icon="mdi-magnify"
        ></v-text-field>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'SearchBar',
  data: () => ({
      e1: '전체',
      items: ['전체', '여성패션', '남성패션', '뷰티'],
    }),
  created() {
    axios.get(`http://i4d106.p.ssafy.io:8081/category/mainCategory`)
      .then(res => {
        this.items = res.data.data
        this.items.unshift({
          name: '전체',
        })
      })
  },
}
</script>

<style scoped>
  >>> .v-text-field__details {
    display: none;
  }

  >>> .v-input__slot {
    margin: 0;
  }

  >>> .mdi-magnify {
    color: #0275d8 !important;
  }
</style>